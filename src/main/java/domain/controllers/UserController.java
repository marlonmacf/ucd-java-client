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
public class UserController {

    private ApiClient apiClient;

    @Autowired
    public UserController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String store(@RequestParam(value = "email") String email,
                        @RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "inspector", defaultValue = "false") String inspector,
                        @RequestParam(value = "score", defaultValue = "0") String score,
                        Model model) {

        if ((!email.equals("")) && (!name.equals("")) && (!password.equals(""))) {
            loggedUser = apiClient.post(UriComponentsBuilder.fromUriString(ApiClient.URL_SERVER)
                    .path("/user")
                    .queryParam("email", email)
                    .queryParam("name", name)
                    .queryParam("password", password)
                    .queryParam("inspector", (inspector.equals("false")) ? "0" : "1")
                    .queryParam("score", score)
                    .build()
                    .toUri())
                    .one(User.class);
        }

        model.addAttribute("loggedUser", loggedUser);
        return "main/index";
    }
}
