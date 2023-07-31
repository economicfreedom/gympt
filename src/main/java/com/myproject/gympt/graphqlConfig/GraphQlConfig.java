package com.myproject.gympt.graphqlConfig;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {

    @Bean
public RuntimeWiringConfigurer runtimeWiringConfigurer() {
   return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.GraphQLLong);
}



}

