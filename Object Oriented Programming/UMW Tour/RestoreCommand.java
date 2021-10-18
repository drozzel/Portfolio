package com.company;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

import java.io.*;

/**
 * This class implements the UserInputCommand interface and is used whenever the user wants to load
 * the the state of the tour that they saved before.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class RestoreCommand implements UserInputCommand{
    private String restoreCommand;

    /**
     * Non-default constructor
     * @param restoreCommand the restore command
     */
    public RestoreCommand(String restoreCommand) {
        this.restoreCommand = restoreCommand;
    }

    /**
     * This method is overriding the method in the UserInputCommand interface. It will return the that the
     * previous saved state of the tour has been restored.
     *
     * @param ts the TourStatus object
     * @return message saying the tour state has been restored
     */
    @Override
    public String carryOut(TourStatus ts) {
        File file = new File(restoreCommand.substring(restoreCommand.indexOf(" ")));
        try {
            BufferedReader br = new BufferedReader(new FileReader(restoreCommand.substring(restoreCommand.indexOf(" "))));
            UserInputCommand command = null;
            String line = br.readLine();

            while (line != null) {
                String line1 = line;
                Boolean teleported = false;
                ts.addSavedUserInputs(line1);
                if (line.toLowerCase().equals("yes") || line.toLowerCase().equals("no")) {
                    line = br.readLine();
                    ts.addSavedUserInputs(line);
                }
                if (("n s e w").contains(line.toLowerCase())) {
                    command = new MovementCommand(line.toLowerCase());
                } else if (line.toLowerCase().contains("verbose") || line.toLowerCase().contains("v ")) {
                    String[] tempinput1 = line.toLowerCase().split(" ");
                    command = new VerboseCommand(tempinput1[tempinput1.length-1]);
                } else if (line.toLowerCase().contains("pickup")) {
                    command = new PickupCommand(line.substring(7));
                } else if (line.toLowerCase().contains("prop")) {
                    command = new DropCommand(line.substring(5));
                } else if (line.substring(0, 1).toLowerCase().equals("p")) {
                    command = new PickupCommand(line.substring(2));
                } else if (line.substring(0, 1).toLowerCase().equals("d")) {
                    command = new DropCommand(line.substring(2));
                } else if (line.contains(":")) {
                    line = br.readLine();
                    if(line == null) {
                        command = new ItemCommand(line1);
                    } else if (ts.getCampus().getLocation(line) instanceof Location) {
                        ts.setCurrentLocation(ts.getCampus().getLocation(line));
                        teleported = true;
                    } else {
                        command = new ItemCommand(line1);
                    }
                } else if (line.toLowerCase().contains("interact ") || line.toLowerCase().contains("i ")) {
                    line = br.readLine();
                    if(line == null) {
                        break;
                    }
                    if(line.toLowerCase().equals("yes")){
                        Campus thisCampus = ts.getCampus();
                        String personName = line1.substring(line1.indexOf(" ")+ 1);
                        Person thePerson = ts.getCurrentLocation().getPersonNamed(personName);
                        Item theirItem = thePerson.getItem().get(0);

                        ts.addToBackpack(theirItem);
                        thePerson.removeItem(theirItem);
                        ts.addPoint(theirItem.getValuePoint());
                        thePerson.setHaveTalked(true);
                        ts.addPoint(thePerson.getValuePoint());
                        thePerson.setValuePoint(0);
                    } else if (line.toLowerCase().equals("no")) {
                        Campus thisCampus = ts.getCampus();
                        String personName = line1.substring(line1.indexOf(" ")+ 1);
                        Person thePerson = ts.getCurrentLocation().getPersonNamed(personName);
                        Item theirItem = thePerson.getItem().get(0);

                        thePerson.setHaveTalked(true);
                        ts.addPoint(thePerson.getValuePoint());
                        thePerson.setValuePoint(0);
                    }
                }
                if(!(line1.toLowerCase().contains("interact ") || line1.toLowerCase().contains("i ") || (teleported))) {
                    line = br.readLine();
                    command.carryOut(ts);
                }
            }
            return "Your saved file has been restored!";
        } catch (IOException e) {
            return "This is not a valid save file! Please either enter 'start' to start a new game or try restoring a valid save file.";
        }
    }
}
