package controlador;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import modelo.Apartamento;

//Modelos
import modelo.Proyecto;
import modelo.RolUsuario;
import modelo.Torre;
import modelo.Venta;
import modelo.DatosGrafica;
import modelo.Pago;

public class VistaPrincipalController implements Initializable {

    //Controladores
    private GestionProyecto gestorProyectos = new GestionProyecto();
    private GestionTorre gestorTorres = new GestionTorre();
    private GestionApartamento gestorApartamentos = new GestionApartamento();
    private GestionVenta gestorVentas = new GestionVenta();
    private RolUsuario usuario;
    private GestionCliente gestorClientes = new GestionCliente();
    private GestionPago gestorPagos = new GestionPago();
    private GenerarPDF generarPDF = new GenerarPDF();

    //Atributos de la vista
    ArrayList<Proyecto> proyectos = new ArrayList<>();
    ArrayList<Torre> torres = new ArrayList<>();
    ArrayList<Apartamento> apartamentos = new ArrayList<>();
    Proyecto proyectoTemporal; //Esta variable se usa como almacenador general del proyecto que se está creando o modificando
    Torre torreTemporal;
    Apartamento apartamentoTemporal;
    Venta ventaTemporal;

    //Formatos
    DecimalFormat numberFormat = new DecimalFormat("##,###");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
    private Label lblInfoAptos;
    @FXML
    private Separator separatorInfoAptos;
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
    private TextField txtAreaApto_Editar;
    @FXML
    private ChoiceBox<String> choiceBoxTipoUnidad_Editar;
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
    private TableView<Apartamento> tableViewApartamentos_Crear;
    @FXML
    private TableColumn<Apartamento, String> columnNumeroApartamento_Crear;
    @FXML
    private TableColumn<Apartamento, Double> columnValorApartamento_Crear;
    @FXML
    private TableColumn<Apartamento, String> columnAreaApartamento_Crear;
    @FXML
    private TableColumn<Apartamento, String> columnMatriculaApartamento_Crear;
    @FXML
    private TableColumn<Apartamento, String> columnTipoUnidadApartamento_Crear;
    @FXML
    private TableColumn<Apartamento, String> columnTorreApartamento_Crear;
    @FXML
    private Button btnVisualizarApartamentos_Crear;
    @FXML
    private Button btnVisualizarTorres_Crear;
    @FXML
    private TableView<Apartamento> tableViewApartamentos_Editar;
    @FXML
    private Button btnVisualizarApartamentos_Editar;
    @FXML
    private Button btnVisualizarTorres_Editar;
    @FXML
    private TableColumn<Apartamento, String> columnNumeroApartamento_Editar;
    @FXML
    private TableColumn<Apartamento, Double> columnValorApartamento_Editar;
    @FXML
    private TableColumn<Apartamento, String> columnAreaApartamento_Editar;
    @FXML
    private TableColumn<Apartamento, String> columnMatriculaApartamento_Editar;
    @FXML
    private TableColumn<Apartamento, String> columnTipoUnidadApartamento_Editar;
    @FXML
    private TableColumn<Apartamento, String> columnTorreApartamento_Editar;
    @FXML
    private TableColumn<Apartamento, Void> columnAccionesApartamento_Editar;
    @FXML
    private AnchorPane anchorPaneInterior_Ventas;
    @FXML
    private Label lblVentas;
    @FXML
    private AnchorPane anchorPaneInterior_VentasCrear;
    @FXML
    private Button btnAgregarVenta_Ventas;
    @FXML
    private TableView<Venta> tableViewVentas_Ventas;
    @FXML
    private TableColumn<Venta, Integer> columnIdVentas;
    @FXML
    private TableColumn<Venta, Double> columnValorVentas;
    @FXML
    private TableColumn<Venta, Integer> columnNumeroCuotasVentas;
    @FXML
    private TableColumn<Venta, Double> columnInteresVentas;
    @FXML
    private TableColumn<Venta, String> columnIdApartamentoVentas;
    @FXML
    private TableColumn<Venta, Void> columnAccionesVentas;
    @FXML
    private Button btnCerrarAgregarVentas;
    @FXML
    private AnchorPane anchorPaneExterior_Ventas;
    @FXML
    private ImageView imgChauxFondo_Ventas;
    @FXML
    private ImageView imgChauxFondo_VentasCrear;
    @FXML
    private TableView<Apartamento> tableViewApartamentosDisponibles_VentasCrear;
    @FXML
    private TableColumn<Apartamento, String> columnApartamento_VentasCrear;
    @FXML
    private TableColumn<Apartamento, String> columnTorre_VentasCrear;
    @FXML
    private TableColumn<Apartamento, String> columnProyecto_VentasCrear;
    @FXML
    private AnchorPane panelDatosdelCliente_VentasCrear;
    @FXML
    private TextField txtNombreCliente_VentasCrear;
    @FXML
    private TextField txtApellidoCliente_VentasCrear;
    @FXML
    private TextField txtDireccionCliente_VentasCrear;
    @FXML
    private ChoiceBox<String> choiceBoxSISBENCliente_VentasCrear;
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
    @FXML
    private Label lblRegistrar_VentasCrear;
    @FXML
    private Label lblDatosCliente_VentasCrear;
    @FXML
    private ImageView imgCliente;
    @FXML
    private Label lblCliente_VentasCrearFactura;
    @FXML
    private Label lblNombreGenerado_VentasCrearFactura;
    @FXML
    private Label lblTelefonoGenerado_VentasCrearFactura;
    @FXML
    private Label lblCompra_VentasCrearFactura;
    @FXML
    private Label lblTorreGenerado_VentasCrearFactura;
    @FXML
    private Label lblApartamentoGenerado_VentasCrearFactura;
    @FXML
    private Label lblDiaDeCobro_VentasCrearFactura;
    @FXML
    private TextField txtDiaIngresar_VentasCrearFactura;
    @FXML
    private Label lblNumeroDecuotas_VentasCrearFactura;
    @FXML
    private Label lblValorDeCadaCuota_VentasCrearFactura;
    @FXML
    private Label lblValorGenerado_VentasCrearFactura;
    @FXML
    private Label lblFactura_VentasCrearFactura;
    @FXML
    private Label lbPago_VentasCrearFactura;
    @FXML
    private Label lblValorApto_VentasCrearFactura;
    @FXML
    private Label lblValorAptoGenerado_VentasCrearFactura;
    @FXML
    private Label lblSISBEN_VentasCrearFactura;
    @FXML
    private Label lblSubsidio_VentasCrearFactura;
    @FXML
    private Label lblSubsidioGenerado_VentasCrearFactura;
    @FXML
    private Button btnConfiramar_VentasCrearFactura;
    @FXML
    private Label lblNumeroGenerado_VentasCrearFactura;
    @FXML
    private AnchorPane anchorPaneInterior_VentasCrearFactura;
    @FXML
    private Label lblDatosDeCobro_VentasCreaFactura;
    @FXML
    private Label lblSISIBENGeneradoRayitas_VentasCrearFactura;
    @FXML
    private AnchorPane anchorPaneInterior_Proyectos1;
    @FXML
    private BarChart<String, Number> GraficaCuotas;
    @FXML
    private PieChart GraficaVenta;
    @FXML
    private TableView<Apartamento> tableViewApartamentosVendidos_Chaux;
    @FXML
    private Label lblSeleccioneUnApartamento_Ventas1;
    @FXML
    private Label lblSeleccioneUnApartamento_Ventas2;
    @FXML
    private AnchorPane anchorPaneInterior_VerCuotas;
    @FXML
    private Label lblCuotas_VerCuotas;
    @FXML
    private ImageView imgChauxFondo_VerCuotas;
    @FXML
    private TableView<Pago> tableViewCuotas_VerCuotas;
    @FXML
    private TableColumn<Pago, Double> columnIdValorCuotas_VerCuotas;
    @FXML
    private TableColumn<Pago, LocalDate> columnFechaPagoCuotas_VerCuotas;
    @FXML
    private TableColumn<Pago, LocalDate> columnFechaVencimientoCuotas_VerCuotas;
    @FXML
    private TableColumn<Pago, String> columnEstadoCuotas_VerCuotas;
    @FXML
    private TableColumn<Pago, Integer> columnNumeroDeCuotaCuotas_VerCuotas;
    @FXML
    private TableColumn<Pago, Void> columnAccionesCuotas_VerCuotas;
    @FXML
    private Button btnCerrarVentana_VerCuotas;
    @FXML
    private HBox HboxFactura_VentasCrearFactura;
    @FXML
    private Label lblValorTotalDeLaCompra_VentasCrearFactura;
    @FXML
    private TableColumn<Apartamento, String> columnApartamentos_Chaux;
    @FXML
    private TableColumn<Apartamento, String> columnTorre_Chaux;
    @FXML
    private TableColumn<Apartamento, Double> columnValor_Chaux;
    @FXML
    private TableColumn<Apartamento, LocalDate> columnFechaEscritura_Chaux;
    @FXML
    private ImageView imgChauxFondo_Dashboard;
    @FXML
    private GridPane calendarioGrid;
    @FXML
    private Button btnGenerarReporte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setear datos ventana principal
        ActualizarCantidadProyectos();
        ActualizarCantidadApartamentos();
        ActualizarCantidadVentas();
        ActualizarGanancias();
        ActualizarTabla(gestorApartamentos.ObtenerApartamentosVendidos(), tableViewApartamentosVendidos_Chaux);
        GraficMenu();
        llenarGraficaCuotas();
        llenarCalendario();

