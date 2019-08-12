package edu.mum.cs.clientservice.buyermodel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class Payment {


    private Long Id;

    private String cardNumber;

    private String cvv;

    private  String cardHolder;

    private LocalDate expirationDate;

    //account for the client who made purchase
    private Long account;

    private double balanceAmount;
}
