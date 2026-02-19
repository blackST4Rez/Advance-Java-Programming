import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("DB: "); String db = sc.nextLine();
        System.out.print("Table: "); String table = sc.nextLine();

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, "root", "admin123");
        PreparedStatement p = c.prepareStatement("INSERT INTO "+table+" VALUES(?,?,?,?)");

        int total = 0;
        String choice;

        do {
            System.out.println("\nRecord #"+(total+1));
            System.out.print("Roll: "); p.setInt(1, sc.nextInt()); sc.nextLine();
            System.out.print("Name: "); p.setString(2, sc.nextLine());
            System.out.print("Age: "); p.setInt(3, sc.nextInt()); sc.nextLine();
            System.out.print("Grade: "); p.setString(4, sc.nextLine());

            p.executeUpdate();
            total++;
            System.out.println("Inserted! Total: "+total);

            System.out.print("Add more? (y/n): ");
            choice = sc.nextLine();

        } while(choice.equalsIgnoreCase("y"));

        System.out.println("\nTotal: "+total);

        ResultSet r = c.createStatement().executeQuery("SELECT * FROM "+table);
        while(r.next())
            System.out.println(r.getInt(1)+" "+r.getString(2)+" "+r.getInt(3)+" "+r.getString(4));

        c.close();
    }
}