        //Setear los datos de las columnas de la tabla proyecto a los valores correspondientes
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnCantTorres.setCellValueFactory(new PropertyValueFactory<>("cantidadTorres"));

        //Setear los datos de las columnas de la tabla torres a los valores correspondientes
        columnNumeroTorre_Crear.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApartamentos_Crear.setCellValueFactory(new PropertyValueFactory<>("cantidadApartamentos"));
        columnNumeroTorre_Editar.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        //Setear los datos de las columnas de la tabla apartamentos a los valores correspondientes
        columnNumeroApartamento_Crear.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnValorApartamento_Crear.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnMatriculaApartamento_Crear.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        columnAreaApartamento_Crear.setCellValueFactory(new PropertyValueFactory<>("area"));
        columnTipoUnidadApartamento_Crear.setCellValueFactory(new PropertyValueFactory<>("tipoUnidad"));
        columnTorreApartamento_Crear.setCellValueFactory(new PropertyValueFactory<>("idTorre"));

        columnNumeroApartamento_Editar.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnValorApartamento_Editar.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnMatriculaApartamento_Editar.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        columnAreaApartamento_Editar.setCellValueFactory(new PropertyValueFactory<>("area"));
        columnTipoUnidadApartamento_Editar.setCellValueFactory(new PropertyValueFactory<>("tipoUnidad"));
        columnTorreApartamento_Editar.setCellValueFactory(new PropertyValueFactory<>("idTorre"));

