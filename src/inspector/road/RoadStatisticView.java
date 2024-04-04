package inspector.road;

import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class RoadStatisticView extends JPanel {
    private static final int HGAP = 15;
    private static final String AVERAGE_SPEED = "Average Speed: ";
    private static final String MAX_SPEED = "Max Speed: ";
    private static final String MIN_SPEED = "Min Speed: ";

    private final JLabel averageSpeedLabel;
    private final JLabel maxSpeedLabel;
    private final JLabel minSpeedLabel;
    private final FlowLayout layoutManager;

    public RoadStatisticView() {
        this.averageSpeedLabel = new JLabel();
        this.maxSpeedLabel = new JLabel();
        this.minSpeedLabel = new JLabel();
        this.layoutManager = new FlowLayout(FlowLayout.CENTER);
        this.layoutManager.setHgap(HGAP);

        this.setLayout(this.layoutManager);
        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);

        this.add(this.averageSpeedLabel);
        this.add(this.maxSpeedLabel);
        this.add(this.minSpeedLabel);
    }

    public void updateStatistics(final RoadSimStatistics roadStatistics) {
        this.averageSpeedLabel.setText(AVERAGE_SPEED + roadStatistics.averageSpeed());
        this.maxSpeedLabel.setText(MAX_SPEED + roadStatistics.maxSpeed());
        this.minSpeedLabel.setText(MIN_SPEED + roadStatistics.minSpeed());
    }




}
