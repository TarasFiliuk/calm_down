package com.epam.ua.trainingProject.service.impl;

import com.epam.ua.trainingProject.exeption.StorageException;
import com.epam.ua.trainingProject.service.StorageService;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {


    private String path;

    @Override
    public void uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
            String filename = file.getOriginalFilename();
            InputStream stream = file.getInputStream();

            Files.copy(stream, Paths.get(path + filename),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {

            String failed_to_store_file = String.format("Failed to store file", file.getName());

            throw new StorageException(failed_to_store_file, e);
        }

    }
}
