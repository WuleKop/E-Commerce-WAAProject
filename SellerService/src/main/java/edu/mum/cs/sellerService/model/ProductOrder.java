package edu.mum.cs.sellerService.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Order order;

    private Integer quantity;


}
