package com.epam.ua.trainingProject.service.impl;

import com.epam.ua.trainingProject.models.Coach;
import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.repository.CoachRepository;
import com.epam.ua.trainingProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;

    //
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElse(null);
        Coach coach = coachRepository.findByEmail(email);

        if (user != null) {
            return user;
        }
        if (coach != null){
            return coach;
        }
        throw new UsernameNotFoundException(
                "No user found with username: " + email);
    }

}