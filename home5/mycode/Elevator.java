import tray.ElevatorTray;
import tray.WaitingTray;
import com.oocourse.TimableOutput;

public class Elevator {
    private ElevatorTray elevator;
    private WaitingTray waiting;
    private int floor;
    private int upLook;
    private int downLook;

    private static final int doorTime = 200;
    private static final int moveTime = 400;

    public Elevator(WaitingTray waiting) {
        floor = 1;
        elevator = new ElevatorTray();
        this.waiting = waiting;
        upLook = 15;
        downLook = 1;
    }

    //没有要处理的请求
    public boolean isEmpty() {
        return elevator.isEmpty() && waiting.isEmpty();
    }

    //到达一层时的操作
    public void arrive() throws InterruptedException {
        TimableOutput.println("ARRIVE-" + floor);
        if (!elevator.hasOff(floor) && !waiting.hasOn(floor)) {
            return;
        }
        open();
        if (elevator.hasOff(floor)) {
            elevator.getOff(floor);
        }
        if (waiting.hasOn(floor)) {
            elevator.getOn(waiting.remove(floor));
        }
        close();
    }

    private void open() throws InterruptedException {
        TimableOutput.println("OPEN-" + floor);
        Thread.sleep(doorTime);
    }

    private void close() throws InterruptedException {
        Thread.sleep(doorTime);
        TimableOutput.println("CLOSE-" + floor);
    }

    //移动一层的操作
    public void move(boolean direction) throws InterruptedException {
        Thread.sleep(moveTime);
        if (direction) {
            floor++;
        } else {
            floor--;
        }
        arrive();
    }

    /*
    public void work() throws InterruptedException {
        if (!getLook()) {
            return;
        }
        arriveTheFirst();
        while (floor - 1 < upLook) {
            move(true);
        }
        while (floor - 1 > downLook) {
            move(false);
        }
    }

    private boolean getLook() {
        if (this.isEmpty()) {
            return false;
        }
        int eup = 0;
        int edown = 14;
        int wup = 0;
        int wdown = 14;
        if (!elevator.isEmpty()) {
            eup = elevator.getEup();
            edown = elevator.getEdown();
        }
        if (!waiting.isEmpty()) {
            wup = waiting.getWup();
            wdown = waiting.getWdown();
        }
        upLook = Math.max(eup, wup);
        downLook = Math.min(edown, wdown);
        return true;
    }

     */

    private void moveTo(int to) throws InterruptedException {
        while (floor < to) {
            move(true);
        }
        while (floor > to) {
            move(false);
        }
    }

    public void arriveTheFirst() throws InterruptedException {
        int tempFloor = waiting.init() + 1;
        if (floor != tempFloor) {
            moveTo(tempFloor);
        } else {
            open();
            elevator.getOn(waiting.remove(floor));
            close();
        }
    }

    public void work() throws InterruptedException {
        while (!elevator.isEmpty()) {
            getLook();
            while (floor - 1 < upLook) {
                move(true);
                getLook();
            }

            while (floor - 1 > downLook) {
                move(false);
                getLook();
            }
        }

    }

    private void getLook() {
        upLook = elevator.getEup();
        downLook = elevator.getEdown();
    }
}

