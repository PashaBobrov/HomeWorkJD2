package iClinic.controller;

import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import iClinic.view.api.ISpecialisationView;
import iClinic.view.api.ITimeTableView;
import iClinic.view.api.IUsersView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/timetableCard")
public class TimeTableCardServlet {

    private final ITimeTableView timeTableView;
    private final IUsersView usersView;

    public TimeTableCardServlet(ITimeTableView timeTableView, IUsersView usersView) {
        this.timeTableView = timeTableView;
        this.usersView = usersView;

    }


    @GetMapping(produces = {"text/html"})
    protected String getTimeTableCard(Model model, HttpSession session)  {
        User currUser = (User) session.getAttribute("User");
        model.addAttribute("doctor", currUser);
        return "timetableCard";
    }

    @PostMapping(produces = {"text/html"})
    public String postTimeTable(Model model, @RequestParam Map<String,String> allParams) throws ParseException {

        int id = Integer.parseInt(allParams.get("doctorId"));
        User doctor = usersView.getById(id);
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dateTime = inFormat.parse((allParams.get("dateTime")));
        TimeTable timeTable = new TimeTable();
        timeTable.setDoctor(doctor);
        timeTable.setDatetime(dateTime);
        //timeTable.setId(666);
        timeTableView.saveTimeTable(timeTable);

        return "redirect:timetable";
    }
}
