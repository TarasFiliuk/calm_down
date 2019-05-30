package com.epam.ua.trainingProject.facade;

import com.epam.ua.trainingProject.converter.RequestConverter;
import com.epam.ua.trainingProject.dto.RequestDTO;
import com.epam.ua.trainingProject.request.JoinToCoachRequest;
import com.epam.ua.trainingProject.service.PendingRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestFacade {
    private final RequestConverter requestConverter;
    private final PendingRequestService pendingRequestService;

    public RequestDTO createCoachRequest(JoinToCoachRequest joinToCoachRequest){
       return requestConverter.convertRequest(pendingRequestService.createCoachRequest(joinToCoachRequest));
    }
}
