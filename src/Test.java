import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Test
 {

    private JFrame startFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JButton playButton;
    private JButton settingsButton;
    private JButton backButton;

    public Test
    () {
        startFrame = new JFrame("Game");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(500, 500);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create start panel
        JPanel startPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Play button
        playButton = new JButton("Play Game");
        playButton.setFont(new Font("Inter", Font.PLAIN, 17));
        startPanel.add(playButton, constraints);

        // Settings button
        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Inter", Font.PLAIN, 17));
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

        startFrame.add(mainPanel);
        startFrame.setVisible(true);
    }

    private JPanel createSettingPanel() {
        // Create a panel for settings
        JPanel settingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Inter", Font.PLAIN, 17));
        settingPanel.add(backButton, constraints);

        backButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "Start");
        });

        return settingPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Test
        ::new);
    }
}