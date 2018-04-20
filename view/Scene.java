// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345
package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class Scene extends JLayeredPane {
   private JLabel[] dice;
   private JLabel card;
   private Resources r = Resources.getInstance();
   private boolean[] shots;
   private JLabel shotCounters;
   private Role[] starringRoles;
   public Scene(int x, int y, int h, int w, model.Location location ){

      //starringRoles = new Roles[location.

      setBounds(x,y,h,w);        // sets bounds for scene card area for this location
      shots = new boolean[location.getShotCounter()];
      for (int i = 0; i < shots.length; i++) {
         shots[i] = false;
      }
      // set card for scene
       card = new JLabel();
       card.setVisible(true);
       card.setIcon(r.getCardBack());
       add(card, new Integer(1));
       card.setBounds(0,0,h,w);
       
       // set locations and number of corresponding on card roles
       int numRoles = location.getScene().getStarringRoles().length;
       starringRoles = new Role[numRoles];
       JLabel die = new JLabel();
       if (numRoles == 1) {
         dice = new JLabel[1];
         die.setVisible(false);
         die.setBounds(80, 45, 45, 45);
         die.setOpaque(true);
         add(die, new Integer(5));
         dice[0] = die;
       } else if (numRoles == 2) {
         dice = new JLabel[2];
         die.setVisible(false);
         die.setOpaque(true);
         die.setBounds(50, 45, 45, 45);
         add(die, new Integer(5));
         dice[0] = die;
         die = new JLabel();
         die.setVisible(false);
         die.setOpaque(true);
         die.setBounds(115, 45, 45, 45);
         add(die, new Integer(5));
         dice[1] = die;
       } else if (numRoles == 3) {
         dice = new JLabel[3];
         die.setVisible(false);
         die.setOpaque(true);
         die.setBounds(18, 45, 45, 45);
         add(die, new Integer(5));
         dice[0] = die;
         
         die = new JLabel();
         die.setVisible(false);
         die.setOpaque(true);
         die.setBounds(80, 45, 45, 45);
         add(die, new Integer(5));
         dice[1] = die;
         
         die = new JLabel();
         die.setVisible(false);
         die.setOpaque(true);
         die.setBounds(142, 45, 45, 45);
         add(die, new Integer(5));
         dice[2] = die;
       }
       
       //location.subscribe(this);
      

       
   }
   
      /* Deadwood role interface taken */
   public void changed (model.Location location, int img){
      if(location.getFlippedScene()){
         card.setIcon(r.getCard(img-1));
       } else {
         card.setIcon(r.getCardBack());
       }
       card.setVisible(true);
   }
   public void changeStarRole(model.Player player, int loc){
      //change the die of the loc index of jlabel to the player number color
      dice[loc].setIcon(r.getDie(player.getPlayerIndex(), player.getRank()-1));
      dice[loc].setVisible(true);
      view.Board.locations[player.getRoom().getIndex()].playerOff(player);
   }
   
   // return JLabel card
   public JLabel getCard() {
      return card;
   }
   
   // set all dice to invisible
   public void resetScenes() {
      for (int i = 0; i < dice.length; i++) {
         dice[i].setVisible(false);
      }
   }
   
}