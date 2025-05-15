package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class ContractFileManager {
    // save file path for contracts.csv as final variable
    private final String CONTRACT_FILE = "src/main/resources/contracts.csv";

    // Constructor
    public ContractFileManager() {
    }

    // saveContract() method - add entry to contract file
    public void saveContract(Contract newContract) {
        try (FileWriter fileWriter = new FileWriter(CONTRACT_FILE, true);
             BufferedWriter br = new BufferedWriter(fileWriter)){
            String salesEntry = null;
            String leaseEntry = null;

            if (newContract instanceof SalesContract) {
                salesEntry = createSalesEntry((SalesContract) newContract);
                br.write(salesEntry);
                br.newLine();
            } else {
                System.out.println("This is a lease transaction");
            }

        } catch (IOException error) {
            error.getLocalizedMessage();
        }
    }

    public String createSalesEntry(SalesContract contract) {
        // init StringBuilder for a sales entry to the contract file
        //        SALE|20210928|Dana Wyatt|dana@texas.com| => from method
        //        10112|1993|Ford|Explorer|SUV|Red|525123|995.00| => from existing method (vehicle info)
        //        49.75|100.00|295.00|1439.75|NO|0.00 => from SalesContract class

        String financedStatus = contract.getIsFinanced() ? "YES" : "NO";

        return "SALE|" + contract.date + "|" +
                contract.customerName + "|" +
                contract.customerEmail + "|" +
                contract.vehicle.convertVehicleToString(contract.vehicle) + "|" +
                String.format("$%.2f", contract.getSalesTax()) + "|" +
                String.format("$%.2f",contract.getProcessingFee()) + "|" +
                String.format("$%.2f",contract.getRecordingFee()) + "|" +
                String.format("$%.2f",contract.getTotalPrice()) + "|" +
                financedStatus + "|" +
                String.format("$%.2f", contract.getMonthlyPayment());
    }
}
