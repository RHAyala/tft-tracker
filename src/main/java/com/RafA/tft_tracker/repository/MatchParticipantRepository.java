package com.RafA.tft_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RafA.tft_tracker.model.MatchParticipant;
import java.util.List;


@Repository
public interface MatchParticipantRepository extends JpaRepository<MatchParticipant,Long>{
    List<MatchParticipant> findByPlayer(long player_id);
}
