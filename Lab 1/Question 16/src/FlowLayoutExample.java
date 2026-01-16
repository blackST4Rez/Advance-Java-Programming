
import javax.swing.*;
import java.awt.*;

public class FlowLayoutExample extends JFrame {
    public FlowLayoutExample(){

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        this.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));
        this.add(new JButton("Button 1"));
        this.add(new JButton("Button 2"));
        this.add(new JButton("Button 3"));
        this.add(new JButton("Button 4"));
        this.add(new JButton("Button 5"));
        this.setSize(300,400);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FlowLayoutExample();
    }
}