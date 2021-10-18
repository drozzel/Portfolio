package com.company;
import java.util.*;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class stores all of the information for a location on the campus.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class Location {
    private String name;
    private String description;
    private Boolean isOutside;
    private Boolean isLocked;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Person> people = new ArrayList<>();
    private int lockedPoints;
    private int pointValue;
    private Boolean hasvisited;
    /**
     * Default constructor for the Location class.
     */
    public Location(){
        name = "placeholder";
        description = "placeholder";
        isLocked = true;
    }
    /**
     * Constructor that will be used later
     * @param s scanner object
     */
    public Location(Scanner s, String n){
    }
    /**
     * Getter method to retrieve the name of the location.
     *
     * @return name name of location
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name of the location.
     *
     * @param n name of location
     */
    public void setName(String n) {
        name = n;
    }
    /**
     * Getter method to get the description of the location.
     *
     * @return description description of the location
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter method to set the description of a location.
     *
     * @param d description of the location
     */
    public void setDescription(String d) {
        description = d;
    }

    /**This method sets the number of points needed to unlock this Location object.
     * @param lockedPoints an integer representing the number of points to unlock this Location object.
     */
    public void setLockedPoints(int lockedPoints) {
        this.lockedPoints = lockedPoints;
    }

    /**This method returns the number of points needed to unlock this Location object.
     * @return an integer representing the number of points needed to unlock this Location object.
     */
    public int getLockedPoints() {
        return lockedPoints;
    }

    /**This method sets the number of points a User will receive for visiting this Location object.
     * @param pointValue an integer representing the number of points the user will receive for visiting.
     */
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    /**This method returns the number of points a User will receive for visiting this Location.
     * @return an Integer representing the number of points the user will receive.
     */
    public int getPointValue() {
        return pointValue;
    }

    /**
     * Getter method to retrieve all of the doors and how to get to them for the location.
     *
     * @return message A message that tells what doors the location has and how to get to them
     */
    public String getDoors() {
        String message = "|";
        for(Door n:doors) {
            if (!n.getEntering().getLocked())
                message = message + " " + n.describe() + " |";
        }
        return message;
    }
    /**
     * This method checks to see if the user is able to go a certain direction and will return the location that the user
     * will enter if there is one.
     *
     * @param dir direction the user wants to go
     * @return entering location
     */
    public Location leaveLocation(String dir) {
        for(Door n:doors) {
            if(n.getDirection().equals(dir)) {
                return n.getEntering();
            }
        }
        return null;
    }
    /**
     * Adds a door to the ArrayList of doors.
     *
     * @param door door object
     */
    public void addDoor(Door door){
        doors.add(door);
    }

    /**
     * Adds an item to the item ArrayList
     *
     * @param item item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes item from the item ArrayList.
     *
     * @param item item
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Takes the name of the item and returns the item object
     *
     * @param name name of item
     * @return item object
     */
    public Item getItemNamed(String name) {
        boolean found = false;
        int index = 0;
        int count = 0;
        for (Item n:items) {
            if (n.getName().equals(name)) {
                found = true;
                index = count;
            }
            count++;
        } if (found) {
            return items.get(index);
        } else {
            return null;
        }
    }

    /**
     * Returns the list of items in the location in a string
     *
     * @return string of items
     */
    public String getItemsInLocation() {
        String message = "The items in " + getName() + " are a: | ";
        for(Item n:items)
            message = message + n.getName() + " | ";
        if (message.equals("The items in " + getName() + " are a: | ")){
            return "There are no items currently in " + getName();
        } else {
            return message;
        }
    }

    /**This method adds a Person object to the people ArrayList of this Location object.
     * @param person a Person object to be added to the people ArrayList.
     */
    public void addPerson (Person person){
        people.add(person);
    }

    /**This method removes a Person object from the people ArrayList of this Location object.
     * @param person a Person object to be added to the people ArrayList.
     */
    public void removePerson(Person person){
        people.remove(person);
    }

    /**This method returns the people ArrayList which is an ArrayList of Person objects.
     * @return an ArrayList of Person objects.
     */
    public ArrayList<Person> getPeople() {
        return people;
    }

    /**This method sets the boolean value of locked or not for a Location object.
     * a location object will be unlocked based on the status and point total of the User.
     * @param locked a boolean that represents  
     */
    public void setLocked(Boolean locked){
        this.isLocked = locked;
    }

    /**This method returns the boolean value of locked or unlocked for a Location object.
     * @return a Boolean representing an unlocked or locked location.
     */
    public Boolean getLocked() {
        return isLocked;
    }


    /**
     * Adds the message to the description of the location
     *
     * @param message any string
     */
    public void addToDescription(String message){
        description = description + "\n" + message;
    }

    /**This method prints all of the Person objects at the current location.
     * if there are no Person objects it will print "There are no people at this location!"
     * @return A String msg.
     */
    public String printPeople() {
        String message = "The people located here are: ";
        for(Person n:people) {
            message = message + "| " + n.getName() + " |";
        }
        if(!message.equals("The people located here are: ")){
        } else {
            message = " There are no people at this location!";
        }
        return message;

    }

    /**This method returns the Person object of the named Person!
     * @param personName a String
     * @return
     */
    public Person getPersonNamed(String personName){
        Person returnMe = null;
        for(Person p: people){
            if(p.getName().equals(personName)){
                returnMe = p;
            }
        }
        return returnMe;
    }

    /**This method updates the locked status of the Location object.
     * @param pointValue
     */
    public void updateLocked(int pointValue){
        if(pointValue >= lockedPoints){
            isLocked = false;
        }
    }

    /**
     * Getter method for hasVisited
     * @return if the location has been visited or not
     */
    public Boolean getHasvisited() {
        return hasvisited;
    }

    /**
     * Setter method for hasVisited
     * @param hasvisited if the location has been visited or not
     */
    public void setHasvisited(Boolean hasvisited) {
        this.hasvisited = hasvisited;
    }
}
