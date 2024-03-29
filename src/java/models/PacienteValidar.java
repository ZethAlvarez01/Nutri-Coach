package models;

import java.util.Calendar;
import java.util.List;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import models.Conexion;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Nutri-Coach
 */
public class PacienteValidar implements Validator {

    private JdbcTemplate jdbcTemplate;

    public PacienteValidar() {

        Conexion conn = new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos

    }

    @Override
    public boolean supports(Class<?> type) {
        return Paciente.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Paciente paciente = (Paciente) o;

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_boleta", "required.no_boleta", "Tu número de boleta o de trabajador es obligatorio");
        System.out.println("No encontre el numero de boleta");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre", "Tu nombre es obligatorio");
        System.out.println("NO ENCONTRE el nombre");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_uno", "required.ap_uno", "Tu primer apellido es obligatorio");
        System.out.println("NO ENCONTRE ap_uno");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_dos", "required.ap_dos", "Tu segundo apellido es obligatorio");
        System.out.println("NO ENCONTRE ap_dos");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sexo", "required.sexo", "Tu genero es obligatorio");
        System.out.println("NO ENCONTRE sexo");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required.telefono", "El número de contacto es obligatorio");
        System.out.println("NO ENCONTRE telefono");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha_n", "required.fecha_n", "Tu fecha de nacimiento es obligatoria");
        System.out.println("NO ENCONTRE fecha_n");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "domicilio", "required.domicilio", "Tu domicilio es obligatorio");
        System.out.println("NO ENCONTRE domicilio");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo", "Tu correo es obligatorio");
        System.out.println("NO ENCONTRE correo");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña", "required.contraseña", "Definir una contraseña es obligatorio");
        System.out.println("NO ENCONTRE contraseña");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña2", "required.contraseña2", "Repetir tu contraseña es obligatorio");
        System.out.println("NO ENCONTRE contraseña2");

///////////////////////VALIDACIÓN DOMICILIO
        if (!paciente.getDomicilio().equals("")) { // IF 1 DOMICILIO ENTRAMOS SOLO SI EL DOMICILIO NO SE ENCUENTRA VACIO
            int invalido = 0;                         // BANDERA DE CONDICION DE INVALIDEZ
            for (int i = 0; i < paciente.getDomicilio().length(); i++) {      // INICIO FOR 1 
                if (paciente.getDomicilio().charAt(i) == '\'') {            //COMPARAMOS SI EL DOMICILIO CONTIENE EL CARACTER ' IF 2
                    System.out.println("Se encontro una comilla");
                    invalido = 1;                                           // CAMBIAMOS EL VALOR DE NUESTRA BANDERA DE INVALIDEZ
                }                                                           // CERAMMOS IF 2
                if (paciente.getDomicilio().charAt(i) == '\"') {               // COMPARAMOS SI EL DOMICILIO CONTIENE EL CARACTER "" IF 3
                    System.out.println("Se encontro una comilla doble");
                    invalido = 1;                                           // CAMBIAMOS EL VALOR DE LA BANDERA DE INVALIDEZ   
                }                                                              // CERRAMOS IF 3
                if (paciente.getDomicilio().charAt(i) == '|') {               // COMPARAMOS SI EL DOMICILIO CONTIENE EL CARACTER |     IF 4
                    System.out.println("Se encontro un pipe");
                    invalido = 1;                                           // CAMBIAMOS EL VALOR DE NUESTRA BANDERA DE INVALIDEZ
                }                                                           // CERRAMOS IF 4
            }

            if (invalido == 1) {                                          // COMPARAMOS SI NUESTRA BANDERA DE VALIDEZ AUMENTO SU VALOR A 1
                errors.rejectValue("domicilio", "domicilio.incorrect", "El domicilio es inválido"); //Si ninguna condición se cumple el DOMICILIO es invalido

            }
        }

////////////////VALIDACIÓN DE ID YA REGISTRADO
        String sql = " select no_empleado from nutriologo";   // CONSULTA PARA EXTRAER NO_EMPLEADO
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1).equals(paciente.getNo_boleta())) {
                errors.rejectValue("no_boleta", "no_boleta.incorrect", "El número de boleta ya esta registrado"); //Si ninguna condición se cumple el NO_BOLETA es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_empleado from psicologo";   // CONSULTA PARA EXTRAER DATOS NO_EMPLEADO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1).equals(paciente.getNo_boleta())) {
                errors.rejectValue("no_boleta", "no_boleta.incorrect", "El número de boleta ya esta registrado"); //Si ninguna condición se cumple el NO_BOLETA es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1));
        }
        sql = " select no_empleado from administrador";   // CONSULTA PARA EXTRAER DATOS NO_EMPLEADO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1).equals(paciente.getNo_boleta())) {
                errors.rejectValue("no_boleta", "no_boleta.incorrect", "El número de boleta ya esta registrado"); //Si ninguna condición se cumple el NO_BOLETA es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_boleta from paciente";   // CONSULTA PARA EXTRAER DATOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1).equals(paciente.getNo_boleta())) {
                errors.rejectValue("no_boleta", "no_boleta.incorrect", "El número de boleta ya esta registrado"); //Si ninguna condición se cumple el NO_BOLETA es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1));
        }

        //////VALIDAR FEHCA DE NACIMIENTO
        if (!paciente.getFecha_n().equals("")) {
            System.out.println(paciente.getFecha_n() + " fecha de nacimiento");
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            if (year - Integer.parseInt(paciente.getFecha_n().substring(0, 4)) < 18) {
                errors.rejectValue("fecha_n", "fecha_n.incorrect", "Debes tener al menos 18 años para ingresar al sistema"); //Si ninguna condición se cumple el telefono es invalido

            }
        }

        ////////////////////// Validar correo     YA REGISTRADO                
        sql = " select correo from nutriologo";   // 
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1).equals(paciente.getCorreo())) {
                errors.rejectValue("correo", "correo.incorrect", "El correo ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1));
        }

        sql = " select correo from psicologo";   // CONSULTA 
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1).equals(paciente.getCorreo())) {
                errors.rejectValue("correo", "correo.incorrect", "El correo ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_boleta from paciente";   // CONSULTA 
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1).equals(paciente.getCorreo())) {
                errors.rejectValue("correo", "correo.incorrect", "El correo ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1));
        }
        if (!paciente.getCorreo().equals("")) {

            if (paciente.getCorreo().charAt(paciente.getCorreo().length() - 1) == '.' || paciente.getCorreo().charAt(paciente.getCorreo().length() - 1) == '.') {
                errors.rejectValue("correo", "correo.incorrect", "El correo no es valido");
            } else {
                int arroba = 0;
                int postArroba = 0;
                int comillaSimple = 0;
                for (int i = 0; i < paciente.getCorreo().length(); i++) {
                    if (paciente.getCorreo().charAt(i) == '@') {
                        arroba = 1;
                        System.out.println();
                    }
                    if (paciente.getCorreo().charAt(i) == '.' && arroba == 1) {
                        postArroba = 1;
                    }

                }
                if (postArroba == 0) {

                    errors.rejectValue("correo", "correo.incorrect", "El correo no es valido porque no tiene bien ubicado el punto");
                } else {
                    if (paciente.getCorreo().matches("['*$#%&/!?¡¿{}+*~|^\\']")) {
                        errors.rejectValue("correo", "correo.incorrect", "El correo no es valido por un mal caracter");
                    } else {
                        ////////////////////VALIDAR DATOS DEL CORREO
                        if (!paciente.getCorreo().equals("")) {
                            int invalido = 0;
                            for (int i = 0; i < paciente.getCorreo().length(); i++) {
                                if (paciente.getCorreo().charAt(i) == '\'') {
                                    System.out.println("Se encontro una comilla");
                                    invalido = 1;
                                }
                                if (paciente.getCorreo().charAt(i) == '\"') {
                                    System.out.println("Se encontro una comilla doble");
                                    invalido = 1;
                                }
                                if (paciente.getCorreo().charAt(i) == '|') {
                                    System.out.println("Se encontro un pipe");
                                    invalido = 1;
                                }
                            }

                            if (invalido == 1) {
                                errors.rejectValue("correo", "correo.incorrect", "El correo es inválido"); //Si ninguna condición se cumple el telefono es invalido

                            }
                        }

                    }
                }

            }
        }

        ////////////////VALIDAR TELEFONO YA REGISTRADO              
        sql = " select telefono from administrador";   // CONSULTA PARA EXTRAER DATOS 
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(paciente.getTelefono())) {
                errors.rejectValue("telefono", "telefono.incorrect", "El teléfono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }
        sql = " select telefono from nutriologo";   // CONSULTA PARA EXTRAER DATOS 
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(paciente.getTelefono())) {
                errors.rejectValue("telefono", "telefono.incorrect", "El teléfono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }

        sql = " select telefono from psicologo";   // CONSULTA PARA EXTRAER DATOS 
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(paciente.getTelefono())) {
                errors.rejectValue("telefono", "correo.incorrect", "El Teléfono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_boleta from paciente";   // CONSULTA PARA EXTRAER DATOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(paciente.getTelefono())) {
                errors.rejectValue("telefono", "telefono.incorrect", "El número telefono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }

        /////////////////////////////////////////////////////////////   
        //Validación de telefono
        //valida telefonos con 8 y 10 digitos
        if (!paciente.getTelefono().equals("")) {
            if (paciente.getTelefono().matches("\\d{10}") || paciente.getTelefono().matches("\\d{8}")) {
                System.out.println("EL TELEFONO ES VALIDO");
            } else {
                errors.rejectValue("telefono", "telefono.incorrect", "El telefono no es valido"); //Si ninguna condición se cumple el telefono es invalido
            }
        }

        ////////////////////////////
        //VALIDACIÓN NÚMERO DE BOLETA
        if (!paciente.getNo_boleta().equals("")) {

            if (paciente.getNo_boleta().matches("\\d{10}")) { // valida que se ingresen solo números
                System.out.println("SOLO TIENE NUMEROS");
            } else {
                errors.rejectValue("no_boleta", "no_boleta.incorrect", "El número de boleta no es  valido"); //Si ninguna condición se cumple el telefono es invalido

            }

        }

        ////////////////////////////////////////////////////////////////   
        //validacion de nombre
        if (!paciente.getNombre().equals("")) { //Ingresa solo si el campo nombre no esta vacío
            //valida que solo se encuentren caracteres alfabeticos minusculos, mayusculos acentos y ñ Ñ ademas de permitir espacios, prohibe la entreda de digitos y caracteres especiales
            if (paciente.getNombre().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) { //la letra s simbolisa espacio

                System.out.println("EL Nombre ES VALIDO");
            } else {
                System.out.println(paciente.getNombre());
                errors.rejectValue("nombre", "nombre.incorrect", "El nombre no es valido"); //Si ninguna condición se cumple el nombre es invalido
            }
        }

        //////////////////////////////////////////////////////////////////////////////////   
        //validacion de primer apellido
        if (!paciente.getAp_uno().equals("")) { // Ingresa solo si el primer apellido no esta vacío
            //valida que solo se encuentren caracteres alfabeticos minusculos, mayusculos acentos y ñ Ñ ademas de permitir espacios, prohibe la entreda de digitos y caracteres especiales
            if (paciente.getAp_uno().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) { //la letra s simbolisa espacio
                System.out.println("EL primer apellido ES VALIDO");
            } else {
                errors.rejectValue("ap_uno", "ap_uno.incorrect", "El apellido no es valido"); //Si ninguna condición se cumple el primer apellido  es invalido
            }

        }

        ////////////////////////////////////////////////////////////////   
        //validacion de segundo apellido
        if (!paciente.getAp_dos().equals("")) { //Ingresa solo si el segundo apellido no se encuentra vació
            //valida que solo se encuentren caracteres alfabeticos minusculos, mayusculos acentos y ñ Ñ ademas de permitir espacios, prohibe la entreda de digitos y caracteres especiales
            if (paciente.getAp_dos().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) { //la letra s simbolisa espacio
                System.out.println("EL Segundo apellido ES VALIDO");
            } else {
                errors.rejectValue("ap_dos", "ap_dos.incorrect", "El apellido no es valido"); //Si ninguna condición se cumple el segundo apellido es invalido
            }
        }

        // Validación de contraseñas 
        //SE VALIDA QUE LAS CONTRASEÑAS NO LLEGUEN VACIAS
        if (!paciente.getContraseña().equals("")) {
            int invalido = 0;
            for (int i = 0; i < paciente.getContraseña().length(); i++) {
                if (paciente.getContraseña().charAt(i) == '\'') {
                    System.out.println("Se encontro una comilla");
                    invalido = 1;
                }
                if (paciente.getContraseña().charAt(i) == '\"') {
                    System.out.println("Se encontro una comilla doble");
                    invalido = 1;
                }
                if (paciente.getContraseña().charAt(i) == '|') {
                    System.out.println("Se encontro un pipe");
                    invalido = 1;
                }
            }

            if (invalido == 1) {
                errors.rejectValue("contraseña", "contraseña.incorrect", "La contraseña es inválida"); //Si ninguna condición se cumple el telefono es invalido

            } else {
                if (!paciente.getContraseña2().equals("")) {
                    invalido = 0;
                    for (int i = 0; i < paciente.getContraseña2().length(); i++) {
                        if (paciente.getContraseña().charAt(i) == '\'') {
                            System.out.println("Se encontro una comilla");
                            invalido = 1;
                        }
                        if (paciente.getContraseña2().charAt(i) == '\"') {
                            System.out.println("Se encontro una comilla doble");
                            invalido = 1;
                        }
                        if (paciente.getContraseña2().charAt(i) == '|') {
                            System.out.println("Se encontro un pipe");
                            invalido = 1;
                        }
                    }
                    if (invalido == 1) {
                        errors.rejectValue("contraseña", "contraseña.incorrect", "La contraseña es inválida"); //Si ninguna condición se cumple el telefono es invalido

                    } else {

                        if (!paciente.getContraseña().equals("") && !paciente.getContraseña2().equals("")) {

                            if (paciente.getContraseña().length() > 7) { // LA CONTRASEÑA DEBE CONTENER MAS DE 7 CARACTERES
                                System.out.println("LA CONTRASEÑA 1 TIENE MAS DE 7 CARACTERES");
                                for (int i = 0; i <= paciente.getContraseña().length() - 1; i++) { // SE REALIZA UN RECORRIDO DESDE 0 HASTA LA LONGITUD DE LA CONTRASEÑA -1
                                    clave1 = paciente.getContraseña().charAt(i);            // SE ASIGNA EL CARACTER CON EL INDICE i 
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

                                    if (paciente.getContraseña2().length() > 7) {           // LA CONTRASEÑA DEBE CONTENER MAS DE 7 CARACTERES

                                        System.out.println("LA CONTRASEÑA 2 TIENE MAS DE 7 CARACTERES");
                                        for (int i = 0; i <= paciente.getContraseña2().length() - 1; i++) {    // SE REALIZA UN RECORRIDO DESDE 0 HASTA LA LONGITUD DE LA CONTRASEÑA -1
                                            clave2 = paciente.getContraseña2().charAt(i);               // SE ASIGNA EL CARACTER CON EL INDICE i
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
                                            if (paciente.getContraseña().equals(paciente.getContraseña2())) {  // SE COMPARA QUE LAS CONTRASEÑAS SEAN IGUALES
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
            }

        }

    }
}
