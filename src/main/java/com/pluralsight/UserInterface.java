package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // Declare an instance of DealershipFileManager
    DealershipFileManager fileManager = new DealershipFileManager();
    ContractFileManager contractFileManager = new ContractFileManager();

    //    the UserInterface will create the Dealership object when it is
    //    created. Down the road, there will be a user option to "switch dealerships".
    ArrayList<Dealership> dealershipList = new ArrayList<Dealership>();

    // Display the home screen with all user options
    public void displayHomeScreen() {
        // Class instantiation declarations
        Dealership currentDealership = fileManager.getDealership();

        // Class variable declarations
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        ArrayList<Vehicle> updatedInventory;
        long selectedVIN = 0;
        Vehicle matchedVehicle;

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
                    displayVehicleList(currentDealership.getVehiclesByPrice());
                    break;
                case "2":
                    displayVehicleList(currentDealership.getVehiclesByMakeModel());
                    break;
                case "3":
                    displayVehicleList(currentDealership.getVehiclesByYear());
                    break;
                case "4":
                    displayVehicleList(currentDealership.getVehiclesByColor());
                    break;
                case "5":
                    displayVehicleList(currentDealership.getVehiclesByMileage());
                    break;
                case "6":
                    displayVehicleList(currentDealership.getVehiclesByType());
                    break;
                case "7":
                    displayVehicleList(currentDealership.getAllVehicles());
                    break;
                case "8":
                    System.out.println("\n*** Create a New Vehicle ***");
                    updatedInventory = currentDealership.addVehicle();
                    currentDealership.setInventory(updatedInventory);
                    fileManager.saveDealership(currentDealership);
                    break;
                case "9":
                    System.out.println("\n*** Remove a New Vehicle ***");
                    System.out.println("Here is the current list of vehicles:");
                    displayVehicleList(currentDealership.getAllVehicles());

                    System.out.println("\nWhat is the VIN number of the card you want to remove?    ");
                    selectedVIN = Long.parseLong(scanner.nextLine());
                    matchedVehicle = findVINMatch(currentDealership.getInventory(), selectedVIN, scanner);
                    if (matchedVehicle != null) {
                        updatedInventory = currentDealership.removeVehicle(matchedVehicle);
                        currentDealership.setInventory(updatedInventory);
                    }

                    fileManager.saveDealership(currentDealership);
                    break;
                case "10":
                    // sale/lease a vehicle
                    System.out.println("\nWhat is the VIN number of the car?    ");
                    selectedVIN = Long.parseLong(scanner.nextLine());
                    matchedVehicle = findVINMatch(currentDealership.getInventory(), selectedVIN, scanner);
                    if (matchedVehicle != null) {
                        // prompt for sale or contract
                        System.out.println("Vehicle found! Is this a sale or a lease contract?    ");
                        String contractType = scanner.nextLine();
                        // activate the matching class
                        switch(contractType.toLowerCase()) {
                            case "sale":
                                // getSalesInfo() from user
                                SalesContract newSalesContract = addSalesTransaction(matchedVehicle, scanner);
                                contractFileManager.saveContract(newSalesContract);
                                break;
                            case "lease":
                                //get lease info
                                LeaseContract newLeaseContract = addLeaseTransaction(matchedVehicle, scanner);
                                contractFileManager.saveContract(newLeaseContract);
                                break;
                            default:
                                System.out.println("Invalid selection - please enter again");
                                break;
                        }

                        //remove vehicle after sale or lease is completed
                        updatedInventory = currentDealership.removeVehicle(matchedVehicle);
                        currentDealership.setInventory(updatedInventory);
                        fileManager.saveDealership(currentDealership);
                    } else {
                        System.out.println("There is no matching VIN. Please try again.");
                    }
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

    // Display the welcome screen when the user first opens the app
    public void displayWelcome() {
        System.out.println(
                " _____ _            ____             _               _     _            _                \n" +
                "|_   _| |__   ___  |  _ \\  ___  __ _| | ___ _ __ ___| |__ (_)_ __      / \\   _ __  _ __  \n" +
                "  | | | '_ \\ / _ \\ | | | |/ _ \\/ _` | |/ _ \\ '__/ __| '_ \\| | '_ \\    / _ \\ | '_ \\| '_ \\ \n" +
                "  | | | | | |  __/ | |_| |  __/ (_| | |  __/ |  \\__ \\ | | | | |_) |  / ___ \\| |_) | |_) |\n" +
                "  |_| |_| |_|\\___| |____/ \\___|\\__,_|_|\\___|_|  |___/_| |_|_| .__/  /_/   \\_\\ .__/| .__/ \n" +
                "                                                            |_|             |_|   |_|     "
        );

        System.out.println("Good morning {employee name}! Let's Get to Work!\n".toUpperCase());
    }

    public void displayOptionMenu() {
        System.out.println("Home Menu".toUpperCase());
        System.out.println("******************");
        System.out.println("Please select one of the following options using the associated number");
        System.out.println("[1] Find vehicles within a price range");
        System.out.println("[2] Find vehicles by make / model");
        System.out.println("[3] Find vehicles by year range");
        System.out.println("[4] Find vehicles by color");
        System.out.println("[5] Find vehicles by mileage range");
        System.out.println("[6] Find vehicles by type (car, truck, SUV, van)");
        System.out.println("[7] List all vehicles in the inventory");
        System.out.println("[8] Add a vehicle");
        System.out.println("[9] Remove a vehicle");
        System.out.println("[10] Sell / Lease a Vehicle");
        System.out.println("[99] Quit");
    }

    public void displayVehicleList(ArrayList<Vehicle> matchingVehicles) {
        String formattedEntry = "%d | %d | %s | %s | %s | %s | %d | $%.2f";
        for (Vehicle vehicle : matchingVehicles) {
            System.out.printf((formattedEntry) + "%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }

    public Vehicle findVINMatch(ArrayList<Vehicle> dealershipInventory, long selectedVIN, Scanner scanner) {
        for (Vehicle vehicle : dealershipInventory) {
            if (vehicle.getVin() == selectedVIN) {
                return vehicle;
            }
        }
        return null;
    }

    public SalesContract addSalesTransaction (Vehicle vehicleInfo, Scanner scanner) {
        // instantiate SalesContract class
        SalesContract newSalesContract = new SalesContract(
                "",
                "",
                "",
                vehicleInfo,
                0,
                0,
                false
        );

        // get today's date formatted per requirements yyyyMMdd
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(formatter);
        newSalesContract.setDate(formattedDate);

        // get customer's name
        System.out.println("What is the customer's name?");
        String customerName = scanner.nextLine();
        newSalesContract.setCustomerName(customerName);

        // get customer's email
        System.out.println("What is the customer's email?");
        String customerEmail = scanner.nextLine();
        newSalesContract.setCustomerEmail(customerEmail);

        System.out.println("Is this purchase financed? [yes | no]");
        String financeSelection = scanner.nextLine();
        newSalesContract.setIsFinanced(financeSelection.equalsIgnoreCase("yes"));

        return newSalesContract;
    }

    public LeaseContract addLeaseTransaction (Vehicle vehicleInfo, Scanner scanner) {
        // instantiate SalesContract class
        LeaseContract newLeaseContract = new LeaseContract(
                "",
                "",
                "",
                vehicleInfo,
                0,
                0,
                0,
                0
        );

        // get today's date formatted per requirements yyyyMMdd
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(formatter);
        newLeaseContract.setDate(formattedDate);

        // get customer's name
        System.out.println("What is the customer's name?");
        String customerName = scanner.nextLine();
        newLeaseContract.setCustomerName(customerName);

        // get customer's email
        System.out.println("What is the customer's email?");
        String customerEmail = scanner.nextLine();
        newLeaseContract.setCustomerEmail(customerEmail);

        // calculate the ending value
        newLeaseContract.endingValue = newLeaseContract.getEndingValue();

        // calculate the total price
        newLeaseContract.totalPrice = newLeaseContract.getTotalPrice();

        // calculate the monthly payment
        newLeaseContract.monthlyPayment = newLeaseContract.getMonthlyPayment();

        return newLeaseContract;
    }

}
