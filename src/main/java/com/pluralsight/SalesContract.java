package com.pluralsight;

public class SalesContract extends Contract {
    final double SALES_TAX = 0.05;
    final double RECORDING_FEE = 100;
    final double PROCESSING_FEE_UNDER_10K = 295;
    final double PROCESSING_FEE_STANDARD = 495;

    private boolean isFinanced;
    private final double salesTax, recordingFee;
    private double processingFee;

    public SalesContract(
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicle,
            double totalPrice,
            double monthlyPayment,
            boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle, totalPrice, monthlyPayment);
        this.salesTax = SALES_TAX;
        this.recordingFee = RECORDING_FEE;
        this.processingFee = 0;
        this.isFinanced = isFinanced;
    }

    public boolean getIsFinanced() {
        return isFinanced;
    }

    public void setIsFinanced(boolean isFinanced) {
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        return salesTax * vehicle.getPrice();
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    // derived getter based on price
    public double getProcessingFee() {
        if (vehicle.getPrice() <= 10000) {
            return PROCESSING_FEE_UNDER_10K;
        } else {
            return PROCESSING_FEE_STANDARD;
        }
    }

    public double getTotalPrice() {
        return  vehicle.getPrice() + getSalesTax() + getRecordingFee() + getProcessingFee();
    }

    public double getMonthlyPayment() {
        final double FINANCE_RATE_OVER_10K = 1.0425;
        final int FINANCE_TERM_OVER_10K = 48;
        final double FINANCE_RATE_UNDER_10K = 1.0525;
        final int FINANCE_TERM_UNDER_10K = 24;

        // financed and price >= 10K
        if (isFinanced && vehicle.getPrice() >= 10000) {
            // 4.25% rate , 48 months term
            setMonthlyPayment(getTotalPrice() * FINANCE_RATE_OVER_10K / FINANCE_TERM_OVER_10K);
        }
        // financed and price < 10K
        else if (isFinanced && vehicle.getPrice() < 10000) {
            // 5.25% rate , 24 months term
            setMonthlyPayment(getTotalPrice() * FINANCE_RATE_UNDER_10K / FINANCE_TERM_UNDER_10K);
        }
        // not financed - paid in full - payment is 0
        else {
            setMonthlyPayment(0);
        }

        return monthlyPayment;
    }

}
