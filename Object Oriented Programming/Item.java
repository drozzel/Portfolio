package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.util.*;

/**
 * This class holds all information regarding the item.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class Item {
    private String name;
    private String message;
    private int valuePoint;
    private ArrayList<ItemCommandInfo> commands = new ArrayList<ItemCommandInfo>();

    /**
     * Default constructor
     */
    public Item() {
        name = null;
        message = null;
        valuePoint = 0;
    }

    /**
     * Non-default constructor
     *
     * @param s scanner object
     */
    public Item(Scanner s) {
    }

    /**
     * Getter method for name
     *
     * @return name of item
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for message
     *
     * @return message of item
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for name
     *
     * @param name name of item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for message
     *
     * @param msg message of item
     */
    public void setMessage(String msg) {
        this.message = msg;
    }

    /**
     * Setter method to set the valuePoint
     *
     * @param valuePoint item value point
     */
    public void setValuePoint(int valuePoint) {
        this.valuePoint = valuePoint;
    }

    /**
     * Getter method to get the valuePoint
     *
     * @return point value of item
     */
    public int getValuePoint(){
        return valuePoint;
    }

    /**
     * this is a method to add a command  to the arrayList
     *
     * @param cn an ItemCommandInfo object to be added to the ArrayList of commands.
     */
    public void addCommand(ItemCommandInfo cn) {
        commands.add(cn);
    }

    /**
     * this is a method to get the command names of the item
     *
     * @return commandName the command name of the item
     */
    public String getCommandNames() {
        String commandName = "The commands for the " + name + " are: ";
        for(ItemCommandInfo n:commands) {
            commandName = commandName + n.getCommandName() + " | ";
        }
        return commandName;
    }

    /**
     * This method returns the specified itemCommandInfo object.
     *
     * @param commandName a String representing an ItemCommandInfo object.
     * @return the specified ItemCommandInfo object.
     */
    public ItemCommandInfo getItemCommand(String commandName){
       ItemCommandInfo returnMe = null;
       for(ItemCommandInfo ifc: commands){
           if(ifc.getCommandName().equals(commandName)){
               returnMe = ifc;
           }
       }
       return returnMe;
    }
}
