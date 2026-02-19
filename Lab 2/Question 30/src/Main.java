import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Database: ");
            String db = sc.nextLine();

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, "root", "admin123");

            System.out.print("Roll number: ");
            int roll = sc.nextInt();

            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM q30.raka30 WHERE roll_no=" + roll);

            System.out.println("\n" + "=".repeat(50));
            if(r.next()) {
                System.out.println("Roll No: " + r.getInt(1));
                System.out.println("Course : " + r.getString(2));
                System.out.println("Marks  : " + r.getInt(3));
            } else {
                System.out.println("No record found for roll no: " + roll);
            }
            System.out.println("=".repeat(50));

            c.close();

        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}