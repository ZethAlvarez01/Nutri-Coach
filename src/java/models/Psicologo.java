package models;

/**
 *
 * @author Zeth
 */
public class Psicologo {

    private String no_cedula;
    private String no_empleado;
    private String nombre;
    private String ap_uno;
    private String ap_dos;
    private String institucion;
    private String telefono;
    private String consultorio;
    private String correo;
    private String contraseña;
    private String contraseña2;
    private String horaEntrada;
    private String horaSalida;
    private String estatus;

    public Psicologo() {
    }

    public Psicologo(String no_cedula, String no_empleado, String nombre, String ap_uno, String ap_dos, String institucion, String telefono, String consultorio, String correo, String contraseña, String contraseña2, String horaEntrada, String horaSalida, String estatus) {
        this.no_cedula = no_cedula;
        this.no_empleado = no_empleado;
        this.nombre = nombre;
        this.ap_uno = ap_uno;
        this.ap_dos = ap_dos;
        this.institucion = institucion;
        this.telefono = telefono;
        this.consultorio = consultorio;
        this.correo = correo;
        this.contraseña = contraseña;
        this.contraseña2 = contraseña2;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.estatus = estatus;
    }

    public String getNo_cedula() {
        return no_cedula;
    }

    public void setNo_cedula(String no_cedula) {
        this.no_cedula = no_cedula;
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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
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
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

  
}
