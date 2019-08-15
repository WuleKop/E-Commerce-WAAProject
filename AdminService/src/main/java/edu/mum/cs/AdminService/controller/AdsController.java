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

    @Autowired
    private AdvertissementService advertissementService;
    @PostMapping("/newAdvertissement")
    public Advertissement savingAdvertissement(@RequestBody  Advertissement advertissement){
        return  advertissementService.save(advertissement);
    }
    @GetMapping("/getAllAds")
    public List<Advertissement> getAllAds() {
        return advertissementService.getAllAdvertissements();
    }

    @GetMapping("/getAdvertissement/{id}")
    public Advertissement getAd(@PathVariable Long id) {
        return advertissementService.findById(id);
    }

    @PutMapping("/updateAdvertissement")
    public Advertissement updateAd(@ModelAttribute Advertissement ad) {
        return advertissementService.update(ad);
    }

    @DeleteMapping("/deleteAd/{id}")
    public void deleteAd (@PathVariable Long id) {
        advertissementService.delete(id);
    }
}


