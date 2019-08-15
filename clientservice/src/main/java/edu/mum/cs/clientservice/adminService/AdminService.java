package edu.mum.cs.clientservice.adminService;



import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

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

   public String approveSeller(User user){
       ResponseEntity<String> responseEntity = restTemplate.postForEntity(adminUrl+"/approveSeller",user,String.class);
       return  responseEntity.getBody();
   }
//    public String approveReview(Review review){
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(adminUrl+"/approveReview",review,String.class);
//        return  responseEntity.getBody();
//    }

   public User createAccountForSeller(User user){
       HttpEntity<User> request = new HttpEntity<>(user);
       User user1 = restTemplate.postForObject(adminUrl+"/seller", request, User.class);
       return  user1;
   }


    public List<User> peristedSellers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(adminUrl + "/sellers", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        return  response.getBody();
    }
    public List<User> AllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(adminUrl + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        return  response.getBody();
    }
    public User getSellerAccount(User user){
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(sellerUrl+"/getSellerProducts/{sId}",user,User.class);
        return  responseEntity.getBody();

    }
    public User getAuthenticatedSellerAccount(User user){
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(adminUrl+"/loggedSeller",user,User.class);
        return  responseEntity.getBody();

    }
    public User approveSellerAccount(User user){
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(adminUrl+"/approve/sellers",user,User.class);
        return  responseEntity.getBody();

    }
    public List<User> allFollowers(Long id) {

        ResponseEntity<List<User>> response = restTemplate.exchange(adminUrl + "/followingSeller/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        return  response.getBody();
    }
    public List<User> allFollowed(Long id){
        ResponseEntity<List<User>> response = restTemplate.exchange(adminUrl + "/followedSeller/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        return  response.getBody();
    }

    public List<Review> UnapprovedReview() {
        ResponseEntity<List<Review>> response = restTemplate.exchange(sellerUrl + "/getUnapprovedReviews", HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
        });
        return response.getBody();
    }



}
