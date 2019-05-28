/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Administrador;
import models.Comentario;
import models.Conexion;
import models.Dieta;
import models.Login;
import models.NeuralNet.Capa_neuronas;
import models.NeuralNet.Crear_RN;
import models.NeuralNet.Implementacion;
import models.NeuralNet.Tratamiento;
import models.NeuralNet.libMatrices;
import models.Nutriologo;
import models.Paciente;
import models.Psicologo;
import models.cita;
import models.citaValidar;
import models.comentarioValidar;
import models.entradaForo;
import models.expediente;
import models.expedienteValidar;
import models.foroValidar;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jms-m
 */
@Controller

public class nutriologoController {

    private JdbcTemplate jdbcTemplate;
    private foroValidar foroValidar;                                 //Variable para validar foro
    private comentarioValidar comentarioValidar;                     //Variable para validar comentarios
    private citaValidar citaValidar;                           //Variable para validar cita 
    private expedienteValidar expedienteValidar;                                 //Variable para validar expediente

    public nutriologoController() {

        this.foroValidar = new foroValidar();                            // Instancia de la clase foroValidar
        this.comentarioValidar = new comentarioValidar();               // Instancia de la clase comentarioalidar
        Conexion conn = new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
        this.citaValidar = new citaValidar();                      // Instancia de la clase diarioValidar
        this.expedienteValidar = new expedienteValidar();                      // Instancia de la clase expedienteValidar
    }

    ///////////////////////////////
    //PETICION DE CRONOGRAMA 
    @RequestMapping(value = "/mostrarHorarioNutriologo", method = RequestMethod.GET)
    public @ResponseBody
    String getCrono(@RequestParam String fechaConsulta, @RequestParam String no_empleadoConsulta) {
        System.out.println("-----getCrononUTRI-------");

        System.out.println(fechaConsulta + " esta fue la fecha");
        System.out.println(no_empleadoConsulta + " este es el no_empleado");
        String sql = " select no_cedula from nutriologo where no_empleado=" + no_empleadoConsulta;   // CONSULTA PARA EXTRAER DATOS HORARIOS
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        System.out.println("ESTE ES EL NUMERO DE CEDULA: " + datosL2);

        String cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);
        System.out.println(cedula);

        sql = "select t1.nombre, t1.ap_uno, t1.ap_dos, t1.no_boleta, t2.no_cita,t2.horario from paciente t1 inner join cita t2 on t1.no_boleta=t2.no_boleta and t1.no_cedula=t2.no_cedula and t2.no_cedula=" + cedula + " and t2.fecha='" + fechaConsulta + "' and t2.estado=3 order by t2.horario";
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        System.out.println(datosL2);

        String objeto;
        if (datosL2.isEmpty()) {
            objeto = new Gson().toJson(null);
        } else {
            objeto = new Gson().toJson(datosL2);
        }

