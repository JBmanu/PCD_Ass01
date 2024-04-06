package car.command.concrete;

import car.CarAgent;
import car.command.CarCommand;

public class ActionCommand implements CarCommand {

    @Override
    public void execute(CarAgent carAgent) {
        carAgent.selectedAction().ifPresent(carAgent::doAction);
    }
}
