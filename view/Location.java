// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345
package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;

public class Location extends JLayeredPane {

   private JLabel[] dice;
   private JLabel card;
   private static Resources r = Resources.getInstance();
   private LinkedList<JLabel> shots = new LinkedList<JLabel>();
   private Scene scene;
   private Role[] extraRoles;
   private model.Location location;
   private int shotCount = 0;
   private int permShotCount = 0;
   
   // contructor for board locations
   public Location(int x, int y, int h, int w, model.DeadWood board, int numRoles, int roomIndex){
     location = (model.Location)board.getRooms()[roomIndex];
     extraRoles = new Role[numRoles];
     dice = new JLabel[board.getNumPlayers()];
     setBounds(x,y,h,w);
     int a = 0;      
     
     // sets the available spots on location for dice when players move to new location
     for (int i = 0; i < dice.length; i++) {
         dice[i] = new JLabel();
         dice[i].setVisible(false);
         add(dice[i], new Integer(5));
         dice[i].setBounds(a, -40, h, w);
         setIcon(board.getPlayer(i));
         a+=13;
      }
      
  
  
   }
   
   // add a view scene to this location
   public void addScene(view.Scene scene) {
      this.scene = scene;
   }
  
      /* Deadwood role interface taken */
   public void changed (model.Location location){
      if(location.getFlippedScene()){
         card.setIcon(r.getCard(location.getScene().getSceneNumber()));
       }else{
         card.setIcon(r.getCardBack());
       }
       card.setVisible(true);
   }
   
   // add new shot counter JLabel
   public void addShot(JLabel shot) {
      shots.add(shot);
      this.shotCount++;
      this.permShotCount++;
   }
   
   //makes shot counter visible after successful act, decrements shot counter as well
   public void visibleShot() {
      shots.get(shotCount-1).setVisible(true);
      shotCount--;
   }
   
   // used by to reset shot counter to invisible after a day is over
   public void resetShots() {
      shotCount = permShotCount;
      for (int i = 0; i < permShotCount; i++) {
         shots.get(i).setVisible(false);
      }
   }
   
   // sets player's icon on location
   public void setIcon(model.Player player) {   
      dice[player.getPlayerIndex()].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
   }
   
   // makes player dice visible on location
   public void playerOn(model.Player player) {
      dice[player.getPlayerIndex()].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
      dice[player.getPlayerIndex()].setVisible(true);
   }
   
   // makes player dice invisible on location
   public void playerOff(model.Player player) {
      dice[player.getPlayerIndex()].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
      dice[player.getPlayerIndex()].setVisible(false);
   }
   
   // adds view Roles to Location
   public void addRole(Role role) {
      for (int i = 0; i < extraRoles.length; i++) {
         if (extraRoles[i] == null) {
            extraRoles[i] = role;
            return;
         }
      }
   }
   
   // adds a view scene to location
   public view.Scene getScene() {
      return this.scene;
   }
   
   //reset the dice on extra role to invisible
  public void resetExtras(){
      for (int i = 0; i < extraRoles.length; i++) {
         extraRoles[i].resetDice();
      }  
   }
}