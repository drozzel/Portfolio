package com.company;
import java.io.*;
import java.util.*;

//James Warndorf, Rachel Mattozzi, Daniel Rozzel | CPSC 240 Section 1 | On my honor... James Warndorf, Rachel Mattozzi, Daniel Rozzel

/**
 * This class is where the tour is run. It first takes the file holding the campus information and then sets up the campus.
 * After that, it then runs the tour.
 *
 * @author James Warndorf, Rachel Mattozzi, Daniel Rozzel
 */
public class TourUMW implements UserInputCommand {


    /**
     * This method is where the program is run. It will prompt the user to enter the campus file and will then check the
     * file and build the campus if the file is acceptable. After the campus is built, the tour begins.
     *
     * @param args
     * @throws IOException this will be thrown if there is an error when setting up the campus due to an error with the file
     */
    public static void main(String[] args) throws IOException {
        TourUMW tour1 = new TourUMW();
        Scanner scnr = new Scanner(System.in);
        //Set up the campus
        Campus FinishedCampus = tour1.setUpCampus(scnr);

        //Create the tour
        TourStatus Tour = TourStatus.getInstance();
        Tour.setCurrentLocation(FinishedCampus.getStartingLocation());
        Tour.setCampus(FinishedCampus);
        Tour.addPoint(FinishedCampus.getStartingLocation().getPointValue());
        FinishedCampus.getStartingLocation().setPointValue(0);
        Iterator<String> keylist = Tour.getCampus().getLocations().keySet().iterator();
        while(keylist.hasNext()) {
            Tour.getCampus().getLocation(keylist.next()).updateLocked(Tour.getPointTotal());

        }
        //Introduces the game to the user.
        System.out.println("Hello and welcome to the " + FinishedCampus.getCampusName() + " Tour! \nHere you will be able to virtually walk through the " + FinishedCampus.getCampusName()+" and have the opportunity to" +
                " talk to staff and students. "+
                "\nThroughout the tour, you will accumulate points. These points are obtainable by going to locations, talking to people, " +
                "\ntaking items from people, and picking up items from locations. To start, all the locations, except the entrance, are locked, " +
                "\nand it is your goal to unlock these locations with your points. The locations will automatically unlock when your accumulated " +
                "\npoints equal the amount of points needed to open the location. There are many exciting things to experience here, and we hope you enjoy your tour today!");


        //Handles the commands of the user and responds appropriately
        UserInputCommand response = new TourUMW();
        System.out.println("\nBefore we start, type 'Instructions' to see the list of commands on how to run this game. You can get these instructions anytime you need!");
        int count=0;
        while (!(response == null)) {
            if(count==0) {
                String instructions = scnr.nextLine();
                while(!(instructions.toLowerCase().equals("instructions"))) {
                    System.out.println("You must type 'Instructions' before starting the game.");
                    instructions = scnr.nextLine();
                }

                UserInputCommand instr = new InstructionsCommand(instructions);
                Boolean leave = true;
                System.out.println(instr.carryOut(Tour));
                System.out.println("\nTo start a new game, type 'Start' or to restore and old game, type 'Restore [filename.txt]'.");
                while (leave) {
                    String start = scnr.nextLine();
                    while (!((start.toLowerCase().equals("start")) || start.toLowerCase().contains("restore"))) {
                        System.out.println("That doesn't say Start or Restore! Please type 'Start' to start a new game or restore your old save file using 'Restore [filename.txt]'");
                        start = scnr.nextLine();
                    }
                    if (start.contains("restore ")) {
                        UserInputCommand restore = new RestoreCommand(start);
                        String msg = restore.carryOut(Tour);
                        if (msg.equals("Your saved file has been restored!")) {
                            leave = false;
                            System.out.println(msg);
                            System.out.println("\n" + Tour.getCurrentLocation().getName());
                            System.out.println(Tour.getCurrentLocation().getDescription());
                            System.out.println(Tour.getCurrentLocation().printPeople());
                            System.out.println(Tour.getCurrentLocation().getItemsInLocation());
                            System.out.println(Tour.getCurrentLocation().getDoors());
                        } else {
                            System.out.println(msg);
                        }
                    } else {
                        leave = false;
                        System.out.println("\n" + FinishedCampus.getStartingLocation().getName());
                        System.out.println(FinishedCampus.getStartingLocation().getDescription());
                        System.out.println(FinishedCampus.getStartingLocation().printPeople());
                        System.out.println(FinishedCampus.getStartingLocation().getDoors());
                        FinishedCampus.getStartingLocation().setHasvisited(true);
                    }
                }
            }
            if (count==0) {
                System.out.println("\nWhat would you like to do now? (For a full list of instructions, type 'Instructions')");
            } else {
                System.out.println("\nIf you get lost and have the map, type 'UMW map:read' to see where you are!");
                System.out.println("What would you like to do now? (For a full list of instructions, type 'Instructions')");
            }
            response = tour1.promptUser(scnr,Tour);
            if (!(response == null)) {
                System.out.println(response.carryOut(Tour));
            } else {
                System.out.println("We hope you enjoyed the tour!");
            }
            count++;
        }
    }

