package domain.controllers;

import domain.entities.Complaint;
import domain.rest.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class ComplaintController {

    private ApiClient apiClient;

    @Autowired
    public ComplaintController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.GET)
    public String index(Model model) {

        List<Complaint> complaints = apiClient.get(UriComponentsBuilder.fromUriString(ApiClient.URL_SERVER)
                .path("/complaint")
                .build()
                .toUri())
                .all(Complaint.class);

        model.addAttribute("complaints", complaints);
        return "main/index";
    }
}
