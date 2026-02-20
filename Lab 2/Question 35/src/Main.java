import java.sql.*;
import javax.sql.rowset.*;

public class Main {
    public static void main(String[] args) throws Exception {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("DB: ");
        String db = sc.nextLine();
        String url = "jdbc:mysql://localhost:3306/" + db;

        // Setup
        try (Connection c = DriverManager.getConnection(url, "root", "admin123")) {
            Statement s = c.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS raka35emp(id INT PRIMARY KEY,name VARCHAR(50),salary INT)");

            ResultSet r = s.executeQuery("SELECT COUNT(*) FROM raka35emp");
            r.next();
            if(r.getInt(1) == 0) {
                s.execute("INSERT INTO raka35emp VALUES (1,'John',50000),(2,'Jane',60000),(3,'Bob',55000)");
                System.out.println("Sample data inserted");
            }
        }

        while(true) {
            System.out.print("\n1.Jdbc 2.Cached 3.Exit: ");
            int ch = sc.nextInt();
            if(ch == 1) jdbcRowSet(url);
            else if(ch == 2) cachedRowSet(url);
            else if(ch == 3) { System.out.println("Bye!"); break; }
        }
        sc.close();
    }

    static void jdbcRowSet(String url) throws Exception {
        JdbcRowSet j = RowSetProvider.newFactory().createJdbcRowSet();
        j.setUrl(url); j.setUsername("root"); j.setPassword("admin123");
        j.setCommand("SELECT * FROM raka35emp"); j.execute();

        System.out.println("\n📊 JDBC RowSet Data:");
        while(j.next())
            System.out.println(j.getInt(1) + "  " + j.getString(2) + "  " + j.getInt(3));
        j.close();
    }

    static void cachedRowSet(String url) throws Exception {
        CachedRowSet c = RowSetProvider.newFactory().createCachedRowSet();
        c.setUrl(url); c.setUsername("root"); c.setPassword("admin123");
        c.setCommand("SELECT * FROM raka35emp"); c.execute();

        if(!c.next()) {
            System.out.println("\nNo data!");
            c.close();
            return;
        }

        c.beforeFirst();
        System.out.println("\nOriginal Data:");
        while(c.next())
            System.out.println(c.getInt(1) + "  " + c.getString(2) + "  " + c.getInt(3));

        c.beforeFirst();
        c.next();
        int id = c.getInt(1);
        String oldName = c.getString(2);
        int oldSalary = c.getInt(3);

        System.out.println("\n✏️ Modifying ID " + id + "...");
        c.updateString(2, oldName + " (Updated)");
        c.updateInt(3, oldSalary + 5000);
        c.updateRow();

        // Sync with conflict handling
        try (Connection conn = DriverManager.getConnection(url, "root", "admin123")) {
            c.acceptChanges(conn);
            System.out.println("Synced!");
        } catch (SyncProviderException e) {
            System.out.println("Sync error: " + e.getMessage());
            // Simple retry
            try (Connection conn = DriverManager.getConnection(url, "root", "admin123")) {
                c.acceptChanges(conn);
                System.out.println("Synced on retry!");
            }
        }

        c.beforeFirst();
        System.out.println("\nUpdated Data:");
        while(c.next())
            System.out.println(c.getInt(1) + "  " + c.getString(2) + "  " + c.getInt(3));

        c.close();
    }
}