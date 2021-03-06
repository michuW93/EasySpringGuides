package com.example.spring_guide;

import com.example.consumingRest.Quote;
import com.example.relationaDataBaseAccess.DatabaseOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@EnableScheduling
public class SpringGuideApplication {
    private final static String URL = "https://gturnquist-quoters.cfapps.io/api/random";
    private static final Logger log = LoggerFactory.getLogger(SpringGuideApplication.class);

    @Autowired
    private DatabaseOperations databaseOperations;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        databaseOperations.doOperations();
        return args -> {
            getQuote(restTemplate);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringGuideApplication.class, args);
    }

    private void getQuote(RestTemplate restTemplate) {
        Quote quote = restTemplate.getForObject(URL, Quote.class);
        if (quote.getType().equals("success")) {
            log.info(quote.toString());
        } else {
            log.error("Problem with getting Quote from: " + URL);
        }
    }
}