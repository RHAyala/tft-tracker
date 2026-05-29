package com.RafA.tft_tracker.service;

import org.springframework.stereotype.Service;

import com.RafA.tft_tracker.config.RiotApiConfig;
import com.RafA.tft_tracker.model.Player;
import com.RafA.tft_tracker.repository.PlayerRepository;

import tools.jackson.databind.JsonNode;

@Service
public class PlayerService {
    private final RiotApiService riotApiService;
    private final PlayerRepository playerRepository;

    public PlayerService(RiotApiService riotApiService,PlayerRepository playerRepository){
        this.riotApiService = riotApiService;
        this.playerRepository = playerRepository;
    }
    public Player getAndSavePlayer(String gameName, String tagLine, String region){
        //Get PUUID from Riot ID
        JsonNode accountData = riotApiService.getAccountByRiotID(gameName, tagLine);
        String puuid = accountData.get("puuid").asString();
        String riotid = gameName + "#" + tagLine;

        return playerRepository.findByPuuid(puuid).orElseGet(()->{
            //Get rank data
            JsonNode rankData = riotApiService.getRankByPuuid(puuid);
            String currentRank = "UNRANKED";
            Integer currentLp = 0;
            if(rankData.size()> 0){
                currentRank = rankData.get("tier").asString()
                +" " + rankData.get("rank").asString();
                currentLp = rankData.get("leaguePoints").asInt();
            }

            //Build and save the player
            Player player = new Player();
            player.setPuuid(puuid);
            player.setRiotId(riotid);
            // player.setSummonerId(summonerData.get("id").asText());
            player.setCurrentLp(currentLp);
            player.setCurrentRank(currentRank);
            player.setRegion(region);

            return playerRepository.save(player);
        });
    }
}
