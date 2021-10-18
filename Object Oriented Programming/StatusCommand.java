package com.company;

public class StatusCommand implements UserInputCommand {
private String status;

/**
 * Non-default constructor
 * @param  status A string
 */
public StatusCommand(String status) {
    this.status = status;

}

@Override
    public String carryOut(TourStatus ts){
    int pointTotal = ts.getPointTotal();
    if(pointTotal <= 1) {
        return("You are a Copper Eagle with " + ts.getPointTotal() + " point(s)");
    }
    else if((pointTotal <=5) && (pointTotal > 1)) {
        return("You are a Silver Eagle with " + ts.getPointTotal() + " points!");
    }
    else if((pointTotal <=10) && (pointTotal >5)) {
        return("You are a Platinum Eagle with " + ts.getPointTotal() + " points!");
    }
    else if((pointTotal <=15) && (pointTotal >10)) {
        return("You are a Gold Eagle with " + ts.getPointTotal() + " points!");
    }
    else if((pointTotal <=19) && (pointTotal >15)) {
        return("You are a Palladium Eagle with " + ts.getPointTotal() + " points!");
    }
    else {
        return ("You are a Diamond Eagle with " + ts.getPointTotal() + " points!");
    }
}

}