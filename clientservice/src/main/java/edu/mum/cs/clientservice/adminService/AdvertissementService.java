package edu.mum.cs.clientservice.adminService;

import edu.mum.cs.clientservice.adminmodel.Advertissement;
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
public class AdvertissementService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${admin.client}")
    private String adminUrl;

//    @Value("${seller.client}")
    private String sellerUrl;

    //@Value("${buyer.client}")
    private String buyerUrl;

//    public List<Advertissement> adminAdvertissements(Long aId) {
//        ResponseEntity<List<Advertissement>> allAdvertissements = restTemplate.exchange(adminUrl + "/getAdminAdvertissements/"+aId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Advertissement>>() {
//        });
//        return allAdvertissements.getBody();
//    }

    public Advertissement findOne(Long id){
        ResponseEntity<Advertissement> advertissement = restTemplate.exchange(adminUrl+"/getAdvertissement/"+id, HttpMethod.GET,null,Advertissement.class);
        return  advertissement.getBody();
    }

    public Advertissement createAdvertissement(Advertissement advertissement){
        HttpEntity<Advertissement> request = new HttpEntity<>(advertissement);
        Advertissement advertissement1= restTemplate.postForObject(adminUrl+"/newAdvertissement", request, Advertissement.class);
        return  advertissement1;
    }
}
