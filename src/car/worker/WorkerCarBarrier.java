package car.worker;

import car.CarAgent;
import car.command.CarCommand;
import monitor.StartStopMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerCarBarrier extends BaseWorker implements Worker {
    private final List<CarAgent> agents;
    private CarCommand command;
    private final CyclicBarrier cycleBarrier;
    private final AtomicInteger counterWorker;
    private final List<StartStopMonitor> startStopMonitorInTailList;


    public WorkerCarBarrier(final List<CarAgent> agents, final CyclicBarrier barrierCycle, final AtomicInteger counterWorker) {
        super();
        this.agents = agents;
        this.cycleBarrier = barrierCycle;
        this.counterWorker = counterWorker;
        this.startStopMonitorInTailList = new ArrayList<>();
    }

    @Override
    protected void execute() {
        System.out.println("HIT: " + this.command.getClass().getSimpleName());
        this.agents.forEach(this.command::execute);
        try {
            this.cycleBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("BREAK BARRIE: " + this.command.getClass().getSimpleName());
        var value = this.counterWorker.incrementAndGet();
        if (value == this.cycleBarrier.getParties()) {
            System.out.println("ONE SHOT BARRIE: " + this.command.getClass().getSimpleName());
            this.startStopMonitorInTailList.forEach(StartStopMonitor::play);
            this.counterWorker.set(0);
        }
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
