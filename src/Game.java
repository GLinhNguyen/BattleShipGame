import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Game implements KeyListener {
    
    public void main (String[] args) {
        Game game = new Game();
    }

    private GamePanel gamePanel;

    // Create a JFram with GamePanel inside, attach key listener
    public Game() {
        // Choose computer difficulty
        String[] options = {"Easy","Normal","Nightmare"};
        String message = "Easy bot will make random move, \nNormal bot will make move that focus in finding your ship, \nNightmare bot will make move focus in finding your ship and will be smart in making move.";
        int difficulty = JOptionPane.showOptionDialog(null, message, "Choose AI difficulty", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
        
        JFrame frame = new JFrame("Battle Ship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        gamePanel = new GamePanel(difficulty);
        frame.getContentPane().add(gamePanel);

        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
