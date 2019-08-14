package edu.mum.cs.clientservice.sellerService.controller;


import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.sellermodel.Order;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.ShippingStatus;
import edu.mum.cs.clientservice.utility.UploadingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @PostMapping("/testProduct")
    public Product addProduct(Product product){
        try {
            String res = UploadingImage.saveUploadedFiles(product.getPictures());
            product.setPictureUrls(res);
            return productService.createProduct(product);
        }catch (Exception e){e.printStackTrace();}
        return  null;
    }
    @GetMapping("/findAccountsOrder/{aId}")
    public List<Order> findAccountsOrders(@PathVariable Long aId) {
        return productService.getAccountsOrders(aId);
    }

}
