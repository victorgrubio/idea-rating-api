package com.victorgarciarubio.idea_rating_api.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIDocumentation(){
        return new OpenAPI()
                .info(
                        new Info().title("Idea Rating API")
                                .description("Idea Rating Application for evaluating ideas")
                                .version("v0.1.0")
                                .license(
                                        new License().name("MIT").url("http://springdoc.org")
                                )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Idea Rating API Wiki Documentation")
                                .url("https://idea-rating-api.wiki.github.org/docs")
                );
    }
}
