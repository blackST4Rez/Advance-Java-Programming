import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class Main extends JFrame {
    Connection c; JTable t; DefaultTableModel m; JTextField id,n,a,sr; JRadioButton ma,fe; JComboBox<String> cb;

    public Main() {
        setTitle("Student CRUD"); setSize(700,400); setDefaultCloseOperation(3);
        connect(); init(); load();
    }

    void connect() {
        try {
            String db = JOptionPane.showInputDialog("DB:");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, "root", "admin123");
            c.createStatement().execute("CREATE TABLE IF NOT EXISTS q37.raka37(id INT PRIMARY KEY,name VARCHAR(50),age INT,gender VARCHAR(10),course VARCHAR(50))");
        } catch(Exception e) { JOptionPane.showMessageDialog(this,"Error"); System.exit(1); }
    }

    void init() {
        setLayout(new BorderLayout());

        // Search panel
        JPanel top = new JPanel(); top.add(new JLabel("Search:")); sr=new JTextField(10);
        top.add(sr); top.add(new JButton("🔍"){{addActionListener(e->search());}});
        top.add(new JButton("🔄"){{addActionListener(e->load());}}); add(top,"North");

        // Table
        m = new DefaultTableModel(new String[]{"ID","Name","Age","Gender","Course"},0);
        add(new JScrollPane(t=new JTable(m)),"Center");

        // Form panel
        JPanel f = new JPanel(new GridLayout(0,2,5,5));
        f.add(new JLabel("ID:")); f.add(id=new JTextField(5));
        f.add(new JLabel("Name:")); f.add(n=new JTextField(10));
        f.add(new JLabel("Age:")); f.add(a=new JTextField(3));
        f.add(new JLabel("Gender:")); JPanel g=new JPanel();
        g.add(ma=new JRadioButton("M",true)); g.add(fe=new JRadioButton("F"));
        ButtonGroup bg=new ButtonGroup(); bg.add(ma); bg.add(fe); f.add(g);
        f.add(new JLabel("Course:")); f.add(cb=new JComboBox<>(new String[]{"Buisness","Journalism","Physics","Engineering"}));

        JPanel b = new JPanel(); String[] btn = {"Add","Upd","Del","Clear"};
        for(String s:btn) b.add(new JButton(s){{
            addActionListener(e->{
                if(s=="Add") op("INSERT");
                else if(s=="Upd") op("UPDATE");
                else if(s=="Del") del();
                else clear();
            });
        }});
        f.add(b); add(f,"South");

        t.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting() && t.getSelectedRow()>=0) {
                int r=t.getSelectedRow(); id.setText(t.getValueAt(r,0)+""); id.setEnabled(false);
                n.setText(t.getValueAt(r,1)+""); a.setText(t.getValueAt(r,2)+"");
                if(t.getValueAt(r,3).equals("Male")) ma.setSelected(true); else fe.setSelected(true);
                cb.setSelectedItem(t.getValueAt(r,4));
            }
        });
    }

    void load() {
        try { m.setRowCount(0);
            ResultSet r=c.createStatement().executeQuery("SELECT * FROM q37.raka37");
            while(r.next()) m.addRow(new Object[]{r.getInt(1),r.getString(2),r.getInt(3),r.getString(4),r.getString(5)});
        } catch(Exception e) {}
    }

    void op(String type) {
        try { String sql = type.equals("INSERT")?"INSERT INTO q37.raka37 VALUES(?,?,?,?,?)":
                "UPDATE q37.raka37 SET name=?,age=?,gender=?,course=? WHERE id=?";
            PreparedStatement p = c.prepareStatement(sql);
            if(type.equals("INSERT")) { p.setInt(1,Integer.parseInt(id.getText())); p.setString(2,n.getText());
                p.setInt(3,Integer.parseInt(a.getText())); p.setString(4,ma.isSelected()?"Male":"Female");
                p.setString(5,cb.getSelectedItem()+""); }
            else { p.setString(1,n.getText()); p.setInt(2,Integer.parseInt(a.getText()));
                p.setString(3,ma.isSelected()?"Male":"Female"); p.setString(4,cb.getSelectedItem()+"");
                p.setInt(5,Integer.parseInt(id.getText())); }
            p.executeUpdate(); JOptionPane.showMessageDialog(this,"Done"); load(); clear();
        } catch(Exception ex) { JOptionPane.showMessageDialog(this,"Failed"+ex.getMessage()); }
    }

    void del() {
        int r=t.getSelectedRow(); if(r<0) return;
        if(JOptionPane.showConfirmDialog(this,"Delete?")==0) try {
            PreparedStatement p=c.prepareStatement("DELETE FROM raka37 WHERE id=?");
            p.setInt(1,(int)t.getValueAt(r,0)); p.executeUpdate(); load(); clear();
        } catch(Exception e) {}
    }

    void search() {
        try { m.setRowCount(0); String s=sr.getText();
            PreparedStatement p=c.prepareStatement("SELECT * FROM q37.raka37 WHERE id=? OR name LIKE ?");
            try { p.setInt(1,Integer.parseInt(s)); } catch(Exception ex) { p.setInt(1,-1); }
            p.setString(2,"%"+s+"%"); ResultSet r=p.executeQuery();
            while(r.next()) m.addRow(new Object[]{r.getInt(1),r.getString(2),r.getInt(3),r.getString(4),r.getString(5)});
        } catch(Exception e) {}
    }

    void clear() { id.setText(""); id.setEnabled(true); n.setText(""); a.setText(""); ma.setSelected(true); cb.setSelectedIndex(0); t.clearSelection(); }

    public static void main(String[] a) { new Main().setVisible(true); }
}