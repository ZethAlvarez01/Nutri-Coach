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
public class cambiarCValidar implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return cambiarC.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        cambiarC cambiarC = (cambiarC) o;

        int minuscula1 = 0; // BANDERA PARA VALIDAR MINUSCULAS DE CONTRASEÑA 1
        int mayuscula1 = 0;  // BANDERA PARA VALIDAR MAYUSCULAS DE CONTRASEÑA 1
        int numero1 = 0;    // BANDERA PARA VALIDAR NUMEROS DE CONTRASEÑA 1
        int minuscula2 = 0;  // BANDERA PARA VALDIAR MINUSCULAS DE CONTRASEÑA 2
        int mayuscula2 = 0;  // BANDERA PARA VALDIAR MAYUSCULAS DE CONTRASEÑA 2
        int numero2 = 0;     // BANDERA PARA VALIDAR NUMEROS DE CONTRASEÑA 2

        char clave1;      // VARIABLE PARA VALIDACIÓN DE CONTRASEÑA 1
        char clave2;      // VARIABLE PARA VALIDACIÓN DE CONTRASEÑA 2
        String con1;      // VARIABLE PARA VALIDACIÓN DE CONTRASEÑA 1
        String con2;      // VARIABLE PARA VALIDACIÓN DE CONTRASEÑA 2

        //Se valida que cada elemento de nuestro formulario no se encuentre en blanco
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña", "required.contraseña", "Definir una contraseña es obligatorio");
        System.out.println("NO ENCONTRE contraseña");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña2", "required.contraseña2", "Repetir tu contraseña es obligatorio");
        System.out.println("NO ENCONTRE contraseña2");

        // Validación de contraseñas 
        //SE VALIDA QUE LAS CONTRASEÑAS NO LLEGUEN VACIAS
        if (!cambiarC.getContraseña().equals("") && !cambiarC.getContraseña2().equals("")) {

            if (cambiarC.getContraseña().length() > 7) { // LA CONTRASEÑA DEBE CONTENER MAS DE 7 CARACTERES
                System.out.println("LA CONTRASEÑA 1 TIENE MAS DE 7 CARACTERES");
                for (int i = 0; i <= cambiarC.getContraseña().length() - 1; i++) { // SE REALIZA UN RECORRIDO DESDE 0 HASTA LA LONGITUD DE LA CONTRASEÑA -1
                    clave1 = cambiarC.getContraseña().charAt(i);            // SE ASIGNA EL CARACTER CON EL INDICE i 
                    con1 = String.valueOf(clave1);                          // CONVERSIÓN DEL CARACTER EN STRING

                    if (con1.matches("[0-9]")) {                            // SE VERIFICA QUE EL STRING CONTENGA UN NUMERO 
                        System.out.println("Contiene numeros");
                        numero1++;                                        // AUMENTA EL VALOR DEL CONTADOR DE NUMEROS
                    } else if (con1.matches("[a-zñ]")) {
                        System.out.println("Contiene minusculas");       // SE VERIFICA QUE EL STRING CONTENGA UNA LETRA MINUSCULA
                        minuscula1++;                                   // AUMENTA EL VALRO DEL CONTADOR DE MINUSCULAS
                    } else if (con1.matches("[A-ZÑ]")) {                   // SE VERIFICA QUE EL STRING CONTENGA UNA LETRA MAYUSCULA
                        System.out.println("Contiene mayusculas");
                        mayuscula1++;                                   // AUMENTA EL VALOR DEL CONTADOR DE MAYUSCULAS
                    }

                }

                if (minuscula1 >= 1 && mayuscula1 >= 1 && numero1 >= 1) {      // SE VERIFICA QUE LA CONTRASEÑA CONTENGA AL MENOS UNA MINUSCULA, UNA MAYUSCULA Y UN NÚMERO

                    if (cambiarC.getContraseña2().length() > 7) {           // LA CONTRASEÑA DEBE CONTENER MAS DE 7 CARACTERES

                        System.out.println("LA CONTRASEÑA 2 TIENE MAS DE 7 CARACTERES");
                        for (int i = 0; i <= cambiarC.getContraseña2().length() - 1; i++) {    // SE REALIZA UN RECORRIDO DESDE 0 HASTA LA LONGITUD DE LA CONTRASEÑA -1
                            clave2 = cambiarC.getContraseña2().charAt(i);               // SE ASIGNA EL CARACTER CON EL INDICE i
                            con2 = String.valueOf(clave2);                               // CONVERSIÓN DEL CARACTER EN STRING

                            if (con2.matches("[0-9]")) {                                 // SE VERIFICA QUE EL STRING CONTENGA UN NUMERO 
                                System.out.println("Contiene numeros");
                                numero2++;                                            // AUMENTA EL VALOR DEL CONTADOR DE NUMEROS
                            } else if (con2.matches("[a-zñ]")) {                         // SE VERIFICA QUE EL STRING CONTENGA UNA LETRA MINUSCULA
                                System.out.println("Contiene minusculas");
                                minuscula2++;                                         // AUMENTA EL VALOR DEL CONTADOR DE MINUSCULAS
                            } else if (con2.matches("[A-ZÑ]")) {                       // SE VERIFICA QUE EL STRING CONTENGA UNA LETRA MAYUSCULA
                                System.out.println("Contiene mayusculas");
                                mayuscula2++;                                        // AUMENTA EL VALOR DEL CONTADOR DE MAYUSCULAS
                            }

                        }

                        if (minuscula2 >= 1 && mayuscula2 >= 1 && numero2 >= 1) {    // SE VERIFICA QUE LA CONTRASEÑA CONTENGA AL MENOS UNA MINUSCULA, UNA MAYUSCULA Y UN NÚMERO
                            if (cambiarC.getContraseña().equals(cambiarC.getContraseña2())) {  // SE COMPARA QUE LAS CONTRASEÑAS SEAN IGUALES
                                System.out.println("Las contraseñas son iguales");
                            } else {
                                errors.rejectValue("contraseña", "contraseña.incorrect", "Las contraseñas no coinciden"); //EN CASO DE NO SER IGUALES SE NOTIFICA EL ERROR
                                System.out.println("Las contraseñas no coinciden");
                            }
                        } else {
                            errors.rejectValue("contraseña2", "contraseña2.incorrect", "Las contraseña debe de contener al menos una letra minuscula, una mayuscula y un número");   // NOTIFICACIÓN DE ERROR
                        }

                    } else {
                        errors.rejectValue("contraseña2", "contraseña2.incorrect", "Las contraseñas deben contener al menos 8 caracteres");   //NOTIFICACIÓN DE ERROR
                    }

                } else {
                    errors.rejectValue("contraseña", "contraseña.incorrect", "Las contraseña debe de contener al menos una letra minuscula, una mayuscula y un número");   //NOTIFICACIÓN DE ERROR
                }

            } else {
                errors.rejectValue("contraseña", "contraseña.incorrect", "Las contraseñas deben contener al menos 8 caracteres"); // NOTIFICACIÓN DE ERROR
            }

        }

    }

}
