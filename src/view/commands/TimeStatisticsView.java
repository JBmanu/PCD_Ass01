package view.commands;

import commands.Stepper;
import commands.TimeStatistics;

import javax.swing.*;
import java.awt.*;

public class TimeStatisticsView extends JPanel {
    public static final String TIMES = "Times: ";
    public static final String MAX_TIME = "Max Time: ";
    public static final String MIN_TIME = "Min Time: ";
    public static final String TOTAL_TIME = "Total Time: ";
    private final JLabel currentAverageTimeLabel;
    private final JLabel currentMaxTimeLabel;
    private final JLabel currentMinTimeLabel;
    private final JLabel currentTotalTimeLabel;
    private final JLabel currentTimesLabel;
    private final FlowLayout layoutManager;

    public TimeStatisticsView() {
        this.currentAverageTimeLabel = new JLabel();
        this.currentMaxTimeLabel = new JLabel();
        this.currentMinTimeLabel = new JLabel();
        this.currentTotalTimeLabel = new JLabel();
        this.currentTimesLabel = new JLabel();
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);

        this.currentTimesLabel.setText(TIMES + 0);
        this.currentMaxTimeLabel.setText(MAX_TIME + 0);
        this.currentMinTimeLabel.setText(MIN_TIME + 0);
        this.currentTotalTimeLabel.setText(TOTAL_TIME + 0);

        this.setLayout(this.layoutManager);
        this.setBackground(Color.white);
        this.add(this.currentAverageTimeLabel);
        this.add(this.currentMaxTimeLabel);
        this.add(this.currentMinTimeLabel);
        this.add(this.currentTotalTimeLabel);
        this.add(this.currentTimesLabel);
    }

    public void updateStatistics(TimeStatistics timeStatistics) {
//        this.currentAverageTimeLabel.setText("Average Time: " + timeStatistics.currentWallTime());
        this.currentTimesLabel.setText(TIMES + timeStatistics.currentWallTime());
        this.currentMaxTimeLabel.setText(MAX_TIME + 0);
        this.currentMinTimeLabel.setText(MIN_TIME + 0);
        this.currentTotalTimeLabel.setText(TOTAL_TIME + timeStatistics.totalWallTime());
    }
}
