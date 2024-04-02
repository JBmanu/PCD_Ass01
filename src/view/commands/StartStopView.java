package view.commands;

import simengineseq.AbstractSimulation;

import javax.swing.*;
import java.awt.*;

public class StartStopView extends JPanel {
    private static final String START = "Start";
    private static final String PAUSE = "Pause";
    private final JButton startButton;
    private final JButton pauseButton;
    private final FlowLayout layoutManager;

    public StartStopView() {
        this.startButton = new JButton(START);
        this.pauseButton = new JButton(PAUSE);
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);

        this.graphicsSetup();
        this.setLayout(this.layoutManager);
        this.setBackground(Color.white);
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

    public void setupSimulation(final AbstractSimulation simulation, final StepperView stepperView) {
        this.startButton.addActionListener(e -> {
            final int steps = stepperView.getTextField();
            if (steps < 1) return;
            simulation.play(steps);
            this.switchStop();
        });
        this.pauseButton.addActionListener(e -> {
            simulation.pause();
            this.switchStart();
        });
    }
}
