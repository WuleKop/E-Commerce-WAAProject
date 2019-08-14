package edu.mum.cs.sellerService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private String name;
    private String description;
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-DD-YYYY")
    private Date manufacturedDate;
    private Integer stockQuantity;

    private Double price;
    @Lob
    @Column( length = 100000 )
    private String pictureUrls;

    @Transient
    @JsonIgnore
    private MultipartFile[] pictures;
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
//    @ManyToMany
//    private List<Order> orders = new ArrayList<>();
//
//    public void addOrder(Order order) {
//        if(orders.add(order)) {
//            order.addProduct(this);
//        }
//    }
//    public void removeOrder (Order order) {
//        if(orders.remove(order)) {
//            order.addProduct(null);
//        }
//    }

}
