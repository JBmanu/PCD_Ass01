package view.commands;

import view.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class StepperView extends JPanel {
    private static final String STEP = "Step:";
    private static final int COLUMNS = 5;
    private final JLabel titleLabel;
    private final JLabel errorLabel;
    private final JTextField textField;
    private final FlowLayout layoutManager;

    public StepperView() {
        this.titleLabel = new JLabel(STEP);
        this.errorLabel = new JLabel();
        this.textField = new JTextField(COLUMNS);
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);

        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.setLayout(this.layoutManager);
        this.textField.setText("10000");
        this.add(this.titleLabel);
        this.add(this.textField);
        this.add(this.errorLabel);
    }

    public int getTextField() {
        try {
            this.clearError();
            return Integer.parseInt(this.textField.getText());
        } catch (NumberFormatException e) {
            this.showError();
            return -1;
        }
    }

    public void showError() {
        this.errorLabel.setText("Put a number in the text field");
    }

    public void clearError() {
        this.errorLabel.setText("");
    }


}
