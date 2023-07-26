package com.myproject.gympt.board.controller;

import com.myproject.gympt.board.model.BoardRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping("")
    public String mainPage() {
        return "Test";
    }

    @GetMapping("/user/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "registerForm";
    }

    @GetMapping("/main")
    public String board() {
        return "main";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(BoardRequest boardRequest){
        return "createForm";
    }


//    public String create
}
