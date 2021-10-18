package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class will handle any commands that deal with showing the backpack's contents
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class BackpackCommand implements UserInputCommand {

    /**
     * Default constructor
     */
    public BackpackCommand() {
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will return the list of backpack items when called.
     * @param ts the TourStatus object
     * @return item list in string format
     */
    @Override
    public String carryOut(TourStatus ts) {
        if (!(ts.backpackHasItems())) {
            return "Your backpack has no items.";
        } else {
            return ts.listBackpackItems() + "\n To use a command, type [Item Name]:[Command Name]";
        }
    }
}
