import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ComplexSwingDemo extends JFrame {

    public ComplexSwingDemo() {
        super("Swing Components Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        // Main panel with tabs for better organization
        JTabbedPane tabbedPane = new JTabbedPane();

        // ── TAB 1: All components together ───────────────────────────────
        JPanel mainPanel = createMainDemoPanel();
        tabbedPane.addTab("All Components", mainPanel);

        // ── TAB 2: Just the Tree ─────────────────────────────────────────
        tabbedPane.addTab("Tree View", new JScrollPane(createSampleTree()));

        add(tabbedPane);
    }

    private JPanel createMainDemoPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ── TOP: Slider + Progress Bar ───────────────────────────────
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 0, 15));

        // Slider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(false);

        JLabel sliderValueLabel = new JLabel("Slider Value: 30", SwingConstants.CENTER);
        sliderValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Slider event handling
        slider.addChangeListener(e -> {
            if (!slider.getValueIsAdjusting()) {
                int value = slider.getValue();
                sliderValueLabel.setText("Slider Value: " + value);
                progressBar.setValue(value);
            }
        });

        JPanel sliderPanel = new JPanel(new BorderLayout(10, 10));
        sliderPanel.add(new JLabel("Volume / Progress Control:"), BorderLayout.NORTH);
        sliderPanel.add(slider, BorderLayout.CENTER);
        sliderPanel.add(sliderValueLabel, BorderLayout.SOUTH);

        // Progress Bar
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(30);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        topPanel.add(sliderPanel);
        topPanel.add(progressBar);

        // ── CENTER: List + Table side by side ───────────────────────────
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));

        // List with selection handling
        String[] items = {
                "Mercury", "Venus", "Earth", "Mars", "Jupiter",
                "Saturn", "Uranus", "Neptune", "Pluto (dwarf)"
        };

        JList<String> planetList = new JList<>(items);
        planetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        planetList.setSelectedIndex(2); // Earth
        planetList.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel selectionLabel = new JLabel("Selected: Earth", SwingConstants.CENTER);
        selectionLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        // List event handling
        planetList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = planetList.getSelectedValue();
                if (selected != null) {
                    selectionLabel.setText("Selected: " + selected);
                }
            }
        });

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Planets (JList)"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(planetList), BorderLayout.CENTER);
        listPanel.add(selectionLabel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Name", "Type", "Distance (AU)"};
        Object[][] data = {
                {1, "Mercury", "Terrestrial", 0.39},
                {2, "Venus",   "Terrestrial", 0.72},
                {3, "Earth",   "Terrestrial", 1.00},
                {4, "Mars",    "Terrestrial", 1.52},
                {5, "Jupiter", "Gas Giant",   5.20},
                {6, "Saturn",  "Gas Giant",   9.58},
                {7, "Uranus",  "Ice Giant",  19.18},
                {8, "Neptune", "Ice Giant",  30.07}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(24);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JLabel("Solar System Table"), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        centerPanel.add(listPanel);
        centerPanel.add(tablePanel);

        // Put everything together
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private JTree createSampleTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Solar System");

        DefaultMutableTreeNode terrestrial = new DefaultMutableTreeNode("Terrestrial planets");
        terrestrial.add(new DefaultMutableTreeNode("Mercury"));
        terrestrial.add(new DefaultMutableTreeNode("Venus"));
        terrestrial.add(new DefaultMutableTreeNode("Earth"));
        terrestrial.add(new DefaultMutableTreeNode("Mars"));

        DefaultMutableTreeNode gasGiants = new DefaultMutableTreeNode("Gas Giants");
        gasGiants.add(new DefaultMutableTreeNode("Jupiter"));
        gasGiants.add(new DefaultMutableTreeNode("Saturn"));

        DefaultMutableTreeNode iceGiants = new DefaultMutableTreeNode("Ice Giants");
        iceGiants.add(new DefaultMutableTreeNode("Uranus"));
        iceGiants.add(new DefaultMutableTreeNode("Neptune"));

        root.add(terrestrial);
        root.add(gasGiants);
        root.add(iceGiants);

        JTree tree = new JTree(root);
        tree.setShowsRootHandles(true);
        tree.setRootVisible(true);
        tree.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        return tree;
    }

    public static void main(String[] args) {
        // Better look & feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // or try:   new com.formdev.flatlaf.FlatLightLaf()
        } catch (Exception e) {
            System.err.println("Failed to set look and feel");
        }

        SwingUtilities.invokeLater(() -> {
            new ComplexSwingDemo().setVisible(true);
        });
    }
}