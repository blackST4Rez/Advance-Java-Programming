import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("DB: "); String db = sc.nextLine();
        System.out.print("Table: "); String table = sc.nextLine();

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, "root", "admin123");
        Statement s = c.createStatement();

        // Get columns
        ResultSet r = s.executeQuery("SELECT * FROM "+table+" LIMIT 0");
        ResultSetMetaData m = r.getMetaData();

        StringBuilder cols = new StringBuilder();
        StringBuilder vals = new StringBuilder();

        for(int i=1; i<=m.getColumnCount(); i++) {
            System.out.print(m.getColumnName(i)+": ");
            String val = sc.nextLine();

            if(cols.length()>0) { cols.append(", "); vals.append(", "); }
            cols.append(m.getColumnName(i));

            if(val.isEmpty()) vals.append("NULL");
            else if(m.getColumnTypeName(i).contains("CHAR") || m.getColumnTypeName(i).contains("TEXT"))
                vals.append("'").append(val).append("'");
            else vals.append(val);
        }

        int rows = s.executeUpdate("INSERT INTO "+table+" ("+cols+") VALUES ("+vals+")");
        System.out.println(rows>0 ? "Inserted!" : "Failed");

        // Show table
        r = s.executeQuery("SELECT * FROM "+table);
        System.out.println("\nData:");
        for(int i=1; i<=m.getColumnCount(); i++) System.out.printf("%-15s", m.getColumnName(i));
        System.out.println();
        while(r.next()) {
            for(int i=1; i<=m.getColumnCount(); i++) System.out.printf("%-15s", r.getObject(i));
            System.out.println();
        }

        c.close();
    }
}