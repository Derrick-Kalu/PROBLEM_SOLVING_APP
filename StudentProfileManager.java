
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class StudentProfileManager extends JFrame {
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton loadButton;
    private JTextArea profileArea;

    private ArrayList<StudentProfile> studentProfiles;

    public StudentProfileManager() {
        setTitle("Manage Student Profiles");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // BorderLayout with gaps

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // GridLayout with gaps
        studentIdField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        loadButton = new JButton("Load");

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(studentIdField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(addButton);
        formPanel.add(updateButton);

        profileArea = new JTextArea();
        profileArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(profileArea);

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.WEST);
        add(loadButton, BorderLayout.EAST);

        studentProfiles = new ArrayList<>();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentProfile();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudentProfile();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudentProfile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudentProfilesFromFile();
            }
        });

        loadStudentProfilesFromFile();
    }

    private void addStudentProfile() {
        String id = studentIdField.getText();
        String name = nameField.getText();
        String email = emailField.getText();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StudentProfile profile = new StudentProfile(id, name, email);
        studentProfiles.add(profile);
        JOptionPane.showMessageDialog(this, "Student profile added.");
        displayStudentProfiles();
    }

    private void updateStudentProfile() {
        String id = studentIdField.getText();
        String name = nameField.getText();
        String email = emailField.getText();

        for (StudentProfile profile : studentProfiles) {
            if (profile.getId().equals(id)) {
                profile.setName(name);
                profile.setEmail(email);
                JOptionPane.showMessageDialog(this, "Student profile updated.");
                displayStudentProfiles();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Student profile not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void deleteStudentProfile() {
        String id = studentIdField.getText();

        studentProfiles.removeIf(profile -> profile.getId().equals(id));
        JOptionPane.showMessageDialog(this, "Student profile deleted.");
        displayStudentProfiles();
    }

    private void loadStudentProfilesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student_profiles.dat"))) {
            studentProfiles = (ArrayList<StudentProfile>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            studentProfiles = new ArrayList<>();
        }
        displayStudentProfiles();
    }

    private void displayStudentProfiles() {
        StringBuilder sb = new StringBuilder();
        for (StudentProfile profile : studentProfiles) {
            sb.append("ID: ").append(profile.getId()).append("\n");
            sb.append("Name: ").append(profile.getName()).append("\n");
            sb.append("Email: ").append(profile.getEmail()).append("\n");
            sb.append("----------------------------\n");
        }
        profileArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentProfileManager().setVisible(true);
            }
        });
    }
}
