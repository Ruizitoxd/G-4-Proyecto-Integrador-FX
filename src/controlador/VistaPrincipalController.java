package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

//Modelos
import modelo.Proyecto;
import modelo.RolUsuario;
        
public class VistaPrincipalController implements Initializable {
    //ControladorProyectos
    GestionProyecto gestorProyectos = new GestionProyecto();
    GestionApartamento gestorApartamentos = new GestionApartamento();
    
    //Componentes FXML
    @FXML
    private TabPane tabPaneBotones;
    @FXML
    private Tab tabChaux;
    @FXML
    private Tab tabProyectos;
    @FXML
    private Tab tabVentas;
    @FXML
    private Tab tabDashboard;
    @FXML
    private ImageView imgPersona;
    @FXML
    private Label lblInfoUsuario;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Separator separatorUsuario;
    @FXML
    private Label lblRol;
    @FXML
    private Label lblCorreo;
    @FXML
    private AnchorPane anchorPaneBaseChaux;
    @FXML
    private Label lblRolUsuario;
    @FXML
    private Separator separatorRol;
    @FXML
    private Separator separatorCorreo;
    @FXML
    private Label lblInfoEmpresa;
    @FXML
    private Label lblProgreso;
    @FXML
    private Separator separatorInfoEmpresa;
    @FXML
    private Label lblCantidadProyectos;
    @FXML
    private Label lblCantidadApartamentos;
    @FXML
    private Label lblCantidadVentas;
    @FXML
    private Label lblGanancias;
    @FXML
    private Label lblCantidadProyectosNum;
    @FXML
    private Label lblCantidadApartamentosNum;
    @FXML
    private Label lblCantidadVentasNum;
    @FXML
    private Label lblGananciasNum;
    @FXML
    private Label lblTemporalGrafico;
    @FXML
    private Label lblInfoAptos;
    @FXML
    private Separator separatorInfoAptos;
    @FXML
    private Label lblApartamentos;
    @FXML
    private Label lblTorre;
    @FXML
    private Label lblValor;
    @FXML
    private Label lblEstado;
    @FXML
    private Label lblTempralNoDisponible;
    @FXML
    private AnchorPane anchorPaneBaseProyectos;
    @FXML
    private AnchorPane anchorPaneExterior_Chaux;
    @FXML
    private AnchorPane anchorPaneInterior_Chaux;
    @FXML
    private AnchorPane paneUsuario_Chaux;
    @FXML
    private AnchorPane paneInformacion_Chaux;
    @FXML
    private AnchorPane paneVentas_Chaux;
    @FXML
    private AnchorPane anchorPaneExterior_Proyectos;
    @FXML
    private AnchorPane anchorPaneInterior_Proyectos;
    @FXML
    private Label lblProyectos_Proyectos;
    @FXML
    private TableView<Proyecto> tableViewProyectos_Proyectos;
    @FXML
    private TableColumn<Proyecto, Integer> columnId;
    @FXML
    private TableColumn<Proyecto, String> columnNombre;
    @FXML
    private TableColumn<Proyecto, Integer> columnCantTorres;
    @FXML
    private TableColumn<Proyecto, Void> columnAcciones;
    @FXML
    private Button btnCrearProyecto_Proyectos;
    @FXML
    private ImageView imgChauxFondo_Proyectos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setear datos ventana principal
        ActualizarCantidadProyectos();
        ActualizarCantidadApartamentos();
        
        System.out.println("Antes Conexión tabla");
        ArrayList proyectosTabla = gestorProyectos.obtenerProyectosAdmin("1");
        System.out.println("Dsps Conexión tabla");
        ObservableList<Proyecto> proyectos = FXCollections.observableArrayList(proyectosTabla);
        
        //Setear los datos de las columnas de las tablas a los valores correspondientes
        columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnCantTorres.setCellValueFactory(cellData -> cellData.getValue().cantidadTorresProperty().asObject());
        
        tableViewProyectos_Proyectos.setItems(proyectos);
        tableViewProyectos_Proyectos.refresh();
        
        //Agregar botones en la columna de acciones|1
        columnAcciones.setCellFactory(columna -> new TableCell<Proyecto, Void>(){
            private final Button btnEditar = new Button("Editar");
            private final Button btnBorrar = new Button("Borrar");
                        
            {
                //Agregar estilo de CSS a los botones
                btnEditar.getStyleClass().add("buttonTableView");
                btnBorrar.getStyleClass().add("buttonTableView");
                
                btnEditar.setOnAction(event -> {
                    Proyecto proyecto = getTableView().getItems().get(getIndex());
                    // Lógica para editar el proyecto
                });
                
                btnBorrar.setOnAction(event -> {
                    Proyecto proyecto = getTableView().getItems().get(getIndex());
                    //Logica para eliminar el proyecto
                    getTableView().getItems().remove(proyecto);
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean vacio) {
                super.updateItem(item, vacio);
                if (vacio){
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(btnEditar, btnBorrar);
                    buttons.getStyleClass().add("hboxBotones");
                    setGraphic(buttons);
                }
            }
        });      
        
        //Hacer pequeñas correciones de color a las imagenes
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        colorAdjust.setBrightness(-0.1);
        imgChauxFondo_Proyectos.setEffect(colorAdjust);
    }

    @FXML
    private void CrearProyectoNuevo(ActionEvent event) {
        //Logica para crear el proyecto
    }
    
    void ActualizarCantidadProyectos(){
        System.out.println("Proyectos");
        lblCantidadProyectosNum.setText(gestorProyectos.obtenerTotalProyecto() + "");
    }
    void ActualizarCantidadApartamentos(){
        System.out.println("Apartamentos");
        lblCantidadApartamentosNum.setText(gestorApartamentos.obtenerApartamentos() + "");
    }
}