
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class CourseManagerFrame extends JFrame {
    private JTextField courseNameField;
    private JTextArea coursesTextArea;
    private JButton addCourseButton;
    private JButton updateCourseButton;
    private JButton deleteCourseButton;
    private JButton saveCoursesButton;
    private JButton loadCoursesButton;

    private ArrayList<Course> courses;
    private int selectedIndex = -1;

    public CourseManagerFrame() {
        setTitle("Course Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        courses = loadCoursesFromFile();

        courseNameField = new JTextField(20);
        coursesTextArea = new JTextArea();
        coursesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(coursesTextArea);

        addCourseButton = new JButton("Add Course");
        updateCourseButton = new JButton("Update Course");
        deleteCourseButton = new JButton("Delete Course");
        saveCoursesButton = new JButton("Save Courses");
        loadCoursesButton = new JButton("Load Courses");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Course Name:"));
        inputPanel.add(courseNameField);
        inputPanel.add(addCourseButton);
        inputPanel.add(updateCourseButton);
        inputPanel.add(deleteCourseButton);
        inputPanel.add(saveCoursesButton);
        inputPanel.add(loadCoursesButton);
        add(inputPanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();
                if (!courseName.isEmpty()) {
                    Course newCourse = new Course(courseName);
                    courses.add(newCourse);
                    coursesTextArea.append(newCourse.toString() + "\n");
                    clearInputFields();
                } else {
                    JOptionPane.showMessageDialog(CourseManagerFrame.this, "Please enter a course name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    String courseName = courseNameField.getText();
                    if (!courseName.isEmpty()) {
                        Course updatedCourse = new Course(courseName);
                        courses.set(selectedIndex, updatedCourse);
                        updateCoursesTextArea();
                        clearInputFields();
                        selectedIndex = -1;
                    } else {
                        JOptionPane.showMessageDialog(CourseManagerFrame.this, "Please enter a course name.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(CourseManagerFrame.this, "No course selected for update.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    courses.remove(selectedIndex);
                    updateCoursesTextArea();
                    clearInputFields();
                    selectedIndex = -1;
                } else {
                    JOptionPane.showMessageDialog(CourseManagerFrame.this, "No course selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCoursesToFile();
            }
        });

        loadCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courses = loadCoursesFromFile();
                updateCoursesTextArea();
            }
        });

        coursesTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = coursesTextArea.viewToModel(evt.getPoint());
                selectedIndex = getLineNumber(index);
                if (selectedIndex >= 0 && selectedIndex < courses.size()) {
                    Course selectedCourse = courses.get(selectedIndex);
                    courseNameField.setText(selectedCourse.getName());
                }
            }
        });

        updateCoursesTextArea();
    }

    private void updateCoursesTextArea() {
        coursesTextArea.setText("");
        for (Course course : courses) {
            coursesTextArea.append(course.toString() + "\n");
        }
    }

    private ArrayList<Course> loadCoursesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("courses.dat"))) {
            return (ArrayList<Course>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveCoursesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("courses.dat"))) {
            oos.writeObject(courses);
            JOptionPane.showMessageDialog(this, "Courses saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving courses: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        courseNameField.setText("");
    }

    private int getLineNumber(int index) {
        int lineNumber = (index < 0) ? -1 : 0;
        try {
            int offset = 0;
            while (offset <= index) {
                offset = coursesTextArea.getLineEndOffset(lineNumber++);
            }
        } catch (Exception e) {
            return -1;
        }
        return lineNumber - 2;
    }
}
