package edu.mum.cs.clientservice.adminService.controller;

import edu.mum.cs.clientservice.adminService.AdvertissementService;
import edu.mum.cs.clientservice.adminmodel.Advertissement;
import edu.mum.cs.clientservice.utility.UploadingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdsRestController {

    @Autowired
    private AdvertissementService advertissementService;


    @PostMapping("/testAdvertissement")
    public Advertissement addProduct(Advertissement advertissement){
        try {
            String res = UploadingImage.saveUploadedFiles(advertissement.getPictures());
            advertissement.setPictureUrls(res);
           return advertissementService.createAdvertissement(advertissement);
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
}


