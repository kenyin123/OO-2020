package tray;

import com.oocourse.TimableOutput;

public class Person {
    private int id;
    private int from;
    private int to;

    public Person(int id, int from, int to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getId() {
        return id;
    }

    public int getTo() {
        return to;
    }

    public void getOn() {
        TimableOutput.println("IN-" + id + "-" + from);
    }

    public void getOff() {
        TimableOutput.println("OUT-" + id + "-" + to);
    }
}
