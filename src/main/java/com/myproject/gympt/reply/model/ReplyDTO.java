package com.myproject.gympt.reply.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReplyDTO {

    private Long id;


    private Long user;


    private Long board;


    private String content;
    private UserEntity userEntity;

    private LocalDateTime createdAt;

}
