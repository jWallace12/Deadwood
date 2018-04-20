package model;


import java.util.*;
import java.io.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
*  DeadWood Assignment
*  Created by: Jonah Wallace and Trevor Glass
*  Date Created: Friday May 5, 2017
*
*  CS345 Spring 2017
*  Professor: Moushumi Sharmin
*
*  Class name is Deadwood, previously Board Class
*/

public class DeadWood {

   private static class Closer extends WindowAdapter {
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   }

   private static int numPlayers;
   private static int numDays;
   private static Room rooms[];
   private static Scene scenes[];
   private static Player players[];
   private static int playerIndex = 0;
   private static int scenesRemaining = 10;
   private static boolean firstDay = true;

   public DeadWood(int numPlayers) {
      rooms = new Room[12];
      rooms[0] = new Trailer();
      rooms[1] = new CastingOffice();
      rooms[2] = new Location( 3, 1, "Clumsy Pit Fighter", "Hit me!", 2, "Thug with Knife", "Meet Suzy, my murderin' knife", 3, "Dangerous Tom",
         "There's two ways we can do this....", 4, "Penny, who is Lost", "Oh, woe! For I am lost!", "extra", 2);
      rooms[3] = new Location( 2, 1, "Shot in Leg", "Ow! Me Leg!", 2, "Saucy Fred", "That's what she said!", 3, "Man Under Horse", "A little help here!", "extra", 3);
      rooms[4] = new Location( 1, 2, "Suspicious Gentleman", "Could you be more specific?", 3, "Flustered Teller", "Would you like a large bill, sir?", "extra", 4);
      rooms[5] = new Location( 3, 1, "Faro Player", "Hit me!", 1, "Sleeping Drunkard", "Zzzzzzz... Whiskey!", 2, "Fall from Balcony", "Arrrgghh!!", 3, "Austrailian Bartender",
         "What'll it be mate?", "extra", 5);
      rooms[6] = new Location( 3, 1, "Dragged by Train", "Omgeezers!!", 1, "Crusty Prospector", "Aww, peaches!", 2, "Preacher with Bag", "The Loard shall provide",
         4, "Cyrus the Gunfighter", "Git to fightin' or git away!", "extra", 6);
      rooms[7] = new Location( 1, 2, "Prisoner in Cell", "Zzzzzzz... Whiskey!", 3, "Feller in Irons", "Ah kilt the wrong man!", "extra", 7);
      rooms[8] = new Location( 2, 1, "Man in Overalls", "Looks like a storm's comin' in", 3, "Mister Keach", "Howdy, stranger", "extra", 8);
      rooms[9] = new Location( 2, 1, "Dead Man", "....", 2, "Crying Woman", "Oh, the humanity!", "extra", 9);
      rooms[10] = new Location( 2, 2, "Woman in Red Dress", "Come up and see me!", 1, "Reluctant Farmer", "I ain't too sure about that!", "extra", 10);
      rooms[11] = new Location( 3, 1, "Railroad Worker", "I'm still drivin' man!", 2, "Falls off Roof", "Aiiiigggghhhhh!!", 2, "Woman in Black Dress",
         "Well, I'll be!", 4, "Mayor McGinty", "People of Deadwood!", "extra", 11);
         
      setAdj(rooms[0],"Trailer", rooms[5], rooms[10], rooms[11]);
      setAdj(rooms[1],"Casting Office", rooms[6], rooms[3], rooms[2]);
      setAdj(rooms[2],"Secret Hideout", rooms[1], rooms[3], rooms[9]);
      setAdj(rooms[3],"Ranch", rooms[2], rooms[1], rooms[8], rooms[4]);
      setAdj(rooms[4],"Bank", rooms[5], rooms[9], rooms[3], rooms[10]);
      setAdj(rooms[5],"Hotel", rooms[4], rooms[0], rooms[9]);
      setAdj(rooms[6],"Train Station", rooms[7], rooms[8], rooms[1]);
      setAdj(rooms[7],"Jail", rooms[8], rooms[6], rooms[11]);
      setAdj(rooms[8],"General Store", rooms[7], rooms[6], rooms[3], rooms[10]);
      setAdj(rooms[9],"Church", rooms[2], rooms[4], rooms[5]);
      setAdj(rooms[10],"Saloon", rooms[8], rooms[0], rooms[11], rooms[4]);
      setAdj(rooms[11],"Main Street", rooms[10], rooms[0], rooms[7]);
      initializeScenes();
      setNumPlayers(numPlayers);
      
      
      
      int sceneIndex = Math.abs((numDays * 10) - 40);
      for (int i = 2; i < rooms.length; i++) {
         rooms[i].setScene(scenes[sceneIndex]);
         rooms[i].resetSceneFlipped();
         rooms[i].resetShotCounter();
         sceneIndex++;
      }
   
   }
   