    /**
     * This method is used whenever we want to get something from the user.
     *
     * @param input scanner object
     * @return userInput input from the keyboard
     */
    public UserInputCommand promptUser(Scanner input, TourStatus ts) throws IOException {
        UserInputCommand command = null;
        String input1 = input.nextLine();

        if (("n s e w").contains(input1.toLowerCase())) {
            command = new MovementCommand(input1.toLowerCase());
            ts.addUserInputs(input1);
        } else if (("q").equals(input1.toLowerCase())) {
            return null;
        } else if (input1.toLowerCase().contains("verbose") || input1.toLowerCase().contains("v ")) {
            String[] tempinput1 = input1.toLowerCase().split(" ");
            command = new VerboseCommand(tempinput1[tempinput1.length-1]);
            ts.addUserInputs(input1);
        } else {
            if (input1.toLowerCase().contains("pickup")) {
                command = new PickupCommand(input1.substring(7));
                ts.addUserInputs(input1);
            } else if (input1.toLowerCase().contains("prop")) {
                command = new DropCommand(input1.substring(5));
                ts.addUserInputs(input1);
            } else if (input1.toLowerCase().equals("backpack")) {
                command = new BackpackCommand();
            } else if (input1.substring(0, 1).toLowerCase().equals("p")) {
                command = new PickupCommand(input1.substring(2));
                ts.addUserInputs(input1);
            } else if (input1.substring(0, 1).toLowerCase().equals("d")) {
                command = new DropCommand(input1.substring(2));
                ts.addUserInputs(input1);
            } else if (input1.contains(":")) {
                command = new ItemCommand(input1);
                ts.addUserInputs(input1);
            } else if (input1.toLowerCase().contains("interact ") || input1.toLowerCase().contains("i ")) {
                command = new InteractCommand(input1);
                ts.addUserInputs(input1);
            } else if (input1.toLowerCase().equals("instructions")) {
                command = new InstructionsCommand(input1);
            } else if (input1.toLowerCase().equals("status")) {
                command = new StatusCommand(input1);
            } else if (input1.toLowerCase().contains("save ") && input1.contains(".txt")) {
                command = new SaveCommand(input1);
            }else {
                command = new InvalidCommand(input1);
            }
            return command;
        }
        return command;
    }

