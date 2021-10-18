package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class implements the UserInputCommand interface. It handles any command that is invalid.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class InvalidCommand implements UserInputCommand {
    private String InvalidCommand;

    /**
     * Non-default constructor
     *
     * @param invalidCommand command entered by the user
     */
    public InvalidCommand(String invalidCommand) {
        InvalidCommand = invalidCommand;
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will let the user know that the command was invalid
     *
     * @param ts TourStatus object
     * @return message saying the command was invalid
     */
    public String carryOut(TourStatus ts) {
        return "'" + InvalidCommand + "' is an invalid command. Please enter a new command.";
    }
}
