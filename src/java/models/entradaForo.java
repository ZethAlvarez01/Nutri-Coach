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
public class entradaForo {
    private String id_entrada;
    private String id_usuario;
    private String titulo;
    private String contenido;
    private String multimedia;
    private String fecha;

    public entradaForo() {
    }

    public entradaForo(String id_entrada, String id_usuario, String titulo, String contenido, String multimedia, String fecha) {
        this.id_entrada = id_entrada;
        this.id_usuario = id_usuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.multimedia = multimedia;
        this.fecha = fecha;
    }

    public String getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(String id_entrada) {
        this.id_entrada = id_entrada;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    

}