package com.RafA.tft_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RafA.tft_tracker.model.RankHistory;
import java.util.List;


public interface RankHistoryRepository extends JpaRepository<RankHistory,Long> {
    List<RankHistory> findByPlayer(Long player_id);

}
