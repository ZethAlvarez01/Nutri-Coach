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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Usuario", "required.Usuario",
                                          "El no. de boleta o empleado es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Pass", "required.Pass",
                                          "La contraseña es obligatoria");
    }
    
}
