package simulation.listener;

import inspector.road.RoadSimStatistics;
import inspector.stepper.Stepper;
import inspector.timeStatistics.TimeStatistics;

public interface ViewSimulationListener {
    /**
     * Called at the beginning of the simulation
     *
     */
    void notifyInit(final Stepper stepper,
                    final TimeStatistics timeStatistics,
                    final RoadSimStatistics roadStatistics);

    /**
     * Called at each step, updater all updates
     *
     */
    void notifyStepDone(final Stepper stepper,
                        final TimeStatistics timeStatistics,
                        final RoadSimStatistics roadStatistics);

    void notifyEnd(final Stepper stepper,
                   final TimeStatistics timeStatistics,
                   final RoadSimStatistics roadStatistics);
}
