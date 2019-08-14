package edu.mum.cs.clientservice.service;


import edu.mum.cs.clientservice.adminmodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.lang.model.element.Parameterizable;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("http://ADMIN-SERVICE")
    private String adminUrl;

    @Value("http:://seller-service:8082")
    private String sellerUrl;

    @Value("http://BUYER-SERVICE")
    private String buyerUrl;


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

    public List<User> getSellers(){
        ResponseEntity<List<User>> responseEntity=restTemplate.exchange(adminUrl + "/sellers", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        return  responseEntity.getBody();
    }




}
