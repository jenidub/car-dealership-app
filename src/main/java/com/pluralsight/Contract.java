package com.pluralsight;

public abstract class Contract {
    // Variable declarations
    protected String date, customerName, customerEmail;
    protected Vehicle vehicle;
    protected double totalPrice, monthlyPayment;

    // Constructor
    public Contract (String date, String customerName, String customerEmail, Vehicle vehicle, double totalPrice, double monthlyPayment) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // getTotalPrice() - abstract/calculated method
    public double getTotalPrice() {
        // is it a salesContract or a leaseContract?
        return 0;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // getMonthlyPayment() - abstract/calculated method
    public double getMonthlyPayment() {
        // is it a salesContract or a leaseContract?
        return 0;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

}
