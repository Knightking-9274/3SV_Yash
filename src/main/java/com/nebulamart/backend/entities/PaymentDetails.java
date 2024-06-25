package com.nebulamart.backend.entities;

public class PaymentDetails {
    private String paymentMethod;
    private String paymentStatus;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorPaymentLinkStatus;
    private String razorpayPaymentid;

    public PaymentDetails(){

    }

    public PaymentDetails(String paymentMethod, String paymentStatus, String paymentId, String razorpayPaymentLinkId,
            String razorpayPaymentLinkReferenceId, String razorPaymentLinkStatus, String razorpayPaymentid) {
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentId = paymentId;
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
        this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
        this.razorPaymentLinkStatus = razorPaymentLinkStatus;
        this.razorpayPaymentid = razorpayPaymentid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpayPaymentLinkId() {
        return razorpayPaymentLinkId;
    }

    public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
    }

    public String getRazorpayPaymentLinkReferenceId() {
        return razorpayPaymentLinkReferenceId;
    }

    public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
        this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
    }

    public String getRazorPaymentLinkStatus() {
        return razorPaymentLinkStatus;
    }

    public void setRazorPaymentLinkStatus(String razorPaymentLinkStatus) {
        this.razorPaymentLinkStatus = razorPaymentLinkStatus;
    }

    public String getRazorpayPaymentid() {
        return razorpayPaymentid;
    }

    public void setRazorpayPaymentid(String razorpayPaymentid) {
        this.razorpayPaymentid = razorpayPaymentid;
    }
    
}
