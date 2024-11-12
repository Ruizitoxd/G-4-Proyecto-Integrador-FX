package Calendario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        CalendarView calendarView = new CalendarView();
        
        StackPane root = new StackPane();
        root.getChildren().add(calendarView);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Calendario de Cuotas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
