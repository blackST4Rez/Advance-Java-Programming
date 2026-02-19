import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("DB: "); String db = sc.nextLine();
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, "root", "admin123");

        // Create table if not exists
        Statement st = c.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS q33.raka33(emp_id INT PRIMARY KEY, emp_name VARCHAR(50), emp_salary DECIMAL(10,2))");

        // Insert sample data if empty
        ResultSet r = st.executeQuery("SELECT COUNT(*) FROM q33.raka33");
        r.next();
        if(r.getInt(1)==0) {
            st.executeUpdate("INSERT INTO q33.raka33 VALUES (101,'Rahul',75000),(102,'Priya',82000),(103,'Amit',45000)");
            System.out.println("Sample data inserted");
        }

        System.out.print("\nEmp ID: ");
        int id = sc.nextInt();

        PreparedStatement p = c.prepareStatement("SELECT * FROM q33.raka33 WHERE emp_id=?");
        p.setInt(1, id);
        r = p.executeQuery();

        System.out.println("\n"+"=".repeat(40));
        if(r.next()) {
            System.out.printf("ID: %d\nName: %s\nSalary: $%.2f\n", r.getInt(1), r.getString(2), r.getDouble(3));
            System.out.printf("Annual: $%.2f\n", r.getDouble(3)*12);
        } else {
            System.out.println("Not found!");
            r = st.executeQuery("SELECT emp_id FROM salary");
            System.out.print("Available: ");
            while(r.next()) System.out.print(r.getInt(1)+" ");
        }
        System.out.println("\n"+"=".repeat(40));
        c.close();
    }
}