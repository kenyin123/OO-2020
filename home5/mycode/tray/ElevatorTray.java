package tray;

import java.util.ArrayList;

public class ElevatorTray {
    private ArrayList<Tray> list;

    public ElevatorTray() {
        list = new ArrayList<Tray>();
        int i;
        for (i = 0; i < 15; i++) {
            Tray temp = new Tray();
            list.add(temp);
        }
    }

    public void getOn(Tray floor) {
        ArrayList<Person> temp = floor.getList();
        int i;
        int index;
        for (i = 0; i < temp.size(); i++) {
            temp.get(i).getOn();
            index = temp.get(i).getTo();
            list.get(index - 1).add(temp.get(i));
        }
    }

    public void getOff(int index) {
        int i;
        for (i = 0; i < list.get(index - 1).getList().size(); i++) {
            list.get(index - 1).getList().get(i).getOff();
        }
        list.get(index - 1).clear();
    }

    public boolean hasOff(int index) {
        if (list.get(index - 1).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isEmpty() {
        int i;
        for (i = 0; i < 15; i++) {
            if (!list.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int getEup() {
        int i = 14;
        while (list.get(i).isEmpty()) {
            i--;
            if (i == 0) {
                break;
            }
        }
        return i;
    }

    public int getEdown() {
        int i = 0;
        while (list.get(i).isEmpty()) {
            i++;
            if (i == 14) {
                break;
            }
        }
        return i;
    }
}