package com.RafA.tft_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RafA.tft_tracker.model.Unit;
import java.util.List;
import java.util.Optional;


@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {
    List<Unit> findByMatchParticipant(long matchParticipantId);
    
}
