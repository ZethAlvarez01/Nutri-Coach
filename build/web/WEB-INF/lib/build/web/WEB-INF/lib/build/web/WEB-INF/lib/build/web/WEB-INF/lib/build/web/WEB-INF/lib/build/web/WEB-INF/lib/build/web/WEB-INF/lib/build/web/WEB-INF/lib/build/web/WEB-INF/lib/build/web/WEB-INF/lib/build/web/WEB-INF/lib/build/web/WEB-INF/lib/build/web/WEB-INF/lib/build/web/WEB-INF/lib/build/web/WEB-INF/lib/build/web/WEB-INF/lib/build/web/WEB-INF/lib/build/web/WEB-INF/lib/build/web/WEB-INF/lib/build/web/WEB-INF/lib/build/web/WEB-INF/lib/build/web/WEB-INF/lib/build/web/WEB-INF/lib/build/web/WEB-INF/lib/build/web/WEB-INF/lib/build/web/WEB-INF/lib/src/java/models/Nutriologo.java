package models;
/**
 *
 * @author zetok
 */
public class Nutriologo {
    private String No_Cedula_P;
    private String No_Empleado;
    private String Nombre;
    private String Apellido_P;
    private String Apellido_M;
    private String Institucion;
    private String Telefono;
    private String Domicilio_C;
    private String Correo;
    private String Contrasena;

    public Nutriologo() {
    }

    public Nutriologo(String No_Cedula_P, String No_Empleado, String Nombre, String Apellido_P, String Apellido_M, String Institucion, String Telefono, String Domicilio_C, String Correo, String Contrasena) {
        this.No_Cedula_P = No_Cedula_P;
        this.No_Empleado = No_Empleado;
        this.Nombre = Nombre;
        this.Apellido_P = Apellido_P;
        this.Apellido_M = Apellido_M;
        this.Institucion = Institucion;
        this.Telefono = Telefono;
        this.Domicilio_C = Domicilio_C;
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

    public String getInstitucion() {
        return Institucion;
    }

    public void setInstitucion(String Institucion) {
        this.Institucion = Institucion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDomicilio_C() {
        return Domicilio_C;
    }

    public void setDomicilio_C(String Domicilio_C) {
        this.Domicilio_C = Domicilio_C;
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
