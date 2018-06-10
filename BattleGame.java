/*
 Hamza Elahi
 260616538
 COMP 202
 Assignment 5
 */

import java.util.Scanner;
import java.util.ArrayList;

public class BattleGame
{
     //Main method to test the game
     public static void main(String[] args){ 
          playGame();  
     }
     
     public static void playGame(){ 
          //Create the player and the monster by calling the FileIO class
          Character player = FileIO.readCharacter("player.txt"); 
          Character monster = FileIO.readCharacter("monster.txt");
          
          //Print their information
          player.printInfo();
          monster.printInfo();
          
          Scanner sc = new Scanner(System.in);   //Create a new scanner object
          
          ArrayList<Spell> spells = new ArrayList<Spell>(); //Initialize an ArrayList of Spells
          spells =FileIO.readSpells("spells.txt");          //Call on the readSpells method from FileIO
          player.setSpells(spells);                         //Set that as the list of spells for the player
          
          System.out.println("\nSpells:");
          
          //Loop through the ArrayList and print the information of the Spells
          for(int i=0;i<spells.size();i++){ 
               System.out.println(spells.get(i));
          }
          
          while(monster.getHealth() > 0 & player.getHealth() > 0){ //Loop while both have health above 0
               System.out.println("\nEnter a command");
               String input = sc.nextLine();
               String inputLC = input.toLowerCase(); //Take input and convert it to lowercase
               
               //if input is ATTACK
               if(inputLC.equals("attack")){ 
                    if(player.getHealth() > 0){    //Player only attacks if his health is above 0
                         doAttack(player, monster);
                    }
                    if(monster.getHealth() > 0){   //Monster only attacks if its health is above 0
                         doAttack(monster, player);
                    }
               }
               //if input is QUIT
               else if(inputLC.equals("quit")){ //Print message if user quits
                    System.out.println("Goodbye. Thank you for playing");
                    return;
               }
               //if input is anything else, assumed to be spell
               else{
                    if(player.getHealth() > 0){ //Player only casts spell if his health is above 0
                         
                         double damage = player.castSpell(inputLC);  //Call the castSpell method to calculate damage of the spell
                         monster.takeDamage(damage); //The monster takes that damage
                         
                         
                         if(damage > 0){ //Print statements if non-zero damage
                              System.out.println(player.getName() + " casts " + inputLC + " for " + String.format("%1$.2f", damage) + " damage.");
                              System.out.println(monster.getName() + " takes " + String.format("%1$.2f", damage) + " damage!");
                         }
                         else if (damage == 0){ //Print statement if zero damage
                              System.out.println(player.getName() + " tried to cast " + inputLC + ", but failed!");
                         }
                         
                         System.out.println("Name: " + monster.getName() + " Health: " + String.format("%1$.2f", Math.max(0,monster.getHealth())) +"\n"); 
                    }
                    
                    if(monster.getHealth() > 0){ //Monster only attacks if its health is above 0
                         doAttack(monster, player);
                    }
                    
               }
               /* This else block was cut out for Question 2d. It contains a print statement in case of wrong input
                else{ 
                System.out.println("Your input was not recognized. Please enter <Attack> or <Quit>");    
                } */
          }
          
          if(monster.getHealth() < 0){ //Print statements and increase player's wins if monster's health drops below 0
               System.out.println("Congratulations on winning");
               player.increaseWins();
               System.out.println(player.getName() + " has won " + player.getWins() + " times.");
          }
          if(player.getHealth() < 0){ //Print statements and increase monster's wins if player's health drops below 0
               System.out.println("Oh no, you have been eaten by the monster");
               monster.increaseWins();
               System.out.println(monster.getName() + " has won " + monster.getWins() + " times.");
          }
          
          //Call the writeCharacter method in the FileIO class
          //Write the player and monster back to their .txt files
          FileIO.writeCharacter(player,"player.txt");
          FileIO.writeCharacter(monster,"monster.txt");
     }
     
     //void method that takes 2 characters as input
     public static void doAttack(Character first, Character second){
          
          //Calculate the damage done by calling the calcAttack method in Character class
          double firstDamage = first.calcAttack();
          String firstDamageStr = String.format("%1$.2f", firstDamage); //Converted to string for formatting purposes
          
          System.out.println(first.getName() + " attacks for " + firstDamageStr + " damage.");
          System.out.println(second.getName() + " takes " + firstDamageStr + " damage.");
          
          second.takeDamage(firstDamage); //The second character takes this much damaage.
          
          if(second.getHealth() <= 0){ //Print statement if the second character is knocked out
               System.out.println(second.getName() + " has been knocked out");    
          }  
          //Print the health of the second character
          //Health has a lower bound of 0, so never prints negative health
          System.out.println("Name: " + second.getName() + " Health: " + String.format("%1$.2f", Math.max(0,second.getHealth())) +"\n");
     }
}