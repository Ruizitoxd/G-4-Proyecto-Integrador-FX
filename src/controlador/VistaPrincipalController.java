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
    private GestionTorre gestorTorres = new GestionTorre();
    private GestionApartamento gestorApartamentos = new GestionApartamento();
    private RolUsuario usuario;

    //Atributos de la vista
    ObservableList<Proyecto> proyectos;
    ObservableList<Torre> torres;
    ObservableList<Apartamento> apartamentos;
    Proyecto proyectoTemporal; //Esta variable se usa como almacenador general del proyecto que se está creando o modificando
    Torre torreTemporal;
    Apartamento apartamentoTemporal;

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
    @FXML
    private TableView<?> tableViewApartamentos_Crear;
    @FXML
    private TableColumn<?, ?> columnIdApartamento_Crear;
    @FXML
    private TableColumn<?, ?> columnNumeroApartamento_Crear;
    @FXML
    private TableColumn<?, ?> columnValorApartamento_Crear;
    @FXML
    private TableColumn<?, ?> columnAreaApartamento_Crear;
    @FXML
    private TableColumn<?, ?> columnMatriculaApartamento_Crear;
    @FXML
    private TableColumn<?, ?> columnFechaEscrituraApartamento_Crear;
    @FXML
    private TableColumn<?, ?> columnTipoUnidadApartamento_Crear;
    @FXML
    private TableColumn<?, ?> columnIdTorreApartamento_Crear;
    @FXML
    private Button btnVisualizarApartamentos_Crear;
    @FXML
    private Button btnVisualizarTorres_Crear;
    @FXML
    private AnchorPane anchorPaneInterior_Ventas;
    @FXML
    private Label lblVentas;
    @FXML
    private TableColumn<?, ?> columnIdVentas;
    @FXML
    private AnchorPane anchorPaneInterior_VentasCrear;
    @FXML
    private Button btnAgregarVenta_Ventas;
    @FXML
    private TableColumn<?, ?> columnValorVentas;
    @FXML
    private TableColumn<?, ?> columnNumeroCuotasVentas;
    @FXML
    private TableColumn<?, ?> columnInteresVentas;
    @FXML
    private TableColumn<?, ?> columnIdApartamentoVentas;
    @FXML
    private TableColumn<?, ?> columnAccionesVentas;
    @FXML
    private Button btnCerrarAgregarVentas;
    @FXML
    private AnchorPane anchorPaneExterior_Ventas;
    @FXML
    private ImageView imgChauxFondo_Ventas;
    @FXML
    private TableView<?> tableViewVentas_Ventas;
    @FXML
    private ImageView imgChauxFondo_VentasCrear;
    @FXML
    private TableView<?> tableViewApartamentosDisponibles_VentasCrear;
    @FXML
    private TableColumn<?, ?> columnApartamento_VentasCrear;
    @FXML
    private TableColumn<?, ?> columnTorre_VentasCrear;
    @FXML
    private TableColumn<?, ?> columnProyecto_VentasCrear;
    @FXML
    private AnchorPane panelDatosdelCliente_VentasCrear;
    @FXML
    private Label lblDatosdel_VentasCrear;
    @FXML
    private Label lblCliente_VentasCrear;
    @FXML
    private TextField txtNombreCliente_VentasCrear;
    @FXML
    private TextField txtApellidoCliente_VentasCrear;
    @FXML
    private TextField txtDireccionCliente_VentasCrear;
    @FXML
    private ChoiceBox<?> choiceBoxSISBENCliente_VentasCrear;
    @FXML
    private TextField txtCedulaCliente_VentasCrear;
    @FXML
    private TextField txtSubsidioCliente_VentasCrear;
    @FXML
    private TextField txtCorreoelectronicoCliente_VentasCrear;
    @FXML
    private TextField txtTelefonoCliente_VentasCrear;
    @FXML
    private AnchorPane panelDatosdeVenta_VentasCrear;
    @FXML
    private Label lblDatosdeVenta_VentasCrear;
    @FXML
    private TextField txtCuotasDatosVenta_crear;
    @FXML
    private TextField txtInteresesDatosdeVenta_crear;
    @FXML
    private Button btnRealizarVenta_VentasCrear;
    @FXML
    private Label lblSeleccioneUnApartamento_Ventas;

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
                    proyectoTemporal = getTableView().getItems().get(getIndex());

                    // Lógica para editar el proyecto
                    AbrirVentanaProyectoEditar(event);

                    txtNombreProyecto_Editar.setText(proyectoTemporal.getNombre());
                });

                btnBorrar.setOnAction(event -> {
                    //Obtener proyecto
                    Proyecto proyecto = getTableView().getItems().get(getIndex());

                    //Logica para eliminar el proyecto
                    boolean elimP = gestorProyectos.BorrarProyecto(proyecto.getId());

                    if (elimP) {
                        getTableView().getItems().remove(proyecto);
                    } else {
                        MostrarAlertaError("Error no se pudo eliminar el proyecto correctamente");
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

        columnAccionesTorre_Editar.setCellFactory(columna -> new TableCell<Torre, Void>() {
            private final Button btnEditarTorre = new Button("Editar");
            private final Button btnBorrarTorre = new Button("Borrar");

            {
                //Agregar estilo de CSS a los botones
                btnEditarTorre.getStyleClass().add("buttonTableView");
                btnBorrarTorre.getStyleClass().add("buttonTableView");

                btnEditarTorre.setOnAction(event -> {
                    //Obtener torre
                    torreTemporal = getTableView().getItems().get(getIndex());

                    // Lógica para editar la torre
                    txtNumeroTorre_Editar.setText(torreTemporal.getNombre());

                });

                btnBorrarTorre.setOnAction(event -> {
                    //Obtener torre
                    Torre torre = getTableView().getItems().remove(getIndex());

                    //Logica para eliminar la torre
                    boolean elim = gestorTorres.BorrarTorre(torre.getId());
                    if (elim) {
                        getTableView().getItems().remove(torre);
                    } else {
                        MostrarAlertaError("no se pudo eliminar correctamente la torre");

                    }
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

    //Actualizaciones de información del programa
    void ActualizarCantidadProyectos() {
        lblCantidadProyectosNum.setText(gestorProyectos.ObtenerTotalProyecto() + "");
    }

    void ActualizarCantidadApartamentos() {
        lblCantidadApartamentosNum.setText(gestorApartamentos.ObtenerApartamentos() + "");
    }

    void ActualizarTablaProyectos() {
        ArrayList<Proyecto> proyectosTabla = gestorProyectos.ObtenerProyectosAdmin(Integer.parseInt(usuario.getId()));
        this.proyectos = FXCollections.observableArrayList(proyectosTabla);
        tableViewProyectos_Proyectos.setItems(proyectos);
    }

    //actualizar tabla torre
    void ActualizarTablaTorres() {
        ArrayList<Torre> torresTabla = gestorTorres.ObtenerTorre(proyectoTemporal.getId());
        proyectoTemporal.modificarTorres(torresTabla);
        this.torres = FXCollections.observableArrayList(torresTabla);
        tableViewTorres_Editar.setItems(torres);
    }

    private void ActualizarChoiceBoxVentana() {
        //Actualizar ChoiceBox ventana Crear
        ObservableList<String> tipoUnidades = FXCollections.observableArrayList(gestorApartamentos.obtenerTipoUnidades());
        choiceBoxTipoUnidad_Crear.getItems().add("Tipo unidad");
        choiceBoxTipoUnidad_Crear.setItems(tipoUnidades);
        choiceBoxTorre_Crear.getItems().add("Torre");

        //Actualizar ChoiceBox ventana Editar
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

    public void MostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //Abrir la ventana para crear un proyecto
    @FXML
    private void AbrirVentanaProyectoNuevo(ActionEvent event) {
        //Abrir la ventana
        anchorPaneInterior_ProyectosCrear.setVisible(true);

        //Reiniciar componentes proyecto
        txtNombreProyecto_Crear.setText("");

        //Reiniciar componentes torre
        txtNumeroTorre_Crear.setText("");
        lblCantidadDeTorresNum_Crear.setText("0");
        tableViewTorres_Crear.setItems(FXCollections.observableArrayList());

        //Reiniciar componentes apartamento
        txtNumeroApto_Crear.setText("");
        txtValorApto_Crear.setText("");
        txtMatriculaApto_Crear.setText("");
        txtAreaApto_Crear.setText("");
        choiceBoxTipoUnidad_Crear.setValue("Tipo unidad");
        choiceBoxTorre_Crear.setValue("Torre");

        //Crear proyecto vacío a guardar
        proyectoTemporal = new Proyecto();
    }

    @FXML
    private void CerrarVentanaProyectoNuevo(ActionEvent event) {
        anchorPaneInterior_ProyectosCrear.setVisible(false);
    }

    //Abrir la ventana para editar un proyecto
    private void AbrirVentanaProyectoEditar(ActionEvent event) {
        //Abrir la ventana
        anchorPaneInterior_ProyectosEditar.setVisible(true);

        //Reiniciar componentes proyecto
        txtNombreProyecto_Editar.setText(proyectoTemporal.getNombre());

        //Reinicar compoentes torre
        txtNumeroTorre_Editar.setText("");
        proyectoTemporal.modificarTorres(gestorTorres.ObtenerTorre(proyectoTemporal.getId())); //Obtener la lista de las torres que tiene el proyecto
        ObservableList<Torre> torresTabla = FXCollections.observableArrayList(proyectoTemporal.obtenerTorres()); //Obtener las torres del proyecto
        tableViewTorres_Editar.setItems(torresTabla); //Setear proyectos

        //Reiniciar componentes aprtamento
        txtNumeroApto_Editar.setText("");
        txtValorApto_Editar.setText("");
        txtMatriculaApto_Editar.setText("");
        txtAreaApto_Editar.setText("");
        choiceBoxTipoUnidad_Editar.setValue("Tipo unidad");
        choiceBoxTorre_Editar.setValue("Torre");
    }

    @FXML
    private void CerrarVentanarProyectoEditar(ActionEvent event) {
        anchorPaneInterior_ProyectosEditar.setVisible(false);
    }

    //logica para añadir torre
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

    //Guardar la torre editada en la base de datos
    @FXML
    private void GuardarTorre(ActionEvent event) {
        torreTemporal.setNombre(txtNumeroTorre_Editar.getText());

        //Actualizar torre a la base de datos
        boolean editT = gestorTorres.ActualizarTorre(torreTemporal.getId(), torreTemporal.getNombre());

        if (editT) {
            ActualizarTablaTorres();

        } else {
            MostrarAlertaError("Error no se pudo editar la torre correctamente");
        }
    }

    @FXML
    private void AñadirApartamento(ActionEvent event) {
        //Crear apartamento vacío
        Apartamento apartamentoNuevo = new Apartamento();

        //Obtener los datos del apartamento;
        apartamentoNuevo.setNumero(txtNumeroApto_Crear.getText());
        apartamentoNuevo.setValor(Integer.parseInt(txtValorApto_Crear.getText()));
        apartamentoNuevo.setMatricula(txtMatriculaApto_Crear.getText());
        apartamentoNuevo.setArea(txtAreaApto_Crear.getText());
        apartamentoNuevo.setTipoUnidad(choiceBoxTipoUnidad_Crear.getValue() + "");

        //Ciclo que añade el apartamento a la torre que coincida con el nombre del choiceBOx
        ArrayList<Torre> ArrayListTemporal = proyectoTemporal.obtenerTorres();
        for (Torre torre : ArrayListTemporal) {
            if (torre.getNombre().equals(choiceBoxTorre_Crear.getValue())) {
                torre.AñadirApartamento(apartamentoNuevo);
                break;
            }
        }
        //Modificar la lista de torres con nuevo apartamento
        proyectoTemporal.modificarTorres(ArrayListTemporal);
        ObservableList<Torre> torresTabla = FXCollections.observableArrayList(proyectoTemporal.obtenerTorres());
        tableViewTorres_Crear.setItems(torresTabla);
    }

    @FXML
    private void GuardarApartamento(ActionEvent event) {
    }

    @FXML
    private void GuardarProyectoCrear(ActionEvent event) {
        proyectoTemporal.setNombre(txtNombreProyecto_Crear.getText());
        proyectos.add(proyectoTemporal);

        //Agregar proyecto a la base de datos
        boolean agregadoP = gestorProyectos.GuardarProyecto(proyectoTemporal, Integer.parseInt(usuario.getId()));
        if (agregadoP) {
            //Agregar torres a la base de datos desde la lista del proyecto
            for (Torre torreActual : proyectoTemporal.obtenerTorres()) {
                boolean agregadoT = gestorTorres.GuardarTorre(torreActual, gestorProyectos.ObtenerIdProyectoUnico(proyectoTemporal.getNombre().toUpperCase()));
                if (agregadoT) {
                    //Agregar apartamento a la base de datos desde la lista de la torre
                    for (Apartamento apartamento : torreActual.obtenerApartamentos()) {
                        gestorApartamentos.GuardarApartamento(apartamento, gestorTorres.ObtenerIdTorreUnica(torreActual.getNombre().toUpperCase()), gestorApartamentos.ObtenerIdTipoUnidad(choiceBoxTipoUnidad_Crear.getValue()));
                    }
                }
            }
            ActualizarTablaProyectos();
            CerrarVentanaProyectoNuevo(event);
        } else {
            MostrarAlertaError("No se ha podido agregar el proyecto correctamente");
        }

        //Ciclo de prueba
        for (Torre torre : proyectoTemporal.obtenerTorres()) {
            for (Apartamento apartamento : torre.obtenerApartamentos()) {
                System.out.println(apartamento.getNumero());
            }
        }
        ActualizarCantidadProyectos();
    }

    @FXML
    private void GuardarProyectoEditado(ActionEvent event) {
        //Editar el proyecto si se ha seleccionado alguno
        if (txtNombreProyecto_Editar.getText() != "") {
            proyectoTemporal.setNombre(txtNombreProyecto_Editar.getText());

            boolean edit = gestorProyectos.ActualizarProyecto(proyectoTemporal.getId(), proyectoTemporal.getNombre());
            if (edit) {
                MostrarMensajeConfirmacion("el proyecto se edito correctamente");
                ActualizarTablaProyectos();
                CerrarVentanarProyectoEditar(event);
            } else {
                MostrarAlertaError("Error no se puedo editar el proyecto correctamente ");

            }
        } else {
            //Soltar mensaje de error
        }
    }

    @FXML
    private void VisualizarApartamentos(ActionEvent event) {
        tableViewApartamentos_Crear.setVisible(true);
        tableViewTorres_Crear.setVisible(false);
        btnVisualizarApartamentos_Crear.setVisible(false);
        btnVisualizarTorres_Crear.setVisible(true);
    }

    @FXML
    private void VisualizarTorres(ActionEvent event) {
        tableViewApartamentos_Crear.setVisible(false);
        tableViewTorres_Crear.setVisible(true);
        btnVisualizarApartamentos_Crear.setVisible(true);
        btnVisualizarTorres_Crear.setVisible(false);
    }

    @FXML
    private void AbrirVentanaVentaNueva(ActionEvent event) {
        anchorPaneInterior_VentasCrear.setVisible(true);
    }

    @FXML
    private void CerrarVentanaVentaNueva(ActionEvent event) {
        anchorPaneInterior_VentasCrear.setVisible(false);
    }
}
