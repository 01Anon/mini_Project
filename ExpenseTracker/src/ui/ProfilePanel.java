package ui;

import javax.swing.*;
import dao.ExpenseDAO;
import model.User;

import java.awt.*;

public class ProfilePanel extends JPanel {

    private JLabel nameLabel, emailLabel, totalExpensesLabel, totalAmountLabel;

    public ProfilePanel(User user) {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Profile Summary"));
        setBackground(new Color(245, 245, 245));

        nameLabel = new JLabel("Name: " + user.getName());
        emailLabel = new JLabel("Email: " + user.getEmail());

        int expenseCount = ExpenseDAO.getExpenseCount(user.getId());
        double totalAmount = ExpenseDAO.getTotalAmount(user.getId());

        totalExpensesLabel = new JLabel("Total Expenses: " + expenseCount);
        totalAmountLabel = new JLabel("Total Amount: ₹" + totalAmount);

        Font f = new Font("Segoe UI", Font.PLAIN, 14);
        nameLabel.setFont(f);
        emailLabel.setFont(f);
        totalExpensesLabel.setFont(f);
        totalAmountLabel.setFont(f);

        add(nameLabel);
        add(emailLabel);
        add(totalExpensesLabel);
        add(totalAmountLabel);
    }

    public void refresh(User user) {
        int expenseCount = ExpenseDAO.getExpenseCount(user.getId());
        double totalAmount = ExpenseDAO.getTotalAmount(user.getId());

        totalExpensesLabel.setText("Total Expenses: " + expenseCount);
        totalAmountLabel.setText("Total Amount: ₹" + totalAmount);
    }
}
