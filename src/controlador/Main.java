package controlador;

import javafx.geometry.Rectangle2D;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaLogin.fxml"));
        Parent root = loader.load();
        //Crear la escena
        Scene scene = new Scene(root);
        //Obtener dimensiones del tamaño de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        //Establecer tamaño de ventana
        primaryStage.setWidth(screenBounds.getWidth() * 0.7);
        primaryStage.setHeight(screenBounds.getHeight() * 0.7);

        //Mostrar la ventana
        Image icon = new Image(getClass().getResourceAsStream("/recursos/imagenes/Logo Chaux.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //Iniciar aplicación
        launch(args);
    }
}
