package com.alergitracker.repository;

import com.alergitracker.entity.FoodTrial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodTrialRepository extends JpaRepository<FoodTrial, Long> {
    List<FoodTrial> findByBabyId(Long babyId);
}