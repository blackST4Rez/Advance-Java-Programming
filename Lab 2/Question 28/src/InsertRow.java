import java.sql.*;

public class InsertRow {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Q28";
    private static final String USER = "root";
    private static final String PASS = "admin123";

    public static void main(String[] args) {
        String tableName = "raka28";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            // Insert a new row
            String sql = "INSERT INTO " + tableName + " (id, name, age, grade) " +
                    "VALUES (3, 'Raka Maharjan', 24, 'A')";

            int rowsAffected = stmt.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Row inserted successfully!");
                System.out.println("Rows affected: " + rowsAffected);
            }

            // Verify by displaying the table
            displayTable(conn, tableName);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void displayTable(Connection conn, String tableName) {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            System.out.println("\nUpdated table data:");
            System.out.println("-".repeat(60));

            for (int i = 1; i <= cols; i++)
                System.out.printf("%-15s", rsmd.getColumnName(i));

            System.out.println("\n" + "-".repeat(60));

            while (rs.next()) {
                for (int i = 1; i <= cols; i++)
                    System.out.printf("%-15s", rs.getObject(i));
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error displaying table: " + e.getMessage());
        }
    }
}