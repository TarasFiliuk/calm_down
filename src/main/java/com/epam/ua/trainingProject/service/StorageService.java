package com.epam.ua.trainingProject.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void uploadFile(MultipartFile file);
}
