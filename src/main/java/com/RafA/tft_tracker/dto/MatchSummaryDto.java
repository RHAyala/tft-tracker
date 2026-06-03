package com.RafA.tft_tracker.dto;

import java.util.List;

import com.RafA.tft_tracker.model.MatchParticipant;
import com.RafA.tft_tracker.model.Unit;

public class MatchSummaryDto {
    private MatchParticipant participant;
    private List<Unit> units;

    public MatchSummaryDto(MatchParticipant participant, List<Unit> units){
        this.participant = participant;
        this.units = units;
    }

    public MatchParticipant getMatchParticipant() {return participant;}
    public List<Unit> getUnits(){return units;}
}
