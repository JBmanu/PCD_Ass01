package car.command.concrete;

import car.CarAgent;
import car.CarPercept;
import car.command.CarCommand;

public class SenseCommand implements CarCommand {

    @Override
    public void execute(CarAgent car) {
        car.setCurrentPercept((CarPercept) car.getCurrentPercepts());
    }
}
