package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Order;
import edu.mum.cs.sellerService.model.Product;
import edu.mum.cs.sellerService.model.ProductOrder;
import edu.mum.cs.sellerService.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductOrderImpl implements IProductorderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Override
    public ProductOrder newProductOrder(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    @Override
    public ProductOrder updateProductOrder(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    @Override
    public List<Order> productOrders(ProductOrder productOrder) {
        //for()
        return new ArrayList<>();
    }

    @Override
    public List<Product> orderProducts(Order order) {
        return null;
    }

    @Override
    public String addProductOrder(List<ProductOrder> productOrders) {
        try {
            for (ProductOrder p : productOrders) {
                productOrderRepository.save(p);
            }
            return "Saved Successfully";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
