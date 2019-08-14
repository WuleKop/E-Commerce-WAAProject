package edu.mum.cs.clientservice.sellerService;



import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.sellermodel.Account;
import edu.mum.cs.clientservice.sellermodel.Order;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import edu.mum.cs.clientservice.sellermodel.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    public Order postOrder(Order order){
        HttpEntity<Order> orderHttpEntity = new HttpEntity<>(order);
        Order peristedOrder = restTemplate.postForObject(sellerUrl+"/addOrder",orderHttpEntity,Order.class);
        return peristedOrder;

    }

    public String peristedProductorder(List<ProductOrder> list) {
        HttpEntity<List<ProductOrder>> request = new HttpEntity<>(list);
        String res = restTemplate.postForObject(sellerUrl + "/purchaseOrders", request, String.class);
        return res;
    }
    public void deleteProduct(Long id) {
        restTemplate.delete(sellerUrl+"/deleteProduct/"+id );
    }
    public List<Review> getReviews(Long id) {
        ResponseEntity<List<Review>> allReviews = restTemplate.exchange(sellerUrl + "/getReviews/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() { });
        return allReviews.getBody();
    }
    public List<Order> getProductOrders(Long pId) {
        ResponseEntity<List<Order>> allOrders = restTemplate.exchange(sellerUrl + "/getProductOrders/"+pId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() { });

        return allOrders.getBody();
    }
    public Order getOrderById(Long id) {
        ResponseEntity<Order> order = restTemplate.exchange(sellerUrl+"/getOrder/"+id,HttpMethod.GET,null,Order.class);
        return  order.getBody();
    }
    public Order updateOrder(Order order) {
        HttpEntity<Order> request = new HttpEntity<>(order);
        Order order1= restTemplate.postForObject(sellerUrl+"/addOrder", request, Order.class);
        return order1;
    }


}
