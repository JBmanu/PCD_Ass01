package inspector;

import inspector.stepper.StepperView;
import simulation.InspectorSimulation;
import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StartStopView extends JPanel {
    private static final String START = "Start";
    private static final String PAUSE = "Pause";
    private final JButton startButton;
    private final JButton pauseButton;
    private final FlowLayout layoutManager;
    private List<StartStopViewListener> listeners;

    public StartStopView() {
        this.startButton = new JButton(START);
        this.pauseButton = new JButton(PAUSE);
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);
        this.listeners = new ArrayList<>();

        this.graphicsSetup();
        this.setLayout(this.layoutManager);
        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.add(this.startButton);
        this.add(this.pauseButton);
        this.activateStartButton();
    }

    private void activateStartButton() {
        this.startButton.setEnabled(true);
    }

    private void deactivateStartButton() {
        this.startButton.setEnabled(false);
    }

    private void activateStopButton() {
        this.pauseButton.setEnabled(true);
    }

    private void deactivateStopButton() {
        this.pauseButton.setEnabled(false);
    }

    private void graphicsSetup() {
        this.deactivateStartButton();
        this.deactivateStopButton();
        this.startButton.setBackground(Color.green);
        this.pauseButton.setBackground(Color.red);
    }

    private void switchStop() {
        this.deactivateStartButton();
        this.activateStopButton();
    }

    private void switchStart() {
        this.deactivateStopButton();
        this.activateStartButton();
    }

    public void addListener(final StartStopViewListener listener) {
        this.listeners.add(listener);
    }

    public void setupSimulation(final InspectorSimulation simulation) {
        this.startButton.addActionListener(e -> {
            if (this.listeners.stream().map(listener -> listener.conditionToStart(simulation)).toList().contains(false)) return;
            this.listeners.forEach(listener -> listener.onStart(simulation));
            simulation.setup();
            simulation.startStopMonitor().play();
            System.out.println("PLAY");
            this.switchStop();
        });
        this.pauseButton.addActionListener(e -> {
            simulation.startStopMonitor().pause();
            this.switchStart();
        });
    }
}
