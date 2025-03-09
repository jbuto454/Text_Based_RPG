package rpg;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the human player and holds their units in this role-playing game.
 */
public class HumanPlayer {

  /**
   * Human Unit 1: Falia
   */
  Unit falia;

  /**
   * Human Unit 2: Erom
   */
  Unit erom;

  /**
   * Human Unit 3: Ama
   */
  Unit ama;

  /**
   * A random number generator to be used for returning random levels and jobs.
   */
  Random random = new Random();

  /**
   * Constructs a human player.
   */
  public HumanPlayer(){
    this.falia = new Unit("Falia", generateLevel(),generateJob());
    this.erom = new Unit("Erom", generateLevel(),generateJob());
    this.ama = new Unit("Ama", generateLevel(),generateJob());
  }

  // Getters and Setters

  /**
   * Returns the falia Unit.
   * Note: This method does not take any parameters.
   * @return falia
   */
  public Unit getFalia(){
    return falia;
  }

  /**
   * Returns the erom Unit.
   * Note: This method does not take any parameters.
   * @return erom
   */
  public Unit getErom() {
    return erom;
  }

  /**
   * Returns the ama Unit.
   * Note: This method does not take any parameters.
   * @return ama
   */
  public Unit getAma() {
    return ama;
  }

  /**
   * Randomly chooses a string representing the level of a unit by generating a random integer.
   * There are three possible levels: low, medium, high.
   * Note: This method does not take any parameters.
   * @return String of the generated level of a human's unit
   */
  private String generateLevel(){
    //initializes the generatedLevel string
    String generatedLevel;

    // generate a random integer from 0 to 2
    int randomInt = this.random.nextInt(3);

    // assign generatedLevel a level based on randomInt's value
    if(randomInt == 0){
      generatedLevel = "low";
    }
    else if(randomInt == 1){
      generatedLevel = "medium";
    }
    else{
      generatedLevel = "high";
    }
    //returns the generated level string
    return generatedLevel;
  }

  /**
   * Randomly chooses a string representing the job of a unit by generating a random integer.
   * There are three possible jobs: mage, knight, archer.
   * Note: This method does not take any parameters.
   * @return String of the generated job a human's unit will take on
   */
  private String generateJob(){
    String generatedJob;

    // generate a random integer from 0 to 2
    int randomInt = this.random.nextInt(3);

    // assign generatedJob a level based on randomInt's value
    if(randomInt == 0){
      generatedJob = "mage";
    }
    else if(randomInt == 1){
      generatedJob = "knight";
    }
    else{
      generatedJob = "archer";
    }

    return generatedJob;
  }

  /**
   * Checks if the user entered a valid move string, meaning it begins with one of the following letters: 'a' 'A' 'b' 'B'
   * Prints a friendly message to enter a valid input and returns null if the string is invalid.
   * @param move String representing the move to be performed by a human unit, for example, "attack" or "block"
   * @return String of "attack" or "block" or null
   */
  public String validateMove(String move){

    //converts the string move that was taken as a parameter into all lowercase
    String moveLower = move.toLowerCase(); 

    //gets the first character of the lowercase string
    char moveFirstChar = moveLower.charAt(0);

    //if the first character is 'a', then we assume the player wants to attack and the string "attack" is returned
    if (moveFirstChar == 'a') {
      return "attack";
      //if the first character is 'b', then we assume the player wants to block and the string "block" is returned
    } else if (moveFirstChar == 'b') {
      return "block";
      //otherwise, a message is printed telling the player that their move is not valid
    } else {
      System.out.println("Please enter a valid move.");
      //null is returned
      return null;
    }

  }

  /**
   * Checks if the computer target selected by the human is alive and returns said target if it exists.
   * If the target with the given name is not alive or does not exist, print a message saying so and return null.
   * @param targetName String that should be the name of a computer unit
   * @param computer ComputerPlayer that the human is currently playing against
   * @return Unit representing the target belonging to the computer or null
   */
  public Unit selectTarget(String targetName, ComputerPlayer computer){

    //checks the value of the given targetName and if the target is alive or not
    //if the target for the given target name is alive, then the unit object is returned
    if (targetName.equals("Criati") && computer.getCriati().getHp() > 0) {
      return computer.getCriati();
    } else if (targetName.equals("Ledde") && computer.getLedde().getHp() > 0) {
      return computer.getLedde();
    } else if (targetName.equals("Tyllion") && computer.getTyllion().getHp() > 0) {
      return computer.getTyllion();
    } else {
      //otherwise a message is printed telling the player that the target doesn't exist and null is returned
      System.out.println("This target either does not exist or is not alive");
      return null;
    }

  }

