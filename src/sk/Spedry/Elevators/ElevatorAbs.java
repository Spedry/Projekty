package sk.Spedry.Elevators;

public abstract class ElevatorAbs implements Runnable {

    int cap, time;
    boolean working;
    boolean exit = false;

    public ElevatorAbs(int cap, int time) {
        this.cap = cap;
        this.time = time;
        this.working = true;
    }

    public void changeVar(int cap, int time) {
        this.cap = cap;
        this.time = time;
    }

    public void boarTheElevator() {

    }

    public int getCap() {
        return cap;
    }

    public int getTime() {
        return time;
    }

    public boolean isWorking() {
        return working;
    }

    public void stop() {
        this.exit = true;
    }

    @Override
    public void run() {

    }
}
