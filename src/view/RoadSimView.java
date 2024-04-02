package view;

import simengineseq.AbstractAgent;
import simengineseq.AbstractEnvironment;
import simengineseq.SimulationListener;
import simtrafficbase.*;
import view.commands.CommandsPanelView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoadSimView extends JFrame implements SimulationListener {
	private final CommandsPanelView commandsPanelView;
	private final RoadSimPanelView roadPanelView;
	private final BorderLayout layoutManager;

	public RoadSimView() {
		super("RoadSim View");
        this.setSize(ViewUtils.GUI_WIDTH, ViewUtils.GUI_HEIGHT);
		this.commandsPanelView = new CommandsPanelView();
        this.roadPanelView = new RoadSimPanelView(ViewUtils.ROAD_WIDTH, ViewUtils.ROAD_HEIGHT);
		this.layoutManager = new BorderLayout();

		this.setLayout(this.layoutManager);
		this.add(BorderLayout.NORTH, this.commandsPanelView);
		this.add(BorderLayout.CENTER, this.roadPanelView);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	public void display() {
		SwingUtilities.invokeLater(() -> this.setVisible(true));
	}

	@Override
	public void notifyInit(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyStepDone(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env) {
		final var e = ((RoadsEnv) env);
        this.roadPanelView.update(e.getRoads(), e.getAgentInfo(), e.getTrafficLights());
	}

}
