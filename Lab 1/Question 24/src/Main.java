import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JLabel statusLabel;
    private JPanel mainPanel;

    public Main() {
        super("File & Color Chooser Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        statusLabel = new JLabel("Click buttons below", SwingConstants.CENTER);
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton fileBtn = new JButton("File Chooser");
        JButton colorBtn = new JButton("Color Chooser");
        buttonPanel.add(fileBtn);
        buttonPanel.add(colorBtn);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // File Chooser
        fileBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                statusLabel.setText("Selected: " + fc.getSelectedFile().getName());
            }
        });

        // Color Chooser
        colorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Color", Color.WHITE);
            if (color != null) {
                statusLabel.setText(String.format("RGB(%d, %d, %d)",
                        color.getRed(), color.getGreen(), color.getBlue()));
                mainPanel.setBackground(color);
                statusLabel.setForeground(
                        (color.getRed()*0.299 + color.getGreen()*0.587 + color.getBlue()*0.114) > 128
                                ? Color.BLACK : Color.WHITE);
            }
        });

        add(mainPanel);
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}