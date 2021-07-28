package chat.controller.web.servlets;

import chat.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Класс сервлет SelectPageServlet.
 * Данный сервлет запускается по URL /selectPage
 * предназначен для выбора пользователям страницы для перехода
 * на выбор страница добавления сообщений и просмотра чата
 * @version 1.1
 */
@Controller
@RequestMapping(value = "/selectPage")
public class SelectPageServlet {

    @PostMapping
    protected String doPost(@RequestParam(defaultValue = "") String message, @RequestParam(defaultValue = "") String chat) {

        if(!message.isEmpty()){
            return "redirect:message";
        } else if(!chat.isEmpty()){
            return "redirect:chat";
        } else {
            return "redirect:signIn";
        }
    }

    @GetMapping
    protected String doGet(Model model,@SessionAttribute User user) {
        model.addAttribute("user",user);
        return "/selectPage.jsp";
    }


}

