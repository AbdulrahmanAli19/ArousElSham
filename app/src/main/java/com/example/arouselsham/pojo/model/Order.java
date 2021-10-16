package com.example.arouselsham.pojo.model;

import com.example.arouselsham.pojo.model.male.Meal;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private String userId;
    private String orderId;
    private String orderState;
    private UserAddress address;
    private boolean isDelivered;
    private long orderDate;
    private long deliverDate;
    private List<Meal> meals;
    private String paymentMethod;

    public Order(String userId, String orderId, String orderState, UserAddress address, long orderDate, long deliverDate, List<Meal> meals) {
        this.userId = userId;
        this.orderId = orderId;
        this.orderState = orderState;
        this.address = address;
        this.orderDate = orderDate;
        this.deliverDate = deliverDate;
        this.meals = meals;
    }

    public Order() {
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliverDate(long deliverDate) {
        this.deliverDate = deliverDate;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public UserAddress getAddress() {
        return address;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public long getDeliverDate() {
        return deliverDate;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
