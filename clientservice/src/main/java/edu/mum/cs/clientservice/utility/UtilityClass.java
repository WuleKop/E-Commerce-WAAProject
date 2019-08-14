package edu.mum.cs.clientservice.utility;

import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import org.springframework.stereotype.Component;

import java.util.List;

public class UtilityClass {

    public String choosenOne(String wholeUrl){
        return wholeUrl.split("\n")[0];
    }

    public Double totalAmount(Double amount,Integer quantity){
        return  (amount* quantity);
    }

    public static Double subTotal(List<ProductOrder> productOrderList){
        Double total = 0.0;
        for(ProductOrder productOrder:productOrderList){
          total += (productOrder.getProduct().getPrice() * productOrder.getQuantity());
        }
        return  total;
    }

    public static Integer totalQuantity(List<ProductOrder> orders){
        return  orders.stream().mapToInt(x-> x.getQuantity()).sum();
    }
}
