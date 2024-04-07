package inspector;

import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

enum SimulationType {
    SINGLE_ROAD("Single Road"),
    SINGLE_ROAD_TRAFFIC_LIGHT("Single Road Traffic Light"),
    CROSSROAD_TRAFFIC_LIGHT("Crossroad Traffic Light");

    private final String name;

    SimulationType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

public class SimulationView extends JPanel {
    private final DefaultComboBoxModel<String> comboBoxModel;
    private final JComboBox<String> comboBox;

    public SimulationView() {
        this.comboBoxModel = new DefaultComboBoxModel<>();
        this.comboBoxModel.addElement(SimulationType.SINGLE_ROAD.getName());
        this.comboBoxModel.addElement(SimulationType.SINGLE_ROAD_TRAFFIC_LIGHT.getName());
        this.comboBoxModel.addElement(SimulationType.CROSSROAD_TRAFFIC_LIGHT.getName());
        this.comboBox = new JComboBox<>(this.comboBoxModel);

        this.setLayout(new FlowLayout());
        this.add(this.comboBox);

        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.setOpaque(false);

        this.comboBox.addActionListener(this.comboBoxActionListener);
    }

    private final ActionListener comboBoxActionListener = e -> {
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String selectedOption = (String) comboBox.getSelectedItem();
        System.out.println("Opzione selezionata: " + selectedOption);

        if (Objects.equals(selectedOption, SimulationType.SINGLE_ROAD.getName())) {

        }
    };

}
