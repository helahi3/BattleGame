/*
 Hamza Elahi
 260616538
 COMP 202
 Assignment 5
 */

import java.io.*;
import java.util.ArrayList;

public class FileIO
{
     public static Character readCharacter(String filename){
          
          try{
               FileReader fr = new FileReader(filename);
               BufferedReader br = new BufferedReader(fr);
               
               String name = br.readLine(); //First line of the input file is the string name
               String attVal = br.readLine(); //Next line is the string attack value
               double attackVal = Double.parseDouble(attVal); //Converts the attack value to a double
               String healthMax = br.readLine(); //Next line is the string max health
               double maxHealth = Double.parseDouble(healthMax); //Converts the max health to a double
               String winsNum = br.readLine(); //Next line is the string number of wins
               int numWins = Integer.parseInt(winsNum); //Converts the string to an int
               
               
               Character myCharacter = new Character(name, attackVal, maxHealth, numWins); //Creates a new character
               
               br.close();
               fr.close();
               return myCharacter; //Returns that character
               
               //Catch the following exceptions
          }          catch(FileNotFoundException e){ 
               throw new IllegalArgumentException("Cannot find the file: "  + filename);
          }          catch(IOException e){
               throw new IllegalArgumentException("IO Exception with the the file: "  + filename);
          }  
     }
     
     public static ArrayList<Spell> readSpells(String filename){
          
          try{
               FileReader fr = new FileReader(filename);
               BufferedReader br = new BufferedReader(fr);
               
               ArrayList<Spell> spellList = new ArrayList<Spell>();
               
               String line = br.readLine(); //Get the next line
               while(line != null){ 
                    String[] parts = line.split(" "); //Split it on the white space
                    
                    //Assign each part to the name, min Damage, max Damage, and odds
                    //Have to parse the doubles for the last 3 values
                    String name = parts[0]; 
                    String minDam = parts[1];
                    double minDamage = Double.parseDouble(minDam);
                    String maxDam = parts[2];
                    double maxDamage = Double.parseDouble(maxDam);
                    String chance = parts[3];
                    double odds = Double.parseDouble(chance);
                    
                    //Create a new spell with the above attributes
                    Spell spell = new Spell(name, minDamage, maxDamage, odds);
                    spellList.add(spell); //Add it to our ArrayList
                    
                    line = br.readLine(); //Get the next line
               }
               br.close();
               fr.close();
               return spellList; //Return the array list
               
               //Catch the following exceptions
          }          catch(FileNotFoundException e){ 
               throw new IllegalArgumentException("Cannot find the file: "  + filename);
          }          catch(IOException e){
               throw new IllegalArgumentException("IO Exception with the the file: "  + filename);
          }
          
     }
     
     public static void writeCharacter(Character character, String filename){
          
          try{
               FileWriter fr = new FileWriter(filename);
               BufferedWriter br = new BufferedWriter(fr);
               
               //Get the name, attack value, max health and number of wins of the character
               //Convert the doubles and integers into Strings
               //Put each value on a different line
               String name = character.getName();
               double attackVal = character.getAttack();
               String attVal = "\n" + attackVal;
               double maxHealth = character.getMaxHealth();
               String maxHealt = "\n" + maxHealth;
               int numWins = character.getWins();
               String numWin = "\n" + numWins;
               
               //Write each of the relevant lines to the file
               br.write(name);
               br.write(attVal);
               br.write(maxHealt);
               br.write(numWin);
               
               br.close();
               fr.close();
               
               //Catch the following exceptions
          }          catch(FileNotFoundException e){ 
               throw new IllegalArgumentException("Cannot find the file: "  + filename);
          }          catch(IOException e){
               throw new IllegalArgumentException("IO Exception with the the file: "  + filename);
          } 
     }
}