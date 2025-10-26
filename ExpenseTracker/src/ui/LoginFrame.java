package ui;

import javax.swing.*;
import dao.UserDAO;
import model.User;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(360, 230);
        setLocationRelativeTo(null); // Center window
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Email Label
        JLabel l1 = new JLabel("Email:");
        l1.setBounds(30, 30, 80, 25);
        add(l1);

        // Email Field
        emailField = new JTextField();
        emailField.setBounds(120, 30, 200, 25);
        add(emailField);

        // Password Label
        JLabel l2 = new JLabel("Password:");
        l2.setBounds(30, 70, 80, 25);
        add(l2);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 200, 25);
        add(passwordField);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 110, 90, 30);
        add(loginBtn);

        // Register Button
        JButton regBtn = new JButton("Register");
        regBtn.setBounds(230, 110, 90, 30);
        add(regBtn);

        // Login Button Action
        loginBtn.addActionListener(e -> handleLogin());

        // Register Button Action
        regBtn.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });
    }

    // 🔹 Separate login logic into its own method (cleaner & safer)
    private void handleLogin() {
        String email = emailField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User u = UserDAO.login(email, pass);

        if (u != null) {
            JOptionPane.showMessageDialog(this, "Welcome " + u.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new DashboardFrame(u).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Entry point for testing this frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
