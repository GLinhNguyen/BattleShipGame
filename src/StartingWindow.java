import Game.GamePanel_test;

import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import static javax.imageio.ImageIO.read;

public class StartingWindow implements KeyListener {

    private JRadioButton timePerTurnOption1;
    private JRadioButton timePerTurnOption2;
    private JRadioButton timePerTurnOption3;

    private JFrame startFrame;
    private JButton playButton;
    private JButton settingsButton;
    private Image bgImage;

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
    // Initialize window
    private void initialize() {
        startFrame = new JFrame();
        startFrame.setBounds(100, 100, 400, 600);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(400, 600);
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);

        // Create a panel with an image background
        class ImagePanel extends JPanel {

            private ImageIcon backgroundImage;

            public ImagePanel() {
                backgroundImage = new ImageIcon(getClass().getResource("/Graphics/Background.png"));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, this);
            }
        }

        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        startFrame.add(mainPanel);

        ImagePanel startPanel = new ImagePanel();
        startPanel.setLayout(null);

        // Add play button
        final ImageIcon buttonIcon = new ImageIcon(getClass().getResource("/Graphics/Play.png"));

        playButton = new JButton(buttonIcon);
        playButton.setFocusPainted(false);
        playButton.setRolloverEnabled(false);

        playButton.setMargin(new Insets(0, 0, 0, 0));
        playButton.setBorder(null);
        playButton.setBounds(0,200, 400, 400);
        playButton.setPreferredSize(playButton.getSize());
        System.out.println(playButton.getSize());
        startPanel.add(playButton);


        // Add hover effect
        final ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Graphics/hoverPlay.png"));

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setIcon(hoverIcon);
                playButton.setMargin(new Insets(0, 0, 0, 0));
                playButton.setBorder(null);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setIcon(buttonIcon);
                playButton.setMargin(new Insets(0, 0, 0, 0));
                playButton.setBorder(null);
            }
        });


        ImageIcon settingIcon = new ImageIcon(getClass().getResource("/Graphics/settings.png"));

        settingsButton = new JButton(settingIcon);
        settingsButton.setFocusPainted(false);
        settingsButton.setRolloverEnabled(false);
        settingsButton.setBounds(100, 100, 200, 200);
        startPanel.add(settingsButton);

        final ImageIcon hoverSettingIcon = new ImageIcon(getClass().getResource("/Graphics/hoverSettings.png"));
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingsButton.setIcon(hoverSettingIcon);
                settingsButton.setMargin(new Insets(0, 0, 0, 0));
                settingsButton.setBorder(null);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsButton.setIcon(settingIcon);
                settingsButton.setMargin(new Insets(0, 0, 0, 0));
                settingsButton.setBorder(null);

            }
        });

        settingsButton.setMargin(new Insets(0, 0, 0, 0));
        settingsButton.setBorder(null);
        mainPanel.add(startPanel, "Start");

        playButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(gamePanel, "Game");
        });

        settingsButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "Settings");
        });

        JPanel settingPanel = createSettingPanel();
        mainPanel.add(settingPanel, "Settings");

        startFrame.setVisible(true);
    }

    private JPanel createSettingPanel() {
        JPanel settingPanel = new JPanel();
        settingPanel.setBounds(100, 100, 400, 650);
        settingPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel timePerTurnLabel = new JLabel("Choose time per turn: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        settingPanel.add(timePerTurnLabel, constraints);
        timePerTurnOption1 = new JRadioButton("40 seconds");
        timePerTurnOption1.setSelected(true);
        ButtonGroup timePerTurnGroup = new ButtonGroup();
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

        JLabel choosePlayerLabel = new JLabel("Choose who plays first: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        settingPanel.add(choosePlayerLabel, constraints);
        String[] choices = { "Player", "Computer", "Random" };
        JComboBox<String> whoPlaysFirstDropdown = new JComboBox<>(choices);
        whoPlaysFirstDropdown.setSelectedIndex(0);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        settingPanel.add(whoPlaysFirstDropdown, constraints);

        JButton startGameButton = new JButton("Play Game");

        // Add start game button
        startGameButton = new JButton("Play Game");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 5; // Span across 3 columns
        constraints.insets = new Insets(50, 10, 0, 10);
        settingPanel.add(startGameButton, constraints);

        return settingPanel;
    }
    public int getTimePerTurnValue() {
        if (timePerTurnOption1.isSelected()) {
            return 40000;
        } else if (timePerTurnOption2.isSelected()) {
            return 60000;
        } else if (timePerTurnOption3.isSelected()) {
            return 90000;
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
