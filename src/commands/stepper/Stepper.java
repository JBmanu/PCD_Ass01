package commands.stepper;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Stepper {
    private int totalStep;
    private int step;

    private final Lock mutex;
    private final Condition conditionTotalStep;
    private final boolean isReadyTotalStep;

    private final Condition conditionStep;
    private final boolean isReadyStep;

    public Stepper() {
        this.totalStep = 0;
        this.step = 0;

        this.mutex = new ReentrantLock();
        this.conditionTotalStep = this.mutex.newCondition();
        this.isReadyTotalStep = false;
        this.conditionStep = this.mutex.newCondition();
        this.isReadyStep = false;
    }

    public int totalStep() {
        return this.totalStep;
    }

    public void setTotalStep(int totalStep) {
        this.totalStep = totalStep;
    }

    public int currentStep() {
        return this.step;
    }

    public void increaseStep() {
        this.step++;
    }

    public boolean hasMoreSteps() {
        return this.step < this.totalStep;
    }


}
