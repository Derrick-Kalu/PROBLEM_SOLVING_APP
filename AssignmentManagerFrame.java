
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AssignmentManagerFrame extends JFrame {
    private JTextField titleField;
    private JTextArea descriptionField;
    private JTextArea assignmentsTextArea;
    private JButton addAssignmentButton;
    private JButton updateAssignmentButton;
    private JButton deleteAssignmentButton;
    private JButton saveAssignmentsButton;
    private JButton loadAssignmentsButton;

    private ArrayList<Assignment> assignments;
    private int selectedIndex = -1;

    public AssignmentManagerFrame() {
        setTitle("Assignment Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        assignments = loadAssignmentsFromFile();

        titleField = new JTextField(20);
        descriptionField = new JTextArea(5, 20);
        assignmentsTextArea = new JTextArea();
        assignmentsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(assignmentsTextArea);

        addAssignmentButton = new JButton("Add Assignment");
        updateAssignmentButton = new JButton("Update Assignment");
        deleteAssignmentButton = new JButton("Delete Assignment");
        saveAssignmentsButton = new JButton("Save Assignments");
        loadAssignmentsButton = new JButton("Load Assignments");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(addAssignmentButton);
        inputPanel.add(updateAssignmentButton);
        inputPanel.add(deleteAssignmentButton);
        inputPanel.add(saveAssignmentsButton);
        inputPanel.add(loadAssignmentsButton);
        add(inputPanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        addAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                if (!title.isEmpty() && !description.isEmpty()) {
                    Assignment newAssignment = new Assignment(title, description);
                    assignments.add(newAssignment);
                    assignmentsTextArea.append(newAssignment.toString() + "\n");
                    clearInputFields();
                } else {
                    JOptionPane.showMessageDialog(AssignmentManagerFrame.this, "Please enter both title and description.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    String title = titleField.getText();
                    String description = descriptionField.getText();
                    if (!title.isEmpty() && !description.isEmpty()) {
                        Assignment updatedAssignment = new Assignment(title, description);
                        assignments.set(selectedIndex, updatedAssignment);
                        updateAssignmentsTextArea();
                        clearInputFields();
                        selectedIndex = -1;
                    } else {
                        JOptionPane.showMessageDialog(AssignmentManagerFrame.this, "Please enter both title and description.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AssignmentManagerFrame.this, "No assignment selected for update.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    assignments.remove(selectedIndex);
                    updateAssignmentsTextArea();
                    clearInputFields();
                    selectedIndex = -1;
                } else {
                    JOptionPane.showMessageDialog(AssignmentManagerFrame.this, "No assignment selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAssignmentsToFile();
            }
        });

        loadAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignments = loadAssignmentsFromFile();
                updateAssignmentsTextArea();
            }
        });

        assignmentsTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = assignmentsTextArea.viewToModel(evt.getPoint());
                selectedIndex = getLineNumber(index);
                if (selectedIndex >= 0 && selectedIndex < assignments.size()) {
                    Assignment selectedAssignment = assignments.get(selectedIndex);
                    titleField.setText(selectedAssignment.getTitle());
                    descriptionField.setText(selectedAssignment.getDescription());
                }
            }
        });

        updateAssignmentsTextArea();
    }

    private void updateAssignmentsTextArea() {
        assignmentsTextArea.setText("");
        for (Assignment assignment : assignments) {
            assignmentsTextArea.append(assignment.toString() + "\n");
        }
    }

    private ArrayList<Assignment> loadAssignmentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("assignments.dat"))) {
            return (ArrayList<Assignment>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveAssignmentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("assignments.dat"))) {
            oos.writeObject(assignments);
            JOptionPane.showMessageDialog(this, "Assignments saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving assignments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        titleField.setText("");
        descriptionField.setText("");
    }

    private int getLineNumber(int index) {
        int lineNumber = (index < 0) ? -1 : 0;
        try {
            int offset = 0;
            while (offset <= index) {
                offset = assignmentsTextArea.getLineEndOffset(lineNumber++);
            }
        } catch (Exception e) {
            return -1;
        }
        return lineNumber - 2;
    }
}
