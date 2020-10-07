package garhett.codefellowship.controllers;

import garhett.codefellowship.models.user.ApplicationUser;
import garhett.codefellowship.models.user.ApplicationUserRepository;
import garhett.codefellowship.models.user.MessagePost;
import garhett.codefellowship.models.user.MessagePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    MessagePostRepository messagePostRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public RedirectView findUser(String findName, Principal principal) {

        return new RedirectView("/user/" + findName);
    }

    @PostMapping("/signup")
    public RedirectView makeNewUser(
                String username,
                String password,
                String firstName,
                String lastName,
                long socialSecurity,
                String dob,
                String bio) throws ParseException {

        dob = dob.substring(0, 10);
        java.util.Date dateUtilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);

        Date sqlDate = new java.sql.Date(dateUtilDate.getTime());


        password = passwordEncoder.encode(password);

        ApplicationUser newUser = new ApplicationUser(username, password, firstName, lastName, socialSecurity, sqlDate, bio);

        applicationUserRepository.save(newUser);

        return new RedirectView("/login");
    }

    @GetMapping("/myprofile")
    public RedirectView showUser(Principal principal) {
        return new RedirectView("/myprofile/" + principal.getName());
    }

    @GetMapping("/myprofile/{username}")
    public String showUserForReal(@PathVariable String username, Model userInfo, Principal principal)    {
        ApplicationUser oldUser = applicationUserRepository.findByUsername(principal.getName());
        userInfo.addAttribute("userStuff", oldUser);
        System.out.println(oldUser.message.size());
        if(oldUser == null) {
            userInfo.addAttribute("userNotFound", true);
        }
        return "myprofile";
    }

    @GetMapping("/user/{username}")
    public String showFoundUser(@PathVariable String username, Model m, Principal principal) {
        System.out.println(username);
        ApplicationUser oldUser = applicationUserRepository.findByUsername(username);
        System.out.println(oldUser.message.size());
        m.addAttribute("userInfo", oldUser.message);
        System.out.println(oldUser);
        return "user";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/signup")
    public String showSignUp() { return "signup"; }
}
