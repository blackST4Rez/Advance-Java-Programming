import javax.swing.*;

public class ExternalHandler extends JFrame {
    private JButton btn;

    public ExternalHandler() {

        btn = new JButton("External Click");

        // Attach external listener (assuming ExternalListener exists)
        btn.addActionListener(new ExternalListener());

        // Add button to frame (using default BorderLayout)
        add(btn);

        setTitle("External Event Handler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 3 = EXIT_ON_CLOSE, but use constant
        setSize(300, 300);
        setLocationRelativeTo(null);     // ← nice touch: center on screen
        setVisible(true);
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
        // Run GUI on Event Dispatch Thread (very important!)
        SwingUtilities.invokeLater(() -> {
            new ExternalHandler();
        });
    }
}