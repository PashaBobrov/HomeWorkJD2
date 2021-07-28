package chat.controller.web.servlets;

import chat.model.User;
import chat.view.Chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;

/**
 * Класс сервлет ChatServlet.
 * Данный сервлет запускается по URL /chat
 * отражает на странице сообщения отправленные для текущего пользователя
 * @version 1.1
 */
@Controller
@RequestMapping(value = "/chat")
public class ChatServlet {

    private final Chat chat;

    public ChatServlet(Chat chat) {
        this.chat = chat;
    }

    @GetMapping()
    protected String doGet(Model model, @SessionAttribute User user) {
        model.addAttribute("listMessage", chat.getMessagelist(user.getLogin()));
        return "/chat.jsp";

    }


}

