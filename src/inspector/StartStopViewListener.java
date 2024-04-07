package inspector;

import simulation.InspectorSimulation;

public interface StartStopViewListener {

    boolean conditionToStart(final InspectorSimulation simulation);

    void onStart(final InspectorSimulation simulation);

}
