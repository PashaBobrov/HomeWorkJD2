package iClinic.controller;

import iClinic.model.user.User;
import iClinic.view.api.IUsersView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/index")
public class indexServlet {

    private final IUsersView usersView;

    public indexServlet(IUsersView usersView) {
        this.usersView = usersView;
    }

    @GetMapping(produces = {"text/html"})
    protected String getAllHTML(Model model,  HttpSession session)  {
        User currUser = (User) session.getAttribute("User");
        if(currUser == null){
            session.setAttribute("User", usersView.getById(1));
            //session.setAttribute("User", usersView.getById(2));
        }
        return "redirect:timetable";
    }


}
