package view;

import commands.Stepper;
import commands.TimeStatistics;
import simengineseq.AbstractAgent;
import simengineseq.AbstractEnvironment;
import simengineseq.AbstractSimulation;
import simengineseq.SimulationListener;
import simtrafficbase.RoadsEnv;
import view.commands.CommandsPanelView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoadSimView extends JFrame implements SimulationListener {
    private final CommandsPanelView commandsPanelView;
    private final RoadPanelView roadPanelView;
    private final BorderLayout layoutManager;
    private final JPanel glassPane;

    public RoadSimView() {
        super("RoadSim View");
        this.setSize(ViewUtils.GUI_WIDTH, ViewUtils.GUI_HEIGHT);
        this.commandsPanelView = new CommandsPanelView();
        this.roadPanelView = new RoadPanelView(ViewUtils.ROAD_WIDTH, ViewUtils.ROAD_HEIGHT);
        this.layoutManager = new BorderLayout();
        this.glassPane = new JPanel();

        this.setupGlassPane();
        this.setLayout(this.layoutManager);

        this.glassPane.add(this.commandsPanelView, BorderLayout.NORTH);
        this.add(BorderLayout.CENTER, this.roadPanelView);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }

    private void setupGlassPane() {
        this.setGlassPane(this.glassPane);
        this.glassPane.setLayout(new BorderLayout());
        this.glassPane.setOpaque(false);
        this.glassPane.setVisible(true);
    }

    public void setupCommandsSimulation(final AbstractSimulation simulation) {
        this.commandsPanelView.setupSimulation(simulation);
    }

    @Override
    public void notifyInit(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyStepDone(final int t, final List<AbstractAgent> agents,
                               final AbstractEnvironment env,
                               final Stepper stepper,
                               final TimeStatistics timeStatistics) {
        final var e = ((RoadsEnv) env);
        this.roadPanelView.update(e.getRoads(), e.getAgentInfo(), e.getTrafficLights());
        this.commandsPanelView.updateCommands(stepper, timeStatistics);
    }

    @Override
    public void notifyEnd(final Stepper stepper, final TimeStatistics timeStatistics) {
        this.commandsPanelView.lastUpdateCommands(timeStatistics);
    }


}
