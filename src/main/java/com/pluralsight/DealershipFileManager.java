package com.pluralsight;

import javax.xml.validation.ValidatorHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DealershipFileManager {
    //    responsible for reading the dealership file,
    //    parsing the data, and creating a Dealership object full of vehicles from the
    //    file. It will also be responsible for saving a dealership and the vehicles back
    //    into the file in the same pipe-delimited format

    boolean isFirstLine = true;
    Dealership dealership = new Dealership("", "", "", null);
    ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();

    // Constructor
    public DealershipFileManager() {
    }

    public Dealership getDealership() {
        final String DEALERSHIP_FILE = "src/main/resources/inventory.csv";
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
                    Vehicle vehicle = new Vehicle(0, 0, 0, "", "", "", "", 0.0);
                    String[] vehicleInfo = line.split("\\|");
                    vehicle.setVin(Integer.parseInt(vehicleInfo[0]));
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
            return dealership;
        } catch (IOException error) {
            error.getLocalizedMessage();
            return null;
        }
    };

    public void saveDealership() {
        System.out.println("saveDealership() method");
    }

}
