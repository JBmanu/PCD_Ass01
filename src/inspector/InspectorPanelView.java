package inspector;

import inspector.road.RoadStatisticView;
import inspector.stepper.StepperView;
import inspector.timeStatistics.TimeStatisticsView;
import simulation.CommandsSimulation;
import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;


public class InspectorPanelView extends JPanel {
    private final StartStopView startStopView;
    private final StepperView stepperView;
    private final TimeStatisticsView timeStatisticsView;
    private final RoadStatisticView roadStatisticView;
    private final BorderLayout layoutManager;

    public InspectorPanelView() {
        this.startStopView = new StartStopView();
        this.stepperView = new StepperView();
        this.timeStatisticsView = new TimeStatisticsView();
        this.roadStatisticView = new RoadStatisticView();

        this.layoutManager = new BorderLayout();
        this.setLayout(this.layoutManager);
        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.setupGraphics();
    }

    private void setupGraphics() {
        final JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        centerPanel.add(this.startStopView);
        centerPanel.add(this.stepperView);
        this.add(centerPanel, BorderLayout.NORTH);

        final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        final JPanel westPanel = new JPanel(flowLayout);
        final int hgap = 15;
        westPanel.setOpaque(false);
        flowLayout.setHgap(hgap);
        westPanel.add(this.timeStatisticsView);
        westPanel.add(this.roadStatisticView);
        this.add(westPanel, BorderLayout.WEST);
    }

    public void setupSimulation(final CommandsSimulation simulation) {
        this.startStopView.setupSimulation(simulation, this.stepperView);
    }

    public void updateInspector(final CommandsSimulation simulation) {
        SwingUtilities.invokeLater(() -> {
            this.stepperView.updateStepper(simulation.stepper());
            this.timeStatisticsView.updateStatistics(simulation.timeStatistics());
            this.roadStatisticView.updateStatistics(simulation.roadStatistics());
        });
    }

    public void endUpdateInspector(final CommandsSimulation simulation) {
        SwingUtilities.invokeLater(() -> {
            this.timeStatisticsView.endUpdateStatistics(simulation.timeStatistics());
        });
    }
}
