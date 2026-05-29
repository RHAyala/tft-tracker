package com.RafA.tft_tracker.service;

import org.springframework.stereotype.Service;

import com.RafA.tft_tracker.model.Match;
import com.RafA.tft_tracker.model.MatchParticipant;
import com.RafA.tft_tracker.model.Player;
import com.RafA.tft_tracker.model.Unit;
import com.RafA.tft_tracker.repository.MatchParticipantRepository;
import com.RafA.tft_tracker.repository.MatchRepository;
import com.RafA.tft_tracker.repository.UnitRepository;

import tools.jackson.databind.JsonNode;
import java.time.Instant;

@Service
public class MatchService {
    private final RiotApiService riotApiService;
    private final MatchRepository matchRepository;
    private final MatchParticipantRepository matchParticipantRepository;
    private final UnitRepository unitRepository;

    public MatchService(RiotApiService riotApiService, 
        MatchRepository matchRepository, 
        MatchParticipantRepository matchParticipantRepository, 
        UnitRepository unitRepository)
    {
        this.riotApiService = riotApiService;
        this.matchRepository = matchRepository;
        this.matchParticipantRepository = matchParticipantRepository;
        this.unitRepository = unitRepository;
    }

    public void getAndSaveMatches(Player player, int count){
        //Get list of recent match IDs
        JsonNode matchIds = riotApiService.getMatchIdsByPuuid(player.getPuuid(), count);
        for (JsonNode matchIdNode : matchIds){
            String matchId = matchIdNode.asString();
            //Step 2: skip if match already saved
            if(matchRepository.findByMatchId(matchId).isPresent()){
                continue;
            }
            //Step 3: get the match data 
            JsonNode matchData = riotApiService.getMatchDataById(matchId);
            JsonNode info = matchData.get("info");
            //Step 4: save the match data
            Match match = new Match();
            match.setMatchId(matchId);
            match.setPlayedAt(Instant.ofEpochMilli(info.get("game_datetime").asLong()));
            match.setSetNumber(info.get("tft_set_number").asInt());
            matchRepository.save(match);
            //Step 5 get and save the participant data
            JsonNode participants = info.get("participants");
            for(JsonNode matchParticipant : participants)
            {
                if (!matchParticipant.get("puuid").asString().equals(player.getPuuid())) {
                    continue;
                }
                MatchParticipant mp = new MatchParticipant();
                mp.setMatch(match);
                mp.setPlayer(player);
                mp.setPlacement(matchParticipant.get("placement").asInt());
                mp.setLevel(matchParticipant.get("level").asInt());
                mp.setGoldLeft(matchParticipant.get("gold_left").asInt());
                mp.setLastRound(matchParticipant.get("last_round").asInt());
                matchParticipantRepository.save(mp);
                //Step 6 get and save the units
                JsonNode units = matchParticipant.get("units");
                for(JsonNode unitNode : units)
                {
                    Unit unit = new Unit();
                    unit.setMatchParticipant(mp);
                    unit.setCharacterId(unitNode.get("character_id").asString());
                    unit.setTier(unitNode.get("tier").asInt());
                    JsonNode itemNamesNode = unitNode.get("itemNames");
                    StringBuilder items = new StringBuilder();
                    for(JsonNode item : itemNamesNode){
                        if(items.length() > 0) items.append(",");
                        items.append(item.asString());
                    }
                    unit.setItems(items.toString());
                    unitRepository.save(unit);
                }
            }
        }
    }
}
