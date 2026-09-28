package com.springapp.demo;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public void pay() {
        System.out.println("paid");
    }
}
