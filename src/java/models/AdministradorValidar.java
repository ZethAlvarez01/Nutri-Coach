package models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Zeth
 */
public class AdministradorValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Administrador.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Administrador administrador=(Administrador)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_empleado", "required.no_empleado",
                                          "Tu no. de empleado es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre",
                                          "Tu nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_uno", "required.ap_uno",
                                          "Tu primer apellido es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cargo", "required.cargo",
                                          "Tu cargo institucional es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required.telefono",
                                          "Ingresa un número de contacto");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo",
                                          "Tu correo es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña", "required.contraseña",
                                          "Definir una contraseña es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña2", "required.contraseña2",
                                          "Repetir tu contraseña es obligatorio");
        
        if(!administrador.getContraseña().equals(administrador.getContraseña2())){
            errors.rejectValue("contraseña", "contraseña.incorrect","Las contraseñas no coinciden");
        }
        
    }
    
}
