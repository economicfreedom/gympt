package com.myproject.gympt.cloud.controller;

import com.myproject.gympt.cloud.service.CloudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/word")
public class WordRestController {

    private final CloudService cloudService;
    @GetMapping("/api")
    public Map<String,Object> wordCloud(){

        return cloudService.getWords();
    }




}
