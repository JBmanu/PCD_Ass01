package car.command;

import car.CarAgent;

public class ActionCommand implements CarCommand {
    private final CarAgent car;

    public ActionCommand(final CarAgent car) {
        this.car = car;
    }

    @Override
    public void execute() {
        this.car.selectedAction().ifPresent(this.car::doAction);
    }
}
