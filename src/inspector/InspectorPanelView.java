package inspector;

import inspector.road.RoadStatisticView;
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
    private final RoadStatisticView roadStatisticView;
    private final FlowLayout layoutManager;

    public InspectorPanelView() {
        this.startStopView = new StartStopView();
        this.stepperView = new StepperView();
        this.timeStatisticsView = new TimeStatisticsView();
        this.roadStatisticView = new RoadStatisticView();

        this.layoutManager = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(this.layoutManager);
        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.add(this.startStopView);
        this.add(this.stepperView);
        this.add(this.timeStatisticsView);
        this.add(this.roadStatisticView);
    }

    public void setupSimulation(final AbstractSimulation simulation) {
        this.startStopView.setupSimulation(simulation, this.stepperView);
    }

    public void updateInspector(final AbstractSimulation simulation) {
        SwingUtilities.invokeLater(() -> {
            this.stepperView.updateStepper(simulation.stepper());
            this.timeStatisticsView.updateStatistics(simulation.timeStatistics());
            this.roadStatisticView.updateStatistics(simulation.roadStatistics());
        });
    }

    public void endUpdateInspector(final AbstractSimulation simulation) {
        SwingUtilities.invokeLater(() -> {
            this.timeStatisticsView.endUpdateStatistics(simulation.timeStatistics());
        });
    }
}
