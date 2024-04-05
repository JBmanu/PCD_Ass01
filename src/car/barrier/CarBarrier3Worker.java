package car.barrier;

import car.command.InvokerCommand;
import car.worker.ActionWorker;
import car.worker.CarWorker;
import car.worker.DecideWorker;
import car.worker.SenseWorker;

import java.util.ArrayList;
import java.util.List;

public class CarBarrier3Worker implements CarBarrierLogic {
//    private final CyclicBarrier barrier;
    private final List<InvokerCommand> invokerCarCommands;
    private final CarWorker senseWorker;
    private final CarWorker decideWorker;
    private final CarWorker actionWorker;

    public CarBarrier3Worker() {
        this.invokerCarCommands = new ArrayList<>();
        this.senseWorker = new SenseWorker(null);
        this.decideWorker = new DecideWorker(null);
        this.actionWorker = new ActionWorker(null);
    }

    @Override
    public void addInvokerCommand(final InvokerCommand invokerCommand) {
        this.invokerCarCommands.add(invokerCommand);
    }

}
