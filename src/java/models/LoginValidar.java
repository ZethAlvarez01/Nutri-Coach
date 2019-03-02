package models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Zeth
 */
public class LoginValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Login.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Login login=(Login)o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Pass", "required.Pass",
                                          "La contraseña es obligatoria");
       
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Usuario","required.Usuario","Tu Usuario es obligatorio");
        System.out.println("NO ENCONTRE EL USUARIO");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Pass","required.Pass","La contraseña es obligatoria");
        System.out.println("NO ENCONTRE LA CONTRASEÑA");
        
    }
    
}
