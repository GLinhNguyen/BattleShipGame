// package Game;
// import MessageStatus.*;
// import Functions.*;
// import BotThings.*; 

// import java.awt.event.KeyEvent;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
// import java.awt.event.MouseMotionListener;
// import javax.swing.*;
// import javax.swing.text.Position;

// import java.awt.*;

// public class GamePanel_test extends JPanel implements MouseListener, MouseMotionListener {
//     private StatusPanel statusPanel;
//     private SelectionGrid computer;
//     private SelectionGrid playerGrid;
//     private Bot aiController;
//     private Ship placingShip;
//     private Location tempPlacingPosition;
//     private int placingShipIndex;
//     private GameState gameState;
//     public static boolean debugModeActive;
//     private SelectionGrid player;
//     private GameState currentState;
//     protected Location location = new Location(x, y);
  

//     public GamePanel_test(int aiChoice) {
//         setBackground(new Color(42, 136, 163));
//         setPreferredSize(new Dimension(800, 600)); // Set to your desired dimensions

//         computer = new SelectionGrid(0, 0);
//         playerGrid = new SelectionGrid(0, computer.getYCoor() + 50);
        
//         if (aiChoice == 0) {
//             aiController = new EasyBot(playerGrid);
//         } else {
//             aiController = new NightmareBot(playerGrid, aiChoice == 2, aiChoice == 2);
//         }

//         statusPanel = new StatusPanel(new Location(0, computer.getYCoor() + 1), computer.getXCoor(), 49);

//         setLayout(new BorderLayout());
//         // add(BorderLayout.NORTH, statusPanel);

//         // JPanel centerPanel = new JPanel(new GridLayout(1, 2));
//         // centerPanel.add(computer);
//         // centerPanel.add(player);
//         // add(centerPanel, BorderLayout.CENTER);

//         addMouseListener(this);
//         addMouseMotionListener(this);

//         restart();
//     }

//     @Override
//     public void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         computer.paint(g);
//         playerGrid.paint(g);
//         if (gameState == GameState.PLACING_SHIPS) {
//             placingShip.paint(g);
//         }
//         statusPanel.paint(g);
//     }

//     public void handleInput(int keyCode) {
//         if (keyCode == KeyEvent.VK_ESCAPE) {
//             System.exit(1);
//         } else if (keyCode == KeyEvent.VK_R) {
//             restart();
//         } else if (gameState == GameState.PLACING_SHIPS && keyCode == KeyEvent.VK_SPACE) {
//             placingShip.toggleSideways();
//             updateShipPlacement(tempPlacingPosition);
//         } else if (keyCode == KeyEvent.VK_D) {
//             debugModeActive = !debugModeActive;
//         }
//         repaint();
//     }

//     public void restart() {
//         computer.reset();
//         playerGrid.reset();
//         playerGrid.setShowShips(true);
//         aiController.reset();
//         tempPlacingPosition = new Location(0, 0);
//         placingShip = new Ship(new Location(0, 0), new Location(playerGrid.getLocation().x, playerGrid.getLocation().y), SelectionGrid.boatSize[0], true);
//         placingShipIndex = 0;
//         updateShipPlacement(tempPlacingPosition);
//         computer.populateShips();
//         debugModeActive = false;
//         statusPanel.reset();
//         gameState = GameState.PLACING_SHIPS;
//     }

//     private void tryPlaceShip(Location mousePosition) {
//         Location targetPosition = playerGrid.getLocationWithinGrid(mousePosition.x, mousePosition.y);
//         updateShipPlacement(targetPosition);
//         if (playerGrid.canPlaceShipAt(targetPosition.x, targetPosition.y, SelectionGrid.boatSize[placingShipIndex], placingShip.isSideways())) {
//             placeShip(targetPosition);
//         }
//     }

//     private void placeShip(Location targetPosition) {
//         placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Placed);
//         playerGrid.placeShip(placingShip, tempPlacingPosition.x, tempPlacingPosition.y);
//         placingShipIndex++;
//         if (placingShipIndex < SelectionGrid.boatSize.length) {
//             placingShip = new Ship(new Location(targetPosition.x, targetPosition.y), new Location(playerGrid.getLocation().x + targetPosition.x * SelectionGrid.cellSize, playerGrid.getLocation().y + targetPosition.y * SelectionGrid.cellSize), SelectionGrid.boatSize[placingShipIndex], true);
//             updateShipPlacement(tempPlacingPosition);
//         } else {
//             gameState = GameState.PLAYING;
//             statusPanel.setTopString("Attack the Computer!");
//             statusPanel.setBottomString("Destroy all Ships to win!");
//         }
//     }

