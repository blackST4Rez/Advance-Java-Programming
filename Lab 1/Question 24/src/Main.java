import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main extends JFrame {

    private JLabel statusLabel;

    public Main() {
        super("File & Color Chooser Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);

        // Main panel with nice padding
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Status label at the top
        statusLabel = new JLabel("Click buttons below to open choosers", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));

        JButton fileBtn = new JButton("File Chooser");
        fileBtn.setPreferredSize(new Dimension(180, 50));
        fileBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JButton colorBtn = new JButton("Color Chooser");
        colorBtn.setPreferredSize(new Dimension(180, 50));
        colorBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        buttonPanel.add(fileBtn);
        buttonPanel.add(colorBtn);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Action for File Chooser
        fileBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a File or Folder");

            // Optional: set some useful filters
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif"));
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "All Files", "*.*"));

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                statusLabel.setText("Selected: " + selectedFile.getAbsolutePath());
                statusLabel.setForeground(new Color(0, 120, 0));
            } else if (result == JFileChooser.CANCEL_OPTION) {
                statusLabel.setText("File selection cancelled");
                statusLabel.setForeground(Color.GRAY);
            }
        });

        // Action for Color Chooser
        colorBtn.addActionListener(e -> {
            Color initialColor = Color.WHITE;
            Color selectedColor = JColorChooser.showDialog(
                    this,
                    "Choose a Color",
                    initialColor
            );

            if (selectedColor != null) {
                statusLabel.setText("Selected Color: " +
                        String.format("RGB(%d, %d, %d)",
                                selectedColor.getRed(),
                                selectedColor.getGreen(),
                                selectedColor.getBlue()));

                // Optional: change background to show selected color
                mainPanel.setBackground(selectedColor);
                statusLabel.setForeground(getContrastColor(selectedColor));
            } else {
                statusLabel.setText("Color selection cancelled");
                statusLabel.setForeground(Color.GRAY);
                mainPanel.setBackground(null); // reset
            }
        });

        add(mainPanel);
    }

    // Helper method to choose black or white text depending on background
    private Color getContrastColor(Color color) {
        double y = (color.getRed() * 0.299) +
                (color.getGreen() * 0.587) +
                (color.getBlue() * 0.114);
        return y > 128 ? Color.BLACK : Color.WHITE;
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