    /**
     * This method will set up the campus based off of the campus file given by the user.
     *
     * @param s scanner object
     * @return FC the finished campus object
     * @exception IOException If the buffered reader finds an error it will throw it here
     */
    public Campus setUpCampus(Scanner s) throws IOException {
        System.out.println("Please enter the name of the campus text file including the file extension");
        String input = s.nextLine();

        //check if the file exists and if it is the correct format
        while(!(checkFile(input))) {
            input = s.nextLine();
        }
        Campus FC = new Campus(input);

        //reading the contents of the file
        String itemLine;
        BufferedReader reader = new BufferedReader(new FileReader(input));
        itemLine = reader.readLine();
        FC.setCampusName(itemLine);
        ArrayList<Person> allPersons = new ArrayList<>();

        //Loop until the whole file is read and both the Location hashtable and Door ArrayList are created
        while (itemLine != null) {
            boolean leave = false;
            if (itemLine.matches(FC.getCampusName()) || (itemLine.equals("*****"))) {
                itemLine=reader.readLine();

                //Loop to fill in the location object hashtable in the Campus class
            } else if (itemLine.matches("Locations:")) {
                Location l1 = new Location();
                itemLine=reader.readLine();

                //This first section creates the starting location and first location in the hashtable
                l1.setName(itemLine);
                itemLine=reader.readLine();
                String full = "";
                while (itemLine.length()>2) {
                    full = full + " " + itemLine;
                    itemLine=reader.readLine();
                }
                l1.setLockedPoints(Integer.parseInt(itemLine));
                itemLine=reader.readLine();
                l1.setPointValue(Integer.parseInt(itemLine));
                l1.setDescription(full);
                l1.setLocked(false);
                l1.setHasvisited(false);
                FC.addLocation(l1);
                FC.setStartingLocation(l1);
                itemLine=reader.readLine();

                //Fill in the rest of the hashtable by looping until we hit the delimiter "*****" telling us Doors: is next
                while (!leave) {
                    Location l2 = new Location();
                    itemLine = reader.readLine();
                    if (!itemLine.equals("*****")) {
                        l2.setName(itemLine);
                        full = "";
                        itemLine = reader.readLine();
                        while (itemLine.length()>2) {
                            full = full + " " + itemLine;
                            itemLine = reader.readLine();
                        }
                        l2.setLockedPoints(Integer.parseInt(itemLine));
                        itemLine=reader.readLine();
                        l2.setPointValue(Integer.parseInt(itemLine));
                        itemLine=reader.readLine();
                        l2.setDescription(full);
                        FC.addLocation(l2);
                        l2.setLocked(true);
                        l2.setHasvisited(false);
                    } else {
                        leave = true;
                    }

                }

                //Loop the same way as we did for Locations:
            } else if (itemLine.matches("Doors:")) {
                Location leaveloc;
                String dir = "placeholder";
                Location enterloc;
                leave = false;

                //Loop until the delimiter "*****"
                while (!leave) {
                    itemLine = reader.readLine();
                    if (!itemLine.equals("*****")) {

                        //Create the Door objects and add them to the Location objects (leaving location) that have those doors
                        while (!itemLine.equals("+++")) {
                            leaveloc = FC.getLocation(itemLine);
                            itemLine = reader.readLine();
                            dir = itemLine;
                            itemLine = reader.readLine();
                            enterloc = FC.getLocation(itemLine);
                            itemLine = reader.readLine();
                            Door d1 = new Door(dir, leaveloc, enterloc);
                            leaveloc.addDoor(d1);
                        }
                    } else {
                        leave = true;
                    }
                }

                //Loop the same way as we did for Locations
            } else if (itemLine.matches("People:")) {
                leave = false;
                String name = "";
                Location location;

                //Loop until the delimiter "*****"
                while (!leave) {
                    Person P1 = new Person();

                    itemLine = reader.readLine();
                    if (!itemLine.equals("*****")) {
                        //Create the Item objects and add them to the Locations that have them
                        name = itemLine;
                        P1.setName(name);
                        itemLine = reader.readLine();
                        location = FC.getLocation(itemLine);
                        itemLine = reader.readLine();
                        P1.setDialogue1(itemLine);
                        itemLine = reader.readLine();
                        P1.setDialogue2(itemLine);
                        itemLine = reader.readLine();
                        P1.setReturnDialogue(itemLine);
                        itemLine = reader.readLine();
                        P1.setValuePoint(Integer.parseInt(itemLine));
                        itemLine = reader.readLine();
                        location.addPerson(P1);
                        allPersons.add(P1);

                    } else {
                        leave = true;
                    }
                }
            }else if (itemLine.matches("Items:")) {
                leave = false;
                String name = "";
                Location location;

                //Loop until the delimiter "*****"
                while (!leave) {
                    Item I1 = new Item();
                    String message = "";
                    itemLine = reader.readLine();
                    if (!itemLine.equals("*****")) {
                        //Create the Item objects and add them to the Locations that have them
                        name = itemLine;
                        I1.setName(name);
                        itemLine = reader.readLine();
                        String locationName = itemLine;
                        itemLine = reader.readLine();
                        while (!itemLine.contains("[")) {
                            message = message + itemLine;
                            itemLine = reader.readLine();
                        }
                        if(!(message.equals(""))) {
                            I1.setMessage(message);
                        }
                        while(itemLine.contains("[")){

                            if(itemLine.contains("(")) {
                                String commandName = itemLine.substring(0, itemLine.indexOf("["));
                                String commandType = itemLine.substring(itemLine.indexOf("[")+1, itemLine.indexOf("("));
                                String returnItem = itemLine.substring(itemLine.indexOf("(")+1, itemLine.lastIndexOf(")"));
                                String commandMsg = itemLine.substring(itemLine.indexOf(":")+1,itemLine.length());
                                ItemCommandInfo thisItemCommand = new ItemCommandInfo(commandName,commandType,commandMsg);
                                thisItemCommand.setReturnItem(returnItem);
                                I1.addCommand(thisItemCommand);
                            } else {
                                String commandName = itemLine.substring(0, itemLine.indexOf("["));
                                String commandType = itemLine.substring(itemLine.indexOf("[")+1, itemLine.lastIndexOf("]"));
                                String commandMsg = itemLine.substring(itemLine.indexOf(":")+1,itemLine.length());
                                ItemCommandInfo thisItemCommand = new ItemCommandInfo(commandName,commandType,commandMsg);
                                I1.addCommand(thisItemCommand);
                            }
                            itemLine = reader.readLine();
                        }
                        I1.setValuePoint(Integer.parseInt(itemLine));
                        if(locationName.equals("none")){
                            FC.addTempItem(I1);
                        }
                        else if(!(FC.getLocation(locationName)==null)){
                            FC.getLocation(locationName).addItem(I1);
                        }
                        else{
                            for(Person person: allPersons){
                                if(locationName.equals(person.getName())){
                                    person.addItem(I1);
                                    break;
                                }
                            }
                        }
                        itemLine = reader.readLine();
                    } else {
                        leave = true;
                    }
                }
            }

        }

        //Finally, add the doors and items to the description of the location and return the finished campus object
//        FC.completeDescriptions();
        return FC;
    }

    /**
     * This method will check to make sure the file has the correct delimiters and format.
     *
     * @param name name of the file
     * @return whether the file is a valid format or not
     * @throws IOException if the file has an error it will throw it here
     * @exception FileNotFoundException if the file does not exist
     */
    public boolean checkFile(String name) throws IOException {
        String itemLine;
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            itemLine = reader.readLine();
            while (itemLine != null) {
                if (itemLine.equals("*****")) {
                    list.add(itemLine);
                } else if (itemLine.equals("Locations:")) {
                    list.add(itemLine);
                } else if (itemLine.equals("+++")) {
                    list.add(itemLine);
                } else if (itemLine.equals("Doors:")) {
                    list.add(itemLine);
                } else if (itemLine.equals("Items:")) {
                    list.add(itemLine);
                }
                itemLine = reader.readLine();
            }
            if ((list.contains("*****")) && (list.contains("Locations:")) && (list.contains("+++")) && (list.contains("Doors:")) && list.contains("Items:")) {
                return true;
            } else {
                System.out.println("The file you input has an incorrect format. Please check your format or submit a new file name");
                return false;
            }
        } catch(FileNotFoundException e)  {
            System.out.println("The file name you inputted does not exist. Please enter a new file name");
            return false;
        }
    }
}
