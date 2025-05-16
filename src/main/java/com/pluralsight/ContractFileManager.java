package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class ContractFileManager {
    // saveContract() method - add entry to contract file
    public void saveContract(Contract newContract) {
        // save file path for contracts.csv as final variable
        final String CONTRACT_FILE = "src/main/resources/contracts.csv";

        try (FileWriter fileWriter = new FileWriter(CONTRACT_FILE, true);
             BufferedWriter br = new BufferedWriter(fileWriter)){
            String newEntry;

            newEntry = newContract instanceof SalesContract ?
                    createSalesEntry((SalesContract) newContract)
                    : createLeaseEntry((LeaseContract) newContract);

            br.write(newEntry);
            br.newLine();

            System.out.println("\n**********   SUCCESS   **********\n");
            System.out.printf("The contract for VIN #%d has been added to the database successfully!\n", newContract.getVehicle().getVin());
            System.out.println("\n*********************************\n\n");

        } catch (IOException error) {
            error.getLocalizedMessage();
        }
    }

    // create a sales entry from the contract information
    public String createSalesEntry(SalesContract contract) {
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

    // create a lease entry from the contract information
    public String createLeaseEntry(LeaseContract contract) {
        // LEASE|20210928|Zachary Westly|zach@texas.com|37846|2021|
        //  Chevrolet|Silverado|truck|Black|2750|31995.00|
        //  15997.50|2239.65|18337.15|541.39

        return "LEASE|" + contract.date + "|" +
                contract.customerName + "|" +
                contract.customerEmail + "|" +
                contract.vehicle.convertVehicleToString(contract.vehicle) + "|" +
                String.format("$%.2f", contract.getEndingValue()) + "|" +
                String.format("$%.2f",contract.getLeaseFee()) + "|" +
                String.format("$%.2f",contract.getTotalPrice()) + "|" +
                String.format("$%.2f", contract.getMonthlyPayment());
    }

}
