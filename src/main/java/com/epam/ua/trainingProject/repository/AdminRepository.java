package com.epam.ua.trainingProject.repository;

import com.epam.ua.trainingProject.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
