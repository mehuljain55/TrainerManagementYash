package com.yash.HrManager.service;

import com.yash.HrManager.Entity.Training;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileUploadService {


    public String setFilePath(MultipartFile file, Training training)
    {

        if(file==null)
        {
            return "No file";
        }

        String directoryPath = getDeploymentRootDirectory() + File.separator + "Files";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + File.separator + training.getTrainingId()+ ".xlsx";

        try {
            saveFile(file, filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed";
        }

    }


    private String getDeploymentRootDirectory() {
        try {
            // Get the root directory where the JAR/WAR is deployed
            return new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get deployment directory");
        }
    }

    private void saveFile(MultipartFile file, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }
    }
}

