
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AssignmentTrackerFrame extends JFrame {
    private JTextArea assignmentsTextArea;

    public AssignmentTrackerFrame(ArrayList<Assignment> assignments) {
        setTitle("Assignments");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        assignmentsTextArea = new JTextArea();
        assignmentsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(assignmentsTextArea);

        add(scrollPane, BorderLayout.CENTER);

        for (Assignment assignment : assignments) {
            assignmentsTextArea.append(assignment.toString() + "\n");
        }
    }
}
