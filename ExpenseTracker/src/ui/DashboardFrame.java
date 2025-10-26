package ui;

import javax.swing.*;
import model.User;

public class DashboardFrame extends JFrame {
    private User user;

    public DashboardFrame(User u) {
        this.user = u;
        setTitle("Dashboard - " + u.getName());
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addBtn = new JButton("Add Expense");
        addBtn.setBounds(120, 60, 150, 40);
        add(addBtn);

        JButton viewBtn = new JButton("View Expenses");
        viewBtn.setBounds(120, 120, 150, 40);
        add(viewBtn);

        // ✅ Add Expense Action
        addBtn.addActionListener(e -> new AddExpenseFrame(u.getId()));

        // ✅ View Expense Action
        viewBtn.addActionListener(e -> new ViewExpensesFrame(u.getId()).setVisible(true));
    }
}
