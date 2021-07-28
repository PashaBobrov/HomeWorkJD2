package chat.controller.web.servlets;

import chat.model.User;
import chat.view.UserFlow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Класс сервлет SignInServlet.
 * Данный сервлет запускается по URL /signIn
 * предназначен для аутентификации пользователя
 * в случае если нажата кнопка BUTTON_REGISTRATION, переход на страницу регистрации
 * @version 1.1
 */
@Controller
@RequestMapping(value = "/signIn")
public class SignInServlet {

    final UserFlow userFlow;


    final String PARAM_LOGIN = "login";
    final String PARAM_PASSWORD = "password";
    final String BUTTON_REGISTRATION = "registration";

    public SignInServlet(UserFlow userFlow) {
        this.userFlow = userFlow;
    }

    @PostMapping
    protected String doPost(HttpServletResponse resp, HttpSession session, @RequestParam Map<String,String> allParams) {

        String registration = allParams.get(BUTTON_REGISTRATION);
        if(registration != null){
            return "/signUp.jsp";
        }

        String login = allParams.get(PARAM_LOGIN);
        String password = allParams.get(PARAM_PASSWORD);

        User user = userFlow.getUser(login);
        if(user != null) {
            if(user.getAcсess(password)) {
                session.setAttribute("user",user);
                return  "/selectPage.jsp";
            }
        }

        try {
            resp.setContentType("text/html");
            resp.getWriter().write("<p><span style='color: blue;'>Wrong parameters!</span></p>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/signIn.jsp";
    }


}

