package models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Nutri-Coach
 */
public class LoginValidar implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Login.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Login login = (Login) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Pass", "required.Pass",
                "La contraseña es obligatoria");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Usuario", "required.Usuario", "Tu Usuario es obligatorio");
        System.out.println("NO ENCONTRE EL USUARIO");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Pass", "required.Pass", "La contraseña es obligatoria");
        System.out.println("NO ENCONTRE LA CONTRASEÑA");

        ///////////////////////VALIDACIÓN DOMICILIO
        if (!login.getUsuario().equals("")) {
            int invalido = 0;
            for (int i = 0; i < login.getUsuario().length(); i++) {
                if (login.getUsuario().charAt(i) == '\'') {
                    System.out.println("Se encontro una comilla");
                    invalido = 1;
                }
                if (login.getUsuario().charAt(i) == '\"') {
                    System.out.println("Se encontro una comilla doble");
                    invalido = 1;
                }
                if (login.getUsuario().charAt(i) == '|') {
                    System.out.println("Se encontro un pipe");
                    invalido = 1;
                }
            }

            if (invalido == 1) {
                errors.rejectValue("Usuario", "Usuario.incorrect", "El usuario es inválido"); //Si ninguna condición se cumple el telefono es invalido

            }
        }

        ///////////////////////VALIDACIÓN DOMICILIO
        if (!login.getPass().equals("")) {
            int invalido = 0;
            for (int i = 0; i < login.getPass().length(); i++) {
                if (login.getPass().charAt(i) == '\'') {
                    System.out.println("Se encontro una comilla");
                    invalido = 1;
                }
                if (login.getPass().charAt(i) == '\"') {
                    System.out.println("Se encontro una comilla doble");
                    invalido = 1;
                }
                if (login.getPass().charAt(i) == '|') {
                    System.out.println("Se encontro un pipe");
                    invalido = 1;
                }
            }

            if (invalido == 1) {
                errors.rejectValue("Pass", "Pass.incorrect", "El password es inválido"); //Si ninguna condición se cumple el telefono es invalido

            }
        }

    }

}
