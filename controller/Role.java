// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345
package controller;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Role extends JPanel{
   
   // corresponding model role
   private model.Role role;
   
   // create role, add mouse listener
   public Role (int x, int y, int h, int w, model.Role r) {
      setBounds(x,y,h,w);
      setOpaque(false);
      addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            clicked();
         }});
      role = r;
   }
   
   // changed die image, set player on/off
   private void clicked() {
      if (!(role.getPlayerOn() == null)) {
         role.setPlayerOn(null);
      } else {
         model.Player player = new model.Player(0, 0, 0);
         role.setPlayerOn(player);
      }
   }
}