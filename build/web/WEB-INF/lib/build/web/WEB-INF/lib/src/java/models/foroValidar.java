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
public class foroValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return entradaForo.class.isAssignableFrom(type);
    }

    @Override
       public void validate(Object o, Errors errors) {
        entradaForo foro=(entradaForo)o;
        
        
        
        
        //Se valida que cada elemento de nuestro formulario no se encuentre en blanco
        
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo","required.titulo","El título  es oligatorio");
        System.out.println("NO ENCONTRE EL TITULO");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contenido","required.contenido","El contenido es obligatorio");
        System.out.println("NO ENCONTRE EL CONTENIDO");
        
  



    }
    
}
