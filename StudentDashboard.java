
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class StudentDashboard extends JFrame {
    private JButton viewProfileButton;
    private JButton viewCoursesButton;
    private JButton trackAssignmentsButton;
    private JButton backButton;

    private LoginPage loginPage;

    public StudentDashboard(LoginPage loginPage) {
        this.loginPage = loginPage;

        setTitle("Student Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        viewProfileButton = new JButton("View Profile");
        viewCoursesButton = new JButton("View Courses");
        trackAssignmentsButton = new JButton("Track Assignments");
        backButton = new JButton("Back");

        add(viewProfileButton);
        add(viewCoursesButton);
        add(trackAssignmentsButton);
        add(backButton);

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentProfileFrame().setVisible(true);
            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourseViewFrame(loadCoursesFromFile()).setVisible(true);
            }
        });

        trackAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignmentTrackerFrame(loadAssignmentsFromFile()).setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage.showLoginPage();
                dispose();
            }
        });
    }

    private ArrayList<Assignment> loadAssignmentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("assignments.dat"))) {
            return (ArrayList<Assignment>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private ArrayList<Course> loadCoursesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("courses.dat"))) {
            return (ArrayList<Course>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}
