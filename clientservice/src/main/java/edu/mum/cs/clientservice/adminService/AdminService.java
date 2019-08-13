package edu.mum.cs.clientservice.adminService;


import edu.mum.cs.clientservice.adminmodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${admin.client}")
    private String adminUrl;

    @Value("${buyer.client}")
    private String buyerUrl;

    @Value("${seller.client}")
    private String sellerUrl;

   public User login(String emailAddress){
      ResponseEntity<User> responseEntity = restTemplate.exchange(adminUrl+"/login/"+emailAddress,
               HttpMethod.GET,null,User.class);
      return responseEntity.getBody();
   }

   //account for buyer
   public User createAccount(User user){
      ResponseEntity<User> responseEntity = restTemplate.postForEntity(adminUrl+"/registration",user,User.class);
      return  responseEntity.getBody();

   }

   public User createAccountForSeller(User user){
       HttpEntity<User> request = new HttpEntity<>(user);
       User user1 = restTemplate.postForObject(adminUrl+"/seller", request, User.class);
       return  user1;
   }
}
