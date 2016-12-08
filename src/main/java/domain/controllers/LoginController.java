package domain.controllers;

import domain.entities.User;
import domain.rest.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import static domain.controllers.MainController.loggedUser;

@Controller
public class LoginController {

    private ApiClient apiClient;

    @Autowired
    public LoginController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
                        Model model) {

        if (loggedUser == null) {
            loggedUser = apiClient.post(UriComponentsBuilder.fromUriString(ApiClient.URL_SERVER)
                    .path("/login")
                    .queryParam("email", email)
                    .queryParam("password", password)
                    .build()
                    .toUri())
                    .one(User.class);
        }

        model.addAttribute("loggedUser", loggedUser);
        return "main/index";
    }
}
