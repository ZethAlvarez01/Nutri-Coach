package models;

/**
 *
 * @author Zeth
 */
public class Administrador {
    private String no_empleado;
    private String nombre;
    private String ap_uno;
    private String ap_dos;
    private String cargo;
    private String telefono;
    private String correo;
    private String contraseña;

    public Administrador() {
    }

    public Administrador(String no_empleado, String nombre, String ap_uno, String ap_dos, String cargo, String telefono, String correo, String contraseña) {
        this.no_empleado = no_empleado;
        this.nombre = nombre;
        this.ap_uno = ap_uno;
        this.ap_dos = ap_dos;
        this.cargo = cargo;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
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

    public String getAp_uno() {
        return ap_uno;
    }

    public void setAp_uno(String ap_uno) {
        this.ap_uno = ap_uno;
    }

    public String getAp_dos() {
        return ap_dos;
    }

    public void setAp_dos(String ap_dos) {
        this.ap_dos = ap_dos;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    
    
}
