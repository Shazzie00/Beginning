package Ogunseye;
import java.util.Scanner;
public class RPLS {

    private int lastWinner;
    private int consecutiveGoal;
    private int  matches;
    // counts and plays the loop each time until target is met
    private int countmatches = 0 ;

    // Each player wins and strings helps organize and keep track of each winner visually for me
    private static final int PLAYER1 = 0;
    private  String nameOne =  "";
    private int playeroneWins;

    private static final int PLAYER2 = 1;
    private  String nameTwo =  "";
    private int playertwoWins;

    // Posible choices in game has allocated number to print each
    private static final int LIZARD = 0;
    private static final int PAPER = 1;
    private static final int ROCK = 2;
    private static final int SCISSORS = 3;
    private static final int SPOCK = 4;
    private static final int TIE = -1;

    // Will only print 0-4
    private  int getrandomWeapon(){ return (int)(Math.random()*5); }

    // return is my break varibale to it. of there is no lizard  then it will reun the empty
    private String convert (int i){
        switch (i){
            case 0 : return "LIZARD";
            case 1 : return "PAPER";
            case 2 : return "ROCK";
            case 3 : return "SCISSORS";
            case 4 : return "SPOCK";
            case 5:  return "TIE";
        }
        return "";
    }

    //How to get who will win aka Consecutive win asks user
    private void  setConsecutiveGoal(int matches){
        if ( matches > 0 ){
            consecutiveGoal = matches;
        }
        else {
            // Check Validiaity ensures users keep proper numbers
            System.out.println("Invalid number, try again");
            Scanner user = new Scanner(System.in);
            System.out.print("How many consecutive wins ? ");
            matches = user.nextInt();
            // rerurns this method so matches is consecutive goal
            setConsecutiveGoal(matches);

        }
    }

    // does each round based on random selection given this round present what which user should win
    private int  playRound(int playerOneSelection, int playerTwoSelection) {
        if (playerOneSelection == playerTwoSelection){ return TIE; }

        if (playerOneSelection == SCISSORS && playerTwoSelection == PAPER){ return PLAYER1;}
        else if (playerOneSelection == PAPER && playerTwoSelection == ROCK){ return PLAYER1;}
        else if (playerOneSelection == ROCK && playerTwoSelection == LIZARD){ return PLAYER1;}
        else if ( playerOneSelection == LIZARD && playerTwoSelection == SPOCK){ return PLAYER1;}
        else if (playerOneSelection == SPOCK && playerTwoSelection == SCISSORS){ return PLAYER1;}
        else if (playerOneSelection == SCISSORS && playerTwoSelection == LIZARD){ return PLAYER1;}
        else if ( playerOneSelection == LIZARD && playerTwoSelection == PAPER ){return PLAYER1;}
        else if (playerOneSelection == PAPER && playerTwoSelection == SPOCK){ return PLAYER1;}
        else if (playerOneSelection == SPOCK && playerTwoSelection == ROCK) { return PLAYER1;}
        else if (playerOneSelection == ROCK && playerTwoSelection == SCISSORS) { return PLAYER1;}
        else {  return PLAYER2; }

    }

    // based iff the playing round this gives each rond the allocated number of points counting
    private void getresult (int playerOneSelection, int playerTwoSelection){

        int roundResult = playRound( playerOneSelection,playerTwoSelection);
        if (roundResult  == TIE ){
            this.playeroneWins = 0;
            this.playertwoWins = 0;
            lastWinner = TIE;
        }
        else if ( roundResult ==PLAYER1){
            this. playeroneWins++;
            this.playertwoWins = 0;
            lastWinner = PLAYER1;
        }
        else if ( roundResult == PLAYER2){
            this.playertwoWins++;
            this.playeroneWins = 0;
            lastWinner = PLAYER2 ;

        }
    }

    // returns last winner and displays name and wining streak
    private String  getLastWinner(){
        if (lastWinner == PLAYER1){
            return nameOne + " -- " + playeroneWins;
        }
        else if (lastWinner == PLAYER2)
        {
            return nameTwo + " -- "  + playertwoWins;}
        else {
            return "TIE ";
        }

    }

    // Calculate who the winner is
    private String  getWinner(){
        if (playeroneWins == consecutiveGoal){

            return "Winner: " + nameOne ;

        }
        else  {
            return  "Winner :" + nameTwo ;}

    }

    // Main  method that is called to pay the game
    public void playGame(){
        // Introduction into the game where user can name and undersand the rules
        System.out.printf("-------------------------------------------------------------------------------------------------%n)");
        System.out.println("Welcome to the arena! Here you wil witness two AI opponents locked in epic battle \n" +
                "with Rock Paper Scissor Lizard and Spock until one participant wins given number of matches in a row? ");
        Scanner user = new Scanner(System.in);
        System.out.print("How many consecutive wins ? ");
        matches = user.nextInt();

        // set how many times it will play
        setConsecutiveGoal(matches);
        user.nextLine();
        System.out.printf("-------------------------------------------------------------------------------------------------%n");
        // user can give name to each AI robot
        System.out.println("Give a name for the Robots  ");
        System.out.print(" | Robot One : ");
        this.nameOne = user.nextLine();
        System.out.print(" | Robot Two : ");
        this.nameTwo = user.nextLine();

        System.out.printf("-------------------------------------------------------------------------------------------------%n");

        // Learned and created a new formating system from: (THE SERVER SIDE) - How to use Java printf to format output
        // https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/How-to-use-Java-printf-to-format-output

        // the number represent amount of strings maximum. small letters do -s , for big letters do -S
        // 'S' is strings
        System.out.printf("| %-15s | %-15S | %-15S | %-15s| %n", "Round",nameOne, nameTwo, "Winner - Streak");
        System.out.printf("-------------------------------------------------------------------------------------------------%n");

        // Loops the game until one player reaches target goal
        while ( (playeroneWins < consecutiveGoal) && (playertwoWins < consecutiveGoal)) {
            int playerOneSelection = getrandomWeapon();
            int playerTwoSelection = getrandomWeapon();

            // Very Important makes sure each round is looped until target is reached, and shows each round
            getresult(playerOneSelection, playerTwoSelection);
            countmatches++;

            System.out.printf("| %-15d | %-15S | %-15S | %-15S| %n",
                    countmatches,convert (playerOneSelection), convert (playerTwoSelection),  getLastWinner());
        }
        //prints last winner
        //Have to creates a new variable to print winner so that the method does not loop each time
        String winner = getWinner();
        System.out.print(winner);

    }
}
