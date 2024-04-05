package car.command;

import car.CarAgent;

public class InvokerCarCommand {
    private final CarCommand senseCommand;
    private final CarCommand decideCommand;
    private final CarCommand actionCommand;

    public InvokerCarCommand(final CarAgent car) {
        this.senseCommand = new SenseCommand(car);
        this.decideCommand = new DecideCommand(car);
        this.actionCommand = new ActionCommand(car);
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
