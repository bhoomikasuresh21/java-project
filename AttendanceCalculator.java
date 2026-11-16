import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendanceCalculator extends JFrame implements ActionListener {

    // Input fields
    private final JTextField totalField;
    private final JTextField absentField;

    // Buttons
    private final JButton calculateButton;
    private final JButton clearButton;

    // Result labels
    private final JLabel attendedValue;
    private final JLabel percentageValue;
    private final JLabel leaveValue;
    private final JLabel messageLabel;

    public AttendanceCalculator() {
        setTitle("Student Attendance Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(520, 520));
        setLocationRelativeTo(null);

        // Main container
        JPanel root = new JPanel(new BorderLayout(16, 16));
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        root.setBackground(new Color(244, 247, 251));
        setContentPane(root);

        // Header
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel title = new JLabel("Student Attendance Calculator");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(30, 41, 59));

        JLabel subtitle = new JLabel("Calculate your attendance and remaining leave allowance");
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(new Color(71, 85, 105));
        subtitle.setBorder(new EmptyBorder(4, 0, 0, 0));

        header.add(title);
        header.add(subtitle);
        root.add(header, BorderLayout.NORTH);

        // Center content (form + results)
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        // Input card
        JPanel formCard = createCardPanel();
        formCard.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8, 8, 8, 8);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JLabel totalLabel = createFieldLabel("Total Classes");
        JLabel absentLabel = createFieldLabel("Absent Classes");

        totalField = createTextField();
        absentField = createTextField();

        gc.gridx = 0; gc.gridy = 0; gc.weightx = 0;
        formCard.add(totalLabel, gc);
        gc.gridx = 1; gc.gridy = 0; gc.weightx = 1.0;
        formCard.add(totalField, gc);

        gc.gridx = 0; gc.gridy = 1; gc.weightx = 0;
        formCard.add(absentLabel, gc);
        gc.gridx = 1; gc.gridy = 1; gc.weightx = 1.0;
        formCard.add(absentField, gc);

        // Buttons row
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonRow.setOpaque(false);

        calculateButton = createPrimaryButton("Calculate");
        clearButton = createSecondaryButton("Clear");

        calculateButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonRow.add(calculateButton);
        buttonRow.add(clearButton);

        gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 2; gc.weightx = 1.0;
        gc.insets = new Insets(14, 8, 4, 8);
        formCard.add(buttonRow, gc);

        center.add(formCard);
        center.add(Box.createVerticalStrut(16));

        // Results card
        JPanel resultsCard = createCardPanel();
        resultsCard.setLayout(new GridBagLayout());
        GridBagConstraints rg = new GridBagConstraints();
        rg.insets = new Insets(8, 8, 8, 8);
        rg.fill = GridBagConstraints.HORIZONTAL;

        attendedValue = createValueLabel("Attended Classes: -");
        percentageValue = createValueLabel("Attendance Percentage: -");
        leaveValue = createValueLabel("Additional Leaves Allowed: -");

        // Emphasize percentage
        percentageValue.setFont(new Font("SansSerif", Font.BOLD, 18));
        percentageValue.setForeground(new Color(79, 70, 229));

        rg.gridx = 0; rg.gridy = 0; rg.weightx = 1.0;
        resultsCard.add(attendedValue, rg);
        rg.gridy = 1;
        resultsCard.add(percentageValue, rg);
        rg.gridy = 2;
        resultsCard.add(leaveValue, rg);

        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        messageLabel.setBorder(new EmptyBorder(6, 4, 0, 4));
        rg.gridy = 3;
        rg.insets = new Insets(10, 8, 4, 8);
        resultsCard.add(messageLabel, rg);

        center.add(resultsCard);
        root.add(center, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                new EmptyBorder(14, 14, 14, 14)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height + 400));
        return panel;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(51, 65, 85));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setMargin(new Insets(8, 10, 8, 10));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
                new EmptyBorder(6, 8, 6, 8)
        ));
        return field;
    }

    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        label.setForeground(new Color(30, 41, 59));
        return label;
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBackground(new Color(79, 70, 229));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(79, 70, 229), 1, true),
                new EmptyBorder(8, 16, 8, 16)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBackground(new Color(241, 245, 249));
        button.setForeground(new Color(30, 41, 59));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
                new EmptyBorder(8, 16, 8, 16)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                int total = Integer.parseInt(totalField.getText().trim());
                int absent = Integer.parseInt(absentField.getText().trim());

                // Input validation (same behavior)
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
                int additionalLeaves = (int) Math.floor(attended / 0.75) - total;
                if (additionalLeaves < 0) {
                    additionalLeaves = 0;
                }

                // Display results
                attendedValue.setText("Attended Classes: " + attended);
                percentageValue.setText(String.format("Attendance Percentage: %.2f%%", percentage));
                leaveValue.setText("Additional Leaves Allowed: " + additionalLeaves);

                // Attendance message (same thresholds/messages)
                if (percentage < 75) {
                    messageLabel.setText("Warning: Attendance is below 75%!");
                    messageLabel.setForeground(new Color(220, 38, 38));
                } else {
                    messageLabel.setText("Good! You meet the 75% requirement.");
                    messageLabel.setForeground(new Color(21, 128, 61));
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
            attendedValue.setText("Attended Classes: -");
            percentageValue.setText("Attendance Percentage: -");
            leaveValue.setText("Additional Leaves Allowed: -");
            messageLabel.setText(" ");
            messageLabel.setForeground(new Color(30, 41, 59));
            totalField.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AttendanceCalculator::new);
    }
}
