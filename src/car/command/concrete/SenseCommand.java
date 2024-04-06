package car.command.concrete;

import car.CarAgent;
import car.CarPercept;
import car.command.CarCommand;

public class SenseCommand implements CarCommand {

    @Override
    public void execute(CarAgent carAgent) {
        carAgent.setCurrentPercept((CarPercept) carAgent.getCurrentPercepts());
    }
}
