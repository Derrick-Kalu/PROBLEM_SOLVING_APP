
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton adminLoginButton;
    private JLabel statusLabel;

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10)); // GridLayout with gaps

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Student Login");
        adminLoginButton = new JButton("Admin Login");

        // Customize button colors
        loginButton.setBackground(Color.BLUE); // Set background color
        loginButton.setForeground(Color.WHITE); // Set text color
        adminLoginButton.setBackground(Color.RED); // Set background color
        adminLoginButton.setForeground(Color.WHITE); // Set text color

        statusLabel = new JLabel(" ");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(adminLoginButton);
        add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStudentLogin();
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAdminLogin();
            }
        });
    }

    private void handleStudentLogin() {
        String enteredUsername = usernameField.getText();
        String enteredPassword = new String(passwordField.getPassword());

        System.out.println("Student Username: " + enteredUsername);
        System.out.println("Student Password: " + enteredPassword);

        // Simple authentication logic for demo purposes
        if ("student".equals(enteredUsername) && "password".equals(enteredPassword)) {
            new StudentDashboard(this).setVisible(true);
            this.setVisible(false);
        } else {
            statusLabel.setText("Invalid student credentials.");
        }
    }

    private void handleAdminLogin() {
        String enteredUsername = usernameField.getText();
        String enteredPassword = new String(passwordField.getPassword());

        System.out.println("Admin Username: " + enteredUsername);
        System.out.println("Admin Password: " + enteredPassword);

        // Simple authentication logic for demo purposes
        if ("admin".equals(enteredUsername) && "password".equals(enteredPassword)) {
            new AdminDashboard(this).setVisible(true);
            this.setVisible(false);
        } else {
            statusLabel.setText("Invalid admin credentials.");
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

    public void showLoginPage() {
        this.setVisible(true);
    }
}
