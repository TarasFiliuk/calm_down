package com.epam.ua.trainingProject.repository;

import com.epam.ua.trainingProject.models.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Integer> {
    Coach findByEmail(String email);
}