   // function to set adjacent rooms
   public static void setAdj(Room targetRoom, String name, Room adjRoom1, Room adjRoom2, Room adjRoom3){
      targetRoom.setNumRooms(name, 3, adjRoom1, adjRoom2, adjRoom3);
   }

   // function to set adjacent rooms
   public static void setAdj(Room targetRoom,String name, Room adjRoom1, Room adjRoom2, Room adjRoom3, Room adjRoom4){
      targetRoom.setNumRooms(name, 4, adjRoom1, adjRoom2, adjRoom3, adjRoom4);
   }


   // set number of players
   public static void setNumPlayers(int inputPlayers) {
      numPlayers = inputPlayers;
      numDays = 4;

      if (numPlayers < 2 || numPlayers > 8){
         System.out.println("Wrong numbers of players: Quitting...");
         System.exit(1);
      }
      else if (numPlayers == 3 || numPlayers == 2) {
         numDays = 3;
      }
      players = new Player[numPlayers];
      initializePlayers(numPlayers);
   }

   // set player attribute based on the number of players playing
   public static void initializePlayers(int numPlayers){

      // name each of the players
      for(int i=0; i < numPlayers; i++){
         if (numPlayers <= 4) {
            players[i] = new Player(0, 1, i);
         }
         else if (numPlayers == 5) {
            players[i] = new Player(2, 1, i);
         }
         else if (numPlayers == 6) {
            players[i] = new Player(4, 1, i);
         }
         else {
            players[i] = new Player(0, 2, i);
         }
      }
   }

