package domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Controller
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Unidos Contra a Dengue!!!";
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "app", defaultValue = "app") String app, Model model) {

        URI request = UriComponentsBuilder.fromUriString("https://ucd-server.herokuapp.com")
                .path("/greeting")
                .queryParam("app", "client/" + app)
                .build()
                .toUri();

        Map response = restTemplate.getForEntity(request, Map.class).getBody();

        model.addAttribute("app", response.get("app"));

        return "views/greeting/index";
    }
}