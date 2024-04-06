package car.command;

import car.CarAgent;

public class InvokerCarCommandImpl implements InvokerCommand {
    private final CarAgent car;
    private final CarCommand senseCommand;
    private final CarCommand decideCommand;
    private final CarCommand actionCommand;

    public InvokerCarCommandImpl(final CarAgent car) {
        this.car = car;
        this.senseCommand = new SenseCommand(car);
        this.decideCommand = new DecideCommand(car);
        this.actionCommand = new ActionCommand(car);
    }

    @Override
    public void setup(final int dt) {
        this.car.setTimeDt(dt);
    }

    @Override
    public void sense() {
        this.senseCommand.execute();
    }
    @Override
    public void decide() {
        this.decideCommand.execute();
    }
    @Override
    public void action() {
        this.actionCommand.execute();
    }

}
