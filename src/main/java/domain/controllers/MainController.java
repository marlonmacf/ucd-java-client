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
    String index() {
        return "cardio/index";
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "name", defaultValue = "YO!") String name, Model model) {

    	/*
        URI request = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/greeting")
                .queryParam("name", name)
                .build()
                .toUri();

        Map response = restTemplate.getForEntity(request, Map.class).getBody();

        model.addAttribute("name", response.get("name"));
	    */

        model.addAttribute("name", name);

        return "main/index";
    }
}
