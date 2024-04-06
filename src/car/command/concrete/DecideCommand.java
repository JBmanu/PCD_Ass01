package car.command.concrete;

import car.CarAgent;
import car.command.CarCommand;

import java.util.Optional;

public class DecideCommand implements CarCommand {

    @Override
    public void execute(CarAgent car) {
        car.setSelectedAction(Optional.empty());
        car.decide();
    }
}
