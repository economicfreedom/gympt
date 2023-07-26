package com.myproject.gympt.user.controller;


import com.myproject.gympt.user.model.UserRequest;
import com.myproject.gympt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String regiForm(UserRequest userRequest) {
        System.out.println("처음엔 여기로옴");
        return "registerForm";
    }

    @PostMapping("/register")
    public String regiForm(@Valid UserRequest userRequest,
                           @RequestParam("nick_name") String nickName,
                           @RequestParam("simple_addr") String simpleAddr,

                           BindingResult bindingResult
    ) {
        userRequest.setNickName(nickName);
        userRequest.setSimpleAddr(simpleAddr);


        log.info("넘어온 데이터 {}", userRequest);

        System.out.println("regi로 왔음");
        if (bindingResult.hasErrors()) {
            System.out.println("에러발생1");
            return "registerForm";
        }
        if (!userRequest.getPassword().equals(userRequest.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            System.out.println("에러발생2");
            return "registerForm";
        }
        try {
            userService.create(userRequest);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            System.out.println("에러발생3");
            return "registerForm";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "registerForm";
        }

        return "redirect:/";
    }


}
