package domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardioController {

    @RequestMapping(value = "/cardio", method = RequestMethod.GET)
    String index(@RequestParam(value = "app", defaultValue = "app") String app) {
        return "cardio/index";
    }
}
