package com.pluralsight;
import java.io.*;
import java.util.*;


public class Dealership {
    // Declare a scanner for the class Dealership
    private final Scanner scanner = new Scanner(System.in);
    private final String FILE_NAME = "src/main/resources/inventory.csv";

    // Attributes
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();

    // Constructor
    public Dealership(String name, String address, String phone, ArrayList<Vehicle> inventory) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = getInventory();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

    // Add a vehicle to the inventory
    public ArrayList<Vehicle> addVehicle() {
        ArrayList<Vehicle> updatedInventory = getInventory();
        Vehicle newEntry = new Vehicle();

        //Get vehicle info from the user
        System.out.println("What is the VIN NUMBER of the new vehicle?    ");
        newEntry.setVin(Long.parseLong(scanner.nextLine()));
        System.out.println("What is the YEAR of the new vehicle?    ");
        newEntry.setYear(Integer.parseInt(scanner.nextLine()));
        System.out.println("What is the MAKE of the new vehicle?    ");
        newEntry.setMake(scanner.nextLine().trim());
        System.out.println("What is the MODEL of the new vehicle?    ");
        newEntry.setModel(scanner.nextLine().trim());
        System.out.println("What is the TYPE of the new vehicle? [ car | truck | SUV | van ]   ");
        newEntry.setVehicleType(scanner.nextLine().trim());
        System.out.println("What is the COLOR of the new vehicle?   ");
        newEntry.setColor(scanner.nextLine().trim());
        System.out.println("What is the MILEAGE of the new vehicle? Please round to the nearest whole number  ");
        newEntry.setOdometer(Integer.parseInt(scanner.nextLine()));
        System.out.println("What is the PRICE of the new vehicle? Numbers only  ");
        newEntry.setPrice(Double.parseDouble(scanner.nextLine()));
        updatedInventory.add(newEntry);
        return updatedInventory;
    }

    // Remove a vehicle from the inventory
    public ArrayList<Vehicle> removeVehicle(Vehicle vehicleToRemove) {
        ArrayList<Vehicle> updatedInventory = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            if (vehicle.getVin() != vehicleToRemove.getVin()) {
                updatedInventory.add(vehicle);
            }
        }
        return updatedInventory;
    }

    // Get Vehicles by characteristics methods
    public ArrayList<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        matchingVehicles.addAll(inventory);
        return matchingVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByPrice () {
        System.out.println("Minimum price:    ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.println("Maximum price:    ");
        double max = Double.parseDouble(scanner.nextLine());

        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            double price = vehicle.getPrice();
            if (price >= min && price <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByMakeModel () {
        System.out.println("Make of vehicle:    ");
        String make = scanner.nextLine();
        System.out.println("Model of vehicle:    ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            String vehicleMake = vehicle.getMake();
            String vehicleModel = vehicle.getModel();
            if (vehicleMake.equalsIgnoreCase(make) && vehicleModel.equalsIgnoreCase(model)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByYear () {
        System.out.println("Year of vehicle:    ");
        int year = Integer.parseInt(scanner.nextLine());

        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            int vehicleYear = vehicle.getYear();
            if (vehicleYear == year) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByColor () {
        System.out.println("Color of vehicle:    ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            String vehicleColor = vehicle.getColor();
            if (vehicleColor.equalsIgnoreCase(color)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByMileage () {
        System.out.println("Mileage of the vehicle:    ");
        double mileage = Double.parseDouble(scanner.nextLine());

        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            double vehicleMileage = vehicle.getOdometer();
            if (vehicleMileage == mileage) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByType () {
        System.out.println("Vehicle Type: Select from the following options [ car | truck | SUV | van ]    ");
        String type = scanner.nextLine();

        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            String vehicleType = vehicle.getVehicleType();
            if (vehicleType.equalsIgnoreCase(type)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public String convertVehicleToString(Vehicle vehicle) {
        String formattedEntry = "%1d | %2d | %3s | %4s | %5s | %6s | %7d | $%8.2f";
        return String.format(formattedEntry,
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice()
        );
    };
}
