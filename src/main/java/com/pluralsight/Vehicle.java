package com.pluralsight;

public class Vehicle {
    // Variables
    private int year, odometer;
    private long vin;
    private String make, model, color, vehicleType;
    private double price;

    // Constructors
    public Vehicle(long vin, int year, int odometer, String make, String model, String color, String vehicleType, double price) {
        this.vin = vin;
        this.year = year;
        this.odometer = odometer;
        this.make = make;
        this.model = model;
        this.color = color;
        this.vehicleType = vehicleType;
        this.price = price;
    }

    public Vehicle () {
        this.vin = 0;
        this.year = 0;
        this.odometer = 0;
        this.make = "";
        this.model = "";
        this.color = "";
        this.vehicleType = "";
        this.price = 0;
    }

    // Getters and Setters
    public long getVin() {
        return vin;
    }

    public void setVin(long vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String convertVehicleToString(Vehicle vehicle) {
        String formattedEntry = "%d|%d|%s|%s|%s|%s|%d|%.2f";

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
