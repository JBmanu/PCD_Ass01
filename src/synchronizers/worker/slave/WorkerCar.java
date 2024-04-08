package synchronizers.worker.slave;

import car.CarAgent;
import car.command.CarCommand;
import synchronizers.monitor.startStop.StartStopMonitor;

import java.util.ArrayList;
import java.util.List;

public class WorkerCar extends BaseWorker implements Worker {
    private final List<CarAgent> agents;
    private CarCommand command;

    private final List<StartStopMonitor> startStopMonitorInTailList;

    public WorkerCar(final List<CarAgent> agents) {
        super();
        this.agents = agents;
        this.startStopMonitorInTailList = new ArrayList<>();
    }

    @Override
    protected void execute() {
        this.agents.forEach(this.command::execute);
        this.startStopMonitorInTailList.forEach(StartStopMonitor::play);
    }

    @Override
    public void play(final CarCommand command) {
        this.command = command;
        this.play();
    }

    @Override
    public void addStartStopMonitorInTail(final StartStopMonitor startStopMonitor) {
        this.startStopMonitorInTailList.add(startStopMonitor);
    }
}
