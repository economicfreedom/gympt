package com.myproject.gympt.user.model;

import com.myproject.gympt.gpt.model.GPTDTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Long id;


    private String name;


    private String email;


    private String simpleAddress;


    private LocalDateTime createdAt;


    private String uid;


    private String password;


    private String nickName;
    private byte gptCount;
    private String status;
    private List<GPTDTO> gptList = List.of();
}
