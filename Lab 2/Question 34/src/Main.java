import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection c;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db(), "root", "admin123");
        Statement st = c.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS emp (id INT PRIMARY KEY, name VARCHAR(50), salary INT, dept VARCHAR(20))");
        st.execute("CREATE TABLE IF NOT EXISTS dept (id INT PRIMARY KEY, name VARCHAR(50), loc VARCHAR(20))");

        ResultSet r = st.executeQuery("SELECT COUNT(*) FROM emp");
        r.next();
        if(r.getInt(1)==0) {
            st.execute("INSERT INTO emp VALUES (1,'John',50000,'IT'),(2,'Jane',60000,'HR'),(3,'Bob',55000,'Finance')");
            st.execute("INSERT INTO dept VALUES (1,'IT','NY'),(2,'HR','CHI'),(3,'Finance','LA')");
        }

        while(true) {
            System.out.print("\n1.Scroll 2.Update 3.Multiple 4.Exit: ");
            int ch = s.nextInt(); s.nextLine();
            if(ch==1) scroll();
            else if(ch==2) update();
            else if(ch==3) multi();
            else break;
        }
        c.close();
    }

    static String db() { System.out.print("DB: "); return s.nextLine(); }

    static void scroll() throws SQLException {
        Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet r = st.executeQuery("SELECT * FROM emp");
        r.last(); System.out.println("Last: "+r.getInt(1)+" "+r.getString(2));
        r.first(); System.out.println("First: "+r.getInt(1)+" "+r.getString(2));
        r.absolute(2); System.out.println("Row2: "+r.getInt(1)+" "+r.getString(2));
        r.relative(1); System.out.println("+1: "+r.getInt(1)+" "+r.getString(2));
        System.out.println("Total: "+r.getRow());
        r.close(); st.close();
    }

    static void update() throws SQLException {
        Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet r = st.executeQuery("SELECT * FROM emp");
        System.out.print("1.Up 2.Ins 3.Del: ");
        int ch = s.nextInt(); s.nextLine();

        if(ch==1) {
            System.out.print("ID: "); int id = s.nextInt(); s.nextLine();
            while(r.next()) if(r.getInt(1)==id) {
                System.out.print("Name: "); r.updateString(2, s.nextLine());
                System.out.print("Salary: "); r.updateInt(3, s.nextInt()); s.nextLine();
                r.updateRow();
                System.out.println("Updated");
                break;
            }
        } else if(ch==2) {
            r.moveToInsertRow();
            System.out.print("ID: "); r.updateInt(1, s.nextInt()); s.nextLine();
            System.out.print("Name: "); r.updateString(2, s.nextLine());
            System.out.print("Salary: "); r.updateInt(3, s.nextInt()); s.nextLine();
            r.insertRow();
            System.out.println("Inserted");
        } else if(ch==3) {
            System.out.print("ID: "); int id = s.nextInt();
            while(r.next()) if(r.getInt(1)==id) { r.deleteRow(); System.out.println("Deleted"); break; }
        }
        r.close(); st.close();
    }

    static void multi() throws SQLException {
        Statement st = c.createStatement();
        String[] q = {"SELECT * FROM emp", "SELECT * FROM dept", "SELECT dept,COUNT(*) FROM emp GROUP BY dept"};
        for(int i=0; i<3; i++) {
            System.out.println("\n#"+(i+1));
            ResultSet r = st.executeQuery(q[i]);
            ResultSetMetaData m = r.getMetaData();
            for(int j=1; j<=m.getColumnCount(); j++) System.out.printf("%-10s", m.getColumnName(j));
            System.out.println();
            while(r.next()) {
                for(int j=1; j<=m.getColumnCount(); j++) System.out.printf("%-10s", r.getObject(j));
                System.out.println();
            }
            r.close();
        }
        st.close();
    }
}