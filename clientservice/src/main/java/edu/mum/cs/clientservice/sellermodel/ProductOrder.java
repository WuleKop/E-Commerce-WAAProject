package edu.mum.cs.clientservice.sellermodel;


import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {


    private Long Id;

    private Product product;

    private Order order;

    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(order, that.order) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order, quantity);
    }
}
