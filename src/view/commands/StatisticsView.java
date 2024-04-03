package view.commands;

import javax.swing.*;
import java.awt.*;

public class StatisticsView extends JPanel {
    private final JLabel currentAverageTimeLabel;
    private final JLabel currentMaxTimeLabel;
    private final JLabel currentMinTimeLabel;
    private final JLabel currentTotalTimeLabel;
    private final JLabel currentTimesLabel;
    private final FlowLayout layoutManager;

    public StatisticsView() {
        this.currentAverageTimeLabel = new JLabel();
        this.currentMaxTimeLabel = new JLabel();
        this.currentMinTimeLabel = new JLabel();
        this.currentTotalTimeLabel = new JLabel();
        this.currentTimesLabel = new JLabel();
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);

        this.setLayout(this.layoutManager);
        this.setBackground(Color.white);
        this.add(this.currentAverageTimeLabel);
        this.add(this.currentMaxTimeLabel);
        this.add(this.currentMinTimeLabel);
        this.add(this.currentTotalTimeLabel);
        this.add(this.currentTimesLabel);
    }

    public void updateTimes(final int averageTime, final int maxTime, final int minTime, final int totalTime, final int times) {
        this.currentAverageTimeLabel.setText("Average Time: " + averageTime);
        this.currentMaxTimeLabel.setText("Max Time: " + maxTime);
        this.currentMinTimeLabel.setText("Min Time: " + minTime);
        this.currentTotalTimeLabel.setText("Total Time: " + totalTime);
        this.currentTimesLabel.setText("Times: " + times);
    }



}
