package edu.mum.cs.clientservice.buyerservice;


import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.sellermodel.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

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


    public Cart buyerCart(Account account){
        ResponseEntity<Cart> responseEntity = restTemplate.exchange(buyerUrl+"/carts/"+account.getId() , HttpMethod.GET,null,Cart.class);
        return responseEntity.getBody();
    }




}
