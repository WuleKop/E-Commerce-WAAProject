package edu.mum.cs.clientservice.sellermodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "MM-DD-YYYY")
    private Date manufacturedDate;
    private Integer stockQuantity;

    private Double price;


    private String pictureUrls;


    @JsonIgnore
    private MultipartFile[] pictures;
    private String status;

    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        if(orders.add(order)) {
            order.addProduct(this);
        }
    }
    public void removeOrder (Order order) {
        if(orders.remove(order)) {
            order.addProduct(null);
        }
    }

}
