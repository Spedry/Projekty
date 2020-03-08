package sk.Spedry.Elevators;

public abstract class ElevatorAbs implements Runnable {

    int cap, time;
    boolean working;

    public ElevatorAbs(int cap, int time) {
        this.cap = cap;
        this.time = time;
        this.working = true;
    }

    public void boarTheElevator() {

    }

    @Override
    public void run() {

    }
}
