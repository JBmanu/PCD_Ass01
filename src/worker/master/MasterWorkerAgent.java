package worker.master;

import car.CarAgent;

public interface MasterWorkerAgent {

    void setup();

    void execute(int dt);

    void terminateWorkers();

    void addCarAgent(final CarAgent carAgent);
}
