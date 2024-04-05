package car.command;

import car.CarAgent;

public class InvokerCarCommand {
    private final CarCommand senseCommand;
    private final CarCommand decideCommand;
    private final CarCommand actionCommand;

    public InvokerCarCommand(final CarAgent car) {
        this.senseCommand = new CarSenseCommand(car);
        this.decideCommand = new CarDecideCommand(car);
        this.actionCommand = new CarActionCommand(car);
    }

    public void sense() {
        this.senseCommand.execute();
    }

    public void decide() {
        this.decideCommand.execute();
    }

    public void action() {
        this.actionCommand.execute();
    }

}
