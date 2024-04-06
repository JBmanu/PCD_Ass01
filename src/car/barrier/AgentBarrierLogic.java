package car.barrier;

import car.command.InvokerCommand;

public interface AgentBarrierLogic {

    void addInvokerCommand(final InvokerCommand invokerCommand);
    void execute(int dt);
}
