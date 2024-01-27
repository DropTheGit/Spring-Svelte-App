package com.ll.springsvelteapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSvelteAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSvelteAppApplication.class, args);
    }

}
