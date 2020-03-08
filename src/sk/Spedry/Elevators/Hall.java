package sk.Spedry.Elevators;

import java.util.Random;

public class Hall extends Queue implements Runnable {
    int howMany, howOften;

    public Hall(int limit, int howMany, int howOften) {
        super(limit);
        this.howMany = howMany;
        this.howOften = howOften;
    }

    Random rand = new Random();

    @Override
    public void run() {
        System.out.println("Hall Thread: " + Thread.currentThread().getName());
        while (peopleInLine < limit) {
            this.peopleInLine += rand.nextInt(howMany) + 1;
            System.out.println("ppl in hall: " + peopleInLine);
            try {
                Thread.sleep(howOften * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Limit bol prekročený");
    }


}