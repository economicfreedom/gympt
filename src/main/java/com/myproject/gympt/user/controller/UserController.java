package com.myproject.gympt.user.controller;


import com.myproject.gympt.gpt.model.GPTDTO;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.model.UserRequest;
import com.myproject.gympt.user.service.RegisterMail;
import com.myproject.gympt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.zip.DataFormatException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final RegisterMail registerMail;

    private String code;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String regiForm(UserRequest userRequest, Model model) {
        System.out.println("처음엔 여기로옴");
        model.addAttribute("request", userRequest);
        return "registerForm";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public String regiForm(@Valid UserRequest userRequest,
                           @RequestParam("nick_name") String nickName,
                           @RequestParam("simple_addr") String simpleAddr,

                           BindingResult bindingResult
            , Model model
    ) {
        userRequest.setNickName(nickName);
        userRequest.setSimpleAddr(simpleAddr);

        model.addAttribute("request", userRequest);
        log.info("넘어온 데이터 {}", userRequest);

        System.out.println("regi로 왔음");
        if (bindingResult.hasErrors()) {
            log.info("회원가입 에러 발생 입력값 불충분함.");
            return "registerForm";
        }
        if (!userRequest.getPassword().equals(userRequest.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            log.info("회원가입 에러 발생 비밀번호가 서로 맞지 않음");
            return "registerForm";
        }
        if (!userRequest.getMemailconfirm().equals(code)) {
            bindingResult.rejectValue("code", "passwordInCorrect",
                    "인증코드가 일치하지 않습니다.");
            log.info("회원가입 에러 발생 비밀번호가 서로 맞지 않음");
            return "registerForm";
        }


        String registerUser = userRequest.getEmail();
        if (!userService.hasEmail(registerUser)) {
            bindingResult.rejectValue("email", "DataIntegrityViolation", "이미 등록된 이메일입니다.");
            log.info("회원가입 에러 발생 이메일이 이미 존재함");
            return "registerForm";
        }

        try {

            UserDTO userDTO = userService.create(userRequest);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();

//            int i = 0;
//            String errorMessage[] = {"","",""};
            String errorMessage = "이미 등록된 아이디입니다.";
            System.out.println("시작" + e.getMessage() + "끝");
            String message = e.getMessage();
//            if (message.contains("user.email_UNIQUE")) i = 1;
//            else if(message.contains("user.email_UNIQUE")) i = 2;
            if (message.contains("user.email_UNIQUE")) errorMessage = "이미 등록된 이메일입니다.";
            else if (message.contains("user.nick_name_UNIQUE")) errorMessage = "이미 등록된 닉네임입니다.";
//            bindingResult.reject("signupFailed", errorMessage[i]);
            bindingResult.reject("signupFailed", errorMessage);

            System.out.println("에러발생3");
            return "registerForm";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "registerForm";
        }

        return "redirect:/user/login";
    }


    @PostMapping("/login/mailConfirm")
    @PreAuthorize("isAnonymous()")
    @ResponseBody
    public String create(@RequestParam("email") String email) throws Exception {
        String code = registerMail.sendSimpleMessage(email);
        log.info("인증 코드 : {}", code);
        this.code = code;
        return code;
    }

    @GetMapping("/userId")
    @ResponseBody
    public UserDTO userId(@RequestParam String uid) throws DataFormatException {
        UserDTO user = userService.getUser(uid);


        return user;
    }

    @GetMapping("/nick")
    @ResponseBody
    public UserDTO nickName(@RequestParam String nick) throws DataFormatException {
        UserDTO user = userService.getNickName(nick);

        return user;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/menu")
    public String myMenu(Principal principal,Model model) throws DataFormatException {
        UserDTO user = userService.getUser(principal.getName());

        model.addAttribute("user",user);
        int i = 3 - user.getGptCount();

        model.addAttribute("gptCount",i);
        return "myMenu";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(
           @RequestParam String password
            ,@RequestParam String password2
            , Principal principal){

        if (!password.equals(password2)){
            return ResponseEntity.badRequest().build();
        }
        userService.update(principal,password);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public String deleteUser(){

        return "deleteUser";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deleteUser(Principal principal,
                                        @RequestBody UserRequest userRequest) throws DataFormatException {

        if (!userRequest.getPassword().equals(userRequest.getPassword2())){
            return ResponseEntity.badRequest().build();
        }
        boolean b = userService.deleteUser(userRequest.getPassword(), principal);

        if (!b){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();

    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/find")
    public String findUserInfo(){
        return "findUserInfo";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/findUserId")
    @ResponseBody
    public ResponseEntity<?> findUserId(@RequestBody UserRequest userRequest){
        if (!code.equals(userRequest.getMemailconfirm())){
            return ResponseEntity.badRequest().body("인증코드가 다릅니다.");
        }

        String userId = userService.findUserId(userRequest.getEmail());

        if (userId == null){
            return ResponseEntity.badRequest().body("존재하지 않는 아이디입니다.");
        }

        return ResponseEntity.ok().body(userId);

    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/findUserPw")
    @ResponseBody
    public ResponseEntity<?> findUserIdPw(@RequestBody UserRequest userRequest){
        log.info("findUserIdPw 실행됨 요청값 {}",userRequest);

        UserDTO userDTO = userService.updatePassword(userRequest.getUid()
                ,userRequest.getEmail()
                ,userRequest.getPassword());

        if (userDTO.getPassword() == null){
            log.info("findUserIdPw user DTO가 null임");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/show-answer")
    public String gptResponses(Principal principal,Model model){
        List<GPTDTO> gptResponseList = userService.getGPTResponseList(principal.getName());

        model.addAttribute("responseList",gptResponseList);


        return "gptList";

    }


}