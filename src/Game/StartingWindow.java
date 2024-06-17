package Game;
import javax.imageio.ImageIO;
import javax.swing.*;

import BotThings.Bot;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class StartingWindow implements KeyListener {
private JFrame startFrame;
private JPanel mainPanel;
private CardLayout cardLayout;
private GamePanel gamePanel;
private BotPanel BotPanel;
private JButton playvsBotButton, settingsButton, BotvsBotButton;
private JRadioButton timePerTurnOption1, timePerTurnOption2, timePerTurnOption3;
private JRadioButton difficultyEasy, difficultyNightmare;
private JRadioButton difficultyEasy1, difficultyNightmare1;
private JRadioButton difficultyEasy2, difficultyNightmare2;
private int difficultyChoice, difficultyChoice1, difficultyChoice2, timePerTurn;
private Image bgImage;

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

public StartingWindow() {
    initialize();
}

private void initialize() {
    startFrame = new JFrame();
    startFrame.setBounds(100, 100, 1000, 800);
    startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    startFrame.addKeyListener(this);
    startFrame.requestFocusInWindow();
    startFrame.setFocusable(true);
    startFrame.setLocationRelativeTo(null);

    try {
        bgImage = ImageIO.read(getClass().getResourceAsStream("/Graphics/Background2-no button.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    mainPanel = new JPanel();
    cardLayout = new CardLayout();
    mainPanel.setLayout(cardLayout);
    startFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
    startFrame.setResizable(false);
    mainPanel.add(createStartPanel(), "Start");
    mainPanel.add(createBotvsBotPanel(), "BotvsBot");
    mainPanel.add(createSettingPanel(), "Settings");
   
    cardLayout.show(mainPanel, "Start");
}

private JPanel createStartPanel() {
    JPanel startPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, null);
        }
    };
    startPanel.setLayout(null);
    
    // Add play vs bot button
    ImageIcon playvsBot = new ImageIcon(getClass().getResource("/Graphics/playvsBot.png"));
    playvsBotButton = new JButton(playvsBot);
    playvsBotButton.setFocusPainted(false);
    playvsBotButton.setRolloverEnabled(false);
    playvsBotButton.setMargin(new Insets(0, 0, 0, 0));
    playvsBotButton.setBorder(null);
    playvsBotButton.setContentAreaFilled(false);
    playvsBotButton.setBounds(350, 600, 275, 41);
    startPanel.add(playvsBotButton);

    // Add hover effect
    ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Graphics/hoverPlayvsBot.png"));
    playvsBotButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            playvsBotButton.setIcon(hoverIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            playvsBotButton.setIcon(playvsBot);
        }
    });

    // Add bot vs bot button
    ImageIcon BotvsBot = new ImageIcon(getClass().getResource("/Graphics/BotVsBot.png"));
    BotvsBotButton = new JButton(BotvsBot);
    BotvsBotButton.setFocusPainted(false);
    BotvsBotButton.setRolloverEnabled(false);
    BotvsBotButton.setMargin(new Insets(0, 0, 0, 0));
    BotvsBotButton.setBorder(null);
    BotvsBotButton.setContentAreaFilled(false);
    BotvsBotButton.setBounds(350, 650, 274, 41);
    startPanel.add(BotvsBotButton);

    // Add hover effect
    ImageIcon hoverBotvsBot = new ImageIcon(getClass().getResource("/Graphics/hoverBotvsBot.png"));
    BotvsBotButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            BotvsBotButton.setIcon(hoverBotvsBot);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            BotvsBotButton.setIcon(BotvsBot);
        }
    });

    // SETTINGS BUTTON
    ImageIcon settingIcon = new ImageIcon(getClass().getResource("/Graphics/settings.png"));
    settingsButton = new JButton(settingIcon);
    settingsButton.setFocusPainted(false);
    settingsButton.setRolloverEnabled(false);
    settingsButton.setContentAreaFilled(false);
    settingsButton.setBounds(350, 700, 275, 41);
   
    startPanel.add(settingsButton);

    ImageIcon hoverSettingIcon = new ImageIcon(getClass().getResource("/Graphics/hoverSettings.png"));
    settingsButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            settingsButton.setIcon(hoverSettingIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            settingsButton.setIcon(settingIcon);
        }
    });

    settingsButton.setMargin(new Insets(20, 20, 20, 20));
    settingsButton.setBorder(null);

    playvsBotButton.addActionListener(e -> startGame());
    settingsButton.addActionListener(e -> cardLayout.show(mainPanel, "Settings"));
    BotvsBotButton.addActionListener(e -> cardLayout.show(mainPanel, "BotvsBot"));

    return startPanel;
}

