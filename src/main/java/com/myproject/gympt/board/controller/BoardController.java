package com.myproject.gympt.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping("")
    public String mainPage(){
        return "Test";
    }

    @GetMapping("/user/login")
    public String loginForm(){
        return "loginForm";
    }
    @GetMapping("/register")
    public String registerForm(){
        return "registerForm";
    }

    @GetMapping("/main")
    public String board(){
        return "main";
    }


}
