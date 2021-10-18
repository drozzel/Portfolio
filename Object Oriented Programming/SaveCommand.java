package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.io.*;
import java.util.ArrayList;

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to save
 * their current state.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class SaveCommand implements UserInputCommand {
    private String saveCommand;

    /**
     * Non-default constructor
     * @param saveCommand the save command
     */
    public SaveCommand(String saveCommand) {
        this.saveCommand = saveCommand;
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will return the that the
     * current state of the tour has been saved.
     *
     * @param ts the TourStatus object
     * @return message saying the tour state has been saved
     */
    @Override
    public String carryOut(TourStatus ts) {

        String fileName = saveCommand.substring(saveCommand.indexOf(" "));
        String msg = "";
        File newFile = new File(fileName);

        try {
            //Checks if the file exists and if so adds on to the end
            if(!newFile.createNewFile()) {
                FileWriter myWriter = new FileWriter(newFile, true);
                ts.getUserInputs().forEach((n) -> {
                    try {
                        myWriter.write(n + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                myWriter.close();
                msg = " The following save has been updated " + fileName;
            //If the file does not exist it creates a new one
            } else {
                FileWriter myWriter = new FileWriter(newFile);
                //Adds on old commands if a file was restored previously
                ts.getUserInputs().forEach((n) -> {
                    ts.addSavedUserInputs(n.toString());
                });
                ts.getSavedUserInputs().forEach((n) -> {
                    try {
                        myWriter.write(n + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                myWriter.close();
                msg = "The following save has been created " + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
