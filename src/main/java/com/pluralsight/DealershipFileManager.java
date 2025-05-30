package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private final Dealership dealership = new Dealership("", "", "", null);
    private final ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();
    private final String DEALERSHIP_FILE = "src/main/resources/inventory.csv";

//    // Constructor
//    public DealershipFileManager() {
//    }

    // getDealership() method - converts the info in csv file and converts it into a Dealership class
    public Dealership getDealership() {
        boolean isFirstLine = true;
        String line;

        try (FileReader fileReader = new FileReader(DEALERSHIP_FILE);
             BufferedReader br = new BufferedReader(fileReader)) {
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    String[] dealerInfo = line.split("\\|");
                    dealership.setName(dealerInfo[0]);
                    dealership.setAddress(dealerInfo[1]);
                    dealership.setPhone(dealerInfo[2]);
                    isFirstLine = false;
                } else {
                    Vehicle vehicle = new Vehicle();
                    String[] vehicleInfo = line.split("\\|");
                    vehicle.setVin(Long.parseLong(vehicleInfo[0]));
                    vehicle.setYear(Integer.parseInt(vehicleInfo[1]));
                    vehicle.setMake(vehicleInfo[2]);
                    vehicle.setModel(vehicleInfo[3]);
                    vehicle.setVehicleType(vehicleInfo[4]);
                    vehicle.setColor(vehicleInfo[5]);
                    vehicle.setOdometer(Integer.parseInt(vehicleInfo[6]));
                    vehicle.setPrice(Double.parseDouble(vehicleInfo[7]));
                    inventory.add(vehicle);
                }
            }
            dealership.setInventory(inventory);
            isFirstLine = true;
            return dealership;
        } catch (IOException error) {
            error.getLocalizedMessage();
            return null;
        }
    };

    // saveDealership() method - saves changes to the inventory and rewrites the inventory.csv file
    public void saveDealership(Dealership dealership) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(DEALERSHIP_FILE))) {
            String firstLine = convertDealerInfoToString(dealership.getName(), dealership.getAddress(), dealership.getPhone());
            br.write(firstLine);
            br.newLine();
            for (Vehicle currentVehicle : dealership.getInventory()) {
                String vehicleEntry = currentVehicle.convertVehicleToString(currentVehicle);
                br.write(vehicleEntry);
                br.newLine();
            }

            System.out.println("\n**********   SUCCESS   **********\n");
            System.out.printf("The inventory file for dealership %s has been updated successfully!\n", dealership.getName());
            System.out.println("Returning to the Home Menu ........... ");
            System.out.println("\n*********************************\n\n");

        } catch (IOException error) {
            error.getLocalizedMessage();
        }
    };

    // helper methods for the saveInventory() method to create valid entry strings
    // dealerInfo string for the first line
    public String convertDealerInfoToString(String dealerName, String dealerAddress, String dealerPhone) {
        String formattedEntry = "%1s|%2s|%3s";
        return String.format(formattedEntry, dealerName, dealerAddress, dealerPhone);
    };

}
