package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.util.ArrayList;

/**
 * This class holds all information of the person objects that are around campus
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class Person {
    private String name;
    private String dialogue1;
    private String dialogue2;
    private String returnDialogue;
    private ArrayList<Item> item = new ArrayList<Item>();
    private boolean haveTalked = false;
    private int valuePoint;

    /**This is the default constructor for the Person class.
     */
    Person(){

    }

    /**This method sets the name of the Person object.
     * @param name a String representing the name of the Person object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**This method returns the name of the Person object.
     * @return a String representing the name of the Person object
     */
    public String getName() {
        return name;
    }

    /**This method sets the beginning dialogue of the Person object.
     * This dialogue will be the first displayed dialogue when a user interacts
     * with the person object.
     * @param dialogue1 a String representing the beginning dialogue of a Person object.
     */
    public void setDialogue1(String dialogue1) {
        this.dialogue1 = dialogue1;
    }

    /**This method returns the beginning dialogue of the Person object.
     * @return a String representing the beginning dialogue of the Person object.
     */
    public String getDialogue1() {
        return dialogue1;
    }
    /**This method sets the ending dialogue of the Person object.
     * This dialogue will be the last displayed dialogue when a user interacts
     * with the person object.
     * @param dialogue2 a String representing the ending dialogue of a Person object.
     */
    public void setDialogue2(String dialogue2) {
        this.dialogue2 = dialogue2;
    }

    /**This method returns the ending dialogue of the Person object.
     * @return a String representing the ending dialogue of the Person object.
     */
    public String getDialogue2() {
        return dialogue2;
    }

    /**This method sets the dialogue when the user interacts with a person they have already talked to.
     * @param returnDialogue The String representing the return dialogue.
     */
    public void setReturnDialogue(String returnDialogue) {
        this.returnDialogue = returnDialogue;
    }

    /**This method returns the dialogue when the user interacts with a person they have already talked to.
     * @return The String representing the return dialogue.
     */
    public String getReturnDialogue() {
        return returnDialogue;
    }

    /**This method sets the value of the haveTalked boolean.
     * @param haveTalked a boolean that represents whether a Person object has
     * been interacted with or not.
     */
    public void setHaveTalked(boolean haveTalked) {
        this.haveTalked = haveTalked;
    }

    /**This method returns the value of the haveTalked boolean for a Person Object.
     * @return a boolean that represents whether a Person object has
     * been interacted with or not.
     */
    public boolean getHaveTalked(){
        return haveTalked;
    }

    /**This method adds an item to the Item array of the Person object.
     * @param item an Item object to be added to the Item array of the Person object.
     */
    public void addItem(Item item){
        this.item.add(item);
    }

    /**This method removes an item from the Item array of the Person object.
     * @param item an Item object to be removed from the Item array of the Person object.
     */
    public void removeItem(Item item){
        this.item.remove(item);
    }

    /**This method returns an ArrayList that represents the item of a Person object.
     * @return an ArrayList representing the item of a Person object.
     */
    public ArrayList<Item> getItem() {
        return item;
    }

    /**This method sets the amount of points interacting with this Person object is worth.
     * This amount includes only the interaction and does not include the amount the User
     * would gain with the item the Person object is holding.
     * @param valuePoint an Integer representing the amount of points the Person object is worth.
     */
    public void setValuePoint(int valuePoint) {
        this.valuePoint = valuePoint;
    }

    /**This method returns the amount of points the Person object is worth.
     * @return an Integer representing the amount of points the Person object is worth.
     */
    public int getValuePoint() {
        return valuePoint;
    }
}
