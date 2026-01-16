import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("JOptionPane Types Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);

        // Main panel with nice spacing
        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        // Title label
        JLabel title = new JLabel("Click each button to see different JOptionPane dialogs", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mainPanel.add(title);

        // Add buttons for each dialog type
        mainPanel.add(createButton("1. Information Message", this::showInfoDialog));
        mainPanel.add(createButton("2. Question Message", this::showQuestionDialog));
        mainPanel.add(createButton("3. Error Message", this::showErrorDialog));
        mainPanel.add(createButton("4. Warning Message", this::showWarningDialog));
        mainPanel.add(createButton("5. Confirmation Dialog", this::showConfirmDialog));
        mainPanel.add(createButton("6. Input Dialog", this::showInputDialog));
        mainPanel.add(createButton("7. Custom Options Dialog", this::showOptionDialog));

        add(mainPanel);
    }

    private JButton createButton(String text, Runnable action) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setPreferredSize(new Dimension(300, 45));
        btn.addActionListener(e -> action.run());
        return btn;
    }

    private void showInfoDialog() {
        JOptionPane.showMessageDialog(
                this,
                "Operation completed successfully!\nYour file has been saved.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showQuestionDialog() {
        JOptionPane.showMessageDialog(
                this,
                "Would you like to continue with the next step?",
                "Question",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    private void showErrorDialog() {
        JOptionPane.showMessageDialog(
                this,
                "Failed to connect to the server.\nPlease check your internet connection.",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void showWarningDialog() {
        JOptionPane.showMessageDialog(
                this,
                "The document has unsaved changes.\nSave before closing?",
                "Unsaved Changes",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void showConfirmDialog() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Do you really want to delete this file?\nThis action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        String message;
        switch (choice) {
            case JOptionPane.YES_OPTION:
                message = "File deleted!";
                break;
            case JOptionPane.NO_OPTION:
                message = "Deletion cancelled.";
                break;
            case JOptionPane.CANCEL_OPTION:
                message = "Action cancelled.";
                break;
            default:
                message = "Dialog closed.";
        }

        JOptionPane.showMessageDialog(this, message, "Result", JOptionPane.PLAIN_MESSAGE);
    }

    private void showInputDialog() {
        String name = (String) JOptionPane.showInputDialog(
                this,
                "Please enter your name:",
                "User Information",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,           // possible values (null = free text)
                "Your name here" // initial value
        );

        if (name != null && !name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hello, " + name + "! Nice to meet you!",
                    "Greeting",
                    JOptionPane.PLAIN_MESSAGE
            );
        } else if (name != null) {
            JOptionPane.showMessageDialog(this, "No name entered.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showOptionDialog() {
        String[] options = {"Save", "Don't Save", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "The document has been modified.\nDo you want to save changes?",
                "Save Changes?",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]  // default button
        );

        String result;
        switch (choice) {
            case 0: result = "Saving changes..."; break;
            case 1: result = "Changes discarded."; break;
            case 2: result = "Action cancelled."; break;
            default: result = "Dialog closed without selection.";
        }

        JOptionPane.showMessageDialog(this, result, "Action Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Failed to set system look and feel");
            }

            new Main().setVisible(true);
        });
    }
}