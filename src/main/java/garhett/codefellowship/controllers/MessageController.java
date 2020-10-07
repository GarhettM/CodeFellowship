package garhett.codefellowship.controllers;


import garhett.codefellowship.models.user.ApplicationUser;
import garhett.codefellowship.models.user.ApplicationUserRepository;
import garhett.codefellowship.models.user.MessagePost;
import garhett.codefellowship.models.user.MessagePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class MessageController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    MessagePostRepository messagePostRepository;

    @PostMapping("/message")
    public RedirectView updateMessageBoard(String message, Principal principal) {
        String username = principal.getName();
        MessagePost messagePost = new MessagePost(message, username);
        ApplicationUser user = applicationUserRepository.findByUsername(username);

        messagePost.applicationUser = user;

        messagePostRepository.save(messagePost);

        user.message.add(messagePost);
        applicationUserRepository.save(user);

        return new RedirectView("/forum");
    }

    @GetMapping("/forum")
    public String showAllMessages(Model allMessages) {
        ArrayList<MessagePost> allOfThem = (ArrayList<MessagePost>) messagePostRepository.findAll();
        allMessages.addAttribute("messages", allOfThem);
        return "forum";
    }
}
