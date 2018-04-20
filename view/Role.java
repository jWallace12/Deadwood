// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345
package view;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class Role extends javax.swing.JLayeredPane
                  implements model.Role.Listener
{
   // die image
   public JLabel dice;
   
   // resource instance
   private Resources r = Resources.getInstance();
   
   // constructor: set bounds, create die
   public Role(int x, int y, int h, int w, model.Role role){
      setBounds(x,y,h,w);
      dice = new JLabel();
      dice.setVisible(true);
      add(dice, new Integer(0));
      dice.setBounds(0,0,h,w);
      
      role.subscribe(this);
   }
   
   // set to corresponding die image, or make invisible
   public void changed (model.Role role){
      if (!(role.getPlayerOn() == null)) {
         int player = role.getPlayerOn().getPlayerIndex();
         int rank = role.getPlayerOn().getRank();
         dice.setIcon(r.getDie(player, rank-1));
         dice.setVisible(true);
         view.Board.locations[role.getPlayerOn().getRoom().getIndex()].playerOff(role.getPlayerOn());
      } else {
         dice.setVisible(false);
      }
   }
   // set all dice to invisible
   public void resetDice(){
      this.dice.setVisible(false);
   }
}
         