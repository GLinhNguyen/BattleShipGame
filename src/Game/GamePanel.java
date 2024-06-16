<<<<<<< Updated upstream
// package Game;
// import MessageStatus.*;
// import Functions.*;
// import BotThings.*;

// import java.awt.event.KeyEvent;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
// import java.awt.event.MouseMotionListener;
// import javax.swing.*;
// import javax.swing.plaf.TextUI;
// import javax.swing.text.Position;

// import java.awt.*;



// public class GamePanel extends JPanel implements MouseListener, MouseMotionListener{

//    // Initialize the game state, which will be used to determine what the player is currently doing

//    // Show a panel to let user know what state they are in


//    public StatusPanel statusPanel;

//    // Computer's grid
//    private SelectionGrid computer;

//    // Functions.Player's grid

//    private GameState currentState;
//    private SelectionGrid player;
//    private Bot aiController;
//     private Location tempPlacingPosition;
//     private int placingShipIndex;
//     public static boolean debugModeActive;
//     private Ship placingShip;

//     private GameState gameState;


//     public GamePanel(int aiChoice) {
//        currentState = GameState.PLACING_SHIPS;
//        setBackground(new Color(42, 136, 163));
//        setPreferredSize(new Dimension(800, 600)); // Set to your desired dimensions

//        computer = new SelectionGrid(0, 0);
//        player = new SelectionGrid(0, computer.getWidth() + 50);

//        if (aiChoice == 0) {
//            aiController = new EasyBot(player);
//        } else {
//            aiController = new NightmareBot(player, aiChoice == 2, aiChoice == 2);
//        }

//        statusPanel = new StatusPanel(new Location(0, computer.getHeight() + 1), computer.getWidth(), 49);
//          // Add statusPanel to the top of the panel
//          add(new JLabel() , BorderLayout.NORTH);

//          // Add computer and player grids to the center of the panel
//          JPanel centerPanel = new JPanel(new SelectionGrid(1, 2));
//          add(centerPanel, BorderLayout.CENTER);
//          add(centerPanel, BorderLayout.CENTER);
//        addMouseListener(this);
//        addMouseMotionListener(this);
//        restart();
//    }

//     public static boolean debugModeActive() {
//         return debugModeActive;

//     }

//     public void restart() {
//                computer.reset();
//                player.reset();
//                // Player can see their own ships by default
//                player.setShowShips(true);
//                aiController.reset();
//                tempPlacingPosition = new Location(0,0);
//                placingShip = new Ship(new Location(0,0),
//                                       new Location(player.getLocation().x,player.getLocation().y),
//                                       SelectionGrid.boatSize[0], true);
//                placingShipIndex = 0;
//                updateShipPlacement(tempPlacingPosition);
//                computer.populateShips();
//                debugModeActive = false;
//                statusPanel.reset();
//                gameState = GameState.PLACING_SHIPS;
//    }

//    private void updateShipPlacement(Location tempPlacingPosition2) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'updateShipPlacement'");
// }
// public void updateGameState(GameState newState) {
//        switch (currentState) {
//            case PLAYING:
//                if(newState == GameState.GAME_WIN) {
//                    statusPanel.messeg;
//                } else if(newState == GameState.GAME_LOSS) {
//                    statusPanel.getMessage();
//                }
//                break;
//            case PLACING_SHIPS:
//                if(newState == GameState.PLAYING) {
//                    statusPanel.getMessage();
//                }
//                break;
//            case GAME_WIN:
//                //play again
//                break;

//            case GAME_LOSS:
//                //restart the game
//                break;


//        }
//        currentState = newState;
//    }


//    @Override
//    public void mouseDragged(MouseEvent e) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
//    }

//    @Override
//    public void mouseMoved(MouseEvent e) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
//    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
//    }

//    @Override
//    public void mousePressed(MouseEvent e) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
//    }

//    @Override
//    public void mouseReleased(MouseEvent e) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
//    }

//    @Override
//    public void mouseEntered(MouseEvent e) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
//    }

//    @Override
//    public void mouseExited(MouseEvent e) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
//    }
// }


// //   public void startGame() {
// //   // Initialize game components
// //   statusPanel = new StatusPanel(0, 0);
// //   Player playerr = new Player();
// //   computer = new SelectionGrid();
// //
// //   // Start the game loop
// //   while (true) {
// //       // Update the game state
// //       updateGameState(currentState);
// //
// //       // Redraw the game panel
// //       repaint();
// //
// //       // Pause for a short period before the next iteration
// //       try {
// //           Thread.sleep(100);
// //       } catch (InterruptedException e) {
// //           e.printStackTrace();
// //       }
// //   }
// //}
// //}
// /*
// * GamePanel class:
// * Handles all the state information and interaction between game elements.
// * Controls two grids, one for the player, and one for the computer, with a
// * status panel between them. Depending on the game state the player can
// * place ships on their grid, or attack the computer's grid. The status
// * panel shows the current state between the two grids.
// */
// // public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {



