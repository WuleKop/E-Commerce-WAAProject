package edu.mum.cs.clientservice.sellerService;


import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.sellermodel.Account;
import edu.mum.cs.clientservice.sellermodel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${admin.client}")
    private String adminUrl;

    @Value("${seller.client}")
    private String sellerUrl;

    @Value("${buyer.client}")
    private String buyerUrl;

    public List<Product> sellerProducts(Long sId) {
        ResponseEntity<List<Product>> allProducts = restTemplate.exchange(sellerUrl + "/getSellerProducts/"+sId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
        });
        return allProducts.getBody();
    }

    public Product findOne(Long id){
         ResponseEntity<Product> product = restTemplate.exchange(sellerUrl+"/getProduct/"+id,HttpMethod.GET,null,Product.class);
         return  product.getBody();
    }
//    public Product updateProduct(Product product) {
//        HttpEntity<Product> request = new HttpEntity<>(product);
//        ResponseEntity<Product> response = restTemplate.exchange(sellerUrl+"/updateProduct",HttpMethod.PUT, request, Product.class);
//        return response.getBody();
//    }

}
