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
public class expedientePsicologicoValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return expedientePsicologico.class.isAssignableFrom(type);
    }

    @Override
       public void validate(Object o, Errors errors) {
        expedientePsicologico ex=(expedientePsicologico)o;
        
        
        
        
        //Se valida que cada elemento de nuestro formulario no se encuentre en blanco
        
        
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contenido","required.contenido","El contenido es obligatorio");
        System.out.println("NO ENCONTRE EL CONTENIDO");
        
  



    }
    
}