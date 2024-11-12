package Calendario;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarView extends GridPane {

    private Map<String, List<Cuota>> cuotasData = new HashMap<>();

    public CalendarView() {
        // Configuración inicial de la vista del calendario
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);

        // Ejemplo de datos de cuotas
        cuotasData.put("2024-10-30", List.of(
                new Cuota("Juan Pérez", 500, "2024-11-05", "Próxima a Vencer"),
                new Cuota("Luis Martínez", 400, "2024-10-20", "Vencida")
        ));
        cuotasData.put("2024-11-05", List.of(
                new Cuota("Ana Gómez", 700, "2024-11-05", "Próxima a Vencer")
        ));
        cuotasData.put("2024-10-15", List.of(
                new Cuota("María López", 350, "2024-10-15", "Vencida")
        ));

        // Crear los días del calendario
        for (int day = 1; day <= 30; day++) {
            String dateKey = "2024-10-" + (day < 10 ? "0" + day : day);
            StackPane dayPane = createDayPane(day, dateKey);
            this.add(dayPane, (day - 1) % 7, (day - 1) / 7);
        }
    }

    private StackPane createDayPane(int day, String dateKey) {
        StackPane dayPane = new StackPane();
        Label dayLabel = new Label(String.valueOf(day));
        dayLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        dayPane.getChildren().add(dayLabel);
        dayPane.setPrefSize(50, 50);
        dayPane.setStyle("-fx-background-color: #e0e0e0; -fx-border-radius: 5; -fx-background-radius: 5;");
        
        if (cuotasData.containsKey(dateKey)) {
            Circle badge = new Circle(5);
            badge.setFill(cuotasData.get(dateKey).stream().anyMatch(c -> c.getEstado().equals("Vencida")) ? Color.RED : Color.ORANGE);
            dayPane.getChildren().add(badge);
            StackPane.setAlignment(badge, Pos.TOP_RIGHT);
            
            dayPane.setOnMouseClicked(event -> showDetails(dateKey));
        }

        return dayPane;
    }

    private void showDetails(String dateKey) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Detalles de Cuotas");
        alert.setHeaderText("Cuotas para la fecha: " + dateKey);
        
        StringBuilder content = new StringBuilder();
        for (Cuota cuota : cuotasData.get(dateKey)) {
            content.append("Cliente: ").append(cuota.getCliente()).append("\n")
                   .append("Monto: $").append(cuota.getMonto()).append("\n")
                   .append("Fecha de Vencimiento: ").append(cuota.getFechaVencimiento()).append("\n")
                   .append("Estado: ").append(cuota.getEstado()).append("\n\n");
        }
        
        alert.setContentText(content.toString());
        alert.showAndWait();
    }
}
