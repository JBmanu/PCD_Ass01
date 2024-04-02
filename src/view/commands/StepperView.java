package view.commands;

import view.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class StepperView extends JPanel {
    private final JLabel label;
    private final JTextField textField;
    private final FlowLayout layoutManager;

    public StepperView() {
        this.label = new JLabel("Step:");
        this.textField = new JTextField(5);
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);

        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.setLayout(this.layoutManager);
        this.add(this.label);
        this.add(this.textField);
    }

    public int getTextField() {
        return Integer.parseInt(this.textField.getText());
    }
}
