package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/settings/{editionId}")
    public Settings index(@PathVariable String editionId) {
        System.out.println("EditionId: " + editionId);

        if ("1".equals(editionId)) {
            return new Settings(1,10, 1, 10, 4, 3);
        } else if ("2".equals(editionId)) {
            return new Settings(1, 5, 1, 20, 3, 3);
        }

        return new Settings(1, 3, 1, 5, 3, 5);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }


    public record Settings(Integer minRounds,
                           Integer maxRounds,
                           Integer minQuestionsPerRound,
                           Integer maxQuestionsPerRound,
                           Integer startingRounds,
                           Integer startingQuestionsPerRound) {}

}
