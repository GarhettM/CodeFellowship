package garhett.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(Model m, Principal principal) {

        m.addAttribute("principal", principal);
        return "home";
    }

}
