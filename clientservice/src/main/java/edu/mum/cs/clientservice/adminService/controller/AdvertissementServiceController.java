package edu.mum.cs.clientservice.adminService.controller;

import edu.mum.cs.clientservice.adminService.AdvertissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdvertissementServiceController {

    @Autowired
    AdvertissementService advertissementService;

    @GetMapping("/uploadAd")
    public String uploadAdvertissement() {
        return "admin/advertUpload";
    }
//    @GetMapping("/getAdminAdvertissements/{aId}")
//    public String getAllProduct(@PathVariable Long aId, Model model) {
//        model.addAttribute("advertissements", advertissementService.adminAdvertissements(aId));
//        return "admin/allAdvertissements";
//    }
    @GetMapping("/getAd/{aId}")
    public String getAdvertissement(@PathVariable Long aId, Model model) {
        model.addAttribute("advertissement",advertissementService.findOne(aId));
        return "admin/advertDetail";
    }
    @GetMapping("/update/{aId}")
    public String getUpdate(@PathVariable Long aId, Model model) {
        model.addAttribute("advertissement", advertissementService.findOne(aId));
        return "admin/updateAdvert";
    }
}
