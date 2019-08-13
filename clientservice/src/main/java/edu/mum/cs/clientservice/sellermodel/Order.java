package edu.mum.cs.clientservice.sellermodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Data
public class Order {

    private Long id;
    private Long accountId;
    private String orderNumber;

    @JsonIgnore
    private List<Product> products = new ArrayList<>();
    @NotNull
    private Integer quantity;
    private Date orderDate;
    private Long paymentId;
    private Double tax;
    private ShippingStatus shippingStatus;

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }
}