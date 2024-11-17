package modelo;

import java.sql.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Apartamento {

    private IntegerProperty id;
    private StringProperty numero;
    private DoubleProperty valor;
    private StringProperty matricula;
    private java.sql.Date fecha;  // Cambiado a fecha en lugar de fechaEscritura
    private StringProperty area;  // Cambiado a 'area' en minúscula
    private StringProperty tipoUnidad;
    private StringProperty idTorre;
    private StringProperty idProyecto;

    public Apartamento(int id, String numero, double valor, String matricula, Date fecha, String area, String tipoUnidad, String idTorre, String idproyecto) {
        this.id = new SimpleIntegerProperty(id);
        this.numero = new SimpleStringProperty(numero);
        this.valor = new SimpleDoubleProperty(valor);
        this.matricula = new SimpleStringProperty(matricula);
        this.fecha = fecha;
        this.area = new SimpleStringProperty(area);
        this.tipoUnidad = new SimpleStringProperty(tipoUnidad);
        this.idTorre = new SimpleStringProperty(idTorre);
        this.idProyecto = new SimpleStringProperty(idproyecto);
    }

    public Apartamento() {
        this.id = new SimpleIntegerProperty(0);
        this.numero = new SimpleStringProperty("");
        this.valor = new SimpleDoubleProperty(0);
        this.matricula = new SimpleStringProperty("");
        this.fecha = fecha;
        this.area = new SimpleStringProperty("");
        this.tipoUnidad = new SimpleStringProperty("");
        this.idTorre = new SimpleStringProperty("");
        this.idProyecto = new SimpleStringProperty("");
    }

    //Getters y setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public double getValor() {
        return valor.get();
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public String getMatricula() {
        return matricula.get();
    }

    public void setMatricula(String matricula) {
        this.matricula.set(matricula);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getArea() {
        return area.get();
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public String getTipoUnidad() {
        return tipoUnidad.get();
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad.set(tipoUnidad);
    }

    public String getIdTorre() {
        return idTorre.get();
    }

    public void setIdTorre(String idTorre) {
        this.idTorre.set(idTorre);
    }

    public String getIdProyecto() {
        return idProyecto.get();
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto.set(idProyecto);
    }

    @Override
    public String toString() {
        return "Apartamento{" + "id=" + id + ", numero=" + numero + ", valor=" + valor + ", matricula=" + matricula + ", fecha=" + fecha + ", area=" + area + ", tipoUnidad=" + tipoUnidad + ", idTorre=" + idTorre + '}';
    }
}
