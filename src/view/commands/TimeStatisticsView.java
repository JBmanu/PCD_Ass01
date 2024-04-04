package view.commands;

import commands.TimeStatistics;

import javax.swing.*;
import java.awt.*;

public class TimeStatisticsView extends JPanel {
    public static final String TIMES = "Times: ";
    public static final String TOTAL_TIME = "Total Time: ";
    public static final String AVERAGE_TIME = "Average Time: ";
    private static final String MILLIS_SECONDS = "ms";
    private final JLabel currentAverageTimeLabel;
    private final JLabel currentTotalTimeLabel;
    private final JLabel currentTimesLabel;
    private final FlowLayout layoutManager;

    public TimeStatisticsView() {
        this.currentAverageTimeLabel = new JLabel();
        this.currentTotalTimeLabel = new JLabel();
        this.currentTimesLabel = new JLabel();
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);
//        this.layoutManager.setVgap(10);

        this.currentTimesLabel.setText(TIMES + 0);

        this.setLayout(this.layoutManager);
        this.setBackground(Color.white);
        this.add(this.currentTimesLabel);
        this.add(this.currentTotalTimeLabel);
        this.add(this.currentAverageTimeLabel);
    }

    public void updateStatistics(final TimeStatistics timeStatistics) {
        this.currentTimesLabel.setText(TIMES + timeStatistics.currentWallTimeSubtractStartWallTime() + MILLIS_SECONDS);
    }

    public void lastUpdateStatistics(final TimeStatistics timeStatistics) {
        this.currentAverageTimeLabel.setText(AVERAGE_TIME + timeStatistics.averageTimeForStep() + MILLIS_SECONDS);
        this.currentTotalTimeLabel.setText(TOTAL_TIME + timeStatistics.totalWallTime() + MILLIS_SECONDS);
    }
}
