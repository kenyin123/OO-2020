import tray.Person;
import tray.WaitingTray;
import com.oocourse.elevator1.ElevatorInput;
import com.oocourse.elevator1.PersonRequest;

public class Producer extends Thread {
    private WaitingTray waiting;
    private Flag flag;

    public Producer(WaitingTray waiting, Flag flag) {
        this.waiting = waiting;
        this.flag = flag;
    }

    public void run() {
        ElevatorInput elevatorInput = new ElevatorInput(System.in);
        while (true) {
            try {
                PersonRequest request = elevatorInput.nextPersonRequest();
                int id = request.getPersonId();
                int from = request.getFromFloor();
                int to = request.getToFloor();
                waiting.add(new Person(id, from, to));
                if (request == null) {
                    elevatorInput.close();
                    flag.setFlag(true);
                    break;
                }
            } catch (Exception e) {
                ;
            }
        }
    }
}
