import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("JOptionPane Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Click buttons to see different dialogs", SwingConstants.CENTER));
        panel.add(btn("1. Information", () -> msg("Success!", "Info", JOptionPane.INFORMATION_MESSAGE)));
        panel.add(btn("2. Error", () -> msg("Error occurred!", "Error", JOptionPane.ERROR_MESSAGE)));
        panel.add(btn("3. Warning", () -> msg("Warning!", "Warning", JOptionPane.WARNING_MESSAGE)));
        panel.add(btn("4. Question", () -> msg("Continue?", "Question", JOptionPane.QUESTION_MESSAGE)));
        panel.add(btn("5. Confirm", this::confirm));
        panel.add(btn("6. Input", this::input));
        panel.add(btn("7. Custom", this::custom));

        add(panel);
    }

    private JButton btn(String text, Runnable action) {
        JButton b = new JButton(text);
        b.addActionListener(e -> action.run());
        return b;
    }

    private void msg(String text, String title, int type) {
        JOptionPane.showMessageDialog(this, text, title, type);
    }

    private void confirm() {
        int choice = JOptionPane.showConfirmDialog(this, "Delete file?", "Confirm",
                JOptionPane.YES_NO_CANCEL_OPTION);
        String result = choice == 0 ? "Deleted" : choice == 1 ? "Cancelled" : "Closed";
        msg(result, "Result", JOptionPane.PLAIN_MESSAGE);
    }

    private void input() {
        String name = JOptionPane.showInputDialog(this, "Enter name:", "Input",
                JOptionPane.QUESTION_MESSAGE);
        if (name != null && !name.trim().isEmpty()) {
            msg("Hello, " + name + "!", "Greeting", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void custom() {
        String[] opts = {"Save", "Don't Save", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this, "Save changes?", "Save",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);
        String result = choice == 0 ? "Saved" : choice == 1 ? "Discarded" : "Cancelled";
        msg(result, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}