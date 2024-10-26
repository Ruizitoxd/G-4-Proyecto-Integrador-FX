package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import modelo.Apartamento;

//Modelos
import modelo.Proyecto;
import modelo.RolUsuario;
import modelo.Torre;
        
public class VistaPrincipalController implements Initializable {
    //Controladores
    private GestionProyecto gestorProyectos = new GestionProyecto();
    private GestionApartamento gestorApartamentos = new GestionApartamento();
    private RolUsuario usuario;
    
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
    @FXML
    private ImageView imgChauxFondo_Proyectos1;
    @FXML
    private AnchorPane anchorPaneInterior_ProyectosCrear;
    @FXML
    private AnchorPane panelRegistrarApartamento;
    @FXML
    private TextField txtNumeroApto;
    @FXML
    private Button btnAñadirProyecto;
    @FXML
    private TextField txtValorApto;
    @FXML
    private TextField txtMatriculaApto;
    @FXML
    private TextField txtAreaApto;
    @FXML
    private ChoiceBox<String> choiceBoxTipoUnidad;
    @FXML
    private Label lblRegistrar;
    @FXML
    private Label lblApartamento;
    @FXML
    private ChoiceBox<?> choiceBoxTorre;
    @FXML
    private AnchorPane panelRegistrarTorre;
    @FXML
    private Label lblRegistrarTorre;
    @FXML
    private TextField txtNumeroTorre;
    @FXML
    private Button btnAñadirTorre;
    @FXML
    private Button btnCerrarCrearProyecto;
    @FXML
    private TableView<?> tableViewTorres;
    @FXML
    private TableColumn<?, ?> columnNumeroTorre;
    @FXML
    private TableColumn<?, ?> columnApartamentos;
    @FXML
    private Label lblNombreProyecto;
    @FXML
    private TextField txtNombreProyecto;
    @FXML
    private Label lblCantidadDeTorres;
    @FXML
    private Button btnGuardarProyecto;
    @FXML
    private Label lblCantidadDeTorresNum;

    public void setIdUsuario(RolUsuario usuario){
        this.usuario = usuario;
        System.out.println("Usuario: "+usuario.getId());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setear datos ventana principal
        ActualizarCantidadProyectos();
        ActualizarCantidadApartamentos();
        
        ArrayList<Proyecto> proyectosTabla = gestorProyectos.obtenerProyectosAdmin("3");
        ObservableList<Proyecto> proyectos = FXCollections.observableArrayList(proyectosTabla);
        System.out.println(proyectos);
                
        //Setear los datos de las columnas de las tablas a los valores correspondientes
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnCantTorres.setCellValueFactory(new PropertyValueFactory<>("cantidadTorres"));
        
        tableViewProyectos_Proyectos.setItems(proyectos);    
        
        //Añadir valores al choiceBox
        ObservableList<String> tipoUnidades = FXCollections.observableArrayList(gestorApartamentos.obtenerTipoUnidades());
        choiceBoxTipoUnidad.setItems(tipoUnidades);
        
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

                    boolean elim = gestorProyectos.borrarProyecto(proyecto.getId());

                    if(elim){
                        getTableView().getItems().remove(proyecto);
                    }else{
                        MostrarAlertaError("No se pudo eliminar el proyecto correctamente");
                    }
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
        imgChauxFondo_Proyectos1.setEffect(colorAdjust);
    }

    void ActualizarCantidadProyectos(){
        lblCantidadProyectosNum.setText(gestorProyectos.obtenerTotalProyecto() + "");
    }
    void ActualizarCantidadApartamentos(){
        lblCantidadApartamentosNum.setText(gestorApartamentos.obtenerApartamentos() + "");
    }

    @FXML
    private void AbrirVentanaProyectoNuevo(ActionEvent event) {
        anchorPaneInterior_ProyectosCrear.setVisible(true);
    }
    
    @FXML
    private void CerrarVentanarProyectoNuevo(ActionEvent event) {
        anchorPaneInterior_ProyectosCrear.setVisible(false);
    }

    @FXML
    private void GuardarProyecto(ActionEvent event) {
        Proyecto proyectoNuevo = new Proyecto();
        proyectoNuevo.setNombre(txtNombreProyecto.getText());
    }

    @FXML
    private void AñadirTorre(ActionEvent event) {
        Torre torreNueva = new Torre();
        torreNueva.setNombre(txtNumeroTorre.getText());
    }

    @FXML
    private void AñadirApartamento(ActionEvent event) {
        Apartamento apartamentoNuevo = new Apartamento();
        apartamentoNuevo.setNumero(txtNumeroApto.getText());
        apartamentoNuevo.setValor(Integer.parseInt(txtValorApto.getText()));
        apartamentoNuevo.setMatricula(txtMatriculaApto.getText());
        apartamentoNuevo.setArea(txtAreaApto.getText());
        apartamentoNuevo.setTipoUnidad(choiceBoxTipoUnidad.getValue() + "");
        apartamentoNuevo.setIdTorre(choiceBoxTorre.getValue() + "");  
    }
    
    private void MostrarAlertaError(String mensaje){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error de Inicio de Sesión");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}