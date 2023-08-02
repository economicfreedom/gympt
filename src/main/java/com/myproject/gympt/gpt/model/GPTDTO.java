package com.myproject.gympt.gpt.model;

import com.myproject.gympt.user.model.UserDTO;
import jakarta.persistence.Column;
import lombok.*;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GPTDTO {

   private Long id;


    private String request;


    private String response;

    private LocalDateTime createdAt;
    private Long userId;
    private String type;


}
