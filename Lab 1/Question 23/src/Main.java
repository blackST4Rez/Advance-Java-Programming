import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("Dialog Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnModal = new JButton("Modal Dialog");
        JButton btnModeless = new JButton("Modeless Dialog");

        toolBar.add(btnModal);
        toolBar.addSeparator();
        toolBar.add(btnModeless);

        // Modal Dialog
        btnModal.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Modal", true);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.add(new JLabel("Modal Dialog - Blocks main window", SwingConstants.CENTER));

            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(ev -> dialog.dispose());
            panel.add(closeBtn, BorderLayout.SOUTH);

            dialog.add(panel);
            dialog.setVisible(true);
        });

        // Modeless Dialog
        btnModeless.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Modeless", false);
            dialog.setSize(350, 150);
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.add(new JLabel("Modeless - Main window still active", SwingConstants.CENTER));

            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(ev -> dialog.dispose());
            panel.add(closeBtn, BorderLayout.SOUTH);

            dialog.add(panel);
            dialog.setVisible(true);
        });

        add(toolBar, BorderLayout.NORTH);
        add(new JLabel("Click toolbar buttons", SwingConstants.CENTER), BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        System.out.println("\nLab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}