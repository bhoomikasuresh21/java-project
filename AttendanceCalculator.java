import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AttendanceCalculator extends JFrame implements ActionListener {

    JLabel title, totalLabel, absentLabel;
    JLabel attendedResult, percentageResult, leaveResult, message;

    JTextField totalField, absentField;

    JButton calculateButton, clearButton;

    public AttendanceCalculator() {

        // Window settings
        setTitle("Student Attendance Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title
        title = new JLabel("Student Attendance Calculator");
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setBounds(65, 25, 380, 35);
        add(title);

        // Total classes
        totalLabel = new JLabel("Total Classes:");
        totalLabel.setBounds(60, 100, 150, 30);
        add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(220, 100, 180, 30);
        add(totalField);

        // Absent classes
        absentLabel = new JLabel("Absent Classes:");
        absentLabel.setBounds(60, 150, 150, 30);
        add(absentLabel);

        absentField = new JTextField();
        absentField.setBounds(220, 150, 180, 30);
        add(absentField);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(100, 210, 120, 35);
        calculateButton.addActionListener(this);
        add(calculateButton);

        // Clear button
        clearButton = new JButton("Clear");
        clearButton.setBounds(250, 210, 120, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Result labels
        attendedResult = new JLabel("Attended Classes: ");
        attendedResult.setBounds(60, 280, 350, 30);
        add(attendedResult);

        percentageResult = new JLabel("Attendance Percentage: ");
        percentageResult.setBounds(60, 320, 350, 30);
        add(percentageResult);

        leaveResult = new JLabel("Additional Leaves Allowed: ");
        leaveResult.setBounds(60, 360, 350, 30);
        add(leaveResult);

        message = new JLabel("");
        message.setBounds(60, 410, 380, 30);
        add(message);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateButton) {

            try {

                int total = Integer.parseInt(totalField.getText());
                int absent = Integer.parseInt(absentField.getText());

                // Input validation
                if (total <= 0 || absent < 0 || absent > total) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter valid class numbers.");
                    return;
                }

                // Calculate attended classes
                int attended = total - absent;

                // Calculate attendance percentage
                double percentage = (attended * 100.0) / total;

                // Calculate additional leaves at 75%
                int additionalLeaves =
                        (int) Math.floor(attended / 0.75) - total;

                if (additionalLeaves < 0) {
                    additionalLeaves = 0;
                }

                // Display results
                attendedResult.setText(
                        "Attended Classes: " + attended);

                percentageResult.setText(
                        String.format("Attendance Percentage: %.2f%%",
                                percentage));

                leaveResult.setText(
                        "Additional Leaves Allowed: " + additionalLeaves);

                // Attendance message
                if (percentage < 75) {
                    message.setText("Warning: Attendance is below 75%!");
                    message.setForeground(Color.RED);
                } else {
                    message.setText("Good! You meet the 75% requirement.");
                    message.setForeground(new Color(0, 128, 0));
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Please enter numbers only.");

            }

        }

        // Clear button functionality
        if (e.getSource() == clearButton) {

            totalField.setText("");
            absentField.setText("");

            attendedResult.setText("Attended Classes: ");
            percentageResult.setText("Attendance Percentage: ");
            leaveResult.setText("Additional Leaves Allowed: ");
            message.setText("");
        }
    }

    public static void main(String[] args) {

        new AttendanceCalculator();

    }
}
