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
public class RecuperarValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
       return Recuperar.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
        Recuperar recuperar=(Recuperar)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo",
                                          "Tu correo es obligatorio");
    }
    
}
