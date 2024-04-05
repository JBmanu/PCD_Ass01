package car.command;

import car.CarAgent;

public class CarDecideCommand implements CarCommand {
    private final CarAgent car;

    public CarDecideCommand(final CarAgent car) {
        this.car = car;
    }

    @Override
    public void execute() {
        this.car.decide();
    }
}
