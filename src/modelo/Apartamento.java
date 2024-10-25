package modelo;

public class Apartamento {
    private int id;
    private String numero;
    private double valor;
    private String matricula;
    private java.sql.Date fecha;  // Cambiado a fecha en lugar de fechaEscritura
    private String area;  // Cambiado a 'area' en minúscula
    private int cantidadApa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public String getArea() {
        return area;  // Cambiado a 'area'
    }

    public void setArea(String area) {
        this.area = area;  // Cambiado a 'area'
    }

    public int getCantidadApa() {
        return cantidadApa;
    }

    public void setCantidadApa(int cantidadApa) {
        this.cantidadApa = cantidadApa;
    }

    public Apartamento() {
    }

    public Apartamento(int id, String numero, double valor, String matricula, java.sql.Date fecha, String area, int cantidadApa) {
        this.id = id;
        this.numero = numero;
        this.valor = valor;
        this.matricula = matricula;
        this.fecha = fecha;  // Cambiado a 'fecha'
        this.area = area;  // Cambiado a 'area'
        this.cantidadApa = cantidadApa;
    }
}
