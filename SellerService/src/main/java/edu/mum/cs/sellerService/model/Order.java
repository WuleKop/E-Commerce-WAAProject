package edu.mum.cs.sellerService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private String orderNumber;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "orders")
//    private List<Product> products = new ArrayList<>();
    @NotNull
    private Integer quantity;
    private Date orderDate;
    private Long paymentId;
    private Double tax;
    private ShippingStatus shippingStatus;

//    public void addProduct(Product product) {
//        products.add(product);
//    }

}