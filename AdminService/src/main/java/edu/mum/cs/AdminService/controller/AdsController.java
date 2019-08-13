package edu.mum.cs.AdminService.controller;

import edu.mum.cs.AdminService.iservice.AdvertissementService;
import edu.mum.cs.AdminService.model.Advertissement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class AdsController {
    private static String UPLOAD_DIR = "/Documents/WAAProject/E-Commerce-WAAProject/AdminService/src/main/resources/templates/AddsPictures";


    @Autowired
    private AdvertissementService advertissementService;

    @GetMapping("/getAllAds")
    public List<Advertissement> getAllAdvertissements() {
        return advertissementService.getAllAdvertissements();
    }

    @PostMapping("/addAdvertissement")
    public ResponseEntity<?> addAdvertissement(@ModelAttribute Advertissement advertissement) {

        String result = null;
        try {

            result = this.saveUploadedFiles(advertissement.getPictures());
            System.out.println(result);

        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        advertissement.setPictureUrls(result);
        Advertissement a = advertissementService.save(advertissement);
        return new ResponseEntity<Advertissement>(a, HttpStatus.OK);
    }

    @GetMapping("/getAdds/{id}")
    public Advertissement getAddAdvertissement(@PathVariable Long id) {
        return advertissementService.findById(id);
    }

    @PutMapping("/updateAdds")
    public Advertissement updateAdvertissement(@RequestBody Advertissement advertissement) {
        return advertissementService.update(advertissement);
    }

    @DeleteMapping("/deleteAdds/{id}")
    public void deleteAdvertissement (@PathVariable Long id) {
        advertissementService.delete(id);
    }


    // Save Files
    private String saveUploadedFiles(MultipartFile[] files) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append("\n");
        }
        return sb.toString();
    }
}

