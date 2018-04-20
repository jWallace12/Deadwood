// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345

package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JFrame;
import java.awt.Dimension;

// board view
public class Board extends JLayeredPane{

   private static class Closer extends WindowAdapter {
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   }

   public static view.Location[] locations = new view.Location[12];

   static Resources r = Resources.getInstance();
   static JLabel currDie;
   static JLabel credits;
   static JLabel dollars;
   static JLabel rehearsal;
   static JLabel[] cards;  
   static private JLabel boardLabel;
   public Board(model.DeadWood modelBoard) throws Exception {
      
      // initialize the visuals
      cards = new JLabel[3];
      boardLabel = new JLabel();
      Class cls = getClass();
      ImageIcon background = r.getBackground();
      boardLabel.setIcon(background);
      add(boardLabel, new Integer(0));
      boardLabel.setBounds(0, 0, background.getIconWidth()+200, background.getIconHeight());
      setBounds(boardLabel.getBounds());
      setUp(modelBoard);  
      initializeCards();         
      initializeDie();
      
      }
      
      // return the cards
      public static JLabel[] getCards(){
         return cards;
      }
      
      // create three seperate cards for number of roles
      private void initializeCards() {
         JLabel oneRole = new JLabel();
         oneRole.setBounds(0,0,216,120);
         oneRole.setVisible(true);
         oneRole.setIcon(r.getCardBack());
         
         JLabel twoRoles = new JLabel();
         twoRoles.setBounds(0,0,216,120);
         twoRoles.setVisible(true);
         twoRoles.setIcon(r.getCardBack());
         JLabel threeRoles = new JLabel();
         threeRoles.setBounds(0,0,216,120);
         threeRoles.setVisible(true);
         threeRoles.setIcon(r.getCardBack());
      }
      
