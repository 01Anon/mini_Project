package ui;

import javax.swing.*;
import dao.UserDAO;
import model.User;

public class ChangePasswordFrame extends JFrame {

    private JPasswordField oldPassField, newPassField, confirmPassField;
    private User user;

    public ChangePasswordFrame(User u) {
        this.user = u;
        setTitle("Change Password");
        setSize(350, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblOld = new JLabel("Old Password:");
        lblOld.setBounds(30, 40, 120, 30);
        add(lblOld);

        oldPassField = new JPasswordField();
        oldPassField.setBounds(160, 40, 150, 30);
        add(oldPassField);

        JLabel lblNew = new JLabel("New Password:");
        lblNew.setBounds(30, 90, 120, 30);
        add(lblNew);

        newPassField = new JPasswordField();
        newPassField.setBounds(160, 90, 150, 30);
        add(newPassField);

        JLabel lblConfirm = new JLabel("Confirm Password:");
        lblConfirm.setBounds(30, 140, 120, 30);
        add(lblConfirm);

        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(160, 140, 150, 30);
        add(confirmPassField);

        JButton saveBtn = new JButton("Change Password");
        saveBtn.setBounds(100, 200, 150, 35);
        add(saveBtn);

        saveBtn.addActionListener(e -> handleChangePassword());
    }

    private void handleChangePassword() {
        String oldPass = new String(oldPassField.getPassword());
        String newPass = new String(newPassField.getPassword());
        String confirmPass = new String(confirmPassField.getPassword());

        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        if (!UserDAO.validateUser(user.getEmail(), oldPass)) {
            JOptionPane.showMessageDialog(this, "Old password is incorrect!");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "New passwords do not match!");
            return;
        }

        if (UserDAO.changePassword(user.getId(), newPass)) {
            JOptionPane.showMessageDialog(this, "Password updated successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error updating password.");
        }
    }
}
