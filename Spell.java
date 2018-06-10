/*
 Hamza Elahi
 260616538
 COMP 202
 Assignment 5
 */

import java.util.Random;

public class Spell
{
     //Private attributes of the Spell
     private String name;
     private double minDamage;
     private double maxDamage;
     private double odds;
     
     //Constructor method for the spell that takes a string and 3 doubles
     public Spell(String name, double minDamage, double maxDamage, double odds){
          this.name = name;
          this.minDamage = minDamage;
          this.maxDamage = maxDamage;
          this.odds = odds;
          
          //Throws an exception for the following cases
          if(minDamage < 0){
               throw new IllegalArgumentException("The minimum damage cannot be less than 0");
          }
          if(minDamage > maxDamage){
               throw new IllegalArgumentException("The minimum damage cannot be greater than the maximum damage");
          }
          if(odds < 0){
               throw new IllegalArgumentException("The odds of the spell cannot be less than 0");
          }
          if(minDamage < 0){
               throw new IllegalArgumentException("The odds of the spell cannot be greater than 1");
          }
     }
     
     public String getName(){ //Getter method for the name
          return this.name;    
     }
     
     public double getDamage(){ //Method to calculate the damage caused by the spell
          Random r = new Random();   
          double randNum1 = r.nextDouble(); //Create a random number and get a double
          
          if(randNum1 > this.odds){ 
               return 0;    
          }
          
          else{ //Return a random double between the min and max damage 
               double damage = r.nextDouble() * (this.maxDamage - this.minDamage) + this.minDamage;
               return damage;
          }
     }
     
     public String toString(){ //toString method that takes no input and returns the following string
          String s = "Spell Name: " + this.name + " Damage: " + this.minDamage + "-" + this.maxDamage + " Chance: " + String.format("%1$.1f", this.odds*100) +"%";
          return s;
     }
}