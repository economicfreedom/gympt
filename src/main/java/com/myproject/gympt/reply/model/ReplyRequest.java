package com.myproject.gympt.reply.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class ReplyRequest {
    private Long boardId;
    private String content;
    private Long id;

}
