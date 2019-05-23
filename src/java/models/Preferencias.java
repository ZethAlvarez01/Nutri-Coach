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
public class Preferencias {
    private String id_preferencias;
    private String no_boleta; 
    private String suplementos;
    private String motivacional;
    private String preparacionA;
    private String beneficiosA;
    private String deportes;
    private String medicamentos;
    private String salud;

    public Preferencias() {
    }

    public Preferencias(String id_preferencias, String no_boleta, String suplementos, String motivacional, String preparacionA, String beneficiosA, String deportes, String medicamentos, String salud) {
        this.id_preferencias = id_preferencias;
        this.no_boleta = no_boleta;
        this.suplementos = suplementos;
        this.motivacional = motivacional;
        this.preparacionA = preparacionA;
        this.beneficiosA = beneficiosA;
        this.deportes = deportes;
        this.medicamentos = medicamentos;
        this.salud = salud;
    }

    public String getId_preferencias() {
        return id_preferencias;
    }

    public void setId_preferencias(String id_preferencias) {
        this.id_preferencias = id_preferencias;
    }

    public String getNo_boleta() {
        return no_boleta;
    }

    public void setNo_boleta(String no_boleta) {
        this.no_boleta = no_boleta;
    }

    public String getSuplementos() {
        return suplementos;
    }

    public void setSuplementos(String suplementos) {
        this.suplementos = suplementos;
    }

    public String getMotivacional() {
        return motivacional;
    }

    public void setMotivacional(String motivacional) {
        this.motivacional = motivacional;
    }

    public String getPreparacionA() {
        return preparacionA;
    }

    public void setPreparacionA(String preparacionA) {
        this.preparacionA = preparacionA;
    }

    public String getBeneficiosA() {
        return beneficiosA;
    }

    public void setBeneficiosA(String beneficiosA) {
        this.beneficiosA = beneficiosA;
    }

    public String getDeportes() {
        return deportes;
    }

    public void setDeportes(String deportes) {
        this.deportes = deportes;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }
    
    
}
