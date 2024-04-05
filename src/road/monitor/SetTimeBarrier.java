package road.monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SetTimeBarrier implements Barrier {
    private final Lock mutex;
    private final Condition isAvail;
    final int participants;
    int counter;

    public SetTimeBarrier(final int nParticipants) {
        this.mutex = new ReentrantLock();
        this.isAvail = this.mutex.newCondition();
        this.participants = nParticipants;
        this.counter = 0;
    }

    @Override
    public void hitAndWaitAll() {
        this.mutex.lock();
        this.counter += 1;
        if (this.counter == this.participants) {
            this.isAvail.signalAll();
        } else {
            try {
                while (this.counter < this.participants) this.isAvail.await();
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.mutex.unlock();
    }
}
