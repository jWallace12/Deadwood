// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLayeredPane;


public class Deadwood {

   static model.DeadWood modelBoard;
   static JFrame frame2;
   static int numPlayers = 0;
   static JButton two;
   static JButton three;
   static JButton four;
   static JButton five;
   static JButton six;
   static JButton seven;
   static JButton eight;

   private static class Closer extends WindowAdapter {
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   }
   
   static class boardMouseListener implements MouseListener {
     
     // set numPlayers with the associated button
     public void mouseClicked(MouseEvent e) {  
        if (e.getSource() == two) {
           numPlayers = 2;
           frame2.dispose();
        } else if (e.getSource() == three) {
           numPlayers = 3;
           frame2.dispose();
        } else if (e.getSource() == four) {
           numPlayers = 4;
           frame2.dispose();
        } else if (e.getSource() == five) {
           numPlayers = 5;
           frame2.dispose();
        } else if (e.getSource() == six) {
           numPlayers = 6;
           frame2.dispose();
        } else if (e.getSource() == seven) {
           numPlayers = 7;
           frame2.dispose();
        } else if (e.getSource() == eight) {
           numPlayers = 8;
           frame2.dispose();
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
   }
   
   // main function
   public static void main(String[] args) throws Exception {
      JFrame frame = new JFrame();
      JLayeredPane pane = new JLayeredPane();
      // pause until number of players are selected
      getNumPlayers();
      while (numPlayers == 0) {
         Thread.sleep(1);
      }
      // create the main frame
      modelBoard = new model.DeadWood(numPlayers);
      view.Board view = new view.Board(modelBoard);
      controller.Board contr = new controller.Board(modelBoard);

      pane.add(view, new Integer(0));
      pane.add(contr, new Integer(1));
      pane.setVisible(true);
      
      frame.setTitle("Deadwood");
      frame.setPreferredSize(new Dimension(1550,990));
      frame.setResizable(false);
      frame.addWindowListener(new Closer());
      
      frame.add(pane);
      
      frame.pack();
      frame.setVisible(true);
      
      // start the game!
      modelBoard.initializeDay();
      modelBoard.nextTurn();
   }
   
   // create window to choose number of players
   public static void getNumPlayers() {
      JLayeredPane pane = new JLayeredPane();
      frame2 = new JFrame();
      frame2.setPreferredSize(new Dimension(310, 300));
      frame2.setResizable(false);
      frame2.setVisible(true);
      
      JLabel welcome = new JLabel("Welcome to Deadwood!");
      welcome.setBounds(80, 10, 150, 50);
      pane.add(welcome, new Integer(2));
    
      JLabel players = new JLabel("How many players?");
      players.setBounds(90, 30, 150, 50);
      pane.add(players, new Integer(2));
      
      two = new JButton("2");
      two.setBounds(20, 80, 50, 50);
      pane.add(two, new Integer(2));
      two.addMouseListener(new boardMouseListener());
      
      three = new JButton("3");
      three.setBounds(90, 80, 50, 50);
      pane.add(three, new Integer(2));
      three.addMouseListener(new boardMouseListener());
      
      four = new JButton("4");
      four.setBounds(160, 80, 50, 50);
      pane.add(four, new Integer(2));
      four.addMouseListener(new boardMouseListener());
      
      five = new JButton("5");
      five.setBounds(230, 80, 50, 50);
      pane.add(five, new Integer(2));
      five.addMouseListener(new boardMouseListener());
      
      six = new JButton("6");
      six.setBounds(55, 150, 50, 50);
      pane.add(six, new Integer(2));
      six.addMouseListener(new boardMouseListener());
      
      seven = new JButton("7");
      seven.setBounds(125, 150, 50, 50);
      pane.add(seven, new Integer(2));
      seven.addMouseListener(new boardMouseListener());
      
      eight = new JButton("8");
      eight.setBounds(195, 150, 50, 50);
      pane.add(eight, new Integer(2));
      eight.addMouseListener(new boardMouseListener());
      
      frame2.add(pane);
      frame2.pack();
   }

      
   
}
