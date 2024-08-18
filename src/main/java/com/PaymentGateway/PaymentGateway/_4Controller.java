package com.PaymentGateway.PaymentGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class _4Controller {

    @Autowired
    private _3Service service;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping(value = "/createOrder", produces = "application/json")
    @ResponseBody
    public ResponseEntity<_1Order> orderCreateOrder(@RequestBody _1Order order) throws Exception {
        _1Order orderCreateOrder = service.orderCreateOrder(order);
        return new ResponseEntity<>(orderCreateOrder, HttpStatus.CREATED);
    }

    @PostMapping("/handle-payment-callback")
    public String handlePaymentCallBack(@RequestParam HashMap<String, String> responsePayload){
        System.out.println(responsePayload);
        service.updateOrder(responsePayload);
        return "success";
    }
}