        columnIdVentas.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnValorVentas.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnNumeroCuotasVentas.setCellValueFactory(new PropertyValueFactory<>("numCuotas"));
        columnInteresVentas.setCellValueFactory(new PropertyValueFactory<>("interes"));
        columnIdApartamentoVentas.setCellValueFactory(new PropertyValueFactory<>("idApartamento"));

        columnApartamento_VentasCrear.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnTorre_VentasCrear.setCellValueFactory(new PropertyValueFactory<>("idTorre"));
        columnProyecto_VentasCrear.setCellValueFactory(new PropertyValueFactory<>("idProyecto"));

        columnIdValorCuotas_VerCuotas.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnEstadoCuotas_VerCuotas.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columnFechaPagoCuotas_VerCuotas.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
        columnFechaVencimientoCuotas_VerCuotas.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
        columnNumeroDeCuotaCuotas_VerCuotas.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnApartamentos_Chaux.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnTorre_Chaux.setCellValueFactory(new PropertyValueFactory<>("idTorre"));
        columnFechaEscritura_Chaux.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnValor_Chaux.setCellValueFactory(new PropertyValueFactory<>("valor"));

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

                        MostrarAlertaError("Error, no se pudo eliminar el proyecto correctamente");
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

                btnEditarTorre.getStyleClass().add("buttonTableViewMini");
                btnBorrarTorre.getStyleClass().add("buttonTableViewMini");

                btnEditarTorre.setOnAction(event -> {
                    //Obtener torre
                    torreTemporal = getTableView().getItems().get(getIndex());

                    // Lógica para editar la torre
                    txtNumeroTorre_Editar.setText(torreTemporal.getNombre());

                });

