package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.util.Iterator;

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to move around campus.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class MovementCommand implements UserInputCommand {
    private String dir;

    /**
     * Non-default constructor
     * @param dir
     */
    public MovementCommand(String dir){
        this.dir = dir;
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will return the information on the location
     * the user has moved to.
     * @param ts the TourStatus object
     * @return location information
     */
    @Override
    public String carryOut(TourStatus ts) {
        String message = "";
        //This runs when leave Location returns an actual location.
        if (!((ts.getCurrentLocation().leaveLocation(dir)) == null)) {
            if(!ts.getCurrentLocation().leaveLocation(dir).getLocked()) {
                ts.updateTourLocation(dir);
                message = "\n" + ts.getCurrentLocation().getName();
                if(!ts.getCurrentLocation().getHasvisited()) {
                    message = message + "\n" + ts.getCurrentLocation().getDescription();
                }
                message = message + "\n" + ts.getCurrentLocation().printPeople();
                message = message + "\n" + ts.getCurrentLocation().getItemsInLocation();

                if (ts.getVerboseMode()) {
                    message = message + "\n" + ts.getCurrentLocation().getDoors();
                }
                ts.addPoint(ts.getCurrentLocation().getPointValue());
                ts.getCurrentLocation().setPointValue(0);
            } else {
                message = ts.getCurrentLocation().leaveLocation(dir).getName() + " is locked.";
                message = message + "\nYou must have at least " + ts.getCurrentLocation().leaveLocation(dir).getLockedPoints() + " points to unlock this location.";
                message = message + "\nYou can check your total points with the 'Status' command.";
            }
        }
        else if(ts.getCurrentLocation().leaveLocation(dir) == null){
            message = "There is no valid door in this direction!";
        }
        Iterator<String> keylist = ts.getCampus().getLocations().keySet().iterator();
        while(keylist.hasNext()) {
            ts.getCampus().getLocation(keylist.next()).updateLocked(ts.getPointTotal());
        }
        ts.getCurrentLocation().setHasvisited(true);
        return message;
    }


}
