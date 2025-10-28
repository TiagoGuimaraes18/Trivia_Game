import java.awt.*;
import javax.swing.*;

public class LoginGUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;
    private UserManager userManager;  

    public LoginGUI() {
        userManager = new UserManager();

        setTitle("TriviaGame");
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        add(title, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 19));
        panel.add(lblUser, gbc);

        gbc.gridx = 1;
        txtUsername = new JTextField(15); 
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 19));
        panel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.PLAIN, 19));
        panel.add(lblPass, gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(90, 25));
        btnLogin.setFocusPainted(false);   
        panel.add(btnLogin, gbc);

        gbc.gridx = 1;
        btnRegister = new JButton("Register");
        btnRegister.setPreferredSize(new Dimension(90, 25));
        btnRegister.setFocusPainted(false);   
        panel.add(btnRegister, gbc);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
       
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            User user = userManager.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(LoginGUI.this, "Login successful!");
                new MainMenuGUI(user).setVisible(true); 
                dispose();  
            } else {
                JOptionPane.showMessageDialog(LoginGUI.this, "Login failed! Please check your credentials.");
            }
        });
        
        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginGUI.this, "Please fill all fields.");
                return;
            }

            User newUser = new User(username, password,0,0,0,0);
            if (userManager.register(newUser)) {
                JOptionPane.showMessageDialog(LoginGUI.this, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(LoginGUI.this, "Error registering the user!");
            }
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

