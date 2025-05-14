package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // all output to the screen, reading of
    //user input, and "dispatching" of the commands to the Dealership as
    //needed. (ex: when the user selects "List all Vehicles", UserInterface would
    //call the appropriate Dealership method and then display the vehicles it
    //returns.)
    DealershipFileManager fileManager = new DealershipFileManager();

    //    the UserInterface will create the Dealership object when it is
    //    created. Down the road, there will be a user option to "switch dealerships".
    ArrayList<Dealership> dealershipList = new ArrayList<Dealership>();

    public UserInterface() {
    }

    public void displayHomeScreen() {
        Dealership currentDealership = fileManager.getDealership();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // Welcome Message
        displayWelcome();

        while (isRunning) {
            // display the Option Menu
            displayOptionMenu();

            // collect user choice
            System.out.println("\nPlease make your selection");
            String selection = scanner.nextLine();

            // switch statement to run selected option
            switch (selection) {
                case "1":
                    System.out.println("dealership.getVehiclesByPrice() - params double min, max");
                    break;
                case "2":
                    System.out.println("dealership.getVehiclesByMakeModel() - params String make or model");
                    break;
                case "3":
                    System.out.println("dealership.getVehiclesByYear() - params int year");
                    break;
                case "4":
                    System.out.println("dealership.getVehiclesByColor() - params String color");
                    break;
                case "5":
                    System.out.println("dealership.getVehiclesByMileage() - params double mileage");
                    break;
                case "6":
                    System.out.println("dealership.getVehiclesByType() - params String type");
                    break;
                case "7":
                    System.out.println("dealership.getAllVehicles() - no params");
                    break;
                case "8":
                    System.out.println("dealership.addVehicle() - param Vehicle");
                    break;
                case "9":
                    System.out.println("dealership.removeVehicle() - param Vehicle");
                    break;
                case "99":
                    System.out.println("Thank you for using our program. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid selection - please enter again");
                    break;
            }
        }

    }

    public void displayWelcome() {
        // TBD: Create Welcome Message Graphic
        System.out.println("Welcome to the JeniDub Dealership Management System!");
    }

    public void displayOptionMenu() {
        System.out.println("Home Menu");
        System.out.println("Please select one of the following options using the number");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
    }

}