        return objeto;
    }

    /////////////////////////////////////////////////////////////// 
    //pantalla de lista de pacientes de psicologo
    @RequestMapping(value = "ConsultarPacientePrincipaln.htm", method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_psicologo y se aplicará el método GET

    public ModelAndView ConsultarPacientePrincipaln(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_psicologo
        System.out.println("paciente");

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                     //CREACIÓN DEL MODELO
        mv.setViewName("ConsultarPacientePrincipaln");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO        

        sql = "select no_boleta,no_cedula,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,"
                + "domicilio,correo from paciente where no_cedula= (select no_cedula from nutriologo where no_empleado=" + alert + ") order by ap_uno"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
        datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPacientes", datosL2);          // Pasa la lilsta completa

        mv.addObject("LongitudP", datosL2.size());    // SE PASA EL TOTAL DE PACIENTES
        mv.addObject("Paciente", new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO  

        return mv;

    }

    ///////////////////////////////////////
    //Pantalla de nueva entrada en el foro
    @RequestMapping(value = "nuevaEntradaN.htm", method = RequestMethod.GET)

    public ModelAndView nuevaEntradaForo(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE NUTRIOLOGOS

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        ModelAndView mv = new ModelAndView();  //CREACIÓN DEL MODELO
        mv.setViewName("nuevaEntradaN");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
        mv.addObject("entradaForo", new entradaForo());                                       // PASAMOS OBJETO entradaForo

        return mv;                                                                                           //Retornarmos el modelo

    }

    ///////////////////////////////////////
    //Pantalla de FORO PRINCIPAL
    @RequestMapping(value = "ForoPrincipalN.htm", method = RequestMethod.GET)

    public ModelAndView ForoPrincipal(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        ModelAndView mv = new ModelAndView();                  //CREACIÓN DEL MODELO
        mv.setViewName("ForoPrincipalN");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
        mv.addObject("entradaForo", new entradaForo());                                       //PASAMOS OBJETO entradaForo

        sql = "select * from entrada order by id_entrada desc";                                            //OBTENEMOS TODOS LOS ELEMENTOS DE LA TABLA ENTRADA ORDENADOS DE MANERA DESCENDENTE A PARTIR DEL ID_ENTRADA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA

        mv.addObject("listaEntradas", datosL2);                                          // PASAMOS EL RESULTADO COMPLETO

        sql = "select id_entrada from entrada order by id_entrada desc";                                  //OBTENEMOS EL ID_ENTRADA DE TODAS LAS ENTRADAS EN LA TABLA ENTRADA ORDENADAS DE MANERA DESCENTE A PARTIR DEL ID_ENTRADA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                             //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA

        int contadorEntradas = datosL2.size();                                                           //ASIGNAMOS EL VALOR DEL TAMAÑO DE LA LISTA OBTENIDA EN LA CONSULTA

        List contadorComentarios = null;                                                              //INICIALIZAMOS LA LISTA contadorComentarios
        String cadena = "";                                                                             //INICIALIZAMOS EL STRING CADENA
        String subcadena = "";                                                                          //INICIALIZAMOS EL STRING SUBCADENA

        cadena = datosL2.get(0).toString();                                                             //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICIÓN O DE LA LISTA DE NUESTRA CONSULTA
        subcadena = cadena.substring(1, cadena.length() - 1);                                              //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA

        sql = "select count(id_comnt) from comentarios where " + subcadena;                              //CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA

        contadorComentarios = this.jdbcTemplate.queryForList(sql);                                    //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        for (int i = 1; i <= contadorEntradas - 1; i++) {                                                     // INICIO DE CICLO FOR EN EL QUE CONTAREMOS DESDE 1 HASTA EL NUMERO DE ENTRADAS - 1

            cadena = datosL2.get(i).toString();                                                            //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE LA LISTA DE NUESTRA CONSULTA 
            subcadena = cadena.substring(1, cadena.length() - 1);                                             //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA
            sql = "select count(id_comnt) from comentarios where " + subcadena;                             // CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA

            contadorComentarios.add(i, this.jdbcTemplate.queryForList(sql));                            //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        }

        List<String> contador = new ArrayList<String>();                                          //CREAMOS UN ARREGLO DE STRINGS EN EL QUE GUARDAREMOS LA CANTIDAD DE COMENTARIOS POR ENTRADA EN EL FORO

        for (int i = 0; i <= contadorEntradas - 1; i++) {                                                   // INICIO DE CICLO FOR 1 EN EL QUE CONTAREMOS DESDE 0 HASTA EL NUMERO DE ENTRADAS -1

            cadena = contadorComentarios.get(i).toString();                                              //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE contadorComentarios

            for (int j = 0; j < cadena.length(); j++) {                                                        //INICIO DE CICLO FOR 2 DONDE CONTAMOS DESDE 0 HASTA LA LONGITUD DE CADENA
                if (cadena.charAt(j) == '=') {                                                             // SI EL CARACTER UBICADO EN LA POSICION J DE CADENA ES IGUAL AL SIMBOLO =    INICIO DE IF 1

                    if (cadena.endsWith("}")) {                                                         // SI NUESTRA CADENA TERMINA CON EL SIMBOLO } INICIO DE IF 2
                        subcadena = cadena.substring(j + 1, cadena.length() - 1);                                      // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -1

                        contador.add(i, subcadena);                                                          // AGREGAMOS LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR

                        j = cadena.length();                                                                      // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                    }                                                                                        // CIERRE DE IF 2
                    if (cadena.endsWith("]")) {                                                          // SI NUESTRA CADENA TERMINA CON EL SIMOBOLO } INICIO DE IF 3
                        subcadena = cadena.substring(j + 1, cadena.length() - 2);                                   // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -2

                        contador.add(i, subcadena);                                                          // AGREGAMOOS  LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR

                        j = cadena.length();                                                                    // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                    }                                                                                        // CIERRE IF 3

                }                                                                                   // CIERRE IF 1
            }                                                                                      // CIERRE FOR 2

        }                                                                                       //FINAL DE CICLO FOR 1

        mv.addObject("contadorComentarios", contador);                                        //PASAMOS OBJETO contador

        return mv;                                                                               // RETORNAMOS EL MODELO

    }

    ///////////////////////////////////////
    //Pantalla de consultar entrada en el foro
    @RequestMapping(value = "ConsultarEntradaN.htm", method = RequestMethod.GET)

    public ModelAndView consultarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                     //CREACIÓN DEL MODELO
        mv.setViewName("ConsultarEntradaN");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO    
        mv.addObject("entradaForo", new entradaForo());                                       //PASAMOS OBJETO entradaForo

        sql = "select * from entrada where id_entrada=" + eF.getId_entrada();                                   // CONSULTA PARA OBTENER TODO SOBRE LA ENTRADA QUE SEA IGUAL AL Id_entrada
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                  //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("Entrada", datosL2);                                                   //PASAMOS OBJETO LA LISTA COMPLETA

        mv.addObject("Comentario", new Comentario());                                      //PASAMOS EL OBJETO Comentario

        sql = "select * from comentarios where id_entrada=" + eF.getId_entrada() + " order by id_comnt";             //CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON id_entrada Y ORDENADOS DE MANERA DESCENDENTE POR id_comnt
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaComentarios", datosL2);                                           //PASAMOS LA LISTA COMPLETA

        sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }

    /////////////////////////////////////////////////////////////// 
    //pantalla de lista de pacientes de nutriologo
    @RequestMapping(value = "bienvenida_nutriologo.htm", method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_nutriologo y se aplicará el método GET

    public ModelAndView bienvenida_nutriologo(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma");
        System.out.println("AQUI ESTA EL CRONOGRAMA");

        HttpSession session = hsr.getSession();
        String alert = (String) session.getAttribute("Nutri");
        System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: " + alert);
        if (alert == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        System.out.println("NO_ EMPLEADO: " + alert);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("bienvenida_nutriologo");

        String sql = "select nombre,ap_uno,ap_dos from nutriologo where no_empleado=" + alert;
        List datosL2 = this.jdbcTemplate.queryForList(sql);

        mv.addObject("datos", datosL2);          // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());

        sql = "select no_boleta,no_cedula,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,"
                + "domicilio,correo from paciente where no_cedula= (select no_cedula from nutriologo where no_empleado=" + alert + ")"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
        datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPacientes", datosL2);          // Pasa la lilsta completa

        mv.addObject("LongitudP", datosL2.size());    // SE PASA EL TOTAL DE PACIENTES
        mv.addObject("Paciente", new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO        
        return mv;

    }

    /////////////////////////////////////////////
    ///pantalla de vista mensajeriaN
    @RequestMapping(value = "mensajeriaN.htm", method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeriaN y se aplicará el método GET

    public ModelAndView mensajeriaN(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte mensajeria");
        System.out.println("AQUI ESTA LA MENSAJERIA");

        HttpSession session = hsr.getSession();
        String alert = (String) session.getAttribute("Nutri");
        System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: " + alert);
        if (alert == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        System.out.println("NO_ EMPLEADO: " + alert);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("mensajeriaN");
        mv.addObject("Nutriologo", new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;
        List datosL2 = this.jdbcTemplate.queryForList(sql);

        mv.addObject("datos", datosL2);          // Pasa la lilsta completa

        return mv;

    }

    /////////////////////////////////////////////
    ///pantalla de vista foro
    @RequestMapping(value = "foroN.htm", method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foroN y se aplicará el método GET

    public ModelAndView foroN(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        ModelAndView mv = new ModelAndView();                        //CREACIÓN DEL MODELO
        mv.setViewName("foroN");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO  
        mv.addObject("entradaForo", new entradaForo());                                       //PASAMOS OBJETO entradaForo 

        sql = "select * from entrada where id_usuario=" + alert + " order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("listaEntradas", datosL2);                                               // PASAMOS LA LISTA COMPLETA
        sql = "select id_entrada,fecha from comentarios where id_usuario=" + alert + " order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("FechaComentarios", datosL2);                                               // PASAMOS LA LISTA COMPLETA

        sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("NombreEntrada", datosL2);                                               // PASAMOS LA LISTA COMPLETA            

        return mv;                                                                                            // RETORNAMOS EL MODELO

    }

    /////////////////////////////////////////////////////////////// 
    //Cronograma 
    @RequestMapping(value = "cronograma.htm", method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA cronograma y se aplicará el método GET

    public ModelAndView cronograma(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma");
        System.out.println("AQUI ESTA EL CRONOGRAMA");

        HttpSession session = hsr.getSession();
        String alert = (String) session.getAttribute("Nutri");
        System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: " + alert);
        if (alert == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        System.out.println("NO_ EMPLEADO: " + alert);

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;
        List datosL2 = this.jdbcTemplate.queryForList(sql);

        ModelAndView mv = new ModelAndView();                                // Creación del modelo
        mv.setViewName("cronograma");                                            // Nombra al modelo
        mv.addObject("datos", datosL2);         // agrega al modelo el objeto datos
        mv.addObject("Nutriologo", new Nutriologo());
        return mv;

    }

    @RequestMapping(params = "pacientes", method = RequestMethod.POST)

    public ModelAndView cambiarPacientes(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma");
        System.out.println("AQUI ESTA EL CRONOGRAMA");

        HttpSession session = hsr.getSession();
        String alert = (String) session.getAttribute("Nutri");
        System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: " + alert);
        if (alert == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        System.out.println("NO_ EMPLEADO: " + alert);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("bienvenida_nutriologo");

        String sql = "select nombre,ap_uno,ap_dos from nutriologo where no_empleado=" + alert;
        List datosL2 = this.jdbcTemplate.queryForList(sql);

        mv.addObject("datos", datosL2);          // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());

        sql = "select no_boleta,no_cedula,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,"
                + "domicilio,correo from paciente where no_cedulap= (select no_cedula from nutriologo where no_empleado=" + alert + ")"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
        datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPacientes", datosL2);          // Pasa la lilsta completa

        mv.addObject("LongitudP", datosL2.size());    // SE PASA EL TOTAL DE PACIENTES
        mv.addObject("Paciente", new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO                           

        return mv;

    }

    @RequestMapping(params = "mensajeria", method = RequestMethod.POST)

    public ModelAndView cambiarMensajeria(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte mensajeria");
        System.out.println("AQUI ESTA LA MENSAJERIA");

        HttpSession session = hsr.getSession();
        String alert = (String) session.getAttribute("Nutri");
        System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: " + alert);
        if (alert == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        System.out.println("NO_ EMPLEADO: " + alert);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("mensajeriaN");
        mv.addObject("Nutriologo", new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;
        List datosL2 = this.jdbcTemplate.queryForList(sql);

        mv.addObject("datos", datosL2);          // Pasa la lilsta completa

        return mv;

    }

    @RequestMapping(params = "foro", method = RequestMethod.POST)

    public ModelAndView cambiarForo(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        ModelAndView mv = new ModelAndView();                        //CREACIÓN DEL MODELO
        mv.setViewName("foroN");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO  
        mv.addObject("entradaForo", new entradaForo());                                       //PASAMOS OBJETO entradaForo 

        sql = "select * from entrada where id_usuario=" + alert + " order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("listaEntradas", datosL2);                                               // PASAMOS LA LISTA COMPLETA
        sql = "select id_entrada,fecha from comentarios where id_usuario=" + alert + " order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("FechaComentarios", datosL2);                                               // PASAMOS LA LISTA COMPLETA

        sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("NombreEntrada", datosL2);                                               // PASAMOS LA LISTA COMPLETA            

        return mv;                                                                                            // RETORNAMOS EL MODELO

    }

    @RequestMapping(params = "cronograma", method = RequestMethod.POST)

    public ModelAndView cambiarCronograma(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma");
        System.out.println("AQUI ESTA EL CRONOGRAMA");

        HttpSession session = hsr.getSession();
        String alert = (String) session.getAttribute("Nutri");
        System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: " + alert);
        if (alert == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        System.out.println("NO_ EMPLEADO: " + alert);

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;
        List datosL2 = this.jdbcTemplate.queryForList(sql);

        ModelAndView mv = new ModelAndView();                                // Creación del modelo
        mv.setViewName("cronograma");                                            // Nombra al modelo
        mv.addObject("datos", datosL2);         // agrega al modelo el objeto datos
        mv.addObject("Nutriologo", new Nutriologo());
        return mv;

    }

    //////////////////////
    //////////PANTALLA DE NUEVA ENTRADA EN EL FORO
    @RequestMapping(params = "nuevaEntrada", method = RequestMethod.POST)
    public ModelAndView cambiarNuevaEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE NUTRIOLOGOS

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        ModelAndView mv = new ModelAndView();  //CREACIÓN DEL MODELO
        mv.setViewName("nuevaEntradaN");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
        mv.addObject("entradaForo", new entradaForo());                                       // PASAMOS OBJETO entradaForo

        return mv;                                                                                           //Retornarmos el modelo

    }

    ////////////////////
    ////ACCIÓN DE BOTON guardarEntrada
    @RequestMapping(params = "guardarEntrada", method = RequestMethod.POST)
    public ModelAndView guardarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        this.foroValidar.validate(eF, result);
        // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
        if (result.hasErrors()) {                                                            // INICIO IF

            //volvemos al formulario porque los datos ingresados son incorrectos
            ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
            mv.setViewName("nuevaEntradaN");                                  //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
            mv.addObject("entradaForo", new entradaForo());                                       // PASAMOS OBJETO entradaForo

            return mv;                                                                                            //RETORNAMOS EL MODELO
        } // CIERRE DE IF
        else {                                                                           // INICIO ELSE

            String contenido = eF.getContenido();
            String titulo = eF.getTitulo();

            String subTitulo = "";
            String subContenido = "";
            for (int i = 0; i < eF.getContenido().length(); i++) {
                if (contenido.charAt(i) == '\'') {
                    subContenido = subContenido + "\\'";
                } else {
                    if (contenido.charAt(i) == '\\') {
                        subContenido = subContenido + "\\\\";
                    } else {
                        subContenido = subContenido + contenido.charAt(i);
                    }

                }
            }

            for (int i = 0; i < eF.getTitulo().length(); i++) {
                if (titulo.charAt(i) == '\'') {
                    subTitulo = subTitulo + "\\'";
                } else {
                    if (titulo.charAt(i) == '\\') {
                        subTitulo = subTitulo + "\\\\";
                    } else {
                        subTitulo = subTitulo + titulo.charAt(i);
                    }

                }
            }

            String sql = "insert into entrada values(" + '0' + "," + alert + ",'" + subTitulo + "','" + subContenido + "','','" + eF.getFecha() + "');";   // INSERTAMOS EN LA TABLA ENTRADA NUESTRO id_usuario mientras el dato de session alert, el titulo de nuestra entrada y el contenido

            this.jdbcTemplate.update(sql);                                                                              // REALIZAMOS LA INSERCIÓN

            ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
            mv.setViewName("ConsultarEntradaN");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
            mv.addObject("entradaForo", new entradaForo());                                       // PASAMOS EL OBJETO entradaForo
            mv.addObject("Comentario", new Comentario());                                         // PASAMOS EL OBJETO comentario

            sql = "select * from entrada where id_usuario=" + alert + " order by id_entrada desc limit 1";      // CONSULTA PARA EXTRAER LA ULTIMA ENTRADA EN EL FORO REALIZADA POR NUESTRO PACIENTE ACTIVO
            datosL2 = this.jdbcTemplate.queryForList(sql);                                           // ASIGNAMOS  EL RESULTADO DE LA CONSULTA

            mv.addObject("Entrada", datosL2);                                             // PASAMOS EL RESULTADO DE LA CONSULTA

            sql = "select * from comentarios where id_entrada=" + eF.getId_entrada() + " order by id_comnt";      // CONSULTA PARA OBTENER TODOS LOS COMENTARIOS REALIZADOS EN ESTA ENTRADA ORDENADOS POR´ id_comnt 
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                // ASIGNAMOS EL RESULTADO DE NUSTRA CONSULTA

            mv.addObject("ListaComentarios", datosL2);                                        // PASAMOS LA LISTA COMPLETA

            sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            return mv;                                                                                           // RETORNAMOS EL MODELO
        }                                                                                                   // CIERRE DE ELSE

    }

    ///////////////
    /// ACCIÓN BOTON EliminarEntrada
    @RequestMapping(params = "EliminarEntrada", method = RequestMethod.POST)
    public ModelAndView EliminarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        String sql = " delete from entrada where id_entrada=" + eF.getId_entrada() + ";";         // BORRAMOS LA ENTRADA IDENTIFICADA CON EL ID id_entrada

        this.jdbcTemplate.update(sql);                                                // REALIZAMOS LA ELIMINACIÓN DE LA ENTRADA

        sql = "delete  from comentarios where id_entrada=" + eF.getId_entrada();                   // BORRAMOS TODOS LOS COMENTARIOS DE REALACIONADOS A LA ENTRADA CON EL ID id_entrada
        this.jdbcTemplate.update(sql);                                            // REALIZAMOS LA ELIMINACION DE LOS COMENTARIOS

        return new ModelAndView("redirect:/foroN.htm");                                       // REDIRECCIONAMOS AL FORO

    }

    //////////////////////
    ////////ACCIÓN DEL BOTÓN MoficiarEntrada
    @RequestMapping(params = "ModificarEntrada", method = RequestMethod.POST)
    public ModelAndView ModificarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        System.out.println("MODIFICAR ENTRADA DEL FORO");

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        this.foroValidar.validate(eF, result);
        // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
        if (result.hasErrors()) {                                                 // INICIO DE IF

            //volvemos al formulario porque los datos ingresados son incorrectos
            System.out.println("no_boleta: " + alert);

            ModelAndView mv = new ModelAndView();                          //CREACIÓN DEL MODELO
            mv.setViewName("ConsultarEntradaN");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
            mv.addObject("entradaForo", new entradaForo());                                      // PASAMOS EL OBJETO entradaForo
            mv.addObject("Comentario", new Comentario());                                        // PASAMOS EL OBJETO Comentario

            sql = "select * from entrada where id_entrada=" + eF.getId_entrada();                                 //CONSULTA PARA OBJETER LA ENTRADA RELACIONADA AL ID id_entrada
            datosL2 = this.jdbcTemplate.queryForList(sql);                                               //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("Entrada", datosL2);                                                 // PASAMOS EL RESULTADO DE LA CONSULTA

            sql = "select * from comentarios where id_entrada=" + eF.getId_entrada() + " order by id_comnt";       // SELECCIONAMOS TODOS LOS COMENTARIOS RELACIONADOS CON EL ID id_entrada ORDENADOS POR id_comnt
            datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaComentarios", datosL2);                                       // PASAMOS LA LISTA COMPLETA

            sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            return mv;                                                                                           // RETORNAMOS EL MODELO
        } // CIERRE DE IF  
        else {                                                                                                    // INICIO DE ELSE

            String contenido = eF.getContenido();
            String titulo = eF.getTitulo();

            String subTitulo = "";
            String subContenido = "";
            for (int i = 0; i < eF.getContenido().length(); i++) {
                if (contenido.charAt(i) == '\'') {
                    subContenido = subContenido + "\\'";
                } else {
                    if (contenido.charAt(i) == '\\') {
                        subContenido = subContenido + "\\\\";
                    } else {
                        subContenido = subContenido + contenido.charAt(i);
                    }

                }
            }

            for (int i = 0; i < eF.getTitulo().length(); i++) {
                if (titulo.charAt(i) == '\'') {
                    subTitulo = subTitulo + "\\'";
                } else {
                    if (titulo.charAt(i) == '\\') {
                        subTitulo = subTitulo + "\\\\";
                    } else {
                        subTitulo = subTitulo + titulo.charAt(i);
                    }

                }
            }

            String sql = "update entrada set titulo='" + subTitulo + "',contenido='" + subContenido + "'where id_entrada=" + eF.getId_entrada() + ";"; // ACTUALIZAMOS LA ENTRADA EN TITULO Y CONTENIDO DONDE el ID DE ENTRADA SE IGUAL A id_entrada

            this.jdbcTemplate.update(sql);                       //REALIZAMOS LA ACTUALIZACIÓN

            ModelAndView mv = new ModelAndView();                                  //CREACIÓN DEL MODELO
            mv.setViewName("ConsultarEntradaN");                            //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
            mv.addObject("entradaForo", new entradaForo());                                       // PASAMOS EL OBJETO entradaForo

            sql = "select * from entrada where id_entrada=" + eF.getId_entrada();                                      // CONSULTA PARA OBTENER LA ENTRADA CON EL ID id_entrada
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                  // ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("Entrada", datosL2);                                                    // PASAMOS EL RESULTADO DE LA CONSULTA
            mv.addObject("Comentario", new Comentario());                                       // PASAMOS EL OBJETO Comentario

            sql = "select * from comentarios where id_entrada=" + eF.getId_entrada() + " order by id_comnt";          // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                 //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaComentarios", datosL2);                                          // PASAMOS EL RESULTADO DE LA CONSULTA

            sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            return mv;                                                                                           // RETORNAMOS EL MODELO

        }                                                                                                          // CIERRE DE ELSE

    }

    /////////////////
    ////VISTA CONSULTAR ENTRADA
    @RequestMapping(params = "consultarEntrada", method = RequestMethod.POST)
    public ModelAndView cambiarConsultarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                     //CREACIÓN DEL MODELO
        mv.setViewName("ConsultarEntradaN");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO   
        mv.addObject("entradaForo", new entradaForo());                                       //PASAMOS OBJETO entradaForo

        sql = "select * from entrada where id_entrada=" + eF.getId_entrada();                                   // CONSULTA PARA OBTENER TODO SOBRE LA ENTRADA QUE SEA IGUAL AL Id_entrada
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                  //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("Entrada", datosL2);                                                   //PASAMOS OBJETO LA LISTA COMPLETA

        mv.addObject("Comentario", new Comentario());                                      //PASAMOS EL OBJETO Comentario

        sql = "select * from comentarios where id_entrada=" + eF.getId_entrada() + " order by id_comnt";             //CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON id_entrada Y ORDENADOS DE MANERA DESCENDENTE POR id_comnt
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaComentarios", datosL2);                                           //PASAMOS LA LISTA COMPLETA

        sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }

    ///////////////////////
    /////VISTA FORO PRINCIPAL
    @RequestMapping(params = "foroPrincipal", method = RequestMethod.POST)
    public ModelAndView cambiarForoPrincipal(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) throws Exception { // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        ModelAndView mv = new ModelAndView();                  //CREACIÓN DEL MODELO
        mv.setViewName("ForoPrincipalN");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
        mv.addObject("entradaForo", new entradaForo());                                       //PASAMOS OBJETO entradaForo

        sql = "select * from entrada order by id_entrada desc";                                            //OBTENEMOS TODOS LOS ELEMENTOS DE LA TABLA ENTRADA ORDENADOS DE MANERA DESCENDENTE A PARTIR DEL ID_ENTRADA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA

        mv.addObject("listaEntradas", datosL2);                                          // PASAMOS EL RESULTADO COMPLETO

        sql = "select id_entrada from entrada order by id_entrada desc";                                  //OBTENEMOS EL ID_ENTRADA DE TODAS LAS ENTRADAS EN LA TABLA ENTRADA ORDENADAS DE MANERA DESCENTE A PARTIR DEL ID_ENTRADA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                             //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA

        int contadorEntradas = datosL2.size();                                                           //ASIGNAMOS EL VALOR DEL TAMAÑO DE LA LISTA OBTENIDA EN LA CONSULTA

        List contadorComentarios = null;                                                              //INICIALIZAMOS LA LISTA contadorComentarios
        String cadena = "";                                                                             //INICIALIZAMOS EL STRING CADENA
        String subcadena = "";                                                                          //INICIALIZAMOS EL STRING SUBCADENA

        cadena = datosL2.get(0).toString();                                                             //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICIÓN O DE LA LISTA DE NUESTRA CONSULTA
        subcadena = cadena.substring(1, cadena.length() - 1);                                              //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA

        sql = "select count(id_comnt) from comentarios where " + subcadena;                              //CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA

        contadorComentarios = this.jdbcTemplate.queryForList(sql);                                    //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        for (int i = 1; i <= contadorEntradas - 1; i++) {                                                     // INICIO DE CICLO FOR EN EL QUE CONTAREMOS DESDE 1 HASTA EL NUMERO DE ENTRADAS - 1

            cadena = datosL2.get(i).toString();                                                            //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE LA LISTA DE NUESTRA CONSULTA 
            subcadena = cadena.substring(1, cadena.length() - 1);                                             //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA
            sql = "select count(id_comnt) from comentarios where " + subcadena;                             // CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA

            contadorComentarios.add(i, this.jdbcTemplate.queryForList(sql));                            //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        }

        List<String> contador = new ArrayList<String>();                                          //CREAMOS UN ARREGLO DE STRINGS EN EL QUE GUARDAREMOS LA CANTIDAD DE COMENTARIOS POR ENTRADA EN EL FORO

        for (int i = 0; i <= contadorEntradas - 1; i++) {                                                   // INICIO DE CICLO FOR 1 EN EL QUE CONTAREMOS DESDE 0 HASTA EL NUMERO DE ENTRADAS -1

            cadena = contadorComentarios.get(i).toString();                                              //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE contadorComentarios

            for (int j = 0; j < cadena.length(); j++) {                                                        //INICIO DE CICLO FOR 2 DONDE CONTAMOS DESDE 0 HASTA LA LONGITUD DE CADENA
                if (cadena.charAt(j) == '=') {                                                             // SI EL CARACTER UBICADO EN LA POSICION J DE CADENA ES IGUAL AL SIMBOLO =    INICIO DE IF 1

                    if (cadena.endsWith("}")) {                                                         // SI NUESTRA CADENA TERMINA CON EL SIMBOLO } INICIO DE IF 2
                        subcadena = cadena.substring(j + 1, cadena.length() - 1);                                      // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -1

                        contador.add(i, subcadena);                                                          // AGREGAMOS LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR

                        j = cadena.length();                                                                      // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                    }                                                                                        // CIERRE DE IF 2
                    if (cadena.endsWith("]")) {                                                          // SI NUESTRA CADENA TERMINA CON EL SIMOBOLO } INICIO DE IF 3
                        subcadena = cadena.substring(j + 1, cadena.length() - 2);                                   // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -2

                        contador.add(i, subcadena);                                                          // AGREGAMOOS  LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR

                        j = cadena.length();                                                                    // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                    }                                                                                        // CIERRE IF 3

                }                                                                                   // CIERRE IF 1
            }                                                                                      // CIERRE FOR 2

        }                                                                                       //FINAL DE CICLO FOR 1

        mv.addObject("contadorComentarios", contador);                                        //PASAMOS OBJETO contador

        return mv;                                                                               // RETORNAMOS EL MODELO

    }

    ////////////////////////
    /////ACCION DEL BOTÓN AGREGAR COMENTARIO
    @RequestMapping(params = "AgregarComentario", method = RequestMethod.POST)
    public ModelAndView AgregarComentario(@ModelAttribute("Comentario") Comentario comen, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.comentarioValidar.validate(comen, result);
        // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
        if (result.hasErrors()) {                                                        // INICIO IF

            //volvemos al formulario porque los datos ingresados son incorrectos
            ModelAndView mv = new ModelAndView();                          //CREACIÓN DEL MODELO
            mv.setViewName("ConsultarEntradaN");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            String sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
            mv.addObject("entradaForo", new entradaForo());                                      // PASAMOS EL OBJETO entradaForo
            mv.addObject("Comentario", new Comentario());                                        // PASAMOS EL OBJETO Comentario

            sql = "select * from entrada where id_entrada=" + comen.getId_entrada();                              //CONSULTAMOS LA ENTRADA CON EL ID id_entrada
            datosL2 = this.jdbcTemplate.queryForList(sql);                                               // ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("Entrada", datosL2);                                                 // PASAMOS EL RESULTADO DE LA CONSULTA

            sql = "select * from comentarios where id_entrada=" + comen.getId_entrada() + " order by id_comnt";     // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
            datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaComentarios", datosL2);                                      // PASAMOS EL RESULTADO DE LA CONSULTA

            sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            return mv;                                                                                           // RETORNAMOS EL MODELO

        } // CIERRE IF
        else {

            String contenido = comen.getContenido();

            String subContenido = "";
            for (int i = 0; i < comen.getContenido().length(); i++) {
                if (contenido.charAt(i) == '\'') {
                    subContenido = subContenido + "\\'";
                } else {
                    if (contenido.charAt(i) == '\\') {
                        subContenido = subContenido + "\\\\";
                    } else {
                        subContenido = subContenido + contenido.charAt(i);
                    }

                }
            }

            String sql = "insert into comentarios values(" + '0' + "," + comen.getId_entrada() + ",'" + comen.getId_usuario() + "','" + subContenido + "','','" + comen.getFecha() + "');";  // INSERTAMOS EL COMENTARIO EN LA ENTRADA CON EL ID id_entrada EL TITULO Y EL CONTENIDO

            this.jdbcTemplate.update(sql);       // INSERTAMOS EL COMENTARIO

            ModelAndView mv = new ModelAndView();                               //CREACIÓN DEL MODELO
            mv.setViewName("ConsultarEntradaN");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
            mv.addObject("entradaForo", new entradaForo());                                         // PASAMOS EL OBJETO entradaForo

            sql = "select * from entrada where id_entrada=" + comen.getId_entrada();                                 // CONSULTA PARA OBTENER LA ENTRADA CON EL ID id_entrada
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                 // ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("Entrada", datosL2);                                                  // PASAMOS EL RESULTADO DE LA CONSULTA
            mv.addObject("Comentario", new Comentario());                                    // PASAMOS EL OBJETO Comentario

            sql = "select * from comentarios where id_entrada=" + comen.getId_entrada() + " order by id_comnt";      // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADO A LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
            datosL2 = this.jdbcTemplate.queryForList(sql);                                               // ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaComentarios", datosL2);                                        // PASAMOS EL RESULTADO DE LA CONSULTA 

            sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

            mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

            return mv;                                                                                           // RETORNAMOS EL MODELO

        }                                                                                                 // CIERRE ELSE

    }

    /////////////////////////////
    /////ACCION DEL BOTÓN EliminarComentario
    @RequestMapping(params = "EliminarComentario", method = RequestMethod.POST)
    public ModelAndView EliminarComentario(@ModelAttribute("Comentario") Comentario comen, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        String sql = "delete  from comentarios where id_comnt=" + comen.getId_comnt();     //ELIMINAMOS EL COMENTARIO RELACIONADO CON EL ID id_comnt
        this.jdbcTemplate.update(sql);                                    //ELIMINAMOS EL COMENTARIO

        ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
        mv.setViewName("ConsultarEntradaN");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        sql = "select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO NUTRIOLOGO
        mv.addObject("entradaForo", new entradaForo());                                      // PASAMOS EL OBJETO entradaForo

        sql = "select * from entrada where id_entrada=" + comen.getId_entrada();                                // CONSULTA PARA OBTENER LA ENTRADA CON EL ID id_entrada
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                    // ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("Entrada", datosL2);                                                  // PASAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("Comentario", new Comentario());                                     // PASAMOS EL OBJETO Comentario

        sql = "select * from comentarios where id_entrada=" + comen.getId_entrada() + " order by id_comnt";   // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS CON LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
        datosL2 = this.jdbcTemplate.queryForList(sql);                                             //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaComentarios", datosL2);                                       // PASAMOS EL RESULTADO DE LA CONSULTA

        sql = "select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaNutriologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaPsicologos", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        sql = "select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
        datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA

        mv.addObject("ListaAdministrador", datosL2);                                           //PASAMOS LA LISTA COMPLETA  

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }

    ///////////////////////////////////
    //////////////Vista expedienteNutriologo
    @RequestMapping(params = "expediente", method = RequestMethod.POST)
    public ModelAndView ConsultarExpedienteNutriologico(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        ModelAndView mv = new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("expedienteNutri");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio,correo  from paciente where no_boleta=" + p.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                                       // Pasa la lilsta completa               

        sql = "select no_cedulap from paciente where no_boleta=" + p.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("pacientePsicologo", datosL2);                                                       // Pasa la lilsta completa               
        mv.addObject("paciente", new Paciente());

        sql = "select * from entrada where id_usuario=" + p.getNo_boleta() + " order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("listaEntradas", datosL2);                                               // PASAMOS LA LISTA COMPLETA

        //  System.out.println("LISTA ENTRADAS: "+datosL2);
        sql = "select id_entrada,fecha from comentarios where id_usuario=" + p.getNo_boleta() + " order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("FechaComentarios", datosL2);

        sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("NombreEntrada", datosL2);                                               // PASAMOS LA LISTA COMPLETA            

        mv.addObject("entradaForo", new entradaForo());                                   //PASAMOS EL OBJETO ENTRADA EN EL FORO

        sql = "select no_cedula from nutriologo where no_empleado=" + alert;                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);

        String cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

        sql = "select no_cita,fecha,horario from cita where no_boleta=" + p.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        // PASAMOS LA LISTA COMPLETA            

        //  String cita=datosL2.get(0).toString().substring(9, datosL2.get(0).toString().length()-1); 
        //   System.out.println("numero de cita: "+cita);
        mv.addObject("datosCita", datosL2);
        mv.addObject("cita", new cita());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Fecha: " + dateFormat.format(date));

        sql = "select fecha from cita where no_boleta=" + p.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        int fechaCita = 0;
        if (datosL2.isEmpty()) {
            fechaCita = 0;
        } else {
            System.out.println("FECHA DE CITA: " + datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length() - 1));
            String fechaAct = datosL2.get(0).toString().substring(7, 11) + "/" + datosL2.get(0).toString().substring(12, 14) + "/" + datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length() - 1);
            System.out.println("FECHA ACTUAL: " + fechaAct);

            if (fechaAct.equals(dateFormat.format(date))) {
                fechaCita = 1;
            }
        }

        mv.addObject("fechaCita", fechaCita);

        sql = "select fecha_ini from expediente where no_boleta=" + p.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        mv.addObject("fechaExpediente", datosL2);

        sql = "select id_expediente from expediente where no_boleta=" + p.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        int expedienteActivo = 0;
        if (datosL2.isEmpty()) {
            expedienteActivo = 0;
        } else {
            expedienteActivo = 1;
        }
        mv.addObject("expedienteActivo", expedienteActivo);
        mv.addObject("expediente", new expediente());
        System.out.println(expedienteActivo);
        return mv;

    }

    /////////////////////////////
    /////ACCION DEL BOTÓN AtenderCita
    @RequestMapping(params = "AtenderCita", method = RequestMethod.POST)
    public ModelAndView AtenderCita(@ModelAttribute("cita") cita c, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
        mv.setViewName("expediente_nutriologo");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_boleta from cita where no_cita=" + c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        if (datosL2.isEmpty()) {
            sql = "select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta=" + c.getNo_boleta();
            datosL2 = this.jdbcTemplate.queryForList(sql);
        } else {
            String boleta = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);
            sql = "select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta=" + boleta;
            datosL2 = this.jdbcTemplate.queryForList(sql);
        }

        mv.addObject("datosPaciente", datosL2);
        mv.addObject("datosCita", c.getNo_cita());                                                       // Pasa la lilsta completa
        mv.addObject("expediente", new expediente());
        mv.addObject("cita", new cita());

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }
/////////////////////////////
    /////ACCION DEL BOTÓN FinalizarCitaN
    @RequestMapping(params = "FinalizarCitaN", method = RequestMethod.POST)
    public ModelAndView FinalizarCitaNutriologo(@ModelAttribute("cita") cita c, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
       
         String sql = "update cita set estado=2  where no_cita=" + c.getNo_cita(); // ACTIVAMOS LA CITA

            this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA

       
       
       
       
        ModelAndView mv = new ModelAndView();                            //CREACIÓN DEL MODELO INICIO DE METODO
        mv.setViewName("expedienteNutri");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

         sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio,correo  from paciente where no_boleta=" + c.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                                       // Pasa la lilsta completa               

        sql = "select no_cedulap from paciente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("pacientePsicologo", datosL2);                                                       // Pasa la lilsta completa               
        mv.addObject("paciente", new Paciente());

        sql = "select * from entrada where id_usuario=" + c.getNo_boleta() + " order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("listaEntradas", datosL2);                                               // PASAMOS LA LISTA COMPLETA

        //  System.out.println("LISTA ENTRADAS: "+datosL2);
        sql = "select id_entrada,fecha from comentarios where id_usuario=" + c.getNo_boleta() + " order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("FechaComentarios", datosL2);

        sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("NombreEntrada", datosL2);                                               // PASAMOS LA LISTA COMPLETA            

        mv.addObject("entradaForo", new entradaForo());                                   //PASAMOS EL OBJETO ENTRADA EN EL FORO

        sql = "select no_cedula from nutriologo where no_empleado=" + alert;                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);

        String cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

        sql = "select no_cita,fecha,horario from cita where no_boleta=" + c.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        // PASAMOS LA LISTA COMPLETA            

        //  String cita=datosL2.get(0).toString().substring(9, datosL2.get(0).toString().length()-1); 
        //   System.out.println("numero de cita: "+cita);
        mv.addObject("datosCita", datosL2);
        mv.addObject("cita", new cita());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Fecha: " + dateFormat.format(date));

        sql = "select fecha from cita where no_boleta=" + c.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        int fechaCita = 0;
        if (datosL2.isEmpty()) {
            fechaCita = 0;
        } else {
            System.out.println("FECHA DE CITA: " + datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length() - 1));
            String fechaAct = datosL2.get(0).toString().substring(7, 11) + "/" + datosL2.get(0).toString().substring(12, 14) + "/" + datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length() - 1);
            System.out.println("FECHA ACTUAL: " + fechaAct);

            if (fechaAct.equals(dateFormat.format(date))) {
                fechaCita = 1;
            }
        }

        mv.addObject("fechaCita", fechaCita);

        sql = "select fecha_ini from expediente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        mv.addObject("fechaExpediente", datosL2);

        sql = "select id_expediente from expediente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        int expedienteActivo = 0;
        if (datosL2.isEmpty()) {
            expedienteActivo = 0;
        } else {
            expedienteActivo = 1;
        }
        mv.addObject("expedienteActivo", expedienteActivo);
        mv.addObject("expediente", new expediente());
        return mv;          // FINAL DE METODO

    
    }

    ///////////////////////////////
    ////Vista para canalizar a psicologia
    @RequestMapping(params = "CanalizarPsicologia", method = RequestMethod.POST)
    public ModelAndView CanalizarPsicologia(@ModelAttribute("paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
        mv.setViewName("CanalizarPsicologia");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select nombre,ap_uno,ap_dos, no_boleta from paciente where no_boleta=" + p.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datosPaciente", datosL2);                                                       // Pasa la lilsta completa

        sql = "select nombre,ap_uno,ap_dos, no_cedula from psicologo where estatus =1;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

        datosL2 = this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta

        mv.addObject("ListaESCOM", datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO    

        mv.addObject("cita", new cita());

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }

    ///////////////////////////////
    ////Vista para agendar siguiente cita
    @RequestMapping(params = "AgendarCita", method = RequestMethod.POST)
    public ModelAndView AgendarSiguienteCita(@ModelAttribute("paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
        mv.setViewName("CitaNutriologo");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        String cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

        sql = "select nombre,ap_uno,ap_dos, no_boleta from paciente where no_boleta=" + p.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datosPaciente", datosL2);                                                       // Pasa la lilsta completa

        sql = "select nombre,ap_uno,ap_dos, no_cedula from nutriologo where estatus =1 and no_cedula=" + cedula; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

        datosL2 = this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta
        System.out.println("NUTRIOLOGO: " + datosL2);
        mv.addObject("ListaESCOM", datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO    

        mv.addObject("cita", new cita());

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }

    ///////////////////////////////
    //PETICION DE PRIMERA CITA PSICOLOGO
    @RequestMapping(value = "/canalizar", method = RequestMethod.GET)
    public @ResponseBody
    String getHORARIO(@RequestParam String fechaConsulta, @RequestParam String cedulaConsulta) {
        System.out.println("-----getHORARIO-------");

        System.out.println(fechaConsulta + " esta fue la fecha");
        System.out.println(cedulaConsulta + " esta es la cedula");
        String sql = " select no_cita,horario from cita where fecha='" + fechaConsulta + "' and no_cedula=" + cedulaConsulta + " and estado=0";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        System.out.println(datosL2);
        String objeto = new Gson().toJson(datosL2);

        return objeto;
    }

    ///////////////////////////////
    //PETICION DE SIGUIENTE CITA
    @RequestMapping(value = "/siguienteNutriologo", method = RequestMethod.GET)
    public @ResponseBody
    String getCitaNutriologo(@RequestParam String fechaConsulta, @RequestParam String cedulaConsulta) {
        System.out.println("-----getHORARIO-------");

        System.out.println(fechaConsulta + " esta fue la fecha");
        System.out.println(cedulaConsulta + " esta es la cedula");
        String sql = " select no_cita,horario from cita where fecha='" + fechaConsulta + "' and no_cedula=" + cedulaConsulta + " and estado=1";   // CONSULTA PARA EXTRAER DATOS HORARIOS
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        System.out.println(datosL2);
        String objeto = new Gson().toJson(datosL2);

        return objeto;
    }

    /////////////////////////////
    /////ACCION DEL BOTÓN CANALIZAR PSICOLOGO
    @RequestMapping(params = "CanalizarPsicolgo", method = RequestMethod.POST)
    public ModelAndView CanalizarPsicolgo(@ModelAttribute("cita") cita c, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.citaValidar.validate(c, result);
        // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
        if (result.hasErrors()) {                                                        // INICIO IF

            //volvemos al formulario porque los datos ingresados son incorrectos
            ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
            mv.setViewName("CanalizarPsicologia");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

            sql = "select nombre,ap_uno,ap_dos, no_boleta from paciente where no_boleta=" + c.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datosPaciente", datosL2);                                                       // Pasa la lilsta completa

            sql = "select nombre,ap_uno,ap_dos, no_cedula from psicologo where estatus =1;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

            datosL2 = this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta

            mv.addObject("ListaESCOM", datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO    

            mv.addObject("cita", new cita());

            return mv;                                                                                           // RETORNAMOS EL MODELO

        } // CIERRE IF
        else {

            String sql = "update cita set estado=3, no_boleta=" + c.getNo_boleta() + " where no_cita=" + c.getNo_cita(); // ACTIVAMOS LA CITA

            this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA

            sql = "select horario from cita where no_cita=" + c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String horarioBase = datosL2.get(0).toString().substring(9, datosL2.get(0).toString().length() - 1);

            sql = "select no_cedula from cita where no_cita=" + c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

            sql = "select fecha from cita where no_cita=" + c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String fecha = datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length() - 1);

            sql = "select horario from cita where no_cedula=" + cedula + " and fecha='" + fecha + "' and estado=1";   // CONSULTA PARA EXTRAER  HORARIOS DE CITA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String horario1 = "";       //VARIABLE PARA HORARIO QUE COMPARAREMOS
            String subHorario = "";      // VARIABLE EN LA QUE SE DIVIDEN LAS HORAS DEL HORARIO QUE SE COMPARARÁ
            String subMinutoHorario1 = "";   // VARIABLE PARA CALCULO DE MINUTOS DEL HORARIO A COMPARAR
            String subMinutoBase = "";     // VARIABLE PARA CALCULOS DE MINUTOS DEL HORARIO BASE
            String subHorarioBase = ""; // VARIABLE PARA EN LA QUE SE DIVIDEN LAS HORAS DEL HORARIO BASE

            for (int x = 0; x < datosL2.size(); x++) {                                                         // INICIO DE CICLO FOR 1 DE X= 0 HASTA EL TAMAÑO DE LA LISTA DE HORARIOS
                horario1 = datosL2.get(x).toString().substring(9, datosL2.get(x).toString().length() - 1);    // CREACION DEL HORARIO A COMPARAR

                //PARA COMPARAR PRIMERA HORA POR LA IZQUIERDA
                for (int i = 0; i < horario1.length(); i++) {                                                  // INICIO CICLO FOR 2

                    if (horario1.charAt(i) == ':') {                                                    // INICIO IF 1 EN EL QUE SE COMPARA EL CARACTER DEL HORARIO EN EL INDICE I CON EL SIMBOLO :
                        subHorario = horario1.substring(0, i);                                          // SEPARACION DEL HORARIO
                        subMinutoHorario1 = horario1.substring(i + 1, i + 3);                              // CALCULO DE MINUTOS DEL HORARIO
                        i = horario1.length();                                                       // FINALIZACIÓN DEL IF AL ASIGNAR A I EL VALOR DE LA LONGITUD DEL HORARIO
                    }                                                                            // CIERRE IF 1

                }                                                                               // CIERRE FOR 2
                for (int i = 0; i < horarioBase.length(); i++) {                                      // INICIO CICLO FOR 3
                    if (horarioBase.charAt(i) == ':') {                                            // INICIO IF 2 EN EL QUE SE COMÁRA EL CARACTER DEL HORARIO BASE EN EL INDICE I CON EL SIMBOLO :
                        subHorarioBase = horarioBase.substring(0, i);                            // SEPARACION DEL HORARIO BASE
                        subMinutoBase = horarioBase.substring(i + 1, i + 3);                      // CALCULO DE MINUTOS DEL HORARIO BASE
                        i = horarioBase.length();                                           // FINALIZACION DEL IF AL ASIFNAR A I EL VALRO DE LA LONGITUD DEL HORARIO BASE
                    }                                                                 // CIERRE IF 2

                }                                                                         // CIERRE FOR 3

                int horaInicio = Integer.parseInt(subHorarioBase); // PASAR HORA A ENTERO

                int horaInicioCompara = Integer.parseInt(subHorario); // PASAR HORA A ENTERO

                float MinInicio = Float.parseFloat(subMinutoBase); // PASAR MINUTOS A FLOTANTE
                float MinFin = Float.parseFloat(subMinutoHorario1);     // PASAR MINUTOS A FLOTANTE

                float minI = MinInicio / 60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
                float minS = MinFin / 60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 

                float horaE = horaInicio + minI;    // GENERACIÓN DE HORA DE ENTRADA
                float horaSa = horaInicioCompara + minS;    // GENERACIÓN DE HORA A COMPARAR

                if (horaE == horaSa) {                         // COMPARACION DE HORA BASE CON HORA DE COMPARACION

                    sql = "update cita set estado=4 where fecha='" + fecha + "' and horario='" + horario1 + "' and no_cedula=" + cedula; // BLOQUEAMOS HORARIOS QUE INTERFIERAN CON LA CITA

                    this.jdbcTemplate.update(sql);       // INSERTAMOS LOS BLOQUEOS
                }

                //PARA COMPARAR SEGUNDA HORA POR LA DERECHA
                int j = 0;              // VARIABLE BANDERA PARA CALCULO DE HORARIOS POR LA DERECHA
                int k = 0;                      // VARIABLE BANDERA PARA CALCULO DE HORARIO POR LA DERECHA
                for (int i = 0; i < horario1.length(); i++) {                 // INICIO FOR 1 POR LA DERECHA

                    if (horario1.charAt(i) == '-') {                     // INICIO IF 1 POR LA DERECHA
                        j = 1;                                         // ASIGNACIÓN DE VALOR A NUESTRA BANDERA J
                        k = i;                                        // ASIGNACION DE VALOR A NUESTRA BANDERA K
                    }                                              // FIN IF 1 POR LA DERECHA
                    if (j == 1 && horario1.charAt(i) == ':') {               // INICIO IF 2 POR LA DERECHA
                        subHorario = horario1.substring(k + 1, i);          // DIVISION DEL SUBHORARIO POR LA DERECHA
                        subMinutoHorario1 = horario1.substring(i + 1, i + 3);   // DIVISION DE LOS MINUTOS POR LA DERECHA
                        i = horario1.length();                            // FINALIZACIÓN DEL IF AL ASIGNAR EL VALOR DE i IGUAL A LA LONGITUDE DEL HORARIO
                        j = 0;                                        // RESTABLECIMIENTO DE BANDERA J
                        k = 0;                                      // RESTABLECIMIENTO DE BANDERA K
                    }                                            // CIERRE IF 2 POR LA DERECHA

                }                                              // CIERRE FOR 1 POR LA DERECHA
                for (int i = 0; i < horarioBase.length(); i++) {        // INICIO FOR 2 POR LA DERECHA
                    if (horarioBase.charAt(i) == '-') {              // INICIO IF 2 POR LA DERECHA EN EL QUE SE COMPARA EL CARACTER DEL HORARIO BASE EN EL INDICE i CON EL CARACTER -
                        j = 1;                                      // ASIGNACION DE VALOR A LA BANDERA J
                        k = i;                                      // ASIGNACION DE VALOR A LA BANDERA K
                    }                                               // CIERRE IF 2 POR LA DERECHA

                    if (j == 1 && horarioBase.charAt(i) == ':') {           // INICIO IF 3 POR LA DERECHA
                        subHorarioBase = horarioBase.substring(k + 1, i);     // DIVICION DEL HORARIO BASE 
                        subMinutoBase = horarioBase.substring(i + 1, i + 3);     // DIVISION DE MINUTOS DEL HORARIO BASE
                        i = horarioBase.length();                             // FINALIZACIÓN DE IF 3 POR LA DERECHA AL ASIGNAR A i EL VALOR DE LA LONGITU DEL HORARIO BASE
                        j = 0;                                       // RESTABLECIMIENTO DE ANDERA J
                        k = 0;                                            // RESTABLECIMIENTO DE BANDERA K
                    }                                                 // CIERRE IF 3 POR LA DERECHA

                }                                                   // CIERRE FOR 2 POR LA DERECHA

                horaInicio = Integer.parseInt(subHorarioBase); // PASAR HORA A ENTERO

                horaInicioCompara = Integer.parseInt(subHorario); // PASAR HORA A ENTERO

                MinInicio = Float.parseFloat(subMinutoBase); // PASAR MINUTOS A FLOTANTE
                MinFin = Float.parseFloat(subMinutoHorario1);     // PASAR MINUTOS A FLOTANTE

                minI = MinInicio / 60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
                minS = MinFin / 60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 

                horaE = horaInicio + minI;    // GENERACIÓN DE HORA DE ENTRADA
                horaSa = horaInicioCompara + minS;    // GENERACIÓN DE HORA DE SALIDA

                if (horaE == horaSa) {

                    sql = "update cita set estado=4 where fecha='" + fecha + "' and horario='" + horario1 + "' and no_cedula=" + cedula; // ACTIVAMOS LA CITA

                    this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA

                }

            }

            sql = "update paciente set no_cedulap=" + cedula + " where no_boleta=" + c.getNo_boleta(); // ACTIVAMOS LA CITA

            this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA

            
        ModelAndView mv = new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("expedienteNutri");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

         sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio,correo  from paciente where no_boleta=" + c.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                                       // Pasa la lilsta completa               

        sql = "select no_cedulap from paciente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("pacientePsicologo", datosL2);                                                       // Pasa la lilsta completa               
        mv.addObject("paciente", new Paciente());

        sql = "select * from entrada where id_usuario=" + c.getNo_boleta() + " order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("listaEntradas", datosL2);                                               // PASAMOS LA LISTA COMPLETA

        //  System.out.println("LISTA ENTRADAS: "+datosL2);
        sql = "select id_entrada,fecha from comentarios where id_usuario=" + c.getNo_boleta() + " order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("FechaComentarios", datosL2);

        sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("NombreEntrada", datosL2);                                               // PASAMOS LA LISTA COMPLETA            

        mv.addObject("entradaForo", new entradaForo());                                   //PASAMOS EL OBJETO ENTRADA EN EL FORO

        sql = "select no_cedula from nutriologo where no_empleado=" + alert;                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);

        cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

        sql = "select no_cita,fecha,horario from cita where no_boleta=" + c.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        // PASAMOS LA LISTA COMPLETA            

        //  String cita=datosL2.get(0).toString().substring(9, datosL2.get(0).toString().length()-1); 
        //   System.out.println("numero de cita: "+cita);
        mv.addObject("datosCita", datosL2);
        mv.addObject("cita", new cita());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Fecha: " + dateFormat.format(date));

        sql = "select fecha from cita where no_boleta=" + c.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        int fechaCita = 0;
        if (datosL2.isEmpty()) {
            fechaCita = 0;
        } else {
            System.out.println("FECHA DE CITA: " + datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length() - 1));
            String fechaAct = datosL2.get(0).toString().substring(7, 11) + "/" + datosL2.get(0).toString().substring(12, 14) + "/" + datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length() - 1);
            System.out.println("FECHA ACTUAL: " + fechaAct);

            if (fechaAct.equals(dateFormat.format(date))) {
                fechaCita = 1;
            }
        }

        mv.addObject("fechaCita", fechaCita);

        sql = "select fecha_ini from expediente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        mv.addObject("fechaExpediente", datosL2);

        sql = "select id_expediente from expediente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        int expedienteActivo = 0;
        if (datosL2.isEmpty()) {
            expedienteActivo = 0;
        } else {
            expedienteActivo = 1;
        }
        mv.addObject("expedienteActivo", expedienteActivo);
        mv.addObject("expediente", new expediente());
        return mv;


           

        }

    }                                                                                                 // CIERRE ELSE

    /////////////////////////////
    /////ACCION DEL BOTÓN SIGUIENTE CITA
    @RequestMapping(params = "SiguienteCita", method = RequestMethod.POST)
    public ModelAndView SiguienteCita(@ModelAttribute("cita") cita c, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.citaValidar.validate(c, result);
        // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
        if (result.hasErrors()) {                                                        // INICIO IF

            //volvemos al formulario porque los datos ingresados son incorrectos
            ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
            mv.setViewName("CitaNutriologo");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

            String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
            mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

            sql = "select nombre,ap_uno,ap_dos, no_boleta from paciente where no_boleta=" + c.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            mv.addObject("datosPaciente", datosL2);                                                       // Pasa la lilsta completa

            sql = "select nombre,ap_uno,ap_dos, no_cedula from nutriologo where estatus =1 and no_cedula=" + c.getNo_cedula(); // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

            datosL2 = this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta

            mv.addObject("ListaESCOM", datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO    

            mv.addObject("cita", new cita());

            return mv;                                                                                           // RETORNAMOS EL MODELO

        } // CIERRE IF
        else {

            String sql = "update cita set estado=3, no_boleta=" + c.getNo_boleta() + " where no_cita=" + c.getNo_cita(); // ACTIVAMOS LA CITA

            this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA

            sql = "select horario from cita where no_cita=" + c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
            List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String horarioBase = datosL2.get(0).toString().substring(9, datosL2.get(0).toString().length() - 1);

            sql = "select no_cedula from cita where no_cita=" + c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

            sql = "select fecha from cita where no_cita=" + c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String fecha = datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length() - 1);

            sql = "select horario from cita where no_cedula=" + cedula + " and fecha='" + fecha + "' and estado=0";   // CONSULTA PARA EXTRAER  HORARIOS DE CITA
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

            String horario1 = "";       //VARIABLE PARA HORARIO QUE COMPARAREMOS
            String subHorario = "";      // VARIABLE EN LA QUE SE DIVIDEN LAS HORAS DEL HORARIO QUE SE COMPARARÁ
            String subMinutoHorario1 = "";   // VARIABLE PARA CALCULO DE MINUTOS DEL HORARIO A COMPARAR
            String subMinutoBase = "";     // VARIABLE PARA CALCULOS DE MINUTOS DEL HORARIO BASE
            String subHorarioBase = ""; // VARIABLE PARA EN LA QUE SE DIVIDEN LAS HORAS DEL HORARIO BASE

            for (int x = 0; x < datosL2.size(); x++) {                                                         // INICIO DE CICLO FOR 1 DE X= 0 HASTA EL TAMAÑO DE LA LISTA DE HORARIOS
                horario1 = datosL2.get(x).toString().substring(9, datosL2.get(x).toString().length() - 1);    // CREACION DEL HORARIO A COMPARAR

                //PARA COMPARAR PRIMERA HORA POR LA IZQUIERDA
                for (int i = 0; i < horario1.length(); i++) {                                                  // INICIO CICLO FOR 2

                    if (horario1.charAt(i) == ':') {                                                    // INICIO IF 1 EN EL QUE SE COMPARA EL CARACTER DEL HORARIO EN EL INDICE I CON EL SIMBOLO :
                        subHorario = horario1.substring(0, i);                                          // SEPARACION DEL HORARIO
                        subMinutoHorario1 = horario1.substring(i + 1, i + 3);                              // CALCULO DE MINUTOS DEL HORARIO
                        i = horario1.length();                                                       // FINALIZACIÓN DEL IF AL ASIGNAR A I EL VALOR DE LA LONGITUD DEL HORARIO
                    }                                                                            // CIERRE IF 1

                }                                                                               // CIERRE FOR 2
                for (int i = 0; i < horarioBase.length(); i++) {                                      // INICIO CICLO FOR 3
                    if (horarioBase.charAt(i) == ':') {                                            // INICIO IF 2 EN EL QUE SE COMÁRA EL CARACTER DEL HORARIO BASE EN EL INDICE I CON EL SIMBOLO :
                        subHorarioBase = horarioBase.substring(0, i);                            // SEPARACION DEL HORARIO BASE
                        subMinutoBase = horarioBase.substring(i + 1, i + 3);                      // CALCULO DE MINUTOS DEL HORARIO BASE
                        i = horarioBase.length();                                           // FINALIZACION DEL IF AL ASIFNAR A I EL VALRO DE LA LONGITUD DEL HORARIO BASE
                    }                                                                 // CIERRE IF 2

                }                                                                         // CIERRE FOR 3

                int horaInicio = Integer.parseInt(subHorarioBase); // PASAR HORA A ENTERO

                int horaInicioCompara = Integer.parseInt(subHorario); // PASAR HORA A ENTERO

                float MinInicio = Float.parseFloat(subMinutoBase); // PASAR MINUTOS A FLOTANTE
                float MinFin = Float.parseFloat(subMinutoHorario1);     // PASAR MINUTOS A FLOTANTE

                float minI = MinInicio / 60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
                float minS = MinFin / 60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 

                float horaE = horaInicio + minI;    // GENERACIÓN DE HORA DE ENTRADA
                float horaSa = horaInicioCompara + minS;    // GENERACIÓN DE HORA A COMPARAR

                if (horaE == horaSa) {                         // COMPARACION DE HORA BASE CON HORA DE COMPARACION

                    sql = "update cita set estado=4 where fecha='" + fecha + "' and horario='" + horario1 + "' and no_cedula=" + cedula; // BLOQUEAMOS HORARIOS QUE INTERFIERAN CON LA CITA

                    this.jdbcTemplate.update(sql);       // INSERTAMOS LOS BLOQUEOS
                }

                //PARA COMPARAR SEGUNDA HORA POR LA DERECHA
                int j = 0;              // VARIABLE BANDERA PARA CALCULO DE HORARIOS POR LA DERECHA
                int k = 0;                      // VARIABLE BANDERA PARA CALCULO DE HORARIO POR LA DERECHA
                for (int i = 0; i < horario1.length(); i++) {                 // INICIO FOR 1 POR LA DERECHA

                    if (horario1.charAt(i) == '-') {                     // INICIO IF 1 POR LA DERECHA
                        j = 1;                                         // ASIGNACIÓN DE VALOR A NUESTRA BANDERA J
                        k = i;                                        // ASIGNACION DE VALOR A NUESTRA BANDERA K
                    }                                              // FIN IF 1 POR LA DERECHA
                    if (j == 1 && horario1.charAt(i) == ':') {               // INICIO IF 2 POR LA DERECHA
                        subHorario = horario1.substring(k + 1, i);          // DIVISION DEL SUBHORARIO POR LA DERECHA
                        subMinutoHorario1 = horario1.substring(i + 1, i + 3);   // DIVISION DE LOS MINUTOS POR LA DERECHA
                        i = horario1.length();                            // FINALIZACIÓN DEL IF AL ASIGNAR EL VALOR DE i IGUAL A LA LONGITUDE DEL HORARIO
                        j = 0;                                        // RESTABLECIMIENTO DE BANDERA J
                        k = 0;                                      // RESTABLECIMIENTO DE BANDERA K
                    }                                            // CIERRE IF 2 POR LA DERECHA

                }                                              // CIERRE FOR 1 POR LA DERECHA
                for (int i = 0; i < horarioBase.length(); i++) {        // INICIO FOR 2 POR LA DERECHA
                    if (horarioBase.charAt(i) == '-') {              // INICIO IF 2 POR LA DERECHA EN EL QUE SE COMPARA EL CARACTER DEL HORARIO BASE EN EL INDICE i CON EL CARACTER -
                        j = 1;                                      // ASIGNACION DE VALOR A LA BANDERA J
                        k = i;                                      // ASIGNACION DE VALOR A LA BANDERA K
                    }                                               // CIERRE IF 2 POR LA DERECHA

                    if (j == 1 && horarioBase.charAt(i) == ':') {           // INICIO IF 3 POR LA DERECHA
                        subHorarioBase = horarioBase.substring(k + 1, i);     // DIVICION DEL HORARIO BASE 
                        subMinutoBase = horarioBase.substring(i + 1, i + 3);     // DIVISION DE MINUTOS DEL HORARIO BASE
                        i = horarioBase.length();                             // FINALIZACIÓN DE IF 3 POR LA DERECHA AL ASIGNAR A i EL VALOR DE LA LONGITU DEL HORARIO BASE
                        j = 0;                                       // RESTABLECIMIENTO DE ANDERA J
                        k = 0;                                            // RESTABLECIMIENTO DE BANDERA K
                    }                                                 // CIERRE IF 3 POR LA DERECHA

                }                                                   // CIERRE FOR 2 POR LA DERECHA

                horaInicio = Integer.parseInt(subHorarioBase); // PASAR HORA A ENTERO

                horaInicioCompara = Integer.parseInt(subHorario); // PASAR HORA A ENTERO

                MinInicio = Float.parseFloat(subMinutoBase); // PASAR MINUTOS A FLOTANTE
                MinFin = Float.parseFloat(subMinutoHorario1);     // PASAR MINUTOS A FLOTANTE

                minI = MinInicio / 60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
                minS = MinFin / 60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 

                horaE = horaInicio + minI;    // GENERACIÓN DE HORA DE ENTRADA
                horaSa = horaInicioCompara + minS;    // GENERACIÓN DE HORA DE SALIDA

                if (horaE == horaSa) {

                    sql = "update cita set estado=4 where fecha='" + fecha + "' and horario='" + horario1 + "' and no_cedula=" + cedula; // ACTIVAMOS LA CITA

                    this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA

                }

            }

             ModelAndView mv = new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("expedienteNutri");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

         sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio,correo  from paciente where no_boleta=" + c.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                                       // Pasa la lilsta completa               

        sql = "select no_cedulap from paciente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("pacientePsicologo", datosL2);                                                       // Pasa la lilsta completa               
        mv.addObject("paciente", new Paciente());

        sql = "select * from entrada where id_usuario=" + c.getNo_boleta() + " order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("listaEntradas", datosL2);                                               // PASAMOS LA LISTA COMPLETA

        //  System.out.println("LISTA ENTRADAS: "+datosL2);
        sql = "select id_entrada,fecha from comentarios where id_usuario=" + c.getNo_boleta() + " order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("FechaComentarios", datosL2);

        sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("NombreEntrada", datosL2);                                               // PASAMOS LA LISTA COMPLETA            

        mv.addObject("entradaForo", new entradaForo());                                   //PASAMOS EL OBJETO ENTRADA EN EL FORO

        sql = "select no_cedula from nutriologo where no_empleado=" + alert;                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);

        cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

        sql = "select no_cita,fecha,horario from cita where no_boleta=" + c.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        // PASAMOS LA LISTA COMPLETA            

        //  String cita=datosL2.get(0).toString().substring(9, datosL2.get(0).toString().length()-1); 
        //   System.out.println("numero de cita: "+cita);
        mv.addObject("datosCita", datosL2);
        mv.addObject("cita", new cita());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Fecha: " + dateFormat.format(date));

        sql = "select fecha from cita where no_boleta=" + c.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        int fechaCita = 0;
        if (datosL2.isEmpty()) {
            fechaCita = 0;
        } else {
            System.out.println("FECHA DE CITA: " + datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length() - 1));
            String fechaAct = datosL2.get(0).toString().substring(7, 11) + "/" + datosL2.get(0).toString().substring(12, 14) + "/" + datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length() - 1);
            System.out.println("FECHA ACTUAL: " + fechaAct);

            if (fechaAct.equals(dateFormat.format(date))) {
                fechaCita = 1;
            }
        }

        mv.addObject("fechaCita", fechaCita);

        sql = "select fecha_ini from expediente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        mv.addObject("fechaExpediente", datosL2);

        sql = "select id_expediente from expediente where no_boleta=" + c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        int expedienteActivo = 0;
        if (datosL2.isEmpty()) {
            expedienteActivo = 0;
        } else {
            expedienteActivo = 1;
        }
        mv.addObject("expedienteActivo", expedienteActivo);
        mv.addObject("expediente", new expediente());
        return mv;


        }

    }                                                                                                 // CIERRE ELSE

    /////////////////////////////
    /////ACCION DEL BOTÓN GuardarExpediente
    @RequestMapping(params = "GuardarExpediente", method = RequestMethod.POST)
    public ModelAndView GuardarExpediente(@ModelAttribute("expediente") expediente ex, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }
        
         this.expedienteValidar.validate(ex, result);
        // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
        if (result.hasErrors()) {  // INICIO IF
            ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
        mv.setViewName("expediente_nutriologo");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_boleta from cita where no_boleta=" + ex.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        if (datosL2.isEmpty()) {
            sql = "select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta=" + ex.getNo_boleta();
            datosL2 = this.jdbcTemplate.queryForList(sql);
        } else {
            String boleta = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);
            sql = "select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta=" + boleta;
            datosL2 = this.jdbcTemplate.queryForList(sql);
        }

        mv.addObject("datosPaciente", datosL2);
                                                             
        mv.addObject("expediente", new expediente());
        mv.addObject("cita", new cita());
        
        return mv;
        }
          
        else{
            
        
         String tipoAct = ex.getTipo_act();
           

          
            String subAct = "";
            for (int i = 0; i < ex.getTipo_act().length(); i++) {
                if (ex.getTipo_act().charAt(i) == '\'') {
                   subAct = subAct + "\\'";
                } else {
                    if (ex.getTipo_act().charAt(i) == '\\') {
                        subAct = subAct + "\\\\";
                    } else {
                        subAct = subAct + ex.getTipo_act().charAt(i);
                    }

                }
            }
            
             String padecimiento = ex.getPadecimiento();
           

            String subPade = "";
            for (int i = 0; i < ex.getPadecimiento().length(); i++) {
                if (ex.getPadecimiento().charAt(i) == '\'') {
                   subPade = subPade + "\\'";
                } else {
                    if (ex.getPadecimiento().charAt(i) == '\\') {
                        subPade = subPade + "\\\\";
                    } else {
                        subPade = subPade + ex.getPadecimiento().charAt(i);
                    }

                }
            }
            
            
            String antec_hf = ex.getAntec_hf();
           
            String subAntec_hf = "";
            for (int i = 0; i < ex.getAntec_hf().length(); i++) {
                if (ex.getAntec_hf().charAt(i) == '\'') {
                   subAntec_hf = subAntec_hf + "\\'";
                } else {
                    if (ex.getAntec_hf().charAt(i) == '\\') {
                        subAntec_hf = subAntec_hf + "\\\\";
                    } else {
                        subAntec_hf = subAntec_hf + ex.getAntec_hf().charAt(i);
                    }

                }
            }
            
            String m_anticonceptivo = ex.getM_anticonceptivo();
           
            String subM_anticonceptivo = "";
            for (int i = 0; i < ex.getM_anticonceptivo().length(); i++) {
                if (ex.getM_anticonceptivo().charAt(i) == '\'') {
                   subM_anticonceptivo = subM_anticonceptivo + "\\'";
                } else {
                    if (ex.getM_anticonceptivo().charAt(i) == '\\') {
                        subM_anticonceptivo = subM_anticonceptivo + "\\\\";
                    } else {
                        subM_anticonceptivo = subM_anticonceptivo + ex.getM_anticonceptivo().charAt(i);
                    }

                }
            }
           
            
            String tipoTerapia = ex.getTipoTerapia();
           
            String subTipoTerapia = "";
            for (int i = 0; i < ex.getTipoTerapia().length(); i++) {
                if (ex.getTipoTerapia().charAt(i) == '\'') {
                   subTipoTerapia = subTipoTerapia + "\\'";
                } else {
                    if (ex.getTipoTerapia().charAt(i) == '\\') {
                        subTipoTerapia = subTipoTerapia + "\\\\";
                    } else {
                        subTipoTerapia = subTipoTerapia + ex.getTipoTerapia().charAt(i);
                    }

                }
            }
            String dosis = ex.getDosis();
           
            String subdosis = "";
            for (int i = 0; i < ex.getDosis().length(); i++) {
                if (ex.getDosis().charAt(i) == '\'') {
                   subdosis = subdosis + "\\'";
                } else {
                    if (ex.getDosis().charAt(i) == '\\') {
                       subdosis = subdosis + "\\\\";
                    } else {
                        subdosis = subdosis + ex.getDosis().charAt(i);
                    }

                }
            }
            
               String apct_grls = ex.getAspect_grls();
           
            String subAspec_grls = "";
            for (int i = 0; i < ex.getAspect_grls().length(); i++) {
                if (ex.getAspect_grls().charAt(i) == '\'') {
                   subAspec_grls = subAspec_grls + "\\'";
                } else {
                    if (ex.getAspect_grls().charAt(i) == '\\') {
                       subAspec_grls = subAspec_grls + "\\\\";
                    } else {
                        subAspec_grls = subAspec_grls + ex.getAspect_grls().charAt(i);
                    }

                }
            }
            
            
               String tratamiento = ex.getTratamient();
           
            String subTratamiento = "";
            for (int i = 0; i < ex.getTratamient().length(); i++) {
                if (ex.getTratamient().charAt(i) == '\'') {
                   subTratamiento = subTratamiento + "\\'";
                } else {
                    if (ex.getTratamient().charAt(i) == '\\') {
                       subTratamiento = subTratamiento + "\\\\";
                    } else {
                        subTratamiento = subTratamiento + ex.getTratamient().charAt(i);
                    }

                }
            }
            
            
             String tiempo= ex.getTiempo();
           
            String subTiempo = "";
            for (int i = 0; i < ex.getTiempo().length(); i++) {
                if (ex.getTiempo().charAt(i) == '\'') {
                   subTiempo = subTiempo + "\\'";
                } else {
                    if (ex.getTiempo().charAt(i) == '\\') {
                       subTiempo = subTiempo + "\\\\";
                    } else {
                        subTiempo = subTiempo + ex.getTiempo().charAt(i);
                    }

                }
            }
            
            
              String golosinas= ex.getGolosinas();
           
            String subPostre = "";
            for (int i = 0; i < ex.getGolosinas().length(); i++) {
                if (ex.getGolosinas().charAt(i) == '\'') {
                   subPostre = subPostre + "\\'";
                } else {
                    if (ex.getGolosinas().charAt(i) == '\\') {
                       subPostre = subPostre + "\\\\";
                    } else {
                        subPostre = subPostre + ex.getGolosinas().charAt(i);
                    }

                }
            }
            
             String HC= ex.getHorariosComida();
           
            String subHC= "";
            for (int i = 0; i < ex.getHorariosComida().length(); i++) {
                if (ex.getHorariosComida().charAt(i) == '\'') {
                   subHC = subHC + "\\'";
                } else {
                    if (ex.getHorariosComida().charAt(i) == '\\') {
                       subHC = subHC + "\\\\";
                    } else {
                        subHC = subHC + ex.getHorariosComida().charAt(i);
                    }

                }
            }
            
              String alergias= ex.getAlergias();
           
            String subAlergias= "";
            for (int i = 0; i < ex.getAlergias().length(); i++) {
                if (ex.getAlergias().charAt(i) == '\'') {
                   subAlergias = subAlergias + "\\'";
                } else {
                    if (ex.getAlergias().charAt(i) == '\\') {
                       subAlergias = subAlergias + "\\\\";
                    } else {
                        subAlergias = subAlergias + ex.getAlergias().charAt(i);
                    }

                }
            }
            
               String observaciones= ex.getObservaciones();
           
            String subObservaciones= "";
            for (int i = 0; i < ex.getObservaciones().length(); i++) {
                if (ex.getObservaciones().charAt(i) == '\'') {
                   subObservaciones = subObservaciones + "\\'";
                } else {
                    if (ex.getObservaciones().charAt(i) == '\\') {
                       subObservaciones = subObservaciones + "\\\\";
                    } else {
                        subObservaciones = subObservaciones + ex.getObservaciones().charAt(i);
                    }

                }
            }
            
            
            
               String recomendaciones= ex.getRecomendaciones();
           
            String subRecomendaciones= "";
            for (int i = 0; i < ex.getRecomendaciones().length(); i++) {
                if (ex.getRecomendaciones().charAt(i) == '\'') {
                   subRecomendaciones = subRecomendaciones + "\\'";
                } else {
                    if (ex.getRecomendaciones().charAt(i) == '\\') {
                       subRecomendaciones = subRecomendaciones + "\\\\";
                    } else {
                        subRecomendaciones = subRecomendaciones + ex.getRecomendaciones().charAt(i);
                    }

                }
            }
            
              String tension= ex.getTension_art();
           
            String subTension= "";
            for (int i = 0; i < ex.getTension_art().length(); i++) {
                if (ex.getTension_art().charAt(i) == '\'') {
                   subTension = subTension + "\\'";
                } else {
                    if (ex.getTension_art().charAt(i) == '\\') {
                       subTension = subTension + "\\\\";
                    } else {
                        subTension = subTension + ex.getTension_art().charAt(i);
                    }

                }
            }
            
              String frecuencia= ex.getFec_cardiaca();
           
            String subFrecuencia= "";
            for (int i = 0; i < ex.getFec_cardiaca().length(); i++) {
                if (ex.getFec_cardiaca().charAt(i) == '\'') {
                   subFrecuencia = subFrecuencia + "\\'";
                } else {
                    if (ex.getFec_cardiaca().charAt(i) == '\\') {
                       subFrecuencia = subFrecuencia + "\\\\";
                    } else {
                        subFrecuencia =subFrecuencia + ex.getFec_cardiaca().charAt(i);
                    }

                }
            }
            
            
            
            
    
        int Dulce = 1;
        int Amarga = 1;
        int Salada = 1;
        int Picante = 1;
        int Acida = 1;
        int Act_f = 0;
        int Tabaco = 0;
        int Alcohol = 0;
        int Act_sex = 0;
        int Edo_gestacion = 0;
        int Terapia_rh = 0;
        int Tratamiento_n = 0;
        int Cantidad_ingesta = 0;
        int Postre = 0;
        int Horario_Comida = 0;

        if (ex.getHorarioComida() == null) {
            Horario_Comida = 0;

        }
        if (ex.getHorarioComida() != null) {
            Horario_Comida = Integer.parseInt(ex.getHorarioComida().toString());

        }

        if (ex.getPostre() == null) {
            Postre = 0;

        }
        if (ex.getPostre() != null) {
            Postre = Integer.parseInt(ex.getPostre().toString());

        }

        if (ex.getCantidadIngesta() == null) {
            Cantidad_ingesta = 0;

        }
        if (ex.getCantidadIngesta() != null) {
            Cantidad_ingesta = Integer.parseInt(ex.getCantidadIngesta().toString());

        }
        if (ex.getDulce() == null) {
            Dulce = 0;

        }
        if (ex.getDulce() =="true") {
            Dulce = Integer.parseInt(ex.getDulce().toString());

        }
        if (ex.getAmarga() == null) {
            Amarga = 0;

        }
        if (ex.getAmarga() =="true") {
            Amarga = Integer.parseInt(ex.getAmarga().toString());

        }
        if (ex.getSalada() == null) {
            Salada = 0;

        }
        if (ex.getSalada() == "true") {
            Salada = 1;

        }
        if (ex.getPicante() == null) {
            Picante = 0;

        }
        if (ex.getPicante() == "true") {
            Picante = 1;

        }
        if (ex.getAcida() == null) {
            Acida = 0;

        }
        if (ex.getAcida() == "true") {
            Acida = 1;

        }

        if (ex.getAct_f() == null) {
            Act_f = 0;

        }
        if (ex.getAct_f() != null) {
            Act_f = Integer.parseInt(ex.getAct_f().toString());

        }
        if (ex.getTabaco() == null) {
            Tabaco = 0;

        }
        if (ex.getTabaco() != null) {
            Tabaco = Integer.parseInt(ex.getTabaco().toString());

        }
        if (ex.getAlcohol() == null) {
            Alcohol = 0;

        }
        if (ex.getAlcohol() != null) {
            Alcohol = Integer.parseInt(ex.getAlcohol().toString());

        }
        if (ex.getAct_sex() == null) {
            Act_sex = 0;

        }
        if (ex.getAct_sex() != null) {
            Act_sex = Integer.parseInt(ex.getAlcohol().toString());

        }
        if (ex.getEdo_gestacion() == null) {
            Edo_gestacion = 0;

        }
        if (ex.getEdo_gestacion() != null) {
            Edo_gestacion = Integer.parseInt(ex.getEdo_gestacion().toString());

        }
        if (ex.getTerapia_rh() == null) {
            Terapia_rh = 0;

        }
        if (ex.getTerapia_rh() != null) {
            Terapia_rh = Integer.parseInt(ex.getTerapia_rh().toString());

        }
        if (ex.getTratamiento_n() == null) {
            Tratamiento_n = 0;

        }
        if (ex.getTratamiento_n() != null) {
            Tratamiento_n = Integer.parseInt(ex.getTratamiento_n().toString());

        }
        String sql = "select id_expediente from expediente where no_boleta=" + ex.getNo_boleta();
        List datosL2 = this.jdbcTemplate.queryForList(sql);

        if (datosL2.isEmpty()) {
            sql = "insert into expediente values(0," + ex.getNo_boleta() + ",'" + ex.getFecha_ini()+"');";   // INSERTAMOS EN LA TABLA ENTRADA NUESTRO id_usuario mientras el dato de session alert, el titulo de nuestra entrada y el contenido

            this.jdbcTemplate.update(sql);
            sql = "select id_expediente from expediente where no_boleta=" + ex.getNo_boleta();
            datosL2 = this.jdbcTemplate.queryForList(sql);
            
            String expediente = datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length() - 1);
            sql = "insert into hojaexpediente values(0," + expediente + ",'','','" + ex.getFecha_ini() + "',0,0,0,0,0,0,'" + subAntec_hf + "','" + Act_f + "','" + subAct + "','" + ex.getFrecuencia() + "','" + subPade + "','" + Tabaco + "','" + ex.getFrec_tabaco() + "','" + Alcohol + "','" + ex.getFrec_alcohol() + "','" + subTratamiento + "','" + subTiempo + "','','','" + subAlergias + "','" + subPostre + "',0,0,0,0,0,'" + Dulce + "','" + Amarga + "','" + Salada + "','" + Picante + "','" + Acida + "','" + Act_sex + "','" + Edo_gestacion + "','" + subM_anticonceptivo + "','" + Terapia_rh + "','" + subdosis + "','" + ex.getPeso() + "','" + ex.getTalla() + "','" + ex.getTemperatura() + "','" + subTension + "','" + subFrecuencia + "','',0.0,'" + ex.getCuello() + "','" + ex.getBrazo() + "','" + ex.getCadera() + "','" + ex.getTorax() + "','" + ex.getAntebrazo() + "','" + ex.getAbdomen() + "','" + ex.getMulso() + "','" + ex.getPierna() + "','" + subAspec_grls + "','','" + ex.getT_Gestacion() + "','" + subTipoTerapia + "','" + Cantidad_ingesta + "','" + Horario_Comida + "','" + subHC + "','" + subRecomendaciones + "','" + subObservaciones  + "','" + Tratamiento_n + "','" + ex.getGolosinas() + "');";   // INSERTAMOS EN LA TABLA ENTRADA NUESTRO id_usuario mientras el dato de session alert, el titulo de nuestra entrada y el contenido

            this.jdbcTemplate.update(sql);
        } else {
            String expediente = datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length() - 1);
            sql = "insert into hojaexpediente values(0," + expediente + ",'','','" + ex.getFecha_ini() + "',0,0,0,0,0,0,'" + subAntec_hf + "','" + Act_f + "','" + subAct + "','" + ex.getFrecuencia() + "','" + subPade + "','" + Tabaco + "','" + ex.getFrec_tabaco() + "','" + Alcohol + "','" + ex.getFrec_alcohol() + "','" + subTratamiento + "','" + subTiempo + "','','','" + subAlergias + "','" + subPostre + "',0,0,0,0,0,'" + Dulce + "','" + Amarga + "','" + Salada + "','" + Picante + "','" + Acida + "','" + Act_sex + "','" + Edo_gestacion + "','" + subM_anticonceptivo + "','" + Terapia_rh + "','" + subdosis + "','" + ex.getPeso() + "','" + ex.getTalla() + "','" + ex.getTemperatura() + "','" + subTension + "','" + subFrecuencia + "','',0.0,'" + ex.getCuello() + "','" + ex.getBrazo() + "','" + ex.getCadera() + "','" + ex.getTorax() + "','" + ex.getAntebrazo() + "','" + ex.getAbdomen() + "','" + ex.getMulso() + "','" + ex.getPierna() + "','" + subAspec_grls + "','','" + ex.getT_Gestacion() + "','" + subTipoTerapia + "','" + Cantidad_ingesta + "','" + Horario_Comida + "','" + subHC + "','" + subRecomendaciones + "','" + subObservaciones  + "','" + Tratamiento_n + "','" + ex.getGolosinas() + "');";   // INSERTAMOS EN LA TABLA ENTRADA NUESTRO id_usuario mientras el dato de session alert, el titulo de nuestra entrada y el contenido

            this.jdbcTemplate.update(sql);
        }

        // REALIZAMOS LA INSERCIÓN
         ModelAndView mv = new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("expedienteNutri");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

         sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
         datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio,correo  from paciente where no_boleta=" + ex.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("ListaPacientes", datosL2);                                                       // Pasa la lilsta completa               

        sql = "select no_cedulap from paciente where no_boleta=" + ex.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("pacientePsicologo", datosL2);                                                       // Pasa la lilsta completa               
        mv.addObject("paciente", new Paciente());

        sql = "select * from entrada where id_usuario=" + ex.getNo_boleta() + " order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("listaEntradas", datosL2);                                               // PASAMOS LA LISTA COMPLETA

        //  System.out.println("LISTA ENTRADAS: "+datosL2);
        sql = "select id_entrada,fecha from comentarios where id_usuario=" + ex.getNo_boleta() + " order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("FechaComentarios", datosL2);

        sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        mv.addObject("NombreEntrada", datosL2);                                               // PASAMOS LA LISTA COMPLETA            

        mv.addObject("entradaForo", new entradaForo());                                   //PASAMOS EL OBJETO ENTRADA EN EL FORO

        sql = "select no_cedula from nutriologo where no_empleado=" + alert;                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);

        String cedula = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

        sql = "select no_cita,fecha,horario from cita where no_boleta=" + ex.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        // PASAMOS LA LISTA COMPLETA            

        //  String cita=datosL2.get(0).toString().substring(9, datosL2.get(0).toString().length()-1); 
        //   System.out.println("numero de cita: "+cita);
        mv.addObject("datosCita", datosL2);
        mv.addObject("cita", new cita());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Fecha: " + dateFormat.format(date));

        sql = "select fecha from cita where no_boleta=" + ex.getNo_boleta() + " and no_cedula=" + cedula + " and estado=3";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
        datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
        int fechaCita = 0;
        if (datosL2.isEmpty()) {
            fechaCita = 0;
        } else {
            System.out.println("FECHA DE CITA: " + datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length() - 1));
            String fechaAct = datosL2.get(0).toString().substring(7, 11) + "/" + datosL2.get(0).toString().substring(12, 14) + "/" + datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length() - 1);
            System.out.println("FECHA ACTUAL: " + fechaAct);

            if (fechaAct.equals(dateFormat.format(date))) {
                fechaCita = 1;
            }
        }

        mv.addObject("fechaCita", fechaCita);

        sql = "select fecha_ini from expediente where no_boleta=" + ex.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);
        mv.addObject("fechaExpediente", datosL2);

        sql = "select id_expediente from expediente where no_boleta=" + ex.getNo_boleta(); 
        datosL2 = this.jdbcTemplate.queryForList(sql);
        int expedienteActivo = 0;
        if (datosL2.isEmpty()) {
            expedienteActivo = 0;
        } else {
            expedienteActivo = 1;
        }
        mv.addObject("expedienteActivo", expedienteActivo);
        mv.addObject("expediente", new expediente());
      

        return mv;                                                                                           // RETORNAMOS EL MODELO
        }
    }

    /////////////////////////////
    /////ACCION DEL BOTÓN Historial EXPEDIENTE
    @RequestMapping(params = "HistorialExpediente", method = RequestMethod.POST)
    public ModelAndView AtenderCita(@ModelAttribute("expediente") expediente ex, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
        mv.setViewName("ExpedienteGeneral");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta=" + ex.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("datosPaciente", datosL2);
        
        sql="select id_expediente from expediente where no_boleta="+ex.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        String expediente=datosL2.get(0).toString().substring(15,datosL2.get(0).toString().length()-1);
        
        
        sql = "select * from hojaexpediente where id_expediente=" + expediente+" order by fecha_ini limit 1";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("ExpedienteBase", datosL2);
        // Pasa la lilsta completa
        mv.addObject("expediente", new expediente());

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190101' and '20190131' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteEnero", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190201' and '20190229' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteFebrero", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190301' and '20190331' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteMarzo", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190401' and '20190430' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteAbril", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190501' and '20190531' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteMayo", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190601' and '20190630' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteJunio", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190701' and '20190731' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteJulio", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190801' and '20190831' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteAgosto", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20190901' and '20190930' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteSeptiembre", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20191001' and '20191031' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteOctubre", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20191101' and '20191130' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteNoviembre", datosL2);

        sql = "select fecha_ini,id_hojaexpediente from hojaexpediente where id_expediente=" + expediente+ " and fecha_ini between '20191201' and '20191231' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("expedienteDiciembre", datosL2);

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }

    /////////////////////////////
    /////ACCION DEL BOTÓN consultar entrada expediente
    @RequestMapping(params = "consultarEntradaExpediente", method = RequestMethod.POST)
    public ModelAndView consultarEntradaExpediente(@ModelAttribute("expediente") expediente ex, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        ModelAndView mv = new ModelAndView();                              //CREACIÓN DEL MODELO
        mv.setViewName("ConsultarExpedienteNutriologo");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

      
            sql = "select * from hojaexpediente where id_hojaExpediente=" + ex.getId_hojaexpediente();   // CONSULTA PARA EXTRAER DATOS DE SESION
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
            mv.addObject("datosExpediente", datosL2);

            
            System.out.println(datosL2);
            
            sql="select id_expediente from hojaexpediente where id_hojaExpediente="+ex.getId_hojaexpediente();
             datosL2 = this.jdbcTemplate.queryForList(sql);   
             String expediente=datosL2.get(0).toString().substring(15,datosL2.get(0).toString().length()-1);

            sql = "select no_boleta from expediente where id_expediente=" + expediente;   // CONSULTA PARA EXTRAER DATOS DE SESION
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
            String boleta = datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length() - 1);

            sql = "select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta=" + boleta;   // CONSULTA PARA EXTRAER DATOS DE SESION
            datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
            mv.addObject("datosPaciente", datosL2);

       

        return mv;                                                                                           // RETORNAMOS EL MODELO

    }

    
    
  /////////////////////
    /////ACION BOTON generar_dieta_nutriologo
    
     @RequestMapping(params = "generarDieta", method = RequestMethod.POST)
    public ModelAndView GenerarDieta(@ModelAttribute("cita") cita c, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }
        
        ModelAndView mv=new ModelAndView();
        mv.setViewName("generar_dieta_nutriologo");
        Conexion conn=new Conexion();                                 //Instacia a la conexión de base de datos
        JdbcTemplate jdbc=new JdbcTemplate(conn.conectar("smae"));
        
        String t1="select*from aceitesygrasas"; // 1
        String t2="select*from aceitesygrasasconproteinas"; // 2
        String t3="select*from alimentoslibresenenergia"; // 3
        String t4="select*from aoa_altosengrasa"; // 4
        String t5="select*from aoa_bajosengrasa"; // 5
        String t6="select*from aoa_moderadosengrasa"; // 6
        String t7="select*from aoa_muybajosengrasa"; // 7
        String t8="select*from azucarescongrasa"; // 8
        String t9="select*from azucaressingrasa"; // 9
        String t10="select*from bebidasalcoholicas"; // 10
        String t11="select*from bebidasybebidasalcoholicas"; // 11
        String t12="select*from burgerking"; // 12
        String t13="select*from cerealescongrasa"; // 13
        String t14="select*from cerealessingrasa"; //14
        String t15="select*from dominos"; // 15
        String t16="select*from frutas"; // 16
        String t17="select*from guarniciones"; // 17
        String t18="select*from kfc"; // 18
        String t19="select*from lecheconazucar"; //19
        String t20="select*from lechedescremada"; // 20
        String t21="select*from lecheentera"; // 21
        String t22="select*from lechesemidescremada"; // 22
        String t23="select*from leguminosas"; // 23
        String t24="select*from littlecaesars"; // 24
        String t25="select*from mcdonalds"; // 25
        String t26="select*from papajohns"; // 26
        String t27="select*from pizzahut"; // 27
        String t28="select*from platillosbebidas"; // 28
        String t29="select*from platillosdesayuno"; // 29
        String t30="select*from platillosdesayuno2"; // 30
        String t31="select*from platillosguarniciones"; // 31
        String t32="select*from platillosplatosfuertes"; // 32
        String t33="select*from platillospostres"; //33
        String t34="select*from platillossopas"; // 34
        String t35="select*from platosfuertes"; // 35
        String t36="select*from postres"; // 36
        String t37="select*from productosyakult"; // 37
        String t38="select*from sopas"; // 38
        String t39="select*from starbucks"; // 39
        String t40="select*from subway"; // 40
        String t41="select*from tacobell"; //41
        String t42="select*from verduras"; // 42
        String t43="select*from wendys"; // 43
        
        List datosT1 = jdbc.queryForList(t1); 
        List datosT2 = jdbc.queryForList(t2); 
        List datosT3 = jdbc.queryForList(t3); 
        List datosT4 = jdbc.queryForList(t4);
        List datosT5 = jdbc.queryForList(t5); 
        List datosT6 = jdbc.queryForList(t6); 
        List datosT7 = jdbc.queryForList(t7); 
        List datosT8 = jdbc.queryForList(t8); 
        List datosT9 = jdbc.queryForList(t9); 
        List datosT10 = jdbc.queryForList(t10); 
        List datosT11 = jdbc.queryForList(t11); 
        List datosT12 = jdbc.queryForList(t12); 
        List datosT13= jdbc.queryForList(t13); 
        List datosT14 = jdbc.queryForList(t4); 
        List datosT15 = jdbc.queryForList(t15); 
        List datosT16 = jdbc.queryForList(t16); 
        List datosT17 = jdbc.queryForList(t17); 
        List datosT18 = jdbc.queryForList(t18); 
        List datosT19 = jdbc.queryForList(t19); 
        List datosT20 = jdbc.queryForList(t20);
        List datosT21 = jdbc.queryForList(t21); 
        List datosT22 = jdbc.queryForList(t22); 
        List datosT23 = jdbc.queryForList(t23); 
        List datosT24 = jdbc.queryForList(t24);
        List datosT25 = jdbc.queryForList(t25); 
        List datosT26 = jdbc.queryForList(t26); 
        List datosT27 = jdbc.queryForList(t27); 
        List datosT28 = jdbc.queryForList(t28); 
        List datosT29 = jdbc.queryForList(t29); 
        List datosT30 = jdbc.queryForList(t30); 
        List datosT31 = jdbc.queryForList(t31); 
        List datosT32 = jdbc.queryForList(t32); 
        List datosT33 = jdbc.queryForList(t33); 
        List datosT34 = jdbc.queryForList(t34);
        List datosT35 = jdbc.queryForList(t35); 
        List datosT36 = jdbc.queryForList(t36); 
        List datosT37 = jdbc.queryForList(t37); 
        List datosT38 = jdbc.queryForList(t38); 
        List datosT39 = jdbc.queryForList(t39); 
        List datosT40 = jdbc.queryForList(t40); 
        List datosT41 = jdbc.queryForList(t41); 
        List datosT42 = jdbc.queryForList(t42);
        List datosT43 = jdbc.queryForList(t43); 
        
        
        //System.out.print(datosL);
   
        mv.addObject("datoT1",datosT1);  
        mv.addObject("datoT2",datosT2);
        mv.addObject("datoT3",datosT3);
        mv.addObject("datoT4",datosT4);
        mv.addObject("datoT5",datosT5);  
        mv.addObject("datoT6",datosT6);
        mv.addObject("datoT7",datosT7);
        mv.addObject("datoT8",datosT8);
        mv.addObject("datoT9",datosT9);  
        mv.addObject("datoT10",datosT10);
        mv.addObject("datoT11",datosT11);
        mv.addObject("datoT12",datosT12);
        mv.addObject("datoT13",datosT13);
        mv.addObject("datoT14",datosT14);
        mv.addObject("datoT15",datosT15);
        mv.addObject("datoT16",datosT16);
        mv.addObject("datoT17",datosT17);
        mv.addObject("datoT18",datosT18);
        mv.addObject("datoT19",datosT19);
        mv.addObject("datoT20",datosT20);
        mv.addObject("datoT21",datosT21);
        mv.addObject("datoT22",datosT22);
        mv.addObject("datoT23",datosT23);
        mv.addObject("datoT24",datosT24);
        mv.addObject("datoT25",datosT25);
        mv.addObject("datoT26",datosT26);
        mv.addObject("datoT27",datosT27);
        mv.addObject("datoT28",datosT28);
        mv.addObject("datoT29",datosT29);
        mv.addObject("datoT30",datosT30);
        mv.addObject("datoT31",datosT31);
        mv.addObject("datoT32",datosT32);
        mv.addObject("datoT33",datosT33);
        mv.addObject("datoT34",datosT34);
        mv.addObject("datoT35",datosT35);
        mv.addObject("datoT36",datosT36);
        mv.addObject("datoT37",datosT37);
        mv.addObject("datoT38",datosT38);
        mv.addObject("datoT39",datosT39);
        mv.addObject("datoT40",datosT40);
        mv.addObject("datoT41",datosT41);
        mv.addObject("datoT42",datosT42);
        mv.addObject("datoT43",datosT43);
        

        String sql = "select nombre,ap_uno,ap_dos, no_empleado,no_cedula from nutriologo where no_empleado=" + alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
        List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA

        mv.addObject("datos", datosL2);                                                       // Pasa la lilsta completa
        mv.addObject("Nutriologo", new Nutriologo());                                             //PASAMOS OBJETO PSICOLOGO   

        sql = "select nombre,ap_uno,ap_dos,no_boleta,edad from paciente where no_boleta=" + c.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
        datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
        mv.addObject("datosPaciente",datosL2);
        
        sql="select id_expediente from expediente where no_boleta="+c.getNo_boleta();
        datosL2 = this.jdbcTemplate.queryForList(sql); 
         String expediente=datosL2.get(0).toString().substring(15, datosL2.get(0).toString().length()-1); 
         sql="select talla,peso from hojaexpediente where id_expediente="+expediente+" order by fecha_ini desc limit 1";
         datosL2 = this.jdbcTemplate.queryForList(sql); 

         String talla="";
         String peso="";
         String actividad="";
         
         
       
     
            mv.addObject("expedientePaciente",datosL2);  
            sql="select talla from hojaexpediente where id_expediente="+expediente+" order by fecha_ini desc limit 1"; 
            datosL2 = this.jdbcTemplate.queryForList(sql); 
             talla=datosL2.get(0).toString().substring(7, datosL2.get(0).toString().length()-1);
            
            sql="select peso from hojaexpediente where id_expediente="+expediente+" order by fecha_ini desc limit 1"; 
            datosL2 = this.jdbcTemplate.queryForList(sql);
            peso=datosL2.get(0).toString().substring(6, datosL2.get(0).toString().length()-1);
            
            sql="select frecuencia  from hojaexpediente where id_expediente="+expediente+" order by fecha_ini desc limit 1"; 
            datosL2 = this.jdbcTemplate.queryForList(sql);
            actividad=datosL2.get(0).toString().substring(12, datosL2.get(0).toString().length()-1);
         
        
       
           
        mv.addObject("dieta",new Dieta());
       
        
         
            sql="select sexo from paciente where no_boleta="+c.getNo_boleta(); 
            datosL2 = this.jdbcTemplate.queryForList(sql);
           String Sexo=datosL2.get(0).toString().substring(6, datosL2.get(0).toString().length()-1);
           
           sql="select edad from paciente where no_boleta="+c.getNo_boleta(); 
            datosL2 = this.jdbcTemplate.queryForList(sql);
           String Edad=datosL2.get(0).toString().substring(6, datosL2.get(0).toString().length()-1);
           
            System.out.println(Sexo);
               System.out.println(Edad);
               Double calorias=0.0;
               
               
               
               
               
        //////////////FORMULA PARA CALCULAR CALORIAS YA ESTA LA TALLA, EL PESO  Y EL SEXO SEPARADOS PARA SOLO INSERTAR EN LA FORMULA
             if(Sexo.equals("H")){
                 //FORMULA PARA HOMBRE
                calorias=66+(13.7*(Double.parseDouble(peso)))+(5*(Double.parseDouble(talla)))-6.8*(Integer.parseInt(Edad));
             }
             if(Sexo.equals("M")){
                 //FORMULA PARA MUJER
                calorias=655+(9.6*(Double.parseDouble(peso)))+(1.8*(Double.parseDouble(talla)))-4.7*(Integer.parseInt(Edad));
             }
             
             if(Sexo.equals("O")){
                 //NO SE QUE PASA AQUÍ
                 
             }
        
             System.out.println(actividad+" actividad");
             
             switch(Integer.parseInt(actividad)){
                 case 0: calorias=calorias*1.2;
                 break;
                 case 1: 
                 case 2:
                 case 3: calorias=calorias*1.375;
                 break;
                 case 4: 
                 case 5: calorias=calorias*1.55;
                 break;
                 case 6: 
                 case 7: calorias=calorias*1.725;
                 break;
                         
             }
             System.out.println(calorias);
             int caloriasF=(int)  Math.round(calorias);
             mv.addObject("calorias",caloriasF);
        return mv;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////
    //ACCIÓN DEL BOTON CERRAR
    @RequestMapping(params = "cerrar", method = RequestMethod.POST)
    public ModelAndView logout(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl) {

        HttpSession session = hsr.getSession();                              //OBETENEMOS LA SESIÓN
        String alert = (String) session.getAttribute("Nutri");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES

        if (alert == null) {                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
            return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
        }

        // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        session.removeAttribute("Nutri");                                 // REMOVEMOS EL ATRIBUTO DE SESION RELACIONADO AL NUTRIÓLOGO

        session.invalidate();                                                 // INVALIDAMOS LA SESION

        return new ModelAndView("redirect:/login.htm");                      // REDIRECCIONAMOS A LOGIN

    }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             