/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Nutri-Coach
 */
public class preferenciasValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Preferencias.class.isAssignableFrom(type);
    }

    @Override
       public void validate(Object o, Errors errors) {
        Preferencias preferencias=(Preferencias)o;
        
        
        
        
        //Se valida que cada elemento de nuestro formulario no se encuentre en blanco
        
        
       if(preferencias.getSuplementos()==null && preferencias.getMotivacional()==null && preferencias.getPreparacionA()==null && preferencias.getBeneficiosA()==null && preferencias.getDeportes()==null && preferencias.getMedicamentos()==null && preferencias.getSalud()==null ){
          errors.rejectValue("suplementos", "suplementos","Debes seleccionar al menos una opción");  
       }
        
  



    }
    
}
