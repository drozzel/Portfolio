package com.company;
import java.util.*;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class stores all of the information of a door object.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class Door {
    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;
    /**
     * Constructor to build the door object.
     *
     * @param dir direction to get from the leaving location to the entering location
     * @param leave leaving location object
     * @param enter entering location object
     */
    public Door(String dir, Location leave, Location enter){
        direction = dir;
        leavingLocation = leave;
        enteringLocation = enter;
    }
    /**
     * Constructor that will be used later.
     *
     * @param s scanner object
     * @param c campus object
     */
    public Door(Scanner s, Campus c) {
    }
    /**
     * String that tells all of the doors the leaving location has and how to get to them.
     *
     * @return message the string of doors and the directions to get to them
     */
    public String describe() {
        String message;
        message = "You can go " + direction.toUpperCase() + " to get to " + enteringLocation.getName();
        return message;
    }
    /**
     * Getter method that retrieves the location the user will leave.
     *
     * @return leavingLocation location object the user is leaving
     */
    public Location getLeaving() {
        return leavingLocation;
    }
    /**
     * Setter method that sets the location the user is leaving.
     *
     * @param leave leaving location object
     */
    public void setLeaving(Location leave) {
        leavingLocation = leave;
    }
    /**
     * Getter method that retrieves the location the user will enter.
     *
     * @return enteringLocation location object the user is entering
     */
    public Location getEntering() {
        return enteringLocation;
    }
    /**
     * Setter method that sets the location the user is entering.
     *
     * @param enter entering locaion object
     */
    public void setEntering(Location enter) {
        enteringLocation = enter;
    }
    /**
     * Getter method that retrieves the direction for the door from one location to the other.
     *
     * @return direction direction to get to the door
     */
    public String getDirection(){
        return direction;
    }
    /**
     * Setter method to set the direction of the door from the leaving location to the entering location.
     *
     * @param dir direction of the door
     */
    public void setDirection(String dir){
        direction = dir;
    }
}
