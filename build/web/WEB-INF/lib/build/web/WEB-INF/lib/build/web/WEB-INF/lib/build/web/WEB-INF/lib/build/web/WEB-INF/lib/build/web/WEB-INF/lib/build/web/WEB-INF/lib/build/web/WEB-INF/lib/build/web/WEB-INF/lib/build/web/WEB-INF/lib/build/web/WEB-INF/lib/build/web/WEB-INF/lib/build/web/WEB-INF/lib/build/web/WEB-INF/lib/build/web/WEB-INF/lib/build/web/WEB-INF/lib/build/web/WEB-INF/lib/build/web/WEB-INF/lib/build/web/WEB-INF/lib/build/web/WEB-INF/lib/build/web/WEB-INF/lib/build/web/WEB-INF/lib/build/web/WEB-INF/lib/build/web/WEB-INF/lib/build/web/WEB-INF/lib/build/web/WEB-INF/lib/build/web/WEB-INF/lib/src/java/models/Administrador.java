/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author zetok
 */
public class Administrador {
    private String No_Empleado;
    private String Nombre;
    private String Apellido_P;
    private String Apellido_M;
    private String Cargo;
    private String Telefono;
    private String Correo;
    private String Contrasena;

    public Administrador() {
    }

    public Administrador(String No_Empleado, String Nombre, String Apellido_P, String Apellido_M, String Cargo, String Telefono, String Correo, String Contrasena) {
        this.No_Empleado = No_Empleado;
        this.Nombre = Nombre;
        this.Apellido_P = Apellido_P;
        this.Apellido_M = Apellido_M;
        this.Cargo = Cargo;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
    }

    public String getNo_Empleado() {
        return No_Empleado;
    }

    public void setNo_Empleado(String No_Empleado) {
        this.No_Empleado = No_Empleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido_P() {
        return Apellido_P;
    }

    public void setApellido_P(String Apellido_P) {
        this.Apellido_P = Apellido_P;
    }

    public String getApellido_M() {
        return Apellido_M;
    }

    public void setApellido_M(String Apellido_M) {
        this.Apellido_M = Apellido_M;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    

    
}
