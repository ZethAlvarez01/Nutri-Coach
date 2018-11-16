package models;

/**
 *
 * @author Zeth
 */
public class Administrador {
    private String no_empleado;
    private String nombre;
    private String apellido_P;
    private String apellido_M;
    private String cargo;
    private String telefono;
    private String correo;
    private String contrasena;

    public Administrador() {
    }

    public Administrador(String no_empleado, String nombre, String apellido_P, String apellido_M, String cargo, String telefono, String correo, String contrasena) {
        this.no_empleado = no_empleado;
        this.nombre = nombre;
        this.apellido_P = apellido_P;
        this.apellido_M = apellido_M;
        this.cargo = cargo;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getNo_empleado() {
        return no_empleado;
    }

    public void setNo_empleado(String no_empleado) {
        this.no_empleado = no_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_P() {
        return apellido_P;
    }

    public void setApellido_P(String apellido_P) {
        this.apellido_P = apellido_P;
    }

    public String getApellido_M() {
        return apellido_M;
    }

    public void setApellido_M(String apellido_M) {
        this.apellido_M = apellido_M;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
