package com.epam.ua.trainingProject.converter;

import com.epam.ua.trainingProject.dto.RequestDTO;
import com.epam.ua.trainingProject.models.PendingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestConverter {
    private final UserConverter userConverter;

    public RequestDTO convertRequest(PendingRequest pendingRequest){
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(pendingRequest.getId());
        requestDTO.setExperience(pendingRequest.getExperience());
        requestDTO.setStatus(pendingRequest.getStatus());
        requestDTO.setUser(userConverter.convertUser(pendingRequest.getUser()));
        return requestDTO;
    }
}
