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

public class Role {
   
   public interface Listener {
      void changed(Role r);
   }
   
   private Collection<Listener> listeners;
   
   public void subscribe(Listener l) {
      listeners.add(l);
   }
   
   protected void changed() {
      for (Listener l : listeners) {
         l.changed(this);
      }
         
   }

   private int rank; // role rank
   private String name; // role name
   private String description; // role description
   private Player playerOn; // player in role
   private String type; // type of role (star/extra)
   private boolean taken = true;
   private int loc;
   
   // constructor
   public Role(int rank, String name, String description, String type, int loc) {
      this.rank = rank;
      this.name = name;
      this.description = description;
      this.playerOn = null;
      this.type = type;
      this.loc = loc;
      listeners = new LinkedList<Listener>();
   }

   // return rank
   public int getRank() {
      return rank;
   }
   // return name
   public String getName() {
      return name;
   }
   // return description
   public String getDescription() {
      return description;
   }
   // return role's player
   public Player getPlayerOn() {
      return this.playerOn;
   }
   // set role's player
   public void setPlayerOn(Player player) {
      this.playerOn = player;
      if(this.type == "extra"){
         changed();
      }else{
         view.Board.locations[player.getRoom().getIndex()].getScene().changeStarRole(player, loc);
      }
   }
   // get type of role
   public String getType(){
      return this.type;
   }
   public void setNull() {
      this.playerOn = null;
   }
}