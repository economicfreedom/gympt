package com.myproject.gympt;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class GraphQLTestDTO {
    private String title;
    private String name;
    private String content;
    private String testData1;
}
