package sk.Spedry.Elevators;

import java.util.Random;

public class Hall extends Queue implements Runnable {
    int howMany, howOften;

    public Hall(int limit, int howMany, int howOften) {
        super(limit);
        this.howMany = howMany;
        this.howOften = howOften;
    }

    public void changeVar(int limit, int howMany, int howOften) {
        this.limit = limit;
        this.howMany = howMany;
        this.howOften = howOften;
    }

    public int getHowMany() {
        return howMany;
    }

    public int getHowOften() {
        return howOften;
    }

    Random rand = new Random();

    @Override
    public void run() {
        int rnd, left;
        System.out.println("Hall Thread: " + Thread.currentThread().getName());
        while (!exit) {
            if (limit > (peopleInLine + (rnd = rand.nextInt(howMany) + 1))) this.peopleInLine += rnd;
            else {
                left = limit - (peopleInLine + rnd);
                peopleInLine = limit;
                System.out.println(limit + "-" + peopleInLine + "+" + rnd + "=" + left);
                System.out.println("limit bol prekročený");
                //System.out.println("muselo odísť: " + left + " ludí");
            }
            //System.out.println("ppl in hall: " + peopleInLine);
            try {
                Thread.sleep(howOften * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("thread hall end");
    }
}