  /**
   * Determines the strength of the attacker by comparing the attacker's job and the job of the target.
   * Mages are strong against knights, but weak against archers. Knights are strong against archers, but weak against mages.
   * There are three possible attacker strengths: same, strong, weak.
   * @param attacker Unit belonging to human that is attacking the target
   * @param target Unit belonging to computer that is being attacked by the human
   * @return String representing the strength of the attacker relative to the target
   */
  public String determineAttackerStrength(Unit attacker, Unit target){
    String determinedStrength;

    // assign determinedStrength by comparing job of attacker with job of the target
    if(attacker.getJob().equalsIgnoreCase(target.getJob())){
      determinedStrength = "same";
    }
    else if((attacker.getJob().equalsIgnoreCase("knight") && target.getJob().equalsIgnoreCase("archer")) ||
            (attacker.getJob().equalsIgnoreCase("archer") && target.getJob().equalsIgnoreCase("mage")) ||
            (attacker.getJob().equalsIgnoreCase("mage") && target.getJob().equalsIgnoreCase("knight"))){
      determinedStrength = "strong";
    }
    else{
      determinedStrength = "weak";
    }

    return determinedStrength;
  }

  /**
   * For the given unit, allow human player to pick between attacking a target of their choosing or blocking.
   * This human unit will carry out the selected move during its turn.
   * Note: This method does not return anything.
   * @param unit Unit that is currently taking a turn
   * @param computer ComputerPlayer that human is playing against
   */
  public void moveUnit(Unit unit, ComputerPlayer computer){

    String moveChoice = " ";
    //checks if the given unit is alive or not
    if (unit.getHp() <= 0) {
      //if it is not alive, then a statement telling the player that is printed
      System.out.println("The unit " + unit.getName() + " is not alive");
    } else {
        //a new instance of the scanner class is created to read user input
        Scanner scannerNew = new Scanner(System.in);
        //a message is printed to prompt the user to select a move
        System.out.println("Please select a move (block or attack)");
        //the scanner is used to get the next string the user entered (block or attack)
        moveChoice = scannerNew.nextLine().trim();
        //the validateMove method is used to check if the move selected by the player is valid
        //if it is not valid, then the user will be asked to enter a move choice again
        //until a valid choice is entered
        while (validateMove(moveChoice) == null) {
        
          System.out.println("Please select a move (block or attack)");
    
          //get the new token, which is the move choice
          moveChoice = scannerNew.nextLine().trim();
  
        }
  
      //if the move choice slected by the player is attack, then the user is asked to select a target
      if (validateMove(moveChoice).equals("attack")) {
  
        //set the initial selectedTargetChoice variable for the while loop
        Unit selectedTargetChoice = null;
  
        //the user is asked to enter a target choice until a valid choice is entered
        while (selectedTargetChoice == null) {
          //select target
          System.out.println("Please select a target (Criati, Ledde, or Tyllion)");
    
          //get the new token, which is the target choice
          String targetChoice = scannerNew.nextLine().trim();
  
          selectedTargetChoice = selectTarget(targetChoice, computer);
  
        }
  
        //once the user has entered a valid target choice, then the determineAttackerStrength method is called to determine how much strength the unit has relative to the target
        String relativeStrength = determineAttackerStrength(unit,selectedTargetChoice);
        //the damage dealt to the given target is determined by passing the calcaulted relativeStrength to the attack method
        int damageDealt = unit.attack(relativeStrength);
        //the damage is given to the target choice with the receiveDamage method
        selectedTargetChoice.receiveDamage(damageDealt);
        //if the player selected block, then the block method is called for the given unit
      } else if (validateMove(moveChoice).equals("block")) {
          unit.block();
      }
    

      }

    
  }

  /**
   * Resets temporary defensive buff of each human unit by setting temporaryDefense back to 0.
   * Note: This method does not take any parameters and does not return anything.
   */
  public void resetTemporaryDefense(){
    this.erom.setTemporaryDefense(0);
    this.falia.setTemporaryDefense(0);
    this.ama.setTemporaryDefense(0);
  }

  /**
   * Determines if human player has lost or is knocked out.
   * This is done by checking if all of its three units are knocked out.
   * Note: This method does not take any parameters.
   * @return boolean true if human has no units left or false
   */
  public boolean isKnockedOut(){

    // return true if all human units have 0 HP or less
    return this.falia.getHp() <= 0 && this.erom.getHp() <= 0 && this.ama.getHp() <= 0;
  }

  /**Returns the sum of all hp points for the human players units
   * @return sum of hp points
   */
  public int sumHp(){
    return this.falia.getHp() + this.erom.getHp() + this.ama.getHp();
  }



}