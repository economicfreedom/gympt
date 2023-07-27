package com.myproject.gympt.board.controller;

import com.myproject.gympt.board.model.BoardRequest;
import com.myproject.gympt.board.service.BoardService;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.zip.DataFormatException;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;

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
    public String create(BoardRequest boardRequest) {
        return "createForm";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid BoardRequest boardRequest
            , BindingResult bindingResult
            , Principal principal) throws DataFormatException {
        if (bindingResult.hasErrors()){
            return "createForm";
        }

        boardRequest.setUid(principal.getName());

        boardService.create(boardRequest);

        return "redirect:/";
    }

//    public String create
}
