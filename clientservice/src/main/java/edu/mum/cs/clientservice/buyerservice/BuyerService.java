package edu.mum.cs.clientservice.buyerservice;


import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.sellermodel.Account;
import edu.mum.cs.clientservice.sellermodel.Order;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${admin.client}")
    private String adminUrl;

    @Value("${seller.client}")
    private String sellerUrl;

    @Value("${buyer.client}")
    private String buyerUrl;


    public Cart buyerCart(Account account) {
        ResponseEntity<Cart> responseEntity = restTemplate.exchange(buyerUrl + "/carts/" + account.getId(), HttpMethod.GET, null, Cart.class);
        return responseEntity.getBody();
    }

    public List<Product> allProducts() {
        ResponseEntity<List<Product>> allProducts = restTemplate.exchange(sellerUrl + "/getAllProducts", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
        });
        return allProducts.getBody();
    }

    public Product findOne(Long id){
         ResponseEntity<Product> product = restTemplate.exchange(sellerUrl+"/getProduct/"+id,HttpMethod.GET,null,Product.class);
         return  product.getBody();
    }


    public List<Order> buyerOrders(Long id){
        ResponseEntity<List<Order>> responseEntity = restTemplate.exchange(sellerUrl + "/buyerOrders/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
        });
        return  responseEntity.getBody();
    }




    //functionality
    public List<ProductOrder> addProductOrder(Product product,Order order,Integer quantity,List<ProductOrder> productOrders){
        ProductOrder productOrder = new ProductOrder();
        ProductOrder toRemove = null;
        for(ProductOrder orderIng: productOrders){
            if(orderIng.getProduct().equals(product)){
                toRemove = orderIng;
                break;
            }
        }
        if(toRemove != null){productOrders.remove(toRemove);};
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productOrder.setProduct(product);
        productOrder.setOrder(order);
        productOrder.setQuantity(quantity);
        productOrders.add(productOrder);
        return  productOrders;
    }



    //functionality
    public List<ProductOrder> removeProduct(List<ProductOrder> productOrders,Long productId){
        ProductOrder orderToRemove = null;
        for(ProductOrder productOrder:productOrders){
            if(productOrder.getProduct().getId() == productId){
                orderToRemove =productOrder;
                break;
            }
        }
        if(orderToRemove != null){productOrders.remove(orderToRemove);}
        return productOrders;
    }


}
