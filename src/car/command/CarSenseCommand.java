package car.command;

import car.CarAgent;
import car.CarPercept;

public class CarSenseCommand implements CarCommand {

    private final CarAgent car;

    public CarSenseCommand(final CarAgent car) {
        this.car = car;
    }


    @Override
    public void execute() {
        this.car.setCurrentPercept((CarPercept) this.car.getCurrentPercepts());
    }
}
