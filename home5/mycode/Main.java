import tray.WaitingTray;
import com.oocourse.TimableOutput;

public class Main {
    public static void main(String[] args) {
        TimableOutput.initStartTimestamp();
        WaitingTray waiting = new WaitingTray();
        Flag flag = new Flag(false);
        Consumer consumer = new Consumer(waiting, flag);
        Producer producer = new Producer(waiting, flag);
        producer.start();
        //Producer2 producer2 = new Producer2(waiting,flag);
        //producer2.start();
        consumer.start();
    }
}
