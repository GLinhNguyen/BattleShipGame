package Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class StartingWindow implements KeyListener {

    private JRadioButton timePerTurnOption1;
    private JRadioButton timePerTurnOption2;
    private JRadioButton timePerTurnOption3;
    private JRadioButton difficultyEasy;
    private JRadioButton difficultyNightmare;

    private JFrame startFrame;
    private JButton playButton;
    private JButton settingsButton;
    private Image bgImage;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int difficultyChoice = 0; // Default to Easy
    private boolean playerFirstTurn = true; // Default to Player
    private int timePerTurn = 10000; // Default to 40 seconds
    public static GamePanel_test gamePanel;

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
        startFrame = new JFrame("Battleship Game");
        startFrame.setBounds(100, 100, 1000, 800);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(1000, 800);
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);
        startFrame.setFocusable(true);
        startFrame.addKeyListener(this);

        try {
            bgImage = ImageIO.read(getClass().getResourceAsStream("/Graphics/Background2-no button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        startFrame.add(mainPanel);

        JPanel startPanel = createStartPanel();
        JPanel settingPanel = createSettingPanel();
        mainPanel.add(startPanel, "Start");
        mainPanel.add(settingPanel, "Settings");

        startFrame.setVisible(true);
    }

    private JPanel createStartPanel() {
        JPanel startPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, this);
            }
        };
        startPanel.setLayout(null);

        // Add play button
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource("/Graphics/Play.png"));
        playButton = new JButton(buttonIcon);
        playButton.setFocusPainted(false);
        playButton.setRolloverEnabled(false);
        playButton.setMargin(new Insets(0, 0, 0, 0));
        playButton.setBorder(null);
        playButton.setBounds(320, 600, 300, 50);
        startPanel.add(playButton);

        // Add hover effect
        ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Graphics/hoverPlay.png"));
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setIcon(buttonIcon);
            }
        });

        // Add settings button
        ImageIcon settingIcon = new ImageIcon(getClass().getResource("/Graphics/settings.png"));
        settingsButton = new JButton(settingIcon);
        settingsButton.setFocusPainted(false);
        settingsButton.setRolloverEnabled(false);
        settingsButton.setBounds(320, 680, 300, 50);
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

        settingsButton.setMargin(new Insets(0, 0, 0, 0));
        settingsButton.setBorder(null);

        playButton.addActionListener(e -> startGame());
        settingsButton.addActionListener(e -> cardLayout.show(mainPanel, "Settings"));

        return startPanel;
    }

    private JPanel createSettingPanel() {
        JPanel settingPanel = new JPanel();
        settingPanel.setBounds(100, 100, 400, 650);
        settingPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel timePerTurnLabel = new JLabel("Choose time limit: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        settingPanel.add(timePerTurnLabel, constraints);

        timePerTurnOption1 = new JRadioButton("10 seconds");
        timePerTurnOption1.setSelected(true);
        ButtonGroup timePerTurnGroup = new ButtonGroup();
        timePerTurnGroup.add(timePerTurnOption1);
        constraints.gridx = 0;
        constraints.gridy = 1;
        settingPanel.add(timePerTurnOption1, constraints);

        timePerTurnOption2 = new JRadioButton("15 seconds");
        timePerTurnGroup.add(timePerTurnOption2);
        constraints.gridx = 2;
        constraints.gridy = 1;
        settingPanel.add(timePerTurnOption2, constraints);

        timePerTurnOption3 = new JRadioButton("20 seconds");
        timePerTurnGroup.add(timePerTurnOption3);
        constraints.gridx = 4;
        constraints.gridy = 1;
        settingPanel.add(timePerTurnOption3, constraints);

        JLabel difficultyLabel = new JLabel("Choose AI difficulty: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        settingPanel.add(difficultyLabel, constraints);

        difficultyEasy = new JRadioButton("Easy");
        difficultyEasy.setSelected(true);
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(difficultyEasy);
        constraints.gridx = 0;
        constraints.gridy = 3;
        settingPanel.add(difficultyEasy, constraints);

        difficultyNightmare = new JRadioButton("Nightmare");
        difficultyGroup.add(difficultyNightmare);
        constraints.gridx = 2;
        constraints.gridy = 3;
        settingPanel.add(difficultyNightmare, constraints);


        JButton startGameButton = new JButton("Play Game");
        startGameButton.addActionListener(e -> {
            if (difficultyEasy.isSelected()) {
                difficultyChoice = 0; // Easy
            } else if (difficultyNightmare.isSelected()) {
                difficultyChoice = 1; // Nightmare
            }

            timePerTurn = getTimePerTurnValue();
            startGame();
        });

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 5;
        constraints.insets = new Insets(50, 10, 0, 10);
        settingPanel.add(startGameButton, constraints);

        return settingPanel;
    }

    private void startGame() {
        if(playButton.isSelected()){
        gamePanel = new GamePanel_test(0, 10000);
        startFrame.getContentPane().removeAll();
        startFrame.getContentPane().add(gamePanel);
        startFrame.revalidate();
        startFrame.repaint();
        }
        else{
            gamePanel = new GamePanel_test(difficultyChoice, timePerTurn);
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