package com.RafA.tft_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="unit")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="match_participant_id",nullable = false)
    @JsonIgnore
    private MatchParticipant matchParticipant;
    
    private String characterId;
    private Integer tier;
    @Column(columnDefinition = "text")
    private String items;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MatchParticipant getMatchParticipant() { return matchParticipant; }
    public void setMatchParticipant(MatchParticipant matchParticipant) { this.matchParticipant = matchParticipant; }

    public String getCharacterId() { return characterId; }
    public void setCharacterId(String characterId) { this.characterId = characterId; }

    public Integer getTier() { return tier; }
    public void setTier(Integer tier) { this.tier = tier; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }
}
