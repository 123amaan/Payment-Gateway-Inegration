package com.PaymentGateway.PaymentGateway;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class _1Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    private String name;
    private String email;
    private String phn_no;
    private String course;
    private Integer amount;
    private String orderStatus;
    private String razorPayOrderId;


    public Integer getOrderID() {
        return orderID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhn_no() {
        return phn_no;
    }

    public String getCourse() {
        return course;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getRazorpayOrderId() {
        return razorPayOrderId;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorPayOrderId = razorpayOrderId;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setPhn_no(String phn_no) {
        this.phn_no = phn_no;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "_1Order{" +
                "orderID=" + orderID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phn_no='" + phn_no + '\'' +
                ", course='" + course + '\'' +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                ", razorpayOrderId='" + razorPayOrderId + '\'' +
                '}';
    }
}
