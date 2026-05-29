package com.RafA.tft_tracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RafA.tft_tracker.service.MatchService;
import com.RafA.tft_tracker.service.PlayerService;
import com.RafA.tft_tracker.service.RiotApiService;
import com.RafA.tft_tracker.model.Player;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final MatchService matchService;

    public PlayerController(PlayerService playerService,MatchService matchService){
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @PostMapping("/import/{gameName}/{tagLine}/{region}")
    public Player importPlayer(@PathVariable String gameName,
                                @PathVariable String tagLine, 
                                @PathVariable String region)
    {
        //Get and save player
        Player player = playerService.getAndSavePlayer(gameName, tagLine, region);
        //Get and save their last matches:
        matchService.getAndSaveMatches(player, 20);

        return player;
    }
    
}