// //     /**
// //      * Reference to the status panel to pass text messages to show what is happening.
// //      */
// //     private StatusPanel statusPanel;
// //     /**
// //      * The computer's grid for the player to attack.
// //      */
// //     private SelectionGrid computer;
// //     /**
// //      * The player's grid for the computer to attack.
// //      */
// //     private SelectionGrid player;
// //     /**
// //      * AI to manage what the computer will do each turn.
// //      */
// //     private Bot aiController;

// //     /**
// //      * Reference to the temporary ship that is being placed during the PlacingShips state.
// //      */
// //     private Ship placingShip;
// //     /**
// //      * Grid position where the placingShip is located.
// //      */
// //     private Location tempPlacingPosition;
// //     /**
// //      * Reference to which ship should be placed next during the PlacingShips state.
// //      */
// //     private int placingShipIndex;
// //     /**
// //      * The game state to represent whether the player can place ships, attack the computer,
// //      * or if the game is already over.
// //      */
// //     private GameState gameState;
// //     /**
// //      * A state that can be toggled with D to show the computer's ships.
// //      */
// //     public static boolean debugModeActive;

// //     /**
// //      * Initialises everything necessary to begin playing the game. The grids for each player are initialised and
// //      * then used to determine how much space is required. The listeners are attached, AI configured, and
// //      * everything set to begin the game with placing a ship for the player.
// //      */
// //     public GamePanel(int aiChoice) {
// //         computer = new SelectionGrid(0,0);
// //         player = new SelectionGrid(0,computer.getYCoor()+50);
// //         setBackground(new Color(42, 136, 163));
// //         setPreferredSize(new Dimension(computer.getXCoor(), player.getLocation().y + player.getYCoor()));
// //         addMouseListener(this);
// //         addMouseMotionListener(this);
// //         if(aiChoice == 0) aiController = new EasyBot(player);
// //         else aiController = new NightmareBot(player,aiChoice == 2,aiChoice == 2);
// //         statusPanel = new StatusPanel(new Location(0,computer.getYCoor()+1),computer.getXCoor(),49);
// //         restart();
// //     }



// //     /**
// //      * Draws the grids for both players, any ship being placed, and the status panel.
// //      *
// //      * @param g Reference to the Graphics object for drawing.
// //      */
// //     public void paint(Graphics g) {
// //         super.paint(g);
// //         computer.paint(g);
// //         player.paint(g);
// //         if(gameState == GameState.PLACING_SHIPS) {
// //             placingShip.paint(g);
// //         }
// //         statusPanel.paint(g);
// //     }

// //     /**
// //      * Handles input based on keys that are pressed.
// //      * Escape quits the application. R restarts.
// //      * Z rotates the ship while in PlacingShips state.
// //      * D activates the debug mode to show computer ships.
// //      *
// //      * @param keyCode The key that was pressed.
// //      */
// //     public void handleInput(int keyCode) {
// //         if(keyCode == KeyEvent.VK_ESCAPE) {
// //             System.exit(1);
// //         } else if(keyCode == KeyEvent.VK_R) {
// //             restart();
// //         } else if(gameState == GameState.PLACING_SHIPS && keyCode == KeyEvent.VK_SPACE) {
// //             placingShip.toggleSideways();
// //             updateShipPlacement(tempPlacingPosition);
// //         } else if(keyCode == KeyEvent.VK_D) {
// //             debugModeActive = !debugModeActive;
// //         }
// //         repaint();
// //     }

// //     /**
// //      * Resets all the class's properties back to their defaults ready for a new game to begin.
// //      */
// //     public void restart() {
// //         computer.reset();
// //         player.reset();
// //         // Player can see their own ships by default
// //         player.setShowShips(true);
// //         aiController.reset();
// //         tempPlacingPosition = new Location(0,0);
// //         placingShip = new Ship(new Location(0,0),
// //                                new Location(player.getLocation().x,player.getLocation().y),
// //                                SelectionGrid.boatSize[0], true);
// //         placingShipIndex = 0;
// //         updateShipPlacement(tempPlacingPosition);
// //         computer.populateShips();
// //         debugModeActive = false;
// //         statusPanel.reset();
// //         gameState = GameState.PLACING_SHIPS;
// //     }

