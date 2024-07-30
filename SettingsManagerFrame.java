import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsManagerFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JButton updateEmailButton;
    private JButton changePasswordButton;
    private JComboBox<String> themeComboBox;

    public SettingsManagerFrame() {
        setTitle("Settings Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for settings fields and buttons
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        settingsPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        settingsPanel.add(emailField, gbc);

        updateEmailButton = new JButton("Update Email");
        gbc.gridx = 2;
        gbc.gridy = 0;
        settingsPanel.add(updateEmailButton, gbc);

        // Password fields
        JLabel currentPasswordLabel = new JLabel("Current Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        settingsPanel.add(currentPasswordLabel, gbc);

        currentPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        settingsPanel.add(currentPasswordField, gbc);

        JLabel newPasswordLabel = new JLabel("New Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        settingsPanel.add(newPasswordLabel, gbc);

        newPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        settingsPanel.add(newPasswordField, gbc);

        changePasswordButton = new JButton("Change Password");
        gbc.gridx = 2;
        gbc.gridy = 2;
        settingsPanel.add(changePasswordButton, gbc);

        // Theme selection
        JLabel themeLabel = new JLabel("Theme:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        settingsPanel.add(themeLabel, gbc);

        themeComboBox = new JComboBox<>(new String[]{"Light", "Dark"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        settingsPanel.add(themeComboBox, gbc);

        add(settingsPanel, BorderLayout.CENTER);

        // Add Action Listeners
        updateEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateEmail();
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChangePassword();
            }
        });

        themeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChangeTheme();
            }
        });
    }

    private void handleUpdateEmail() {
        String email = emailField.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Example: Update email logic (to be replaced with actual logic)
        JOptionPane.showMessageDialog(this, "Email updated successfully.");
    }

    private void handleChangePassword() {
        String currentPassword = new String(currentPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());

        if (currentPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Passwords cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Example: Change password logic (to be replaced with actual logic)
        JOptionPane.showMessageDialog(this, "Password changed successfully.");
    }

    private void handleChangeTheme() {
        String selectedTheme = (String) themeComboBox.getSelectedItem();

        // Example: Change theme logic (to be replaced with actual logic)
        if ("Dark".equals(selectedTheme)) {
            // Set dark theme
            JOptionPane.showMessageDialog(this, "Dark theme selected.");
        } else {
            // Set light theme
            JOptionPane.showMessageDialog(this, "Light theme selected.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SettingsManagerFrame().setVisible(true);
            }
        });
    }
}
