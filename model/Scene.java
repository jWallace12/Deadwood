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

public class Scene {

   private String name; // name
   private String description; // description
   private Location currLocation;
   private boolean sceneActive; // active scene or not
   private Role starringRoles[]; // on card roles
   private int budget; // budget
   private int sceneNumber; // scene ID
   private int img;

   // constructor for 1 role
   public Scene(String sceneName, String description, int budget, int sceneNumber, int rank1, String name1, String description1, String type, int imageNum, int loc) {
      this.name = sceneName;
      this.description = description;
      this.sceneActive = false;
      this.sceneNumber = sceneNumber;
      this.budget = budget;
      this.starringRoles = new Role[1];
      this.starringRoles[0] = new Role(rank1, name1, description1, type, loc);
      this.img = imageNum;
   }
   // constructor for 2 roles
   public Scene(String sceneName, String description, int budget, int sceneNumber, int rank1, String name1, String description1, int rank2, String name2, String description2, String type,
    int imageNum, int loc1, int loc2) {
      this.name = sceneName;
      this.description = description;
      this.sceneActive = false;
      this.sceneNumber = sceneNumber;
      this.budget = budget;
      this.starringRoles = new Role[2];
      this.starringRoles[0] = new Role(rank1, name1, description1, type, loc1);
      this.starringRoles[1] = new Role(rank2, name2, description2, type, loc2);
      this.img = imageNum;

   }
   // constructor for 3 roles
   public Scene(String sceneName, String description, int budget, int sceneNumber, int rank1, String name1, String description1, int rank2, String name2, String description2,
      int rank3, String name3, String description3, String type, int imageNum, int loc1, int loc2, int loc3) {
      this.name = sceneName;
      this.description = description;
      this.sceneActive = false;
      this.sceneNumber = sceneNumber;
      this.starringRoles = new Role[3];
      this.budget = budget;
      this.starringRoles[0] = new Role(rank1, name1, description1, type, loc1);
      this.starringRoles[1] = new Role(rank2, name2, description2, type, loc2);
      this.starringRoles[2] = new Role(rank3, name3, description3, type, loc3);
      this.img = imageNum;

   }


   // return name
   public String getName(){
      return name;
   }
   // return description
   public String getDescription(){
      return description;
   }
   // return budget
   public int getBudget(){
      return budget;
   }
   // return scene number
   public int getSceneNumber(){
      return sceneNumber;
   }
   // return starring roles
   public Role[] getStarringRoles() {
      return this.starringRoles;
   }
   public boolean isActive(){
      return sceneActive;
   }
   public Location getLocation(Location currLocation) {
      return this.currLocation = currLocation;
   }
   public int getImg() {
      return this.img;
   }
   public void activeScene() {
      this.sceneActive = true;
   }
   public void inactiveScene() {
      this.sceneActive = false;
   }
}
