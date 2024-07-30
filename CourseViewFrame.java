
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CourseViewFrame extends JFrame {
    private JTextArea coursesTextArea;

    public CourseViewFrame(ArrayList<Course> courses) {
        setTitle("Courses");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        coursesTextArea = new JTextArea();
        coursesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(coursesTextArea);

        add(scrollPane, BorderLayout.CENTER);

        for (Course course : courses) {
            coursesTextArea.append(course.toString() + "\n");
        }
    }
}