// //     /**
// //      * Uses the mouse position to test update the ship being placed during the
// //      * PlacingShip state. Then if the place it has been placed is valid the ship will
// //      * be locked in by calling placeShip().
// //      *
// //      * @param mousePosition Mouse coordinates inside the panel.
// //      */
// //     private void tryPlaceShip(Location mousePosition) {
// //         Location targetPosition = player.getLocationWithinGrid(mousePosition.x, mousePosition.y);
// //         updateShipPlacement(targetPosition);
// //         if(player.canPlaceShipAt(targetPosition.x, targetPosition.y,
// //                 SelectionGrid.boatSize[placingShipIndex],placingShip.isSideways())) {
// //             placeShip(targetPosition);
// //         }
// //     }

// //     /**
// //      * Finalises the insertion of the ship being placed by storing it in the player's grid.
// //      * Then either prepares the next ship for placing, or moves to the next state.
// //      *
// //      * @param targetPosition The position on the grid to insert the ship at.
// //      */
// //     private void placeShip(Location targetPosition) {
// //         placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Placed);
// //         player.placeShip(placingShip,tempPlacingPosition.x,tempPlacingPosition.y);
// //         placingShipIndex++;
// //         // If there are still ships to place
// //         if(placingShipIndex < SelectionGrid.boatSize.length) {
// //             placingShip = new Ship(new Location(targetPosition.x, targetPosition.y),
// //                           new Location(player.getLocation().x + targetPosition.x * SelectionGrid.cellSize,
// //                        player.getLocation().y + targetPosition.y * SelectionGrid.cellSize),
// //                           SelectionGrid.boatSize[placingShipIndex], true);
// //             updateShipPlacement(tempPlacingPosition);
// //         } else {
// //             gameState = GameState.PLAYING;
// //             statusPanel.setTopString("Attack the Computer!");
// //             statusPanel.setBottomString("Destroy all Ships to win!");
// //         }
// //     }

// //     /**
// //      * Attempts to fire at a position on the computer's board.
// //      * The player is notified if they hit/missed, or nothing if they
// //      * have clicked the same place again. After the player's turn,
// //      * the AI is given a turn if the game is not already ended.
// //      *
// //      * @param mousePosition Mouse coordinates inside the panel.
// //      */
// //     private void tryFireAtComputer(Location mousePosition) {
// //         Location targetPosition = computer.getLocationWithinGrid(mousePosition.x,mousePosition.y);
// //         // Ignore if position was already clicked
// //         if(!computer.isLocationMarked(targetPosition)) {
// //             doPlayerTurn(targetPosition);
// //             // Only do the AI turn if the game didn't end from the player's turn.
// //             if(!computer.areAllShipsDestroyed()) {
// //                 doAITurn();
// //             }
// //         }
// //     }

// //     /**
// //      * Processes the player's turn based on where they selected to attack.
// //      * Based on the result of the attack a message is displayed to the player,
// //      * and if they destroyed the last ship the game updates to a won state.
// //      *
// //      * @param targetPosition The grid position clicked on by the player.
// //      */
// //     private void doPlayerTurn(Location targetPosition) {
// //         boolean hit = computer.markLocation(targetPosition);
// //         String hitMiss = hit ? "Hit" : "Missed";
// //         String destroyed = "";
// //         if(hit && computer.getMarkerAtLocation(targetPosition).getAssociatedShip().isDestroyed()) {
// //             destroyed = "(Destroyed)";
// //         }
// //         statusPanel.setTopString("Player " + hitMiss + " " + targetPosition + destroyed);
// //         if(computer.areAllShipsDestroyed()) {
// //             // Player wins!
// //             gameState = GameState.GAME_LOSS;
// //             statusPanel.showGameOver(true);
// //         }
// //     }

// //     /**
// //      * Processes the AI turn by using the AI Controller to select a move.
// //      * Then processes the result to display it to the player. If the AI
// //      * destroyed the last ship the game will end with AI winning.
// //      */
// //     private void doAITurn() {
// //         Location aiMove = aiController.selectMove();
// //         boolean hit = player.markLocation((Location) aiMove);
// //         String hitMiss = hit ? "Hit" : "Missed";
// //         String destroyed = "";
// //         if(hit && player.getMarkerAtLocation(aiMove).getAssociatedShip().isDestroyed()) {
// //             destroyed = "(Destroyed)";
// //         }
// //         statusPanel.setBottomString("Computer " + hitMiss + " " + aiMove + destroyed);
// //         if(player.areAllShipsDestroyed()) {
// //             // Computer wins!
// //             gameState = GameState.GAME_LOSS;
// //             statusPanel.showGameOver(false);
// //         }
// //     }

