package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This interface holds the method that will be used by all different versions of user commands
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public interface UserInputCommand {

    /**
     * This method is the base for the processing of a user command.
     *
     * @param ts the TourStatus object
     * @return a string of the method being complete
     */
    public default String carryOut(TourStatus ts){
        return null;
    }

}
