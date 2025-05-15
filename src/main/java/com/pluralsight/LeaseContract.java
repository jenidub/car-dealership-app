package com.pluralsight;

public class LeaseContract extends Contract {
    final double EEV_ENDING_VALUE = 0.50;
    final double LEASE_FEE = 1.07;
    final double FINANCE_RATE = 1.04;
    final int FINANCE_TERM = 36;

    public LeaseContract (
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicle,
            double totalPrice,
            double monthlyPayment) {
        super(date, customerName, customerEmail, vehicle, totalPrice, monthlyPayment);
    }

    public double getTotalPrice() {
        return vehicle.getPrice() * LEASE_FEE;
    }

    public double getMonthlyPayment() {
        return (getTotalPrice() * FINANCE_RATE)/FINANCE_TERM;
    }

}
