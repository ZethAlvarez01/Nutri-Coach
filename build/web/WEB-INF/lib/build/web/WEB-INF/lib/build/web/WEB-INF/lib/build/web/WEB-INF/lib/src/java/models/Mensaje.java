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
public class Mensaje {
    // CAMPOS QUE COMPONEN EL FORMULARIO DE MENSAJE
    
    private String id_mensaje;
    private String id_usuario_emisor;
    private String id_usuario_receptor;
    private String fecha;
    private String hora;
    private String contenido;

    public Mensaje() {
    }

    public Mensaje(String id_mensaje, String id_usuario_emisor, String id_usuario_receptor, String fecha, String hora, String contenido) {
        this.id_mensaje = id_mensaje;
        this.id_usuario_emisor = id_usuario_emisor;
        this.id_usuario_receptor = id_usuario_receptor;
        this.fecha = fecha;
        this.hora = hora;
        this.contenido = contenido;
    }

    public String getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(String id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public String getId_usuario_emisor() {
        return id_usuario_emisor;
    }

    public void setId_usuario_emisor(String id_usuario_emisor) {
        this.id_usuario_emisor = id_usuario_emisor;
    }

    public String getId_usuario_receptor() {
        return id_usuario_receptor;
    }

    public void setId_usuario_receptor(String id_usuario_receptor) {
        this.id_usuario_receptor = id_usuario_receptor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
   
}
