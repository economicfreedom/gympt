package com.myproject.gympt.gpt.controller;


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
        // validation
        // ...
        var type = gptRequest.getType();
        var gender = gptRequest.getGender();
        var age = gptRequest.getAge();
        var purpose = gptRequest.getPurpose();
        var discomfort = gptRequest.getDiscomfort().isEmpty() ? "없습니다." : gptRequest.getDiscomfort() + "입니다.";

        var prompt = "";

        switch (type) {
            case "운동" -> {
                prompt = createExercisePrompt(age, gender, discomfort, purpose);
            }
            case "다이어트" -> {
                prompt = createDietPrompt(age, discomfort, purpose);
            }
            case "질문" -> {
                prompt = createAnswer(discomfort);
            }

            default -> {
                // handle unexpected value of `type`
                return ResponseEntity.badRequest().build();
            }
        }

        ChatGptRequest request = new ChatGptRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        String response = chatGptResponse.getChoices().get(0).getMessage().getContent();
        Byte aByte = gptService.create(prompt, response, principal, gptRequest.getType());

        userService.update(principal);

        return ResponseEntity.ok().build();
    }

    //    private String createExercisePrompt(short age, String gender, String discomfort, String purpose) {
//        return "당신은 나의 운동 질문에 대해 나의 나이와 성별 그리고" +
//                " 운동에 불편한 곳 및 운동 목적에 대해 맞는 답변을 내려주셔야합니다." +
//                " 질문 :  [저는 " + age + "살" +
//                " " + gender + "이고 " +
//                "운동에 불편한 곳은 " + discomfort +
//                " 저의  운동 목적은 " + purpose +
//                "입니다 3일치 운동 루틴을 알려주세요 다른 문장은 작성하지 마시고 운동루틴만 작성해서 보내주세요]";
//    }
    private String createDietPrompt(short age, String discomfort, String purpose) {
        return "I am " + age + " years old, and I have an allergy to " + discomfort + "." +
                "My diet goal is " + purpose + "." +
                "Considering my allergy, please provide a suitable meal plan. In Korean";
    }

    private String createExercisePrompt(short age, String gender, String discomfort, String purpose) {
        return "I am a " + age + " year old " + gender + "." +
                "I experience discomfort when exercising in " + discomfort + ", and my fitness goal is " + purpose + "." +
                "Please provide a 3-day exercise routine. In Korean";
    }

    //
//    private String createDietPrompt(short age, String discomfort, String purpose) {
//        return "당신은 나의 식단 질문에 대해 나의 성별과 나이 및 알러지가 포함된 성분이 있는 음식은 작성하시면 안되며" +
//                "식단 목적에 맞는 식단을 답변을 해주세야합니다." +
//                "중요 : 제가 가지고 있는 알러지를 식단에 포함 시켜 내려주시면 절대 안 됩니다." +
//                "질문 : [저의 나이는 " + age + "이고 " +
//                "알러지는" + discomfort + "를 가지고 있습니다 식단의 목적은 " + purpose +
//                " 입니다 이러한 점을 고려한 식단을 알려주세요 다른 문장은 작성하지 마시고 식단만 작성해서 보내주세요]";
//    }
    private String createAnswer(String answer) {
        return
                "Can you provide a long, well thought out, clear guide on [" + answer + "]?" +
                        "long, clear guide on [desired topic & question]? " +
                        "It should include only hard facts and exclude speculation or uncertainty. " +
                        "speculation or uncertainty. Your explanation should be detailed, comprehensive, in-depth, and thoroughly researched, and provide only accurate and reliable information. At the end of your explanation, indicate the" +
                        "accuracy of the information provided in % and explain why it is so accurate. Information provided" +
                        "Two web sources that can be verified for accuracy and a common URL (accurate as of 2021," +
                        ".com, .gov, .org level) that can verify the accuracy of the information provided. reply in korean";

    }
}