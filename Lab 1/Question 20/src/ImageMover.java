import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageMover extends JFrame {

    private static final int IMG_WIDTH = 32;
    private static final int IMG_HEIGHT = 32;

    private int x = 200;
    private int y = 200;
    private Image img;
    private JPanel panel;

    public ImageMover() {

        System.out.println("Looking for image: img.png");
        boolean imageLoaded = false;

        // 1. Current directory
        File currentDirFile = new File("img.png");
        if (currentDirFile.exists()) {
            img = new ImageIcon("img.png").getImage();
            imageLoaded = true;
        }

        // 2. Specific path (fallback)
        if (!imageLoaded) {
            String specificPath =
                    "F:/BSc.CSIT/7th Semester/Advance-Java-Programming/Lab 1/Question 20/img.png";
            File specificFile = new File(specificPath);
            if (specificFile.exists()) {
                img = new ImageIcon(specificPath).getImage();
                imageLoaded = true;
            }
        }

        // 3. Classpath
        if (!imageLoaded) {
            java.net.URL imageURL = getClass().getResource("/img.png");
            if (imageURL != null) {
                img = new ImageIcon(imageURL).getImage();
                imageLoaded = true;
            }
        }

        // File chooser if still not loaded
        if (!imageLoaded) {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                img = new ImageIcon(chooser.getSelectedFile().getAbsolutePath()).getImage();
                imageLoaded = true;
            }
        }

        // 🔹 SCALE IMAGE TO 32x32 🔹
        if (imageLoaded && img != null) {
            img = getScaledImage(img, IMG_WIDTH, IMG_HEIGHT);
            System.out.println("Image resized to 32x32");
        }

        setTitle("Image Mover - 32x32 Image");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Grid
                g.setColor(new Color(240, 240, 240));
                for (int i = 0; i < getWidth(); i += 20)
                    g.drawLine(i, 0, i, getHeight());
                for (int j = 0; j < getHeight(); j += 20)
                    g.drawLine(0, j, getWidth(), j);

                if (img != null) {
                    g.drawImage(img, x, y, this);

                    g.setColor(Color.BLUE);
                    g.drawRect(x, y, IMG_WIDTH, IMG_HEIGHT);

                    g.setColor(Color.RED);
                    g.drawString("Position: (" + x + ", " + y + ")", 10, 20);
                }
            }
        };

        panel.setBackground(Color.WHITE);
        panel.setFocusable(true);
        add(panel);

        // Keyboard controls
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int step = e.isShiftDown() ? 50 : 10;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> x -= step;
                    case KeyEvent.VK_RIGHT -> x += step;
                    case KeyEvent.VK_UP -> y -= step;
                    case KeyEvent.VK_DOWN -> y += step;
                    case KeyEvent.VK_R -> { x = 200; y = 200; }
                }

                int maxX = panel.getWidth() - IMG_WIDTH;
                int maxY = panel.getHeight() - IMG_HEIGHT;
                x = Math.max(0, Math.min(x, maxX));
                y = Math.max(0, Math.min(y, maxY));

                panel.repaint();
            }
        });

        // Mouse click move
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX() - IMG_WIDTH / 2;
                y = e.getY() - IMG_HEIGHT / 2;
                panel.repaint();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
        panel.requestFocusInWindow();
    }

    // 🔹 Image scaling helper
    private Image getScaledImage(Image src, int w, int h) {
        BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(src, 0, 0, w, h, null);
        g2.dispose();
        return resized;
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
        System.out.println();
        SwingUtilities.invokeLater(ImageMover::new);
    }
}
