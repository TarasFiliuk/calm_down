package com.epam.ua.trainingProject.controller;

import com.epam.ua.trainingProject.dto.RequestDTO;
import com.epam.ua.trainingProject.dto.UserDto;
import com.epam.ua.trainingProject.error.Errors;
import com.epam.ua.trainingProject.facade.RequestFacade;
import com.epam.ua.trainingProject.facade.UserFacade;
import com.epam.ua.trainingProject.request.JoinToCoachRequest;
import com.epam.ua.trainingProject.request.UserRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.ua.trainingProject.utils.Constants.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.epam.ua.trainingProject.utils.Constants.OPERATION_NOT_ALLOWED_MESSAGE;
import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class UserRestController {
    private final UserFacade userFacade;
    private final RequestFacade requestFacade;

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserRequest user) {
        UserDto userDTO = userFacade.saveNewUser(user);
        log.info("User with id {} successful created", userDTO.getId());
        return ok().body(userDTO);
    }

    @ApiOperation("update user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "update user"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest user) {
        log.info("User with id {} updated", user.getId());
        return ok().body(userFacade.updateUser(user));
    }


    @ApiOperation("get user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "get user"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        return ok().body(userFacade.getUser(id));
    }

    @ApiOperation("get all user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "get all user"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ok().body(userFacade.findAllUsers());
    }

    @ApiOperation("delete user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "delete user"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userFacade.deleteUser(id);
        log.info("User with id {} deleted", id);
        return ok().build();
    }
    @ApiOperation("delete user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "delete user"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping("/coachRequest")
    public ResponseEntity<RequestDTO> createCoachRequest(@RequestBody JoinToCoachRequest joinToCoachRequest){
        return  ok().body(requestFacade.createCoachRequest(joinToCoachRequest));

    }

    @GetMapping("/getCurrent")
    public Object getCurrent(){
        return SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
