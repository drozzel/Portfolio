package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class implements the UserInputCommand interface and is used to print out the instructions of the game
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class InstructionsCommand implements UserInputCommand{
    private String instruction;

    /**
     * Non-default constructor
     *
     * @param instruction the word instruction
     */
    public InstructionsCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * This method overrides the method in the UserInputCommand interface. It will return the list of instructions.
     *
     * @param ts TourStats object
     * @return message telling the user the instructions
     */
    @Override
    public String carryOut(TourStatus ts) {
        String message = "Here are the list of instructions!";
        message = message + "\n-To move locations, type                                                                 'n/s/w/e'";
        message = message + "\n-Verbose mode shows the doors of each location regardless if previously visited.         'verbose/v [on/off]'";
        message = message + "\n-To see your current status, type                                                        'Status'";
        message = message + "\n-To interact with a person, type                                                         'Interact [Person's Name]'";
        message = message + "\n-To take an item from a person, type                                                     'yes/no'";
        message = message + "\n-To pickup an item, type                                                                 'Pickup [Item Name]'";
        message = message + "\n-To drop an item, type                                                                   'Drop [Item Name]'";
        message = message + "\n-To do an item specific command, type                                                    '[Item name]:[Item Command]'";
        message = message + "\n-To see the items in your backpack, type                                                 'Backpack'";
        message = message + "\n-To save the game and name the file, type                                                'Save [File name]'";
        message = message + "\n-To restore the game, type                                                               'Restore [File name]'";
        message = message + "\n-To quit the game, type                                                                  'q'";
        return message;
    }
}