   // create the scene cards
   public static void initializeScenes() {

      // initialize all of the scenes, along with their associated roles
      scenes = new Scene[40];
      scenes[0] = new Scene("Evil Wears a Hat", "Calhoun is seperated from the group during a white-knuckle "
         + "chase near Desperation Bluff", 4, 7, 2, "Defrocked Priest", "Look about below!", 3, "Marshal Canfield", "Hold fast!", 4, "One-Eyed Man", "Balderdash!", "star", 1, 0, 1, 2);
      scenes[1] = new Scene("Square Deal City", "Douglas and Katherine confront Aunt Martha about her missing pies. "
         + "Devin sulks quietly in a side room.", 6, 14, 2, "Squeaking Boy", "I'll say!", 4, "Pharaoh Imhotep", "Attack soldiers!", 6, "Aunt Martha", "You got nothin'!", "star", 6, 0, 1, 2);
      scenes[2] = new Scene("Law and the Old West", "Charlie 'Three Guns' Henderson cooperates with Johnny Lae and reluctantly"
         + " enters the witless protection progam.", 3, 20, 1, "Rug Merchant", "Don't leave my store!", 2, "Banker", "Trust me.", 5, "Talking Mule", "Nice work, Johnny!", "star", 2, 0, 1, 2);
      scenes[3]= new Scene("Davy Crockett: A Drunkard's Tale", "Robert enlists the aid of several farm animals in order to"
         + " ascertain the efficacy of his new hangover remedy.", 4, 31, 4, "The Duck", "Waaaak!", 6, "His Brother", "Waaaaaaaak!", "star", 7, 0, 1);
      scenes[4]= new Scene("The Life and Times of John Skywater", "Disheartened by his lack of business acumen and his poor choice "
         + "of investment parteners. John Skywater set off into the Cree Nation to convince them to kidnap his wife.", 5, 22, 5, "Auctioneer", "Going once!", 6, "General Custer", "Go West!", "star", 3, 0, 1);
      scenes[5]= new Scene("The Way the West Was Run","Jose explains patiently, but with thinly veiled contempt, the intricacies of "
         + "Arizona bureaucracy, as thoguh speaking to a simple and distracted child.", 2, 34, 2, "Town Drunk", "Even me!", 4, "Squinting Miner", "Sure we can!",
         5, "Poltergeist", "Wooooo!", "star", 8, 0, 1, 2);
      scenes[6]= new Scene("My Years on the Prairie", "Virgil and Stacey set out at midnight to track down the stray coes, unaware that "
         + "they are being pursued by inch-high aliens from outer space.", 5, 32, 3, "Drunk", "Where's Willard", 4, "Librarian", "Shhhhhh!", 6, "Man with Hay", "Hey!", "star", 4, 0, 1, 2);
      scenes[7]= new Scene("Down in the Valley", "A tripped waiter is the spark igniting a brawl of cataclysimic proportions. "
         + "Walter is injured in the neck.", 3, 24, 1, "Angry Barber", "Hold him still!", 3, "Woman with Beard", "Nonsense, Frank", 5, "Man on Fire", "It burns!", "star", 9, 0, 1, 2);
      scenes[8]= new Scene("Buffalo Bill: The Lost Years", "Buffalo Bill's companion Marty disappears in a freak electrical storm. "
         + "Bill enlists the aid of the Sidekick Friends Network.", 4, 12, 2, "Hollering Boy", "Over here, mister!", 3, "Drunk Farmer", "Git outta me barn!",
         5, "Meek Little Sarah", "He's so cute!", "star", 5, 0, 1, 2);
      scenes[9]= new Scene("Ol' Shooter and Little Doll", "Shooter discovers that he has been proceding for days with no trousers. "
         + "This causes him no small embarrassment as he searches for them with Little Doll.", 4, 14, 1, "Sleeping Man", "Snnkkk snnkk snnkk.", 2, "Man with Pig", "Tally-Hoo!",
         4, "Shooter", "Where's my britches?", "star", 10, 0, 1, 2);
      scenes[10]= new Scene("The Robbers of Trains", "Coogan fconfronts the toughest thug in his gang. Big Jake, in an abbreviated "
         + "knife fight. Coogan settles the dispute with fearless guile and a kick in the family jewels.", 4, 19, 1, "Buster", "One two three go!", 4, "Man Reading Paper", "Ouchie!",
         5, "Fat Pete", "Nice kick, boss!", "star", 11, 0, 1, 2);
      scenes[11]= new Scene("Jesse James: Man of Action", "Jesse's brother Jed and Henry throw him a suprise birthday party. "
         + "Jesse's nerves get the better of him when the birthday cake explodes.", 5, 8, 2, "Shot in Back", "Arrrgghh!", 4, "Shot in Leg", "Ooh, Lordy", 5, "Leaps Into Cake",
         "Dangit, Jesse!", "star", 16, 0, 1, 2);
      scenes[12]= new Scene("Beyond the Pail: Life without Lactose", "Henry discovers for the first time that his ability to "
         + "digest cream has disappeared along with his hair. Other cowboys attempt to console him.", 2, 12, 6, "Martin", "Have you tried soy cheese?", "star", 12, 0);
      scenes[13]= new Scene("Disaster at Flying J", "After the mine explosion, the traveleing circus takes time out to get "
         + "drunk and start a fight", 5, 6, 2, "Piano Player", "It's a nocture!", 3, "Man in Turban", "My stars!", 4, "Falls on Hoe", "Ow!", "star", 17, 0, 1, 2);
      scenes[14]= new Scene("A Man Called 'Cow'", "Nothing will settle the debates amond the skeptical locals, short of a "
         + "demonstration of Hector's speacial talent", 3, 16, 3, "Preacher", "My word!", 6, "Amused Witness", "Tee hee hee!", "star", 13, 0, 1);
      scenes[15]= new Scene("Shakespeare in Lubbock", "Willliam decides that it is time to be movin' on. Julia convinces him "
         + "to stick around just long enough to get into big trouble", 3, 23, 1, "Falls from Tree", "What ho!", 3, "Laughing Woman", "Tis to laugh!",
         4, "Man with Whistle", "Tweeeeet!", "star", 18, 0, 1, 2);
      scenes[16]= new Scene("Taffy Commercial", "Jackson encourages the children to eat only taffy, becuase gum can kill them stone dead.", 2, 2, 3, "Curious Girl", "Are you sure?",
         4, "Ghost of Plato", "It happened to me!", "star", 14, 0, 1);
      scenes[17]= new Scene("Go West, You!", "Susan and Peter encounter some of the perils of the Badlands: rutted mud roads, "
         + "torrential rain storms, and a bad of the 'grumble tummy'.", 3, 30, 4, "Ex-Convict", "Never again!", 6, "Man with Onion", "Fresh onions!", "star", 19, 0, 1);
      scenes[18]= new Scene("Gum Commercial", "Inspector Pete speaks to a riveted audience about the many hidden dangers of taffy, "
         + "not the least of which is that taffy can kill you stone dead.", 2, 3, 2, "Surprised Bison", "Mmrrrrrrph!", 4, "Man with Horn", "Ta daaaa!", "star", 15, 0, 1);
      scenes[19]= new Scene("The Life and Times of John Skywater", "John discovers his long lost sister. Marcie, and instructs her "
         + "in the ways of gunfighting and whiskey distrillation.", 5, 15, 3, "Staggering Man", "You never know!", 5, "Woman with Beer", "Howdy stranger!",
         6, "Marcie", "Welcome home!", "star", 20, 0, 1, 2);
      scenes[20]= new Scene("Gun! The Musical", "A song and dance extravaganza, 'Hunka Hunka Burnin' Lead.", 6, 25, 4, "Looks like Elvis", "Thankyouverymuch",
         5, "Singing Dead Man", "Yeah!", 6, "Apothecary", "Such drugs I have", "star", 21, 0, 1, 2);
      scenes[21]= new Scene("One False Step for Mankind", "After a dozen failed attempts, one rocket carries Horatio and his six "
         + "children to the Moon, where they enjoy a picnic and a spirited game of badminton.", 6, 21, 1, "Flustered Man", "Well, I never!", 2, "Space Monkey",
         "Ook!", 5, "Cowbot Dan", "Bzzzzzt!", "star", 26, 0, 1, 2);
      scenes[22]= new Scene("Humor at the Expense of Others", "Phil and his cohort of unfeeling smart-mouths make fun of Sancho and his great big hat.",
         5, 16, 2, "Jailer", "You there!", 4, "Mephistophele", "Be not afraid!", 5, "Breaks a Window", "Oops!", "star", 22, 0, 1, 2);
      scenes[23]= new Scene("Thirteen the Hard Way", "After some delay, the Pony Express arrives. "
         + "Isaac, Gwen, Francis, Terry, Conrad, Brooke, Jerry, Howard, MacNeill,Jones, Spike, Cornwall, Crawford are all there.", 5, 15, 1, "Man in Poncho",
         "Howdy, Jones!", 3, "Ecstatic Housewife", "This is fine!", 5, "Isaac", "The mail!", "star", 27, 0, 1, 2);
      scenes[24]= new Scene("The Search for Maggie White", "Alone in the Wilderness, Maggie makes the best of her situation. "
         + "In what seems like no time at all, she contructs  a sturdy two-story house from branches and mud", 6, 12, 5, "Film Critic", "Implausible!", 6, "Hobo with Bat",
         "Nice house!", "star", 23, 0, 1);
      scenes[25]= new Scene("How They Get Milk", "Josie asks the Milkman how they get milk, After a thoughtful pause, he begins. 'Not like you'd expect!'", 4, 2, 2,
         "Cow", "Moo.", 3, "St. Clement of Alexandria", "Peace be with you, child!", 4, "Josie", "Yikes!", "star", 28, 0, 1, 2);
      scenes[26]= new Scene("Picante Sauce Commercial", "A dozen grizzled cowboys surround a fire. Suddenly, they exclaim, 'That's not my mayonnasie!'", 2, 1, 3,
         "Bewhisker'd Cowpoke", "Oh, sweet Lord!", 5, "Dog", "Wurf!", "star", 24, 0, 1);
      scenes[27]= new Scene("My Years on the Prairie","Louise takes instructions from Henry, the neighbor boy, in an absurdly "
         + "suggestive explaination of how to plow a field.", 5, 27, 2, "Willard", "Ain't that a sight?", 3, "Leprechaun", "Begorrah!", 5, "Startled Ox", "Mrrh?", "star", 29, 0, 1, 2);
      scenes[28]= new Scene("Jesse James: Man of Action", "A hail of gunfire results when Jesse's friend Barton marries Jesse's childhood sweetheart.", 5, 14, 1,
         "Shot in Head", "Arrgh", 4, "Leaps Out of Cake", "Oh, for Pete's sake!", 6, "Shot Three Times", "Ow! Ow! Ow!", "star", 25, 0, 1, 2);
      scenes[29]= new Scene("Davy Crockett: A Drunkard's Tale", "In an absurb dream sequence. Crockett recalls an episode "
         + "of fear and chaos in which his childhood friend Timmy was trapped at the bottom of a well.", 4, 12, 2, "Voice of God", "Grab hold, son!", 3, "Hands of God",
         "!", 4, "Jack Hemp", "America!", "star", 30, 0, 1, 2);
      scenes[30]= new Scene("Czechs in the Sonora", "Bob reverts to his ancestral ways in a short fight over a disembodied hand", 4, 25, 5, "Opice (Monkey)", "Uuk! (ook!)",
         6, "Man with a Gun", "Hold it right there!", "star", 31, 0, 1);
      scenes[31]= new Scene("J. Robert, Man of Substances", "Horace and Mathilde discover that the mysterious orange powder filling "
         + "Doctor Lucky’s air vents is neither Agent Orange nor weaponized Tang, but a rare form of cheese mold.", 4, 13, 1, "Man with Rope", "Look out below!",
         2, "Svetlana", "Says who?", 5, "Accidental Victim", "Ow! My spine!", "star", 36, 0, 1, 2);
      scenes[32] = new Scene("Swing 'em Wide", "Hector makes a surprising discovery behind the Chinese grocery store.", 6, 35, 3, "Liberated Nun", "Let me have it!", 5,
         "Witch Doctor", "Oogie Boogie!", 6, "Voice of Reason", "Come on, now!", "star", 33, 0, 1, 2);
      scenes[33] = new Scene("Swing 'em Wide", "Black Jack invites Dixon and the Captain to a late-night poker game. Little do they"
         + "know that Gertrude and Isabella await them at the table.", 6, 19, 1, "Thrifty Mike", "Call!", 3, "Sober Physician", "Raise!", 5, "Man on Floor", "Fold!", "star", 32, 0, 1, 2);
      scenes[34] = new Scene("Thirteen the Hard Way", "After operating for only six minutes, the Pony Express disbands and gives "
         + "way to the international Telegraph and Railroad Systems. Little boys cry.", 5, 17, 2, "Very Wet Man", "Sheesh!", 4, "Dejected Housewife", "Its time has come",
         5, "Man with Box", "Progress!", "star", 37, 0, 1, 2);
      scenes[35] = new Scene("How They Get Milk", "Josie is thoroughly off milk at this point. The Milkman shows her one more way that she might not have heard before.",
         4, 8, 4, "Marksman", "Pull!", 5, "Postal Worker", "It's about time!", 6, "A Horse", "Yes sir!", "star", 38, 0, 1, 2);
      scenes[36] = new Scene("How the Grinch Stole Texas", "The doe-eyed citizens of El Paso gather together around a warm fire "
         + "and pray for the safety of those poor souls in Oklahoma. It almost works.", 5, 9, 3, "Detective", "I have a hunch", 4, "File Clerk", "My stapler!", 5, "Cindy Lou", "Dear Lord!", "star", 35, 0, 1, 2);
      scenes[37] = new Scene("Trails of  the First Pioneers", "A fire breaks out in the town livery. Before long, the surrounding buildings "
         + "are engulfed in flame. The world falls into chaos", 4, 5, 2, "Burning Man", "Make it stop!", 4, "Cheese Vendor", "Opa!", 5, "Hit with a Table", "Ow! A table!", "star", 34, 0, 1, 2);
      scenes[38] = new Scene("Breakin' in Trick Ponies", "Uncle Stewart reveals what to do when all else fails", 3, 19, 2, "Fraternity Pledge", "Beer me!", 6, "Man with sword",
         "None shall pass!", "star", 39, 0, 1);
      scenes[39] = new Scene("Guster's Other Stands", "General George Armstrong Custer clinches another victoy at "
         + "the battle of Little Sands. His trusty steed Cairo is not so lucky.", 5, 40, 2, "Farmer", "Git off that!", 4, "Exploding Horse", "Boom!", 6, "Jack", "Here we go again!", "star", 40, 0, 1, 2);

      // shuffle the scenes
      List<Scene> list =  Arrays.asList(scenes);
      Collections.shuffle(list);
      scenes = list.toArray(new Scene[list.size()]);
   }

