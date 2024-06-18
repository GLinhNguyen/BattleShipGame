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
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
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
    private Image bgImage;

    private javax.swing.Timer turnTimer ;
  


    public BotPanel(int bot1Choice, int bot2Choice) {
       
        setPreferredSize(new Dimension(1000, 800));
        setBackground(new Color(42, 136, 163));
        
        gameState = GameState.PLACING_SHIPS;
        computer1 = new SelectionGrid(320, 0);
        computer2 = new SelectionGrid(320, computer1.getHeight() + 50);
       

        // Initialize bots based on choices
        bot1 = createBot(bot1Choice, computer1);
        bot2 = createBot(bot2Choice, computer2);

        Location statusPanelLocation = new Location(320, computer1.getHeight() + 1);
        statusPanel = new StatusPanel(statusPanelLocation, computer1.getWidth(), 49);
        statusPanel.setTopString("Bot 1's Turn");
        statusPanel.setBottomString("Bot 2's Turn");
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
                    repaint();
                    statusPanel.setTopString("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

         try {
            bgImage = ImageIO.read(getClass().getResourceAsStream("/Graphics/BG-gamPanel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Bot createBot(int choice, SelectionGrid grid) {
        if (choice == 0) {
            return new EasyBot(grid);
        } else {
            return new NightmareBot(grid, choice == 2, choice == 2);
        }
    }

    public void  startBotVsBotGame() {
        if (gameState != GameState.PLACING_SHIPS) return;
        bot1.placeShips();
        bot2.placeShips();
        gameState = GameState.FIRING;
        turnTimer = new javax.swing.Timer(500, e -> playTurn());
        turnTimer.start();
    }

    private void playTurn() {
        if (gameState != GameState.FIRING) return;

        if (!computer1.areAllShipsDestroyed() && !computer2.areAllShipsDestroyed()) {
            if (bot1Turn ) {
                Location bot1Move = bot1.selectMove();
                boolean bot1Hit = computer2.markLocation(bot1Move);
                String bot1HitMiss = bot1Hit ? "Hit" : "Missed";
                statusPanel.setTopString("Bot 1 " + bot1HitMiss + " " + bot1Move);
                bot1Turn = false;}
            else {

            Location bot2Move = bot2.selectMove();
            boolean bot2Hit = computer1.markLocation(bot2Move);
            String bot2HitMiss = bot2Hit ? "Hit" : "Missed";
            statusPanel.setBottomString("Bot 2 " + bot2HitMiss + " " + bot2Move);

            bot1Turn = true;
            }
        } else if(computer1.areAllShipsDestroyed()){
                statusPanel.setTopString("Bot 2 wins!");
                statusPanel.setBottomString("");
                turnTimer.stop();
            }
            else{
                
                statusPanel.setTopString("Bot 1 wins!");
                statusPanel.setBottomString("");
               
        }

        repaint();
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
        computer1.paint(g);
        computer2.paint(g);
        statusPanel.paint(g);
    }
    public static boolean debugModeActive() {
        return debugModeActive;
    }

   

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
