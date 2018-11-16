package models;
/**
 *
 * @author Zeth
 */
public class Paciente {
    private String no_boleta;
    private String nombre;
    private String ap_uno;
    private String ap_dos;
    private String sexo;
    private String fecha_n;
    private String telefono;
    private String domicilio;
    private String correo;
    private String contraseña;
    private String contraseña2;
    
    public Paciente() {
    }

    public Paciente(String no_boleta, String nombre, String ap_uno, String ap_dos, String sexo, String fecha_n, String telefono, String domicilio, String correo, String contraseña,String contraseña2) {
        this.no_boleta = no_boleta;
        this.nombre = nombre;
        this.ap_uno = ap_uno;
        this.ap_dos = ap_dos;
        this.sexo = sexo;
        this.fecha_n = fecha_n;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.correo = correo;
        this.contraseña = contraseña;
        this.contraseña2 = contraseña2;
    }

    public String getNo_boleta() {
        return no_boleta;
    }

    public void setNo_boleta(String no_boleta) {
        this.no_boleta = no_boleta;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_n() {
        return fecha_n;
    }

    public void setFecha_n(String fecha_n) {
        this.fecha_n = fecha_n;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

     public String getContraseña2() {
        return contraseña;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }
    
}
