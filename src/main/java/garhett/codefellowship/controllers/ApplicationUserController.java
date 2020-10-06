package garhett.codefellowship.controllers;

import garhett.codefellowship.models.user.ApplicationUser;
import garhett.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView makeNewUser(
                String username,
                String password,
                String firstName,
                String lastName,
                long socialSecurity,
                long dob,
                String bio) {

        password = passwordEncoder.encode(password);

        ApplicationUser newUser = new ApplicationUser(username, password, firstName, lastName, socialSecurity, dob, bio);

        applicationUserRepository.save(newUser);

        return new RedirectView("/login");
    }

    @GetMapping("/user")
    public String showUser(Model userInfo, Principal principal) {

        ApplicationUser oldUser = applicationUserRepository.findByUsername(principal.getName());

        userInfo.addAttribute("userStuff", oldUser);
        System.out.println(oldUser.toString());
        return "user";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/signup")
    public String showSignUp() { return "signup"; }
}