package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Order;
import edu.mum.cs.sellerService.model.Product;
import edu.mum.cs.sellerService.model.ProductOrder;

import java.util.List;

public interface IProductorderService {

    ProductOrder newProductOrder(ProductOrder productOrder);




    ProductOrder updateProductOrder(ProductOrder productOrder);

    List<Order> productOrders(ProductOrder productOrder);

    List<Product> orderProducts(Order order);
    String addProductOrder(List<ProductOrder> productOrders);
}
