
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private JButton manageAssignmentsButton;
    private JButton manageCoursesButton;
    private JButton manageStudentsButton;
    private JButton manageSettingsButton;
    private JButton viewReportsButton;
    private JButton logoutButton;
    private JButton backButton;

    private LoginPage loginPage;

    public AdminDashboard(LoginPage loginPage) {
        this.loginPage = loginPage;

        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 10, 10)); // GridLayout with gaps

        manageAssignmentsButton = new JButton("Manage Assignments");
        manageCoursesButton = new JButton("Manage Courses");
        manageStudentsButton = new JButton("Manage Students");
        manageSettingsButton = new JButton("Manage Settings");
        viewReportsButton = new JButton("View Reports");
        logoutButton = new JButton("Logout");
        backButton = new JButton("Back");

        add(manageAssignmentsButton);
        add(manageCoursesButton);
        add(manageStudentsButton);
        add(manageSettingsButton);
        add(viewReportsButton);
        add(logoutButton);
        add(backButton);

        manageAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignmentManagerFrame().setVisible(true);
            }
        });

        manageCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourseManagerFrame().setVisible(true);
            }
        });

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentProfileManager().setVisible(true);
            }
        });

        manageSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageSettingsFrame().setVisible(true);
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportsFrame().setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage.showLoginPage();
                dispose();
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
}
