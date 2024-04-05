package car.barrier;

import car.command.InvokerCarCommand;
import car.worker.ActionWorker;
import car.worker.CarWorker;
import car.worker.DecideWorker;
import car.worker.SenseWorker;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CarBarrier3Worker implements CarBarrierLogic {
    private final CyclicBarrier barrier;
    private final List<InvokerCarCommand> invokerCarCommands;
    private final CarWorker senseWorker;
    private final CarWorker decideWorker;
    private final CarWorker actionWorker;

    public CarBarrier3Worker(final List<InvokerCarCommand> invokerCarCommands) {
        this.invokerCarCommands = invokerCarCommands;
        this.barrier = new CyclicBarrier(this.invokerCarCommands.size());
        this.senseWorker = new SenseWorker(this.barrier);
        this.decideWorker = new DecideWorker(this.barrier);
        this.actionWorker = new ActionWorker(this.barrier);
    }


}
