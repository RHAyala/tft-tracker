package com.RafA.tft_tracker.dto;

import java.util.List;

import com.RafA.tft_tracker.model.Player;

public class PlayerResponseDto{
    private Player player;
    private List<MatchSummaryDto> matches;

    public PlayerResponseDto(Player player, List<MatchSummaryDto> matches){
        this.player = player;
        this.matches = matches;
    }

    public Player getPlayer() {return player;};
    public List<MatchSummaryDto> getMatches() {return matches;}
}