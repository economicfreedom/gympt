package com.myproject.gympt.gpt.controller;

import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.zip.DataFormatException;

@Controller
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {

    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/gym-pt/{num}")
    public String view(@PathVariable Byte num, Principal principal) throws DataFormatException {

        UserDTO user = userService.getUser(principal.getName());
        String show ;
        switch (num){
            case 1 -> show="gympt";
            case 2 -> show="diet";
            default -> show = "redirect:/main";
        }



        if (user.getGptCount() == 3){
            return "redirect:/main";
        }

        return show;
    }

}
