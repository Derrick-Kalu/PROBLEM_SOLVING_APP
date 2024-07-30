import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class StudentProfileManagerFrame extends JFrame {
    private JTextField studentNameField;
    private JButton saveButton;

    public StudentProfileManagerFrame() {
        setTitle("Student Profile Manager");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        studentNameField = new JTextField(20);
        saveButton = new JButton("Save Profile");

        add(new JLabel("Student Name:"));
        add(studentNameField);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = studentNameField.getText();
                try {
                    saveProfile(studentName);
                    JOptionPane.showMessageDialog(null, "Profile Saved: " + studentName);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void saveProfile(String studentName) throws IOException {
        try (FileWriter writer = new FileWriter("student_profiles.txt", true)) {
            writer.write(studentName + "\n");
        }
    }
}