// BOT VS BOT PANEL
private JPanel createBotvsBotPanel() {
    JPanel BotvsBotPanel = new JPanel();
    BotvsBotPanel.setBounds(100, 100, 1000, 800);
    BotvsBotPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    JLabel difficultyLabel1 = new JLabel("Choose AI difficulty for Bot 1: ");
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    BotvsBotPanel.add(difficultyLabel1, constraints);

    difficultyEasy1 = new JRadioButton("Easy");
    difficultyEasy1.setSelected(true);
    ButtonGroup difficultyGroup1 = new ButtonGroup();
    difficultyGroup1.add(difficultyEasy1);
    constraints.gridx = 0;
    constraints.gridy = 3;
    BotvsBotPanel.add(difficultyEasy1, constraints);

    difficultyNightmare1 = new JRadioButton("Nightmare");
    difficultyGroup1.add(difficultyNightmare1);
    constraints.gridx = 2;
    constraints.gridy = 3;
    BotvsBotPanel.add(difficultyNightmare1, constraints);

    JLabel difficultyLabel2 = new JLabel("Choose AI difficulty for Bot 2: ");
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 2;
    BotvsBotPanel.add(difficultyLabel2, constraints);

    difficultyEasy2 = new JRadioButton("Easy");
    difficultyEasy2.setSelected(true);
    ButtonGroup difficultyGroup2 = new ButtonGroup();
    difficultyGroup2.add(difficultyEasy2);
    constraints.gridx = 0;
    constraints.gridy = 5;
    BotvsBotPanel.add(difficultyEasy2, constraints);

    difficultyNightmare2 = new JRadioButton("Nightmare");
    difficultyGroup2.add(difficultyNightmare2);
    constraints.gridx = 2;
    constraints.gridy = 5;
    BotvsBotPanel.add(difficultyNightmare2, constraints);

    JButton startGameButton = new JButton("Play Game");
    startGameButton.addActionListener(e -> {
        if (difficultyEasy1.isSelected()) {
            difficultyChoice1 = 0; // Easy
        } else if (difficultyNightmare1.isSelected()) {
            difficultyChoice1 = 1; // Nightmare
        }

        if (difficultyEasy2.isSelected()) {
            difficultyChoice2 = 0; // Easy
        } else if (difficultyNightmare2.isSelected()) {
            difficultyChoice2 = 1; // Nightmare
        }
      
        BotPanel = new BotPanel(difficultyChoice1, difficultyChoice2);
        startFrame.getContentPane().removeAll();
        startFrame.getContentPane().add(BotPanel);
        startFrame.revalidate();
        startFrame.repaint();
        BotPanel.startBotVsBotGame();
    });
    constraints.gridx = 0;
    constraints.gridy = 7;
    constraints.gridwidth = 2;
    BotvsBotPanel.add(startGameButton, constraints);

    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> cardLayout.show(mainPanel, "Start"));
    constraints.gridx = 3;
    constraints.gridy = 7;
    constraints.gridwidth = 2;
    BotvsBotPanel.add(backButton, constraints);

    return BotvsBotPanel;
}

// SETTINGS PANEL
private JPanel createSettingPanel() {
    JPanel settingPanel = new JPanel();
    settingPanel.setBounds(100, 100, 400, 650);
    settingPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    JLabel difficultyLabel = new JLabel("Choose AI difficulty: ");
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    settingPanel.add(difficultyLabel, constraints);

    difficultyEasy = new JRadioButton("Easy");
    difficultyEasy.setSelected(true);
    ButtonGroup difficultyGroup = new ButtonGroup();
    difficultyGroup.add(difficultyEasy);
    constraints.gridx = 0;
    constraints.gridy = 1;
    settingPanel.add(difficultyEasy, constraints);

    difficultyNightmare = new JRadioButton("Nightmare");
    difficultyGroup.add(difficultyNightmare);
    constraints.gridx = 2;
    constraints.gridy = 1;
    settingPanel.add(difficultyNightmare, constraints);

    JLabel timePerTurnLabel = new JLabel("Choose time limit: ");
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    settingPanel.add(timePerTurnLabel, constraints);

    timePerTurnOption1 = new JRadioButton("10 seconds");
    timePerTurnOption1.setSelected(true);
    ButtonGroup timePerTurnGroup = new ButtonGroup();
    timePerTurnGroup.add(timePerTurnOption1);
    constraints.gridx = 0;
    constraints.gridy = 3;
    settingPanel.add(timePerTurnOption1, constraints);

    timePerTurnOption2 = new JRadioButton("15 seconds");
    timePerTurnGroup.add(timePerTurnOption2);
    constraints.gridx = 2;
    constraints.gridy = 3;
    settingPanel.add(timePerTurnOption2, constraints);

    timePerTurnOption3 = new JRadioButton("20 seconds");
    timePerTurnGroup.add(timePerTurnOption3);
    constraints.gridx = 4;
    constraints.gridy = 3;
    settingPanel.add(timePerTurnOption3, constraints);

    JButton startGameButton = new JButton("Start Game");
    startGameButton.addActionListener(e -> {
        if (difficultyEasy.isSelected()) {
            difficultyChoice = 0; // Easy
        } else if (difficultyNightmare.isSelected()) {
            difficultyChoice = 1; // Nightmare
        }

        timePerTurn = getTimePerTurnValue();
        startGame();
    });
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 2;
    settingPanel.add(startGameButton, constraints);

    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> cardLayout.show(mainPanel, "Start"));
    constraints.gridx = 2;
    constraints.gridy = 4;
    constraints.gridwidth = 2;
    settingPanel.add(backButton, constraints);

    return settingPanel;
}

public void startGame() {
    if(playvsBotButton.isSelected()){
        gamePanel = new GamePanel(0, 10000);
        startFrame.getContentPane().removeAll();
        startFrame.getContentPane().add(gamePanel);
        startFrame.revalidate();
        startFrame.repaint();
        }
        else{
            gamePanel = new GamePanel(difficultyChoice, getTimePerTurnValue());
            startFrame.getContentPane().removeAll();
            startFrame.getContentPane().add(gamePanel);
            startFrame.revalidate();
            startFrame.repaint();
        }
    }



public int getTimePerTurnValue() {
    if (timePerTurnOption1.isSelected()) {
        return 10;
    } else if (timePerTurnOption2.isSelected()) {
        return 15;
    } else if (timePerTurnOption3.isSelected()) {
        return 20;
    } else {
        throw new IllegalStateException("Invalid time per turn selection.");
    }
}

@Override
public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode());
}

@Override
public void keyReleased(KeyEvent e) {
}

@Override
public void keyTyped(KeyEvent e) {
}
}