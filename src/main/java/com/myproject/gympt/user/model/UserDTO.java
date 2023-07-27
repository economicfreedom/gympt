package com.myproject.gympt.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

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
}
