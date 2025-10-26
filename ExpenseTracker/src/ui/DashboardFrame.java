package ui;

import javax.swing.*;
import model.User;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private User user;
    private ProfilePanel profilePanel;

    public DashboardFrame(User u) {
        this.user = u;
        setTitle("Dashboard - " + u.getName());
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Buttons
        JButton addBtn = new JButton("Add Expense");
        addBtn.setBounds(300, 60, 200, 40);
        add(addBtn);

        JButton viewBtn = new JButton("View Expenses");
        viewBtn.setBounds(300, 120, 200, 40);
        add(viewBtn);

        JButton changePassBtn = new JButton("Change Password");
        changePassBtn.setBounds(300, 180, 200, 40);
        add(changePassBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(300, 240, 200, 40);
        add(logoutBtn);

        // Profile Section
        profilePanel = new ProfilePanel(u);
        profilePanel.setBounds(30, 40, 240, 280);
        add(profilePanel);

        // Button actions
        addBtn.addActionListener(e -> new AddExpenseFrame(u.getId()));
        viewBtn.addActionListener(e -> new ViewExpensesFrame(u.getId()).setVisible(true));
        changePassBtn.addActionListener(e -> new ChangePasswordFrame(u).setVisible(true));

        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }
}
