import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameGUI extends JFrame {
    final private Game game;
    final private JLabel questionLabel;
    final private JPanel topPanel; 
    final private JButton btnA, btnB, btnC, btnD;
    final private JPanel buttonPanel;  
    final private QuestionManager questionM;
    final private User player;
    private int questionCorrect;
    private Timer questionTimer;
    private int timeLeft;
    private JLabel timerLabel = new JLabel("Time: 10s");
    
    public GameGUI(User player) {
        game = new Game(player);
        this.player = player;
        questionM = new QuestionManager(); //
        questionCorrect = 0;
        
        setTitle("Trivia Game");
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Choose a Category!", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 22));
        
        timerLabel = new JLabel("Time: 10", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(new EmptyBorder(30, 50, 20, 50)); 
        topPanel.add(questionLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        topPanel.add(timerLabel);
         
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 50, 50));
        buttonPanel.setBorder(new EmptyBorder(100, 100, 100, 100));

        btnA = new JButton("A");
        btnA.setFocusPainted(false);
        btnB = new JButton("B");
        btnB.setFocusPainted(false);
        btnC = new JButton("C");
        btnC.setFocusPainted(false);
        btnD = new JButton("D");
        btnD.setFocusPainted(false);

        buttonPanel.add(btnA);
        buttonPanel.add(btnB);
        buttonPanel.add(btnC);
        buttonPanel.add(btnD);
          
        add(buttonPanel, BorderLayout.CENTER);

        btnA.addActionListener(e -> answerSelected("A"));
        btnB.addActionListener(e -> answerSelected("B"));
        btnC.addActionListener(e -> answerSelected("C"));
        btnD.addActionListener(e -> answerSelected("D"));
        
        setVisible(true);

        showCategorySelectionScreen();
    }

    private void showCategorySelectionScreen() {
        getContentPane().removeAll();
        
        timerLabel.setVisible(false); 
        add(topPanel, BorderLayout.NORTH);
        
        List<Category> cat = new ArrayList<>();
        cat.addAll(questionM.getRandomCategories()); 
        
        questionLabel.setText("Choose a Category!");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel categoryPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 400, 30));

        JButton cat1 = new JButton(cat.get(0).getNameCategory());
        cat1.setPreferredSize(new Dimension(200, 150));
        cat1.setFocusPainted(false);
        JButton cat2 = new JButton(cat.get(1).getNameCategory());
        cat2.setPreferredSize(new Dimension(200, 150));
        cat2.setFocusPainted(false);
        JButton cat3 = new JButton(cat.get(2).getNameCategory());
        cat3.setPreferredSize(new Dimension(200, 150));
        cat3.setFocusPainted(false);
        
        cat1.addActionListener(e -> startCategory(cat.get(0).getId()));
        cat2.addActionListener(e -> startCategory(cat.get(1).getId()));
        cat3.addActionListener(e -> startCategory(cat.get(2).getId()));

        categoryPanel.add(cat1);
        categoryPanel.add(cat2);
        categoryPanel.add(cat3);
        
        add(categoryPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private void startCategory(int categoryId) {
        getContentPane().removeAll();

        add(topPanel,BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        game.loadQuestions(categoryId);
        Question q = game.getCurrentQuestion();
        showQuestionOnUI(q);

        revalidate();
        repaint();
    }
    
    private void loadNextQuestion() {
        Question q = game.nextQuestion();    
        if(q != null) {   
            showQuestionOnUI(q);
            return;
        }
        if (game.isGameOver()) {
            if(questionCorrect == 9){
                JOptionPane.showMessageDialog(this, "Congratulations, you won!");
                new MainMenuGUI(player).setVisible(true); 
                dispose();  // Fecha a janela de login
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Game Over!");
                new MainMenuGUI(player).setVisible(true); 
                dispose();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Level Up " + game.getCurrentLevel() + "!");
            showCategorySelectionScreen();
        }
    }
  
    private void showQuestionOnUI(Question q) {       
            timerLabel.setVisible(true);
            questionLabel.setText("<html><div style='text-align:center;'>" + q.getQuestionText() + "</div></html>");
            btnA.setText("A: " + q.getOptionA());
            btnB.setText("B: " + q.getOptionB());
            btnC.setText("C: " + q.getOptionC());
            btnD.setText("D: " + q.getOptionD());
            startTimer();
    }
    
    private void answerSelected(String option) {
        questionTimer.stop();
        boolean correct = game.checkAnswer(option);
        if (correct) {
            JOptionPane.showMessageDialog(this, "Correct answer!");
            questionCorrect+=1;
        } else {
            JOptionPane.showMessageDialog(this, "Wrong answer!");
        }
        loadNextQuestion();
    }

    private void startTimer() {
    if (questionTimer != null) {
        questionTimer.stop();
    }

    timeLeft = 10; 
    timerLabel.setText("Time: " + timeLeft);

    questionTimer = new Timer(1000, e -> {
        timeLeft--;
        timerLabel.setText("Time: " + timeLeft + "s");

        if (timeLeft <= 0) {
            questionTimer.stop();
            JOptionPane.showMessageDialog(this, "Time's up! You lost!");
            game.setGameOver(true); 
            player.incrementIncorrect();
        }
    });

    questionTimer.start();
    }

}