package car.command;

import car.CarAgent;

public class CarActionCommand implements CarCommand {
    private final CarAgent car;

    public CarActionCommand(final CarAgent car) {
        this.car = car;
    }

    @Override
    public void execute() {

    }
}
