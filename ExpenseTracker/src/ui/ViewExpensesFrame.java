package ui;

import dao.ExpenseDAO;
import model.Expense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewExpensesFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private int userId;

    public ViewExpensesFrame(int userId) {
        this.userId = userId;
        setTitle("View / Manage Expenses");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new Object[]{"ID", "Title", "Category", "Amount", "Date", "Description"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnEdit = new JButton("Edit Selected");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnRefresh = new JButton("Refresh");

        JPanel panel = new JPanel();
        panel.add(btnEdit);
        panel.add(btnDelete);
        panel.add(btnRefresh);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        loadExpenses();

        // ---- DELETE FUNCTION ----
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }
            int id = (int) model.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this expense?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (ExpenseDAO.deleteExpense(id)) {
                    JOptionPane.showMessageDialog(this, "Expense deleted successfully!");
                    loadExpenses();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete expense!");
                }
            }
        });

        // ---- EDIT FUNCTION ----
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to edit.");
                return;
            }
            int id = (int) model.getValueAt(row, 0);
            String title = (String) model.getValueAt(row, 1);
            String category = (String) model.getValueAt(row, 2);
            double amount = Double.parseDouble(model.getValueAt(row, 3).toString());
            String date = model.getValueAt(row, 4).toString();
            String desc = (String) model.getValueAt(row, 5);

            new EditExpenseFrame(id, title, category, amount, date, desc, this).setVisible(true);
        });

        // ---- REFRESH FUNCTION ----
        btnRefresh.addActionListener(e -> loadExpenses());
    }

    public void loadExpenses() {
        model.setRowCount(0);
        List<Expense> list = ExpenseDAO.getExpenses(userId);
        for (Expense e : list) {
            model.addRow(new Object[]{
                    e.getId(),
                    e.getTitle(),
                    e.getCategory(),
                    e.getAmount(),
                    e.getDate(),
                    e.getDescription()
            });
        }
    }
}
