package com.epam.ua.trainingProject.service;

import com.epam.ua.trainingProject.models.PendingRequest;
import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.request.JoinToCoachRequest;


public interface PendingRequestService {
    PendingRequest save(User user, JoinToCoachRequest joinToCoachRequest);
    PendingRequest createCoachRequest(JoinToCoachRequest joinToCoachRequest);
    PendingRequest getRequestById(int id);
}
