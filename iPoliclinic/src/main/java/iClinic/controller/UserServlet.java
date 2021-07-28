package iClinic.controller;

import iClinic.model.TimeTable;
import iClinic.model.user.User;
import iClinic.view.api.IUsersView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserServlet {

    private final IUsersView usersView;

    public UserServlet(IUsersView usersView) {
        this.usersView = usersView;
    }

    @GetMapping(produces = {"text/html"})
    protected String getAllHTML(Model model)  {
        List<User> users = this.usersView.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/{id}", produces = {"text/html"})
    protected String getTimeTableByParam(Model model, @PathVariable int id)  {
        User user = this.usersView.getById(id);
        model.addAttribute("user", user);
        return "userCard";
    }


    @PostMapping(produces = {"text/html"})
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody TimeTable timeTable){

    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Airport> airports = this.airportView.getAll();
//
//        req.setAttribute("airports", airports);
//        req.getRequestDispatcher("/views/airports.jsp").forward(req, resp);
//    }
}
