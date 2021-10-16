package com.example.arouselsham.pojo.model;

public class PaymentModel {
    private String paymentText;
    private int paymentImage;

    public PaymentModel(String paymentText, int paymentImage) {
        this.paymentText = paymentText;
        this.paymentImage = paymentImage;
    }

    public String getPaymentText() {
        return paymentText;
    }

    public int getPaymentImage() {
        return paymentImage;
    }
}
