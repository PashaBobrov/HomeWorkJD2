package chat.controller.web.servlets;

import chat.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Класс сервлет SelectPageServlet.
 * Данный сервлет запускается по URL /selectPage
 * предназначен для выбора пользователям страницы для перехода
 * на выбор страница добавления сообщений и просмотра чата
 * @version 1.1
 */
@WebServlet(name = "SelectPageServlet",urlPatterns = "/selectPage")
public class SelectPageServlet extends HttpServlet {

    final String BUTTON_MESSAGE = "message";
    final String BUTTON_CHAT = "chat";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter(BUTTON_MESSAGE) != null){
            resp.sendRedirect(req.getContextPath() + "/message");
        } else if(req.getParameter(BUTTON_CHAT) != null){
            resp.sendRedirect(req.getContextPath() + "/chat");
        } else {

            resp.sendRedirect(req.getContextPath() + "/signIn");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user",user);
        getServletContext().getRequestDispatcher("/selectPage.jsp").forward(req, resp);
    }


}

