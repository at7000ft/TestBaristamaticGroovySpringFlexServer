package com.baristamatic

import com.baristamatic.services.BaristaService
import com.baristamatic.services.IBaristaService

/**
 * <p> Title: Main.java </p>
 * <p> Description: Command line front-end
 * As a servlet use TestFlex4Baristamatic client
 *
 * </p>
 * <p> Oct 3, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */ 
public class Main{
    IBaristaService baristaService
    List validEntries

    static main(args) {
        Main main = new Main()
        main.init()
        main.mainLoop()
    }

    private init() {
        baristaService = new BaristaService()
        validEntries = baristaService.getDrinks().collect {it.number.toString()} + ["q", "r"]
    }

    private mainLoop() {
        def stdin = new BufferedReader(new InputStreamReader(System.in))

        while(true) {
            dumpInventory()
            showDrinks()
            String drinkNumber = stdin.readLine()
            if (!isValidEntry(drinkNumber) || drinkNumber.trim().length() == 0) {
                println "Invalid selection: " + drinkNumber + "\n"
                continue;
            }
            if (drinkNumber?.toLowerCase()?.equals("q")) {
                System.exit(0)
            } else if (drinkNumber?.toLowerCase()?.equals("r")) {
                //Restock
                baristaService = new BaristaService()
            } else if (!baristaService.areIngredientsAvailable(drinkNumber)) {
                println "Out of stock: " + baristaService.getDrink(drinkNumber).displayName
            } else {
                println "Dispensing: " + baristaService.getDrink(drinkNumber).displayName
                makeDrink(drinkNumber)
            }
        }
    }

    private isValidEntry(String entry) {
        if (entry == null || entry.length() == 0) {
            return false;
        }
        validEntries.contains(entry)
    }

    private dumpInventory() {
        println "Inventory:"
        def inventoryList = baristaService.getInventoryAvailable();
        for (i in inventoryList) {
            println i.displayName + ": " + i.count
        }
    }

    private showDrinks() {
        println "\nMenu"
        for (d in baristaService.getDrinks()) {
            println d.number + ", " + d.displayName + ", \$" +
                        d.cost +
                        ", " + baristaService.areIngredientsAvailable(Integer.toString(d.number))
        }
    }

    private makeDrink(String drinkNumber) {
        if (baristaService.getDrink(drinkNumber) != null) {
            baristaService.orderDrink(drinkNumber)
        }
    }
}
