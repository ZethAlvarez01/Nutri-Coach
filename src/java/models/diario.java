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
public class diario {
     // CAMPOS QUE COMPONEN EL FORMULARIO DE MENSAJE
    
    private String id_hojas;
    private String id_diario;
    private String fecha;
    private String contenido;
    private String sentimiento;
    private String observaciones;

    public diario() {
    }

    public diario(String id_hojas, String id_diario, String fecha, String contenido, String sentimiento, String observaciones) {
        this.id_hojas = id_hojas;
        this.id_diario = id_diario;
        this.fecha = fecha;
        this.contenido = contenido;
        this.sentimiento = sentimiento;
        this.observaciones = observaciones; 
    }

    public String getId_hojas() {
        return id_hojas;
    }

    public void setId_hojas(String id_hojas) {
        this.id_hojas = id_hojas;
    }

    public String getId_diario() {
        return id_diario;
    }

    public void setId_diario(String id_diario) {
        this.id_diario = id_diario;
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

    public String getSentimiento() {
        return sentimiento;
    }

    public void setSentimiento(String sentimiento) {
        this.sentimiento = sentimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
}
