
package battleship.players;

import battleship.Board;

import java.util.Random;

public class drozzel_BattleshipPlayer implements BattleshipPlayer {

    //include your instance variables here to maintain a record of your game state
    //remember which of your opponent's squares you've shot at
    //remember what was revealed at each square so you can strategize future moves
    int row = 0;
    int col = 0;





    /**
     * hideShips - This method is called once at the beginning of each game
     * when you need to hide your ships.
     * <p>
     * You must return a valid Board object. See that class for details.
     * Note carefully: under *no* circumstances should you return the same
     * board twice in a row; i.e., two successive calls to your hideShips()
     * method must always return *different* answers!
     */
    public Board hideShips() {

        //this code prevents cheaters - leave this here to prevent cheaters from looking at your board
        try {
            SecurityManager antiCheater = new SecurityManager();
            System.setSecurityManager(antiCheater);
        } catch (SecurityException e) {
            System.out.println("");
        }

        Random random = new Random();

        char[][] battleGrid = new char[Board.HEIGHT][Board.WIDTH];

        int row;
        int column;
        int numShip = 0;
        char[] shipTypes = new char[]{'A', 'B', 'S', 'D', 'P'};
        int[] shipLengths = new int[]{5, 4, 3, 3, 2};
        int shipLength;
        char shipType;
        int xCoord;
        int yCoord;
        int numberOfShips = 5;
        boolean noOverlap = false;
        char[] orientations = new char[]{'V', 'H'};
        int counter;
        boolean inBounds = false;
        boolean validShip = false;


        for (column = 0; column < Board.WIDTH; column++) {

            for (row = 0; row < Board.HEIGHT; row++) {
                battleGrid[column][row] = ' ';
            }// end base row loop
        }// end base column loop

        while (numShip < numberOfShips) {
            shipType = shipTypes[numShip];
            shipLength = shipLengths[numShip];
            validShip = false;


            while (!validShip) {
                xCoord = random.nextInt(10);
                yCoord = random.nextInt(10);
                char orientation = orientations[random.nextInt(2)];
                inBounds = false;


                while (!inBounds) {
                    if (orientation == ('H') && xCoord + shipLength >= 10) {
                        xCoord = random.nextInt(10);
                    } else if (orientation == ('V') && yCoord + shipLength >= 10) {
                        yCoord = random.nextInt(10);
                    } else {
                        inBounds = true;
                    }
                }
                boolean noOverlap2 = true;
                counter = 0;

                if (orientation == 'V') {

                    while (noOverlap2 && counter < shipLength) {
                        noOverlap = battleGrid[xCoord][yCoord + counter] == (' ');
                        if (noOverlap) {
                            noOverlap2 = true;
                        } else {
                            noOverlap2 = false;
                        }
                        counter++;

                    }
                } else {
                    while (noOverlap2 && counter < shipLength) {
                        noOverlap = battleGrid[xCoord + counter][yCoord] == (' ');
                        if (noOverlap) {
                            noOverlap2 = true;
                        } else {
                            noOverlap2 = false;
                        }
                        counter++;

                    }
                }

                if (noOverlap && inBounds) {
                    validShip = true;

                    if (orientation == ('V')) {
                        for (counter = 0; counter < shipLength; counter++) {
                            battleGrid[xCoord][yCoord + counter] = shipType;
                        }

                    } else if (orientation == ('H')) {
                        for (counter = 0; counter < shipLength; counter++) {
                            battleGrid[xCoord + counter][yCoord] = shipType;
                        }


                    }


                }

            }
            numShip++;

        }
        Board myBoard = null;
        try{
            myBoard = new Board(battleGrid);
        }
        catch (Exception e){
            System.out.println("Board was setup incorrectly.");
        }
        return myBoard;
    }

    /**
     * go - This method is called repeatedly throughout the game, every
     * time it's your turn.
     * <p>
     * When it's your turn, and go() is called, you must call fireAt() on
     * the Board object which is passed as a parameter. You must do this
     * exactly *once*: trying to fire more than once during your turn will
     * be detected as cheating.
     */
    public void go(Board opponentsBoard) {
        // INSERT INCREDIBLE CODE HERE
        opponentsBoard.fireAt(row, col);
        if (row < 9) {
            row = row +1;

        }
        else{
            row = 0;
            col = col +1;
        }
    }

    /**
     * reset - This method is called when a game has ended and a new game
     * is beginning. It gives you a chance to reset any instance variables
     * you may have created, so that your BattleshipPlayer starts fresh.
     */
    public void reset() {
        // RESET YOUR OBJECT HERE
        int row = 0;
        int col = 0;
    }
}
