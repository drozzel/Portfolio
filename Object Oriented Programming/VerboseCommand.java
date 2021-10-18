package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to switch
 * from having the directions printed or not.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class VerboseCommand implements UserInputCommand {
    private String verboseCommand;

    /**
     * Non-default constructor
     * @param verboseCommand the verbose command
     */
    public VerboseCommand(String verboseCommand) {
        this.verboseCommand = verboseCommand;
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will return whether
     * the verbose mode has been turned off or on.
     * @param ts the TourStatus object
     * @return a message saying that the mode is on or off
     */
    @Override
    public String carryOut(TourStatus ts) {
        if(verboseCommand.equalsIgnoreCase("on")){
            ts.setVerboseMode(true);
            return "Verbose mode has been turned on.";
        }
        else if(verboseCommand.equalsIgnoreCase(("off"))) {
            ts.setVerboseMode(false);
            return "Verbose mode has been turned off.";
        }
        else {
            InvalidCommand command1 = new InvalidCommand(verboseCommand);
            return command1.carryOut(ts);
        }
    }
}
