package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to drop an item from their backpack.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class DropCommand implements UserInputCommand {
    private String itemName;

    /**
     * Non-default constructor
     *
     * @param itemName name of item
     */
    public DropCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * This method overrides the method in the UserInputCommand interface. It will drop the item from the backpack and add it to the ArrayList
     * of items in the location
     *
     * @param ts TourStats object
     * @return message telling the user the process is complete
     */
    @Override
    public String carryOut(TourStatus ts) {
        if (ts.listBackpackItems().contains(itemName)) {
            ts.dropItemFromBackpack(itemName);
            return "The item '" + itemName + "' has been dropped from the backpack.";
        } else {
            InvalidCommand command1 = new InvalidCommand(itemName);
            return command1.carryOut(ts);
        }
    }
}
