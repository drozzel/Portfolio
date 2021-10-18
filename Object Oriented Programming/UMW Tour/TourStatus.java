package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class will hold all the necessary information to keep track of the tour.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class TourStatus {
    private Campus campus;
    private Location currentLocation;
    private ArrayList<Item> backpack = new ArrayList<Item>();
    private static TourStatus theInstance = new TourStatus();
    private int pointTotal;
    private Boolean verboseMode = true;
    private ArrayList<String> userInputs = new ArrayList<String>();
    private ArrayList<String> savedUserInputs = new ArrayList<String>();

    /**
     * Default constructor for the class.
     */
    private TourStatus() {
        campus = null;
        currentLocation = null;
    }

    /**
     * Singleton use for constructor
     *
     * @return
     */
    public static TourStatus getInstance() {
        if (theInstance == null) {
            theInstance = new TourStatus();
        }
        return theInstance;
    }
    /**
     * Setter method to set the campus variable as a campus object.
     *
     * @param campus campus object
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    /**
     * Getter method that returns the campus variable.
     *
     * @return campus campus object
     */
    public Campus getCampus() {
        return campus;
    }
    /**
     * Setter method that sets the current location of the user.
     * @param location location object
     */
    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }
    /**
     * Getter method that returns the current location of the user
     *
     * @return currentlocation current location of the user
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }
    /**
     * This method will update the current location of the user as long as it is a valid direction.
     *
     * @param dir the direction the user wants to go
     */
    public void updateTourLocation(String dir) {
        currentLocation = currentLocation.leaveLocation(dir);
    }
    /**
     * Adds an item to the backpack ArrayList.
     *
     * @param item item to add
     */
    public void addToBackpack(Item item) {
        backpack.add(item);
    }
    /**
     * Removes an item from the backpack ArrayList.
     *
     * @param item item to remove
     */
    public void removeFromBackpack(Item item) {
        backpack.remove(item);
    }
    /**
     * Drops the item from the backpack and adds it to the location's ArrayList.
     *
     * @param name name of item
     * @return the item that was removed or null if the item does not exist
     */
    public Item dropItemFromBackpack(String name) {
        boolean found = false;
        int index = 0;
        int count = 0;
        for (Item n:backpack) {
            if (n.getName().equals(name)) {
                found = true;
                index = count;
            }
            count++;
        } if (found) {
            Item itemDropped = backpack.get(index);
            removeFromBackpack(itemDropped);
            getCurrentLocation().addItem(itemDropped);
            return itemDropped;
        } else {
            return null;
        }
    }
    /**
     * Picks up an item from a location and puts it in the backpack. It also removes the item from the location.
     *
     * @param name name of item
     * @return the item picked up
     */
    public Item pickupItemFromLocation(String name) {
        Item itemPickedUp = getCurrentLocation().getItemNamed(name);
        addToBackpack(itemPickedUp);
        getCurrentLocation().removeItem(itemPickedUp);
        return itemPickedUp;
    }
    /**
     * Lists the items in the backpack
     *
     * @return list of items in string form
     */
    public String listBackpackItems() {
        String message = "The items in your backpack are: ";
        String message2 = "";
       ListIterator iterator =backpack.listIterator();

        while (iterator.hasNext()){
            Item item = (Item) iterator.next();
          message2 = message2 +"\n" + item.getName() +"\n" + item.getCommandNames();

        }
        return message + message2;
    }
    /**
     * Checks to see if there are any items in the backpack.
     *
     * @return boolean
     */
    public boolean backpackHasItems() {
        if (backpack.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**This method sets the total value of pointTotal.
     * @param pointTotal an Integer representing the total points the User has accumulated.
     */
    public void setPointTotal(int pointTotal) {
        this.pointTotal = pointTotal;
    }

    /**This method returns the total value of pointTotaL.
     * @return An Integer representing the total points the User has.
     */
    public int getPointTotal() {
        return pointTotal;
    }

    /**This method is used to increase the pointTotal value.
     * @param points an Integer representing the number of points to be added.
     */
    public void addPoint(int points){
         pointTotal = pointTotal + points;
    }

    /**This method returns the specified Item form the backpack!
     * @param itemName a String representing the Item name.
     * @return the Specified item.
     */
    public Item getItemFromBackpack(String itemName){
        Item returnMe = null;
        for(Item i: backpack){
            if(i.getName().equals(itemName)){
                returnMe = i;
            }
        }
        return returnMe;
    }

    /**
     * this method will set the Verbose Mode to true or false for whether or not the verbose mode showuld be on
     * @param verbose Boolean true for on and false for off
     */
    public void setVerboseMode(Boolean verbose) {
        verboseMode = verbose;
    }

    /**
     * this method is a getter to determine whether the verbose mode will be on or off.
     * return the boolean for whether or not the verbose mode is on or off
     */
    public Boolean getVerboseMode() {
        return verboseMode;
    }

    public void addUserInputs(String input) {
        userInputs.add(input);
    }

    public ArrayList getUserInputs() {
        return userInputs;
    }

    public void addSavedUserInputs(String input) { savedUserInputs.add(input); }

    public ArrayList getSavedUserInputs() { return savedUserInputs; }
}
