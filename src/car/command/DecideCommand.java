package car.command;

import car.CarAgent;

import java.util.Optional;

public class DecideCommand implements CarCommand {
    private final CarAgent car;

    public DecideCommand(final CarAgent car) {
        this.car = car;
    }

    @Override
    public void execute() {
        this.car.setSelectedAction(Optional.empty());
        this.car.decide();
    }
}
