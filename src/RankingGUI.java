import java.awt.*;
import javax.swing.*;


public class RankingGUI extends JFrame {
    UserManager um = new UserManager();
    final private JButton btnBack;

    public RankingGUI(User user) {
        setTitle("Trivia Game" );
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel rankingPanel = new JPanel();
        rankingPanel.setLayout(new BoxLayout(rankingPanel, BoxLayout.Y_AXIS));
        rankingPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("Ranking", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 45));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(new Color(30, 30, 30)); 
        rankingPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        rankingPanel.add(lblTitle);
        rankingPanel.add(Box.createRigidArea(new Dimension(0, 50)));

         
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 18)); 
        textArea.append(String.format("%-20s | %-15s | %-15s\n", "USERNAME", "WON", "CORRECTS"));
        textArea.append("----------------------------------------------------------------------------------\n");

        for (User u : um.getAllUsers()) {
            textArea.append(String.format("%-20s | %-15d | %-15d\n", u.getUsername(), u.getVictory(), u.getCorrect()));
        }

        Dimension buttonSize = new Dimension(80, 60);
        btnBack = createButton("Back", buttonSize);

        btnBack.addActionListener(e -> {
            new MainMenuGUI(user).setVisible(true);
            dispose();             
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(rankingPanel, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.CENTER);
        
        add(mainPanel, BorderLayout.CENTER);
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