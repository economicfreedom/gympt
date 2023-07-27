package com.myproject.gympt.user.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {


    private String name;
    @Pattern(regexp = "^[a-z0-9]{3,16}$",message = "소문자와 숫자를 포함하여 3글자에서 16글자 사이여야 합니다")
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String uid;
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;
    @NotEmpty(message = "비밀번호확인은 필수항목입니다.")
    private String password2;

    @Email
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;

    private String simpleAddr;

    private Long id;

    private String nickName;

}
