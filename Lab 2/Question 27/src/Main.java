import java.sql.*;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Q27";
    private static final String USER = "root";
    private static final String PASS = "admin123";

    public static void main(String[] args) {
        String tableName = "raka27";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            System.out.println("\nData from table: " + tableName);
            System.out.println("=".repeat(80));

            for (int i = 1; i <= columnCount; i++)
                System.out.printf("%-20s", rsmd.getColumnName(i));

            System.out.println("\n" + "-".repeat(80));

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++)
                    System.out.printf("%-20s", rs.getObject(i) == null ? "NULL" : rs.getObject(i));
                System.out.println();
            }

            System.out.println("=".repeat(80) + "\nData retrieval completed!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}