                btnBorrarTorre.setOnAction(event -> {
                    //Obtener torre
                    Torre torre = getTableView().getItems().get(getIndex());

                    //Logica para eliminar la torre
                    boolean elim = gestorTorres.BorrarTorre(torre.getId());
                    if (elim) {
                        getTableView().getItems().remove(torre);
                    } else {

                        MostrarAlertaError("No se pudo eliminar correctamente la torre");

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

        columnAccionesApartamento_Editar.setCellFactory(columna -> new TableCell<Apartamento, Void>() {
            private final Button btnEditarApartamento = new Button("Editar");
            private final Button btnBorrarApartamento = new Button("Borrar");

            {
                //Agregar estilo de CSS a los botones
                btnEditarApartamento.getStyleClass().add("buttonTableViewMini");
                btnBorrarApartamento.getStyleClass().add("buttonTableViewMini");

                btnEditarApartamento.setOnAction(event -> {
                    //Obtener apartamento
                    apartamentoTemporal = getTableView().getItems().get(getIndex());

                    // Lógica para editar la apartamento
                    txtNumeroApto_Editar.setText(apartamentoTemporal.getNumero());
                    txtValorApto_Editar.setText(apartamentoTemporal.getValor() + "");
                    txtAreaApto_Editar.setText(apartamentoTemporal.getArea());
                    choiceBoxTipoUnidad_Editar.setValue(apartamentoTemporal.getTipoUnidad());
                });

                btnBorrarApartamento.setOnAction(event -> {
                    //Obtener apartamento
                    Apartamento apartamento = getTableView().getItems().get(getIndex());

                    //Logica para eliminar la torre
                    boolean elim = gestorApartamentos.BorrarApartamento(apartamento.getId());
                    if (elim) {
                        getTableView().getItems().remove(apartamento);
                    } else {
                        MostrarAlertaError("No se pudo eliminar correctamente la torre");

                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean vacio) {
                super.updateItem(item, vacio);
                if (vacio) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(btnEditarApartamento, btnBorrarApartamento);
                    buttons.getStyleClass().add("hboxBotones");
                    setGraphic(buttons);
                }
            }
        });

        columnAccionesVentas.setCellFactory(columna -> new TableCell<Venta, Void>() {
            private final Button btnBorrarVenta = new Button("Borrar");
            private final Button btnCuotaVenta = new Button("Cuotas");

            {
                //Agregar estilo de CSS a los botones
                btnBorrarVenta.getStyleClass().add("buttonTableViewMini");
                btnCuotaVenta.getStyleClass().add("buttonTableViewMini");

                btnBorrarVenta.setOnAction(event -> {
                    //Lógica para borrar venta
                    Venta venta = getTableView().getItems().get(getIndex());
                    boolean elim = gestorVentas.EliminarVenta(venta.getId());
                    if (elim) {
                        getTableView().getItems().remove(venta);
                    } else {
                        MostrarAlertaError("No se pudo eliminar correctamente la venta");
                    }
                });

                btnCuotaVenta.setOnAction(event -> {
                    ventaTemporal = getTableView().getItems().get(getIndex());
                    AbrirVentanaCuotas(event);
                });
            }

            @Override
            protected void updateItem(Void item, boolean vacio) {
                super.updateItem(item, vacio);
                if (vacio) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(btnBorrarVenta, btnCuotaVenta);
                    buttons.getStyleClass().add("hboxBotones");
                    setGraphic(buttons);
                }
            }
        });

        columnAccionesCuotas_VerCuotas.setCellFactory(columnna -> new TableCell<Pago, Void>() {
            private final Button btnPagar = new Button("Pagar");
            private final DatePicker datePicker = new DatePicker();

            {
                btnPagar.getStyleClass().add("buttonTableView");
                datePicker.getStyleClass().add("date-picker");

                btnPagar.setOnAction(event -> {
                    // Obtener la fecha seleccionada del DatePicker
                    LocalDate fechaSeleccionada = datePicker.getValue();
                    if (fechaSeleccionada != null) {
                        Date fechaSqlDate = Date.valueOf(fechaSeleccionada);

                        // Lógica para pagar la cuota con la fecha seleccionada
                        gestorPagos.EditarPago(getTableView().getItems().get(getIndex()).getId(), fechaSqlDate);

                        // Actualizar la tabla
                        ActualizarTabla(gestorPagos.ObtenerCuotas(ventaTemporal.getId()), tableViewCuotas_VerCuotas);
                    } else {
                        // Manejo si no se selecciona ninguna fecha
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione una fecha antes de pagar.");
                        alert.showAndWait();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean vacio) {
                super.updateItem(item, vacio);
                if (vacio) {
                    setGraphic(null);
                } else {
                    // Crear un HBox para contener el botón y el DatePicker
                    HBox hbox = new HBox(5, datePicker, btnPagar);
                    hbox.getStyleClass().add("hboxBotones");
                    setGraphic(hbox);
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
        imgChauxFondo_Ventas.setEffect(colorAdjust);
        imgChauxFondo_VentasCrear.setEffect(colorAdjust);
        imgChauxFondo_VerCuotas.setEffect(colorAdjust);
        imgChauxFondo_Dashboard.setEffect(colorAdjust);
    }

    public void GraficMenu() {
        GraficaVenta.getData().clear();
        // Obtiene los datos de ventas y no vendido
        DatosGrafica dg = gestorApartamentos.DatosGrafica();
        int dato1 = dg.getDato1();
        int dato2 = dg.getDato2();

        // Agrega los datos al gráfico circular (PieChart)
        PieChart.Data vendidos = new PieChart.Data("Vendidos", dato1);
        PieChart.Data noVendidos = new PieChart.Data("No vendido", dato2);

        GraficaVenta.getData().add(vendidos);
        GraficaVenta.getData().add(noVendidos);

        vendidos.getNode().setStyle("-fx-pie-color: #f9560b;");
        noVendidos.getNode().setStyle("-fx-pie-color: #060f22;");
    }

    public void llenarGraficaCuotas() {
        GraficaCuotas.getData().clear();

        DatosGrafica dg = gestorPagos.datosGraficaDash();
        int cuotasPagadas = dg.getDato1();
        int cuotasVencidas = dg.getDato2();

        XYChart.Series<String, Number> serieCuotas = new XYChart.Series<>();
        serieCuotas.setName("Estado de Cuotas");

        serieCuotas.getData().add(new XYChart.Data<>("Pagadas", cuotasPagadas));
        serieCuotas.getData().add(new XYChart.Data<>("Vencidas", cuotasVencidas));

        GraficaCuotas.getData().add(serieCuotas);
    }

    public void llenarCalendario() {
        // Dimensiones del GridPane
        double gridWidth = 850;
        double gridHeight = 550;

        // Cantidad de columnas y filas
        int totalColumns = 7; // Una semana tiene 7 días
        int totalRows = 5; // Suponiendo 5 filas para acomodar días del mes

        // Calcular tamaños de celdas
        double cellWidth = gridWidth / totalColumns;
        double cellHeight = gridHeight / totalRows;

        // Configurar proporciones de columnas y filas
        calendarioGrid.getColumnConstraints().clear();
        calendarioGrid.getRowConstraints().clear();
        for (int i = 0; i < totalColumns; i++) {
            ColumnConstraints col = new ColumnConstraints(cellWidth);
            calendarioGrid.getColumnConstraints().add(col);
        }
        for (int i = 0; i < totalRows; i++) {
            RowConstraints row = new RowConstraints(cellHeight);
            calendarioGrid.getRowConstraints().add(row);
        }
        //Llenar de botones
        for (int day = 1; day <= 30; day++) {
            Button dayButton = new Button(String.valueOf(day));
            dayButton.getStyleClass().add("btnCalendario");
            dayButton.setStyle("-fx-min-width: " + cellWidth * 0.8 + "px; -fx-min-height: " + cellHeight * 0.8 + "px;");

            // Agregar evento al botón
            int finalDay = day; // Variable para usar dentro de la lambda
            dayButton.setOnAction(event -> mostrarMensajeCuota(finalDay));

            // Añadir botón al GridPane (distribuir en filas y columnas)
            int row = (day - 1) / totalColumns; // Suponiendo una semana de 7 días
            int col = (day - 1) % totalColumns;
            calendarioGrid.add(dayButton, col, row);
        }
    }

    // Método para mostrar el diálogo
    private void mostrarMensajeCuota(int day) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Cuotas del día " + day);

        // Contenido de la ventana
        dialog.getDialogPane().setContent(new Label("Cuotas pendientes para el día " + day));

        // Botón para cerrar
        dialog.getDialogPane().getButtonTypes().addAll(javafx.scene.control.ButtonType.CLOSE);

        dialog.showAndWait();
    }

    //Funcion para obtener el usuario registrado en el login y sus dependencias
    public void SetIdUsuario(RolUsuario usuarioParam) {
        this.usuario = usuarioParam;
        lblNombreUsuario.setText(usuario.getNombre());
        lblRolUsuario.setText(usuario.getRol());
        lblCorreoUsuario.setText(usuario.getCorreo());

        //Información de los proyectos del administrador
        if (usuario.getRol().equals("Administrador")) {
            tabVentas.disableProperty().set(true);
            tabDashboard.disableProperty().set(true);
            ActualizarTabla(gestorProyectos.ObtenerProyectosAdmin(Integer.parseInt(usuario.getId())), tableViewProyectos_Proyectos);
        } else {
            tabProyectos.disableProperty().set(true);
            ActualizarTabla(gestorVentas.ObtenerVentas(Integer.parseInt(usuario.getId())), tableViewVentas_Ventas);
        }

        ActualizarChoiceBoxVentana();
    }

    //Actualizaciones de información del programa
    void ActualizarCantidadProyectos() {
        lblCantidadProyectosNum.setText(gestorProyectos.ObtenerTotalProyecto() + "");
    }

    void ActualizarCantidadApartamentos() {
        lblCantidadApartamentosNum.setText(gestorApartamentos.ObtenerApartamentos() + "");
    }

    void ActualizarCantidadVentas() {
        lblCantidadVentasNum.setText(gestorVentas.obtenerCantidadVentas() + "");
    }

    void ActualizarGanancias() {
        lblGananciasNum.setText(numberFormat.format(gestorVentas.obtenerGanancias()) + "");
    }

    <T> void ActualizarTabla(ArrayList<T> listaItems, TableView<T> tabla) {
        ObservableList<T> listaObservable = FXCollections.observableArrayList(listaItems);
        tabla.setItems(listaObservable);
        tabla.refresh();
    }

    private void ActualizarChoiceBoxVentana() {
        if (usuario.getRol().equals("Administrador")) {
            //Actualizar ChoiceBox ventana Crear
            ObservableList<String> tipoUnidades = FXCollections.observableArrayList(gestorApartamentos.obtenerTipoUnidades());
            choiceBoxTipoUnidad_Crear.setItems(tipoUnidades);

            //Actualizar ChoiceBox ventana Editar
            choiceBoxTipoUnidad_Editar.setItems(tipoUnidades);
        } else {
            choiceBoxSISBENCliente_VentasCrear.setValue("SISBEN");
            choiceBoxSISBENCliente_VentasCrear.setItems(FXCollections.observableArrayList("Si", "No"));
        }
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
        //Crear proyecto vacío a guardar
        proyectoTemporal = new Proyecto();
        choiceBoxTipoUnidad_Crear.setValue("Tipo unidad");
        choiceBoxTorre_Crear.setValue("Torre");

        //Abrir la ventana
        anchorPaneInterior_ProyectosCrear.setVisible(true);
    }

    @FXML
    private void CerrarVentanaProyectoNuevo(ActionEvent event) {
        anchorPaneInterior_ProyectosCrear.setVisible(false);

        //Reiniciar componentes proyecto
        txtNombreProyecto_Crear.setText("");

        //Reiniciar componentes torre
        txtNumeroTorre_Crear.setText("");
        lblCantidadDeTorresNum_Crear.setText("0");
        tableViewTorres_Crear.setItems(FXCollections.observableArrayList());
        tableViewApartamentos_Crear.setItems(FXCollections.observableArrayList());

        //Reiniciar componentes apartamento
        txtNumeroApto_Crear.setText("");
        txtValorApto_Crear.setText("");
        txtMatriculaApto_Crear.setText("");
        txtAreaApto_Crear.setText("");
        choiceBoxTipoUnidad_Crear.setValue("Tipo unidad");
        choiceBoxTorre_Crear.getItems().clear();
        choiceBoxTorre_Crear.setValue("Torre");

    }

    //Abrir la ventana para editar un proyecto
    private void AbrirVentanaProyectoEditar(ActionEvent event) {
        //Reiniciar componentes proyecto
        txtNombreProyecto_Editar.setText(proyectoTemporal.getNombre());
        choiceBoxTipoUnidad_Editar.setValue("Tipo unidad");

        proyectoTemporal.modificarTorres(gestorTorres.ObtenerTorre(proyectoTemporal.getId())); //Obtener la lista de las torres que tiene el proyecto
        ActualizarTabla(proyectoTemporal.obtenerTorres(), tableViewTorres_Editar);

        apartamentos = gestorApartamentos.ObtenerApartamentosProyecto(proyectoTemporal.getId());
        ActualizarTabla(apartamentos, tableViewApartamentos_Editar);

        //Abrir la ventana
        anchorPaneInterior_ProyectosEditar.setVisible(true);
    }

    @FXML
    private void CerrarVentanarProyectoEditar(ActionEvent event) {
        anchorPaneInterior_ProyectosEditar.setVisible(false);

        //Reinicar compoentes torre
        txtNumeroTorre_Editar.setText("");

        //Reiniciar componentes apartamento
        txtNumeroApto_Editar.setText("");
        txtValorApto_Editar.setText("");

        txtAreaApto_Editar.setText("");
        choiceBoxTipoUnidad_Editar.setValue("Tipo unidad");

    }

    //Logica para añadir torre
    @FXML
    private void AñadirTorre(ActionEvent event) {
        Torre torreNueva = new Torre();
        torreNueva.setNombre(txtNumeroTorre_Crear.getText());

        proyectoTemporal.añadirTorre(torreNueva);
        lblCantidadDeTorresNum_Crear.setText(proyectoTemporal.getCantidadTorres() + "");
        choiceBoxTorre_Crear.getItems().add(torreNueva.getNombre());

        ActualizarTabla(proyectoTemporal.obtenerTorres(), tableViewTorres_Crear);
    }

    //Guardar la torre editada en la base de datos
    @FXML
    private void GuardarTorre(ActionEvent event) {
        torreTemporal.setNombre(txtNumeroTorre_Editar.getText());

        //Actualizar torre a la base de datos
        boolean editT = gestorTorres.ActualizarTorre(torreTemporal.getId(), torreTemporal.getNombre());

        if (editT) {

            ActualizarTabla(gestorTorres.ObtenerTorre(proyectoTemporal.getId()), tableViewTorres_Editar);

        } else {
            MostrarAlertaError("Error no se pudo editar la torre correctamente");
        }
    }

    //Logica para añadir apartamento
    @FXML
    private void AñadirApartamento(ActionEvent event) {
        //Crear apartamento vacío
        Apartamento apartamentoNuevo = new Apartamento();

        //Obtener los datos del apartamento;
        apartamentoNuevo.setNumero(txtNumeroApto_Crear.getText());

        apartamentoNuevo.setValor(Double.parseDouble(txtValorApto_Crear.getText()));
        apartamentoNuevo.setMatricula(txtMatriculaApto_Crear.getText());
        apartamentoNuevo.setArea(txtAreaApto_Crear.getText());

        apartamentoNuevo.setTipoUnidad(choiceBoxTipoUnidad_Crear.getValue());

        //Ciclo que añade el apartamento a la torre que coincida con el nombre del choiceBOx
        ArrayList<Torre> ArrayListTemporal = proyectoTemporal.obtenerTorres();
        for (Torre torre : ArrayListTemporal) {
            if (torre.getNombre().equals(choiceBoxTorre_Crear.getValue())) {
                apartamentoNuevo.setIdTorre(torre.getNombre());
                torre.AñadirApartamento(apartamentoNuevo);
                break;
            }
        }

        //Modificar la lista de torres con nuevo apartamento
        proyectoTemporal.modificarTorres(ArrayListTemporal);

        ActualizarTabla(proyectoTemporal.obtenerTorres(), tableViewTorres_Crear);

        //Actualizar lista apartamentos
        this.apartamentos.add(apartamentoNuevo);
        ActualizarTabla(apartamentos, tableViewApartamentos_Crear);
    }

    //Guardar el apartamento editado en la base de datos
    @FXML
    private void GuardarApartamento(ActionEvent event) {
        apartamentoTemporal.setNumero(txtNumeroApto_Editar.getText());
        apartamentoTemporal.setValor(Double.parseDouble(txtValorApto_Editar.getText()));
        apartamentoTemporal.setArea(txtAreaApto_Editar.getText());
        apartamentoTemporal.setTipoUnidad(choiceBoxTipoUnidad_Editar.getValue());

        boolean editA = gestorApartamentos.ActualizarApartamento(apartamentoTemporal.getId(), apartamentoTemporal.getNumero(), apartamentoTemporal.getValor(), apartamentoTemporal.getArea(), gestorApartamentos.ObtenerIdTipoUnidad(apartamentoTemporal.getTipoUnidad()));

        if (editA) {
            ActualizarTabla(apartamentos, tableViewApartamentos_Editar);
        } else {
            MostrarAlertaError("Error no se pudo editar el apartamento correctamente");
        }
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

            ActualizarTabla(gestorProyectos.ObtenerProyectosAdmin(Integer.parseInt(usuario.getId())), tableViewProyectos_Proyectos);
            CerrarVentanaProyectoNuevo(event);
        } else {
            MostrarAlertaError("No se ha podido agregar el proyecto correctamente");
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
                MostrarMensajeConfirmacion("El proyecto se editó correctamente");

                ActualizarTabla(gestorProyectos.ObtenerProyectosAdmin(Integer.parseInt(usuario.getId())), tableViewProyectos_Proyectos);
                CerrarVentanarProyectoEditar(event);
            } else {
                MostrarAlertaError("Error no se puedo editar el proyecto correctamente ");

            }
        } else {
            //Soltar mensaje de error
        }
    }

    @FXML
    private void AlternarTablasApartamentosCrear(ActionEvent event) {
        tableViewApartamentos_Crear.setVisible(true);
        tableViewTorres_Crear.setVisible(false);
        btnVisualizarApartamentos_Crear.setVisible(false);
        btnVisualizarTorres_Crear.setVisible(true);
    }

    @FXML
    private void AlternarTablasTorresCrear(ActionEvent event) {
        tableViewApartamentos_Crear.setVisible(false);
        tableViewTorres_Crear.setVisible(true);
        btnVisualizarApartamentos_Crear.setVisible(true);
        btnVisualizarTorres_Crear.setVisible(false);
    }

    @FXML
    private void AlternarTablasApartamentosEditar(ActionEvent event) {
        tableViewApartamentos_Editar.setVisible(true);
        tableViewTorres_Editar.setVisible(false);
        btnVisualizarApartamentos_Editar.setVisible(false);
        btnVisualizarTorres_Editar.setVisible(true);
    }

    @FXML
    private void AlternarTablasTorresEditar(ActionEvent event) {
        tableViewApartamentos_Editar.setVisible(false);
        tableViewTorres_Editar.setVisible(true);
        btnVisualizarApartamentos_Editar.setVisible(true);
        btnVisualizarTorres_Editar.setVisible(false);
    }

    @FXML
    private void AbrirVentanaVentaNueva(ActionEvent event) {
        ActualizarTabla(gestorApartamentos.ObtenerApartamentosNoVendidos(), tableViewApartamentosDisponibles_VentasCrear);

        anchorPaneInterior_VentasCrear.setVisible(true);
    }

    @FXML
    private void CerrarVentanaVentaNueva(ActionEvent event) {
        anchorPaneInterior_VentasCrear.setVisible(false);

        //Reiniciar componentes de la venta
        txtCuotasDatosVenta_crear.setText("");
        txtInteresesDatosdeVenta_crear.setText("");

        //Reiniciar componentes del cliente
        txtNombreCliente_VentasCrear.setText("");
        txtApellidoCliente_VentasCrear.setText("");
        txtDireccionCliente_VentasCrear.setText("");
        txtCedulaCliente_VentasCrear.setText("");
        txtSubsidioCliente_VentasCrear.setText("");
        txtCorreoelectronicoCliente_VentasCrear.setText("");
        choiceBoxSISBENCliente_VentasCrear.setValue("SISBEN");
        txtTelefonoCliente_VentasCrear.setText("");
    }

    @FXML
    private void RealizarVenta(ActionEvent event) {
        ventaTemporal = new Venta();
        ventaTemporal.setNumCuotas(Integer.parseInt(txtCuotasDatosVenta_crear.getText()));
        ventaTemporal.setInteres(Double.parseDouble(txtInteresesDatosdeVenta_crear.getText()) / 100);
        ventaTemporal.setValor(tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getValor() * (1 + ventaTemporal.getInteres()));

        int idCliente = gestorClientes.IdentificarCliente(txtCedulaCliente_VentasCrear.getText());

        if (idCliente != 0) {
            //Agregar la venta al cliente
            ventaTemporal.setValor(ventaTemporal.getValor() - gestorClientes.obtenerSubsidio(idCliente));
            gestorVentas.GuardarVenta(ventaTemporal, gestorApartamentos.ObtenerApartamentoUnico(tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getNumero(), tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getIdTorre(), tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getIdProyecto()), Integer.parseInt(usuario.getId()), idCliente, gestorClientes.ObtenerSISBEN(idCliente));
        } else {
            //Crear el cliente
            gestorClientes.GuardarCliente(txtNombreCliente_VentasCrear.getText(), txtApellidoCliente_VentasCrear.getText(), txtDireccionCliente_VentasCrear.getText(), txtCedulaCliente_VentasCrear.getText(), Double.parseDouble(txtSubsidioCliente_VentasCrear.getText()), txtCorreoelectronicoCliente_VentasCrear.getText(), choiceBoxSISBENCliente_VentasCrear.getValue());
            idCliente = gestorClientes.IdentificarCliente(txtCedulaCliente_VentasCrear.getText());
            gestorClientes.GuardarNumeroCliente(idCliente, txtTelefonoCliente_VentasCrear.getText());
            //Agregar la venta al cliente
            ventaTemporal.setValor(ventaTemporal.getValor() - gestorClientes.obtenerSubsidio(idCliente));
            gestorVentas.GuardarVenta(ventaTemporal, gestorApartamentos.ObtenerApartamentoUnico(tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getNumero(), tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getIdTorre(), tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getIdProyecto()), Integer.parseInt(usuario.getId()), idCliente, gestorClientes.ObtenerSISBEN(idCliente));
        }

        //Setear componentes de la factura
        //Info cliente
        lblNombreGenerado_VentasCrearFactura.setText(gestorClientes.obtenerNombre(idCliente));
        lblTelefonoGenerado_VentasCrearFactura.setText(gestorClientes.obtenerNumero(idCliente));
        //Info compra
        lblTorreGenerado_VentasCrearFactura.setText("Torre: " + tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getIdTorre());
        lblApartamentoGenerado_VentasCrearFactura.setText("Apartamento: " + tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getNumero());
        //Info datos cobro0
        lblNumeroGenerado_VentasCrearFactura.setText(ventaTemporal.getNumCuotas() + "");
        lblValorGenerado_VentasCrearFactura.setText("$" + ventaTemporal.getValor() / ventaTemporal.getNumCuotas());
        //Info pago
        lblValorAptoGenerado_VentasCrearFactura.setText(tableViewApartamentosDisponibles_VentasCrear.getSelectionModel().getSelectedItem().getValor() + "");
        String texto = gestorClientes.ObtenerSISBEN(idCliente) ? "-10%" : "--";
        lblSISIBENGeneradoRayitas_VentasCrearFactura.setText(texto);
        lblSubsidioGenerado_VentasCrearFactura.setText(gestorClientes.obtenerSubsidio(idCliente) + "");
        //Valor final de la venta
        lblValorTotalDeLaCompra_VentasCrearFactura.setText(ventaTemporal.getValor() + "");

        anchorPaneInterior_VentasCrearFactura.setVisible(true);

        //Actualizar tablas
        ActualizarTabla(gestorApartamentos.ObtenerApartamentosNoVendidos(), tableViewApartamentosDisponibles_VentasCrear); //Tabla de apartamentos disponibles
        ActualizarTabla(gestorVentas.ObtenerVentas(Integer.parseInt(usuario.getId())), tableViewVentas_Ventas); //Tabla de ventas
    }

    @FXML
    private void ConfirmarVenta(ActionEvent event) {
        //Registrar las cuotas
        gestorPagos.GuardarPago(ventaTemporal.getNumCuotas(), txtDiaIngresar_VentasCrearFactura.getText(), ventaTemporal.getValor() / ventaTemporal.getNumCuotas(), Integer.parseInt(usuario.getId()), gestorVentas.obtenerIdUltimaVenta(), gestorClientes.IdentificarCliente(txtCedulaCliente_VentasCrear.getText()));

        anchorPaneInterior_VentasCrearFactura.setVisible(false);
        CerrarVentanaVentaNueva(event);
    }

    private void AbrirVentanaCuotas(ActionEvent event) {
        ActualizarTabla(gestorPagos.ObtenerCuotas(ventaTemporal.getId()), tableViewCuotas_VerCuotas);
        anchorPaneInterior_VerCuotas.setVisible(true);
    }

    @FXML
    private void CerrarVentanaCuotas(ActionEvent event) {
        anchorPaneInterior_VerCuotas.setVisible(false);
    }

    @FXML
    private void GenerarReporte(ActionEvent event) {
        generarPDF.geneararpdf(gestorApartamentos.DatosReportes());
        MostrarMensajeConfirmacion("PDF generado exitosamente.");
    }
}
