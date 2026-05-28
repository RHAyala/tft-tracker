package com.RafA.tft_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RafA.tft_tracker.model.Player;

public interface PlayerRepository extends JpaRepository<Player,Long>{
    Optional<Player> findByPuuid(String puuid);
    Optional<Player> findByRiotId(String riotId);
}
