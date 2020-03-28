package tray;

import java.util.ArrayList;

//单个floor
public class Tray {
    private ArrayList<Person> list;

    public Tray() {
        list = new ArrayList<>();
    }

    public Tray(ArrayList<Person> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void add(Person p) {
        list.add(p);
    }

    public void remove(Person p) {
        list.remove(p);
    }

    public ArrayList<Person> getList() {
        return (ArrayList<Person>) list.clone();
    }

    public Tray clone() {
        return new Tray((ArrayList<Person>) list.clone());
    }

    public void clear() {
        list.clear();
    }
}
