package view.commands;

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
//        this.setSize();

        this.layoutManager = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(this.layoutManager);
        this.setBackground(Color.white);
        this.add(this.startButton);
        this.add(this.stopButton);
    }

    public void setStartButtonAction(final Runnable action) {
        this.startButton.addActionListener(e -> action.run());
    }

    public void setStopButtonAction(final Runnable action) {
        this.stopButton.addActionListener(e -> action.run());
    }
}
