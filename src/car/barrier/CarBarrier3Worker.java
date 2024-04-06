package car.barrier;

import car.command.InvokerCommand;
import simulation.AbstractSimulation;

import java.util.ArrayList;
import java.util.List;

public class CarBarrier3Worker implements AgentBarrierLogic {
    private final List<InvokerCommand> invokerCarCommands;
//    private final AbstractSimulation simulation;
//    private final CarWorker senseWorker;
//    private final CarWorker decideWorker;
//    private final CarWorker actionWorker;

    public CarBarrier3Worker(final AbstractSimulation simulation) {
//        this.simulation = simulation;
        this.invokerCarCommands = new ArrayList<>();
//        this.senseWorker = new SenseWorker(null);
//        this.decideWorker = new DecideWorker(null);
//        this.actionWorker = new ActionWorker(null);
//
//        this.senseWorker.setStartStopInTail(this.decideWorker.startStopSimulation());
//        this.decideWorker.setStartStopInTail(this.actionWorker.startStopSimulation());
//        this.actionWorker.setStartStopInTail(this.simulation.startStopMonitor());
//
//        this.senseWorker.start();
//        this.decideWorker.start();
//        this.actionWorker.start();
    }

    @Override
    public void addInvokerCommand(final InvokerCommand invokerCommand) {
        this.invokerCarCommands.add(invokerCommand);
//        this.senseWorker.addInvokerCarInvoker(invokerCommand);
//        this.decideWorker.addInvokerCarInvoker(invokerCommand);
//        this.actionWorker.addInvokerCarInvoker(invokerCommand);
    }

    @Override
    public void execute(final int dt) {
        this.invokerCarCommands.forEach(invoker -> invoker.setup(dt));
        this.invokerCarCommands.forEach(InvokerCommand::sense);
        this.invokerCarCommands.forEach(InvokerCommand::decide);
        this.invokerCarCommands.forEach(InvokerCommand::action);
    }

}
