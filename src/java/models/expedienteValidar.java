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
public class expedienteValidar implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return expediente.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        expediente expediente = (expediente) o;

        //Se valida que cada elemento de nuestro formulario no se encuentre en blanco
     
        
        if (expediente.getAmarga() == null && expediente.getAcida()== null && expediente.getDulce() == null && expediente.getSalada() ==null && expediente.getPicante()==null) {
            errors.rejectValue("picante", "picante", "Debes seleccionar al menos una opción");
        }

    }

}
