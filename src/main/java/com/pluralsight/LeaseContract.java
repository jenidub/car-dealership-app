package com.pluralsight;

public class LeaseContract extends Contract {
    final double EEV_ENDING_VALUE_PCT = 0.50;
    double endingValue;
    final double LEASE_FEE_RATE = 0.07;
    double leaseFee;
    final double FINANCE_RATE = 1.04;
    final int FINANCE_TERM = 36;

    public LeaseContract (
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicle,
            double leaseFee,
            double totalPrice,
            double monthlyPayment,
            double endingValue) {
        super(date, customerName, customerEmail, vehicle, totalPrice, monthlyPayment);
        this.endingValue = endingValue;
        this.leaseFee = leaseFee;
    }

    public double getEndingValue() {
        return vehicle.getPrice() * EEV_ENDING_VALUE_PCT;
    }

    public double getLeaseFee() {
        return getEndingValue() * LEASE_FEE_RATE;
    }

    public double getTotalPrice() {
        return getEndingValue() + getLeaseFee();
    }

    public double getMonthlyPayment() {
        return (getTotalPrice() * FINANCE_RATE)/FINANCE_TERM;
    }

}
