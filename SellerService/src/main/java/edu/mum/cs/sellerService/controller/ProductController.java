package edu.mum.cs.sellerService.controller;

import edu.mum.cs.sellerService.model.Product;
import edu.mum.cs.sellerService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ProductController {

    // Linux: /home/{user}/test
    // Windows: C:/Users/{user}/test
    private static String UPLOAD_DIR = "/home/wule/Repos/WAAProject/E-Commerce-WAAProject/SellerService/src/main/resources/templates/productPictures";

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
      return productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@ModelAttribute Product product) {

        String result = null;
        try {

            result = this.saveUploadedFiles(product.getPictures());
            System.out.println(result);

        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        product.setPictureUrls(result);
        Product p = productService.save(product);
        return new ResponseEntity<Product>(p, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct (@PathVariable Long id) {
        productService.delete(id);
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
