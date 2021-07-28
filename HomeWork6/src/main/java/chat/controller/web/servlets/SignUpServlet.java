package chat.controller.web.servlets;

import chat.model.User;
import chat.view.UserFlow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Класс сервлет SignUpServlet.
 * Данный сервлет запускается по URL /signUp
 * предназначен для регистрации пользователя
 * @version 1.1
 */
@Controller
@RequestMapping(value = "/signUp")
public class SignUpServlet {

    private final UserFlow userFlow;


    final String PARAM_FIO = "fio";
    final String PARAM_DATE_OF_BIRTH = "dateOfBirth";
    final String PARAM_LOGIN = "login";
    final String PARAM_PASSWORD = "password";

    public SignUpServlet(UserFlow userFlow) {
        this.userFlow = userFlow;
    }

    @PostMapping
    protected String doPost(@RequestParam Map<String,String> allParams)  {

        String login = allParams.get("login");
        String fio = allParams.get("fio");
        String dateOfBirth = allParams.get("dateOfBirth");
        String password = allParams.get("password");

        Date bufDate = null;
        try {
            bufDate = new SimpleDateFormat("dd.MM.uuuu").parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (userFlow.isNewUser(login)) {
            userFlow.add(new User(fio, login, password, bufDate));
        } else {
            return "/signUp.jsp";
        }
        return "/signIn.jsp";
    }


    private boolean checkValueNotNULL(String... values) {
        for (String value : values) {
            if (value == null) {
                return false;
            }
        }
        return true;
    }

}

