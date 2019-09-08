package com.mum.ecommerce.iservice;

import com.mum.ecommerce.domain.Cart;

import java.util.List;

public interface ICartService {


    Cart createCart(Cart cart);

    Cart updateCart(Cart cart);

    String checkOutCart(Cart cart);

    List<Cart> clientCarts(Long clientId);


    Cart findCart(Long id);
}
