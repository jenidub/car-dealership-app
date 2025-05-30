package com.pluralsight;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // starting the application via its main()
        //method and then creating the user interface and getting it started.
        DealershipFileManager fileManager = new DealershipFileManager();
        Dealership newDealer = fileManager.getDealership();
        ArrayList<Vehicle> dealerInventory = newDealer.getInventory();
        UserInterface homeScreen = new UserInterface();
        homeScreen.displayHomeScreen();
    }
}