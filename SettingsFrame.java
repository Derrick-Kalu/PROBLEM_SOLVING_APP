import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    public SettingsFrame() {
        setTitle("System Settings");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea settingsArea = new JTextArea();
        settingsArea.setText("Settings content here...");
        add(new JScrollPane(settingsArea), BorderLayout.CENTER);
    }
}
