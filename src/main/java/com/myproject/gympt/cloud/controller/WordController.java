package com.myproject.gympt.cloud.controller;

import com.myproject.gympt.cloud.model.ReqAndResDTO;
import com.myproject.gympt.cloud.service.CloudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordController {
    private final CloudService cloudService;
       @GetMapping("/cloud")
    public String cloud(Model model) {
           ReqAndResDTO ecoReqAndReq = cloudService.getEcoReqAndReq();
           ReqAndResDTO itReqAndReq = cloudService.getItReqAndReq();
           ReqAndResDTO poliReqAndReq = cloudService.getPoliReqAndReq();
           ReqAndResDTO socReqAndReq = cloudService.getSocReqAndReq();

           model.addAttribute("eco",ecoReqAndReq);
           model.addAttribute("it",itReqAndReq);
           model.addAttribute("poli",poliReqAndReq);
           model.addAttribute("soc",socReqAndReq);


           return "wordCloud";

    }


}
