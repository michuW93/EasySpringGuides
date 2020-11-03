package com.example.spring_guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.greetings"})
public class SpringGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGuideApplication.class, args);
    }
}