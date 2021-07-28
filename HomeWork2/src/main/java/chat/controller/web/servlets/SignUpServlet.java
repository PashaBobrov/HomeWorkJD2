package chat.controller.web.servlets;

import chat.model.User;
import chat.view.UserFlow;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс сервлет SignUpServlet.
 * Данный сервлет запускается по URL /signUp
 * предназначен для регистрации пользователя
 * @version 1.1
 */
@WebServlet(name = "SignUpServlet",urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    final String PARAM_FIO = "FIO";
    final String PARAM_DATE_OF_BIRTH = "dateOfBirth";
    final String PARAM_LOGIN = "login";
    final String PARAM_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String valueFIO = req.getParameter(PARAM_FIO);
        String valueDate = req.getParameter(PARAM_DATE_OF_BIRTH);
        String valueLogin = req.getParameter(PARAM_LOGIN);
        String valuePassword = req.getParameter(PARAM_PASSWORD);

        Date bufDate = null;
        try {
            bufDate = new SimpleDateFormat("dd.MM.uuuu").parse(valueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserFlow userFlow = UserFlow.getINSTANCE();

        if (userFlow.isNewUser(valueLogin)) {
            userFlow.add(new User(valueFIO, valueLogin, valuePassword, bufDate));
        } else {
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.write("<p><span style='color: blue;'>" + valueLogin + "User is busy!</span></p>");
            resp.sendRedirect(req.getContextPath() + "/signUp");
        }
        resp.sendRedirect(req.getContextPath() + "/signIn");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/signUp.jsp").forward(req, resp);

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

