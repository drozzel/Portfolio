package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.util.Iterator;
import java.util.Scanner;

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to interact
 * with a person on campus.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class InteractCommand implements UserInputCommand {
    private String interactCommand;

    /**
     * Non-default constructor
     * @param interactCommand the interact command
     */
    public InteractCommand(String interactCommand) {
        this.interactCommand = interactCommand;
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will return a message
     * once the interaction is complete with what happened.
     * @param ts the TourStatus object
     * @return a message from the outcome from the interaction
     */
    @Override
    public String carryOut(TourStatus ts) {
        String msg = "";
        Campus thisCampus = ts.getCampus();
        String personName = interactCommand.substring(interactCommand.indexOf(" ")+ 1);
        Person thePerson = ts.getCurrentLocation().getPersonNamed(personName);
        if(thePerson != null){
            if(!thePerson.getHaveTalked()) {
                if(!thePerson.getItem().isEmpty()) {
                    Item theirItem = thePerson.getItem().get(0);
                    System.out.println(thePerson.getName() + ":" + " " + thePerson.getDialogue1());
                    System.out.println("\n" + "Would you like to take the " + theirItem.getName() + "? (yes/no)");
                    Scanner scanner = new Scanner(System.in);
                    String response = scanner.next();
                    while(response != null) {

                        if ((response.equalsIgnoreCase("yes"))) {
                            ts.addUserInputs(response);
                            msg = "The following item has been added to your backpack: " + theirItem.getName();
                            ts.addToBackpack(theirItem);
                            thePerson.removeItem(theirItem);
                            ts.addPoint(theirItem.getValuePoint());
                            msg = msg + "\n" + thePerson.getName() + ":" + " " + thePerson.getDialogue2();
                            thePerson.setHaveTalked(true);
                            ts.addPoint(thePerson.getValuePoint());
                            msg = msg + "\nYou earned " + thePerson.getValuePoint() + " for talking to " + thePerson.getName() + ", " +
                                    "and you earned " + theirItem.getValuePoint() + " for taking the " + theirItem.getName() + ". " +
                                    "This gives you a total of " + ts.getPointTotal() + " point(s).";
                            thePerson.setValuePoint(0);
                            break;
                        } else if ((response.equalsIgnoreCase("no"))) {
                            ts.addUserInputs(response);
                            System.out.println("You didn't take the " + theirItem.getName());
                            msg = msg + "\n" + thePerson.getName() + ":" + " " + thePerson.getDialogue2();
                            thePerson.setHaveTalked(true);
                            ts.addPoint(thePerson.getValuePoint());
                            msg = msg + "\n\nYou earned " + thePerson.getValuePoint() + " for talking to " + thePerson.getName() + "." +
                                    " This gives you a total of " + ts.getPointTotal() + " point(s).";
                            thePerson.setValuePoint(0);
                            break;
                        } else {
                            InvalidCommand command1 = new InvalidCommand(response);
                            System.out.println(command1.carryOut(ts));
                            response = scanner.next();
                        }
                    }
                }
                else{
                    msg =  thePerson.getName() + ":" + " " + thePerson.getDialogue1() + " " + thePerson.getDialogue2();
                    msg = msg + "\n\nYou earned " + thePerson.getValuePoint() + " for talking to " + thePerson.getName() + "." +
                            " This gives you a total of " + ts.getPointTotal() + " point(s).";
                    thePerson.setHaveTalked(true);
                }

            }
            else {
                if (!thePerson.getItem().isEmpty()) {
                    Item theirItem = thePerson.getItem().get(0);
                    System.out.println(msg = msg + "\n" + thePerson.getName() + ":" + " " + thePerson.getReturnDialogue());
                    System.out.println("\n" + "Would you like to take the " + theirItem.getName() + "?");
                    Scanner scanner = new Scanner(System.in);
                    String response = scanner.next();
                    ts.addUserInputs(response);
                    while (response != null) {
                        if ((response.equalsIgnoreCase("yes"))) {
                            ts.addUserInputs(response);
                            msg = "The following item has been added to your backpack: " + theirItem.getName();
                            ts.addToBackpack(theirItem);
                            thePerson.removeItem(theirItem);
                            ts.addPoint(theirItem.getValuePoint());
                            thePerson.setHaveTalked(true);
                            ts.addPoint(thePerson.getValuePoint());
                            msg = msg + "\n\nYou earned " + thePerson.getValuePoint() + " for talking to " + thePerson.getName() + ", " +
                                    "and you earned " + theirItem.getValuePoint() + " for taking the " + theirItem.getName()+ ". " +
                                    "This gives you a total of " + ts.getPointTotal() + " point(s).";
                            thePerson.setValuePoint(0);
                            break;
                        } else if ((response.equalsIgnoreCase("no"))) {
                            ts.addUserInputs(response);
                            msg = "You didn't take the " + theirItem.getName();
                            thePerson.setHaveTalked(true);
                            ts.addPoint(thePerson.getValuePoint());
                            msg = msg + "\n\nYou earned " + thePerson.getValuePoint() + " for talking to " + thePerson.getName() + "." +
                                    " This gives you a total of " + ts.getPointTotal() + " point(s).";
                            thePerson.setValuePoint(0);
                            break;
                        } else {
                            InvalidCommand command1 = new InvalidCommand(response);
                            System.out.println(command1.carryOut(ts));
                            response = scanner.next();
                        }
                    }
                } else {
                    msg = msg + thePerson.getName() + ":" + " " + thePerson.getReturnDialogue();
                    msg = msg + "\n\nYou earned " + thePerson.getValuePoint() + " for talking to " + thePerson.getName() + "." +
                            " This gives you a total of " + ts.getPointTotal() + " point(s).";
                }
            }
        }
        else{
            msg = "That person does not exist at this location!";
        }
        Iterator<String> keylist = ts.getCampus().getLocations().keySet().iterator();
        while(keylist.hasNext()) {
            ts.getCampus().getLocation(keylist.next()).updateLocked(ts.getPointTotal());
        }
        return msg;
    }
}