package com.PaymentGateway.PaymentGateway;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class _3Service {

    @Autowired
    private _2Repo repo;

    @Value("${razorpay.key.id}")
    private String razorpayKey;

    @Value("${razorpay.key.secret.key}")
    private String razorpaySecret;

    private RazorpayClient razorpayClient;

    public _1Order orderCreateOrder(_1Order order) throws Exception {

        JSONObject orderReq = new JSONObject();
        orderReq.put("amount", order.getAmount() * 100); // amount in paise
        orderReq.put("currency", "INR");
        orderReq.put("receipt", order.getEmail());

        this.razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

        Order razorPayOrder = razorpayClient.orders.create(orderReq);
        System.out.println(razorPayOrder);

        order.setRazorpayOrderId(razorPayOrder.get("id"));
        order.setOrderStatus(razorPayOrder.get("status"));

        repo.save(order);
        return order;
    }

    public _1Order updateOrder(HashMap<String, String> responsePayload){
        String razorpayOrderId = responsePayload.get("razorpay_order_id");
       _1Order order = repo.findByRazorPayOrderId(razorpayOrderId);
       order.setOrderStatus("PAYMENT COMPLETED");
       _1Order updatedOrder = repo.save(order);
       return updatedOrder;
    }
}
