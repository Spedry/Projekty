package sk.Spedry.Elevators;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class GUI extends Application{
    public static int sirka = 600, vyska = 350;

    Stage window; // celé okno
    Scene win; // vnútro okna
    private static Text txtHall, txtElOne, txtQueue, txtElTwo;

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
        window.setTitle("Building");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label pplInHall = new Label("Počet ludí v hale: ");
        txtHall = new Text("0");
        GridPane.setConstraints(pplInHall, 0, 0);
        GridPane.setConstraints(txtHall, 1, 0);

        Label pplInElevatorOne = new Label("Počet ludí vo výtahu 1: ");
        txtElOne = new Text("0");
        GridPane.setConstraints(pplInElevatorOne, 0, 1);
        GridPane.setConstraints(txtElOne, 1, 1);

        Label pplInQueue = new Label("Počet ludí v queue: ");
        txtQueue = new Text("0");
        GridPane.setConstraints(pplInQueue, 0, 2);
        GridPane.setConstraints(txtQueue, 1, 2);

        Label pplInElevatorTwo = new Label("Počet ludí vo výtahu 2: ");
        txtElTwo = new Text("0");
        GridPane.setConstraints(pplInElevatorTwo, 0, 3);
        GridPane.setConstraints(txtElTwo, 1, 3);

        gridPane.getChildren().addAll(pplInHall, txtHall, pplInElevatorOne, txtElOne, pplInQueue, txtQueue, pplInElevatorTwo, txtElTwo);
        win = new Scene(gridPane, sirka, vyska);

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

        Button button = new Button("Login");
        button.setOnAction(e -> {

        });


        // definowať ktorá scéna sa ma zobraziť vo window
        window.setScene(win);
        // ukáž window
        window.show();
    }
}