   public static void initializeDay() {
      if (!(firstDay)) {
         for (int i = 2; i < 12; i++) { 
            view.Board.locations[i].resetShots();
         }
      }
      // move the players back to the Trailer, and reset all
      // of their scenes, rolls, and rehearsalCounts
      for (int i = 0; i < players.length; i++) {
         players[i].move(rooms[0]);
         players[i].takeRole(null, null);
         players[i].resetRehearsalCount();
      }

      // give all the locations new scenes
      int sceneIndex = Math.abs((numDays * 10) - 40);
      for (int i = 2; i < rooms.length; i++) {
         rooms[i].setScene(scenes[sceneIndex]);
         rooms[i].resetSceneFlipped();
         rooms[i].resetShotCounter();
         if (!(firstDay)) {
            view.Board.locations[i].getScene().getCard().setVisible(true);
            view.Board.resetCards(view.Board.locations[i].getScene().getCard());
            //view.Board.changeCard(view.Board.locations[i].getScene().getCard(), rooms[i].getScene().getImg());
         }
         sceneIndex++;
      }
      scenesRemaining = 10;
      firstDay = false;

   }


   public static void declareWinner() throws Exception {
   Thread.sleep(1000);
      int playerValues[] = new int[players.length];

      // display and save each player's final score
      for (int i = 0; i < playerValues.length; i++) {
         Player currPlayer = players[i];
         playerValues[i] = (currPlayer.getCredits()) + (currPlayer.getDollars()) + (currPlayer.getRank() * 5);
      }

      int maxPlayerIndex = 0;
      int maxValue = 0;
      Player winner;

      // find the player with the highest score
      for (int i = 0; i < players.length; i++) {
         if (playerValues[i] > maxValue) {
            maxPlayerIndex = i;
            maxValue = playerValues[i];
         }
      }
      
      view.Board.endGame(maxPlayerIndex);

   }


