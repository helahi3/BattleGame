/*
 Hamza Elahi
 260616538
 COMP 202
 Assignment 5
 */

import java.util.Random;
import java.util.ArrayList;

public class Character
{
     //Private attributes of the character
     private String name;
     private double attackVal;
     private double maxHealth;
     private double currHealth;
     private int numWins;
     private static ArrayList<Spell> spells; //Added for Q2a
     
     //Constructor method that takes a string, 2 doubles and an int
     public Character(String name, double attackVal, double maxHealth, int numWins){
          //Assigns each of the input parameters to the attributes of that instance
          this.name = name;
          this.attackVal = attackVal;
          this.maxHealth = maxHealth;
          this.numWins = numWins;
          this.currHealth = maxHealth; //The initial 'current health' is equal to the max health
     }
     
     public String toString(){    //Method returns a string that is the name and current Health of the character
          String s = "Name: " + name + " Current Health: " + currHealth;
          return s;
     }
     
     public double calcAttack(){  //Method calculates the attack value using Random
          Random r = new Random(); 
          double attVal = this.attackVal * (r.nextDouble()*0.4 + 0.3); //Multiplies the attack value by random between 0.3 and 0.7
          
          return attVal;
     }
     
     public void takeDamage(double damage){ //Method takes as input a double, subtracts it from the current health
          this.currHealth = this.currHealth - damage;
     }
     
     public void increaseWins(){ //Method increases the number of wins
          this.numWins++;
     }
     
     public double getHealth(){ //Method returns the current health
          return this.currHealth;
     }
     
     public void printInfo(){ //Void method that prints out a string
          String s = "Name: " + this.name + " Health: " + this.currHealth + "\nAttack Value: " + this.attackVal + "\nNumber of Wins: " + this.numWins;
          System.out.println(s);
     }
     
     public String getName(){ //Method that returns the name
          return this.name;
     }
     
     public void setSpells(ArrayList<Spell> spellList){ //Method adds spells to the ArrayList of spells 
          this.spells = spellList;
     }
     
     public int getWins(){ //Getter method for number of wins
          return this.numWins;
     }
     
     public double getAttack(){ //Getter method for attack value
          return this.attackVal;
     }
     
     public double getMaxHealth(){ //Getter method for max health
          return this.maxHealth;
     }
     
     
     public double castSpell(String spellName){ //Method that accepts the spell name and returns damage of the spell
          
          String spellNameLC = spellName.toLowerCase();  //Converts the spell name input to lower case
          Spell[] spellArr = new Spell[this.spells.size()];  //Creates an array of Spells of the size of the ArrayList of spells
          double damage =0;
          
          for(int i = 0; i < spellArr.length; i++){ //Loops through each spell
               spellArr[i] = this.spells.get(i); //Each index of the spells Array List is assigned to this new array
               String thisSpellName = spellArr[i].getName(); //Get the name of each spell
               
               if(thisSpellName.equals(spellNameLC)){ //If the input spell name is equal to the spell name from our array
                    
                    damage = spellArr[i].getDamage(); //Get the damage of that spell and return is as a double
                    return damage;
               }
          }
          
          System.out.println(this.name + " does not know the spell " + spellName); //Otherwise print a message and return 0
          return 0;
     }
     
}