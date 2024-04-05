package car.command;

import car.CarAgent;
import car.CarPercept;

public class SenseCommand implements CarCommand {

    private final CarAgent car;

    public SenseCommand(final CarAgent car) {
        this.car = car;
    }


    @Override
    public void execute() {
        this.car.setCurrentPercept((CarPercept) this.car.getCurrentPercepts());
    }
}
