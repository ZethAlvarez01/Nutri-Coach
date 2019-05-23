package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import models.Conexion;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Nutri-Coach
 */
public class PsicologoValidar implements Validator {

    private JdbcTemplate jdbcTemplate;

    public PsicologoValidar() {

        Conexion conn = new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos

    }

    @Override
    public boolean supports(Class<?> type) {
        return Psicologo.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Psicologo psicologo = (Psicologo) o;

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_cedula", "required.no_cedula", "el numero de cedula es oligatorio");
        System.out.println("NO ENCONTRE la cedula");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_empleado", "required.no_empleado", "Tu no. de empleado es obligatorio");
        System.out.println("NO ENCONTRE EL NUMERO DE EMPLEADO");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre", "Tu nombre es obligatorio");
        System.out.println("NO ENCONTRE EL Nombre");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_uno", "required.ap_uno", "Tu primer apellido es obligatorio");
        System.out.println("NO ENCONTRE EL primer apellido");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ap_dos", "required.ap_dos", "Tu segundo apellido es obligatorio");
        System.out.println("NO ENCONTRE EL SEGUNDO apellido");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required.telefono", "Ingresa un número de contacto");
        System.out.println("NO ENCONTRE EL telefono");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo", "Tu correo es obligatorio");
        System.out.println("NO ENCONTRE EL correo");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña", "required.contraseña", "Definir una contraseña es obligatorio");
        System.out.println("NO ENCONTRE la contraseña1");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña2", "required.contraseña2", "Repetir tu contraseña es obligatorio");
        System.out.println("NO ENCONTRE la contraseña2");
        System.out.println(psicologo.getContraseña2());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "institucion", "required.institucion", "Tu institucion adscrita es obligatoria");
        System.out.println("NO ENCONTRE EL instituto");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "consultorio", "required.consultorio", "Tu domicilio de consultorio es obligatorio");
        System.out.println("NO ENCONTRE EL consultorio");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaEntrada", "required.horaEntrada", "Tu Hora de entrada es obligatoria");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaSalida", "required.horaSalida", "Tu Hora de salida es obligatoria");

        /////////////////////////////////////////////////////////////   
        //Validación de telefono
        //valida telefonos con 8 y 10 digitos
        if (!psicologo.getTelefono().equals("")) {
            if (psicologo.getTelefono().matches("\\d{10}") || psicologo.getTelefono().matches("\\d{8}")) {
                System.out.println("EL TELEFONO ES VALIDO");
            } else {
                errors.rejectValue("telefono", "telefono.incorrect", "El telefono no es valido"); //Si ninguna condición se cumple el telefono es invalido
            }
        }

        ////////////////VALIDACION DE ID
        String sql = " select no_empleado from nutriologo";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1).equals(psicologo.getNo_empleado())) {
                errors.rejectValue("no_empleado", "no_empleado.incorrect", "El número de empleado ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_empleado from psicologo";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1).equals(psicologo.getNo_empleado())) {
                errors.rejectValue("no_empleado", "no_empleado.incorrect", "El número de empleado ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1));
        }
        sql = " select no_empleado from administrador";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1).equals(psicologo.getNo_empleado())) {
                errors.rejectValue("no_empleado", "no_empleado.incorrect", "El número de empleado ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(13, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_boleta from paciente";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1).equals(psicologo.getNo_empleado())) {
                errors.rejectValue("no_empleado", "no_empleado.incorrect", "El número de boleta ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1));
        }

        ////////////////////// Validar correo                     
        sql = " select correo from nutriologo";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1).equals(psicologo.getCorreo())) {
                errors.rejectValue("correo", "correo.incorrect", "El correo ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1));
        }

        sql = " select correo from psicologo";   // 
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1).equals(psicologo.getCorreo())) {
                errors.rejectValue("correo", "correo.incorrect", "El correo ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_boleta from paciente";
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1).equals(psicologo.getCorreo())) {
                errors.rejectValue("correo", "correo.incorrect", "El correo ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(8, datosL2.get(i).toString().length() - 1));
        }
        if (!psicologo.getCorreo().equals("")) {

            if (psicologo.getCorreo().charAt(psicologo.getCorreo().length() - 1) == '.' || psicologo.getCorreo().charAt(psicologo.getCorreo().length() - 1) == '.') {
                errors.rejectValue("correo", "correo.incorrect", "El correo no es valido");
            } else {
                int arroba = 0;
                int postArroba = 0;
                int comillaSimple = 0;
                for (int i = 0; i < psicologo.getCorreo().length(); i++) {
                    if (psicologo.getCorreo().charAt(1) == '@') {
                        arroba = 1;
                    }
                    if (psicologo.getCorreo().charAt(1) == '.' && arroba == 1) {
                        postArroba = 1;
                    }

                }
                if (postArroba == 0) {

                    errors.rejectValue("correo", "correo.incorrect", "El correo no es valido");
                } else {
                    if (psicologo.getCorreo().matches("['*$#%&/!?¡¿{}+*~|^\\']")) {
                        errors.rejectValue("correo", "correo.incorrect", "El correo no es valido");
                    } else {
                        ////////////////////VALIDAR DATOS DEL CORREO
                        if (!psicologo.getCorreo().equals("")) {
                            int invalido = 0;
                            for (int i = 0; i < psicologo.getCorreo().length(); i++) {
                                if (psicologo.getCorreo().charAt(i) == '\'') {
                                    System.out.println("Se encontro una comilla");
                                    invalido = 1;
                                }
                                if (psicologo.getCorreo().charAt(i) == '\"') {
                                    System.out.println("Se encontro una comilla doble");
                                    invalido = 1;
                                }
                                if (psicologo.getCorreo().charAt(i) == '|') {
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
        sql = " select telefono from aministrador";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(psicologo.getTelefono())) {
                errors.rejectValue("telefono", "telefono.incorrect", "El teléfono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }

        sql = " select telefono from nutriologo";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(psicologo.getTelefono())) {
                errors.rejectValue("telefono", "telefono.incorrect", "El teléfono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }

        sql = " select telefono from psicologo";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(psicologo.getTelefono())) {
                errors.rejectValue("telefono", "correo.incorrect", "El Teléfono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_boleta from paciente";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1).equals(psicologo.getTelefono())) {
                errors.rejectValue("telefono", "telefono.incorrect", "El número telefono ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(10, datosL2.get(i).toString().length() - 1));
        }

        ////////////////VALIDACION CEDULA
        sql = " select no_cedula from nutriologo";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1).equals(psicologo.getNo_cedula())) {
                errors.rejectValue("no_cedula", "no_cedula.incorrect", "El número de cedula ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1));
        }

        sql = " select no_cedula from psicologo";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        for (int i = 0; i < datosL2.size(); i++) {
            if (datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1).equals(psicologo.getNo_cedula())) {
                errors.rejectValue("no_cedula", "no_cedula.incorrect", "El número de cedula ya esta registrado"); //Si ninguna condición se cumple el telefono es invalido

            }
            System.out.println(datosL2.get(i).toString().substring(11, datosL2.get(i).toString().length() - 1));
        }

        ////////////////////////////
        //VALIDACIÓN NÚMERO DE EMPLEADO
        if (!psicologo.getNo_empleado().equals("")) {

            if (psicologo.getNo_empleado().matches("\\d{10}")) { // valida que se ingresen solo números
                System.out.println("SOLO TIENE NUMEROS");
            } else {
                errors.rejectValue("no_empleadp", "no_empleado.incorrect", "El número de empleado no es  valido"); //Si ninguna condición se cumple el telefono es invalido

            }

        }
        ////////////////////////////
        //VALIDACIÓN NÚMERO DE CEDULA
        if (!psicologo.getNo_cedula().equals("")) {

            if (psicologo.getNo_cedula().matches("\\d{10}") || psicologo.getNo_cedula().matches("\\d{7}") || psicologo.getNo_cedula().matches("\\d{8}") || psicologo.getNo_cedula().matches("\\d{9}")) { // valida que se ingresen solo números
                System.out.println("SOLO TIENE NUMEROS");
            } else {
                errors.rejectValue("no_empleadp", "no_empleado.incorrect", "El número de empleado no es  valido"); //Si ninguna condición se cumple el telefono es invalido

            }

        }

        /////////////////////////////////////
        //////////////Validación de horas de trabajo
        /////////////////////////////////////////////////////////////   
        //Validación de telefono
        if (!psicologo.getHoraEntrada().equals("")) {
            if (!psicologo.getHoraSalida().equals("")) {
                LocalTime inicio = LocalTime.parse(psicologo.getHoraEntrada());
                LocalTime fin = LocalTime.parse(psicologo.getHoraSalida());

                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                Date horaIni;
                Date horaFin;
                try {
                    horaIni = dateFormat.parse(psicologo.getHoraEntrada());
                    horaFin = dateFormat.parse(psicologo.getHoraSalida());
                    if (horaIni.compareTo(horaFin) < 0) {//Jornada laboral:

                        if (horaIni.toString().charAt(5) != '0') {
                            errors.rejectValue("horaSalida", "horaSalida.incorrect", "Horario invalido");
                        }
                        if (horaFin.toString().charAt(5) != '0') {
                            errors.rejectValue("horaSalida", "horaSalida.incorrect", "Horario invalido");
                        }
                    } else {
                        errors.rejectValue("horaSalida", "horaSalida.incorrect", "No cuentas con una jornada laboral valida"); //Si ninguna condición se cumple el telefono es invalido
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(PsicologoValidar.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        ////////////////////////////////////////////////////////////////   
        //validacion de nombre
        if (!psicologo.getNombre().equals("")) { //Ingresa solo si el campo nombre no esta vacío
            //valida que solo se encuentren caracteres alfabeticos minusculos, mayusculos acentos y ñ Ñ ademas de permitir espacios, prohibe la entreda de digitos y caracteres especiales
            if (psicologo.getNombre().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) { //la letra s simbolisa espacio

                System.out.println("EL Nombre ES VALIDO");
            } else {
                System.out.println(psicologo.getNombre());
                errors.rejectValue("nombre", "nombre.incorrect", "El nombre no es valido"); //Si ninguna condición se cumple el nombre es invalido
            }
        }

        //////////////////////////////////////////////////////////////////////////////////   
        //validacion de primer apellido
        if (!psicologo.getAp_uno().equals("")) { // Ingresa solo si el primer apellido no esta vacío
            //valida que solo se encuentren caracteres alfabeticos minusculos, mayusculos acentos y ñ Ñ ademas de permitir espacios, prohibe la entreda de digitos y caracteres especiales
            if (psicologo.getAp_uno().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) { //la letra s simbolisa espacio
                System.out.println("EL primer apellido ES VALIDO");
            } else {
                errors.rejectValue("ap_uno", "ap_uno.incorrect", "El apellido no es valido"); //Si ninguna condición se cumple el primer apellido  es invalido
            }

        }

        ////////////////////////////////////////////////////////////////   
        //validacion de segundo apellido
        if (!psicologo.getAp_dos().equals("")) { //Ingresa solo si el segundo apellido no se encuentra vació
            //valida que solo se encuentren caracteres alfabeticos minusculos, mayusculos acentos y ñ Ñ ademas de permitir espacios, prohibe la entreda de digitos y caracteres especiales
            if (psicologo.getAp_dos().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) { //la letra s simbolisa espacio
                System.out.println("EL Segundo apellido ES VALIDO");
            } else {
                errors.rejectValue("ap_dos", "ap_dos.incorrect", "El apellido no es valido"); //Si ninguna condición se cumple el segundo apellido es invalido
            }
        }

        // Validación de contraseñas 
        //SE VALIDA QUE LAS CONTRASEÑAS NO LLEGUEN VACIAS
        if (!psicologo.getContraseña().equals("")) {
            int invalido = 0;
            for (int i = 0; i < psicologo.getContraseña().length(); i++) {
                if (psicologo.getContraseña().charAt(i) == '\'') {
                    System.out.println("Se encontro una comilla");
                    invalido = 1;
                }
                if (psicologo.getContraseña().charAt(i) == '\"') {
                    System.out.println("Se encontro una comilla doble");
                    invalido = 1;
                }
                if (psicologo.getContraseña().charAt(i) == '|') {
                    System.out.println("Se encontro un pipe");
                    invalido = 1;
                }
            }

            if (invalido == 1) {
                errors.rejectValue("contraseña", "contraseña.incorrect", "La contraseña es inválida"); //Si ninguna condición se cumple el telefono es invalido

            } else {
                if (!psicologo.getContraseña2().equals("")) {
                    invalido = 0;
                    for (int i = 0; i < psicologo.getContraseña2().length(); i++) {
                        if (psicologo.getContraseña().charAt(i) == '\'') {
                            System.out.println("Se encontro una comilla");
                            invalido = 1;
                        }
                        if (psicologo.getContraseña2().charAt(i) == '\"') {
                            System.out.println("Se encontro una comilla doble");
                            invalido = 1;
                        }
                        if (psicologo.getContraseña2().charAt(i) == '|') {
                            System.out.println("Se encontro un pipe");
                            invalido = 1;
                        }
                    }
                    if (invalido == 1) {
                        errors.rejectValue("contraseña", "contraseña.incorrect", "La contraseña es inválida"); //Si ninguna condición se cumple el telefono es invalido

                    } else {

                        if (!psicologo.getContraseña().equals("") && !psicologo.getContraseña2().equals("")) {

                            if (psicologo.getContraseña().length() > 7) { // LA CONTRASEÑA DEBE CONTENER MAS DE 7 CARACTERES
                                System.out.println("LA CONTRASEÑA 1 TIENE MAS DE 7 CARACTERES");
                                for (int i = 0; i <= psicologo.getContraseña().length() - 1; i++) { // SE REALIZA UN RECORRIDO DESDE 0 HASTA LA LONGITUD DE LA CONTRASEÑA -1
                                    clave1 = psicologo.getContraseña().charAt(i);            // SE ASIGNA EL CARACTER CON EL INDICE i 
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

                                    if (psicologo.getContraseña2().length() > 7) {           // LA CONTRASEÑA DEBE CONTENER MAS DE 7 CARACTERES

                                        System.out.println("LA CONTRASEÑA 2 TIENE MAS DE 7 CARACTERES");
                                        for (int i = 0; i <= psicologo.getContraseña2().length() - 1; i++) {    // SE REALIZA UN RECORRIDO DESDE 0 HASTA LA LONGITUD DE LA CONTRASEÑA -1
                                            clave2 = psicologo.getContraseña2().charAt(i);               // SE ASIGNA EL CARACTER CON EL INDICE i
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
                                            if (psicologo.getContraseña().equals(psicologo.getContraseña2())) {  // SE COMPARA QUE LAS CONTRASEÑAS SEAN IGUALES
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
