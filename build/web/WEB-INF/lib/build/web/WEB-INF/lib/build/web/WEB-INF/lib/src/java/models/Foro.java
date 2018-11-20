package models;
/**
 *
 * @author Zeth
 */
public class Foro {
    private double salida1;
    private double salida2;
    private double salida3;
    private double salida4;
    private double salida5;
    private double salida6;
    private String nombre;

    public Foro() {
    }

    public Foro(double salida1, double salida2, double salida3, double salida4, double salida5, double salida6,String nombre) {
        this.salida1 = salida1;
        this.salida2 = salida2;
        this.salida3 = salida3;
        this.salida4 = salida4;
        this.salida5 = salida5;
        this.salida6 = salida6;
        this.nombre=nombre;
    }

    public double getSalida1() {
        return salida1;
    }

    public void setSalida1(double salida1) {
        this.salida1 = salida1;
    }

    public double getSalida2() {
        return salida2;
    }

    public void setSalida2(double salida2) {
        this.salida2 = salida2;
    }

    public double getSalida3() {
        return salida3;
    }

    public void setSalida3(double salida3) {
        this.salida3 = salida3;
    }

    public double getSalida4() {
        return salida4;
    }

    public void setSalida4(double salida4) {
        this.salida4 = salida4;
    }

    public double getSalida5() {
        return salida5;
    }

    public void setSalida5(double salida5) {
        this.salida5 = salida5;
    }

    public double getSalida6() {
        return salida6;
    }

    public void setSalida6(double salida6) {
        this.salida6 = salida6;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
