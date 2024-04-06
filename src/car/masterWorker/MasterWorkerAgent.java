package car.masterWorker;

import car.CarAgent;

public interface MasterWorkerAgent {

    void setup();

    void play(int dt);

    void pause();

    void terminate();

    void addCarAgent(final CarAgent carAgent);
}
