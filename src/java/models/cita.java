/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jms-m
 */
public class cita {
     // CAMPOS QUE COMPONEN EL FORMULARIO DE MENSAJE
    
    private String no_cita;
    private String no_boleta;
    private String no_cedula;
    private String fecha;
    private String estado;
    private String horario;

    public cita() {
    }

    public cita(String no_cita, String no_boleta, String no_cedula, String fecha, String estado, String horario) {
        this.no_cita = no_cita;
        this.no_boleta = no_boleta;
        this.no_cedula = no_cedula;
        this.fecha = fecha;
        this.estado = estado;
        this.horario = horario;
    }

    public String getNo_cita() {
        return no_cita;
    }

    public void setNo_cita(String no_cita) {
        this.no_cita = no_cita;
    }

    public String getNo_boleta() {
        return no_boleta;
    }

    public void setNo_boleta(String no_boleta) {
        this.no_boleta = no_boleta;
    }

    public String getNo_cedula() {
        return no_cedula;
    }

    public void setNo_cedula(String no_cedula) {
        this.no_cedula = no_cedula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    

}
