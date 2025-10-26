package ui;

import dao.ExpenseDAO;
import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditExpenseFrame extends JFrame {
    public EditExpenseFrame(int id, String title, String category, double amount, String date, String desc, ViewExpensesFrame parent) {
        setTitle("Edit Expense");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField tfTitle = new JTextField(title, 20);
        JTextField tfCategory = new JTextField(category, 20);
        JTextField tfAmount = new JTextField(String.valueOf(amount), 20);
        JTextField tfDate = new JTextField(date, 20);
        JTextField tfDesc = new JTextField(desc, 20);

        JButton btnSave = new JButton("Save Changes");

        setLayout(new GridLayout(6, 2, 10, 10));
        add(new JLabel("Title:"));
        add(tfTitle);
        add(new JLabel("Category:"));
        add(tfCategory);
        add(new JLabel("Amount:"));
        add(tfAmount);
        add(new JLabel("Date (yyyy-mm-dd):"));
        add(tfDate);
        add(new JLabel("Description:"));
        add(tfDesc);
        add(new JLabel(""));
        add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                Expense exp = new Expense();
                exp.setId(id);
                exp.setTitle(tfTitle.getText());
                exp.setCategory(tfCategory.getText());
                exp.setAmount(Double.parseDouble(tfAmount.getText()));
                exp.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tfDate.getText()));
                exp.setDescription(tfDesc.getText());

                if (ExpenseDAO.updateExpense(exp)) {
                    JOptionPane.showMessageDialog(this, "Expense updated successfully!");
                    parent.loadExpenses();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update expense!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid data entered!");
            }
        });
    }
}
