package ui;
import javax.swing.*;
import dao.UserDAO;
import model.User;

public class RegisterFrame extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;

    public RegisterFrame() {
        setTitle("Register");
        setSize(350, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(30, 30, 80, 25);
        add(l1);

        nameField = new JTextField();
        nameField.setBounds(120, 30, 180, 25);
        add(nameField);

        JLabel l2 = new JLabel("Email:");
        l2.setBounds(30, 70, 80, 25);
        add(l2);

        emailField = new JTextField();
        emailField.setBounds(120, 70, 180, 25);
        add(emailField);

        JLabel l3 = new JLabel("Password:");
        l3.setBounds(30, 110, 80, 25);
        add(l3);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 110, 180, 25);
        add(passwordField);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(120, 160, 100, 30);
        add(registerBtn);

        registerBtn.addActionListener(e -> {
            User u = new User();
            u.setName(nameField.getText());
            u.setEmail(emailField.getText());
            u.setPassword(new String(passwordField.getPassword()));

            if (UserDAO.register(u)) {
                JOptionPane.showMessageDialog(this, "Registered successfully!");
                dispose();
                new LoginFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed!");
            }
        });
    }
}
