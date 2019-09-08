package com.mum.ecommerce.service;

import com.mum.ecommerce.EcommerceApplication;
import com.mum.ecommerce.dao.CartDao;
import com.mum.ecommerce.domain.Cart;
import com.mum.ecommerce.iservice.ICartService;
import com.mum.ecommerce.utility.Messages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CartService implements ICartService {


    private static final Logger logger = LogManager.getLogger(EcommerceApplication.class);

    @Autowired
    private CartDao cartDao;



    @Override
    public Cart createCart(Cart cart) {
        try {
            cartDao.save(cart);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Cart updateCart(Cart cart) {
        try {
            return cartDao.save(cart);
        } catch (Exception e) {
            logger.error(e);
        }
        return  null;
    }

    @Override
    public String checkOutCart(Cart cart) {
        try {
           cartDao.delete(cart);
        } catch (Exception e) {
            logger.error(e);
        }
        return Messages.success;
    }

    @Override
    public List<Cart> clientCarts(Long clientId) {
        return cartDao.clientCarts(clientId);
    }

    @Override
    public Cart findCart(Long id) {
        return  cartDao.getOne(id);
    }
}
