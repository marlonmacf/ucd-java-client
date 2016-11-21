package domain.controllers;

import domain.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    static User loggedUser;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index(Model model) {

        model.addAttribute("loggedUser", loggedUser);
        return "main/index";
    }
}
