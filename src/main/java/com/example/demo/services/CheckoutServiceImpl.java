package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{


    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository ) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve cart info from service
        Cart cart = purchase.getCart();

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //populate cart with cartItems.
        Set<CartItem> cartItems = purchase.getCartItems();
        if (cartItems.isEmpty()){
            return new PurchaseResponse("Cart Empty");
        }
        cartItems.forEach(item -> cart.add(item));

        cart.setCartItem(purchase.getCartItems());
        cart.setCustomer(purchase.getCustomer());

        //populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        cart.setStatus(Cart.StatusType.ordered);

        //save to database
        //customerRepository.save(customer);
        cartRepository.save(cart);



        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}