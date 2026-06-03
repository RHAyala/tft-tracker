package com.RafA.tft_tracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RafA.tft_tracker.config.RiotApiConfig;
import com.RafA.tft_tracker.dto.MatchSummaryDto;
import com.RafA.tft_tracker.dto.PlayerResponseDto;
import com.RafA.tft_tracker.model.MatchParticipant;
import com.RafA.tft_tracker.model.Player;
import com.RafA.tft_tracker.model.Unit;
import com.RafA.tft_tracker.repository.MatchParticipantRepository;
import com.RafA.tft_tracker.repository.PlayerRepository;
import com.RafA.tft_tracker.repository.UnitRepository;

import tools.jackson.databind.JsonNode;

@Service
public class PlayerService {
    private final RiotApiService riotApiService;
    private final PlayerRepository playerRepository;
    private final MatchParticipantRepository matchParticipantRepository;
    private final UnitRepository unitRepository;

    public PlayerService(RiotApiService riotApiService,PlayerRepository playerRepository, MatchParticipantRepository matchParticipantRepository, UnitRepository unitRepository){
        this.riotApiService = riotApiService;
        this.playerRepository = playerRepository;
        this.matchParticipantRepository = matchParticipantRepository;
        this.unitRepository = unitRepository;
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

    public PlayerResponseDto buildPlayerResponse(Player player){
        List<MatchParticipant> participants = matchParticipantRepository.findByPlayer(player);
        
        List<MatchSummaryDto> matchSummaries = participants.stream()
                .map(participant -> {
                    List<Unit> units = unitRepository.findByMatchParticipant(participant);
                    return new MatchSummaryDto(participant, units);
                })
                .collect(Collectors.toList());


        return new PlayerResponseDto(player, matchSummaries);
    }
}
