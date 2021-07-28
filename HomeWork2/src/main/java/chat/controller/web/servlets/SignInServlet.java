package chat.controller.web.servlets;


import chat.model.User;
import chat.view.StorageSession;
import chat.view.UserFlow;
import chat.Storage.api.IStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс сервлет SignInServlet.
 * Данный сервлет запускается по URL /signIn
 * предназначен для аутентификации пользователя
 * в случае если нажата кнопка BUTTON_REGISTRATION, переход на страницу регистрации
 * @version 1.1
 */
@WebServlet(name = "SignInServlet",urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    final String PARAM_LOGIN = "login";
    final String PARAM_PASSWORD = "password";
    final String BUTTON_REGISTRATION = "registration";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter(BUTTON_REGISTRATION) != null){
            resp.sendRedirect(req.getContextPath() + "/signUp");
            return;
        }

        String valueLogin = req.getParameter(PARAM_LOGIN);
        String valuePassword = req.getParameter(PARAM_PASSWORD);

        UserFlow userFlow = UserFlow.getINSTANCE();

        User user = userFlow.getUser(valueLogin);
        boolean flagauthentication = false;
        if(user != null) {
            if(user.getAcсess(valuePassword)) {
                IStorage storage = new StorageSession(req);
                storage.setValue("user",user);
                resp.sendRedirect(req.getContextPath() + "/selectPage");
                flagauthentication = true;
            }
        }

        if(!flagauthentication) {
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.write("<p><span style='color: blue;'>Wrong parameters!</span></p>");
            resp.sendRedirect(req.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/signIn.jsp").forward(req, resp);
    }
}

