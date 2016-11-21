package domain.controllers;

import domain.entities.User;
import domain.rest.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class RankingController {

    private ApiClient apiClient;

    @Autowired
    public RankingController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String index(Model model) {

        List<User> ranking = apiClient.get(UriComponentsBuilder.fromUriString(ApiClient.URL_SERVER)
                .path("/ranking")
                .build()
                .toUri())
                .all(User.class);

        model.addAttribute("ranking", ranking);

        return "main/index";
    }
}
