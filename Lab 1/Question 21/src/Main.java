import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        SwingUtilities.invokeLater(() -> {
            MenuBar frame = new MenuBar();
            frame.setTitle("Simple Window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);

            // Add a basic menu bar
            JMenuBar mb = new JMenuBar();
            JMenu file = new JMenu("File");
            file.add(new JMenuItem("Exit"));
            mb.add(file);

            frame.setJMenuBar(mb);
            frame.setVisible(true);
        });
    }
}