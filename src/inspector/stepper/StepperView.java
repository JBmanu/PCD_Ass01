package inspector.stepper;

import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class StepperView extends JPanel {
    private static final String STEP = "Step:";
    private static final int COLUMNS = 5;
    private final JLabel titleSetStepLabel;
    private final JTextField stepTextField;

    private final JLabel currentStepLabel;
    private final JLabel errorLabel;
    private final FlowLayout layoutManager;

    public StepperView() {
        this.titleSetStepLabel = new JLabel(STEP);
        this.currentStepLabel = new JLabel();
        this.errorLabel = new JLabel();
        this.stepTextField = new JTextField(COLUMNS);
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);

        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.setLayout(this.layoutManager);
        this.stepTextField.setText("100");
        this.currentStepLabel.setText("Current Step: 0");
        this.add(this.titleSetStepLabel);
        this.add(this.stepTextField);
        this.add(this.currentStepLabel);
        this.add(this.errorLabel);
    }

    public int getStep() {
        try {
            this.clearError();
            return Integer.parseInt(this.stepTextField.getText());
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

    public void updateStepper(final Stepper stepper) {
        final int currentStep = stepper.currentStep() + 1;
        this.currentStepLabel.setText("Current Step: " + currentStep);
    }
}
