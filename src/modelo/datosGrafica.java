package modelo;

public class DatosGrafica {

    private int dato1;
    private int dato2;

    public int getDato1() {
        return dato1;
    }

    public void setDato1(int dato1) {
        this.dato1 = dato1;
    }

    public int getDato2() {
        return dato2;
    }

    public void setDato2(int dato2) {
        this.dato2 = dato2;
    }

    public DatosGrafica(int ventas, int noVendido) {
        this.dato1 = ventas;
        this.dato2 = noVendido;
    }

}
