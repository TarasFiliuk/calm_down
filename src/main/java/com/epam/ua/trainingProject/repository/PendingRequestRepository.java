package com.epam.ua.trainingProject.repository;

import com.epam.ua.trainingProject.models.PendingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingRequestRepository extends JpaRepository<PendingRequest,Integer> {
    boolean existsByUser_EmailAndUserId(String email,int userId);
}
