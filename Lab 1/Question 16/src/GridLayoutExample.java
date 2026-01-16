
import javax.swing.*;
import java.awt.*;

public class GridLayoutExample extends JFrame {
    public GridLayoutExample() {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        setTitle("Grid Layout");
        setLayout(new GridLayout(5, 2, 4, 5));
        for (int i = 0; i < 10; ++i) {
            add(new JButton("Button " + (i+1)));
        }
        setSize(300, 200);
        setDefaultCloseOperation(3);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutExample();
    }
}