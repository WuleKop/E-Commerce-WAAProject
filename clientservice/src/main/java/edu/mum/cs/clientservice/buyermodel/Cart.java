package edu.mum.cs.clientservice.buyermodel;


import edu.mum.cs.clientservice.sellermodel.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class Cart {


    private Long Id;



    private LocalDate cartDate;


    //orderId for reusing it
    private Long orderId;

    //the owner id of the cart
    private Long cartOwner;


    private Order order;

    //for the sake of keeping the total amount of the order placed on this cart
    private double  totalAmount;




}
