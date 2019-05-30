package com.epam.ua.trainingProject.service.impl;

import com.epam.ua.trainingProject.exeption.NotFoundExeption;
import com.epam.ua.trainingProject.models.Coach;
import com.epam.ua.trainingProject.models.PendingRequest;
import com.epam.ua.trainingProject.repository.CoachRepository;
import com.epam.ua.trainingProject.request.CoachRequest;
import com.epam.ua.trainingProject.service.CoachService;
import com.epam.ua.trainingProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.ua.trainingProject.models.Role.ROLE_COACH;
import static com.epam.ua.trainingProject.utils.Constants.NOT_FOUND;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoachServiceImpl implements CoachService {
    private final CoachRepository coachRepository;
    private final UserService userService;


    public Coach save(Coach coach){
        return coachRepository.save(coach);
    }
    public Coach saveCoachFromRequest(PendingRequest pendingRequest) {
        Coach coach = new Coach();
        coach.setRole(ROLE_COACH);
        coach.setEmail(pendingRequest.getUser().getEmail());
        coach.setUsername(pendingRequest.getUser().getUsername());
        coach.setSurName(pendingRequest.getUser().getSurname());
        coach.setExperience(pendingRequest.getExperience());
        coach.setPassword(pendingRequest.getUser().getPassword());
        coach.setAccountNonExpired(true);
        coach.setAccountNonLocked(true);
        coach.setCredentialsNonExpired(true);
        coach.setEnabled(true);
        return coachRepository.save(coach);
    }

    @Override
    public Coach getCoach(int id) {
        return coachRepository.findById(id).orElseThrow(() -> new NotFoundExeption("coach with id : " + id, NOT_FOUND));
    }

    @Override
    public List<Coach> getAllCoach() {
        return coachRepository.findAll();
    }

    @Override
    public void deleteCoach(int id) {
        coachRepository.delete(this.getCoach(id));
    }

    @Override
    public Coach updateCoach(CoachRequest coachRequest) {
        Coach coach = this.getCoach(coachRequest.getId());
        ofNullable(coachRequest.getName()).ifPresent(coach::setUsername);
        ofNullable(coachRequest.getSurName()).ifPresent(coach::setSurName);
        ofNullable(coach.getExperience()).ifPresent(coach::setExperience);
        return coachRepository.save(coach);
    }

//    @Override
//    public Coach addNewTrainee(int coachId, int traineeId) {
//        User user = userService.findUserByIdAndRoleUser(traineeId);
//        Coach coach = this.getCoach(coachId);
//        coach.getTrainee().add(user);
//        return this.save(coach);
//
//    }
}