//     private void tryFireAtComputer(Location mousePosition) {
//         Location targetPosition = computer.getLocationWithinGrid(mousePosition.x, mousePosition.y);
//         if (!computer.isLocationMarked(targetPosition)) {
//             doPlayerTurn(targetPosition);
//             if (!computer.areAllShipsDestroyed()) {
//                 doAITurn();
//             }
//         }
//     }

//     private void doPlayerTurn(Location targetPosition) {
//         boolean hit = computer.markLocation(targetPosition);
//         String hitMiss = hit ? "Hit" : "Missed";
//         String destroyed = "";
//         if (hit && computer.getMarkerAtLocation(targetPosition).getAssociatedShip().isDestroyed()) {
//             destroyed = "(Destroyed)";
//         }
//         statusPanel.setTopString("Player " + hitMiss + " " + targetPosition + destroyed);
//         if (computer.areAllShipsDestroyed()) {
//             gameState = GameState.GAME_WIN;
//             statusPanel.showGameOver(true);
//         }
//     }

//     private void doAITurn() {
//         Location aiMove = aiController.selectMove();
//         boolean hit = playerGrid.markLocation(aiMove);
//         String hitMiss = hit ? "Hit" : "Missed";
//         String destroyed = "";
//         if (hit && playerGrid.getMarkerAtLocation(aiMove).getAssociatedShip().isDestroyed()) {
//             destroyed = "(Destroyed)";
//         }
//         statusPanel.setBottomString("Computer " + hitMiss + " " + aiMove + destroyed);
//         if (playerGrid.areAllShipsDestroyed()) {
//             gameState = GameState.GAME_LOSS;
//             statusPanel.showGameOver(false);
//         }
//     }

//     private void tryMovePlacingShip(Location mousePosition) {
//         if (playerGrid.isLocationWithinCoordinate(mousePosition)) {
//             Location targetPos = playerGrid.getLocationWithinGrid(mousePosition.x, mousePosition.y);
//             updateShipPlacement(targetPos);
//         }
//     }

//     private void updateShipPlacement(Location tempPlacingPosition2) {
//         if (placingShip.isSideways()) {
//             tempPlacingPosition2.x = Math.min(tempPlacingPosition2.x, SelectionGrid.gridWidth - SelectionGrid.boatSize[placingShipIndex]);
//         } else {
//             tempPlacingPosition2.y = Math.min(tempPlacingPosition2.y, SelectionGrid.gridHeight - SelectionGrid.boatSize[placingShipIndex]);
//         }
//         placingShip.setDrawPosition(new Location(tempPlacingPosition2), new Location(playerGrid.getLocation().x + tempPlacingPosition2.x * SelectionGrid.cellSize, playerGrid.getLocation().y + tempPlacingPosition2.y * SelectionGrid.cellSize));
//         tempPlacingPosition = tempPlacingPosition2;
//         if (playerGrid.canPlaceShipAt(tempPlacingPosition.x, tempPlacingPosition.y, SelectionGrid.boatSize[placingShipIndex], placingShip.isSideways())) {
//             placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Valid);
//         } else {
//             placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Invalid);
//         }
//     }

//     @Override
//     public void mouseReleased(MouseEvent e) {
//         Location mousePosition = new Location(e.getX(), e.getY());
//         if (gameState == GameState.PLACING_SHIPS && playerGrid.isLocationWithinCoordinate(mousePosition)) {
//             tryPlaceShip(mousePosition);
//         } else if (gameState == GameState.PLAYING && computer.isLocationWithinCoordinate(mousePosition)) {
//             tryFireAtComputer(mousePosition);
//         }
//         repaint();
//     }

//     @Override
//     public void mouseMoved(MouseEvent e) {
//         if (gameState != GameState.PLACING_SHIPS) return;
//         tryMovePlacingShip(new Location(e.getX(), e.getY()));
//         repaint();
//     }
//         public void updateGameState(GameState newState) {
//         switch (currentState) {
//             case PLAYING:
//                 if(newState == GameState.GAME_WIN) {
//                     statusPanel.getMessage();
//                 } else if(newState == GameState.GAME_LOSS) {
//                     statusPanel.getMessage();
//                 }
//                 break;
//             case PLACING_SHIPS:
//                 if(newState == GameState.PLAYING) {
//                     statusPanel.getMessage();
//                 }
//                 break;
//             case GAME_WIN:
//             if(newState == GameState.GAME_WIN) {
//                 statusPanel.getMessage();
//             }
//             restart();
//                 break;

