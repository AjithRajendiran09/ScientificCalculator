import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame {
    private JTextField display;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttonPanel = createButtonPanel();
        buttonPanel.setLayout(new GridLayout(5, 5, 5, 5));

        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        String[] buttonLabels = {
                "7", "8", "9", "/", "sqrt",
                "4", "5", "6", "*", "%",
                "1", "2", "3", "-", "1/x",
                "0", "+/-", ".", "+", "=",
                "C", "CE", "Back"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
    
            if ("=".equals(buttonText)) {
                evaluateExpression();
            } else if ("C".equals(buttonText)) {
                clearDisplay();
            } else if ("CE".equals(buttonText)) {
                clearEntry();
            } else if ("Back".equals(buttonText)) {
                backspace();
            } else {
                appendToDisplay(buttonText);
            }
    
            // Update the display
            updateDisplay();
        }
    
        private void evaluateExpression() {
            String result = Calculator.evaluate(display.getText());
            display.setText(result);
        }
    
        private void clearDisplay() {
            display.setText("");
        }
    
        private void clearEntry() {
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
    
        private void backspace() {
            display.setText("");
        }
    
        private void appendToDisplay(String text) {
            display.setText(display.getText() + text);
        }
    
        private void updateDisplay() {
            // Update the display text based on the calculation
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScientificCalculator());
    }
}
