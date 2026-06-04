package com.RafA.tft_tracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.RafA.tft_tracker.config.RiotApiConfig;

import tools.jackson.databind.JsonNode;

@Service
public class RiotApiService {
    private final RestClient restClient;
    private final RiotApiConfig config;

    public RiotApiService(RestClient restClient,RiotApiConfig config){
        this.restClient = restClient;
        this.config = config;
    }
    //Step 1: Get PUUID from riot id (name + tag)
    public JsonNode getAccountByRiotID(String gameName, String tagLine){
        String url = config.getRegionalBaseUrl()
                    + "/riot/account/v1/accounts/by-riot-id/"
                    + gameName + "/" + tagLine;
        System.out.println("GET ACCOUNT URL: " + url);
        return restClient.get().uri(url).retrieve().body(JsonNode.class);
    }
    //Step 2: Get summonder data by PUUID
    public JsonNode getSummonerByPuuid(String puuid){
        String url = config.getPlatformBaseUrl()
                    +"/tft/summoner/v1/summoners/by-puuid/"
                    + puuid;
        return restClient.get().uri(url).retrieve().body(JsonNode.class);
    }

    // Step 3: Get ranked info by summoner ID
    public JsonNode getRankByPuuid(String puuid) {
        String url = config.getPlatformBaseUrl()
                + "/tft/league/v1/by-puuid/" + puuid;

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(JsonNode.class);
    }
    //Step 4: Get list of recent match IDs by PUUID
    public JsonNode getMatchIdsByPuuid(String puuid, int count){
        String url = config.getRegionalBaseUrl()
                    +"/tft/match/v1/matches/by-puuid/" + puuid
                    +"/ids?queue=1100&count=" + count;
        return restClient.get().uri(url).retrieve().body(JsonNode.class);
    }
    //Step 5: Get full match data by ID
    public JsonNode getMatchDataById(String matchId){
        String url = config.getRegionalBaseUrl()
                    + "/tft/match/v1/matches/" 
                    + matchId;
        return restClient.get().uri(url).retrieve().body(JsonNode.class);
    }
}