   // go to the next player
   public static void nextTurn() throws Exception {
      // find the player for the current turn
      Player currPlayer = players[playerIndex];
      view.Board.changeDie(currPlayer);
   }

   // check if a player can rehearse
   public static boolean checkRehearse(Player currPlayer){
      // check if the player is in a role
      if(currPlayer.getRole() == null){
         return false;
      }
      // check if the player is at the point of guaranteed success for acting
      if(currPlayer.getRehearsalCount() >= (currPlayer.getScene().getBudget() - 1)){
         return false;
      }
      // increment the player's rehearsal count
      currPlayer.incrementRehearsalCount();
      return true;
   }

   // function that tries to move player to new room
   public static boolean move(Player currPlayer, String requestedLocation){
      // if player is in a current role, it can't move
      if(currPlayer.getRole() != null){
         return false;
      }
      Room roomChoices[] = currPlayer.getRoom().getAdjRooms();
      Room currRoom = currPlayer.getRoom();
      // iterate through adjacent rooms, and see if any match the specified location
      for (int i = 0; i < roomChoices.length; i++) {
         if (requestedLocation.equals(roomChoices[i].getName())) {
            currPlayer.move(roomChoices[i]);
            if(roomChoices[i] instanceof Location){
                if (!(roomChoices[i].getFlippedScene())){
                   roomChoices[i].getScene().activeScene();
                   roomChoices[i].flipSceneCard();
                }
            try {
                 controller.Board.chosenWork(currPlayer);
            } catch (Exception e) {
              System.out.println("Oops! Exception caught");
            }  
            return true;
            }
         }
      }
      // move failure
      return false;
   }
   
