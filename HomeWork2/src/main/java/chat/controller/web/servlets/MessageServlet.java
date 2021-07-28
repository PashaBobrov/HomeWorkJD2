package chat.controller.web.servlets;

import chat.model.User;
import chat.model.Message;
import chat.view.Chat;
import chat.view.StorageSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Класс сервлет MessageServlet.
 * Данный сервлет запускается по URL /message
 * читает с формы и добавляет в чат сообщения пользователя
 * @version 1.1
 */
@WebServlet(name = "MessageServlet",urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    final String PARAM_USER = "user";
    final String PARAM_TEXT = "messageText";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String valueUser = req.getParameter(PARAM_USER);
        String valueMessage = req.getParameter(PARAM_TEXT);

        if(valueUser == null || valueMessage == null) {
            resp.sendRedirect(req.getContextPath() + "/message");
            return;
        }
        Chat chat = Chat.getINSTANCE();
        User user = (User) new StorageSession(req).getValue("user");
        chat.add(new Message(valueUser,user.getLogin(),valueMessage));
        resp.sendRedirect(req.getContextPath() + "/selectPage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/message.jsp").forward(req, resp);
    }


}

