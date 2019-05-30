package com.epam.ua.trainingProject.service.impl;

import com.epam.ua.trainingProject.exeption.NotFoundExeption;
import com.epam.ua.trainingProject.exeption.RequestException;
import com.epam.ua.trainingProject.models.PendingRequest;
import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.repository.PendingRequestRepository;
import com.epam.ua.trainingProject.request.JoinToCoachRequest;
import com.epam.ua.trainingProject.service.PendingRequestService;
import com.epam.ua.trainingProject.service.UserService;
import com.epam.ua.trainingProject.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.epam.ua.trainingProject.models.Status.PENDING;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PendingRequestServiceImpl implements PendingRequestService {

    private final PendingRequestRepository requestRepository;
    private final UserService userService;

    @Override
    public PendingRequest createCoachRequest(JoinToCoachRequest joinToCoachRequest) {
        return this.save(Objects.requireNonNull(userService.findUserByEmail(joinToCoachRequest.getEmail()).orElse(null)), joinToCoachRequest);
    }

    @Override
    public PendingRequest getRequestById(int id) {
       return requestRepository.findById(id).orElseThrow(() -> new NotFoundExeption("request with id : " + id, Constants.NOT_FOUND));
    }

    @Override
    public PendingRequest save(User user, JoinToCoachRequest joinToCoachRequest) {
        boolean exists = requestRepository.existsByUser_EmailAndUserId(joinToCoachRequest.getEmail(), user.getId());
        if (exists) {
            throw new RequestException(user.getUsername() + " " + user.getSurname() +" " + "please wait", "Your request is being processed");
        } else {
            PendingRequest pendingRequest = new PendingRequest();
            pendingRequest.setStatus(PENDING);
            pendingRequest.setUser(user);
            pendingRequest.setExperience(joinToCoachRequest.getExperience());
            return requestRepository.save(pendingRequest);
        }
    }
}
