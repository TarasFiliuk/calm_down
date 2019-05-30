package com.epam.ua.trainingProject.converter;

import com.epam.ua.trainingProject.dto.CoachDTO;
import com.epam.ua.trainingProject.models.Coach;
import org.springframework.stereotype.Component;

@Component
public class CoachConverter {

    public CoachDTO convertCoach(Coach coach){
        CoachDTO coachDTO = new CoachDTO();
        coachDTO.setId(coach.getId());
        coachDTO.setName(coach.getUsername());
        coachDTO.setSurName(coach.getSurName());
        coachDTO.setExperience(coach.getExperience());
        coachDTO.setTrainee(coach.getTrainee());
        return coachDTO;
    }
 }
