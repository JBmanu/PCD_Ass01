package view.commands;

import simengineseq.AbstractSimulation;

import javax.swing.*;
import java.awt.*;

public class StartStopView extends JPanel{
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private final JButton startButton;
    private final JButton stopButton;
    private final FlowLayout layoutManager;

    public StartStopView() {
        this.startButton = new JButton(START);
        this.stopButton = new JButton(STOP);
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);

        this.graphicsSetup();
        this.setLayout(this.layoutManager);
        this.setBackground(Color.white);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.setEnabled(true);
    }

    private void activateStartButton() {
        this.startButton.setEnabled(true);
    }
    private void deactivateStartButton() {
        this.startButton.setEnabled(false);
    }
    private void activateStopButton() {
        this.stopButton.setEnabled(true);
    }
    private void deactivateStopButton() {
        this.stopButton.setEnabled(false);
    }

    private void graphicsSetup() {
        this.deactivateStartButton();
        this.deactivateStopButton();
        this.startButton.setBackground(Color.green);
        this.stopButton.setBackground(Color.red);
    }

    public void setupSimulation(final AbstractSimulation simulation, final StepperView stepperView) {
        this.startButton.addActionListener(e -> {
            final int steps = stepperView.getTextField();
            if (steps < 1) return;
            simulation.start(stepperView.getTextField());
            this.activateStopButton();
            this.deactivateStartButton();
        });
        this.stopButton.addActionListener(e -> {
            simulation.stop();
            this.activateStartButton();
            this.deactivateStopButton();
        });
    }
}
