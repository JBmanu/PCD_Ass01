package synchronizers.worker.master;

import car.CarAgent;

public interface MasterWorker {

    void setup();

    void execute(int dt);

    void terminateWorkers();

    void addCarAgent(final CarAgent carAgent);
}
