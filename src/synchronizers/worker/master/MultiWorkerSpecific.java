package synchronizers.worker.master;

import car.CarAgent;
import car.command.CarCommand;
import car.command.concrete.ActionCommand;
import car.command.concrete.DecideCommand;
import car.command.concrete.SenseCommand;
import synchronizers.monitor.cycleBarrier.MyCyclicBarrier;
import synchronizers.monitor.cycleBarrier.MyCyclicBarrierImpl;
import synchronizers.monitor.startStop.StartStopMonitor;
import synchronizers.worker.slave.Worker;
import synchronizers.worker.slave.WorkerCarBarrier;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class MultiWorkerSpecific extends BaseMasterWorker implements MasterWorker {
    private final List<List<Worker>> carsWorkersList;
    private final List<CarCommand> commands;
    private final MyCyclicBarrier senseCycleBarrier;
    private final MyCyclicBarrier decideCycleBarrier;
    private final MyCyclicBarrier actionCycleBarrier;
    private int senseDivisor;
    private int decideDivisor;
    private int actionDivisor;

    public MultiWorkerSpecific(StartStopMonitor starStopMonitorSimulation) {
        super(starStopMonitorSimulation);
        this.carsWorkersList = new ArrayList<>();
        this.commands = List.of(new SenseCommand(), new DecideCommand(), new ActionCommand());
        this.senseCycleBarrier = new MyCyclicBarrierImpl(this.startStopMonitorSimulation());
        this.decideCycleBarrier = new MyCyclicBarrierImpl(this.startStopMonitorSimulation());
        this.actionCycleBarrier = new MyCyclicBarrierImpl(this.startStopMonitorSimulation());
        this.senseDivisor = 5;
        this.decideDivisor = 5;
        this.actionDivisor = 5;
    }

    @Override
    public void setup() {
        final List<List<CarAgent>> carDividedSenseList = ListUtils.divideEqually(this.carAgents(), this.senseDivisor);
        final List<List<CarAgent>> carDividedDecideList = ListUtils.divideEqually(this.carAgents(), this.decideDivisor);
        final List<List<CarAgent>> carDividedActionList = ListUtils.divideEqually(this.carAgents(), this.actionDivisor);
        this.senseCycleBarrier.setup(carDividedSenseList.size());
        this.decideCycleBarrier.setup(carDividedDecideList.size());
        this.actionCycleBarrier.setup(carDividedActionList.size());

        List<Worker> senseWorkers = carDividedSenseList.stream().map(car -> (Worker) new WorkerCarBarrier(this.senseCycleBarrier, car)).toList();
        List<Worker> decideWorkers = carDividedDecideList.stream().map(car -> (Worker) new WorkerCarBarrier(this.decideCycleBarrier, car)).toList();
        List<Worker> actionWorkers = carDividedActionList.stream().map(car -> (Worker) new WorkerCarBarrier(this.actionCycleBarrier, car)).toList();

        this.carsWorkersList.add(senseWorkers);
        this.carsWorkersList.add(decideWorkers);
        this.carsWorkersList.add(actionWorkers);
    }

    @Override
    public void execute(int dt) {
        this.setDtToCarAgents(dt);
        int index = 0;
        for (var command : this.commands) {
            this.carsWorkersList.get(index++).forEach(worker -> worker.play(command));
            this.startStopMonitorSimulation().pauseAndWaitUntilPlay();
        }
    }

    @Override
    public void terminateWorkers() {
        this.carsWorkersList.forEach(workers -> workers.forEach(Worker::terminate));
    }
}