      // create locations, shot counters, scenes, and roles
      private void setUp(model.DeadWood modelBoard) {
         model.Room[] rooms = model.DeadWood.getRooms();
         model.Location currLocation = (model.Location)rooms[6];
         Role role;
         Location location = new Location(0,0,0,0,modelBoard,0,6);
         Trailer trailer;
         CastingOffice office;
         JLabel shot;
         Scene scene;
         
         trailer = new Trailer(1000, -20, 300, 400, modelBoard); //1100, 120
         add(trailer, new Integer(1));
         
         office = new CastingOffice(20, 160, 300, 400, modelBoard);
         add(office, new Integer(1));
         
                                                                            
         Location trainStation = new Location(21, 68, 216, 120, modelBoard, 4, 2);      // train station, 4 counters
         locations[6] = trainStation;
         add(trainStation, new Integer(2));
         scene = new Scene(21,68,205,115, (model.Location)rooms[6]);
         trainStation.addScene(scene);
         add(scene, new Integer(1));
        
         role = new Role(51, 268, 46, 46, currLocation.getExtraRoles()[0]); // dragged by train
         trainStation.addRole(role);
         add(role, new Integer(3));
         role = new Role(114, 227, 46, 46, currLocation.getExtraRoles()[1]); // crusty
         trainStation.addRole(role);
         add(role, new Integer(3));
         role = new Role(114,320, 46, 46, currLocation.getExtraRoles()[2]);
         trainStation.addRole(role);
         add(role, new Integer(3));
         role = new Role(49, 356, 46, 46, currLocation.getExtraRoles()[3]);
         trainStation.addRole(role);
         add(role, new Integer(3));
         
         shot = setShots(currLocation, 42, 15);
         trainStation.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 95, 15);
         trainStation.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 150, 15);
         trainStation.addShot(shot);
         add(shot, new Integer(4));
         
         
         /******************************************************************************/
         currLocation = (model.Location)rooms[2]; // secret hideout
         Location secretHideout = new Location(27, 733, 216, 120, modelBoard, 4, 2); // 36, 793
         locations[2] = secretHideout;
         add(secretHideout, new Integer(2));
         
         scene = new Scene(27, 733, 205, 115, currLocation);
         secretHideout.addScene(scene);
         add(scene, new Integer(1));
         
         shot = setShots(currLocation, 362, 772);
         secretHideout.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 305, 772);
         secretHideout.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 251, 772);
         secretHideout.addShot(shot);
         add(shot, new Integer(4));
         
         
         role = new Role(435, 719, 46, 46, currLocation.getExtraRoles()[0]);
         add(role, new Integer(3));
         secretHideout.addRole(role);
         role = new Role(521, 719, 46, 46, currLocation.getExtraRoles()[1]);
         add(role, new Integer(3));
         secretHideout.addRole(role);
         role = new Role(435, 808, 46, 46, currLocation.getExtraRoles()[2]);
         add(role, new Integer(3));
         secretHideout.addRole(role);
         role = new Role(521, 808, 46, 46, currLocation.getExtraRoles()[3]);
         add(role, new Integer(3));
         secretHideout.addRole(role);
         
                  /******************************************************************************/

         currLocation = (model.Location)rooms[9]; // church
         
         location = new Location(623, 735, 216, 120, modelBoard, 2, 9);
         locations[9] = location;
         add(location, new Integer(2));
         
         scene = new Scene(623, 735, 205, 115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
         
         role = new Role(857, 730, 46, 46, currLocation.getExtraRoles()[0]);
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(858, 809, 46, 46, currLocation.getExtraRoles()[1]);
         add(role, new Integer(3));
         location.addRole(role);
         
         shot = setShots(currLocation, 630, 680);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 690, 680);
         location.addShot(shot);
         add(shot, new Integer(4));
         
         
                  /******************************************************************************/

         currLocation = (model.Location)rooms[5]; // hotel
         
         location = new Location(969, 740, 216, 120, modelBoard, 4, 5);
         locations[5] = location;
         add(location, new Integer(1));
         scene = new Scene(969,740,205,115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
         
         role = new Role(1111, 469, 46, 46, currLocation.getExtraRoles()[1]); // drunkard
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(1044, 509, 46, 46, currLocation.getExtraRoles()[0]);   // faro
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(1111, 557, 46, 46, currLocation.getExtraRoles()[2]);  // balcony
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(1046, 596, 46, 46, currLocation.getExtraRoles()[3]);  // bartender
         add(role, new Integer(3));
         location.addRole(role); 
         
         shot = setShots(currLocation, 1012, 691);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 1065, 691);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 1117, 691);
         location.addShot(shot);
         add(shot, new Integer(4));
         
                  /******************************************************************************/

         currLocation = (model.Location)rooms[10]; // saloon
         
         location = new Location(632, 280, 205, 115, modelBoard, 2, 10);
         locations[10] = location;
         add(location, new Integer(2));
         scene = new Scene(632,280,205,115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
   
         role = new Role(877, 276, 46, 46, currLocation.getExtraRoles()[0]);     // woman                            
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(877, 352, 46, 46, currLocation.getExtraRoles()[1]);  // farmer    
         add(role, new Integer(3));
         location.addRole(role);   
         
         shot = setShots(currLocation, 686, 224);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 631, 224);
         location.addShot(shot);
         add(shot, new Integer(4));
         
             /******************************************************************************/

         currLocation = (model.Location)rooms[4]; // bank
         
         location = new Location(623, 475, 216, 120, modelBoard, 2, 4);
         locations[4] = location;
         add(location, new Integer(2));
         scene = new Scene(623,475,205,115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
         
         role = new Role(911, 544, 46, 46, currLocation.getExtraRoles()[0]); // gentleman
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role (911, 470, 46, 46, currLocation.getExtraRoles()[1]); // flustered                                 
         add(role, new Integer(3));
         location.addRole(role);
         
         shot = setShots(currLocation, 845, 555);
         location.addShot(shot);
         add(shot, new Integer(4));
         
             /******************************************************************************/

         currLocation = (model.Location)rooms[3]; // ranch
         
         location = new Location(252, 477, 216, 120, modelBoard, 3, 3);
         locations[3] = location;
         add(location, new Integer(2));
         scene = new Scene(252, 477, 205, 115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
         
         role = new Role(412,608, 46, 46, currLocation.getExtraRoles()[0]); // shot
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(488, 608, 46, 46, currLocation.getExtraRoles()[1]);  // saucy
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(488, 525, 46, 46, currLocation.getExtraRoles()[2]); // horse
         add(role, new Integer(3));
         location.addRole(role);
         
         shot = setShots(currLocation, 477, 481);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 531, 481);
         location.addShot(shot);
         add(shot, new Integer(4));
         
            /******************************************************************************/

         
         currLocation = (model.Location)rooms[8]; // general store
         
         location = new Location(370, 282, 216, 120, modelBoard, 2, 8);
         locations[8] = location;
         add(location, new Integer(2));
         scene = new Scene(370,282,205,115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
   
         role = new Role(236,276 , 46, 46, currLocation.getExtraRoles()[0]);  // overalls
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(236, 358, 46, 46, currLocation.getExtraRoles()[1]); // keach
         add(role, new Integer(3));
         location.addRole(role);
         
         shot = setShots(currLocation, 321, 285);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 321, 336);
         location.addShot(shot);
         add(shot, new Integer(4));
         
            /******************************************************************************/

         
         currLocation = (model.Location)rooms[7]; // jail
         
         location = new Location(282, 27, 216, 120, modelBoard, 2, 7);
         locations[7] = location;
         add(location, new Integer(2));
         
         scene = new Scene(282,27,205,115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
         
         role = new Role(519, 25, 46, 46, currLocation.getExtraRoles()[0]);  // prisoner
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(519, 105, 46, 46, currLocation.getExtraRoles()[1]); // irons
         add(role, new Integer(3));
         location.addRole(role);
         
         shot = setShots(currLocation, 450, 161);
         location.addShot(shot);
         add(shot, new Integer(4));
         
             /******************************************************************************/

         
         currLocation = (model.Location)rooms[11]; // main street
         
         location = new Location(969, 27, 216, 122, modelBoard, 4, 11);
         locations[11] = location;
         add(location, new Integer(2));
         scene = new Scene(969,27,205,115, currLocation);
         location.addScene(scene);
         add(scene, new Integer(1));
         
         shot = setShots(currLocation, 809, 30);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 862, 30);
         location.addShot(shot);
         add(shot, new Integer(4));
         shot = setShots(currLocation, 919, 30);
         location.addShot(shot);
         add(shot, new Integer(4));

         role = new Role(635, 22, 46, 46, currLocation.getExtraRoles()[0]); //railroad worker
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(720, 22, 46, 46, currLocation.getExtraRoles()[1]); //falls off roof
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(637, 105, 46, 46, currLocation.getExtraRoles()[2]); // woman in black dress
         add(role, new Integer(3));
         location.addRole(role);
         role = new Role(720, 105, 46, 46, currLocation.getExtraRoles()[3]); //mayor Mcginty
         add(role, new Integer(3));
         location.addRole(role);

      }
      

    // create shots   
    private static JLabel setShots(model.Location location, int shotX, int shotY){
        int imgShift = 10;
        JLabel shot = new JLabel();
        shot.setVisible(false);
        shot.setBounds(shotX,shotY, 30, 30);
        int shotcounter = location.getShotCounter();
        shot.setIcon(r.getShotCounter());
        return shot;     
   }
   
   // set up the sidebar for player states 
   public static void initializeDie() {
     JLabel currPlayer = new JLabel("Current Player");
     currPlayer.setBounds(1300, 40, 100, 45);
     currPlayer.setVisible(true);
     boardLabel.add(currPlayer, new Integer(2));
     
     currDie = new JLabel();
     currDie.setBounds(1320, 75, 45, 45);
     currDie.setVisible(true);
     boardLabel.add(currDie, new Integer(2));
     
     credits = new JLabel();
     credits.setVisible(true);
     credits.setBounds(1310, 120, 100, 45);
     boardLabel.add(credits, new Integer(2));
     
     dollars = new JLabel();
     dollars.setVisible(true);
     dollars.setBounds(1310, 150, 100, 45);
     boardLabel.add(dollars, new Integer(2));
     
     rehearsal = new JLabel();
     rehearsal.setVisible(true);
     rehearsal.setBounds(1310, 180, 100, 45);
     boardLabel.add(rehearsal, new Integer(2));  
     
     }
     public static void changeDie(model.Player player){
     currDie.setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
      
     // show current player information
     credits.setText("Credits: " + Integer.toString(player.getCredits()));
     rehearsal.setText("Rehearses: " + Integer.toString(player.getRehearsalCount()));
     dollars.setText("Dollars: " + Integer.toString(player.getDollars()));
     }
     
     public static void flipCard(int roomIndex) {
      locations[roomIndex].getScene().getCard().setIcon(r.getCardBack());
      locations[roomIndex].getScene().getCard().setVisible(false);
     }
     
     // create window to delcare the game's winner
     public static void endGame(int index) throws Exception {
        JOptionPane winner = new JOptionPane();
        winner.showInputDialog("Congrats Player " + (index+1) + ", you win! Thanks everyone for playing!");
        Thread.sleep(2000);
        System.exit(0);
        
     }
     
     // change cards back to flipped
     public static void resetCards(JLabel card) {
        card.setIcon(r.getCardBack());
     }
}
