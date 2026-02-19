import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection c;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db(), "root", "admin123");
        c.createStatement().execute("CREATE TABLE IF NOT EXISTS student(roll INT PRIMARY KEY,name VARCHAR(50),age INT,grade VARCHAR(5))");

        while(true) {
            System.out.println("\n1.Insert 2.Display 3.Search 4.Update 5.Delete 6.Exit");
            System.out.print("Choice: ");
            int ch = s.nextInt(); s.nextLine();

            if(ch==1) insert();
            else if(ch==2) display("SELECT * FROM q31.raka31");
            else if(ch==3) search();
            else if(ch==4) update();
            else if(ch==5) delete();
            else break;
        }
        c.close();
    }

    static String db() { System.out.print("DB: "); return s.nextLine(); }

    static void insert() throws SQLException {
        PreparedStatement p = c.prepareStatement("INSERT INTO q31.raka31 VALUES(?,?,?,?)");
        System.out.print("Roll: "); p.setInt(1, s.nextInt()); s.nextLine();
        System.out.print("Name: "); p.setString(2, s.nextLine());
        System.out.print("Age: "); p.setInt(3, s.nextInt()); s.nextLine();
        System.out.print("Grade: "); p.setString(4, s.nextLine());
        System.out.println(p.executeUpdate()>0?"Inserted":"Failed");
    }

    static void display(String sql) throws SQLException {
        ResultSet r = c.createStatement().executeQuery(sql);
        ResultSetMetaData m = r.getMetaData();
        System.out.println();
        for(int i=1;i<=m.getColumnCount();i++) System.out.printf("%-10s", m.getColumnName(i));
        System.out.println("\n"+"-".repeat(40));
        while(r.next()) {
            for(int i=1;i<=m.getColumnCount();i++) System.out.printf("%-10s", r.getObject(i));
            System.out.println();
        }
    }

    static void search() throws SQLException {
        PreparedStatement p = c.prepareStatement("SELECT * FROM q31.raka31 WHERE roll=?");
        System.out.print("Roll: "); p.setInt(1, s.nextInt());
        ResultSet r = p.executeQuery();
        if(r.next()) System.out.println(r.getInt(1)+" "+r.getString(2)+" "+r.getInt(3)+" "+r.getString(4));
        else System.out.println("Not found");
    }

    static void update() throws SQLException {
        PreparedStatement p = c.prepareStatement("UPDATE q31.raka31 SET name=?,age=?,grade=? WHERE roll=?");
        System.out.print("Roll to update: "); int roll = s.nextInt(); s.nextLine();
        System.out.print("New Name: "); p.setString(1, s.nextLine());
        System.out.print("New Age: "); p.setInt(2, s.nextInt()); s.nextLine();
        System.out.print("New Grade: "); p.setString(3, s.nextLine());
        p.setInt(4, roll);
        System.out.println(p.executeUpdate()>0?"Updated":"Failed");
    }

    static void delete() throws SQLException {
        PreparedStatement p = c.prepareStatement("DELETE FROM q31.raka31 WHERE roll=?");
        System.out.print("Roll to delete: "); p.setInt(1, s.nextInt());
        System.out.println(p.executeUpdate()>0?"Deleted":"Failed");
    }
}