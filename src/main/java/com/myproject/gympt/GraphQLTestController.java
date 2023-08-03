package com.myproject.gympt;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLTestController {

    @QueryMapping
    public GraphQLTestDTO graphQlTest(@Argument int id) {
        GraphQLTestDTO graphQLTestDTO;
        switch (id) {
            case 1 -> {
                graphQLTestDTO = GraphQLTestDTO
                        .builder()
                        .title("제목1")
                        .content("테스트컨 컨텐츠1")
                        .build();


            }
            case 2 -> {
                graphQLTestDTO = GraphQLTestDTO
                        .builder()
                        .title("제목2")
                        .content("테스트컨 컨텐츠2")
                        .build();

            }
            case 3 -> {
                graphQLTestDTO = GraphQLTestDTO
                        .builder()
                        .title("제목3")
                        .content("테스트컨 컨텐츠3")
                        .build();

            }
            default -> graphQLTestDTO = null;
        }

        return graphQLTestDTO;
    }
}
