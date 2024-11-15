package controlador;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
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
import javafx.scene.layout.VBox;
import modelo.Apartamento;

//Modelos
import modelo.Proyecto;
import modelo.RolUsuario;
import modelo.Torre;
import modelo.Venta;
import modelo.DatosGrafica;

public class VistaPrincipalController implements Initializable {

    //Controladores
    private GestionProyecto gestorProyectos = new GestionProyecto();
    private GestionTorre gestorTorres = new GestionTorre();
    private GestionApartamento gestorApartamentos = new GestionApartamento();
    private GestionVenta gestorVentas = new GestionVenta();
    private RolUsuario usuario;

    //Atributos de la vista
    ArrayList<Proyecto> proyectos = new ArrayList<>();
    ArrayList<Torre> torres = new ArrayList<>();
    ArrayList<Apartamento> apartamentos = new ArrayList<>();
    Proyecto proyectoTemporal; //Esta variable se usa como almacenador general del proyecto que se está creando o modificando
    Torre torreTemporal;
    Apartamento apartamentoTemporal;
    Venta ventaTemporal;
    
    //Formato
    DecimalFormat formato = new DecimalFormat("##,###");
    
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
    private VBox VboxFactura_VentasCrearFactura;
    @FXML
    private Label lblNumeroFacturaGenerado_VentasCrearFactura;
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
    private Button btnCancelar_VentasCrearFactura;
    @FXML
    private Label lblValorTotalDeCadaCuota_VentasCrearFactura;
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
    private ImageView imgChauxFondo_Proyectos1;
    @FXML
    private BarChart<?, ?> GraficaCuotas;
    @FXML
    private PieChart GraficaVenta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setear datos ventana principal
        ActualizarCantidadProyectos();
        ActualizarCantidadApartamentos();
        ActualizarCantidadVentas();
        ActualizarGanancias();
        GraficMenu();

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
            private final Button btnEditarVenta = new Button("Editar");
            private final Button btnBorrarVenta = new Button("Borrar");
            private final Button btnCuotaVenta = new Button("Cuotas");

            {
                //Agregar estilo de CSS a los botones
                btnEditarVenta.getStyleClass().add("buttonTableViewMiniMini");
                btnBorrarVenta.getStyleClass().add("buttonTableViewMiniMini");
                btnCuotaVenta.getStyleClass().add("buttonTableViewMiniMini");

                btnEditarVenta.setOnAction(event -> {
                    //Lógica para editar venta
                });

                btnBorrarVenta.setOnAction(event -> {
                    //Lógica para borrar venta
                });

                btnCuotaVenta.setOnAction(event -> {
                    //Logica para abrir cuotas
                });
            }

            @Override
            protected void updateItem(Void item, boolean vacio) {
                super.updateItem(item, vacio);
                if (vacio) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(btnEditarVenta, btnBorrarVenta, btnCuotaVenta);
                    buttons.getStyleClass().add("hboxBotonesMini");
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
        imgChauxFondo_Ventas.setEffect(colorAdjust);
        imgChauxFondo_VentasCrear.setEffect(colorAdjust);
    }

    public void GraficMenu() {
        GraficaVenta.getData().clear();

        // Obtiene los datos de ventas y no vendido
        DatosGrafica dg = gestorApartamentos.DatosGrafica();
        int ventas = dg.getDato1();
        int noVendido = dg.getDato2();

        // Agrega los datos al gráfico circular (PieChart)
        GraficaVenta.getData().add(new PieChart.Data("vendidos ", ventas));
        GraficaVenta.getData().add(new PieChart.Data("no vendidos", noVendido));
    }

    //Funcion para obtener el usuario registrado en el login y sus dependencias
    public void SetIdUsuario(RolUsuario usuarioParam) {
        this.usuario = usuarioParam;
        lblNombreUsuario.setText(usuario.getNombre());
        lblRolUsuario.setText(usuario.getRol());
        lblCorreoUsuario.setText(usuario.getCorreo());

        //Información de los proyectos del administrador
        if (usuario.getRol().equals("Administrador")) {
            ActualizarTabla(gestorProyectos.ObtenerProyectosAdmin(Integer.parseInt(usuario.getId())), tableViewProyectos_Proyectos);
        } else {
            ActualizarTabla(gestorVentas.ObtenerVentas(Integer.parseInt(usuario.getId())), tableViewVentas_Ventas);
        }
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
    
    void ActualizarGanancias(){
        lblGananciasNum.setText(formato.format(gestorVentas.obtenerGanancias()) + "");
    }

    <T> void ActualizarTabla(ArrayList<T> listaItems, TableView<T> tabla) {
        ObservableList<T> listaObservable = FXCollections.observableArrayList(listaItems);
        tabla.setItems(listaObservable);
        tabla.refresh();
    }

    private void ActualizarChoiceBoxVentana() {
        //Actualizar ChoiceBox ventana Crear
        ObservableList<String> tipoUnidades = FXCollections.observableArrayList(gestorApartamentos.obtenerTipoUnidades());
        choiceBoxTipoUnidad_Crear.setItems(tipoUnidades);

        //Actualizar ChoiceBox ventana Editar
        choiceBoxTipoUnidad_Editar.setItems(tipoUnidades);

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

        //Reiniciar componentes aprtamento
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
        //ActualizarTabla(, tableViewApartamentosDisponibles_VentasCrear);
        
        anchorPaneInterior_VentasCrear.setVisible(true);
    }

    @FXML
    private void CerrarVentanaVentaNueva(ActionEvent event) {
        anchorPaneInterior_VentasCrear.setVisible(false);
    }

    @FXML
    private void RealizarVenta(ActionEvent event) {

    }
}
