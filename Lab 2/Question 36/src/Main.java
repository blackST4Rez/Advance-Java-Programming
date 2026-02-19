import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Q36";
        String user = "root";
        String pass = "admin123";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {

            // Create test table
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts (id INT PRIMARY KEY, balance INT)");
            stmt.execute("DELETE FROM accounts");
            stmt.execute("INSERT INTO accounts VALUES (1, 1000), (2, 500)");

            System.out.println("Initial balances: " + getBalance(conn, 1) + ", " + getBalance(conn, 2));

            // Start transaction
            conn.setAutoCommit(false);

            try {
                // Transfer money
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 200 WHERE id = 1");
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 200 WHERE id = 2");

                // Check if valid
                if(getBalance(conn, 1) >= 0) {
                    conn.commit();
                    System.out.println("Transaction committed!");
                } else {
                    conn.rollback();
                    System.out.println("Transaction rolled back (negative balance)");
                }

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Final balances: " + getBalance(conn, 1) + ", " + getBalance(conn, 2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static int getBalance(Connection conn, int id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE id = " + id);
        rs.next();
        return rs.getInt(1);
    }
}