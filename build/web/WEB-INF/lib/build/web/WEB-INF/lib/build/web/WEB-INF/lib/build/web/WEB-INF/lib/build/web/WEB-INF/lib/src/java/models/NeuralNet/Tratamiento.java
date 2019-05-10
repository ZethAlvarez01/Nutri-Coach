package models.NeuralNet;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Zeth
 */
public class Tratamiento {
    int edad;
    String sexo;
    int peso;
    int altura;
    String ansiedad;
    String depresion;
    String ira;
    String estres;
    String feliz;
    String dulce;
    String amarga;
    String salada;
    String picante;
    String acida;
    String actividad;
    String suplementos;
    String motivacion;
    String preparar;
    String beneficios;
    String deportes;
    String medicamentos;
    String salud;

    public Tratamiento(String edad, String sexo, String peso, String altura, String ansiedad, String depresion, String ira, String estres, String feliz, String dulce, String amarga, String salada, String picante, String acida, String actividad, String suplementos, String motivacion, String preparar, String beneficios, String deportes, String medicamentos, String salud) {
        this.edad=Integer.parseInt(edad);
        this.sexo = sexo;
        this.peso =Integer.parseInt(peso);
        this.altura =Integer.parseInt(altura);
        this.ansiedad = ansiedad;
        this.depresion = depresion;
        this.ira = ira;
        this.estres = estres;
        this.feliz = feliz;
        this.dulce = dulce;
        this.amarga = amarga;
        this.salada = salada;
        this.picante = picante;
        this.acida = acida;
        this.actividad = actividad;
        this.suplementos = suplementos;
        this.motivacion = motivacion;
        this.preparar = preparar;
        this.beneficios = beneficios;
        this.deportes = deportes;
        this.medicamentos = medicamentos;
        this.salud = salud;
    }
    
    public double[] vector(){
        double[] vector=new double[42];
        ArrayList<Integer> binario;
        ArrayList<Integer> aux;
       
        binario=binario(edad,0);
        
        switch(sexo){
            case "H":
                binario.add(0);
                binario.add(1);
                break;
            case "M":
                binario.add(1);
                binario.add(0);
                break;
            case "O":
                binario.add(1);
                binario.add(1);
                break;
        }

        aux=binario(peso,0);
        binario.addAll(aux);        
        aux=binario(altura,1);
        binario.addAll(aux);
        
        if(ansiedad.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(depresion.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(ira.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(estres.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(feliz.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(dulce.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(amarga.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(salada.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(picante.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(acida.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }

        if(actividad.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(suplementos.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(motivacion.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(preparar.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(beneficios.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(deportes.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        if(medicamentos.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
       
        if(salud.equals("true")){
            binario.add(1);
        }else{
            binario.add(0);
        }
        
        System.out.println(binario);
        System.out.println(binario.size());
        
        for(int i=0;i<binario.size();i++){
            vector[i]=binario.get(i);
        }
        
        return vector;
    }
    
    public ArrayList<String> seleccion(double[] salida){
        ArrayList<String> tipo=new ArrayList<>();
        
        for(int i=0;i<salida.length;i++){
            if(salida[i]>0.5){
                switch(i){
                    case 0:
                        tipo.add("motivacional");
                        break;
                    case 1:
                        tipo.add("preparacionA");
                        break;
                    case 2:
                        tipo.add("beneficiosA");
                        break;
                    case 3:
                        tipo.add("deportes");
                        break;
                    case 4:
                        tipo.add("medicamentos");
                        break;
                    case 5:
                        tipo.add("salud");
                        break;
                }
            }
        }   
        return tipo;
    }
    
    ArrayList<Integer> binario(int numero,int flg){
        ArrayList<Integer> binario=new ArrayList<>();
        int digito=0;
        while(numero!=0){
            digito=numero%2;
            numero=numero/2;
            binario.add(digito);
        }
        
        switch(flg){
            case 0:
                while(binario.size()!=7){
                    binario.add(0);
                }
                break;
            case 1:
                while(binario.size()!=8){
                    binario.add(0);
                }
                break;
        }
        
        Collections.reverse(binario);
        
        return binario;
    }
}
