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
public class ActividadP {
    private String id_actividad;
    private String no_boleta;
    private String no_cedula;
    private String fecha;
    private String contenido;
    private String multimedia;

    public ActividadP() {
    }

    public ActividadP(String id_actividad, String no_boleta, String no_cedula, String fecha, String contenido, String multimedia) {
        this.id_actividad = id_actividad;
        this.no_boleta = no_boleta;
        this.no_cedula = no_cedula;
        this.fecha = fecha;
        this.contenido = contenido;
        this.multimedia = multimedia;
    }

    public String getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(String id_actividad) {
        this.id_actividad = id_actividad;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

  
}