// //     /**
// //      * Updates the ship being placed location if the mouse is inside the grid.
// //      *
// //      * @param mousePosition Mouse coordinates inside the panel.
// //      */
// //     private void tryMovePlacingShip(Location mousePosition) {
// //         if(player.isLocationWithinCoordinate(mousePosition)) {
// //             Location targetPos = player.getLocationWithinGrid(mousePosition.x, mousePosition.y);
// //             updateShipPlacement(targetPos);
// //         }
// //     }

// //     /**
// //      * Constrains the ship to fit inside the grid. Updates the drawn position of the ship,
// //      * and changes the colour of the ship based on whether it is a valid or invalid placement.
// //      *
// //      * @param tempPlacingPosition2 The grid coordinate where the ship being placed should change to.
// //      */
// //     private void updateShipPlacement(Location tempPlacingPosition2) {
// //         // Constrain to fit inside the grid
// //         if(placingShip.isSideways()) {
// //             tempPlacingPosition2.x = Math.min(tempPlacingPosition2.x, SelectionGrid.gridWidth - SelectionGrid.boatSize[placingShipIndex]);
// //         } else {
// //             tempPlacingPosition2.y = Math.min(tempPlacingPosition2.y, SelectionGrid.gridHeight - SelectionGrid.boatSize[placingShipIndex]);
// //         }
// //         // Update drawing position to use the new target position
// //         placingShip.setDrawPosition(new Location(tempPlacingPosition2),
// //                                     new Location(player.getLocation().x + tempPlacingPosition2.x * SelectionGrid.cellSize,
// //                                  player.getLocation().y + tempPlacingPosition2.y * SelectionGrid.cellSize));
// //         // Store the grid position for other testing cases
// //         tempPlacingPosition = tempPlacingPosition2;
// //         // Change the colour of the ship based on whether it could be placed at the current location.
// //         if(player.canPlaceShipAt(tempPlacingPosition.x, tempPlacingPosition.y,
// //                 SelectionGrid.boatSize[placingShipIndex],placingShip.isSideways())) {
// //             placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Valid);
// //         } else {
// //             placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Invalid);
// //         }
// //     }

// //     /**
// //      * Triggered when the mouse button is released. If in the PlacingShips state and the
// //      * cursor is inside the player's grid it will try to place the ship.
// //      * Otherwise if in the FiringShots state and the cursor is in the computer's grid,
// //      * it will try to fire at the computer.
// //      *
// //      * @param e Details about where the mouse event occurred.
// //      */
// //     @Override
// //     public void mouseReleased(MouseEvent e) {
// //         Location mousePosition = new Location(e.getX(), e.getY());
// //         if(gameState == GameState.PLACING_SHIPS && player.isLocationWithinCoordinate(mousePosition)) {
// //             tryPlaceShip(mousePosition);
// //         } else if(gameState == GameState.PLAYING && computer.isLocationWithinCoordinate(mousePosition)) {
// //             tryFireAtComputer(mousePosition);
// //         }
// //         repaint();
// //     }

// //     /**
// //      * Triggered when the mouse moves inside the panel. Does nothing if not in the PlacingShips state.
// //      * Will try and move the ship that is currently being placed based on the mouse coordinates.
// //      *
// //      * @param e Details about where the mouse event occurred.
// //      */
// //     @Override
// //     public void mouseMoved(MouseEvent e) {
// //         if(gameState != GameState.PLACING_SHIPS) return;
// //         tryMovePlacingShip(new Location(e.getX(), e.getY()));
// //         repaint();
// //     }

// //     /**
// //      * Not used.
// //      *
// //      * @param e Not used.
// //      */
// //     @Override
// //     public void mouseClicked(MouseEvent e) {}
// //     /**
// //      * Not used.
// //      *
// //      * @param e Not used.
// //      */
// //     @Override
// //     public void mousePressed(MouseEvent e) {}
// //     /**
// //      * Not used.
// //      *
// //      * @param e Not used.
// //      */
// //     @Override
// //     public void mouseEntered(MouseEvent e) {}
// //     /**
// //      * Not used.
// //      *
// //      * @param e Not used.
// //      */
// //     @Override
// //     public void mouseExited(MouseEvent e) {}
// //     /**
// //      * Not used.
// //      *
// //      * @param e Not used.
// //      */
// //     @Override
// //     public void mouseDragged(MouseEvent e) {}

// // 	public static boolean debugModeActive() {
// // 		// TODO Auto-generated method stub
// // 		throw new UnsupportedOperationException("Unimplemented method 'debugModeActive'");
// // 	}

