package model;


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

public abstract class Room {

   private String name; // room name
   private Room adjRoom[]; // adj rooms
   public int index;

   // constructor
   public Room(){

   }

   // set 3 adjacent rooms
   public void setNumRooms(String newName, int numRooms, Room room1, Room room2, Room room3) {
      this.name = newName;
      adjRoom = new Room[numRooms];
      adjRoom[0] = room1;
      adjRoom[1] = room2;
      adjRoom[2] = room3;
   }
   // set 4 adjacent rooms
   public void setNumRooms(String newName, int numRooms, Room room1, Room room2, Room room3, Room room4) {
      this.name = newName;
      adjRoom = new Room[numRooms];
      adjRoom[0] = room1;
      adjRoom[1] = room2;
      adjRoom[2] = room3;
      adjRoom[3] = room4;
   }
   // return adjacent rooms
   public Room[] getAdjRooms(){
      return adjRoom;
   }
   // return name
   public String getName(){
      return name;
   }
   
   public int getIndex() {
      return index;
   }

   // abstract functions
   public abstract void flipSceneCard();
   public abstract void setScene(Scene scene);
   public abstract Scene getScene();
   public abstract int getShotCounter();
   public abstract boolean getFlippedScene();
   public abstract Role[] getExtraRoles();
   public abstract void decrementShotCounter();
   public abstract void resetSceneFlipped();
   public abstract void resetShotCounter();

}
