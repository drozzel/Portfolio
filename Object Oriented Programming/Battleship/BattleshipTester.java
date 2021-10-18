package battleship;

import battleship.players.drozzel_BattleshipPlayer;

/**
 * Created by yoda
 */
public class BattleshipTester {

    public static final int MAX = 3;  //number of complete games to play
    
    public static void main(String[] args) {

        drozzel_BattleshipPlayer a = new drozzel_BattleshipPlayer();
        drozzel_BattleshipPlayer b = new drozzel_BattleshipPlayer();


        for (int numGames = 0; numGames < MAX; numGames++) {
            a.reset();
            b.reset();
        
            Board aBoard = a.hideShips();
            System.out.println(aBoard);
      
            Board bBoard = b.hideShips();
            System.out.println(bBoard);
   
            int step = 1;
            for (int i = 0; i < 100; i++) {  //THIS LOOP PLAYS 1 GAME
               System.out.println("STEP " + step + " ================");
               bBoard.firedAtThisRound = false;
               a.go(bBoard);
               System.out.println(bBoard);
   
               aBoard.firedAtThisRound = false;
               b.go(aBoard);
               System.out.println(aBoard);
   
               if (aBoard.isComplete()) {
                   System.out.println("B Wins!");
                   break;
               }
               if (bBoard.isComplete()) {
                   System.out.println("A Wins!");
                   break;
               }
               step++;
           }//end game loop
      }//end all games
   }//end main()
}//end BattleshipTester class