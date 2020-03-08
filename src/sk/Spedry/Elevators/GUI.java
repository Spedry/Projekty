package sk.Spedry.Elevators;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


public class GUI extends Application{
    public static int sirka = 600, vyska = 350;

    Stage window; // celé okno
    Scene win; // vnútro okna
    private static Text txtHall, txtElOne, txtQueue, txtElTwo;
    private static VBox vBox;

    static ElevatorOne elevatorOne = new ElevatorOne(5,6,50,4,4);

    public static void main(String[] args) {

        Thread thread = new Thread(elevatorOne);
        thread.start();
        // štartuje app
        launch(args);
    }

    public void set() {
        txtHall.setText(Integer.toString(elevatorOne.hall.getPeopleInLine()));
        txtElOne.setText(Integer.toString(elevatorOne.getPeopleIn()));
        txtQueue.setText(Integer.toString(elevatorOne.elevatorTwo.queue.getPeopleInLine()));
        txtElTwo.setText(Integer.toString(elevatorOne.elevatorTwo.getPeopleIn()));
    }

    // telo app
    @Override
    public void start(Stage primaryStage) {
        // definovanie widnow ako primStage
        window = primaryStage;

        txtHall = new Text("0");
        txtElOne = new Text("0");
        txtQueue = new Text("0");
        txtElTwo = new Text("0");

        vBox = new VBox();
        vBox.getChildren().addAll(txtHall, txtElOne, txtQueue, txtElTwo);
        win = new Scene(vBox, sirka, vyska);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        set();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();


        window.setTitle("Building");
        // definowať ktorá scéna sa ma zobraziť vo window
        window.setScene(win);
        // ukáž window
        window.show();
    }
}
