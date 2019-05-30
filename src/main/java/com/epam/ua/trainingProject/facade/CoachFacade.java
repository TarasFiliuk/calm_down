package com.epam.ua.trainingProject.facade;

import com.epam.ua.trainingProject.converter.CoachConverter;
import com.epam.ua.trainingProject.dto.CoachDTO;
import com.epam.ua.trainingProject.models.PendingRequest;
import com.epam.ua.trainingProject.request.CoachRequest;
import com.epam.ua.trainingProject.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoachFacade {
    private final CoachService coachService;
    private final CoachConverter coachConverter;

    public CoachDTO saveCoach(PendingRequest pendingRequest) {
       return coachConverter.convertCoach(coachService.saveCoachFromRequest(pendingRequest));
    }
    public CoachDTO findCoach(int id){
        return coachConverter.convertCoach(coachService.getCoach(id));
    }

    public List<CoachDTO> findAllCoach(){
        return coachService.getAllCoach().stream().map(coachConverter::convertCoach).collect(Collectors.toList());
    }

    public void deleteCoach(int id){
        coachService.deleteCoach(id);
    }

    public CoachDTO updateCoach(CoachRequest coachRequest) {
        return coachConverter.convertCoach(coachService.updateCoach(coachRequest));
    }
//    public CoachDTO addNewTrainee(int coachId,int traineeId){
//      return coachConverter.convertCoach(coachService.addNewTrainee(coachId,traineeId));
//    }
}
