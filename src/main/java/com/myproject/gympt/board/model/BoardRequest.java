package com.myproject.gympt.board.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardRequest {
    private Long id;
    private String uid;
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200)
    private String title;
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
    private String boardType;

}
