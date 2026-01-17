import java.awt.*;
import java.awt.event.*;

public class EventHandlingDemo extends Frame
        implements WindowListener, ItemListener, FocusListener, MouseListener {

    Checkbox cb;
    TextField tf;
    Label lbl;

    public EventHandlingDemo() {
        setLayout(new FlowLayout());

        cb = new Checkbox("Accept");
        tf = new TextField(15);
        lbl = new Label("Perform an action");

        add(cb);
        add(tf);
        add(lbl);

        cb.addItemListener(this);
        tf.addFocusListener(this);
        addMouseListener(this);
        addWindowListener(this);

        setTitle("Event Handling Demo");
        setSize(300, 200);
        setVisible(true);
    }

    // Item Event
    public void itemStateChanged(ItemEvent e) {
        lbl.setText("Checkbox changed");
    }

    // Focus Events
    public void focusGained(FocusEvent e) {
        lbl.setText("TextField focused");
    }

    public void focusLost(FocusEvent e) {
        lbl.setText("TextField lost focus");
    }

    // Mouse Events
    public void mouseClicked(MouseEvent e) {
        lbl.setText("Mouse clicked");
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    // Window Events
    public void windowClosing(WindowEvent e) {
        dispose();
    }

    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

    public static void main(String[] args) {
        new EventHandlingDemo();
    }
}
