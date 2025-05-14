package com.pluralsight;
import java.util.*;


public class Dealership {
    // Dealership will hold information about the dealership (name, address, …)
    //and maintain a list of vehicles. Since it has the list of vehicles, it will also
    //have the methods that search the list for matching vehicles as well as
    //add/remove vehicles.

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
    public static void addVehicle(Vehicle vehicle) {

    }

    // Remove a vehicle from the inventory
    public static void removeVehicle(Vehicle vehicle) {

    }

    // Get Vehicles by characteristics methods
    public ArrayList<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        matchingVehicles.addAll(inventory);
        return matchingVehicles;
    }

    public ArrayList<Vehicle> getVehiclesByPrice (double min, double max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            double price = vehicle.getPrice();
            if (price >= min && price <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByMakeModel (String make, String model) {
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

    public ArrayList<Vehicle> getVehiclesByYear (int year) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            int vehicleYear = vehicle.getYear();
            if (vehicleYear == year) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByColor (String color) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            String vehicleColor = vehicle.getColor();
            if (vehicleColor.equalsIgnoreCase(color)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByMileage (double mileage) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            double vehicleMileage = vehicle.getOdometer();
            if (vehicleMileage == mileage) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };

    public ArrayList<Vehicle> getVehiclesByType (String type) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<Vehicle>();
        for (Vehicle vehicle: inventory) {
            String vehicleType = vehicle.getVehicleType();
            if (vehicleType.equalsIgnoreCase(type)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    };
}
