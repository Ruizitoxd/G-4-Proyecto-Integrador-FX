package controlador;

import datos.ValidarUsuario;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.RolUsuario;

public class VistaLoginController implements Initializable {    

    @FXML
    private AnchorPane anchorPane;
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
    @FXML
    private AnchorPane paneLogin;

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

        String correo = txtCorreo.getText();
        String contraseña = txtContraseña.getText();
        if(!"".equals(correo)|| !"".equals(contraseña)){
            RolUsuario RU = new RolUsuario();
            ValidarUsuario Val = new ValidarUsuario();
            RU = Val.validarAdmin(correo, contraseña);
            if(RU.getCorreo()!=null || RU.getIdentificacion()!=null){
                CargarVistaPrincipal(event);
            }else{
                MostrarAlertaError("Correo o contraseña incorrectos.");
            }
        } else {
            MostrarAlertaError("Por favor ingrese datos antes de iniciar sesión.");
        }
    }        
            
            
            //Cargar vista principal
    private void CargarVistaPrincipal(ActionEvent event){
        try {
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
    
    private void MostrarAlertaError(String mensaje){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error de Inicio de Sesión");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}