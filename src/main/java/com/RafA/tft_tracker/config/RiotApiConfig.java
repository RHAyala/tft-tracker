package com.RafA.tft_tracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
@Configuration
public class RiotApiConfig {
    @Value("${riot.api.key}")
    private String apiKey;

    @Value("${riot.api.region.platform}")
    private String platformRegion;

    @Value("${riot.api.region.regional}")
    private String region;

    @Bean
    public RestClient restClient(){
        return RestClient.builder().defaultHeader("X-Riot-Token", apiKey).build();
    }

    public String getPlatformBaseUrl(){
        return "https://" + platformRegion + ".api.riotgames.com";
    }

    public String getRegionalBaseUrl(){
        return "https://" + region + ".api.riotgames.com";
    }
}
