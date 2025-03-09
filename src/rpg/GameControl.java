package rpg;

/**
 * This is a simplified version of a role-playing game.
 */
public class GameControl {

  /**
   * Creates a human player to play the game.
   */
  HumanPlayer human = new HumanPlayer();

  /**
   * Creates a computer player to play the game.
   */
  ComputerPlayer computer = new ComputerPlayer();

  /**
   * Prints the game's context and rules.
   * Note: This method does not take any parameters and does not return anything.
   */
  public void printInstructions(){
    System.out.println();
    System.out.println("Welcome to the final battle against enemy forces. You will be facing off against the computer.");
    System.out.println("Each of you will have 3 units with randomly generated jobs and levels.");
    System.out.println("The jobs are: mage, knight, and archer. Archers are strong against mages, but weak against knights.");
    System.out.println("Mages are strong against knights, but weak against archers. Knights are strong against archers, but weak against mages.");
    System.out.println("There are two moves: attack (deal damage to one target) and block (temporarily increase defense).");
    System.out.println("Combat is turn based; all your live units will take a turn and then all the computer's live units will take a turn.");
    System.out.println("You have 10 turns to defeat the computer. If both players still have units standing, you only win ");
    System.out.println("if the combined HP of your units exceeds the computer's.");
    System.out.println();
  }
  
  /**
   * Prints the current status of all human units and all computer units.
   * Note: This method does not take any parameters and does not return anything.
   */
  public void printStatus(){
    System.out.println();
    System.out.println("Your units:");
    this.human.getFalia().printCurrentStatus();
    this.human.getErom().printCurrentStatus();
    this.human.getAma().printCurrentStatus();
    System.out.println();
    System.out.println("Computer units:");
    this.computer.getCriati().printCurrentStatus();
    this.computer.getLedde().printCurrentStatus();
    this.computer.getTyllion().printCurrentStatus();
    System.out.println();
  }

  /**
   * Takes the human player's turn by calling moveUnit on each of the human player's three units: Falia, Erom, and Ama.
   * Prints the unit's job and level before moving it. Checks if there is no winner before proceeding to the next move.
   * If there is a winner between the first and second unit's turn or between the second and third unit's turn,
   * then return out of the method to end the human turn.
   * Resets any computer temporary defense after all human units have made their move.
   * Note: This method does not return anything.
   * @param turn int representing the current turn that the game is on.
   */
  public void takeHumanTurn(int turn){

    takeHumanTurnHelper("falia");

    //prints a blank line
    System.out.println();

    //checks if there has been a winner of the game
    if (getWinner(turn) == null)  {
      //if there has not been a winner of the game, erom take a turn by calling the takeHumanTurnHelper method.
      takeHumanTurnHelper("erom");
    } else {
        //if the value of the getWinner method is not null, the method is exited with a return statement
        return;
    } 

    System.out.println();

    //checks if there has been a winner of the game
    if (getWinner(turn) == null)  {
      //if there has not been a winner of the game, ama take a turn by calling the takeHumanTurnHelper method.
      takeHumanTurnHelper("ama");
    } else {
        //if the value of the getWinner method is not null, the method is exited with a return statement
        return;
    } 
    //prints a blank line
    System.out.println();

    //reset temporary defence of any unit that may have used block during the turn using the resetTemporaryDefense method.
    this.computer.resetTemporaryDefense();

  }

  /**
   * Takes the unitName as a string and retrives the relevant unit.
   * Checks if the unit is still alive, if so a statement about the unit is printed.
   * Calls the moveUnit method from the human class to take the unit's turn.
   */
  public void takeHumanTurnHelper(String unitName){
    //initializes the unitForTurn variable
    Unit unitForTurn = null;

    if (unitName.equals("ama")) {
      unitForTurn = this.human.getAma();
    } else if (unitName.equals("falia")) {
        unitForTurn = this.human.getFalia();
    } else if (unitName.equals("erom")) {
        unitForTurn = this.human.getErom();
    }
    //checks if the unit is alive or not
    if (unitForTurn.getHp() > 0) {
      //if they are alive, then a statement about unit's job, name and level is printed
      System.out.println(unitForTurn.getJob().toUpperCase() + " " + unitForTurn.getName() + " (Lv. " + unitForTurn.getLevel() + ")" + " is ready to act.");
    }
    //calls the movunit method for unit to take their turn
    this.human.moveUnit(unitForTurn, this.computer);

  }

