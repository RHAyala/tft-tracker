package com.RafA.tft_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RafA.tft_tracker.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    Optional<Match> findByMatchId(String matchId);
}
