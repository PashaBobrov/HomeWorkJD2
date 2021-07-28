package iClinic.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.Request;
import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import iClinic.view.UsersView;
import iClinic.view.api.ISpecialisationView;
import iClinic.view.api.ITimeTableView;
import net.minidev.json.JSONObject;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/timetable")
public class TimeTableServlet {

    private final ITimeTableView timeTableView;
    private final ISpecialisationView specialisationView;

    public TimeTableServlet(ITimeTableView timeTableView, ISpecialisationView specialisationView) {
        this.timeTableView = timeTableView;
        this.specialisationView = specialisationView;

    }

    @GetMapping(produces = {"text/html"})
    protected String getAllHTML(Model model, HttpSession session)  {
        setModelParam(model, session);
        return "timetable";
    }

    @GetMapping(value = "/sp/{id}", produces = {"text/html"})
    protected String getSpecialisationByParam(Model model,  HttpSession session, @PathVariable Integer id)  {
        session.setAttribute("specialisationId", id);
        setModelParam(model, session);
        return "timetable";
    }

    @DeleteMapping
    public void deleteTimeTable(Model model, @RequestBody HashMap<String, String> param, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, String> result = new HashMap<>();
        try {
            TimeTable timeTable = timeTableView.getById(Integer.parseInt(param.get("id")));
            timeTableView.deleteTimeTable(timeTable);
            result.put("redirect",req.getContextPath() + "/timetable");
        }
        catch (IllegalArgumentException e) {
            result.put("redirect",req.getContextPath() + "/timetable");
        }
        finally {
            printResult(result, resp);
        }
    }

    @PatchMapping
    public void updateTime(Model model, @RequestBody HashMap<String, String> param, HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, String> result = new HashMap<>();
        try {
            TimeTable timeTable = timeTableView.getById(Integer.parseInt(param.get("id")));
            String doSome = param.get("do");
            if(doSome.equals("book")) {
                User currUser = (User) session.getAttribute("User");
                if(currUser != null){
                    timeTable.setPatient(currUser);
                }
            } else { //empty
                timeTable.setPatient(null);
            }
            timeTableView.saveTimeTable(timeTable);
            result.put("redirect",req.getContextPath() + "/timetable");
        }
        catch (IllegalArgumentException e) {
            result.put("redirect",req.getContextPath() + "/timetable");
        }
        finally {
            printResult(result, resp);
        }
    }

    private void printResult(HashMap<String, String> result,  HttpServletResponse resp) throws IOException {
        JSONObject respObject = new JSONObject(result);
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        printWriter.print(respObject);
        printWriter.flush();
    }

    private void setModelParam(Model model, HttpSession session) {

        User currUser = (User) session.getAttribute("User");

        Specialisation specialisation = null;
        List<TimeTable> listTimeTable = null;
        Integer specialisationId = (Integer) session.getAttribute("specialisationId");
        if (specialisationId == null) {
            listTimeTable = timeTableView.getAll();
        } else {
            specialisation = specialisationView.getById(specialisationId);
            listTimeTable = timeTableView.getBySpecialisation(specialisation);
        }
        model.addAttribute("listTimeTable", listTimeTable);
        List<Specialisation> listSpecialisation = specialisationView.getAll();
        model.addAttribute("listSpecialisation", listSpecialisation);
        model.addAttribute("specialisation", specialisation);
        model.addAttribute("roleDoctor",currUser.getRole().getId() == 1);

    }

}
