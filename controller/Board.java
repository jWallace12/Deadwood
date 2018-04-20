// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345

package controller;

import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.Dimension;


public class Board extends JLayeredPane
{
   private static boolean valid = true;

   private static class Closer extends WindowAdapter {
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   }
   
   // declare buttons
   static JButton act;
   static JButton rehearse;
   static JButton work;
   static JButton upgrade;
   static JButton end;
   static JButton inputRole;
   static JButton twoDollars;
   static JButton twoCredits;
   static JButton threeDollars;
   static JButton threeCredits;
   static JButton fourDollars;
   static JButton fourCredits;
   static JButton fiveDollars;
   static JButton fiveCredits;
   static JButton sixDollars;
   static JButton sixCredits;
   
   
   static JButton trainStation;
   static JButton mainStreet;
   static JButton trailer;
   static JButton castingOffice;
   static JButton hotel;
   static JButton bank;
   static JButton secretHideout;
   static JButton ranch;
   static JButton generalStore;
   static JButton jail;
   static JButton saloon;
   static JButton church;
   
   static JFrame frame;

   
   static view.Resources r = view.Resources.getInstance();
   
   // contructor for Board controller
   public Board(model.DeadWood modelBoard){
      ImageIcon background = r.getBackground();
      setBounds(0,0,1500,900);
      setButtons(background);
   }
   
   //initalized all the buttons used with pixel location and mouse listeners
   private void setButtons(ImageIcon back) {
         act = new JButton("Act");
         act.setBounds(back.getIconWidth()+80, 220, 120, 30);
         add(act, new Integer(2));
         act.addMouseListener(new boardMouseListener());
         
         rehearse = new JButton("Rehearse");
         rehearse.setBounds(back.getIconWidth()+80, 255, 120, 30);
         add(rehearse, new Integer(2));
         rehearse.addMouseListener(new boardMouseListener());
         
         work = new JButton("Work");
         work.setBounds(back.getIconWidth()+80, 290, 120, 30);
         add(work, new Integer(2));
         work.addMouseListener(new boardMouseListener());
         
         upgrade = new JButton("Upgrade");
         upgrade.setBounds(back.getIconWidth()+80, 325, 120, 30);
         add(upgrade, new Integer(2));
         upgrade.addMouseListener(new boardMouseListener());
         
         end = new JButton("End");
         end.setBackground(Color.red);
         end.setBounds(back.getIconWidth()+80, 360, 120, 30);
         add(end, new Integer(2));
         end.addMouseListener(new boardMouseListener());
         
         trailer = new JButton("Trailers");
         trailer.setBackground(Color.pink);
         trailer.setBounds(back.getIconWidth()+80, 420, 120, 30);
         add(trailer, new Integer(2));
         trailer.addMouseListener(new boardMouseListener());
         
         castingOffice = new JButton("Casting Office");
         castingOffice.setBackground(Color.pink);
         castingOffice.setBounds(back.getIconWidth()+80, 460, 120, 30);
         add(castingOffice, new Integer(2));
         castingOffice.addMouseListener(new boardMouseListener());
         
         saloon = new JButton("Saloon");
         saloon.setBackground(Color.pink);
         saloon.setBounds(back.getIconWidth()+80, 500, 120, 30);
         add(saloon, new Integer(2));
         saloon.addMouseListener(new boardMouseListener());
         
         hotel = new JButton("Hotel");
         hotel.setBackground(Color.pink);
         hotel.setBounds(back.getIconWidth()+80, 540, 120, 30);
         add(hotel, new Integer(2));
         hotel.addMouseListener(new boardMouseListener());
         
         mainStreet = new JButton("Main Street");
         mainStreet.setBackground(Color.pink);
         mainStreet.setBounds(back.getIconWidth()+80, 580, 120, 30);
         add(mainStreet, new Integer(2));
         mainStreet.addMouseListener(new boardMouseListener());
         
         bank = new JButton("Bank");
         bank.setBackground(Color.pink);
         bank.setBounds(back.getIconWidth()+80, 620, 120, 30);
         add(bank, new Integer(2));
         bank.addMouseListener(new boardMouseListener());
         
         church = new JButton("Church");
         church.setBackground(Color.pink);
         church.setBounds(back.getIconWidth()+80, 660, 120, 30);
         add(church, new Integer(2));
         church.addMouseListener(new boardMouseListener());
         
         ranch = new JButton("Ranch");
         ranch.setBackground(Color.pink);
         ranch.setBounds(back.getIconWidth()+80, 700, 120, 30);
         add(ranch, new Integer(2));
         ranch.addMouseListener(new boardMouseListener());
         
         generalStore = new JButton("General Store");
         generalStore.setBackground(Color.pink);
         generalStore.setBounds(back.getIconWidth()+80, 740, 120, 30);
         add(generalStore, new Integer(2));
         generalStore.addMouseListener(new boardMouseListener());
         
         jail = new JButton("Jail");
         jail.setBackground(Color.pink);
         jail.setBounds(back.getIconWidth()+80, 780, 120, 30);
         add(jail, new Integer(2));
         jail.addMouseListener(new boardMouseListener());
         
         trainStation = new JButton("Train Station");
         trainStation.setBackground(Color.pink);
         trainStation.setBounds(back.getIconWidth()+80, 820, 120, 30);
         add(trainStation, new Integer(2));
         trainStation.addMouseListener(new boardMouseListener());
         
         secretHideout = new JButton("Secret Hideout");
         secretHideout.setBackground(Color.pink);
         secretHideout.setBounds(back.getIconWidth()+80, 860, 120, 30);
         add(secretHideout, new Integer(2));
         secretHideout.addMouseListener(new boardMouseListener());   
      }

