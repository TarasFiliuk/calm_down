package com.epam.ua.trainingProject.controller;

import com.epam.ua.trainingProject.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/admin")
public class AdminRestController {

    private final AdminService adminService;

    @PostMapping("/approveCoach/{id}")
    public void approveCoach(@PathVariable int id){
        adminService.approveCoach(id);
    }
}
