import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ReportsManagerFrame extends JFrame {
    private JTextArea reportArea;
    private JButton viewReportButton;
    private JButton generateReportButton;
    private JButton exportReportButton;

    public ReportsManagerFrame() {
        setTitle("Reports Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text area to display reports
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane reportScrollPane = new JScrollPane(reportArea);
        add(reportScrollPane, BorderLayout.CENTER);

        // Buttons
        viewReportButton = new JButton("View Report");
        generateReportButton = new JButton("Generate Report");
        exportReportButton = new JButton("Export Report");

        // Customize button colors
        viewReportButton.setBackground(Color.GREEN);
        viewReportButton.setForeground(Color.WHITE);
        generateReportButton.setBackground(Color.BLUE);
        generateReportButton.setForeground(Color.WHITE);
        exportReportButton.setBackground(Color.ORANGE);
        exportReportButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewReportButton);
        buttonPanel.add(generateReportButton);
        buttonPanel.add(exportReportButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewReport();
            }
        });

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGenerateReport();
            }
        });

        exportReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExportReport();
            }
        });
    }

    private void handleViewReport() {
        // Example: View a hardcoded report (to be replaced with dynamic data)
        String report = "Academic Success Report\n\n";
        report += "Student: John Doe\n";
        report += "Courses Completed: 5\n";
        report += "Assignments Submitted: 12\n";
        report += "Average Grade: A\n";
        reportArea.setText(report);
    }

    private void handleGenerateReport() {
        // Example: Generate a report (this could be more complex)
        String report = "Generated Report\n\n";
        report += "Student: Jane Smith\n";
        report += "Courses Completed: 4\n";
        report += "Assignments Submitted: 10\n";
        report += "Average Grade: B+\n";
        reportArea.setText(report);
        JOptionPane.showMessageDialog(this, "Report generated successfully.");
    }

    private void handleExportReport() {
        String report = reportArea.getText();
        if (report.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No report to export.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Export the report to a file
        try (FileWriter writer = new FileWriter("report.txt")) {
            writer.write(report);
            JOptionPane.showMessageDialog(this, "Report exported successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReportsManagerFrame().setVisible(true);
            }
        });
    }
}
