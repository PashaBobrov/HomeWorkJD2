package iClinic.controller;

import iClinic.view.api.IUsersView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationServlet {

    private final IUsersView usersView;

    public RegistrationServlet(IUsersView usersView) {
        this.usersView = usersView;
    }

    @GetMapping(produces = {"text/html"})
    protected String getAllHTML(Model model)  {
        return "registration";
    }


    @PostMapping(value = "/registration", produces = {"text/html"})
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
