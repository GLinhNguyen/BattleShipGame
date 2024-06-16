package Game;

import MessageStatus.*;
import Functions.*;
import BotThings.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class BotPanel extends JPanel {
    private StatusPanel statusPanel;
    private SelectionGrid computer1;
    private SelectionGrid computer2;
    private Bot bot1;
    private Bot bot2;
    private GameState gameState;
    public static boolean debugModeActive;

    public BotPanel(int bot1Choice, int bot2Choice) {
        setBackground(new Color(42, 136, 163));
        gameState = GameState.PLACING_SHIPS;
        computer1 = new SelectionGrid(0, 0);
        computer2 = new SelectionGrid(0, computer1.getHeight() + 50);
        setPreferredSize(new Dimension(computer1.getWidth(), computer2.getLocation().y + computer2.getHeight())); // Set to your desired dimensions

        // Initialize bots based on choices
        bot1 = createBot(bot1Choice, computer1);
        bot2 = createBot(bot2Choice, computer2);

        Location statusPanelLocation = new Location(0, computer1.getHeight() + 1);
        statusPanel = new StatusPanel(statusPanelLocation, computer1.getWidth(), 49);

        setLayout(new BorderLayout());

        // Add button to start the bot vs bot game
        JButton startButton = new JButton("Start Bot vs Bot");
        startButton.addActionListener(e -> startBotVsBotGame());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Bot createBot(int choice, SelectionGrid grid) {
        if (choice == 0) {
            return new EasyBot(grid);
        } else {
            return new NightmareBot(grid, choice == 2, choice == 2);
        }
    }

    public void startBotVsBotGame() {
        bot1.placeShips();
        bot2.placeShips();
        gameState = GameState.FIRING;
        Timer timer = new Timer(1000, e -> playTurn());
        timer.start();
    }

    private void playTurn() {
        if (gameState != GameState.FIRING) return;

        if (!computer1.areAllShipsDestroyed() && !computer2.areAllShipsDestroyed()) {
            Location bot1Move = bot1.selectMove();
            boolean bot1Hit = computer2.markLocation(bot1Move);
            String bot1HitMiss = bot1Hit ? "Hit" : "Missed";
            statusPanel.setAnnouncement("Bot 1 " + bot1HitMiss + " " + bot1Move);

            if (computer2.areAllShipsDestroyed()) {
                gameState = GameState.GAME_WIN;
                statusPanel.showGameOver(true);
                return;
            }

            Location bot2Move = bot2.selectMove();
            boolean bot2Hit = computer1.markLocation(bot2Move);
            String bot2HitMiss = bot2Hit ? "Hit" : "Missed";
            statusPanel.setAnnouncement("Bot 2 " + bot2HitMiss + " " + bot2Move);

            if (computer1.areAllShipsDestroyed()) {
                gameState = GameState.GAME_WIN;
                statusPanel.showGameOver(true);
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        computer1.paint(g);
        computer2.paint(g);
        statusPanel.paint(g);
    }
    public static boolean debugModeActive() {
        return debugModeActive;
    }

    public void handleInput(int keyCode) {
    }
}
