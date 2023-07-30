package com.myproject.gympt.gpt.controller;

import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.zip.DataFormatException;

@Controller
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {

    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/gym-pt")
    public String view(Principal principal) throws DataFormatException {

        UserDTO user = userService.getUser(principal.getName());

        if (user.getGptCount() == 2){
            return "redirect:/main";
        }

        return "gympt";
    }

}
