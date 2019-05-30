package com.epam.ua.trainingProject.service;

import com.epam.ua.trainingProject.models.Coach;
import com.epam.ua.trainingProject.models.PendingRequest;
import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.request.CoachRequest;

import java.util.List;

public interface CoachService {
    Coach saveCoachFromRequest(PendingRequest pendingRequest);

    Coach getCoach(int id);

    List<Coach> getAllCoach();

    void deleteCoach(int id);

    Coach updateCoach(CoachRequest coachRequest);

//    Coach addNewTrainee(int coachId, int traineeId);

}
