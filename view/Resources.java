// GUI implementation of Deadwood
// By Trevor Glass and Jonah Wallace
// WWU - CSCI - 345
package view;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Resources {
   private ImageIcon[] cards;
   private ImageIcon[][] dice;
   private ImageIcon background;
   private ImageIcon cardBack;
   private ImageIcon shotCounter;
   static Resources instance;

   private Resources() {
      //for the scene cards
      cards = new ImageIcon[40];
      try{
      // sets image icons to the correct files found in resources folder
         background = new ImageIcon(
            ImageIO.read(
               new File (String.format("./resources/DeadwoodBoard.jpg"))));
               
         cardBack = new ImageIcon(
            ImageIO.read(
               new File (String.format("./resources/cardBack.png"))));

         shotCounter = new ImageIcon(
            ImageIO.read(
               new File (String.format("./resources/shot.png"))));

         for(int i = 0; i < 40; i++){
            cards[i] = new ImageIcon(
               ImageIO.read(
                  new File(String.format("./resources/cards/%d.png", i+1))));
         }
         dice = new ImageIcon[8][6];

         for(int i = 0; i < 8; i++){
            for(int j = 0; j < 6; j++){
               dice[i][j] = new ImageIcon(
                  ImageIO.read(
                     new File (String.format("./resources/dice/%d%d.png", i, j))));
            }
         }
      } catch (IOException e){
         e.printStackTrace();
         System.exit(1);
      }



   }
   // get corresponding card image
   public ImageIcon getCard(int i) {
      return cards[i];
   }
   
   // get corresponding die image
   public ImageIcon getDie(int i, int j) {
      return dice[i][j];
   }
   
   // get background image
   public ImageIcon getBackground() {
      return background;
   }

   // get image of scene card back
   public ImageIcon getCardBack() {
      return cardBack;
   }

   // get shot counter image
   public ImageIcon getShotCounter() {
      return shotCounter;
   }

   // return singleton instance
   public static Resources getInstance() {
      if (instance == null) {
         instance = new Resources();
      }
      return instance;
   }
}