  /**
   * Takes the computer player's turn and resets any human temporary defense after the computer has made its moves.
   * Note: This method does not take any parameters and does not return anything.
   */
  public void takeComputerTurn(){
    //retrieves the instance of the given unit from the human class
    Unit falia = this.human.getFalia();
    Unit erom = this.human.getErom();
    Unit ama = this.human.getAma();

    //calls the computer strategy method to play the computer's turn
    this.computer.strategy(falia, erom, ama);

    //reset temporary defence of any unit that may have used block during the turn using the resetTemporaryDefense method.
    this.human.resetTemporaryDefense();

  }

  /**
   * Gets the winner of the game based on the turn parameter and whether one of the players has been knocked out.
   * If the turn is less than 10, return null if both players are alive, otherwise return the winner if the opposing player is knocked out.
   * If both players still have living units after 10 turns, then the player with the greatest sum of HP wins, otherwise it is a tie.
   * @param turn int representing the current turn that the game is on.
   * @return String representing who won the game ("human" or "computer") or "tie" if there is a tie.
   * Return null if both players are still alive and the current turn is less than 10.
   */
  public String getWinner(int turn){

    if (computer.isKnockedOut() == true) {
      return "human";
    } else if (human.isKnockedOut() == true) {
      return "computer";
    } else if (turn >= 10 && computer.isKnockedOut() == false && human.isKnockedOut() == false && computer.sumHp() > human.sumHp()) {
      return "computer";
    } else if (turn >= 10 && computer.isKnockedOut() == false && human.isKnockedOut() == false && computer.sumHp() < human.sumHp()) {
      return "human";
    } else if (turn >= 10 && computer.isKnockedOut() == false && human.isKnockedOut() == false && computer.sumHp() == human.sumHp()) {
      return "tie";
    }

    return null;

  }

  /**
   * Creates an instance of GameControl and contains the flow of this role-playing game.
   * Note: This method does not return anything.
   * @param args Not used.
   */
  public static void main(String[] args){

    //create GameControl object and print the game instructions
    GameControl game = new GameControl();

    game.printInstructions();

    //initialize a String variable to keep track of the winner
    String winner = "";
    //initialize a boolean variable to keep track of whether someone has won within 10 turns
    boolean hasWon = false;

    /*
     *  Create a loop that runs 10 times or exits if there is a winner. In each iteration:
     *    - print the current turn number
     *    - print the current status of all units. Hint: printStatus() is given to you in this class
     *    - take the human player's turn
     *    - check for a winner and update your String variable and boolean variable accordingly
     *    - print the current status of all units. Hint: printStatus() is given to you in this class
     *    - take the computer player's turn
     *    - check for a winner and update your String variable and boolean variable accordingly
     */

    //loop runs for ten turns
    for (int i = 1; i <= 10; i++) {
      //prints a blank line and a string divider at the start of each loop for visual clarity
      System.out.println();
      System.out.println("------------------------------------------------------------------------------------");
      //prints the current turn number
      System.out.println("The current turn number is: " + i);
      //prints a blank line
      game.printStatus();
      //Lets the player know that it is their turn 
      System.out.println("It is now your turn.");
      //prints a blank line
      System.out.println();
      //Calls the takeHumanTurn method to allow the player to take their turn
      game.takeHumanTurn(i);
      //Checks if there is a winner for the game, by checking if the output of the getWinner methpd is not null
      if (game.getWinner(i) != null) {
        //if there is a winner for the game, the winner variable is set to the output of the getWinner function
        winner = game.getWinner(i);
        //the has won variable is set to true
        hasWon = true;
        //the loops is exited
        break;
      }
      //prints the current status of the game using the printStatus method
      game.printStatus();
      //prints a statement telling the player that the computer is going to take their turn
      System.out.println("It is now the computer's turn.");
      //prints a blank line
      System.out.println();
      //calls the takeComputerTurn method to allow the computer to take its turn
      game.takeComputerTurn();
      if (game.getWinner(i) != null) {
        //if there is a winner for the game, the winner variable is set to the output of the getWinner function
        winner = game.getWinner(i);
        //the has won variable is set to true
        hasWon = true;
        //the loops is exited
        break;
      }
    }

    //Find the winner if there wasn't one determined within the 10 turns
    if (hasWon == false) {
      winner = game.getWinner(10);
    }

    //Print the end result of the game
    System.out.println();
    System.out.println("The winner is: " + winner);
  }
}

