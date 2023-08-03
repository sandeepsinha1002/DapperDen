package com.springboot.project.model;

import jakarta.persistence.Column;

public class PaymentInformation {


    @Column(name = "cardholder_name")
    private String cardholderName;

    @Column(name = "order_total")
    private String orderTotal;

    @Column(name = "card_number")
    private String cardNumber;
    
    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "cvv")
    private String cvv;
    
}
