import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JDesktopPane desktop = new JDesktopPane();
    private int docCount = 0, imgCount = 0, calcCount = 0;

    public Main() {
        super("JInternalFrame Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        desktop.setBackground(new Color(240, 240, 245));

        // Menu
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File"), win = new JMenu("Window");

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);

        addMenuItem(win, "New Document", () -> addFrame("Document"));
        addMenuItem(win, "New Image Viewer", () -> addFrame("Image"));
        addMenuItem(win, "New Calculator", () -> addFrame("Calculator"));
        win.addSeparator();
        addMenuItem(win, "Tile Windows", this::tile);
        addMenuItem(win, "Cascade Windows", this::cascade);

        mb.add(file);
        mb.add(win);
        setJMenuBar(mb);

        // Toolbar
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        addButton(tb, "New Document", () -> addFrame("Document"));
        addButton(tb, "New Image", () -> addFrame("Image"));
        tb.addSeparator();
        addButton(tb, "Tile", this::tile);
        addButton(tb, "Cascade", this::cascade);

        add(tb, BorderLayout.NORTH);
        add(desktop, BorderLayout.CENTER);
    }

    private void addMenuItem(JMenu menu, String name, Runnable action) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(e -> action.run());
        menu.add(item);
    }

    private void addButton(JToolBar tb, String name, Runnable action) {
        JButton btn = new JButton(name);
        btn.addActionListener(e -> action.run());
        tb.add(btn);
    }

    private void addFrame(String type) {
        int num = type.equals("Document") ? ++docCount : type.equals("Image") ? ++imgCount : ++calcCount;
        JInternalFrame f = new JInternalFrame(type + " - " + num, true, true, true, true);
        f.setSize(420, 320);
        f.setLocation(30 + desktop.getAllFrames().length * 30, 30 + desktop.getAllFrames().length * 30);

        if (type.equals("Document")) {
            JTextArea ta = new JTextArea();
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            f.add(new JScrollPane(ta));
        } else if (type.equals("Image")) {
            JLabel lbl = new JLabel("Image Viewer", SwingConstants.CENTER);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 36));
            f.add(lbl);
        } else {
            f.add(createCalc());
        }

        desktop.add(f);
        f.setVisible(true);
        try { f.setSelected(true); } catch (Exception e) {}
    }

    private JPanel createCalc() {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField d = new JTextField("0");
        d.setHorizontalAlignment(SwingConstants.RIGHT);
        d.setFont(new Font("Segoe UI", Font.BOLD, 24));
        d.setEditable(false);
        p.add(d, BorderLayout.NORTH);

        JPanel bp = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] btns = {"7","8","9","/","4","5","6","*","1","2","3","-","C","0","=","+"};

        final double[] n1 = {0}, n2 = {0};
        final String[] op = {""};
        final boolean[] start = {true};

        for (String t : btns) {
            JButton b = new JButton(t);
            b.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            b.addActionListener(e -> {
                String c = e.getActionCommand();
                if (c.matches("[0-9]")) {
                    d.setText(start[0] ? c : d.getText() + c);
                    start[0] = false;
                } else if (c.equals("C")) {
                    d.setText("0");
                    n1[0] = n2[0] = 0;
                    op[0] = "";
                    start[0] = true;
                } else if (c.matches("[+\\-*/]")) {
                    n1[0] = Double.parseDouble(d.getText());
                    op[0] = c;
                    start[0] = true;
                } else if (c.equals("=")) {
                    n2[0] = Double.parseDouble(d.getText());
                    double r = op[0].equals("+") ? n1[0] + n2[0] : op[0].equals("-") ? n1[0] - n2[0] :
                            op[0].equals("*") ? n1[0] * n2[0] : n1[0] / n2[0];
                    d.setText(String.valueOf(r));
                    start[0] = true;
                }
            });
            bp.add(b);
        }

        p.add(bp, BorderLayout.CENTER);
        return p;
    }

    private void tile() {
        JInternalFrame[] fs = desktop.getAllFrames();
        if (fs.length == 0) return;
        int cols = (int) Math.ceil(Math.sqrt(fs.length));
        Dimension s = desktop.getSize();
        int w = s.width / cols, h = s.height / ((int) Math.ceil((double) fs.length / cols));
        for (int i = 0; i < fs.length; i++) fs[i].setBounds((i % cols) * w, (i / cols) * h, w, h);
    }

    private void cascade() {
        JInternalFrame[] fs = desktop.getAllFrames();
        for (int i = 0; i < fs.length; i++) fs[i].setBounds(i * 30, i * 30, 450, 350);
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}