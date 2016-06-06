package domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "app", defaultValue = "app") String app, Model model) {

        /*
        URI request = UriComponentsBuilder.fromUriString("https://ucd-server.herokuapp.com")
                .path("/greeting")
                .queryParam("app", "client/" + app)
                .build()
                .toUri();

        Map response = restTemplate.getForEntity(request, Map.class).getBody();

        model.addAttribute("app", response.get("app"));
        */

        return "main/index";
    }
}
