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
public class Comentario {
    private String id_comnt;
    private String id_entrada;
    private String id_usuario;
    private String contenido;
    private String multimedia;
    private String fecha;

    public Comentario() {
    }

    public Comentario(String id_comnt, String id_entrada, String id_usuario, String contenido, String multimedia, String fecha) {
        this.id_comnt = id_comnt;
        this.id_entrada = id_entrada;
        this.id_usuario = id_usuario;
        this.contenido = contenido;
        this.multimedia = multimedia;
        this.fecha = fecha;
    }

    public String getId_comnt() {
        return id_comnt;
    }

    public void setId_comnt(String id_comnt) {
        this.id_comnt = id_comnt;
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