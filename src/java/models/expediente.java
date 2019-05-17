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
public class expediente {
    private String id_expediente;
    private String no_boleta;
    private String diagnostico;
    private String pronostico;
    private String fecha_ini;
    private String motivacional;
    private String prepacionA;
    private String beneficiosA;
    private String deportes;
    private String medicamentos;
    private String salud;
    private String antec_hf;
    private String act_f;
    private String tipo_act;
    private String frecuencia;
    private String padecimiento;
    private String tabaco;
    private String frec_tabaco;
    private String alcohol;
    private String frec_alcohol;
    private String tratamient;
    private String tiempo;
    private String motivo;
    private String hora;
    private String alergias;
    private String postre;
    private String ansiedad;
    private String depresion;
    private String ira;
    private String estres;
    private String felicidad;
    private String dulce;
    private String amarga;
    private String salada;
    private String picante;
    private String acida;
    private String act_sex;
    private String edo_gestacion;
    private String m_anticonceptivo;
    private String terapia_rh;
    private String dosis;
    private String peso;
    private String talla;
    private String temperatura;
    private String tension_art;
    private String fec_cardiaca;
    private String frec_respiratoria;
    private String cabeza;
    private String cuello;
    private String brazo;
    private String cadera;
    private String torax;
    private String antebrazo;
    private String abdomen;
    private String mulso;
    private String pierna;
    private String aspect_grls;
    private String otras_medidas;
    private String T_Gestacion;
    private String TipoTerapia;
    private String CantidadIngesta;
    private String HorarioComida;
    private String HorariosComida;
    private String recomendaciones;
    private String observaciones;
    private String tratamiento_n;

    public expediente() {
    }

    public expediente(String id_expediente, String no_boleta, String diagnostico, String pronostico, String fecha_ini, String motivacional, String prepacionA, String beneficiosA, String deportes, String medicamentos, String salud, String antec_hf, String act_f, String tipo_act, String frecuencia, String padecimiento, String tabaco, String frec_tabaco, String alcohol, String frec_alcohol, String tratamient, String tiempo, String motivo, String hora, String alergias, String postre, String ansiedad, String depresion, String ira, String estres, String felicidad, String dulce, String amarga, String salada, String picante, String acida, String act_sex, String edo_gestacion, String m_anticonceptivo, String terapia_rh, String dosis, String peso, String talla, String temperatura, String tension_art, String fec_cardiaca, String frec_respiratoria, String cabeza, String cuello, String brazo, String cadera, String torax, String antebrazo, String abdomen, String mulso, String pierna, String aspect_grls, String otras_medidas, String T_Gestacion, String TipoTerapia, String CantidadIngesta, String HorarioComida, String HorariosComida, String recomendaciones, String observaciones, String tratamiento_n) {
        this.id_expediente = id_expediente;
        this.no_boleta = no_boleta;
        this.diagnostico = diagnostico;
        this.pronostico = pronostico;
        this.fecha_ini = fecha_ini;
        this.motivacional = motivacional;
        this.prepacionA = prepacionA;
        this.beneficiosA = beneficiosA;
        this.deportes = deportes;
        this.medicamentos = medicamentos;
        this.salud = salud;
        this.antec_hf = antec_hf;
        this.act_f = act_f;
        this.tipo_act = tipo_act;
        this.frecuencia = frecuencia;
        this.padecimiento = padecimiento;
        this.tabaco = tabaco;
        this.frec_tabaco = frec_tabaco;
        this.alcohol = alcohol;
        this.frec_alcohol = frec_alcohol;
        this.tratamient = tratamient;
        this.tiempo = tiempo;
        this.motivo = motivo;
        this.hora = hora;
        this.alergias = alergias;
        this.postre = postre;
        this.ansiedad = ansiedad;
        this.depresion = depresion;
        this.ira = ira;
        this.estres = estres;
        this.felicidad = felicidad;
        this.dulce = dulce;
        this.amarga = amarga;
        this.salada = salada;
        this.picante = picante;
        this.acida = acida;
        this.act_sex = act_sex;
        this.edo_gestacion = edo_gestacion;
        this.m_anticonceptivo = m_anticonceptivo;
        this.terapia_rh = terapia_rh;
        this.dosis = dosis;
        this.peso = peso;
        this.talla = talla;
        this.temperatura = temperatura;
        this.tension_art = tension_art;
        this.fec_cardiaca = fec_cardiaca;
        this.frec_respiratoria = frec_respiratoria;
        this.cabeza = cabeza;
        this.cuello = cuello;
        this.brazo = brazo;
        this.cadera = cadera;
        this.torax = torax;
        this.antebrazo = antebrazo;
        this.abdomen = abdomen;
        this.mulso = mulso;
        this.pierna = pierna;
        this.aspect_grls = aspect_grls;
        this.otras_medidas = otras_medidas;
        this.T_Gestacion = T_Gestacion;
        this.TipoTerapia = TipoTerapia;
        this.CantidadIngesta = CantidadIngesta;
        this.HorarioComida = HorarioComida;
        this.HorariosComida = HorariosComida;
        this.recomendaciones = recomendaciones;
        this.observaciones = observaciones;
        this.tratamiento_n = tratamiento_n;
    }

