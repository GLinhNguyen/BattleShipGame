import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class StartingWindow implements KeyListener {

    private JRadioButton timePerTurnOption1;
    private JRadioButton timePerTurnOption2;
    private JRadioButton timePerTurnOption3;
    private JButton startGameButton;

    private JFrame startFrame;
    private JButton playButton;
    private JButton settingsButton;
    
    public  Image bgImage;
    
    
    
     public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StartingWindow window = new StartingWindow();
                window.startFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private GamePanel gamePanel;

    // Create a JFram with GamePanel inside, attach key listener
    public StartingWindow() {
        initialize();
    }

    private void initialize(){
        
        startFrame = new JFrame();
        startFrame.setBounds(100, 100, 400, 600);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(400,600);
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);

        // Load the background image
        try {
            bgImage = ImageIO.read(new File("/Users/giahuy/Documents/GitHub/BattleShipGame/Graphics/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a custom JPanel class
        final Image bgImage = this.bgImage;
        class ImagePanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }

        // Create a main panel to hold all the components
        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        startFrame.add(mainPanel);

        // Create start panel
        ImagePanel startPanel = new ImagePanel();
        startPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        

        // Add components to the start panel

        // Title
        JLabel title = new JLabel("Battleship Game");
        title.setFont(new Font("Mighty Souly", Font.PLAIN, 30));
        title.setBackground(Color.white);
        title.setForeground(Color.decode("#7F6DF2"));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5; // Span across 5 columns
        constraints.insets = new Insets(0, 0, 100, 0);
        startPanel.add(title, constraints);
       
        // Play button
        // Load the button image
        final ImageIcon buttonIcon = new ImageIcon("/Users/giahuy/Documents/GitHub/BattleShipGame/Graphics/playButton.png");
        
        
        playButton = new JButton(buttonIcon);
        playButton.setFocusPainted(false);
        playButton.setRolloverEnabled(false);

        // Load the hover image
        final ImageIcon hoverIcon = new ImageIcon("/Users/giahuy/Documents/GitHub/BattleShipGame/Graphics/hoverPLay.png");
        
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setIcon(hoverIcon);
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.insets = new Insets(0, 0, 10, 0);
                playButton.setMargin(new Insets(0, 0, 0, 0));
                playButton.setBorder(null);
                constraints.fill = GridBagConstraints.HORIZONTAL;
                startPanel.add(playButton, constraints);
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setIcon(buttonIcon);
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.insets = new Insets(0, 0, 10, 0);
                playButton.setMargin(new Insets(0, 0, 0, 0));
                playButton.setBorder(null);
                constraints.fill = GridBagConstraints.HORIZONTAL;
                startPanel.add(playButton, constraints);
            }
        });
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.insets = new Insets(0, 0, 10, 0);
            playButton.setMargin(new Insets(0, 0, 0, 0));
            playButton.setBorder(null);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            startPanel.add(playButton, constraints);
        
        

        
 
        // Settings button
        ImageIcon settingIcon = new ImageIcon("/Users/giahuy/Documents/GitHub/BattleShipGame/Graphics/settingButton.png");
        
        settingsButton = new JButton(settingIcon);
        constraints.gridx = 0;
        constraints.gridy = 2;
        settingsButton.setMargin(new Insets(0, 0, 0, 0));
        settingsButton.setBorder(null);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        startPanel.add(settingsButton, constraints);

        // Add the start panel to the main panel
        mainPanel.add(startPanel, "Start");

        playButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "Game");
        });
        
        settingsButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "Settings");
        });

        // Create a panel for settings
        JPanel settingPanel = createSettingPanel();
        mainPanel.add(settingPanel, "Settings");

        startFrame.setVisible(true);
    }

    private JPanel createSettingPanel() {

            // Create a panel for settings
            JPanel settingPanel = new JPanel();
            settingPanel.setBounds(100, 100, 400, 650);
            settingPanel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            
            // Add radio buttons for time per turn
            ButtonGroup timePerTurnGroup = new ButtonGroup();

            // Add a label and radio buttons for time per turn
            JLabel timePerTurnLabel = new JLabel("Choose time per turn: ");
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2; // Span across 5 columns
            settingPanel.add(timePerTurnLabel, constraints);
            timePerTurnOption1 = new JRadioButton("40 seconds");
            timePerTurnOption1.setSelected(true);
            timePerTurnGroup.add(timePerTurnOption1);
            constraints.gridx = 0;
            constraints.gridy = 1;  
            settingPanel.add(timePerTurnOption1, constraints);
            
            timePerTurnOption2 = new JRadioButton("60 seconds");
            timePerTurnGroup.add(timePerTurnOption2);
            constraints.gridx = 2;
            constraints.gridy = 1;
            settingPanel.add(timePerTurnOption2, constraints);

            timePerTurnOption3 = new JRadioButton("90 seconds");
            timePerTurnGroup.add(timePerTurnOption3);
            constraints.gridx = 4;
            constraints.gridy = 1;
            settingPanel.add(timePerTurnOption3, constraints);
            
            // Add a label and dropdown for who plays first
            JLabel choosePlayerLabel = new JLabel("Choose who plays first: ");
            constraints.gridx = 0;
            constraints.gridy = 2; 
            constraints.gridwidth = 2; // Span across 3 columns
            settingPanel.add(choosePlayerLabel, constraints);
            String [] choices = {"Player", "Computer", "Random"};
            JComboBox<String> whoPlaysFirstDropdown = new JComboBox<>(choices);
            whoPlaysFirstDropdown.setSelectedIndex(0);
            constraints.gridx = 2;
            constraints.gridy = 2;
            constraints.gridwidth = 3; // Span across 3 columns
            settingPanel.add(whoPlaysFirstDropdown, constraints);

            // Add start game button
            startGameButton = new JButton("Play Game");
            constraints.gridx = 1;
            constraints.gridy = 3;
            constraints.gridwidth = 5; // Span across 3 columns
            constraints.insets = new Insets(50, 10, 0, 10);
            settingPanel.add(startGameButton, constraints);

            return settingPanel;
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

    // MouseListener methods
    
}
