package view;

import commands.Stepper;
import commands.TimeStatistics;
import simengineseq.AbstractAgent;
import simengineseq.AbstractEnvironment;
import simengineseq.AbstractSimulation;
import simengineseq.SimulationListener;
import simtrafficbase.*;
import view.commands.CommandsPanelView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoadSimView extends JFrame implements SimulationListener {
	private final CommandsPanelView commandsPanelView;
	private final RoadPanelView roadPanelView;
	private final BorderLayout layoutManager;

	public RoadSimView() {
		super("RoadSim View");
        this.setSize(ViewUtils.GUI_WIDTH, ViewUtils.GUI_HEIGHT);
		this.commandsPanelView = new CommandsPanelView();
        this.roadPanelView = new RoadPanelView(ViewUtils.ROAD_WIDTH, ViewUtils.ROAD_HEIGHT);
		this.layoutManager = new BorderLayout();

		this.setLayout(this.layoutManager);
		this.add(BorderLayout.NORTH, this.commandsPanelView);
		this.add(BorderLayout.CENTER, this.roadPanelView);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		SwingUtilities.invokeLater(() -> this.setVisible(true));
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


}
