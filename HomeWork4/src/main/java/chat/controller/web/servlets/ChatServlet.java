package chat.controller.web.servlets;

import chat.model.User;
import chat.view.Chat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Класс сервлет ChatServlet.
 * Данный сервлет запускается по URL /chat
 * отражает на странице сообщения отправленные для текущего пользователя
 * @version 1.1
 */
@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Chat chat = Chat.getINSTANCE();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("listMessage", chat.getMessagelist(user.getLogin()));
        getServletContext().getRequestDispatcher("/chat.jsp").forward(req, resp);

    }


}

