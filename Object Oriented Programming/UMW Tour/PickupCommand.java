package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to pickup an item from a location
 * and place it in their backpack.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class PickupCommand implements UserInputCommand {
    private String itemName;

    /**
     * Non-default constructor
     * @param itemName name of item
     */
    public PickupCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * This method overrides the method in the UserInputCommand interface. It will pick up the item from the location and add it to the ArrayList
     * of items in their backpack as well as remove it from the location's ArrayList.
     * @param ts TourStats object
     * @return message telling the user the process is complete as well as the message of the item
     */
    @Override
    public String carryOut(TourStatus ts) {
        if (!((ts.getCurrentLocation().getItemNamed(itemName)) == null)) {
            ts.addPoint(ts.getCurrentLocation().getItemNamed(itemName).getValuePoint());
            return "The item '" + itemName + "' has been added to your backpack. " + "\n" + ts.pickupItemFromLocation(itemName).getMessage();
        } else {
            InvalidCommand command1 = new InvalidCommand(itemName);
            return command1.carryOut(ts);
        }
    }
}
