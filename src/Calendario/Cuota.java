package Calendario;

public class Cuota {
    private String cliente;
    private double monto;
    private String fechaVencimiento;
    private String estado;

    public Cuota(String cliente, double monto, String fechaVencimiento, String estado) {
        this.cliente = cliente;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }

    public String getCliente() { return cliente; }
    public double getMonto() { return monto; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public String getEstado() { return estado; }
}
