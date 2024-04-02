package view.commands;

import view.ViewUtils;

import javax.swing.*;
import java.awt.*;


public class CommandsPanelView extends JPanel {
    private final StepperView stepperView;
    private final StartStopView startStopView;
    private final FlowLayout layoutManager;

    public CommandsPanelView() {
        this.stepperView = new StepperView();
        this.startStopView = new StartStopView();

        this.layoutManager = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(this.layoutManager);
        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.add(this.startStopView);
        this.add(this.stepperView);
    }

    public void setStartButtonAction(final Runnable action) {
//        this.startButton.addActionListener(e -> action.run());
    }

    public void setStopButtonAction(final Runnable action) {
//        this.stopButton.addActionListener(e -> action.run());
    }
}
