import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("Main Frame - Toolbar Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create toolbar
        JToolBar toolBar = new JToolBar("Control Panel");
        toolBar.setFloatable(false); // cannot be detached

        // Buttons
        JButton btnModal = new JButton("Modal Dialog");
        JButton btnModeless = new JButton("Modeless Dialog");

        // Add buttons to toolbar
        toolBar.add(btnModal);
        toolBar.addSeparator();
        toolBar.add(btnModeless);

        // Action for Modal Dialog
        btnModal.addActionListener(e -> {
            JDialog modalDialog = new JDialog(this, "Modal Dialog", true); // true = modal
            modalDialog.setSize(380, 220);
            modalDialog.setLocationRelativeTo(this);

            JLabel label = new JLabel("This is a MODAL dialog", SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 18));
            label.setForeground(new Color(0, 100, 180));

            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(ev -> modalDialog.dispose());

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.add(label, BorderLayout.CENTER);
            panel.add(closeBtn, BorderLayout.SOUTH);

            modalDialog.add(panel);
            modalDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            // Show modal dialog - blocks until closed
            modalDialog.setVisible(true);
        });

        // Action for Modeless (Non-modal) Dialog
        btnModeless.addActionListener(e -> {
            JDialog modelessDialog = new JDialog(this, "Modeless Dialog", false); // false = modeless
            modelessDialog.setSize(420, 260);
            modelessDialog.setLocationRelativeTo(this);

            JLabel label = new JLabel("This is a MODELLESS (non-modal) dialog", SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 18));
            label.setForeground(new Color(120, 0, 180));

            JTextArea textArea = new JTextArea("You can still interact with the main window!\n\nTry moving or clicking the main frame while this dialog is open.");
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textArea.setBackground(new Color(245, 245, 255));

            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(ev -> modelessDialog.dispose());

            JPanel panel = new JPanel(new BorderLayout(15, 15));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.add(label, BorderLayout.NORTH);
            panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            panel.add(closeBtn, BorderLayout.SOUTH);

            modelessDialog.add(panel);
            modelessDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            // Show modeless dialog - does NOT block
            modelessDialog.setVisible(true);
        });

        // Add toolbar to the top of the frame
        add(toolBar, BorderLayout.NORTH);

        // Optional: add some content to main frame
        JLabel welcome = new JLabel("Click buttons in the toolbar above", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        add(welcome, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Optional: Use system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                System.err.println("Failed to set look and feel");
            }

            new Main().setVisible(true);
        });
    }
}