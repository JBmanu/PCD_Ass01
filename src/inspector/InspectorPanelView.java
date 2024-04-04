package inspector;

import inspector.stepper.Stepper;
import inspector.stepper.StepperView;
import inspector.timeStatistics.TimeStatistics;
import inspector.timeStatistics.TimeStatisticsView;
import simulation.AbstractSimulation;
import inspector.startStop.StartStopView;
import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;


public class InspectorPanelView extends JPanel {
    private final StartStopView startStopView;
    private final StepperView stepperView;
    private final TimeStatisticsView timeStatisticsView;
    private final FlowLayout layoutManager;

    public InspectorPanelView() {
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
        SwingUtilities.invokeLater(() -> {
            this.stepperView.updateStepper(stepper);
            this.timeStatisticsView.updateStatistics(timeStatistics);
        });
    }

    public void lastUpdateCommands(final TimeStatistics timeStatistics) {
        SwingUtilities.invokeLater(() -> {
            this.timeStatisticsView.lastUpdateStatistics(timeStatistics);
        });
    }
}
