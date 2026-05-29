package com.RafA.tft_tracker.model;

import java.lang.annotation.Target;

import jakarta.persistence.*;
@Entity
@Table(name="match_participant")    
public class MatchParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="match_id", nullable = false)
    private Match match;
    @ManyToOne
    @JoinColumn(name="player_id", nullable = false)
    private Player player;

    private Integer placement;
    private Integer level;
    private Integer lastRound;
    private Integer goldLeft;

     public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public Integer getPlacement() { return placement; }
    public void setPlacement(Integer placement) { this.placement = placement; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getLastRound() { return lastRound; }
    public void setLastRound(Integer lastRound) { this.lastRound = lastRound; }

    public Integer getGoldLeft() { return goldLeft; }
    public void setGoldLeft(Integer goldLeft) { this.goldLeft = goldLeft; }
}
