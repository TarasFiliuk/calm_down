package com.epam.ua.trainingProject.controller;

import com.epam.ua.trainingProject.dto.CoachDTO;
import com.epam.ua.trainingProject.error.Errors;
import com.epam.ua.trainingProject.facade.CoachFacade;
import com.epam.ua.trainingProject.request.CoachRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.ua.trainingProject.utils.Constants.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.epam.ua.trainingProject.utils.Constants.OPERATION_NOT_ALLOWED_MESSAGE;
import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/coach")
public class CoachRestController {
    private final CoachFacade coachFacade;

//    @PostMapping("/register")
//    public ResponseEntity<CoachDTO> saveUser(@RequestBody CoachRequest coachRequest) {
//        CoachDTO coachDTO = coachFacade.saveCoachFromRequest(coachRequest);
//        log.info("User with id {} successful created", coachDTO.getId());
//        return ok().body(coachDTO);
//    }

    @ApiOperation("get coach")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "get coach"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/find/{id}")
    public ResponseEntity<CoachDTO> findCoach(@PathVariable int id) {
        return ok().body(coachFacade.findCoach(id));
    }

    @ApiOperation("get all coach")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "get all coach"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/findAll")
    public ResponseEntity<List<CoachDTO>> findAllCoach() {
        return ok().body(coachFacade.findAllCoach());
    }

    @ApiOperation("delete coach")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "delete coach"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoach(@PathVariable int id) {
        coachFacade.deleteCoach(id);
        return ok().build();
    }

    @ApiOperation("update coach")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "update user"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PutMapping("/updateCoach")
    public ResponseEntity<CoachDTO> updateUser(@RequestBody CoachRequest coachRequest) {
        log.info("User with id {} updated", coachRequest.getId());
        return ok().body(coachFacade.updateCoach(coachRequest));
    }

//    @ApiOperation("add new trainee")
//    @ApiResponses(value = {
//            @ApiResponse(code = SC_OK, message = "add new trainee"),
//            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
//            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
//    @PostMapping("{coachId}/addTrainee/{traineeId}")
//    public ResponseEntity<CoachDTO> addNewTrainee(@PathVariable int coachId,
//                                                  @PathVariable int traineeId) {
//
//        return ok().body(coachFacade.addNewTrainee(coachId, traineeId));
//    }
}