// //     public void startGame() {
// //         // TODO Auto-generated method stub
// //         throw new UnsupportedOperationException("Unimplemented method 'startGame'");
// //     }
// // }
=======
//package Game;
//import MessageStatus.*;
//import Functions.*;
//import BotThings.*;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import javax.swing.*;
//import javax.swing.plaf.TextUI;
//import javax.swing.text.Position;
//
//import java.awt.*;
//
//
//
//public class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
//
//   // Initialize the game state, which will be used to determine what the player is currently doing
//
//   // Show a panel to let user know what state they are in
//
//
//   public StatusPanel statusPanel;
//
//   // Computer's grid
//   private SelectionGrid computer;
//
//   // Functions.Player's grid
//
//   private GameState currentState;
//   private SelectionGrid player;
//   private Bot aiController;
//    private Location tempPlacingPosition;
//    private int placingShipIndex;
//    public static boolean debugModeActive;
//    private Ship placingShip;
//
//    private GameState gameState;
//
//
//    public GamePanel(int aiChoice) {
//       currentState = GameState.PLACING_SHIPS;
//       setBackground(new Color(42, 136, 163));
//       setPreferredSize(new Dimension(800, 600)); // Set to your desired dimensions
//
//       computer = new SelectionGrid(0, 0);
//       player = new SelectionGrid(0, computer.getWidth() + 50);
//
//       if (aiChoice == 0) {
//           aiController = new EasyBot(player);
//       } else {
//           aiController = new NightmareBot(player, aiChoice == 2, aiChoice == 2);
//       }
//
//       statusPanel = new StatusPanel(new Location(0, computer.getHeight() + 1), computer.getWidth(), 49);
//         // Add statusPanel to the top of the panel
//         add(new JLabel() , BorderLayout.NORTH);
//
//         // Add computer and player grids to the center of the panel
//         JPanel centerPanel = new JPanel(new SelectionGrid(1, 2));
//         add(centerPanel, BorderLayout.CENTER);
//         add(centerPanel, BorderLayout.CENTER);
//       addMouseListener(this);
//       addMouseMotionListener(this);
//       restart();
//   }
//
//    public static boolean debugModeActive() {
//        return debugModeActive;
//
//    }
//
//    public void restart() {
//               computer.reset();
//               player.reset();
//               // Player can see their own ships by default
//               player.setShowShips(true);
//               aiController.reset();
//               tempPlacingPosition = new Location(0,0);
//               placingShip = new Ship(new Location(0,0),
//                                      new Location(player.getLocation().x,player.getLocation().y),
//                                      SelectionGrid.boatSize[0], true);
//               placingShipIndex = 0;
//               updateShipPlacement(tempPlacingPosition);
//               computer.populateShips();
//               debugModeActive = false;
//               statusPanel.reset();
//               gameState = GameState.PLACING_SHIPS;
//   }
//
//   private void updateShipPlacement(Location tempPlacingPosition2) {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'updateShipPlacement'");
//}
//public void updateGameState(GameState newState) {
//       switch (currentState) {
//           case PLAYING:
//               if(newState == GameState.GAME_WIN) {
//                   statusPanel.messeg;
//               } else if(newState == GameState.GAME_LOSS) {
//                   statusPanel.getMessage();
//               }
//               break;
//           case PLACING_SHIPS:
//               if(newState == GameState.PLAYING) {
//                   statusPanel.getMessage();
//               }
//               break;
//           case GAME_WIN:
//               //play again
//               break;
//
//           case GAME_LOSS:
//               //restart the game
//               break;
//
//
//       }
//       currentState = newState;
//   }
//
//
//   @Override
//   public void mouseDragged(MouseEvent e) {
//       // TODO Auto-generated method stub
//       throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
//   }
//
//   @Override
//   public void mouseMoved(MouseEvent e) {
//       // TODO Auto-generated method stub
//       throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
//   }
//
//   @Override
//   public void mouseClicked(MouseEvent e) {
//       // TODO Auto-generated method stub
//       throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
//   }
//
//   @Override
//   public void mousePressed(MouseEvent e) {
//       // TODO Auto-generated method stub
//       throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
//   }
//
//   @Override
//   public void mouseReleased(MouseEvent e) {
//       // TODO Auto-generated method stub
//       throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
//   }
//
//   @Override
//   public void mouseEntered(MouseEvent e) {
//       // TODO Auto-generated method stub
//       throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
//   }
//
//   @Override
//   public void mouseExited(MouseEvent e) {
//       // TODO Auto-generated method stub
//       throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
//   }
//}
//
//
////   public void startGame() {
////   // Initialize game components
////   statusPanel = new StatusPanel(0, 0);
////   Player playerr = new Player();
////   computer = new SelectionGrid();
////
////   // Start the game loop
////   while (true) {
////       // Update the game state
////       updateGameState(currentState);
////
////       // Redraw the game panel
////       repaint();
////
////       // Pause for a short period before the next iteration
////       try {
////           Thread.sleep(100);
////       } catch (InterruptedException e) {
////           e.printStackTrace();
////       }
////   }
////}
////}
///*
//* GamePanel class:
//* Handles all the state information and interaction between game elements.
//* Controls two grids, one for the player, and one for the computer, with a
//* status panel between them. Depending on the game state the player can
//* place ships on their grid, or attack the computer's grid. The status
//* panel shows the current state between the two grids.
//*/
//// public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
//
//
//
////     /**
////      * Reference to the status panel to pass text messages to show what is happening.
////      */
////     private StatusPanel statusPanel;
////     /**
////      * The computer's grid for the player to attack.
////      */
////     private SelectionGrid computer;
////     /**
////      * The player's grid for the computer to attack.
////      */
////     private SelectionGrid player;
////     /**
////      * AI to manage what the computer will do each turn.
////      */
////     private Bot aiController;
//
////     /**
////      * Reference to the temporary ship that is being placed during the PlacingShips state.
////      */
////     private Ship placingShip;
////     /**
////      * Grid position where the placingShip is located.
////      */
////     private Location tempPlacingPosition;
////     /**
////      * Reference to which ship should be placed next during the PlacingShips state.
////      */
////     private int placingShipIndex;
////     /**
////      * The game state to represent whether the player can place ships, attack the computer,
////      * or if the game is already over.
////      */
////     private GameState gameState;
////     /**
////      * A state that can be toggled with D to show the computer's ships.
////      */
////     public static boolean debugModeActive;
//
////     /**
////      * Initialises everything necessary to begin playing the game. The grids for each player are initialised and
////      * then used to determine how much space is required. The listeners are attached, AI configured, and
////      * everything set to begin the game with placing a ship for the player.
////      */
////     public GamePanel(int aiChoice) {
////         computer = new SelectionGrid(0,0);
////         player = new SelectionGrid(0,computer.getYCoor()+50);
////         setBackground(new Color(42, 136, 163));
////         setPreferredSize(new Dimension(computer.getXCoor(), player.getLocation().y + player.getYCoor()));
////         addMouseListener(this);
////         addMouseMotionListener(this);
////         if(aiChoice == 0) aiController = new EasyBot(player);
////         else aiController = new NightmareBot(player,aiChoice == 2,aiChoice == 2);
////         statusPanel = new StatusPanel(new Location(0,computer.getYCoor()+1),computer.getXCoor(),49);
////         restart();
////     }
//
//
//
////     /**
////      * Draws the grids for both players, any ship being placed, and the status panel.
////      *
////      * @param g Reference to the Graphics object for drawing.
////      */
////     public void paint(Graphics g) {
////         super.paint(g);
////         computer.paint(g);
////         player.paint(g);
////         if(gameState == GameState.PLACING_SHIPS) {
////             placingShip.paint(g);
////         }
////         statusPanel.paint(g);
////     }
//
////     /**
////      * Handles input based on keys that are pressed.
////      * Escape quits the application. R restarts.
////      * Z rotates the ship while in PlacingShips state.
////      * D activates the debug mode to show computer ships.
////      *
////      * @param keyCode The key that was pressed.
////      */
////     public void handleInput(int keyCode) {
////         if(keyCode == KeyEvent.VK_ESCAPE) {
////             System.exit(1);
////         } else if(keyCode == KeyEvent.VK_R) {
////             restart();
////         } else if(gameState == GameState.PLACING_SHIPS && keyCode == KeyEvent.VK_SPACE) {
////             placingShip.toggleSideways();
////             updateShipPlacement(tempPlacingPosition);
////         } else if(keyCode == KeyEvent.VK_D) {
////             debugModeActive = !debugModeActive;
////         }
////         repaint();
////     }
//
////     /**
////      * Resets all the class's properties back to their defaults ready for a new game to begin.
////      */
////     public void restart() {
////         computer.reset();
////         player.reset();
////         // Player can see their own ships by default
////         player.setShowShips(true);
////         aiController.reset();
////         tempPlacingPosition = new Location(0,0);
////         placingShip = new Ship(new Location(0,0),
////                                new Location(player.getLocation().x,player.getLocation().y),
////                                SelectionGrid.boatSize[0], true);
////         placingShipIndex = 0;
////         updateShipPlacement(tempPlacingPosition);
////         computer.populateShips();
////         debugModeActive = false;
////         statusPanel.reset();
////         gameState = GameState.PLACING_SHIPS;
////     }
//
////     /**
////      * Uses the mouse position to test update the ship being placed during the
////      * PlacingShip state. Then if the place it has been placed is valid the ship will
////      * be locked in by calling placeShip().
////      *
////      * @param mousePosition Mouse coordinates inside the panel.
////      */
////     private void tryPlaceShip(Location mousePosition) {
////         Location targetPosition = player.getLocationWithinGrid(mousePosition.x, mousePosition.y);
////         updateShipPlacement(targetPosition);
////         if(player.canPlaceShipAt(targetPosition.x, targetPosition.y,
////                 SelectionGrid.boatSize[placingShipIndex],placingShip.isSideways())) {
////             placeShip(targetPosition);
////         }
////     }
//
////     /**
////      * Finalises the insertion of the ship being placed by storing it in the player's grid.
////      * Then either prepares the next ship for placing, or moves to the next state.
////      *
////      * @param targetPosition The position on the grid to insert the ship at.
////      */
////     private void placeShip(Location targetPosition) {
////         placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Placed);
////         player.placeShip(placingShip,tempPlacingPosition.x,tempPlacingPosition.y);
////         placingShipIndex++;
////         // If there are still ships to place
////         if(placingShipIndex < SelectionGrid.boatSize.length) {
////             placingShip = new Ship(new Location(targetPosition.x, targetPosition.y),
////                           new Location(player.getLocation().x + targetPosition.x * SelectionGrid.cellSize,
////                        player.getLocation().y + targetPosition.y * SelectionGrid.cellSize),
////                           SelectionGrid.boatSize[placingShipIndex], true);
////             updateShipPlacement(tempPlacingPosition);
////         } else {
////             gameState = GameState.PLAYING;
////             statusPanel.setTopString("Attack the Computer!");
////             statusPanel.setBottomString("Destroy all Ships to win!");
////         }
////     }
//
////     /**
////      * Attempts to fire at a position on the computer's board.
////      * The player is notified if they hit/missed, or nothing if they
////      * have clicked the same place again. After the player's turn,
////      * the AI is given a turn if the game is not already ended.
////      *
////      * @param mousePosition Mouse coordinates inside the panel.
////      */
////     private void tryFireAtComputer(Location mousePosition) {
////         Location targetPosition = computer.getLocationWithinGrid(mousePosition.x,mousePosition.y);
////         // Ignore if position was already clicked
////         if(!computer.isLocationMarked(targetPosition)) {
////             doPlayerTurn(targetPosition);
////             // Only do the AI turn if the game didn't end from the player's turn.
////             if(!computer.areAllShipsDestroyed()) {
////                 doAITurn();
////             }
////         }
////     }
//
////     /**
////      * Processes the player's turn based on where they selected to attack.
////      * Based on the result of the attack a message is displayed to the player,
////      * and if they destroyed the last ship the game updates to a won state.
////      *
////      * @param targetPosition The grid position clicked on by the player.
////      */
////     private void doPlayerTurn(Location targetPosition) {
////         boolean hit = computer.markLocation(targetPosition);
////         String hitMiss = hit ? "Hit" : "Missed";
////         String destroyed = "";
////         if(hit && computer.getMarkerAtLocation(targetPosition).getAssociatedShip().isDestroyed()) {
////             destroyed = "(Destroyed)";
////         }
////         statusPanel.setTopString("Player " + hitMiss + " " + targetPosition + destroyed);
////         if(computer.areAllShipsDestroyed()) {
////             // Player wins!
////             gameState = GameState.GAME_LOSS;
////             statusPanel.showGameOver(true);
////         }
////     }
//
////     /**
////      * Processes the AI turn by using the AI Controller to select a move.
////      * Then processes the result to display it to the player. If the AI
////      * destroyed the last ship the game will end with AI winning.
////      */
////     private void doAITurn() {
////         Location aiMove = aiController.selectMove();
////         boolean hit = player.markLocation((Location) aiMove);
////         String hitMiss = hit ? "Hit" : "Missed";
////         String destroyed = "";
////         if(hit && player.getMarkerAtLocation(aiMove).getAssociatedShip().isDestroyed()) {
////             destroyed = "(Destroyed)";
////         }
////         statusPanel.setBottomString("Computer " + hitMiss + " " + aiMove + destroyed);
////         if(player.areAllShipsDestroyed()) {
////             // Computer wins!
////             gameState = GameState.GAME_LOSS;
////             statusPanel.showGameOver(false);
////         }
////     }
//
////     /**
////      * Updates the ship being placed location if the mouse is inside the grid.
////      *
////      * @param mousePosition Mouse coordinates inside the panel.
////      */
////     private void tryMovePlacingShip(Location mousePosition) {
////         if(player.isLocationWithinCoordinate(mousePosition)) {
////             Location targetPos = player.getLocationWithinGrid(mousePosition.x, mousePosition.y);
////             updateShipPlacement(targetPos);
////         }
////     }
//
////     /**
////      * Constrains the ship to fit inside the grid. Updates the drawn position of the ship,
////      * and changes the colour of the ship based on whether it is a valid or invalid placement.
////      *
////      * @param tempPlacingPosition2 The grid coordinate where the ship being placed should change to.
////      */
////     private void updateShipPlacement(Location tempPlacingPosition2) {
////         // Constrain to fit inside the grid
////         if(placingShip.isSideways()) {
////             tempPlacingPosition2.x = Math.min(tempPlacingPosition2.x, SelectionGrid.gridWidth - SelectionGrid.boatSize[placingShipIndex]);
////         } else {
////             tempPlacingPosition2.y = Math.min(tempPlacingPosition2.y, SelectionGrid.gridHeight - SelectionGrid.boatSize[placingShipIndex]);
////         }
////         // Update drawing position to use the new target position
////         placingShip.setDrawPosition(new Location(tempPlacingPosition2),
////                                     new Location(player.getLocation().x + tempPlacingPosition2.x * SelectionGrid.cellSize,
////                                  player.getLocation().y + tempPlacingPosition2.y * SelectionGrid.cellSize));
////         // Store the grid position for other testing cases
////         tempPlacingPosition = tempPlacingPosition2;
////         // Change the colour of the ship based on whether it could be placed at the current location.
////         if(player.canPlaceShipAt(tempPlacingPosition.x, tempPlacingPosition.y,
////                 SelectionGrid.boatSize[placingShipIndex],placingShip.isSideways())) {
////             placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Valid);
////         } else {
////             placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Invalid);
////         }
////     }
//
////     /**
////      * Triggered when the mouse button is released. If in the PlacingShips state and the
////      * cursor is inside the player's grid it will try to place the ship.
////      * Otherwise if in the FiringShots state and the cursor is in the computer's grid,
////      * it will try to fire at the computer.
////      *
////      * @param e Details about where the mouse event occurred.
////      */
////     @Override
////     public void mouseReleased(MouseEvent e) {
////         Location mousePosition = new Location(e.getX(), e.getY());
////         if(gameState == GameState.PLACING_SHIPS && player.isLocationWithinCoordinate(mousePosition)) {
////             tryPlaceShip(mousePosition);
////         } else if(gameState == GameState.PLAYING && computer.isLocationWithinCoordinate(mousePosition)) {
////             tryFireAtComputer(mousePosition);
////         }
////         repaint();
////     }
//
////     /**
////      * Triggered when the mouse moves inside the panel. Does nothing if not in the PlacingShips state.
////      * Will try and move the ship that is currently being placed based on the mouse coordinates.
////      *
////      * @param e Details about where the mouse event occurred.
////      */
////     @Override
////     public void mouseMoved(MouseEvent e) {
////         if(gameState != GameState.PLACING_SHIPS) return;
////         tryMovePlacingShip(new Location(e.getX(), e.getY()));
////         repaint();
////     }
//
////     /**
////      * Not used.
////      *
////      * @param e Not used.
////      */
////     @Override
////     public void mouseClicked(MouseEvent e) {}
////     /**
////      * Not used.
////      *
////      * @param e Not used.
////      */
////     @Override
////     public void mousePressed(MouseEvent e) {}
////     /**
////      * Not used.
////      *
////      * @param e Not used.
////      */
////     @Override
////     public void mouseEntered(MouseEvent e) {}
////     /**
////      * Not used.
////      *
////      * @param e Not used.
////      */
////     @Override
////     public void mouseExited(MouseEvent e) {}
////     /**
////      * Not used.
////      *
////      * @param e Not used.
////      */
////     @Override
////     public void mouseDragged(MouseEvent e) {}
//
//// 	public static boolean debugModeActive() {
//// 		// TODO Auto-generated method stub
//// 		throw new UnsupportedOperationException("Unimplemented method 'debugModeActive'");
//// 	}
//
////     public void startGame() {
////         // TODO Auto-generated method stub
////         throw new UnsupportedOperationException("Unimplemented method 'startGame'");
////     }
//// }
>>>>>>> Stashed changes
