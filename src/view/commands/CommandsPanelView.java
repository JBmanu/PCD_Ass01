package view.commands;

import commands.Stepper;
import simengineseq.AbstractSimulation;
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

    public void setupSimulation(final AbstractSimulation simulation) {
        this.startStopView.setupSimulation(simulation, this.stepperView);
    }

    public void updateStepper(final Stepper stepper) {
        SwingUtilities.invokeLater(() -> this.stepperView.updateStepper(stepper));
    }
}
