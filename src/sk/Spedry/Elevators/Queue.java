package sk.Spedry.Elevators;

public class Queue implements Runnable {
    int peopleInLine, limit;

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

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (peopleInLine != 0)
            System.out.println("ppl in queue: " + peopleInLine);
        }
    }
}