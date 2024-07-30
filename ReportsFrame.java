import javax.swing.*;
import java.awt.*;

public class ReportsFrame extends JFrame {
    public ReportsFrame() {
        setTitle("System Reports");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea reportArea = new JTextArea();
        reportArea.setText("Reports content here...");
        add(new JScrollPane(reportArea), BorderLayout.CENTER);
    }
}
