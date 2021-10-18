package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to call an
 * item specific command that will switch that item to an item from the temporary item ArrayList in the Campus class.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class ItemCommand implements UserInputCommand{
    private String itemCommand;

    /**
     * Non-default constructor
     *
     * @param itemCommand name the item specific command
     */
    public ItemCommand(String itemCommand) {
        this.itemCommand = itemCommand;
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will return the item name that the
     * original item object has been changed to.
     *
     * @param ts the TourStatus object
     * @return the name of the new item
     */
    @Override
    public String carryOut(TourStatus ts) {
        String msg = "";
        Campus thisCampus = ts.getCampus();
        String itemName = itemCommand.substring(0,itemCommand.indexOf(":"));
        String commandName = itemCommand.substring(itemCommand.indexOf(":")+1,itemCommand.length());
        Item thisItem = ts.getItemFromBackpack(itemName);
        //Makes sure the item exists
        if( thisItem != null){
            ItemCommandInfo thisItemCommand = thisItem.getItemCommand(commandName);
            //Checks to make sure the command exists on the item
            if(thisItemCommand != null){
                //Runs through the command based on the type
                if(thisItemCommand.getCommandType().equals("Transform")){
                    ts.removeFromBackpack(thisItem);
                    thisCampus.addTempItem(thisItem);
                    ts.addToBackpack(thisCampus.getTempItem(thisItemCommand.getReturnItem()));
                    msg = thisItemCommand.getCommandMsg();
                    ts.addPoint(thisCampus.getTempItem(thisItemCommand.getReturnItem()).getValuePoint());
                } else if (thisItemCommand.getCommandType().equals("Disappear")) {
                    ts.removeFromBackpack(thisItem);
                    msg = thisItemCommand.getCommandMsg();
                } else if(thisItemCommand.getCommandType().equals("Print")) {
                    if(thisItem.getName().equals("UMW map")){
                        msg = thisItemCommand.getCommandMsg() +" " + ts.getCurrentLocation().getName();
                    } else {
                        msg =thisItemCommand.getCommandMsg();
                    }
                } else {
                    ArrayList<String> locationNames = new ArrayList<>(thisCampus.getLocations().keySet());
                    int randomNum = ThreadLocalRandom.current().nextInt(0, locationNames.size());
                    String newLocationName = locationNames.get(randomNum);
                    Location newLocation = thisCampus.getLocation(newLocationName);
                    //Picks a random number that is of a valid location to teleport to
                    while(newLocation.getLocked() || newLocation == ts.getCurrentLocation()) {
                        randomNum = ThreadLocalRandom.current().nextInt(0, thisCampus.getLocations().size());
                        newLocationName = locationNames.get(randomNum);
                        newLocation = thisCampus.getLocation(newLocationName);
                    }

                    ts.setCurrentLocation(newLocation);
                    msg = "You have been teleported to a new location!" + "\n\n" + ts.getCurrentLocation().getName();
                    msg = msg + "\n" + ts.getCurrentLocation().printPeople();
                    msg = msg + "\n" + ts.getCurrentLocation().getItemsInLocation();
                    msg = msg + "\n" + ts.getCurrentLocation().getDoors();
                    ts.addUserInputs(ts.getCurrentLocation().getName());
                }

            } else {
                msg = "This is not a valid item command!";
            }
        } else {
            msg = "This item does not exist in the backpack!";
        }
        return msg;
    }
}