    public String getId_expediente() {
        return id_expediente;
    }

    public void setId_expediente(String id_expediente) {
        this.id_expediente = id_expediente;
    }

    public String getNo_boleta() {
        return no_boleta;
    }

    public void setNo_boleta(String no_boleta) {
        this.no_boleta = no_boleta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getMotivacional() {
        return motivacional;
    }

    public void setMotivacional(String motivacional) {
        this.motivacional = motivacional;
    }

    public String getPrepacionA() {
        return prepacionA;
    }

    public void setPrepacionA(String prepacionA) {
        this.prepacionA = prepacionA;
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

    public String getAntec_hf() {
        return antec_hf;
    }

    public void setAntec_hf(String antec_hf) {
        this.antec_hf = antec_hf;
    }

    public String getAct_f() {
        return act_f;
    }

    public void setAct_f(String act_f) {
        this.act_f = act_f;
    }

    public String getTipo_act() {
        return tipo_act;
    }

    public void setTipo_act(String tipo_act) {
        this.tipo_act = tipo_act;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String getTabaco() {
        return tabaco;
    }

    public void setTabaco(String tabaco) {
        this.tabaco = tabaco;
    }

    public String getFrec_tabaco() {
        return frec_tabaco;
    }

    public void setFrec_tabaco(String frec_tabaco) {
        this.frec_tabaco = frec_tabaco;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getFrec_alcohol() {
        return frec_alcohol;
    }

    public void setFrec_alcohol(String frec_alcohol) {
        this.frec_alcohol = frec_alcohol;
    }

    public String getTratamient() {
        return tratamient;
    }

    public void setTratamient(String tratamient) {
        this.tratamient = tratamient;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
    }

    public String getAnsiedad() {
        return ansiedad;
    }

    public void setAnsiedad(String ansiedad) {
        this.ansiedad = ansiedad;
    }

    public String getDepresion() {
        return depresion;
    }

    public void setDepresion(String depresion) {
        this.depresion = depresion;
    }

    public String getIra() {
        return ira;
    }

    public void setIra(String ira) {
        this.ira = ira;
    }

    public String getEstres() {
        return estres;
    }

    public void setEstres(String estres) {
        this.estres = estres;
    }

    public String getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(String felicidad) {
        this.felicidad = felicidad;
    }

    public String getDulce() {
        return dulce;
    }

    public void setDulce(String dulce) {
        this.dulce = dulce;
    }

    public String getAmarga() {
        return amarga;
    }

    public void setAmarga(String amarga) {
        this.amarga = amarga;
    }

    public String getSalada() {
        return salada;
    }

    public void setSalada(String salada) {
        this.salada = salada;
    }

    public String getPicante() {
        return picante;
    }

    public void setPicante(String picante) {
        this.picante = picante;
    }

    public String getAcida() {
        return acida;
    }

    public void setAcida(String acida) {
        this.acida = acida;
    }

    public String getAct_sex() {
        return act_sex;
    }

    public void setAct_sex(String act_sex) {
        this.act_sex = act_sex;
    }

    public String getEdo_gestacion() {
        return edo_gestacion;
    }

    public void setEdo_gestacion(String edo_gestacion) {
        this.edo_gestacion = edo_gestacion;
    }

    public String getM_anticonceptivo() {
        return m_anticonceptivo;
    }

    public void setM_anticonceptivo(String m_anticonceptivo) {
        this.m_anticonceptivo = m_anticonceptivo;
    }

    public String getTerapia_rh() {
        return terapia_rh;
    }

    public void setTerapia_rh(String terapia_rh) {
        this.terapia_rh = terapia_rh;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getTension_art() {
        return tension_art;
    }

    public void setTension_art(String tension_art) {
        this.tension_art = tension_art;
    }

    public String getFec_cardiaca() {
        return fec_cardiaca;
    }

    public void setFec_cardiaca(String fec_cardiaca) {
        this.fec_cardiaca = fec_cardiaca;
    }

    public String getFrec_respiratoria() {
        return frec_respiratoria;
    }

    public void setFrec_respiratoria(String frec_respiratoria) {
        this.frec_respiratoria = frec_respiratoria;
    }

    public String getCabeza() {
        return cabeza;
    }

    public void setCabeza(String cabeza) {
        this.cabeza = cabeza;
    }

    public String getCuello() {
        return cuello;
    }

    public void setCuello(String cuello) {
        this.cuello = cuello;
    }

    public String getBrazo() {
        return brazo;
    }

    public void setBrazo(String brazo) {
        this.brazo = brazo;
    }

    public String getCadera() {
        return cadera;
    }

    public void setCadera(String cadera) {
        this.cadera = cadera;
    }

    public String getTorax() {
        return torax;
    }

    public void setTorax(String torax) {
        this.torax = torax;
    }

    public String getAntebrazo() {
        return antebrazo;
    }

    public void setAntebrazo(String antebrazo) {
        this.antebrazo = antebrazo;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getMulso() {
        return mulso;
    }

    public void setMulso(String mulso) {
        this.mulso = mulso;
    }

    public String getPierna() {
        return pierna;
    }

    public void setPierna(String pierna) {
        this.pierna = pierna;
    }

    public String getAspect_grls() {
        return aspect_grls;
    }

    public void setAspect_grls(String aspect_grls) {
        this.aspect_grls = aspect_grls;
    }

    public String getOtras_medidas() {
        return otras_medidas;
    }

    public void setOtras_medidas(String otras_medidas) {
        this.otras_medidas = otras_medidas;
    }

    public String getT_Gestacion() {
        return T_Gestacion;
    }

    public void setT_Gestacion(String T_Gestacion) {
        this.T_Gestacion = T_Gestacion;
    }

    public String getTipoTerapia() {
        return TipoTerapia;
    }

    public void setTipoTerapia(String TipoTerapia) {
        this.TipoTerapia = TipoTerapia;
    }

    public String getCantidadIngesta() {
        return CantidadIngesta;
    }

    public void setCantidadIngesta(String CantidadIngesta) {
        this.CantidadIngesta = CantidadIngesta;
    }

    public String getHorarioComida() {
        return HorarioComida;
    }

    public void setHorarioComida(String HorarioComida) {
        this.HorarioComida = HorarioComida;
    }

    public String getHorariosComida() {
        return HorariosComida;
    }

    public void setHorariosComida(String HorariosComida) {
        this.HorariosComida = HorariosComida;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTratamiento_n() {
        return tratamiento_n;
    }

    public void setTratamiento_n(String tratamiento_n) {
        this.tratamiento_n = tratamiento_n;
    }

    
  
}
