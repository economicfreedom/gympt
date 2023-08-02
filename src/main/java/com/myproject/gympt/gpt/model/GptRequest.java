package com.myproject.gympt.gpt.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GptRequest {
    private String gender;
    private String discomfort;
    private String purpose;
    private Short age;
    private String type;

}
