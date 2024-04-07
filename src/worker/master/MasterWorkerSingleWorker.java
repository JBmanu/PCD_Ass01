package worker.master;

import car.CarAgent;
import car.command.CarCommand;
import car.command.concrete.ActionCommand;
import car.command.concrete.DecideCommand;
import car.command.concrete.SenseCommand;
import worker.slave.BaseWorker;
import monitor.startStop.StartStopMonitor;
import worker.slave.Worker;
import worker.slave.WorkerCar;

import java.util.ArrayList;
import java.util.List;

public class MasterWorkerSingleWorker extends BaseWorker implements MasterWorkerAgent {
    private Worker carsWorker;
    private final List<CarAgent> carAgents;
    private final StartStopMonitor starStopMonitorSimulation;
    private int timeDt;

    public MasterWorkerSingleWorker(final StartStopMonitor starStopMonitorSimulation) {
        super();
        this.starStopMonitorSimulation = starStopMonitorSimulation;
        this.carAgents = new ArrayList<>();
    }

    @Override
    protected void execute() {
        this.carAgents.stream().parallel().forEach(carAgent -> carAgent.setTimeDt(this.timeDt));
        this.runCommand(new SenseCommand());
        this.runCommand(new DecideCommand());
        this.runCommand(new ActionCommand());
        this.starStopMonitorSimulation.play();
    }

    private void runCommand(final CarCommand command) {
        this.carsWorker.play(command);
        this.pauseAndWaitUntilPlay();
    }

    @Override
    public void setup() {
        this.carsWorker = new WorkerCar(this.carAgents);
        this.carsWorker.addStartStopMonitorInTail(this);
    }

    @Override
    public void execute(int dt) {
        this.timeDt = dt;
        this.play();
    }

    @Override
    public void terminateWorkers() {
        this.carsWorker.terminate();
        super.terminate();
    }

    @Override
    public void addCarAgent(final CarAgent carAgent) {
        this.carAgents.add(carAgent);
    }


}
