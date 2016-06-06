package domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CardioController {

    @RequestMapping("/cardio")
    String index() {
        return "cardio/index";
    }
}
