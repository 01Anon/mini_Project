package db;  // Places this class inside a package named 'db'

import java.sql.*;  // Imports JDBC classes (Connection, DriverManager, SQLException, etc.)

public class DBConnection {
    // Constants for database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USER = "root";
    private static final String PASS = "Wolfie@186";

    // Static method to establish and return a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
