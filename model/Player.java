package model;


import java.util.Random;
import java.util.*;
import java.io.*;


/*
*  DeadWood Assignment
*  Created by: Jonah Wallace and Trevor Glass
*  Date Created: Friday May 5, 2017
*
*  CS345 Spring 2017
*  Professor: Moushumi Sharmin
*
*  Class name is Deadwood, previously Board Class
*/

public class Player {

   private int credits; // player credits
   private int dollars; // player dollars
   private int rank; // player rank
   private int rehearsalCount; // player rehearsal count
   private int playerIndex;
   private Room currRoom; // player room
   private Scene currScene; // player scene
   private Role currRole; // player role

   // constructor
   public Player(int credits, int rank, int playerIndex) {
      this.credits = credits;
      this.dollars = 0;
      this.rank = rank;
      this.playerIndex = playerIndex;
   }

   // set the scene and role
   public void takeRole(Scene scene, Role role) {
      this.currScene = scene;
      this.currRole = role;
   }

   // move player
   public void move(Room nextRoom) {
      Room temp = this.currRoom;
      this.currRoom = nextRoom;
      if (temp instanceof Trailer) {
         view.Trailer.playerOff(this);
      } else if (temp instanceof CastingOffice) {
         view.CastingOffice.playerOff(this);
      }  else if (temp instanceof Location) {
         view.Location loc = view.Board.locations[temp.getIndex()];
         loc.playerOff(this);
      }
      
      if (nextRoom instanceof Trailer) {
         view.Trailer.playerOn(this);
      } else if (nextRoom instanceof CastingOffice) {
         view.CastingOffice.playerOn(this);
         controller.Board.upgradeChoices();
      } else if (nextRoom instanceof Location) {
         view.Location loc = view.Board.locations[this.currRoom.getIndex()];
         loc.playerOn(this);
       }
   }
   // increment rehearsal counter
   public void rehearse() {
      this.rehearsalCount++;
      view.Board.changeDie(this);
   }
   // change rank
   public void rankUp(int rank) {
      this.rank = rank;
      view.Board.changeDie(this); 
      view.CastingOffice.playerOn(this);
   }

   // roll a six-sided die
   public int rollDie() {
      Random roll = new Random();
      int result = roll.nextInt(6) + 1;
      System.out.println("Rolled a " + result + ".");
      return result;
   }

   // update dollar amount
   public void changeDollars(int dollars) {
      this.dollars += dollars;
      view.Board.changeDie(this);
   }
   // update credits
   public void changeCredits(int credits) {
      this.credits += credits;
      view.Board.changeDie(this);
   }

   // increment rehearsal count
   public void incrementRehearsalCount(){
      this.rehearsalCount++;
      view.Board.changeDie(this);
   }

   // reset rehearsal count
   public void resetRehearsalCount() {
      this.rehearsalCount = 0;
      view.Board.changeDie(this);
   }

   // return player dollars
   public int getDollars() {
      return dollars;
   }

   // return player credits
   public int getCredits() {
      return credits;
   }

   // return player rank
   public int getRank() {
      return rank;
   }
   // return player role
   public Role getRole(){
      return currRole;
   }
   // return player room
   public Room getRoom(){
      return currRoom;
   }
   // return player scene
   public Scene getScene(){
      return currScene;
   }
   // return player rehearsal count
   public int getRehearsalCount(){
      return rehearsalCount;
   }
   
   public int getPlayerIndex(){
      return playerIndex;
   }
}
