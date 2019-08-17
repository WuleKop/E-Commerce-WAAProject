package edu.mum.cs.clientservice.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadingImage {

    public static String saveUploadedFiles(MultipartFile[] files) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File("images");
        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            String uploadFilePath =   "images/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(file.getOriginalFilename()).append("\n");
        }
        return sb.toString();
    }
}
