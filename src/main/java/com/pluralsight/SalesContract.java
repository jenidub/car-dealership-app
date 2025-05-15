package com.pluralsight;

public class SalesContract extends Contract {
    private boolean isFinanced;

    public SalesContract(
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicle,
            double totalPrice,
            double monthlyPayment,
            boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle, totalPrice, monthlyPayment);
        this.isFinanced = isFinanced;
    }

    public boolean getIsFinanced() {
        return isFinanced;
    }

    public void setIsFinanced(boolean isFinanced) {
        this.isFinanced = isFinanced;
    }

    public double getTotalPrice() {
        final double SALES_TAX = 1.05;
        final double RECORDING_FEE = 100;
        final double PROCESSING_FEE_UNDER_10K = 295;
        final double PROCESSING_FEE_STANDARD = 495;

        double calculatedTotalPrice = (vehicle.getPrice() * SALES_TAX) + RECORDING_FEE;
        if (vehicle.getPrice() >= 10000) {
            return calculatedTotalPrice += PROCESSING_FEE_STANDARD;
        } else {
            return calculatedTotalPrice += PROCESSING_FEE_UNDER_10K;
        }
    }

    public double getMonthlyPayment() {
        final double FINANCE_RATE_OVER_10K = 1.0425;
        final int FINANCE_TERM_OVER_10K = 48;
        final double FINANCE_RATE_UNDER_10K = 1.0525;
        final int FINANCE_TERM_UNDER_10K = 24;

        // financed and price >= 10K
        if (isFinanced && vehicle.getPrice() >= 10000) {
            // 4.25% rate , 48 months term
            setMonthlyPayment((getTotalPrice() * FINANCE_RATE_OVER_10K)/FINANCE_TERM_OVER_10K);
            return getMonthlyPayment();
        }
        // financed and price < 10K
        else if (isFinanced && vehicle.getPrice() < 10000) {
            // 5.25% rate , 24 months term
            setMonthlyPayment((getTotalPrice() * FINANCE_RATE_UNDER_10K)/ FINANCE_TERM_UNDER_10K);
            return getMonthlyPayment();
        }
        // not financed - paid in full
        else {
            return 0;
        }
    }
}
