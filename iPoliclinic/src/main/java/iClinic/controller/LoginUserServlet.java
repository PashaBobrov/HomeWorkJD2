package iClinic.controller;

import iClinic.model.TimeTable;
import iClinic.model.user.User;
import iClinic.view.api.IUsersView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/loginUser")
public class LoginUserServlet {

    private final IUsersView usersView;

    public LoginUserServlet(IUsersView usersView) {
        this.usersView = usersView;
    }

    @GetMapping(produces = {"text/html"})
    protected String getAllHTML(Model model)  {
        return "loginUser";
    }


    @PostMapping(value = "/loginUser", produces = {"text/html"})
    @ResponseStatus(HttpStatus.CREATED)
    public String signIn(@RequestParam Map<String,String> allParams){
        int a = 0;
        return "";
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Airport> airports = this.airportView.getAll();
//
//        req.setAttribute("airports", airports);
//        req.getRequestDispatcher("/views/airports.jsp").forward(req, resp);
//    }
}
