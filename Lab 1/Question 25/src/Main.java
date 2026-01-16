import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JDesktopPane desktopPane;

    public Main() {
        super("JInternalFrame Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        // 1. Create the desktop pane (the container for internal frames)
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(240, 240, 245));

        // 2. Create a menu bar
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // 3. Create a toolbar
        JToolBar toolBar = createToolBar();
        add(toolBar, BorderLayout.NORTH);

        // 4. Add the desktop pane to the center
        add(desktopPane, BorderLayout.CENTER);

        // Optional: status bar at the bottom
        JLabel statusLabel = new JLabel(" Ready", SwingConstants.LEFT);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        add(statusLabel, BorderLayout.SOUTH);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Window menu
        JMenu windowMenu = new JMenu("Window");

        JMenuItem newDocItem = new JMenuItem("New Document");
        newDocItem.addActionListener(e -> addNewInternalFrame("Document"));

        JMenuItem newImageItem = new JMenuItem("New Image Viewer");
        newImageItem.addActionListener(e -> addNewInternalFrame("Image"));

        JMenuItem newCalcItem = new JMenuItem("New Calculator");
        newCalcItem.addActionListener(e -> addNewInternalFrame("Calculator"));

        JMenuItem tileItem = new JMenuItem("Tile Windows");
        tileItem.addActionListener(e -> tileInternalFrames());

        JMenuItem cascadeItem = new JMenuItem("Cascade Windows");
        cascadeItem.addActionListener(e -> cascadeInternalFrames());

        windowMenu.add(newDocItem);
        windowMenu.add(newImageItem);
        windowMenu.add(newCalcItem);
        windowMenu.addSeparator();
        windowMenu.add(tileItem);
        windowMenu.add(cascadeItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        return menuBar;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar("Tools");
        toolBar.setFloatable(false);

        JButton newDocBtn = new JButton("New Document");
        newDocBtn.addActionListener(e -> addNewInternalFrame("Document"));

        JButton newImageBtn = new JButton("New Image");
        newImageBtn.addActionListener(e -> addNewInternalFrame("Image"));

        JButton tileBtn = new JButton("Tile");
        tileBtn.addActionListener(e -> tileInternalFrames());

        JButton cascadeBtn = new JButton("Cascade");
        cascadeBtn.addActionListener(e -> cascadeInternalFrames());

        toolBar.add(newDocBtn);
        toolBar.add(newImageBtn);
        toolBar.addSeparator();
        toolBar.add(tileBtn);
        toolBar.add(cascadeBtn);

        return toolBar;
    }

    private void addNewInternalFrame(String type) {
        JInternalFrame frame = new JInternalFrame(
                type + " - " + (getNextNumber(type)),
                true,    // resizable
                true,    // closable
                true,    // maximizable
                true     // iconifiable
        );

        frame.setSize(420, 320);
        frame.setLocation(30 + (desktopPane.getAllFrames().length * 30),
                30 + (desktopPane.getAllFrames().length * 30));

        // Different content depending on type
        switch (type) {
            case "Document":
                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                frame.add(new JScrollPane(textArea));
                break;

            case "Image":
                JLabel label = new JLabel("Image Viewer", SwingConstants.CENTER);
                label.setFont(new Font("Segoe UI", Font.BOLD, 36));
                label.setForeground(new Color(100, 150, 200));
                frame.add(label);
                break;

            case "Calculator":
                JPanel calcPanel = createSimpleCalculator();
                frame.add(calcPanel);
                break;
        }

        desktopPane.add(frame);
        frame.setVisible(true);

        // Bring to front and select
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            // ignore
        }
    }

    private int docCount = 0;
    private int imgCount = 0;
    private int calcCount = 0;

    private int getNextNumber(String type) {
        switch (type) {
            case "Document": return ++docCount;
            case "Image":    return ++imgCount;
            case "Calculator": return ++calcCount;
            default: return 1;
        }
    }

    private JPanel createSimpleCalculator() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField display = new JTextField("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Segoe UI", Font.BOLD, 24));
        display.setEditable(false);
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String btnText : buttons) {
            JButton btn = new JButton(btnText);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            buttonPanel.add(btn);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }

    private void tileInternalFrames() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        if (frames.length == 0) return;

        int cols = (int) Math.ceil(Math.sqrt(frames.length));
        int rows = (int) Math.ceil((double) frames.length / cols);

        Dimension size = desktopPane.getSize();
        int w = size.width / cols;
        int h = size.height / rows;

        int x = 0, y = 0, count = 0;

        for (JInternalFrame frame : frames) {
            frame.setBounds(x * w, y * h, w, h);
            x++;
            if (x >= cols) {
                x = 0;
                y++;
            }
            count++;
            if (count >= frames.length) break;
        }
    }

    private void cascadeInternalFrames() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        if (frames.length == 0) return;

        int offset = 30;
        for (int i = 0; i < frames.length; i++) {
            frames[i].setBounds(
                    i * offset,
                    i * offset,
                    450,
                    350
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Optional: system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Could not set look and feel");
            }

            new Main().setVisible(true);
        });
    }
}