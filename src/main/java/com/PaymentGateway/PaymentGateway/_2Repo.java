package com.PaymentGateway.PaymentGateway;

import com.razorpay.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  _2Repo extends JpaRepository<_1Order, Integer> {
    public _1Order findByRazorPayOrderId(String orderId);
}
