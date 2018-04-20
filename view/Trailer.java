package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;

public class Trailer extends JLayeredPane {

   private static JLabel[] dice;
   private static Resources r = Resources.getInstance();
   
   public Trailer(int x, int y, int h, int w, model.DeadWood board) {
      int a = 0;
      dice = new JLabel[board.getNumPlayers()];
      setBounds(x,y,h,w);
      for (int i = 0; i < dice.length; i++) {
         dice[i] = new JLabel();
         dice[i].setVisible(true);
         add(dice[i], new Integer(5));
         dice[i].setBounds(a, 130, h, w);
         setIcon(board.getPlayer(i));
         a+=13;
      }
   }
   
   static public void setIcon(model.Player player) {   
      dice[player.getPlayerIndex()].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
   }
   static public void playerOn(model.Player player) {
      dice[player.getPlayerIndex()].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
      dice[player.getPlayerIndex()].setVisible(true);
   }
   static public void playerOff(model.Player player) {
      dice[player.getPlayerIndex()].setVisible(false);
   }
}