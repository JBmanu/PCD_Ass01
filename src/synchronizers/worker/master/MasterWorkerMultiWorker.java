package synchronizers.worker.master;

import car.CarAgent;
import car.command.CarCommand;
import car.command.concrete.ActionCommand;
import car.command.concrete.DecideCommand;
import car.command.concrete.SenseCommand;
import synchronizers.worker.slave.Worker;
import synchronizers.worker.slave.WorkerCarBarrier;
import synchronizers.monitor.cycleBarrier.MyCyclicBarrier;
import synchronizers.monitor.cycleBarrier.MyCyclicBarrierImpl;
import synchronizers.monitor.startStop.StartStopMonitor;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class MasterWorkerMultiWorker implements MasterWorkerAgent {
    private final StartStopMonitor starStopMonitorSimulation;

    private List<Worker> carsWorkers;
    private MyCyclicBarrier cycleBarrier;

    private final List<CarAgent> carAgents;
    private final int divisor;

    public MasterWorkerMultiWorker(final StartStopMonitor starStopMonitorSimulation) {
        super();
        this.starStopMonitorSimulation = starStopMonitorSimulation;
        this.carAgents = new ArrayList<>();
        this.carsWorkers = new ArrayList<>();
        this.cycleBarrier = new MyCyclicBarrierImpl(this.starStopMonitorSimulation);
        this.divisor = 9;
    }

    @Override
    public void setup() {
        final List<List<CarAgent>> carDividedList = ListUtils.divideEqually(this.carAgents, this.divisor);
        this.cycleBarrier.setup(carDividedList.size());
        carDividedList.forEach(car -> this.carsWorkers.add(new WorkerCarBarrier(this.cycleBarrier, car)));
    }

    private void runCommand(final CarCommand command) {
        System.out.println("RUN COMMAND: " + command.getClass().getSimpleName());
        this.carsWorkers.forEach(worker -> worker.play(command));
        this.starStopMonitorSimulation.pauseAndWaitUntilPlay();
    }

    @Override
    public void execute(int dt) {
        this.carAgents.stream().parallel().forEach(carAgent -> carAgent.setTimeDt(dt));
        this.runCommand(new SenseCommand());
        this.runCommand(new DecideCommand());
        this.runCommand(new ActionCommand());
    }

    @Override
    public void terminateWorkers() {
        this.carsWorkers.forEach(Worker::terminate);
    }

    @Override
    public void addCarAgent(CarAgent carAgent) {
        this.carAgents.add(carAgent);
    }


}
