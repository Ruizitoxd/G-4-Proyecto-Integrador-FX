package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VistaLoginController implements Initializable {    

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane panelLogin;
    @FXML
    private Button btnEntrar;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label lblChaux;
    @FXML
    private Label lblConstructionGroup;
    @FXML
    private Label lblBienvenida;
    @FXML
    private Label lblCorreo;
    @FXML
    private Label lblContraseña;
    @FXML
    private TextField txtCorreo;
    @FXML
    private CheckBox ckBoxMostrarContraseña;
    @FXML
    private PasswordField txtContraseñaOculta;
    @FXML
    private TextField txtContraseña;
    @FXML
    private ImageView imgConstructora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtContraseña.textProperty().bindBidirectional(txtContraseñaOculta.textProperty());
        
         ckBoxMostrarContraseña.selectedProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue) {
               // Mostrar el TextField y ocultar el PasswordField
               txtContraseña.setVisible(true);
               txtContraseña.setManaged(true);
               txtContraseñaOculta.setVisible(false);
               txtContraseñaOculta.setManaged(false);
           } else {
               // Mostrar el PasswordField y ocultar el TextField
               txtContraseñaOculta.setVisible(true);
               txtContraseñaOculta.setManaged(true);
               txtContraseña.setVisible(false);
               txtContraseña.setManaged(false);
           }
       });
    }

    @FXML
    private void IniciarSesion(ActionEvent event) {
        try {
            //Logica para iniciar sesion
            
            
            //Cargar vista principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaPrincipal.fxml"));
            Parent root = loader.load();   
            
            //Crear la escena
            Scene scene = new Scene(root);
            
            //Obtener dimensiones del tamaño de la pantalla
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            
            //Establecer tamaño de ventana
            Stage stage = new Stage();
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
            
            //Mostrar la ventana
            Image icon = new Image(getClass().getResourceAsStream("/recursos/imagenes/Logo Chaux.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Chaux Construction Group");
            stage.initModality(Modality.NONE);
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            
            //Cerar la ventana VistaLogin
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();
        } catch (IOException ex) {
            Logger.getLogger(VistaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}