// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345

package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;

public class CastingOffice extends JLayeredPane {

   private static JLabel[] dice;
   private static Resources r = Resources.getInstance();
   
   // casting office constructor
   public CastingOffice(int x, int y, int h, int w, model.DeadWood board) {
      int a = 0;
      dice = new JLabel[board.getNumPlayers()];
      setBounds(x,y,h,w);
      //adds bounds for player's dice to appear and makes invisible
      for (int i = 0; i < dice.length; i++) {
         dice[i] = new JLabel();
         dice[i].setVisible(false);
         add(dice[i], new Integer(5));
         dice[i].setBounds(a, 130, h, w);
         setIcon(board.getPlayer(i));
         a+=13;
      }
   }
   
   // sets a icon depending on the player argument and players rank
   static public void setIcon(model.Player player) {   
      dice[player.getPlayerIndex()].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
   }
   
   // makes the player dice appear with specified color and rank
   static public void playerOn(model.Player player) {
      dice[player.getPlayerIndex()].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
      dice[player.getPlayerIndex()].setVisible(true);
   }
   
   // makes the player dice invisible
   static public void playerOff(model.Player player) {
      dice[player.getPlayerIndex()].setVisible(false);
   }
}