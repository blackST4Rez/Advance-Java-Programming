
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExternalListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        JOptionPane.showMessageDialog(null,"Handled from external class");
    }
}