package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
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
    private GestionTorre gestorTorres = new GestionTorre();
    private GestionApartamento gestorApartamentos = new GestionApartamento();
    private RolUsuario usuario;

    //Atributos de la vista
    ObservableList<Proyecto> proyectos;
    Proyecto proyectoTemporal; //Esta variable se usa como almacenador general del proyecto que se está creando o modificando

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
    private AnchorPane anchorPaneInterior_ProyectosCrear;
    @FXML
    private AnchorPane panelRegistrarApartamento;
    @FXML
    private Label lblRegistrar;
    @FXML
    private Label lblApartamento;
    @FXML
    private AnchorPane panelRegistrarTorre;
    @FXML
    private Label lblRegistrarTorre;
    @FXML
    private Button btnCerrarCrearProyecto;
    @FXML
    private Label lblCorreoUsuario;
    @FXML
    private ImageView imgChauxFondo_Proyectos_Crear;
    @FXML
    private Label lblNombreProyecto_Crear;
    @FXML
    private TextField txtNombreProyecto_Crear;
    @FXML
    private Label lblCantidadDeTorres_Crear;
    @FXML
    private Label lblCantidadDeTorresNum_Crear;
    @FXML
    private TableView<Torre> tableViewTorres_Crear;
    @FXML
    private Button btnGuardarProyecto_Crear;
    @FXML
    private AnchorPane anchorPaneInterior_ProyectosEditar;
    @FXML
    private ImageView imgChauxFondo_Proyectos_Editar;
    @FXML
    private Button btnCerrarEditarProyecto;
    @FXML
    private Label lblNombreProyecto_Editar;
    @FXML
    private TextField txtNombreProyecto_Editar;
    @FXML
    private Label lblCantidadDeTorres_Editar;
    @FXML
    private Label lblCantidadDeTorresNum_Editar;
    @FXML
    private TableView<Torre> tableViewTorres_Editar;
    @FXML
    private AnchorPane panelEditarApartamento;
    @FXML
    private Label lblEditar;
    @FXML
    private Label lblApartamento_Editar;
    @FXML
    private TextField txtNumeroApto_Editar;
    @FXML
    private TextField txtValorApto_Editar;
    @FXML
    private TextField txtMatriculaApto_Editar;
    @FXML
    private TextField txtAreaApto_Editar;
    @FXML
    private ChoiceBox<String> choiceBoxTipoUnidad_Editar;
    @FXML
    private ChoiceBox<String> choiceBoxTorre_Editar;
    @FXML
    private Button btnAñadirProyecto_Editar;
    @FXML
    private AnchorPane panelEditarTorre;
    @FXML
    private Label lblEditarTorre;
    @FXML
    private TextField txtNumeroTorre_Editar;
    @FXML
    private Button btnAñadirTorre_Editar;
    @FXML
    private Button btnGuardarProyecto_Editar;
    @FXML
    private TableColumn<Torre, String> columnNumeroTorre_Crear;
    @FXML
    private TableColumn<Torre, Integer> columnApartamentos_Crear;
    @FXML
    private TableColumn<Torre, String> columnNumeroTorre_Editar;
    @FXML
    private TableColumn<Torre, Void> columnAccionesTorre_Editar;
    @FXML
    private TextField txtNumeroTorre_Crear;
    @FXML
    private TextField txtNumeroApto_Crear;
    @FXML
    private TextField txtValorApto_Crear;
    @FXML
    private TextField txtMatriculaApto_Crear;
    @FXML
    private TextField txtAreaApto_Crear;
    @FXML
    private ChoiceBox<String> choiceBoxTipoUnidad_Crear;
    @FXML
    private ChoiceBox<String> choiceBoxTorre_Crear;
    @FXML
    private Button btnAñadirProyecto_Crear;
    @FXML
    private Button btnAñadirTorre_Crear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setear datos ventana principal
        ActualizarCantidadProyectos();
        ActualizarCantidadApartamentos();

        //Setear los datos de las columnas de la tabla proyecto a los valores correspondientes
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnCantTorres.setCellValueFactory(new PropertyValueFactory<>("cantidadTorres"));

        //Setear los datos de las columnas de la tabla torres a los valores correspondientes
        columnNumeroTorre_Crear.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApartamentos_Crear.setCellValueFactory(new PropertyValueFactory<>("cantidadApartamentos"));
        columnNumeroTorre_Editar.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        //Añadir valores al choiceBox
        ActualizarChoiceBoxVentana();

        //Agregar botones en la columna de acciones|1
        columnAcciones.setCellFactory(columna -> new TableCell<Proyecto, Void>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnBorrar = new Button("Borrar");

            {
                //Agregar estilo de CSS a los botones
                btnEditar.getStyleClass().add("buttonTableView");
                btnBorrar.getStyleClass().add("buttonTableView");

                btnEditar.setOnAction(event -> {
                    //Obtener proyecto
                    Proyecto proyecto = getTableView().getItems().get(getIndex());

                    // Lógica para editar el proyecto
                    AbrirVentanaProyectoEditar(event, proyecto);

                });

                btnBorrar.setOnAction(event -> {
                    //Obtener proyecto
                    Proyecto proyecto = getTableView().getItems().get(getIndex());

                    //Logica para eliminar el proyecto
                    boolean elim = gestorProyectos.borrarProyecto(proyecto.getId());

                    if (elim) {
                        getTableView().getItems().remove(proyecto);
                    } else {
                        MostrarAlertaError("No se pudo eliminar el proyecto correctamente");
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean vacio) {
                super.updateItem(item, vacio);
                if (vacio) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(btnEditar, btnBorrar);
                    buttons.getStyleClass().add("hboxBotones");
                    setGraphic(buttons);
                }
            }
        });
        
        columnAccionesTorre_Editar.setCellFactory(columna -> new TableCell<Torre, Void>(){
            private final Button btnEditarTorre = new Button("Editar");
            private final Button btnBorrarTorre = new Button("Borrar");
            
            {
                //Agregar estilo de CSS a los botones
                btnEditarTorre.getStyleClass().add("buttonTableView");
                btnBorrarTorre.getStyleClass().add("buttonTableView");

                btnEditarTorre.setOnAction(event -> {
                    //Obtener torre
                    Torre torre = getTableView().getItems().get(getIndex());

                    // Lógica para editar la torre
                });

                btnBorrarTorre.setOnAction(event -> {
                    //Obtener torre
                    Torre torre = getTableView().getItems().remove(getIndex());

                    //Logica para eliminar la torre
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean vacio) {
                super.updateItem(item, vacio);
                if (vacio) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(btnEditarTorre, btnBorrarTorre);
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
        imgChauxFondo_Proyectos_Crear.setEffect(colorAdjust);
        imgChauxFondo_Proyectos_Editar.setEffect(colorAdjust);
    }

    //Funcion para obtener el usuario registrado en el login y sus dependencias
    public void SetIdUsuario(RolUsuario usuarioParam) {
        this.usuario = usuarioParam;
        lblNombreUsuario.setText(usuario.getNombre().toUpperCase());
        lblRolUsuario.setText(usuario.getRol());
        lblCorreoUsuario.setText(usuario.getCorreo());

        //Información de los proyectos del administrador
        ActualizarTablaProyectos();
    }

    void ActualizarCantidadProyectos() {
        lblCantidadProyectosNum.setText(gestorProyectos.obtenerTotalProyecto() + "");
    }

    void ActualizarCantidadApartamentos() {
        lblCantidadApartamentosNum.setText(gestorApartamentos.obtenerApartamentos() + "");
    }

    void ActualizarTablaProyectos() {
        ArrayList<Proyecto> proyectosTabla = gestorProyectos.obtenerProyectosAdmin(Integer.parseInt(usuario.getId()));
        this.proyectos = FXCollections.observableArrayList(proyectosTabla);
        tableViewProyectos_Proyectos.setItems(proyectos);
    }

    private void ActualizarChoiceBoxVentana() {
        //Actualizar ChoiceBox ventana Crear
        ObservableList<String> tipoUnidades = FXCollections.observableArrayList(gestorApartamentos.obtenerTipoUnidades());
        choiceBoxTipoUnidad_Crear.getItems().add("Tipo unidad");
        choiceBoxTipoUnidad_Crear.setItems(tipoUnidades);
        choiceBoxTorre_Crear.getItems().add("Torre");
        
        //Actualizar ChoiceBox ventana Crear
        choiceBoxTipoUnidad_Editar.getItems().add("Tipo unidad");
        choiceBoxTipoUnidad_Editar.setItems(tipoUnidades);
        choiceBoxTorre_Editar.getItems().add("Torre");
    }
    
    private void MostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    //Abrir la ventana para crear un proyecto
    @FXML
    private void AbrirVentanaProyectoNuevo(ActionEvent event) {
        anchorPaneInterior_ProyectosCrear.setVisible(true);
        txtNombreProyecto_Crear.setText("");
        txtNumeroTorre_Crear.setText("");
        txtNumeroApto_Crear.setText("");
        txtValorApto_Crear.setText("");
        txtMatriculaApto_Crear.setText("");
        txtAreaApto_Crear.setText("");
        choiceBoxTipoUnidad_Crear.setValue("Tipo unidad");
        choiceBoxTorre_Crear.setValue("Torre");
        proyectoTemporal = new Proyecto();
    }

    @FXML
    private void CerrarVentanaProyectoNuevo(ActionEvent event) {
        anchorPaneInterior_ProyectosCrear.setVisible(false);
    }
    
    private void AbrirVentanaProyectoEditar(ActionEvent event, Proyecto proyecto) {
        //Informacion de la vista
        anchorPaneInterior_ProyectosEditar.setVisible(true);
        txtNombreProyecto_Editar.setText(proyecto.getNombre());
        txtNumeroTorre_Editar.setText("");
        txtNumeroApto_Editar.setText("");
        txtValorApto_Editar.setText("");
        txtMatriculaApto_Editar.setText("");
        txtAreaApto_Editar.setText("");
        choiceBoxTipoUnidad_Editar.setValue("Tipo unidad");
        choiceBoxTorre_Editar.setValue("Torre");
        
        //Generación del proyecto a editar
        proyectoTemporal = proyecto;
        proyectoTemporal.modificarTorres(gestorTorres.obtenerTorre(proyectoTemporal.getId()));
        
        //Obtener lista de torres del proyecto
        ObservableList<Torre> torresTabla = FXCollections.observableArrayList(proyectoTemporal.obtenerTorres());
        tableViewTorres_Editar.setItems(torresTabla);
    }

    @FXML
    private void CerrarVentanarProyectoEditar(ActionEvent event) {
        anchorPaneInterior_ProyectosEditar.setVisible(false);
    }

    @FXML
    private void GuardarProyecto(ActionEvent event) {
        proyectoTemporal.setNombre(txtNombreProyecto_Crear.getText());
        proyectos.add(proyectoTemporal);

        boolean agregado = gestorProyectos.guardarProyecto(proyectoTemporal, Integer.parseInt(usuario.getId()));
        if (agregado) {
            ActualizarTablaProyectos();
            CerrarVentanaProyectoNuevo(event);
        } else {
            MostrarAlertaError("No se ha podido agregar el proyecto correctamente");
        }
    }

    @FXML
    private void AñadirTorre(ActionEvent event) {
        Torre torreNueva = new Torre();
        torreNueva.setNombre(txtNumeroTorre_Crear.getText());

        proyectoTemporal.añadirTorre(torreNueva);
        lblCantidadDeTorresNum_Crear.setText(proyectoTemporal.getCantidadTorres() + "");
        choiceBoxTorre_Crear.getItems().add(torreNueva.getNombre());

        ObservableList<Torre> torresTabla = FXCollections.observableArrayList(proyectoTemporal.obtenerTorres());
        tableViewTorres_Crear.setItems(torresTabla);
    }

    @FXML
    private void AñadirApartamento(ActionEvent event) {
        Apartamento apartamentoNuevo = new Apartamento();
        apartamentoNuevo.setNumero(txtNumeroApto_Crear.getText());
        apartamentoNuevo.setValor(Integer.parseInt(txtValorApto_Crear.getText()));
        apartamentoNuevo.setMatricula(txtMatriculaApto_Crear.getText());
        apartamentoNuevo.setArea(txtAreaApto_Crear.getText());
        apartamentoNuevo.setTipoUnidad(choiceBoxTipoUnidad_Crear.getValue() + "");
        apartamentoNuevo.setIdTorre(choiceBoxTorre_Crear.getValue() + "");
    }

    @FXML
    private void GuardarApartamento(ActionEvent event) {
    }

    @FXML
    private void GuardarTorre(ActionEvent event) {
    }
}
