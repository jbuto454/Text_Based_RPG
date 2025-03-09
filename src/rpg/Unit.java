package rpg;
import java.util.Random;

/**
 * Represents a unit that can belong to either the computer or human player.
 */
public class Unit {

  /**
   * The unit's name.
   */
  String name;

  /**
   * The unit's level, which is randomly generated based on a given level range.
   */
  private int level = 0;

  /**
   * The unit's job, such as mage, knight or archer.
   */
  private String job;

  /**
   * The unit's current health points or HP.
   */
  private int hp = 0;

  /**
   * The unit's attack stat, which is determined by level and impacts damage dealt to a target.
   */
  private int attack = 0;

  /**
   * The unit's defense stat, which is determined by level and impacts damage received by this unit.
   */
  private int defense = 0;

  /**
   * The unit's temporary defense stat, which is granted by the "block" move.
   */
  private int temporaryDefense = 0;

  /**
   * The unit's evasion stat, which is determined by level and impacts whether this unit dodges the incoming attack.
   */
  private int evasion = 0;

  /**
   * A random number generator to be used in this class.
   */
  Random random = new Random();

  /**
   * Constructs a unit by assigning the given name and job as well as calculating other stats.
   * The level must be randomly generated given the level range that is passed.
   * @param name String representing the name of this unit
   * @param levelRange String representing the level range of this unit, such as low, medium or high
   * @param job String representing the job of this unit
   */
  public Unit(String name, String levelRange, String job){

    //assign name and job to this unit based on given parameters
    this.name = name;
    this.job = job;


    /*
     * randomly assign this unit's level based on the given levelRange
     *   - "low" means the level can be set to an int from 1 to 3
     *   - "medium" means the level can bes et to an int from 4 to 6
     *   - "high" means the level can be set to an int from 7 to 10
     */

    if (levelRange.equals("low")) {
      //calls the random nextInt method to get a random number between 1 and 3 (inclusive)
      this.level = this.random.nextInt(1,4);
    } else if (levelRange.equals("medium")) {
      //calls the random nextInt method to get a random number between 4 and 6 (inclusive)
      this.level = this.random.nextInt(4,7);
    } else if (levelRange.equals("high")) {
      //calls the random nextInt method to get a random number between 7 and 10 (inclusive)
      this.level = this.random.nextInt(7,11);
    }

    /*
     * calculate and assign this unit's stats of hp, attack, defense and evasion.
     *  calculate and set the multiplier variable to a tenth of the level. Ex: (this.level / 10)
     *  multiply the calculated multiplier with the stat's specific max value, which are given below.
     *  - max HP value: 100
     *  - max attack value: 20
     *  - max defense: 20
     *  - max evasion: 5
     * Hint: avoid integer division by using decimals and round the final value to an integer
     * set the rounded value to this unit's stat (Ex: this.hp)
     */

    //sets the multiplierVariable to be the level of the given unit dvided by 10.
    double multiplierVariable = this.level/10.0;

    //calls the setHp method to set the Hp of the unit to 100 times the multiplierVariable
    setHp((int) (Math.round(100.0*multiplierVariable)));
    //calls the setAttack method to set the attack of the unit to 20 times the multiplierVariable
    setAttack((int) Math.round((20.0*multiplierVariable)));
    //calls the setDefense method to set the defense of the unit to 20 times the multiplierVariable
    setDefense((int) Math.round((20.0*multiplierVariable)));
    //calls the setEvasion method to set the evasion of the unit to 5 times the multiplierVariable
    setEvasion((int) Math.round((5.0*multiplierVariable)));
    

  }

  // Getters and Setters

  /**
   * Returns this unit's level.
   * Note: This method does not take any parameters.
   * @return level
   */
  public int getLevel() {

    return this.level;

  }

  /**
   * Returns this unit's job.
   * Note: This method does not take any parameters.
   * @return job
   */
  public String getJob() {

    return this.job;

  }

  /**
   * Returns this unit's hp.
   * Note: This method does not take any parameters.
   * @return hp
   */
  public int getHp() {

    return this.hp;

  }

  /**
   * Returns this unit's name.
   * Note: This method does not take any parameters.
   * @return name
   */
  public String getName() {

    return this.name;

  }

  /**
   * Sets this unit's hp stat to the given hp.
   * Note: This method does not return anything.
   * @param hp int representing given health points value
   */
  public void setHp(int hp) {

    this.hp = hp;

  }

    /**
   * Sets this unit's attack stat to the given attack.
   * Note: This method does not return anything.
   * @param attack int representing given attack points value
   */
  public void setAttack(int attack) {

    this.attack = attack;

  }

      /**
   * Sets this unit's defense stat to the given defense.
   * Note: This method does not return anything.
   * @param attack int representing given defense points value
   */
  public void setDefense(int defense) {

    this.defense = defense;

  }

  /**
   * Sets this unit's temporary defense stat to the given temporary defense.
   * Note: This method does not return anything.
   * @param temporaryDefense int representing given temporary defense value
   */
  public void setTemporaryDefense(int temporaryDefense) {

    this.temporaryDefense = temporaryDefense;

  }

