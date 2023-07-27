package com.myproject.gympt.board.model;

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
@ToString
public class BoardDTO {

    private Long id;

    private String nickName;
    private String title;


    private String content;


    private LocalDateTime createdAt;


    private UserEntity user;


    private String boardType;

    private List<ReplyEntity> replyList = List.of();

}

