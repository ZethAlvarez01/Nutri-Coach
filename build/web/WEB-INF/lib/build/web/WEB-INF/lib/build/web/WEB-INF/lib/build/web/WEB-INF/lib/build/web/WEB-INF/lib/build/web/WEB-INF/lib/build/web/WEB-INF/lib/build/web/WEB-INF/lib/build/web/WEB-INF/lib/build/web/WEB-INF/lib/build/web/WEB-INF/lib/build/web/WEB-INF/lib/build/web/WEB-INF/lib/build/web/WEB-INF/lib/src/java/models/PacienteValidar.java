package models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Zeth
 */
public class PacienteValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Paciente.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Paciente paciente=(Paciente)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_boleta", "required.no_boleta");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre",
                                          "Tu nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_uno", "required.ap_uno",
                                          "Tu primer apellido es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sexo", "required.sexo",
                                          "Tu genero es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required.telefono",
                                          "Ingresa un número de contacto");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha_n", "required.fecha_n",
                                          "Tu fecha de nacimiento es obligatoria");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "domicilio", "required.domicilio",
                                          "Tu domicilio es obligatori0");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo",
                                          "Tu correo es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña", "required.contraseña",
                                          "Definir una contraseña es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña2", "required.contraseña2",
                                          "Repetir tu contraseña es obligatorio");
        
        if(!paciente.getContraseña().equals(paciente.getContraseña2())){
            errors.rejectValue("contraseña", "contraseña.incorrect","Las contraseñas no coinciden");
        }
        
    }
    
}
