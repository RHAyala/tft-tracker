package com.RafA.tft_tracker.model;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.*;

@Entity
@Table(name = "rank_history")
public class RankHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    private String season;
    private String rank;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }
}
