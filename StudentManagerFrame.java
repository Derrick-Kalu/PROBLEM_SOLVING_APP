import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagerFrame extends JFrame {
    private JTextField studentNameField;
    private JTextField studentIDField;
    private JTextArea studentDetailsArea;
    private JButton addStudentButton;
    private JButton removeStudentButton;
    private JButton updateStudentButton;
    private JButton loadStudentsButton;

    private DefaultListModel<String> studentListModel;
    private JList<String> studentList;

    public StudentManagerFrame() {
        setTitle("Student Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for input fields and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Input fields
        JLabel nameLabel = new JLabel("Student Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(nameLabel, gbc);

        studentNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(studentNameField, gbc);

        JLabel idLabel = new JLabel("Student ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(idLabel, gbc);

        studentIDField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(studentIDField, gbc);

        JLabel detailsLabel = new JLabel("Student Details:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        inputPanel.add(detailsLabel, gbc);

        studentDetailsArea = new JTextArea(5, 20);
        studentDetailsArea.setLineWrap(true);
        studentDetailsArea.setWrapStyleWord(true);
        JScrollPane detailsScrollPane = new JScrollPane(studentDetailsArea);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        inputPanel.add(detailsScrollPane, gbc);

        // Buttons
        addStudentButton = new JButton("Add Student");
        removeStudentButton = new JButton("Remove Student");
        updateStudentButton = new JButton("Update Student");
        loadStudentsButton = new JButton("Load Students");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addStudentButton);
        buttonPanel.add(removeStudentButton);
        buttonPanel.add(updateStudentButton);
        buttonPanel.add(loadStudentsButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // List to display students
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        JScrollPane listScrollPane = new JScrollPane(studentList);
        add(listScrollPane, BorderLayout.WEST);

        // Add Action Listeners
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddStudent();
            }
        });

        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemoveStudent();
            }
        });

        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateStudent();
            }
        });

        loadStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoadStudents();
            }
        });
    }

    private void handleAddStudent() {
        String name = studentNameField.getText();
        String id = studentIDField.getText();
        String details = studentDetailsArea.getText();

        if (name.isEmpty() || id.isEmpty() || details.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name, ID, and details cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String student = "ID: " + id + ", Name: " + name + ", Details: " + details;
        studentListModel.addElement(student);
        studentNameField.setText("");
        studentIDField.setText("");
        studentDetailsArea.setText("");
        JOptionPane.showMessageDialog(this, "Student added successfully.");
    }

    private void handleRemoveStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            studentListModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, "Student removed successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "No student selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdateStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            String name = studentNameField.getText();
            String id = studentIDField.getText();
            String details = studentDetailsArea.getText();

            if (name.isEmpty() || id.isEmpty() || details.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name, ID, and details cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String updatedStudent = "ID: " + id + ", Name: " + name + ", Details: " + details;
            studentListModel.set(selectedIndex, updatedStudent);
            JOptionPane.showMessageDialog(this, "Student updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "No student selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLoadStudents() {
        // Example: Load students from a file or database (not implemented)
        JOptionPane.showMessageDialog(this, "Load students functionality not implemented.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagerFrame().setVisible(true);
            }
        });
    }
}
