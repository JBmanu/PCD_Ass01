package worker.slave;

import car.CarAgent;
import car.command.CarCommand;
import monitor.cycleBarrier.MyCyclicBarrier;
import monitor.startStop.StartStopMonitor;

import java.util.ArrayList;
import java.util.List;

public class WorkerCarBarrier extends BaseWorker implements Worker {
    private final List<CarAgent> agents;
    private CarCommand command;
    private final MyCyclicBarrier cyclicBarrier;
    private final List<StartStopMonitor> startStopMonitorInTailList;


    public WorkerCarBarrier(final MyCyclicBarrier cyclicBarrier, final List<CarAgent> agents) {
        super();
        this.agents = agents;
        this.cyclicBarrier = cyclicBarrier;
        this.startStopMonitorInTailList = new ArrayList<>();
    }

    @Override
    protected void execute() {
        System.out.print("HIT ");
        this.agents.forEach(this.command::execute);
        this.cyclicBarrier.awaitThatBroken();
    }

    @Override
    public void play(CarCommand command) {
        this.command = command;
        this.play();
    }

    @Override
    public void addStartStopMonitorInTail(StartStopMonitor starStopMonitorSimulation) {
        this.startStopMonitorInTailList.add(starStopMonitorSimulation);
    }
}
