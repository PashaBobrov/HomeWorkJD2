package chat.controller.web.servlets;

import chat.model.User;
import chat.model.Message;
import chat.view.Chat;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Класс сервлет MessageServlet.
 * Данный сервлет запускается по URL /message
 * читает с формы и добавляет в чат сообщения пользователя
 * @version 1.1
 */
@Controller
@RequestMapping(value = "/message")
public class MessageServlet {

    private final Chat chat;

    public MessageServlet(Chat chat) {
        this.chat = chat;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@RequestParam String valueUser,@RequestParam String valueMessage, @SessionAttribute User user){

        if(valueUser == null || valueMessage == null) {
            return "/message.jsp";
        }

        chat.add(new Message(valueUser,user.getLogin(),valueMessage));
        return "/selectPage.jsp";
    }

    @GetMapping
    protected String doGet(Model model) {
            return "/message.jsp";
    }


}