   public static void act(Player currPlayer) throws Exception {
      if (currPlayer.getRole() != null) {
         if ((currPlayer.rollDie() + currPlayer.getRehearsalCount()) >= currPlayer.getScene().getBudget()) {
            //System.out.println("Acting was a success!");
            currPlayer.getRoom().decrementShotCounter();
            if (currPlayer.getRole().getType().equals("extra")) {
               //System.out.println("Received extra role pay.");
               currPlayer.changeDollars(1);
               currPlayer.changeCredits(1);
            }
            else {
               //System.out.println("Received starring role pay.");
               currPlayer.changeCredits(2);
            }
   
            if (currPlayer.getRoom().getShotCounter() == 0){
               //System.out.println("Congrats! Scene over!");
               payBonus((Location)currPlayer.getRoom());
               endScene(currPlayer);
               scenesRemaining--;
               if (scenesRemaining == 1) {
                  numDays--;
                  if (numDays == 0) {
                     declareWinner();
                  } else {
                  System.out.println("Time for a new day!");
                     initializeDay();
                  }
                  //System.out.println("Day over. " + numDays + " days remaining.");
                  return;
               }
            }
         } else {
            //System.out.println("Acting failure!");
            if (currPlayer.getRole().getType().equals("extra")) {
               //System.out.println("Received extra role failure pay.");
               currPlayer.changeDollars(1);
            }
         }
      } else {
         //System.out.println("Not a valid move");
      }
   }

