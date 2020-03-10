package sk.Spedry.Elevators;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GUI extends Application{
    public static int sirka = 590, vyska = 655;

    Stage window; // celé okno
    Scene win; // vnútro okna
    final int WINDOW_SIZE = 10;
    private static Text txtHall, txtElOne, txtQueue, txtElTwo;

    static ElevatorOne elevatorOne = new ElevatorOne(5,6, 15, 11,50,4,4);

    static Thread thread = new Thread(elevatorOne);

    public static void main(String[] args) {


        thread.start();
        // štartuje app
        launch(args);
    }

    public void set() {
        txtHall.setText(Integer.toString(elevatorOne.hall.getPeopleInLine()));
        txtElOne.setText(elevatorOne.getPeopleIn());
        txtQueue.setText(Integer.toString(ElevatorOne.elevatorTwo.queue.getPeopleInLine()));
        txtElTwo.setText(ElevatorOne.elevatorTwo.getPeopleIn());
    }

    // telo app
    @Override
    public void start(Stage primaryStage) {
        // definovanie widnow ako primStage
        window = primaryStage;
        window.setTitle("Building");

        GridPane gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(2);

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
        GridPane.setConstraints(pplInQueue, 2, 0);
        GridPane.setConstraints(txtQueue, 3, 0);

        Label pplInElevatorTwo = new Label("Počet ludí vo výtahu 2: ");
        txtElTwo = new Text("0");
        GridPane.setConstraints(pplInElevatorTwo, 2, 1);
        GridPane.setConstraints(txtElTwo, 3, 1);


        gridPane.getChildren().addAll(pplInHall, txtHall, pplInElevatorOne, txtElOne, pplInQueue, txtQueue, pplInElevatorTwo, txtElTwo);

        Thread thread = new Thread(() -> {
            Runnable updater = () -> set();

            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }

                Platform.runLater(updater);
            }
        });
        thread.setDaemon(true);
        thread.start();

        Label labelHowMany = new Label("Max prichádzajúcich ľudí: ");
        TextField textFieldHowMany = new TextField(Integer.toString(elevatorOne.hall.getHowMany()));
        GridPane.setConstraints(labelHowMany, 0, 5);
        GridPane.setConstraints(textFieldHowMany, 1, 5);

        Label labelHowOften = new Label("Ako často budú prichádzať: ");
        TextField textFieldHowOften = new TextField(Integer.toString(elevatorOne.hall.getHowOften()));
        GridPane.setConstraints(labelHowOften, 0, 6);
        GridPane.setConstraints(textFieldHowOften, 1, 6);

        Label labelHallLimit = new Label("Limit haly: ");
        TextField textFieldHallLimit = new TextField(Integer.toString(elevatorOne.hall.getLimit()));
        GridPane.setConstraints(labelHallLimit, 0, 7);
        GridPane.setConstraints(textFieldHallLimit, 1, 7);

        Label labelCapOne = new Label("Max ludí v E. 1: ");
        TextField textFieldCapOne = new TextField(Integer.toString(elevatorOne.getCap()));
        GridPane.setConstraints(labelCapOne, 2, 5);
        GridPane.setConstraints(textFieldCapOne, 3, 5);

        Label labelRychlostOne = new Label("Rýchlosť E. 1: ");
        TextField textFieldRychlostsOne = new TextField(Integer.toString(elevatorOne.getTime()));
        GridPane.setConstraints(labelRychlostOne, 2, 6);
        GridPane.setConstraints(textFieldRychlostsOne, 3, 6);

        Label labelQueueLimit = new Label("Limit queue: ");
        TextField textFielQueueLimit = new TextField(Integer.toString(ElevatorOne.elevatorTwo.queue.getLimit()));
        GridPane.setConstraints(labelQueueLimit, 0, 8);
        GridPane.setConstraints(textFielQueueLimit, 1, 8);

        Label labelCapTwo = new Label("Max ludí v E. 2: ");
        TextField textFieldCapTwo = new TextField(Integer.toString(ElevatorOne.elevatorTwo.getCap()));
        GridPane.setConstraints(labelCapTwo, 2, 7);
        GridPane.setConstraints(textFieldCapTwo, 3, 7);

        Label labelRychlostTwo = new Label("Rýchlosť E. 2: ");
        TextField textFieldRychlostsTwo = new TextField(Integer.toString(ElevatorOne.elevatorTwo.getTime()));
        GridPane.setConstraints(labelRychlostTwo, 2, 8);
        GridPane.setConstraints(textFieldRychlostsTwo, 3, 8);

        Label labelChanceOne = new Label("Šanca na pokazenie E. 1: ");
        TextField textFieldChanceOne = new TextField(Integer.toString(elevatorOne.getChance()));
        GridPane.setConstraints(labelChanceOne, 0, 9);
        GridPane.setConstraints(textFieldChanceOne, 1, 9);

        Label labelChanceTwo = new Label("Šanca na pokazenie E. 2: ");
        TextField textFieldChanceTwo = new TextField(Integer.toString(ElevatorOne.elevatorTwo.getChance()));
        GridPane.setConstraints(labelChanceTwo, 2, 9);
        GridPane.setConstraints(textFieldChanceTwo, 3, 9);

        Label labelRepairOne = new Label("Rýchlosť opravi E. 2: ");
        TextField textFieldRepairOne = new TextField(Integer.toString(elevatorOne.getRepair()));
        GridPane.setConstraints(labelRepairOne, 0, 10);
        GridPane.setConstraints(textFieldRepairOne, 1, 10);

        Label labelRepairTwo = new Label("Rýchlosť opravi E. 2: ");
        TextField textFieldRepairTwo = new TextField(Integer.toString(ElevatorOne.elevatorTwo.getRepair()));
        GridPane.setConstraints(labelRepairTwo, 2, 10);
        GridPane.setConstraints(textFieldRepairTwo, 3, 10);

        gridPane.getChildren().addAll(labelHowMany, textFieldHowMany, labelHowOften, textFieldHowOften, labelHallLimit, textFieldHallLimit,
                labelCapOne, textFieldCapOne, labelRychlostOne, textFieldRychlostsOne, labelQueueLimit, textFielQueueLimit, labelCapTwo, textFieldCapTwo,
                labelRychlostTwo, textFieldRychlostsTwo, labelChanceOne, textFieldChanceOne, labelChanceTwo, textFieldChanceTwo, labelRepairOne, textFieldRepairOne,
                labelRepairTwo, textFieldRepairTwo);

        Button button = new Button("Set variables");
        button.setOnAction(e -> {
            elevatorOne.changeVar(Integer.parseInt(textFieldCapOne.getText()),
                    Integer.parseInt(textFieldRychlostsOne.getText()),
                    Integer.parseInt(textFieldChanceOne.getText()),
                    Integer.parseInt(textFieldRepairOne.getText()),
                    Integer.parseInt(textFieldHallLimit.getText()),
                    Integer.parseInt(textFieldHowMany.getText()),
                    Integer.parseInt(textFieldHowOften.getText()));
            ElevatorOne.elevatorTwo.changeVar(Integer.parseInt(textFieldCapTwo.getText()),
                    Integer.parseInt(textFieldRychlostsTwo.getText()),
                    Integer.parseInt(textFieldChanceTwo.getText()),
                    Integer.parseInt(textFieldRepairTwo.getText()),
                    Integer.parseInt(textFielQueueLimit.getText()));
        });
        button.setMaxWidth(Double.MAX_VALUE);
        GridPane.setConstraints(button,  0, 11, 11, 4);
        gridPane.getChildren().add(button);

        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/s");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("People");
        yAxis.setAnimated(false); // axis animations are removed

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Elevators");
        lineChart.setAnimated(false); // disable animations

        XYChart.Series<String, Number> hall = new XYChart.Series<>();
        hall.setName("Hall");
        XYChart.Series<String, Number> elOne = new XYChart.Series<>();
        elOne.setName("Elevator 1");
        XYChart.Series<String, Number> queue = new XYChart.Series<>();
        queue.setName("Queue");
        XYChart.Series<String, Number> elTwo = new XYChart.Series<>();
        elTwo.setName("Elevator 2");

        lineChart.getData().addAll(hall, elOne, queue, elTwo);

        GridPane.setConstraints(lineChart, 0, 15, 15, 4);
        gridPane.getChildren().add(lineChart);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Platform.runLater(() -> {

                Date now = new Date();

                hall.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), elevatorOne.hall.getPeopleInLine()));
                elOne.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), working(elevatorOne.getPeopleIn())));
                queue.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), ElevatorOne.elevatorTwo.queue.getPeopleInLine()));
                elTwo.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), working(ElevatorOne.elevatorTwo.getPeopleIn())));

                if (hall.getData().size() > WINDOW_SIZE)
                    hall.getData().remove(0);
                if (elOne.getData().size() > WINDOW_SIZE)
                    elOne.getData().remove(0);
                if (queue.getData().size() > WINDOW_SIZE)
                    queue.getData().remove(0);
                if (elTwo.getData().size() > WINDOW_SIZE)
                    elTwo.getData().remove(0);
            });
        }, 0, 950, TimeUnit.MILLISECONDS);

        win = new Scene(gridPane, sirka, vyska);

        window.setOnCloseRequest(e -> closeProgram());
        // definowať ktorá scéna sa ma zobraziť vo window
        window.setScene(win);
        // ukáž window
        window.show();
    }

    private void closeProgram() {
        ElevatorOne.elevatorTwo.queue.stop();
        ElevatorOne.elevatorTwo.stop();
        elevatorOne.hall.stop();
        elevatorOne.stop();
        Platform.exit();
        System.exit(0);

    }

    private int working(String pplIn) {
        if (pplIn.equals("nefunguje"))
            return 0;
        else return Integer.parseInt(pplIn);
    }
}
