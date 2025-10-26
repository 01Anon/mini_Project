package ui;
import javax.swing.*;
import dao.ExpenseDAO;
import model.Expense;
import java.util.Date;

public class AddExpenseFrame extends JFrame {
    public AddExpenseFrame(int userId) {
        setTitle("Add Expense");
        setSize(350, 350);
        setLayout(null);

        JLabel l1 = new JLabel("Title:");
        l1.setBounds(30, 30, 100, 25);
        add(l1);
        JTextField titleField = new JTextField();
        titleField.setBounds(120, 30, 180, 25);
        add(titleField);

        JLabel l2 = new JLabel("Category:");
        l2.setBounds(30, 70, 100, 25);
        add(l2);
        JTextField categoryField = new JTextField();
        categoryField.setBounds(120, 70, 180, 25);
        add(categoryField);

        JLabel l3 = new JLabel("Amount:");
        l3.setBounds(30, 110, 100, 25);
        add(l3);
        JTextField amountField = new JTextField();
        amountField.setBounds(120, 110, 180, 25);
        add(amountField);

        JLabel l4 = new JLabel("Description:");
        l4.setBounds(30, 150, 100, 25);
        add(l4);
        JTextArea descField = new JTextArea();
        descField.setBounds(120, 150, 180, 80);
        add(descField);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(120, 250, 100, 30);
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                Expense ex = new Expense();
                ex.setUserId(userId);
                ex.setTitle(titleField.getText());
                ex.setCategory(categoryField.getText());
                ex.setAmount(Double.parseDouble(amountField.getText()));
                ex.setDate(new Date());
                ex.setDescription(descField.getText());

                if (ExpenseDAO.addExpense(ex))
                    JOptionPane.showMessageDialog(this, "Expense Added!");
                else
                    JOptionPane.showMessageDialog(this, "Error adding expense!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid data!");
            }
        });

        setVisible(true);
    }
}
