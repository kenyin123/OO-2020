import tray.WaitingTray;

public class Consumer extends Thread {
    private Elevator elevator;
    private WaitingTray waiting;
    private Flag flag;

    public Consumer(WaitingTray waiting, Flag flag) {
        this.waiting = waiting;
        this.elevator = new Elevator(waiting);
        this.flag = flag;
    }

    public void run() {
        while (true) {
            try {
                if (waiting.isEmpty() && flag.getFlag()) {
                    break;
                }
                elevator.arriveTheFirst();
                elevator.work();
            } catch (InterruptedException e) {
                ;
            }

        }

    }
}
