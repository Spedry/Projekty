package sk.Spedry.Elevators;

public class ElevatorTwo extends ElevatorAbs implements Runnable{

    int peopleIn;
    Queue queue;

    public ElevatorTwo(int cap, int time, int limit) {
        super(cap, time);
        queue = new Queue(limit);
        Thread threadQueue = new Thread(queue);
        threadQueue.start();
    }

    public void boarTheElevator() {
        if (queue.peopleInLine >= cap) {
            peopleIn = cap;
            queue.board(cap);
        } else {
            peopleIn = queue.peopleInLine;
            queue.board(queue.peopleInLine);
        }
    }

    public int getPeopleIn() {
        return peopleIn;
    }

    @Override
    public void run() {
        System.out.println("ElevatorTwo Thread: " + Thread.currentThread().getName());
        while (true) {
            if (queue.peopleInLine == 0) {
                try {
                    System.out.println("elevatorTwo is waiting");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                boarTheElevator();
                System.out.println("ppl in elevatorTwo: " + peopleIn);
                try {
                    Thread.sleep(time * 1000);

                    peopleIn = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

