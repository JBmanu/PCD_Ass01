package car.masterWorker;

import car.CarAgent;
import car.command.CarCommand;
import car.command.concrete.ActionCommand;
import car.command.concrete.DecideCommand;
import car.command.concrete.SenseCommand;
import car.worker.BaseWorker;
import car.worker.Worker;
import car.worker.WorkerCarBarrier;
import monitor.StartStopMonitor;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class MasterWorkerMultiWorker extends BaseWorker implements MasterWorkerAgent {
    private final StartStopMonitor starStopMonitorSimulation;
    private final CyclicBarrier cycleBarrier;
    private final AtomicInteger counterWorker;
    private List<Worker> carsWorkers;
    private final int divisor;
    private final List<CarAgent> carAgents;
    private int timeDt;

    public MasterWorkerMultiWorker(final StartStopMonitor starStopMonitorSimulation) {
        super();
        this.starStopMonitorSimulation = starStopMonitorSimulation;
        this.carAgents = new ArrayList<>();
        this.carsWorkers = new ArrayList<>();
        this.divisor = 30;
        this.cycleBarrier = new CyclicBarrier(this.divisor);
        this.counterWorker = new AtomicInteger(0);
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
        this.carsWorkers.forEach(worker -> worker.play(command));
        this.pauseAndWaitUntilPlay();
    }

    @Override
    public void setup() {
        final List<List<CarAgent>> carDividedList = ListUtils.divideEqually(this.carAgents, this.divisor);
        carDividedList.forEach(car -> this.carsWorkers.add(new WorkerCarBarrier(car, this.cycleBarrier, this.counterWorker)));
        this.carsWorkers.forEach(worker -> worker.addStartStopMonitorInTail(this));
    }

    @Override
    public void play(int dt) {
        this.timeDt = dt;
        this.play();
    }

    @Override
    public void addCarAgent(CarAgent carAgent) {
        this.carAgents.add(carAgent);
    }


}