  /**
   * DO NOT MODIFY, REQUIRED FOR TESTING
   *
   * Returns this unit's temporary defense stat.
   * Note: This method does not take any parameters.
   * @return temporaryDefense
   */
  public int getTemporaryDefense() {
    return this.temporaryDefense;
  }

  /**
   * DO NOT MODIFY, REQUIRED FOR TESTING
   *
   * Sets this unit's evasion stat to the given evasion value.
   * Note: This method does not return anything.
   * @param evasion int representing given value for evasion
   */
  public void setEvasion(int evasion) {
    this.evasion = evasion;
  }

   /**
   * DO NOT MODIFY, REQUIRED FOR TESTING
   *
   * Returns this unit's evasion stat.
   * Note: This method does not take any parameters.
   * @return evasion
   */
  public int getEvasion() {
    return this.evasion;
  }

  /**
   * Prints the unit's name, level, job, and remaining HP.
   * If the unit has no remaining hp, prints that this unit is knocked out.
   * Note: This method does not take any parameters and does not return anything.
   */
  public void printCurrentStatus(){

    //if the unit's hp is 0 then a message that the unit is knocked out and cannot move is printed
    if (getHp() < 1) {
      System.out.println("The unit " + getName() + " is knocked out and cannot move");
      //otherwise, the current stats of the given unit are printed
    } else {
        System.out.println(getName() + " is a " + "level " + getLevel() + " " + getJob() + " with " + getHp() + " Hp remaining.");
    }

  }

  /**
   * Calculates damage based on this unit's attack stat, maximum attack, and attacker strength relative to target.
   * @param attackerStrength String representing the attacker's strength relative to the target
   * @return int representing the total damage this unit will deal when attacking
   */
  public int attack(String attackerStrength){

    //the attack variable is initialized
    double attackVariable = 1.0;

    /*
     * check the value of attackerStrength to determine the multiplier which will be applied to the damage
     *  later in this method
     *   - "same" results in an unchanged multiplier of 1.0
     *   - "strong" results in a multiplier that will increase the damage by 20%
     *   - "weak" results in a multiplier that halves damage or decreases by 50%
     */

    //if the given attackerStrength is equal to strong, then the attackVariable is set to 1.2
    if (attackerStrength.equals("strong")) {
      attackVariable = 1.2;
      //if the given attackerStrength is equal to weak, then the attackVariable is set to 0.5
    } else if (attackerStrength.equals("weak")) {
      attackVariable = 0.5;
    }

    //assign attackMax variable to the maximum attack value of 50.0

    double attackMax = 50.0;

    //get the raw damage by dividing this unit's attack stat by 30.0 then multiplying this by the max attack value

    double rawDamage = (this.attack/30.0)*attackMax;

    //multiply the raw damage by the multiplier variable you assigned above
    // Hint: make sure to round and convert to an integer before returning the result

    return (int) Math.round(rawDamage*attackVariable);

  }

  /**
   * Provides temporary defensive buff to reduce damage taken during the current turn.
   * Note: This method does not take any parameters and does not return anything.
   */
  public void block(){
    //adds 2 to the current temporaryDefense value for this given unit
    this.temporaryDefense += 2;

  }

  /**
   * Uses this unit's evasion, temporaryDefense, and defense stats to either dodge the attack or adjust damage.
   * If the attack is not dodged, applies the adjusted damage to this unit's remaining HP.
   * Prints a message containing the damage received and the remaining HP.
   * Note: This method does not return anything.
   * @param damage int representing the incoming damage from an opposing unit
   */
  public void receiveDamage(int damage){

    /*
     * perform evasion check to determine whether this unit will take damage:
     *   - generate a random number between 0 and 20
     *   - if the number is less than or equal to this unit's evasion stat, the unit will dodge the attack
     *   - if the attack is dodged, print a message and return out of this method
     */

    if (getEvasion() > 0) {
      //if the evasion value of the unit is greater than 0, then a random number between 0 and 20 (inclusive) is selected
      int randomInt2 = this.random.nextInt(21);
      //if the selected random number is less than or equal to the value of the evasion stat, then the unit dodges the attack and the method returns to exit
      if (randomInt2 <= getEvasion()) {
        System.out.println("They Dodged!");
        return;
      } 
    }
      
    //calculate the defense adjustment by adding this unit's defensive stats (temporary and normal defense)
    // divide this sum by 10.0

    float defenseAdjustment = (float) ((this.temporaryDefense + this.defense) / 10.0);

    //calculate the actual damage received by dividing the given damage value by the calculated defense adjustment

    int damageReceived = (int) Math.round(damage/defenseAdjustment);
    int remainingHp = getHp() - damageReceived;
    
    if (remainingHp <= 0) {
      //set this unit's HP to 0 if the damage received exceeds the remaining HP
      setHp(0);
    } else {
      //update this unit's HP stat by subtracting the damage
      setHp(remainingHp);
    }
    
    //print how much damage was received and the remaning HP
    System.out.println("Damage received by " + getName() + " : " + damageReceived + ", " + "remaining hp: " + getHp());

  }
}