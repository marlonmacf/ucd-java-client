package domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static domain.controllers.MainController.loggedUser;

@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String index(Model model) {
        loggedUser = null;

        model.addAttribute("loggedUser", null);
        return "main/index";
    }
}
