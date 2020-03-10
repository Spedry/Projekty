package sk.Spedry.Elevators;

public class Queue implements Runnable {
    int peopleInLine, limit;
    boolean exit = false;
    public Queue(int limit) {
        this.limit = limit;
    }

    public void changeVar(int limit) {
        this.limit = limit;
    }

    public void board(int cap) {
        peopleInLine -= cap;
    }

    public void addInLine(int add) {
        peopleInLine += add;
    }

    public int getPeopleInLine() {
        return peopleInLine;
    }

    public int getLimit() {
        return limit;
    }

    public void stop() {
        this.exit = true;
    }

    @Override
    public void run() {
        while(!exit) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (peopleInLine != 0)
            System.out.println("ppl in queue: " + peopleInLine);
        }
        System.out.println("thread queue end");
    }
}