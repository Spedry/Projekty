package sk.Spedry.Elevators;

import java.util.Random;

public abstract class ElevatorAbs implements Runnable {

    int cap, time, chance, repair;
    boolean working;
    boolean exit = false;

    Random rand = new Random();

    public ElevatorAbs(int cap, int time, int chance, int repair) {
        this.cap = cap;
        this.time = time;
        this.working = true;
        this.chance = chance;
        this.repair = repair;
    }

    public void changeVar(int cap, int time, int chance, int repair) {
        this.cap = cap;
        this.time = time;
        this.chance = chance;
        this.repair = repair;
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

    public int getChance() {
        return chance;
    }

    public int getRepair() {
        return repair;
    }

    public void stop() {
        this.exit = true;
    }

    @Override
    public void run() {

    }
}
