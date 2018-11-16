package models;

/**
 *
 * @author Zeth
 */
public class Psicologo {
    private String No_Cedula_P;
    private String No_Empleado;
    private String Nombre;
    private String Apellido_P;
    private String Apellido_M;
    private String Telefono;
    private String Correo;
    private String Contrasena;

    public Psicologo() {
    }

    public Psicologo(String No_Cedula_P, String No_Empleado, String Nombre, String Apellido_P, String Apellido_M, String Telefono, String Correo, String Contrasena) {
        this.No_Cedula_P = No_Cedula_P;
        this.No_Empleado = No_Empleado;
        this.Nombre = Nombre;
        this.Apellido_P = Apellido_P;
        this.Apellido_M = Apellido_M;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
    }

    public String getNo_Cedula_P() {
        return No_Cedula_P;
    }

    public void setNo_Cedula_P(String No_Cedula_P) {
        this.No_Cedula_P = No_Cedula_P;
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
