package sk.Spedry.Elevators;

public class ElevatorTwo extends ElevatorAbs implements Runnable{

    int peopleIn;
    Queue queue;

    public ElevatorTwo(int cap, int time, int chance, int repair, int limit) {
        super(cap, time, chance, repair);
        queue = new Queue(limit);
        Thread threadQueue = new Thread(queue);
        threadQueue.start();
    }

    public void changeVar(int cap, int time, int chance, int repair, int limit) {
        this.cap = cap;
        this.time = time;
        this.chance = chance;
        this.repair = repair;
        queue.changeVar(limit);
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

    public String getPeopleIn() {
        if (working)
            return String.valueOf(peopleIn);
        else
            return "nefunguje";
    }

    @Override
    public void run() {
        System.out.println("ElevatorTwo Thread: " + Thread.currentThread().getName());
        while (!exit) {
            if (queue.peopleInLine == 0) {
                try {
                    //System.out.println("elevatorTwo is waiting");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                boarTheElevator();
                //System.out.println("ppl in elevatorTwo: " + peopleIn);
                try {
                    Thread.sleep(time * 1000);
                    peopleIn = 0;
                    if (rand.nextInt(99) + 1 < chance) {
                        working = false;
                        //System.out.println("výťah č 2 sa pokazil");
                        Thread.sleep(repair * 1000);
                        working = true;
                        //System.out.println("výťah č 2 sa opravil");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("thread elevatorTwo end");
    }
}

