package sk.Spedry.Elevators;

public class testmain {

    public static void main(String[] args) {
        ElevatorOne elevatorOne = new ElevatorOne(5,6,50,4,4);
        Thread thread = new Thread(elevatorOne);
        thread.start();
    }
}
