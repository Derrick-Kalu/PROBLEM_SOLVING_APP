
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

public class ManageSettingsFrame extends JFrame {
    private JTextField setting1Field;
    private JTextField setting2Field;
    private JButton updateButton;
    private JButton saveButton;
    private JButton loadButton;
    private Properties settings;

    public ManageSettingsFrame() {
        setTitle("Manage Settings");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10)); // GridLayout with gaps

        JLabel setting1Label = new JLabel("Setting 1:");
        JLabel setting2Label = new JLabel("Setting 2:");

        setting1Field = new JTextField();
        setting2Field = new JTextField();

        updateButton = new JButton("Update");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        add(setting1Label);
        add(setting1Field);
        add(setting2Label);
        add(setting2Field);
        add(updateButton);
        add(saveButton);
        add(loadButton);

        settings = new Properties();

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSettings();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettingsToFile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSettingsFromFile();
            }
        });

        loadSettingsFromFile();
    }

    private void updateSettings() {
        settings.setProperty("setting1", setting1Field.getText());
        settings.setProperty("setting2", setting2Field.getText());
        JOptionPane.showMessageDialog(this, "Settings updated successfully.");
    }

    private void saveSettingsToFile() {
        try (FileOutputStream fos = new FileOutputStream("settings.properties")) {
            settings.store(fos, null);
            JOptionPane.showMessageDialog(this, "Settings saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSettingsFromFile() {
        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            settings.load(fis);
            setting1Field.setText(settings.getProperty("setting1", ""));
            setting2Field.setText(settings.getProperty("setting2", ""));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading settings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageSettingsFrame().setVisible(true);
            }
        });
    }
}
