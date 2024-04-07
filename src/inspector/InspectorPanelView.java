package inspector;

import inspector.masterWorker.MasterWorkerView;
import inspector.road.RoadStatisticView;
import inspector.stepper.StepperView;
import inspector.timeStatistics.TimeStatisticsView;
import simulation.InspectorSimulation;
import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;


public class InspectorPanelView extends JPanel {
    private final StartStopView startStopView;
    private final StepperView stepperView;
    private final TimeStatisticsView timeStatisticsView;
    private final RoadStatisticView roadStatisticView;
    private final MasterWorkerView masterWorkerView;

    private final BorderLayout layoutManager;

    public InspectorPanelView() {
        this.startStopView = new StartStopView();
        this.stepperView = new StepperView();
        this.timeStatisticsView = new TimeStatisticsView();
        this.roadStatisticView = new RoadStatisticView();
        this.masterWorkerView = new MasterWorkerView();

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

        final JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightPanel.setOpaque(false);
        rightPanel.add(this.masterWorkerView);
        this.add(rightPanel, BorderLayout.EAST);

        final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        final JPanel westPanel = new JPanel(flowLayout);
        final int hgap = 15;
        westPanel.setOpaque(false);
        flowLayout.setHgap(hgap);
        westPanel.add(this.timeStatisticsView);
        westPanel.add(this.roadStatisticView);
        this.add(westPanel, BorderLayout.WEST);

        this.setOpaque(false);
    }

    public void setupSimulation(final InspectorSimulation simulation) {
        this.startStopView.setupSimulation(simulation, this.stepperView);
    }

    public void updateInspector(final InspectorSimulation simulation) {
        this.stepperView.updateStepper(simulation.stepper());
        this.timeStatisticsView.updateStatistics(simulation.timeStatistics());
        this.roadStatisticView.updateStatistics(simulation.roadStatistics());
    }

    public void endUpdateInspector(final InspectorSimulation simulation) {
        this.timeStatisticsView.endUpdateStatistics(simulation.timeStatistics());
    }
}
