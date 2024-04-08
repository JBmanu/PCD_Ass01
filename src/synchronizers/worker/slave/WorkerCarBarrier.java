package synchronizers.worker.slave;

import car.CarAgent;
import car.command.CarCommand;
import synchronizers.monitor.cycleBarrier.MyCyclicBarrier;
import synchronizers.monitor.startStop.StartStopMonitor;

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
        this.cyclicBarrier.hit();
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
