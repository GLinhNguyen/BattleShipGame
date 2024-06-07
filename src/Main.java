
import Game.GamePanel_test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import BotThings.Bot;
import BotThings.EasyBot;
import BotThings.NightmareBot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements KeyListener{
    public static GamePanel_test gamePanel;
    public static void main(String[] args) {
        String[] options = new String[] {"Easy", "Nightmare"};
        String message = "Easy will make moves entirely randomly,\nMedium will focus on areas where it finds ships,"
                + "\nand Hard will make smarter choices over Medium.";
        int difficultyChoice = JOptionPane.showOptionDialog(null, message,
                "Choose an AI Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
      


        JFrame frame = new JFrame("Battleship Game");
         
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        gamePanel = new GamePanel_test (difficultyChoice);
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(new Main());
    }
    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode());
    }

    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void keyReleased(KeyEvent e) {}
}
