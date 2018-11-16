package models;
/**
 *
 * @author Zeth
 */
public class Paciente {
    private String No_Boleta;
    private String Nombre;
    private String Apellido_P;
    private String Apellido_M;
    private String Sexo;
    private String Fecha_Nacimiento;
    private String Telefono;
    private String Domicilio;
    private String Correo;
    private String Contrasena;

    public Paciente() {
    }

    public Paciente(String No_Boleta, String Nombre, String Apellido_P, String Apellido_M, String Sexo, String Fecha_Nacimiento, String Telefono, String Domicilio, String Correo, String Contrasena) {
        this.No_Boleta = No_Boleta;
        this.Nombre = Nombre;
        this.Apellido_P = Apellido_P;
        this.Apellido_M = Apellido_M;
        this.Sexo = Sexo;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Telefono = Telefono;
        this.Domicilio = Domicilio;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
    }
    
    

    public String getNo_Boleta() {
        return No_Boleta;
    }

    public void setNo_Boleta(String No_Boleta) {
        this.No_Boleta = No_Boleta;
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

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(String Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
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

    public void setContrasena(String Contraseña) {
        this.Contrasena = Contrasena;
    }

    
    
}
