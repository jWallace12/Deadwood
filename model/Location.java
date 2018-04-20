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

public class Location extends Room{

   private Scene scene; // location's scene
   private int shotCounter; // location's overall shot counter
   private int currShotCounter; // location's functioning shot counter
   private String Description; // location's description
   private boolean sceneFlipped; // whether scene is flipped or not
   private Role extraRoles[]; // off card roles

   // constructor for 2 roles
   public Location(int shotCounter, int rank1, String name1, String description1, int rank2, String name2, String description2,  String type, int index){
      this.shotCounter = shotCounter;
      this.currShotCounter = shotCounter;
      this.sceneFlipped = false;
      this.extraRoles = new Role[2];
      this.extraRoles[0] = new Role(rank1, name1, description1, type, -1);
      this.extraRoles[1] = new Role(rank2, name2, description2, type, -1);
      this.index = index;
   }

   // constructor for 3 roles
   public Location(int shotCounter, int rank1, String name1, String description1, int rank2, String name2, String description2,
      int rank3, String name3, String description3, String type, int index) {
      this.shotCounter = shotCounter;
      this.currShotCounter = shotCounter;
      this.sceneFlipped = false;
      this.extraRoles = new Role[3];
      this.extraRoles[0] = new Role(rank1, name1, description1, type, -1);
      this.extraRoles[1] = new Role(rank2, name2, description2, type, -1);
      this.extraRoles[2] = new Role(rank3, name3, description3, type, -1);
      this.index = index;
   }

   // constructor for 4 roles
   public Location(int shotCounter, int rank1, String name1, String description1, int rank2, String name2, String description2,
      int rank3, String name3, String description3, int rank4, String name4, String description4, String type, int index) {
      this.shotCounter = shotCounter;
      this.currShotCounter = shotCounter;
      this.sceneFlipped = false;
      this.extraRoles = new Role[4];
      this.extraRoles[0] = new Role(rank1, name1, description1, type, -1);
      this.extraRoles[1] = new Role(rank2, name2, description2, type, -1);
      this.extraRoles[2] = new Role(rank3, name3, description3, type, -1);
      this.extraRoles[3] = new Role(rank4, name4, description4, type, -1);
      this.index = index;
   }

   // return scene
   public void setScene(Scene scene) {
      this.scene = scene;
   }

   // decrement function shot counter
   public void decrementShotCounter(){
      this.currShotCounter--;
      view.Board.locations[this.getIndex()].visibleShot();
   }

   // read off scene's stats
   public void flipSceneCard(){
      this.sceneFlipped = true;
      int index = this.getIndex();
      int img = this.scene.getImg();
      view.Location loc = view.Board.locations[index];
      loc.getScene().changed(this, img);
      System.out.println("Printing current scene's stats...");
      System.out.println("Name = " + this.scene.getName());
      System.out.println("Description = " + this.scene.getDescription());
      System.out.println("Shot Counter = " + this.shotCounter);
      System.out.println("Budget = " + this.scene.getBudget());
      System.out.println("");
      System.out.println("Available Extra Roles in this scene include:");
      for(int i = 0; i < extraRoles.length; i++){
         if (extraRoles[i].getPlayerOn() == null) {
            System.out.println("Name: " + extraRoles[i].getName() + " Description: " + extraRoles[i].getDescription() + " Rank: " + extraRoles[i].getRank());
         }
      }
      System.out.println("");
      System.out.println("Starring Roles in this scene include:");
      Role starRoles[] = scene.getStarringRoles();
      for (int i = 0; i < starRoles.length; i++) {
         if (starRoles[i].getPlayerOn() == null) {
            System.out.println("Name: " + starRoles[i].getName() + " Description: " + starRoles[i].getDescription() + " Rank: " + starRoles[i].getRank());
         }
      }
      System.out.println("");
   }

   // return scene
   public Scene getScene(){
      return scene;
   }
   // return functioning shot counter
   public int getShotCounter(){
      return currShotCounter;
   }
   // return sceneFlipped
   public boolean getFlippedScene() {
      return sceneFlipped;
   }
   // return off card roles
   public Role[] getExtraRoles() {
      return this.extraRoles;
   }
   // reset sceneFlipped
   public void resetSceneFlipped() {
      this.sceneFlipped = false;
   }
   // reset functioning shot counter
   public void resetShotCounter() {
      this.currShotCounter = this.shotCounter;
   }
   
   public void playerOff(Player player) {
   
   }








}
