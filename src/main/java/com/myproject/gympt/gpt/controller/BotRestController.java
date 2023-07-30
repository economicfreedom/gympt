package com.myproject.gympt.gpt.controller;

//import com.theokanning.openai.completion.CompletionRequest;
//import com.theokanning.openai.service.OpenAiService;

import com.myproject.gympt.gpt.model.GptRequest;
import com.myproject.gympt.gpt.model.ChatGptRequest;
import com.myproject.gympt.gpt.model.ChatGptResponse;
import com.myproject.gympt.gpt.service.GPTService;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
@RequestMapping("/bot")
public class BotRestController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;
    @Autowired
    private RestTemplate template;
    @Autowired
    private GPTService gptService;

    @Autowired
    private UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody GptRequest gptRequest, Principal principal) {
        var gender = gptRequest.getGender();
        var age =        gptRequest.getAge();
        var discomfort = gptRequest.getDiscomfort();
        var purpose =    gptRequest.getPurpose();
        if (discomfort.equals( "없음") || discomfort.isEmpty()) {
            discomfort = "없습니다.";
        } else {
            discomfort = discomfort + "입니다.";
        }

        // Create prompt message
        var prompt = " 질문 :  [저는 " + age + "살" +
                " " + gender + "이고 " +
                "운동에 불편한 곳은 " + discomfort +
                " 저의  운동 목적은 " + purpose +
                "입니다 3일치 운동 루틴을 알려주세요 다른 문장은 작성하지 마시고 운동루틴만 작성해서 보내주세요]";
        ChatGptRequest request = new ChatGptRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        String response = chatGptResponse.getChoices().get(0).getMessage().getContent();
        Byte aByte = gptService.create(prompt, response, principal);


        UserDTO update = userService.update(principal);



        return ResponseEntity.ok().build();


    }

}
