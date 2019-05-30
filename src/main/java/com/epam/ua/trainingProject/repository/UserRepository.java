package com.epam.ua.trainingProject.repository;

import com.epam.ua.trainingProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //    User findByIdAndRole(int id, Role role);
    User findByUsername(String userName);

    Optional<User> findByEmail(String email);

    User findByActivationCode(String code);
}