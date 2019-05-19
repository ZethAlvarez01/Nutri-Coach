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
public class expedientePsicologico {
    private String id_hojaPsicologo;
    private String id_expediente;
    private String fecha;
    private String no_boleta;
    private String no_cedula;
    private String contenido;

    public expedientePsicologico() {
    }

    public expedientePsicologico(String id_hojaPsicologo, String id_expediente, String fecha, String no_boleta, String no_cedula, String contenido) {
        this.id_hojaPsicologo = id_hojaPsicologo;
        this.id_expediente = id_expediente;
        this.fecha = fecha;
        this.no_boleta = no_boleta;
        this.no_cedula = no_cedula;
        this.contenido = contenido;
    }

    public String getId_hojaPsicologo() {
        return id_hojaPsicologo;
    }

    public void setId_hojaPsicologo(String id_hojaPsicologo) {
        this.id_hojaPsicologo = id_hojaPsicologo;
    }

    public String getId_expediente() {
        return id_expediente;
    }

    public void setId_expediente(String id_expediente) {
        this.id_expediente = id_expediente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

   

   
}
