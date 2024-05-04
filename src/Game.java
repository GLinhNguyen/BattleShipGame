import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Game implements KeyListener {

    private JFrame frame;
    private JRadioButton timePerTurnOption1;
    private JRadioButton timePerTurnOption2;
    private JRadioButton timePerTurnOption3;
    private JCheckBox whoPlaysFirstCheckbox;
    private JButton startGameButton;
    
     public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Game window = new Game();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private GamePanel gamePanel;

    // Create a JFram with GamePanel inside, attach key listener
    public Game() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Battleship Game Setup");
        frame.setBounds(100, 100, 400, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Add radio buttons for time per turn
        ButtonGroup timePerTurnGroup = new ButtonGroup();

        // Add a label and radio buttons for time per turn
        JLabel timePerTurnLabel = new JLabel("Choose time per turn: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2; // Span across 5 columns
        frame.getContentPane().add(timePerTurnLabel, constraints);

        timePerTurnOption1 = new JRadioButton("40 seconds");
        timePerTurnOption1.setSelected(true);
        timePerTurnGroup.add(timePerTurnOption1);
        constraints.gridx = 0;
        constraints.gridy = 1;  
        frame.getContentPane().add(timePerTurnOption1, constraints);
        
        timePerTurnOption2 = new JRadioButton("60 seconds");
        timePerTurnGroup.add(timePerTurnOption2);
        constraints.gridx = 2;
        constraints.gridy = 1;
        frame.getContentPane().add(timePerTurnOption2, constraints);

        timePerTurnOption3 = new JRadioButton("90 seconds");
        timePerTurnGroup.add(timePerTurnOption3);
        constraints.gridx = 4;
        constraints.gridy = 1;
        frame.getContentPane().add(timePerTurnOption3, constraints);
        
        // Add a label and dropdown for who plays first
        JLabel choosePlayerLabel = new JLabel("Choose who plays first: ");
        constraints.gridx = 0;
        constraints.gridy = 2; 
        constraints.gridwidth = 2; // Span across 3 columns
        frame.getContentPane().add(choosePlayerLabel, constraints);
        String [] choices = {"Player", "Computer", "Random"};
        JComboBox<String> whoPlaysFirstDropdown = new JComboBox<>(choices);
        whoPlaysFirstDropdown.setSelectedIndex(0);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 3; // Span across 3 columns
        frame.getContentPane().add(whoPlaysFirstDropdown, constraints);

        // Add start game button
        startGameButton = new JButton("Play Game");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 5; // Span across 3 columns
        constraints.insets = new Insets(50, 10, 0, 10);
        frame.getContentPane().add(startGameButton, constraints);

        startGameButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int timePerTurn = getTimePerTurnValue();
            
                    System.out.println("Time per turn: " + timePerTurn + " seconds");
                    System.out.println("Who plays first: " + whoPlaysFirstDropdown.getSelectedItem());

                    // Add your game initialization code here
                }
            }
        });
    }

    private int getTimePerTurnValue() {
        if (timePerTurnOption1.isSelected()) {
            return 40;
        } else if (timePerTurnOption2.isSelected()) {
            return 60;
        } else if (timePerTurnOption3.isSelected()) {
            return 90;
        } else {
            throw new IllegalStateException("Invalid time per turn selection.");
        }
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
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