   // static class for mouse listeners used by buttons
   static class boardMouseListener implements MouseListener, ActionListener {
     public JOptionPane field = new JOptionPane();
  
   // Handles actions depending on the moused clicked event.
   // Deactive all other buttons when a valid move is completed
     public void mouseClicked(MouseEvent e)  {   
         if ((e.getSource() == act) && (valid)) {
            valid = false;
            try {
               model.DeadWood.act(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()));
            } catch (Exception ex) {
               System.out.println("Caught exception, quitting...");
               System.exit(0);
            }
            
         } else if ((e.getSource() == rehearse) && (valid)) {
            if (model.DeadWood.checkRehearse(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()))) {
               valid = false;
            }
           // go to next turn, and reactive all buttons
         } else if (e.getSource() == end) {
            valid = true;
            model.DeadWood.incrementPlayerIndex();
            try {
               model.DeadWood.nextTurn();
            } catch (Exception ex) {
               System.out.println("Caught exception, quitting...");
               System.exit(0);
            }
         } else if ((e.getSource() == work) && (valid)) {
               String answer = field.showInputDialog("Howdy! Which role would you like to take?");
            if (answer != null) {
               if (model.DeadWood.checkWork(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), answer)) {
                  valid = false;
               }   
            }        
         } else if ((e.getSource() == upgrade) && (valid)) {
            if (model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()).getRoom().getName().equals("Casting Office")) {
               upgradeChoices();
               // creates buttons for upgrading rank price options
               
               JLayeredPane pane = new JLayeredPane();
               frame = new JFrame();
               frame.setPreferredSize(new Dimension(375, 350));
               frame.setResizable(false);
               frame.setVisible(true);
               
               JLabel string = new JLabel("Choose your rank!");
               string.setBounds(130, -70, 200, 200);
               pane.add(string, new Integer(2));
               
               twoDollars = new JButton("Rank 2 - 4 Dollars");
               twoDollars.setBounds(20, 50, 150, 40);
               pane.add(twoDollars, new Integer(2));
               twoDollars.addMouseListener(new boardMouseListener());
               
               twoCredits = new JButton("Rank 2 - 5 Credits");
               twoCredits.setBounds(200, 50, 150, 40);
               pane.add(twoCredits, new Integer(2));
               twoCredits.addMouseListener(new boardMouseListener());
               
               threeDollars = new JButton("Rank 3 - 10 Dollars");
               threeDollars.setBounds(20, 100, 150, 40);
               pane.add(threeDollars, new Integer(2));
               threeDollars.addMouseListener(new boardMouseListener());
               
               threeCredits = new JButton("Rank 3 - 10 Credits");
               threeCredits.setBounds(200, 100, 150, 40);
               pane.add(threeCredits, new Integer(2));
               threeCredits.addMouseListener(new boardMouseListener());
               
               fourDollars = new JButton("Rank 4 - 18 Dollars");
               fourDollars.setBounds(20, 150, 150, 40);
               pane.add(fourDollars, new Integer(2));
               fourDollars.addMouseListener(new boardMouseListener());
               
               fourCredits = new JButton("Rank 4 - 15 Credits");
               fourCredits.setBounds(200, 150, 150, 40);
               pane.add(fourCredits, new Integer(2));
               fourCredits.addMouseListener(new boardMouseListener());
               
               fiveDollars = new JButton("Rank 5 - 28 Dollars");
               fiveDollars.setBounds(20, 200, 150, 40);
               pane.add(fiveDollars, new Integer(2));
               fiveDollars.addMouseListener(new boardMouseListener());
               
               fiveCredits = new JButton("Rank 5 - 20 Credits");
               fiveCredits.setBounds(200, 200, 150, 40);
               pane.add(fiveCredits, new Integer(2));
               fiveCredits.addMouseListener(new boardMouseListener());
               
               sixDollars = new JButton("Rank 6 - 40 Dollars");
               sixDollars.setBounds(20, 250, 150, 40);
               pane.add(sixDollars, new Integer(2));
               sixDollars.addMouseListener(new boardMouseListener());
               
               sixCredits = new JButton("Rank 6 - 25 Credits");
               sixCredits.setBounds(200, 250, 150, 40);
               pane.add(sixCredits, new Integer(2));
               sixCredits.addMouseListener(new boardMouseListener());
               
               frame.add(pane);
               frame.pack();
            }
         } else if ((e.getSource() == trainStation) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Train Station")) {
               valid = false;
            }
         } else if ((e.getSource() == mainStreet) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Main Street")) {
               valid = false;
            }
         } else if ((e.getSource() == trailer) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Trailer")) {
               valid = false;
            }  
         } else if ((e.getSource() == castingOffice) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Casting Office")) {
               valid = false;
            }
         } else if ((e.getSource() == ranch) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Ranch")) {
               valid = false;
            }
         } else if ((e.getSource() == saloon) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Saloon")) {
               valid = false;
            }
         } else if ((e.getSource() == secretHideout) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Secret Hideout")) {
               valid = false;
            }
         } else if ((e.getSource() == bank) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Bank")) {
               valid = false;
            }
         } else if ((e.getSource() == jail) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Jail")) {
               valid = false;
            }
         } else if ((e.getSource() == church) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Church")) {
               valid = false;
            }
         } else if ((e.getSource() == generalStore) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "General Store")) {
               valid = false;
            }
         } else if ((e.getSource() == hotel) && (valid)) {
            if (model.DeadWood.move(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "Hotel")) {
               valid = false;
            }
         } else if ((e.getSource() == twoDollars) && (valid)) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "$", "2")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == twoCredits) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "cr", "2")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == threeDollars) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "$", "3")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == threeCredits) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "cr", "3")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == fourDollars) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "$", "4")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == fourCredits) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "cr", "4")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == fiveDollars) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "$", "5")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == fiveCredits) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "cr", "5")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == sixDollars) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "$", "6")) {
               valid = false;
               frame.dispose();
            }
         } else if (e.getSource() == sixDollars) {
            if (model.DeadWood.upgradeRank(model.DeadWood.getPlayer(model.DeadWood.getPlayerIndex()), "cr", "6")) {
               valid = false;
               frame.dispose();
            }
         }
     }
     
     public void mousePressed(MouseEvent e) {
     }
     public void mouseReleased(MouseEvent e) {
     }
     public void mouseEntered(MouseEvent e) {
     }
     public void mouseExited(MouseEvent e) {
     }
     public void actionPerformed(ActionEvent e) {
     }
  }
  
  //when work button is pressed, another window prompts user to input a requested role
  public static void chosenWork(model.Player player) throws Exception {
     JOptionPane question = new JOptionPane();
     if (player.getRoom().getScene().isActive()) {
        String answer = question.showInputDialog("Howdy! Type in the name of a role if you'd like to take one!");
        if (answer != null) {
           model.DeadWood.checkWork(player, answer);
        }
     }
  }
  
  // window prompts user to choose a rank to upgrade to and creates buttons
  public static void upgradeChoices() {
      frame = new JFrame();
      JLayeredPane pane = new JLayeredPane();
      frame = new JFrame();
      frame.setPreferredSize(new Dimension(375, 350));
      frame.setResizable(false);
      frame.setVisible(true);
      
      JLabel string = new JLabel("Choose your rank!");
      string.setBounds(130, -70, 200, 200);
      pane.add(string, new Integer(2));
      
      twoDollars = new JButton("Rank 2 - 4 Dollars");
      twoDollars.setBounds(20, 50, 150, 40);
      pane.add(twoDollars, new Integer(2));
      twoDollars.addMouseListener(new boardMouseListener());
      
      twoCredits = new JButton("Rank 2 - 5 Credits");
      twoCredits.setBounds(200, 50, 150, 40);
      pane.add(twoCredits, new Integer(2));
      twoCredits.addMouseListener(new boardMouseListener());
      
      threeDollars = new JButton("Rank 3 - 10 Dollars");
      threeDollars.setBounds(20, 100, 150, 40);
      pane.add(threeDollars, new Integer(2));
      threeDollars.addMouseListener(new boardMouseListener());
      
      threeCredits = new JButton("Rank 3 - 10 Credits");
      threeCredits.setBounds(200, 100, 150, 40);
      pane.add(threeCredits, new Integer(2));
      threeCredits.addMouseListener(new boardMouseListener());
      
      fourDollars = new JButton("Rank 4 - 18 Dollars");
      fourDollars.setBounds(20, 150, 150, 40);
      pane.add(fourDollars, new Integer(2));
      fourDollars.addMouseListener(new boardMouseListener());
      
      fourCredits = new JButton("Rank 4 - 15 Credits");
      fourCredits.setBounds(200, 150, 150, 40);
      pane.add(fourCredits, new Integer(2));
      fourCredits.addMouseListener(new boardMouseListener());
      
      fiveDollars = new JButton("Rank 5 - 28 Dollars");
      fiveDollars.setBounds(20, 200, 150, 40);
      pane.add(fiveDollars, new Integer(2));
      fiveDollars.addMouseListener(new boardMouseListener());
      
      fiveCredits = new JButton("Rank 5 - 20 Credits");
      fiveCredits.setBounds(200, 200, 150, 40);
      pane.add(fiveCredits, new Integer(2));
      fiveCredits.addMouseListener(new boardMouseListener());
      
      sixDollars = new JButton("Rank 6 - 40 Dollars");
      sixDollars.setBounds(20, 250, 150, 40);
      pane.add(sixDollars, new Integer(2));
      sixDollars.addMouseListener(new boardMouseListener());
      
      sixCredits = new JButton("Rank 6 - 25 Credits");
      sixCredits.setBounds(200, 250, 150, 40);
      pane.add(sixCredits, new Integer(2));
      sixCredits.addMouseListener(new boardMouseListener());
      
      frame.add(pane);
      frame.pack();
  }
}
