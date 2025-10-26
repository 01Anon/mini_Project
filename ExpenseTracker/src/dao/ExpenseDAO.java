package dao;
import db.DBConnection;
import model.Expense;
import java.sql.*;
import java.util.*;

public class ExpenseDAO {

    public static boolean addExpense(Expense e) {
        String sql = "INSERT INTO expenses (user_id, title, category, amount, date, description ) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, e.getUserId());
            pst.setString(2, e.getTitle());
            pst.setString(3, e.getCategory());
            pst.setDouble(4, e.getAmount());
            pst.setDate(5, new java.sql.Date(e.getDate().getTime()));
            pst.setString(6, e.getDescription());
            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Expense> getExpenses(int userId) {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM expenses WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Expense e = new Expense();
                e.setId(rs.getInt("id"));
                e.setUserId(userId);
                e.setTitle(rs.getString("title"));
                e.setCategory(rs.getString("category"));
                e.setAmount(rs.getDouble("amount"));
                e.setDate(rs.getDate("date"));
                e.setDescription(rs.getString("description"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static boolean deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateExpense(Expense e) {
        String sql = "UPDATE expenses SET title = ?, category = ?, amount = ?, date = ?, description = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, e.getTitle());
            pst.setString(2, e.getCategory());
            pst.setDouble(3, e.getAmount());
            pst.setDate(4, new java.sql.Date(e.getDate().getTime()));
            pst.setString(5, e.getDescription());
            pst.setInt(6, e.getId());

            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static int getExpenseCount(int userId) {
        String sql = "SELECT COUNT(*) FROM expenses WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getTotalAmount(int userId) {
        String sql = "SELECT SUM(amount) FROM expenses WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

}
