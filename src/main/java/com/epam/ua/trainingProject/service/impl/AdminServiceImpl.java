package com.epam.ua.trainingProject.service.impl;

import com.epam.ua.trainingProject.models.PendingRequest;
import com.epam.ua.trainingProject.service.AdminService;
import com.epam.ua.trainingProject.service.CoachService;
import com.epam.ua.trainingProject.service.PendingRequestService;
import com.epam.ua.trainingProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.epam.ua.trainingProject.models.Status.APPROVE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final CoachService coachService;
    private final PendingRequestService pendingRequestService;

    @Override
    public void approveCoach(int requestId) {
        PendingRequest requestById = pendingRequestService.getRequestById(requestId);
        requestById.setStatus(APPROVE);
        coachService.saveCoachFromRequest(requestById);
        userService.deleteUser(requestById.getUser().getId());
    }
}

