import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentProfileFrame extends JFrame {
    private JTextArea profileTextArea;
    private JButton saveButton;

    public StudentProfileFrame() {
        setTitle("Student Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        profileTextArea = new JTextArea(10, 30);
        saveButton = new JButton("Save Profile");

        JScrollPane scrollPane = new JScrollPane(profileTextArea);
        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        loadProfile();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveProfile(profileTextArea.getText());
                    JOptionPane.showMessageDialog(null, "Profile Saved");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadProfile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_profile.txt"))) {
            String line;
            StringBuilder profile = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                profile.append(line).append("\n");
            }
            profileTextArea.setText(profile.toString());
        } catch (IOException e) {
            profileTextArea.setText("Error loading profile.");
        }
    }

    private void saveProfile(String profileText) throws IOException {
        try (FileWriter writer = new FileWriter("student_profile.txt")) {
            writer.write(profileText);
        }
    }
}