   // check if player can take a role
   public static boolean checkWork(Player currPlayer, String requestedRole){
      // player can't take a role if they aren't in a Location
      if (!((currPlayer.getRoom()) instanceof Location)) {
         return false;
      }
      Location currLocation = ((Location)currPlayer.getRoom());
      // player is already in a role, fail
      if (currPlayer.getRole() != null) {
         return false;
      }
      // scene is already over, fail
      if ((currLocation.getShotCounter()) == 0) {
         return false;
      }
      Role checkExtras[] = currLocation.getExtraRoles();

      // iterate through the extra roles, and see if any match the specified role
      for (int i = 0; i < checkExtras.length; i++) {
         if (requestedRole.equals(checkExtras[i].getName())) {
            if ((checkExtras[i].getPlayerOn() == null) && (currPlayer.getRank() >= checkExtras[i].getRank())) {
               currPlayer.takeRole(currLocation.getScene(), checkExtras[i]);
               checkExtras[i].setPlayerOn(currPlayer);
               return true;
            }
         }
      }
      Role checkStars[] = currLocation.getScene().getStarringRoles();
      // iterate through the starring roles, and see if any match the specified role
      for (int i = 0; i < checkStars.length; i++) {
         if (((requestedRole.equals(checkStars[i].getName())) && (currPlayer.getRank() >= checkStars[i].getRank()))) {
            if (checkStars[i].getPlayerOn() == null) {
               currPlayer.takeRole(currLocation.getScene(), checkStars[i]);
               checkStars[i].setPlayerOn(currPlayer);
               return true;
            }
         }
      }
      // no roles matched, fail
      return false;
   }

   // pay bonus for end of scene
   public static void payBonus(Location location) {
      // extract all of the roles in the location
      Role[] stars = location.getScene().getStarringRoles();
      Role[] extras = location.getExtraRoles();
      boolean checker = false;
      // check if there are any starring roles taken.
      // if there is not, don't pay out a bonus
      for (int i = 0; i < stars.length; i++) {
         if (stars[i].getPlayerOn() != null) {
            checker = true;
            break;
         }
      }
      // return if there were no active star roles taken
      if (!(checker)) {
         System.out.println("There is no bonus to give.");
         return;
      } else {
         System.out.println("Bonus Rewarded to Star Role Players");
         // pay each working extra the dollar amount equal to the rank of their role
         for (int i = 0; i < extras.length; i++) {
            if (extras[i].getPlayerOn() != null) {
               extras[i].getPlayerOn().changeDollars(extras[i].getRank());
            }
         }
         // pay out star roles

         // Sort from greatest to largest
         for(int j = 1; j < stars.length; j++){
            Role key = stars[j];
            int i = j - 1;
            while((i >= 0) && (stars[i].getRank() < key.getRank())) {
               stars[i+1] = stars[i];
               i--;
               stars[i+1] = key;
            }
         }

         // starting with the highest ranking role, have each role roll the die, and play the role
         // if a player is on the specified role
         int budget = location.getScene().getBudget();
         int i = 0;
         for (int j = 0; j < budget; j++) {
            if (stars[i].getPlayerOn() != null) {
               int roll = stars[i].getPlayerOn().rollDie();
               stars[i].getPlayerOn().changeDollars(roll);
               //System.out.println(stars[i].getPlayerOn().getName() + " earned $" + roll + "!");
            }
            i++;
            if (i == stars.length) {
               i = 0;
            }
         }

      }
   }

