package com.adjuster.campaignagregator;

import com.adjuster.campaignagregator.service.CampaignAggregator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Entry point for the application.
 * Print the consumed Campaigns summary
 */
@SpringBootApplication
public class Application {
    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(CampaignAggregator campaignAggregator) throws Exception {
        return args -> {
            campaignAggregator.printCampaignSummary();
        };
    }
}
