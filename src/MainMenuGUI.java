import java.awt.*;
import javax.swing.*;

public class MainMenuGUI extends JFrame {
    final private JButton btnPlay;
    final private JButton btnRanking;
    final private JButton btnStatistics;
    final private JButton btnExit;
 
    public MainMenuGUI(User user) {
        setTitle("TriviaGame" );
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.setOpaque(false); 

        JLabel lblTitle = new JLabel("Trivia Game", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 45));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(new Color(30, 30, 30)); // cor do texto
        menuPanel.add(Box.createRigidArea(new Dimension(0, 70)));
        menuPanel.add(lblTitle);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        // Espa�o superior
        menuPanel.add(Box.createRigidArea(new Dimension(0, 200)));

        Dimension buttonSize = new Dimension(300, 60);

        btnPlay = createMenuButton("Play", buttonSize);
        btnPlay.setFocusPainted(false); 
        btnRanking = createMenuButton("Ranking", buttonSize);
        btnRanking.setFocusPainted(false);
        btnStatistics = createMenuButton("Stats", buttonSize);
        btnStatistics.setFocusPainted(false);
        btnExit = createMenuButton("Exit", buttonSize);
        btnExit.setFocusPainted(false);

        menuPanel.add(btnPlay);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(btnRanking);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(btnStatistics);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(btnExit);

        // Adiciona o painel centralizado
        add(menuPanel, BorderLayout.CENTER);
    
        btnPlay.addActionListener(e -> {
        new GameGUI(user).setVisible(true);
        dispose();
        });

        btnRanking.addActionListener(e -> {
            new RankingGUI(user).setVisible(true);
        });

        btnStatistics.addActionListener(e -> {
            new StatsGUI(user).setVisible(true);
        });

        btnExit.addActionListener(e -> {
            int opt = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private JButton createMenuButton(String text, Dimension size) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(size);
        btn.setMaximumSize(size);
        btn.setMinimumSize(size);
        btn.setFocusPainted(false); 
        return btn;
    }
}


