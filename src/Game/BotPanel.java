package Game;

import Boards.Location;
import Boards.SelectionGrid;
import MessageStatus.*;
import Functions.*;
import MessageStatus.GameState;
import MessageStatus.StatusPanel;

import BotThings.*;
import Functions.Ship;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

import Boards.Location;
import Boards.SelectionGrid;

public class BotPanel extends JPanel implements KeyListener {
    private StatusPanel statusPanel;
    private SelectionGrid computer1;
    private SelectionGrid computer2;
    private Bot bot1;
    private Bot bot2;
    boolean bot1Turn = true;
    private GameState gameState;
    public static boolean debugModeActive;
    private int timeLimit;
    private javax.swing.Timer turnTimer;
    private int countdown;
    private TimerTask countdownTask;

    public BotPanel(int bot1Choice, int bot2Choice, int timeLimit) {
        this.timeLimit = timeLimit;
        this.countdown = timeLimit;
        setPreferredSize(new Dimension(1000, 800));
        setBackground(new Color(42, 136, 163));
        
        gameState = GameState.PLACING_SHIPS;
        computer1 = new SelectionGrid(0, 0);
        computer2 = new SelectionGrid(0, computer1.getHeight() + 50);
        setPreferredSize(new Dimension(1000, computer2.getLocation().y + computer2.getHeight())); // Set to your desired dimensions

        // Initialize bots based on choices
        bot1 = createBot(bot1Choice, computer1);
        bot2 = createBot(bot2Choice, computer2);


        Location statusPanelLocation = new Location(0, computer1.getHeight() + 1);
        statusPanel = new StatusPanel(statusPanelLocation, computer1.getWidth(), 49);

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    gameState = GameState.PLACING_SHIPS;
                    computer1.reset();
                    computer2.reset();
                    bot1.reset();
                    bot2.reset();
                    statusPanel.showGameOver(false);
                    statusPanel.setAnnouncement("");
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setLayout(new BorderLayout());

        // Start the timer
        startCountdownTimer();
    }

    private Bot createBot(int choice, SelectionGrid grid) {
        if (choice == 0) {
            return new EasyBot(grid);
        } else {
            return new NightmareBot(grid, choice == 2, choice == 2);
        }
    }

    public void startBotVsBotGame() {
        if (gameState != GameState.PLACING_SHIPS) return;
        bot1.placeShips();
        bot2.placeShips();
        gameState = GameState.FIRING;
        Timer timer = new Timer(200, e -> playTurn());
        timer.start();
    }

    private void playTurn() {
        if (gameState != GameState.FIRING) return;
        if (!computer1.areAllShipsDestroyed() && !computer2.areAllShipsDestroyed()) {
            if (bot1Turn ) {
                Location bot1Move = bot1.selectMove();
                boolean bot1Hit = computer2.markLocation(bot1Move);
                String bot1HitMiss = bot1Hit ? "Hit" : "Missed";
                statusPanel.setAnnouncement("Bot 1 " + bot1HitMiss + " " + bot1Move);
                bot1Turn = false;
            }
            else{
            Location bot2Move = bot2.selectMove();
            boolean bot2Hit = computer1.markLocation(bot2Move);
            String bot2HitMiss = bot2Hit ? "Hit" : "Missed";
            statusPanel.setAnnouncement("Bot 2 " + bot2HitMiss + " " + bot2Move);
            bot1Turn = true;
            }
        } else if(computer1.areAllShipsDestroyed()){
                gameState = GameState.GAME_WIN;
                statusPanel.showGameOver(true);
                statusPanel.setAnnouncement("Bot 2 wins!");
            }
        else{
                gameState = GameState.GAME_LOSS;
                statusPanel.showGameOver(true);
                statusPanel.setAnnouncement("Bot 1 wins!");
        }

        repaint();
    }

    private void startCountdownTimer() {
        countdownTask = new TimerTask() {
            @Override
            public void run() {
                if (countdown > 0) {
                    countdown--;
                    repaint();
                } else {
                    this.cancel();
                    handleTimeOut();
                }
            }
        };

        Timer countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(countdownTask, 1000, 1000); // Start after 1 second, repeat every 1 second
    }

    private void handleTimeOut() {
        gameState = GameState.GAME_WIN; // Or a different state to indicate timeout
        statusPanel.setBottomString("Time's up! Game Over.");
        statusPanel.showGameOver(false);
        if (turnTimer != null) {
            turnTimer.stop();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        computer1.paint(g);
        computer2.paint(g);
        statusPanel.paint(g);

        // Draw the countdown timer
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Time Left: " + countdown + " s", getWidth() - 250, 50);
    }
   public void restart() {
        computer2.reset();
        computer1.reset();
        bot1.reset();
        bot2.reset();
        bot1.placeShips();
        bot2.placeShips();
        gameState = GameState.FIRING;
        statusPanel.reset();
        startCountdownTimer();
    
    }
    public static boolean debugModeActive() {
        return debugModeActive;
    }

    public void handleInput(int keyCode) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
