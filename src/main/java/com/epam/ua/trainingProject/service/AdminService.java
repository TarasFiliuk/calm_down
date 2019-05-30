package com.epam.ua.trainingProject.service;

import com.epam.ua.trainingProject.dto.CoachDTO;
import com.epam.ua.trainingProject.request.CoachRequest;

public interface AdminService {
    void approveCoach(int requestId);
}