   // find the price for the currency type and requested rank
   public static int getUpgradePrice(String sym, int requestedRank){

      // initialize prices
      int[][] ranks = new int[5][2];
      ranks[0][0] = 4;
      ranks[0][1] = 5;
      ranks[1][0] = 10;
      ranks[1][1] = 10;
      ranks[2][0] = 18;
      ranks[2][1] = 15;
      ranks[3][0] = 28;
      ranks[3][1] = 20;
      ranks[4][0] = 40;
      ranks[4][1] = 25;

      // check the parameters pass, and return the correct price
      if ((requestedRank > 1) && (requestedRank < 7)) {
         if (sym.equals("$")) {
            return ranks[requestedRank-2][0];
         } else if (sym.equals("cr")) {
            return ranks[requestedRank-2][1];
         } else {
            return -1;
         }
      } else {
         System.out.println("Requested rank was not within the appropriate range.");
         return -1;
      }
   }

   // try to upgrade player with specified currency and rank
   public static boolean upgradeRank(Player person, String symbol, String request){
   /* Check if players rank up rather than ranking down */
      try {
         int rankRequest = Integer.parseInt(request);
         if (person.getRank() > rankRequest){
            System.out.println("Your current rank is greater than your request! Try again!");
            return false;
         }
         else {
         /* Handle player using dollars as currenecy */
            int price = getUpgradePrice(symbol, rankRequest);
            if (price == -1) {
               System.out.println("Your form of currency was invalid.");
               return false;
            }
            if (symbol.equals("$")){
               /* Check if player has enough money */
               if (person.getDollars() >= price){
                  person.changeDollars(-price);
                  person.rankUp(rankRequest);
                  return true;
               } else {
                  return false;
                  //System.out.println(person.getName() + "has insufficent amount of dollars");
               }
               /* Handle player using credit as currency */
            } else if (symbol.equals("cr")) {
               if (person.getCredits() >= price){
                  person.changeCredits(-price);
                  person.rankUp(rankRequest);
                  return true;

               } else {
                  return false;
                  //System.out.println(person.getName() + "has insufficent amount of credits");
               }
            }
         }
      } catch (NumberFormatException nfe) {
         System.out.println("Requested rank was not a valid number");
         return false;
      }
      return false;
   }

   // reinitialize player statistics at the end of a scene
   public static void endScene(Player person){
      Role role = person.getRole();
      view.Board.flipCard(person.getRoom().getIndex());

      person.getRoom().getScene().inactiveScene();
      int currSceneNum = person.getScene().getSceneNumber();
      for (int i = 0; i < players.length; i++) {
         if ((players[i].getScene() != null) && (players[i].getScene().getSceneNumber() == currSceneNum)) {
            players[i].getRole().setNull();
            players[i].takeRole(null, null);
            players[i].resetRehearsalCount();
            players[i].move(players[i].getRoom());
         }
      }
      view.Board.locations[person.getRoom().getIndex()].getScene().resetScenes();
      view.Board.locations[person.getRoom().getIndex()].resetExtras();
      
      

   }

   // assign player to role
   public static boolean processWork(Player person, String[] splitAnswer){
      if (splitAnswer.length > 1) {
         String requestedRole = "";
         for(int i = 1; i < splitAnswer.length; i++) {
            if (i == (splitAnswer.length-1)) {
               requestedRole += splitAnswer[i];
            }
            else {
               requestedRole += splitAnswer[i] + " ";
            }
         }

         // check if its valid for player to taken specified role
         if(checkWork(person, requestedRole)){
            System.out.println("Congrats! " + person.getRole().getName() + " taken");
            return true;
         } else {
            System.out.println("Role cannot be taken");
            return false;
         }
      } else {
         System.out.println("Not a valid command.");
         return false;
      }
   }
   
   public static Room[] getRooms() {
      return rooms;
   }
   
   public static Player getPlayer(int i) {
      return players[i];
   }
   
   public static int getNumPlayers() {
      return numPlayers;
   }
   
   public static int getPlayerIndex() {
      return playerIndex;
   }
   
   public static void incrementPlayerIndex() {
      playerIndex++;
      if (playerIndex == players.length) {
         playerIndex = 0;
      }
   }
}