//             case GAME_LOSS:
//             if(newState == GameState.GAME_LOSS) {
//                 statusPanel.getMessage();
//             }
//             restart();
//                 break;


//         }
//         currentState = newState;
//     }

//     @Override
//     public void mouseClicked(MouseEvent e) {}
//     @Override
//     public void mousePressed(MouseEvent e) {}
//     @Override
//     public void mouseEntered(MouseEvent e) {}
//     @Override
//     public void mouseExited(MouseEvent e) {}
//     @Override
//     public void mouseDragged(MouseEvent e) {}

//     public static boolean debugModeActive() {
//         return debugModeActive;
//     }

//     public void startGame() {
//         // Initialize game components
//         statusPanel = new StatusPanel(0, 0);
//         player = new SelectionGrid();
//         computer = new SelectionGrid();

//         // Start the game loop
//         while (true) {
//             // Update the game state
//             updateGameState(gameState);

//             // Redraw the game panel
//             repaint();

//             // Pause for a short period before the next iteration
//             try {
//                 Thread.sleep(100);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }
package Game;


import MessageStatus.*;
import Functions.*;
import BotThings.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class GamePanel_test extends JPanel implements MouseListener, MouseMotionListener {
    private StatusPanel statusPanel;
    private SelectionGrid computer;
    private SelectionGrid playerGrid;
    private Bot aiController;
    private Ship placingShip;
    private Location tempPlacingPosition;
    private int placingShipIndex;
    private GameState gameState;
    public static boolean debugModeActive;
    private SelectionGrid player;
    private GameState currentState;

    private Stack<Move> moveStack = new Stack<>();
    private JButton undoButton;

    private int redoLevel = 0;
    private JButton redoButton;


    public GamePanel_test(int aiChoice) {
        setBackground(new Color(42, 136, 163));
        setPreferredSize(new Dimension(800, 600)); // Set to your desired dimensions
        currentState = GameState.PLACING_SHIPS;
        computer = new SelectionGrid(0, 0);
        playerGrid = new SelectionGrid(0, computer.getHeight() + 50);

        if (aiChoice == 0) {
            aiController = new EasyBot(playerGrid);
        } else {
            aiController = new NightmareBot(playerGrid, aiChoice == 2, aiChoice == 2);
        }

        Location statusPanelLocation = new Location(80, computer.getHeight() + 1);
        statusPanel = new StatusPanel(statusPanelLocation, computer.getWidth(), 49);

        setLayout(new BorderLayout());

        addMouseListener(this);
        addMouseMotionListener(this);

        restart();
    }

    public class Move {
        private Location location; // Location of the move (attack or ship placement)
        private boolean isPlayerMove; // indicate if it's a player or AI move

        public Move(Location location, boolean isPlayerMove) {
            this.location = location;
            this.isPlayerMove = isPlayerMove;
        }

        // Getter for location
        public Location getLocation() {
            return location;
        }

        // Setter for location
        public void setLocation(Location location) {
            this.location = location;
        }

        // Getter for isPlayerMove
        public boolean isPlayerMove() {
            return isPlayerMove;
        }

        // Setter for isPlayerMove
        public void setPlayerMove(boolean playerMove) {
            isPlayerMove = playerMove;
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        computer.paint(g);
        playerGrid.paint(g);
        if (gameState == GameState.PLACING_SHIPS) {
            placingShip.paint(g);
        }
        statusPanel.paint(g);
    }

    public void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        } else if (keyCode == KeyEvent.VK_R) {
            restart();
        } else if (gameState == GameState.PLACING_SHIPS && keyCode == KeyEvent.VK_SPACE) {
            placingShip.toggleSideways();
            updateShipPlacement(tempPlacingPosition);
        } else if (keyCode == KeyEvent.VK_D) {
            debugModeActive = !debugModeActive;
        }
        repaint();
    }

    public void restart() {
        computer.reset();
        playerGrid.reset();
        playerGrid.setShowShips(true);
        aiController.reset();
        tempPlacingPosition = new Location(0, 0);
        placingShip = new Ship(new Location(0, 0), new Location(playerGrid.getLocation().x, playerGrid.getLocation().y), SelectionGrid.boatSize[0], true);
        placingShipIndex = 0;
        updateShipPlacement(tempPlacingPosition);
        computer.populateShips();
        debugModeActive = false;
        statusPanel.reset();
        gameState = GameState.PLACING_SHIPS;
    }

    private void tryPlaceShip(Location mousePosition) {
        Location targetPosition = playerGrid.getLocationWithinGrid(mousePosition.x, mousePosition.y);
        updateShipPlacement(targetPosition);
        if (playerGrid.canPlaceShipAt(targetPosition.x, targetPosition.y, SelectionGrid.boatSize[placingShipIndex], placingShip.isSideways())) {
            placeShip(targetPosition);
            moveStack.push(new Move(targetPosition, true));
        }
    }


    private void placeShip(Location targetPosition) {
        placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Placed);
        playerGrid.placeShip(placingShip, tempPlacingPosition.x, tempPlacingPosition.y);
        placingShipIndex++;
        if (placingShipIndex < SelectionGrid.boatSize.length) {
            placingShip = new Ship(new Location(targetPosition.x, targetPosition.y), new Location(playerGrid.getLocation().x + targetPosition.x * SelectionGrid.cellSize, playerGrid.getLocation().y + targetPosition.y * SelectionGrid.cellSize), SelectionGrid.boatSize[placingShipIndex], true);
            updateShipPlacement(tempPlacingPosition);
        } else {
            gameState = GameState.PLAYING;
            statusPanel.setAnnouncement("Attack the Computer!");
        }
    }

    private void tryFireAtComputer(Location mousePosition) {
        Location targetPosition = computer.getLocationWithinGrid(mousePosition.x, mousePosition.y);
        // Push AI move onto stack before attack
        moveStack.push(new Move(targetPosition, false));
        if (!computer.isLocationMarked(targetPosition)) {
            doPlayerTurn(targetPosition);
            if (!computer.areAllShipsDestroyed()) {
                doAITurn();
            }
        }
    }

    private void doPlayerTurn(Location targetPosition) {
        boolean hit = computer.markLocation(targetPosition);
        String hitMiss = hit ? "Hit" : "Missed";
        String destroyed = "";
        if (hit && computer.getMarkerAtLocation(targetPosition).getAssociatedShip().isDestroyed()) {
            destroyed = "(Destroyed)";
        }
        statusPanel.setAnnouncement("Player " + hitMiss + " " + targetPosition + destroyed);
        if (computer.areAllShipsDestroyed()) {
            gameState = GameState.GAME_WIN;
            statusPanel.showGameOver(true);
        }
    }

    private void doAITurn() {
        Location aiMove = aiController.selectMove();
        boolean hit = playerGrid.markLocation(aiMove);
        String hitMiss = hit ? "Hit" : "Missed";
        String destroyed = "";
        if (hit && playerGrid.getMarkerAtLocation(aiMove).getAssociatedShip().isDestroyed()) {
            destroyed = "(Destroyed)";
        }
        statusPanel.setAnnouncement("Computer " + hitMiss + " " + aiMove + destroyed);
        if (playerGrid.areAllShipsDestroyed()) {
            gameState = GameState.GAME_LOSS;
            statusPanel.showGameOver(false);
        }
    }

    private void tryMovePlacingShip(Location mousePosition) {
        if (playerGrid.isLocationWithinCoordinate(mousePosition)) {
            Location targetPos = playerGrid.getLocationWithinGrid(mousePosition.x, mousePosition.y);
            updateShipPlacement(targetPos);
        }
    }

    private void updateShipPlacement(Location tempPlacingPosition2) {
        if (placingShip.isSideways()) {
            tempPlacingPosition2.x = Math.min(tempPlacingPosition2.x, SelectionGrid.gridWidth - SelectionGrid.boatSize[placingShipIndex]);
        } else {
            tempPlacingPosition2.y = Math.min(tempPlacingPosition2.y, SelectionGrid.gridHeight - SelectionGrid.boatSize[placingShipIndex]);
        }
        placingShip.setDrawPosition(new Location(tempPlacingPosition2), new Location(playerGrid.getLocation().x + tempPlacingPosition2.x * SelectionGrid.cellSize, playerGrid.getLocation().y + tempPlacingPosition2.y * SelectionGrid.cellSize));
        tempPlacingPosition = tempPlacingPosition2;
        if (playerGrid.canPlaceShipAt(tempPlacingPosition.x, tempPlacingPosition.y, SelectionGrid.boatSize[placingShipIndex], placingShip.isSideways())) {
            placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Valid);
        } else {
            placingShip.setShipPlacementColour(Ship.ShipPlacementColour.Invalid);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Location mousePosition = new Location(e.getX(), e.getY());
        if (gameState == GameState.PLACING_SHIPS && playerGrid.isLocationWithinCoordinate(mousePosition)) {
            tryPlaceShip(mousePosition);
        } else if (gameState == GameState.PLAYING && computer.isLocationWithinCoordinate(mousePosition)) {
            tryFireAtComputer(mousePosition);
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gameState != GameState.PLACING_SHIPS) return;

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tryMovePlacingShip(new Location(e.getX(), e.getY()));
                return null;
            }
    
            @Override
            protected void done() {
                repaint();
            }
        };
    
        worker.execute();
    }

    public void updateGameState(GameState newState) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                switch (currentState) {
                    case PLAYING:
                        if (newState == GameState.GAME_WIN) {
                            statusPanel.getMessage();
                        } else if (newState == GameState.GAME_LOSS) {
                            statusPanel.getMessage();
                        }
                        break;
                    case PLACING_SHIPS:
                        if (newState == GameState.PLAYING) {
                            statusPanel.getMessage();
                        }
                        break;
                    // other cases...
                }
                return null;
            }
        };
    
        worker.execute();
    }

    public void undo() {
        if (moveStack.isEmpty()) {
            return; // Nothing to undo
        }

        Move lastMove = moveStack.pop();

        // Handle undo based on move type
        if (lastMove.isPlayerMove) {
            // Undo player move (remove ship placement from grid)
            playerGrid.removeShip(lastMove.getLocation());
            computer.removeShip(lastMove.getLocation());
            // Reset placingShip and tempPlacingPosition
            placingShip = null;
            tempPlacingPosition = new Location(0, 0);
            gameState = GameState.PLACING_SHIPS;
            statusPanel.setAnnouncement("Undo: Player ship placement");
        } else {
            // Check if the undo AI move resulted in a player ship being sunk
            // If so, revert the player's last move (attack) as well
            if (playerGrid.getMarkerAtLocation(lastMove.getLocation()).getAssociatedShip().isDestroyed()) {
                if (!moveStack.isEmpty()) {
                    Move previousMove = moveStack.peek();
                    if (previousMove.isPlayerMove()) {
                        // Revert player attack (undo previous move from stack)
                        redoLevel++;
                        redo(); // Recursive call to undo the player's attack
                    }
                }
            }
        }
        redoLevel = 0;
        statusPanel.setAnnouncement("Undo successfully " );
        repaint();
    }


    public void redo() {
        if (redoLevel > 0 && !moveStack.isEmpty()) {
            Move redoMove = moveStack.peek();
            if (redoMove.isPlayerMove()) {
                // Redo player move (place ship back on grid)
                playerGrid.placeShip(redoMove.getLocation(), placingShip.isSideways());
                computer.placeShip(redoMove.getLocation(), placingShip.isSideways());
                gameState = GameState.PLACING_SHIPS; // Might need adjustment based on game flow
            } else {
                // Simulate computer's attack (fire at the location)
                doPlayerTurn(redoMove.getLocation()); // Assuming doPlayerTurn handles the attack logic
            }
            moveStack.pop(); // Remove the redone move from the stack
            redoLevel--;
            statusPanel.setAnnouncement("Redo successfully ");
            repaint();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    

    public static boolean debugModeActive() {
        return debugModeActive;
    }

    // public void startGame() {
    //     // Initialize game components
    //     statusPanel = new StatusPanel(new Location(100, 50), placingShipIndex, placingShipIndex); // Ensure statusPanel is initialized before using it

    //     player = new SelectionGrid();
    //     computer = new SelectionGrid();
    //     currentState = GameState.PLACING_SHIPS;


    //     // Start the game loop
    //     while (true) {
    //         // Update the game state
    //         updateGameState(gameState);

    //         // Redraw the game panel
    //         repaint();

    //         // Pause for a short period before the next iteration
    //         try {
    //             Thread.sleep(0);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
}

