package inspector.masterWorker;

import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;

public class MasterWorkerView extends JPanel {
    private final DefaultComboBoxModel<String> comboBoxModel;
    private final JComboBox<String> comboBox;

    public MasterWorkerView() {
        this.comboBoxModel = new DefaultComboBoxModel<>();
        this.comboBoxModel.addElement("Generic Worker");
        this.comboBoxModel.addElement("Specific Worker");
        this.comboBox = new JComboBox<>(this.comboBoxModel);
        this.setLayout(new FlowLayout());
        this.add(this.comboBox);

        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.setOpaque(false);
    }

}
