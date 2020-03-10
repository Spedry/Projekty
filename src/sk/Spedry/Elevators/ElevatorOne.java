package sk.Spedry.Elevators;

public class ElevatorOne extends ElevatorAbs implements Runnable{

    int peopleIn;
    Hall hall;

    static ElevatorTwo elevatorTwo = new ElevatorTwo(3, 4, 20);

    public ElevatorOne(int cap, int time, int limit, int howMany, int howOften) {
        super(cap, time);
        this.working = true;
        hall = new Hall(limit, howMany, howOften);
        Thread threadHall = new Thread(hall);
        threadHall.start();
    }

    public void changeVar(int cap, int time, int limit, int howMany, int howOften) {
        this.cap = cap;
        this.time = time;
        hall.changeVar(limit, howMany, howOften);
    }

    public void boarTheElevator() {
        if (hall.peopleInLine >= cap) {
            peopleIn = cap;
            hall.board(cap);
        } else {
            peopleIn = hall.peopleInLine;
            hall.board(hall.peopleInLine);
        }

    }

    public int getPeopleIn() {
        return peopleIn;
    }

    @Override
    public void run() {
        Thread thread1 = new Thread(elevatorTwo);
        thread1.start();
        System.out.println("ElevatorOne Thread: " + Thread.currentThread().getName());
        while (!exit) {
            if (hall.peopleInLine == 0) {
                try {
                    System.out.println("elevatorOne is waiting");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                boarTheElevator();
                System.out.println("ppl in elevatorOne: " + peopleIn);
                try {
                    Thread.sleep(time * 1000);
                    elevatorTwo.queue.addInLine(peopleIn);
                    peopleIn = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("thread elevatorOne end");
    }
}
