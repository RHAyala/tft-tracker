package com.RafA.tft_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String puuid;
    @Column(nullable = false)
    private String riotId;
    private String summonerId;
    private String currentRank;
    private Integer currentLp;
    private String region;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPuuid() { return puuid; }
    public void setPuuid(String puuid) { this.puuid = puuid; }

    public String getRiotId() { return riotId; }
    public void setRiotId(String riotId) { this.riotId = riotId; }

    public String getSummonerId() { return summonerId; }
    public void setSummonerId(String summonerId) { this.summonerId = summonerId; }

    public String getCurrentRank() { return currentRank; }
    public void setCurrentRank(String currentRank) { this.currentRank = currentRank; }

    public Integer getCurrentLp() { return currentLp; }
    public void setCurrentLp(Integer currentLp) { this.currentLp = currentLp; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    

}
