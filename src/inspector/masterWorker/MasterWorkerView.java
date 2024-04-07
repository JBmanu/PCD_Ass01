package inspector.masterWorker;

import utils.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MasterWorkerView extends JPanel {
    private final DefaultComboBoxModel<String> comboBoxModel;
    private final JComboBox<String> comboBox;

    // Generic
    private final JPanel genericPanel;
    private final JLabel genericLabel;
    private final JTextField genericTextField;
    // Specific
    private final JPanel specificPanel;
    private final JLabel senseLabel;
    private final JTextField senseTextField;
    private final JLabel decideLabel;
    private final JTextField decideTextField;
    private final JLabel actionLabel;
    private final JTextField actionTextField;

    public MasterWorkerView() {
        this.comboBoxModel = new DefaultComboBoxModel<>();
        this.comboBoxModel.addElement("Generic Worker");
        this.comboBoxModel.addElement("Specific Worker");
        this.comboBox = new JComboBox<>(this.comboBoxModel);

        // Generic
        this.genericLabel = new JLabel("Generic Worker");
        this.genericTextField = new JTextField("2", 3);
        // Specific
        this.senseLabel = new JLabel("Sense");
        this.senseTextField = new JTextField("2", 3);
        this.decideLabel = new JLabel("Decide");
        this.decideTextField = new JTextField("2", 3);
        this.actionLabel = new JLabel("Action");
        this.actionTextField = new JTextField("2", 3);

        // Generic setup
        this.genericPanel = new JPanel(new FlowLayout());
        this.genericPanel.add(this.genericLabel);
        this.genericPanel.add(this.genericTextField);

        // Specific setup
        this.specificPanel = new JPanel(new FlowLayout());
        this.specificPanel.add(this.senseLabel);
        this.specificPanel.add(this.senseTextField);
        this.specificPanel.add(this.decideLabel);
        this.specificPanel.add(this.decideTextField);
        this.specificPanel.add(this.actionLabel);
        this.specificPanel.add(this.actionTextField);

        this.setLayout(new FlowLayout());
        this.add(this.genericPanel);
        this.add(this.specificPanel);
        this.add(this.comboBox);

        this.specificPanel.setVisible(false);

        this.setBackground(ViewUtils.GUI_BACKGROUND_COLOR);
        this.setOpaque(false);

        this.comboBox.addActionListener(this.comboBoxActionListener);
    }

    private ActionListener comboBoxActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedOption = (String) comboBox.getSelectedItem();
            System.out.println("Opzione selezionata: " + selectedOption);
            if (selectedOption.equals("Generic Worker")) {
                genericPanel.setVisible(true);
                specificPanel.setVisible(false);
            } else {
                genericPanel.setVisible(false);
                specificPanel.setVisible(true);
            }
        }
    };

}
