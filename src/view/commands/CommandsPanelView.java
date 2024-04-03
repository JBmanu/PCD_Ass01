package view.commands;

import commands.Stepper;
import commands.TimeStatistics;
import simengineseq.AbstractSimulation;
import view.ViewUtils;

import javax.swing.*;
import java.awt.*;


public class CommandsPanelView extends JPanel {
    private final StartStopView startStopView;
    private final StepperView stepperView;
    private final TimeStatisticsView timeStatisticsView;
    private final FlowLayout layoutManager;

    public CommandsPanelView() {
        this.startStopView = new StartStopView();
        this.stepperView = new StepperView();
        this.timeStatisticsView = new TimeStatisticsView();

        this.layoutManager = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(this.layoutManager);
        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.add(this.startStopView);
        this.add(this.stepperView);
        this.add(this.timeStatisticsView);
    }

    public void setupSimulation(final AbstractSimulation simulation) {
        this.startStopView.setupSimulation(simulation, this.stepperView);
    }

    public void updateCommands(final Stepper stepper, final TimeStatistics timeStatistics) {
        SwingUtilities.invokeLater(() -> this.stepperView.updateStepper(stepper));
        SwingUtilities.invokeLater(() -> this.timeStatisticsView.updateStatistics(timeStatistics));
    }
}
