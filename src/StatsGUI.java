import java.awt.*;
import javax.swing.*;

public class StatsGUI extends JFrame {
    final private JButton btnBack;

    public StatsGUI(User player) {
        setTitle("Trivia Game" );
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("Stats", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 45));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(new Color(30, 30, 30)); 
        statsPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        statsPanel.add(lblTitle);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.BOLD, 30)); 
        textArea.append(String.format("Total Answers        : %d  \n", player.getAnswers()));
        textArea.append("----------------------------------------------------------------------------------\n");
        textArea.append(String.format("Correct Answers    : %d \n", player.getCorrect()));
        textArea.append("----------------------------------------------------------------------------------\n");
        textArea.append(String.format("Incorrect Answers  : %d \n", player.getIncorrect()));
        textArea.append("----------------------------------------------------------------------------------\n");
        textArea.append(String.format("Percentage              : %d%%   \n", (Math.abs(player.getIncorrect()-player.getCorrect())*100/player.getAnswers())));
        textArea.append("----------------------------------------------------------------------------------\n");
        textArea.append(String.format("Won Games             : %d  \n", player.getVictory()));
        textArea.append("----------------------------------------------------------------------------------\n");

        Dimension buttonSize = new Dimension(80, 60);
        btnBack = createButton("Back", buttonSize);

        btnBack.addActionListener(e -> {
            new MainMenuGUI(player).setVisible(true);
            dispose();              
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(statsPanel, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.CENTER);

        add(mainPanel,BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
        setVisible(true);
    }
        
    private JButton createButton(String text, Dimension size) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(size);
        btn.setMaximumSize(size);
        btn.setMinimumSize(size);
        btn.setFocusPainted(false); 
        return btn;
    }
}