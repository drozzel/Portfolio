package com.company;
import java.util.*;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class holds all of the information on the campus object.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class Campus {
    private String campusName;
    private Hashtable<String, Location> locations = new Hashtable<String, Location>();
    private Location startingLocation;
    private String filename;
    private Hashtable<String, Item> tempItems = new Hashtable<String, Item>();
    /**
     * Constructor to set the file name.
     *
     * @param filename name of the file
     */
    public Campus(String filename){
        filename = filename;
    }
    /**
     * Constructor to set the starting location and name of the campus.
     *
     * @param entry entering location
     * @param name name of the campus
     */
    public Campus(Location entry, String name) {
        startingLocation = entry;
        campusName = name;
    }
    /**
     * Adds a location to the Hashtable of location objects.
     *
     * @param location location object
     */
    public void addLocation(Location location) {
        locations.put(location.getName(),location);
    }
    /**
     * Getter method to get the location when given the name.
     *
     * @param name name of location
     * @return location location object
     */
    public Location getLocation(String name) {
        return locations.get(name);
    }
    /**
     * Removes a location from the Hashtable of location objects given the name.
     *
     * @param name name of location
     */
    public void removeLocation(String name){
        locations.remove(name);
    }
    /**
     * Setter method to set the name of the campus.
     *
     * @param n name of campus
     */
    public void setCampusName(String n) {
        campusName = n;
    }
    /**
     * Getter method to retrieve the name of the campus.
     *
     * @return campusname name of campus
      */
    public String getCampusName() {
        return campusName;
    }
    /**
     * Setter method to set the starting location.
     *
     * @param start starting location object
     */
    public void setStartingLocation(Location start) {
        startingLocation = start;
    }
    /**
     * Getter method to retrieve the starting location object.
     *
     * @return startingLocation starting location object
     */
    public Location getStartingLocation() {
        return startingLocation;
    }
    /**
     * Setter method to set the name of the file
     *
     * @param f name of the file
     */
    public void setFilename(String f) {
        filename = f;
    }
    /**
     * Getter method to retrieve the name of the file.
     *
     * @return filename name of the file
     */
    public String getFilename() {
        return filename;
    }
    /**
     * this will add a temp item to the Hashtable
     *
     * @param ti Item
     */
    public void addTempItem(Item ti) {
        tempItems.put(ti.getName(),ti);
    }
    /**
     * this will remove the temp item from the hashtable
     *
     * @param item String
     */
    public void removeTempItem(String item) {
        tempItems.remove(item);
    }

    /**
     * this will get the temp items in the Hashtable
     *
     * @param item String
     * @return item Item object
     */
    public Item getTempItem(String item) {
        return tempItems.get(item);
    }

    /**This method returns the hashtable of locations.
     *
     * @return The Hashtable of locations
     */
    public Hashtable<String, Location> getLocations() {
        return locations;
    }
}