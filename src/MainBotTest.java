import Game.BotPanel;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainBotTest implements KeyListener {
    public static BotPanel gamePanel;

    public static void main(String[] args) {
        String[] options = new String[]{"Easy", "Nightmare"};
        String message = "Easy will make moves entirely randomly,"
                + "\nand Nightmare will make smarter choices.";

        // Choose bot 1 difficulty
        int bot1Choice = JOptionPane.showOptionDialog(null, "Choose difficulty for Bot 1:\n" + message,
                "Choose Bot 1 Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        // Choose bot 2 difficulty
        int bot2Choice = JOptionPane.showOptionDialog(null, "Choose difficulty for Bot 2:\n" + message,
                "Choose Bot 2 Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        JFrame frame = new JFrame("Battleship Bot vs Bot");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Initialize game panel with bot choices
        gamePanel = new BotPanel(bot1Choice, bot2Choice);

        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
