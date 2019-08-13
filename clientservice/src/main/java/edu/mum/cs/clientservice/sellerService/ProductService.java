package edu.mum.cs.clientservice.sellerService;



import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.sellermodel.Account;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.Review;
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

    public Product createProduct(Product product){
        HttpEntity<Product> request = new HttpEntity<>(product);
        Product product1= restTemplate.postForObject(sellerUrl+"/newProduct", request, Product.class);
        return  product1;
    }
    public void deleteProduct(Long id) {
        restTemplate.delete(sellerUrl+"/deleteProduct/"+id );
    }
    public List<Review> getReviews(Long id) {
        ResponseEntity<List<Review>> allReviews = restTemplate.exchange(sellerUrl + "/getReviews/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() { });
        return allReviews.getBody();
    }

}
