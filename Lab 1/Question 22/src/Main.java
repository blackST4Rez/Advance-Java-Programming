import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("Swing Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Slider
        JSlider slider = new JSlider(0, 100, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(25);

        JLabel label = new JLabel("Value: 50", SwingConstants.CENTER);
        slider.addChangeListener(e -> label.setText("Value: " + slider.getValue()));

        // List
        String[] items = {"Mercury", "Venus", "Earth", "Mars", "Jupiter"};
        JList<String> list = new JList<>(items);
        list.setSelectedIndex(2);

        // Table
        String[] columns = {"Planet", "Type"};
        Object[][] data = {
                {"Earth", "Terrestrial"},
                {"Mars", "Terrestrial"},
                {"Jupiter", "Gas Giant"}
        };
        JTable table = new JTable(data, columns);

        // Layout
        JPanel top = new JPanel(new BorderLayout());
        top.add(slider, BorderLayout.CENTER);
        top.add(label, BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(list), new JScrollPane(table));
        split.setDividerLocation(200);

        panel.add(top, BorderLayout.NORTH);
        panel.add(split, BorderLayout.CENTER);
        add(panel);
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}