package com.myproject.gympt.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myproject.gympt.reply.db.ReplyEntity;
import com.myproject.gympt.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BoardDTO {

    private Long id;

    private String nickName;
    private String title;


    private String content;


    private LocalDateTime createdAt;


    private UserEntity user;


    private String boardType;

    @JsonManagedReference
    private List<ReplyEntity> replyList = List.of();

}

