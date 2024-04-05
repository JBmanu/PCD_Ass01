package car.worker;

import java.util.concurrent.CyclicBarrier;

public class ActionWorker extends BaseWorker implements CarWorker {

    public ActionWorker(final CyclicBarrier barrier) {
        super(barrier);
    }

    @Override
    public void run() {
        while (!this.isEmptyInvokerCarCommandQueue()) {
            try {
                this.takeInvokerCarCommand().action();
                this.barrier().await();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

}
