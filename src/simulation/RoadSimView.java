package simulation;

import road.RoadsEnv;
import inspector.InspectorPanelView;
import road.RoadPanelView;
import simulation.listener.ViewSimulationListener;
import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class RoadSimView extends JFrame implements ViewSimulationListener {
    private final InspectorPanelView inspectorPanelView;
    private final RoadPanelView roadPanelView;
    private final BorderLayout layoutManager;
    private final JPanel glassPane;

    public RoadSimView() {
        super("RoadSim View");
        this.setSize(ViewUtils.GUI_WIDTH, ViewUtils.GUI_HEIGHT);
        this.inspectorPanelView = new InspectorPanelView();
        this.roadPanelView = new RoadPanelView(ViewUtils.ROAD_WIDTH, ViewUtils.ROAD_HEIGHT);
        this.layoutManager = new BorderLayout();
        this.glassPane = new JPanel();

        this.setupGlassPane();
        this.setLayout(this.layoutManager);

        this.glassPane.add(this.inspectorPanelView, BorderLayout.NORTH);
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

    public void setupCommandsSimulation(final CommandsSimulation simulation) {
        this.inspectorPanelView.setupSimulation(simulation);
    }

    @Override
    public void notifyInit(final int t, final CommandsSimulation simulation) {
        // TODO Auto-generated method stub
    }

    @Override
    public void notifyStepDone(final int t, final CommandsSimulation simulation) {
        final var e = ((RoadsEnv) simulation.environment());
        this.roadPanelView.update(e.getRoads(), e.getAgentInfo(), e.getTrafficLights());
        this.inspectorPanelView.updateInspector(simulation);
    }

    @Override
    public void notifyEnd(final CommandsSimulation simulation) {
        this.inspectorPanelView.endUpdateInspector(simulation);
    }


}
