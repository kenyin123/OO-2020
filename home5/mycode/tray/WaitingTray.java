package tray;

import java.util.ArrayList;

public class WaitingTray {
    private ArrayList<Tray> list;

    public WaitingTray() {
        list = new ArrayList<Tray>();
        int i;
        for (i = 1; i <= 15; i++) {
            Tray temp = new Tray();
            list.add(temp);
        }
    }

    public synchronized void add(Person p) {
        while (!this.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                ;
            }
        }
        int index = p.getFrom();
        list.get(index - 1).add(p);
        notifyAll();
    }

    //输入的是-1后的，输出也是-1后的
    public synchronized int init() throws InterruptedException {
        while (this.isEmpty()) {
            try {
                notifyAll();
                wait();
            } catch (InterruptedException e) {
                ;
            }
        }
        int i = 0;
        while (list.get(i).isEmpty()) {
            i++;
        }
        //notifyAll();
        return i;
    }

    public Tray remove(int index) {
        Tray floor = list.get(index - 1).clone();
        list.get(index - 1).clear();
        return floor;
    }

    public boolean hasOn(int index) {
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

    public int getWup() {
        int i = 14;
        while (list.get(i).isEmpty()) {
            i--;
            if (i == 0) {
                break;
            }
        }
        return i;
    }

    public int getWdown() {
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