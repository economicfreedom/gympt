package com.myproject.gympt.cloud.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReqAndResDTO {

    private Integer id;


    private String newsType;


    private String request;


    private String response;


    private LocalDateTime createAt;


    private Integer cloudId;
}
