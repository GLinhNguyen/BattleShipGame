package Game;

import MessageStatus.*;
import Functions.*;
import BotThings.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Boards.Location;
import Boards.SelectionGrid;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

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
    private int timeLimit;
    private Timer gameTimer;
    private int countdown;
    private JButton restartButton;
    private Image bgImage;

   
    public GamePanel_test(int aiChoice, int countdownDuration) {
        this.timeLimit = countdownDuration;
        setPreferredSize(new Dimension(1000, 800)); // Set to your desired dimensions
        currentState = GameState.PLACING_SHIPS;
        computer = new SelectionGrid(320, 0);
        playerGrid = new SelectionGrid(320, computer.getHeight() + 50);
    
        if (aiChoice == 0) {
            aiController = new EasyBot(playerGrid);
        } else {
            aiController = new NightmareBot(playerGrid, aiChoice == 2, aiChoice == 2);
        }
    
        Location statusPanelLocation = new Location(320, computer.getHeight() + 1);
        statusPanel = new StatusPanel(statusPanelLocation, computer.getWidth(), 49);
    
        setLayout(new BorderLayout());
    
        addMouseListener(this);
        addMouseMotionListener(this);
    
        // Initialize and add the restart button
        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restart());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restartButton);
        // add(buttonPanel, BorderLayout.EAST);
    
        restart();
    
        // Load the background image
         try {
            bgImage = ImageIO.read(getClass().getResourceAsStream("/Graphics/BG-gamPanel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the background image
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    
        // Draw other components
        computer.paint(g);
        playerGrid.paint(g);
        statusPanel.paint(g);
        if (gameState == GameState.PLACING_SHIPS) {
            placingShip.paint(g);
        }
       
        drawCountdownTimer(g);
    }
    
    public void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        } else if (keyCode == KeyEvent.VK_R) {
            restart();
        } else if (gameState == GameState.PLACING_SHIPS && keyCode == KeyEvent.VK_W) {
            placingShip.toggleSideways();
            updateShipPlacement(tempPlacingPosition);
        } else if (keyCode == KeyEvent.VK_D) {
            debugModeActive = !debugModeActive;
        }
        repaint();
    }

  
    private void drawCountdownTimer(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Efour Digital Pro", Font.BOLD, 30));
        String timerText = "Time: " + countdown + "s";
        g.drawString(timerText, getWidth() - 200, 50);
    }

    private void startGameTimer() {
        countdown = timeLimit;
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                countdown--;
                if (countdown <= 0) {
                    gameTimer.cancel();
                    endGame();
                }
                repaint();
            }
        }, 1000, 1000);
    }

    private void endGame() {
        if (playerGrid.areAllShipsDestroyed()) {
            gameState = GameState.GAME_LOSS;
            statusPanel.showGameOver(false); // Computer wins
        } else if (computer.areAllShipsDestroyed()) {
            gameState = GameState.GAME_WIN;
            statusPanel.showGameOver(true); // Player wins
        } else {
            gameState = GameState.GAME_LOSS;
            statusPanel.showGameOver(false); // Time ran out
        }
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
        startGameTimer();
    }

    private void tryPlaceShip(Location mousePosition) {
        Location targetPosition = playerGrid.getLocationWithinGrid(mousePosition.x, mousePosition.y);
        updateShipPlacement(targetPosition);
        if (playerGrid.canPlaceShipAt(targetPosition.x, targetPosition.y, SelectionGrid.boatSize[placingShipIndex], placingShip.isSideways())) {
            placeShip(targetPosition);
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
            gameState = GameState.FIRING;
            statusPanel.setBottomString("Attack the Computer!");
        }
    }

    private void tryFireAtComputer(Location mousePosition) {
        Location targetPosition = computer.getLocationWithinGrid(mousePosition.x, mousePosition.y);
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
        statusPanel.setTopString("Player " + hitMiss + " " + targetPosition + destroyed);
        if (computer.areAllShipsDestroyed()) {
            gameState = GameState.GAME_WIN;
            statusPanel.showGameOver(true);
            gameTimer.cancel();
        }
        else {
            statusPanel.setBottomString("Computer's Turn");
            startGameTimer();
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
        statusPanel.setBottomString("Computer " + hitMiss + " " + aiMove + destroyed);
        if (playerGrid.areAllShipsDestroyed()) {
            gameState = GameState.GAME_LOSS;
            statusPanel.showGameOver(false);
            gameTimer.cancel();
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
            tempPlacingPosition2.x = Math.min(tempPlacingPosition2.x, SelectionGrid.gridXNum - SelectionGrid.boatSize[placingShipIndex]);
        } else {
            tempPlacingPosition2.y = Math.min(tempPlacingPosition2.y, SelectionGrid.gridYNum - SelectionGrid.boatSize[placingShipIndex]);
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
        } else if (gameState == GameState.FIRING && computer.isLocationWithinCoordinate(mousePosition)) {
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
                    case FIRING:
                        if (newState == GameState.GAME_WIN) {
                            statusPanel.getMessage();
                        } else if (newState == GameState.GAME_LOSS) {
                            statusPanel.getMessage();
                        }
                        break;
                    case PLACING_SHIPS:
                        if (newState == GameState.FIRING) {
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
}
