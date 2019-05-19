/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ActividadP;
import models.Conexion;
import models.NeuralNet.Capa_neuronas;
import models.NeuralNet.Crear_RN;
import models.NeuralNet.Implementacion;
import models.NeuralNet.Tratamiento;
import models.NeuralNet.libMatrices;
import models.Paciente;
import models.Psicologo;
import models.entradaForo;
import models.foroValidar;
import models.Comentario;
import models.Nutriologo;
import models.cita;
import models.citaValidar;
import models.comentarioValidar;
import models.diario;
import models.diarioValidar;
import models.expediente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jms-m
 */
@Controller
public class PacienteController {
     private JdbcTemplate jdbcTemplate;
     private int[] topology={42,5,6};
     private foroValidar foroValidar;                                 //Variable para validar foro
     private comentarioValidar comentarioValidar;                     //Variable para validar comentarios
      private diarioValidar diarioValidar;                           //Variable para validar hoja del diario
      private citaValidar citaValidar;                           //Variable para validar cita
     
      public PacienteController() {
        this.foroValidar=new foroValidar();                            // Instancia de la clase foroValidar
        this.comentarioValidar=new comentarioValidar();               // Instancia de la clase comentarioValidar
        this.diarioValidar=new diarioValidar();                      // Instancia de la clase diarioValidar
        this.citaValidar=new citaValidar();                      // Instancia de la clase diarioValidar
        Conexion conn=new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
    }
      
     ///////////////////////////////
     //PETICION DE PRIMERA CITA
      
      @RequestMapping(value="/mostrarHorario", method=RequestMethod.GET)
       public @ResponseBody String getHORARIO(@RequestParam String fechaConsulta, @RequestParam String cedulaConsulta){
        System.out.println("-----getHORARIO-------");
        
        System.out.println(fechaConsulta+" esta fue la fecha");
          System.out.println(cedulaConsulta+" esta es la cedula");
       String sql=" select no_cita,horario from cita where fecha='"+fechaConsulta+"' and no_cedula="+cedulaConsulta+" and estado=0";   // CONSULTA PARA EXTRAER DATOS HORARIOS
                               List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
           System.out.println(datosL2);
     String objeto = new Gson().toJson(datosL2);
                               
                              
         
        return objeto; 
    }
       
      
      
      
      
      
 ///////////////////////////////////////
      //Pantalla de primera cita
        @RequestMapping(value="primera_cita.htm",method = RequestMethod.GET) 
    
     public ModelAndView primeraCita(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         System.out.println("PRIMERA CITA GET"); 
        
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("primera_cita");
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASA OBJETO PACIENTE
                
          
                                  mv.addObject("Nutriologo",new Nutriologo());                                             // PASA OBJETO NUTRIOLOGO
             
             
           
                                   mv.addObject("cita",new cita());                                             // PASA OBJETO CITA
            
                                 
           
             sql="select nombre,ap_uno,ap_dos, no_cedula from nutriologo where estatus =1;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

            
      
             datosL2=this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta
                  
             mv.addObject("ListaESCOM",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO    
             
             
             
             
             
             
             return mv;                                                                                          // RETORNAMOS EL MODELO
                
         
         
                    
                
    }
     
      ///////////////////////////////////////
      //Pantalla de diario
        @RequestMapping(value="diario_paciente.htm",method = RequestMethod.GET) 
    
     public ModelAndView DiarioPaciente(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         System.out.println("DIARIO GET"); 
        
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("diario_paciente");
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASA OBJETO PACIENTE
                                 mv.addObject("diario",new diario());                                             // PASA OBJETO DIARIO
      
              
                    
                sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente =( select id_expediente from expediente where no_boleta="+alert+"))";   // CONSULTA PARA EXTRAER TODAS LAS HOJAS DEL DIARIO
                               datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                mv.addObject("hojas",datosL2);                                                       // Pasa la lilsta completa                 
                        System.out.println(datosL2);         
                return mv;                                                                                          // RETORNAMOS EL MODELO
                
         
         
                    
                
    }
     
     
     
     
     ///////////////////////////////////////
      //Pantalla de seguimiento psicologico
        @RequestMapping(value="SeguimientoPsicologico.htm",method = RequestMethod.GET) 
    
     public ModelAndView SeguimientoPsicologico(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         System.out.println("Seguimiento psicologico GET"); 
        
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("SeguimientoPsicologico");
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASA OBJETO PACIENTE
                
                 sql="select nombre, ap_uno, ap_dos, no_cedula,telefono,correo,institucion, consultorio from psicologo where no_cedula=(select no_cedulap from paciente where no_boleta="+alert+" )";   // CONSULTA PARA EXTRAER EL NOMBRE DEL PSICOLOGO QUE NOS ATIENDE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("nombrePs",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             // PASA OBJETO PSICOLOGO
                
             
                 sql="select * from actividadp where no_boleta="+alert+" order by fecha desc";   // CONSULTA PARA EXTRAER EL NOMBRE DEL PSICOLOGO QUE NOS ATIENDE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("Actividades",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("ActividadP",new ActividadP());                                             // PASA OBJETO PSICOLOGO
                
                                 
                
                                 
                                 
                    return mv;                                                                                          // RETORNAMOS EL MODELO
                
         
         
                    
                
    }
     
       ///////////////////////////////////////
      //Pantalla de consultar Actividad de seguimiento psicologico
        @RequestMapping(value="ConsultarActivdad.htm",method = RequestMethod.GET) 
    
     public ModelAndView consultarActividadSeguimiento (@ModelAttribute("ActividadP") ActividadP ap, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
      
        
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarActividad");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente        
                              
                                 
                                 
                 sql="select * from actividadp where id_actividad="+ap.getId_actividad();                                   // CONSULTA PARA OBTENER TODO SOBRE LA ENTRADA QUE SEA IGUAL AL Id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                  //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("Actividad",datosL2);                                                   //PASAMOS OBJETO LA LISTA COMPLETA
                                 
                               
                 
                                 
             
         
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
     
         
                    
                
    }
     
     
     
     
     
     
     ///////////////////////////////////////
      //Pantalla de nueva entrada en el foro
        @RequestMapping(value="nuevaEntrada.htm",method = RequestMethod.GET) 
    
     public ModelAndView nuevaEntradaForo(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
      
         
                
                ModelAndView mv=new ModelAndView();  //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntrada");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                
                return mv;                                                                                           //Retornarmos el modelo
                
     
         
                    
                
    }
     
     ///////////////////////////////////////
      //Pantalla de FORO PRINCIPAL
        @RequestMapping(value="ForoPrincipal.htm",method = RequestMethod.GET) 
    
     public ModelAndView ForoPrincipal(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
           
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
       
       
                
                ModelAndView mv=new ModelAndView();                  //CREACIÓN DEL MODELO
                mv.setViewName("ForoPrincipal");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo
                
                                 
                sql="select * from entrada order by id_entrada desc";                                            //OBTENEMOS TODOS LOS ELEMENTOS DE LA TABLA ENTRADA ORDENADOS DE MANERA DESCENDENTE A PARTIR DEL ID_ENTRADA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("listaEntradas",datosL2);                                          // PASAMOS EL RESULTADO COMPLETO
                                
                     
                 sql="select id_entrada from entrada order by id_entrada desc";                                  //OBTENEMOS EL ID_ENTRADA DE TODAS LAS ENTRADAS EN LA TABLA ENTRADA ORDENADAS DE MANERA DESCENTE A PARTIR DEL ID_ENTRADA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                             //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA
                                            
                 int contadorEntradas=datosL2.size();                                                           //ASIGNAMOS EL VALOR DEL TAMAÑO DE LA LISTA OBTENIDA EN LA CONSULTA
                 
                 List contadorComentarios = null;                                                              //INICIALIZAMOS LA LISTA contadorComentarios
                 String cadena="";                                                                             //INICIALIZAMOS EL STRING CADENA
                 String subcadena="";                                                                          //INICIALIZAMOS EL STRING SUBCADENA
                 
                
                cadena=datosL2.get(0).toString();                                                             //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICIÓN O DE LA LISTA DE NUESTRA CONSULTA
                subcadena=cadena.substring(1,cadena.length()-1);                                              //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA
                 
                 sql="select count(id_comnt) from comentarios where "+subcadena;                              //CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA
                     
                           
                contadorComentarios= this.jdbcTemplate.queryForList(sql);                                    //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                 
                 
                 for(int i=1;i<=contadorEntradas-1;i++){                                                     // INICIO DE CICLO FOR EN EL QUE CONTAREMOS DESDE 1 HASTA EL NUMERO DE ENTRADAS - 1
                     
                cadena=datosL2.get(i).toString();                                                            //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE LA LISTA DE NUESTRA CONSULTA 
                subcadena=cadena.substring(1,cadena.length()-1);                                             //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA
                 sql="select count(id_comnt) from comentarios where "+subcadena;                             // CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA
                     
                           
                 contadorComentarios.add(i, this.jdbcTemplate.queryForList(sql));                            //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                
                 }
                 
                 
                 
                 List<String> contador = new ArrayList<String>();                                          //CREAMOS UN ARREGLO DE STRINGS EN EL QUE GUARDAREMOS LA CANTIDAD DE COMENTARIOS POR ENTRADA EN EL FORO
                 
                                 
                 
                 for(int i=0;i<=contadorEntradas-1;i++){                                                   // INICIO DE CICLO FOR 1 EN EL QUE CONTAREMOS DESDE 0 HASTA EL NUMERO DE ENTRADAS -1
                     
                cadena=contadorComentarios.get(i).toString();                                              //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE contadorComentarios
               
                 for(int j=0;j<cadena.length();j++){                                                        //INICIO DE CICLO FOR 2 DONDE CONTAMOS DESDE 0 HASTA LA LONGITUD DE CADENA
                     if(cadena.charAt(j)=='='){                                                             // SI EL CARACTER UBICADO EN LA POSICION J DE CADENA ES IGUAL AL SIMBOLO =    INICIO DE IF 1
                             
                         if(cadena.endsWith("}")){                                                         // SI NUESTRA CADENA TERMINA CON EL SIMBOLO } INICIO DE IF 2
                   subcadena=cadena.substring(j+1,cadena.length()-1);                                      // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -1
                   
                   
                       contador.add(i,subcadena);                                                          // AGREGAMOS LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR
                   
                   
                   j=cadena.length();                                                                      // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                 }                                                                                        // CIERRE DE IF 2
                     if(cadena.endsWith("]")){                                                          // SI NUESTRA CADENA TERMINA CON EL SIMOBOLO } INICIO DE IF 3
                   subcadena=cadena.substring(j+1,cadena.length()-2);                                   // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -2
                   
                   contador.add(i,subcadena);                                                          // AGREGAMOOS  LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR
                   
                   j=cadena.length();                                                                    // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                 }                                                                                        // CIERRE IF 3
                         
                         
                     }                                                                                   // CIERRE IF 1
                 }                                                                                      // CIERRE FOR 2
                
                 }                                                                                       //FINAL DE CICLO FOR 1
                 
                 
                 
                 mv.addObject("contadorComentarios",contador);                                        //PASAMOS OBJETO contador
                 
                                 
                return mv;                                                                               // RETORNAMOS EL MODELO
                    
                
    }          
     
     
     
     
     
     
     
      ///////////////////////////////////////
      //Pantalla de consultar entrada en el foro
        @RequestMapping(value="ConsultarEntrada.htm",method = RequestMethod.GET) 
    
     public ModelAndView consultarEntrada (@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
      
        
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente        
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo
                                 
                                 
                 sql="select * from entrada where id_entrada="+eF.getId_entrada();                                   // CONSULTA PARA OBTENER TODO SOBRE LA ENTRADA QUE SEA IGUAL AL Id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                  //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("Entrada",datosL2);                                                   //PASAMOS OBJETO LA LISTA COMPLETA
                                 
                                 mv.addObject("Comentario",new Comentario());                                      //PASAMOS EL OBJETO Comentario
                 
                                 
                                 
                 sql="select * from comentarios where id_entrada="+eF.getId_entrada()+" order by id_comnt";             //CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON id_entrada Y ORDENADOS DE MANERA DESCENDENTE POR id_comnt
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaComentarios",datosL2);                                           //PASAMOS LA LISTA COMPLETA
                    
               sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
     
         
                    
                
    }
     
     
     
      
      
      
      
/////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foro.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foro y se aplicará el método GET
    
        public ModelAndView foro(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton foro se cambiara a la vista de foroPs
        
        
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                        //CREACIÓN DEL MODELO
                mv.setViewName("foro");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lista completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente 
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo 
                                 
                sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("listaEntradas",datosL2);                                               // PASAMOS LA LISTA COMPLETA
               
                   sql = "select id_entrada,fecha from comentarios where id_usuario="+alert+" order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("FechaComentarios",datosL2);                                               // PASAMOS LA LISTA COMPLETA
              
                                 
                   sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("NombreEntrada",datosL2);                                               // PASAMOS LA LISTA COMPLETA            
                                 
                                 
                                 return mv;                                                                                            // RETORNAMOS EL MODELO
                                
                                 
                                                                                                                     
                
     }
     
     
      /////////////////////////////////////////////
     ///pantalla de vista mensajeriaP
      @RequestMapping(value="mensajeria.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeria y se aplicará el método GET
    
     public ModelAndView mensajeria(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("mensajeria"); 
        
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();                      //CREACIÓN DEL MODELO
                mv.setViewName("mensajeria");                            //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                
                return mv;
                
     }
     
        /////////////////////////////////////////////
     ///pantalla de vista EXPEDIENTE
      @RequestMapping(value="expedientePaciente.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeria y se aplicará el método GET
    
        
     public ModelAndView expediente(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("expediente"); 
        
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                          //CREACIÓN DEL MODELO
                mv.setViewName("expedientePaciente");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 
                                 
                                 sql="select*from evolucion where id_exp=(select id_expediente from expediente where no_boleta='"+alert+"');";
                    List datosEv=this.jdbcTemplate.queryForList(sql);
                    //ConsultaEvolucion evo=new ConsultaEvolucion(login.getUsuario());
                    //List datas=evo.consulta();
                    System.out.println(datosEv);
                    mv.addObject("datas",datosEv);
                    //sql="select edad,sexo,peso,altura,ansiedad,depresion,ira,estres,"
                      //      + "felicidad,dulce,amarga,salada,picante,acida,act_f,suplementos,"
                       //     + "motivacional,preparacionA,beneficiosA,deportes,medicamentos,salud from paciente,expediente where paciente.no_boleta='"+lo.getUsuario()+"';";

                   List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
                    for(Map<String, Object> row : rows){
                       String edad = row.get("edad").toString();
                       String sexo = row.get("sexo").toString();
                       String peso = row.get("peso").toString();
                       String altura = row.get("altura").toString();
                       String ansiedad = row.get("ansiedad").toString();
                       String depresion = row.get("depresion").toString();
                       String ira = row.get("ira").toString();
                       String estres = row.get("estres").toString();
                       String felicidad = row.get("felicidad").toString();
                       String dulce = row.get("dulce").toString();
                       String amarga = row.get("amarga").toString();
                       String salada = row.get("salada").toString();
                       String picante = row.get("picante").toString();
                       String acida = row.get("acida").toString();
                       String act_f = row.get("act_f").toString();
                       String suplementos = row.get("suplementos").toString();
                       String motivacional = row.get("motivacional").toString();
                       String preparacionA = row.get("preparacionA").toString();
                       String beneficiosA = row.get("beneficiosA").toString();
                       String deportes = row.get("deportes").toString();
                       String medicamentos = row.get("medicamentos").toString();
                       String salud= row.get("salud").toString();
                       
                       double pesoD=Double.parseDouble(peso);
                       int pesoI=(int)pesoD;
                       String pesoS=pesoI+"";
                       
                       System.out.println(edad + " " + pesoS + " "+ sexo + " " + dulce + " " +medicamentos+" "+act_f);
                       
                       Tratamiento tr=new Tratamiento(edad,sexo,pesoS,altura,ansiedad,
                               depresion,ira,estres,felicidad,dulce,amarga,salada,picante,
                               acida,act_f,suplementos,motivacional, preparacionA,beneficiosA,
                               deportes,medicamentos,salud);
                       
                        double[] x=tr.vector();
                        ArrayList<Capa_neuronas> neural_net;
                        libMatrices op=new libMatrices();

                        Crear_RN redRecomendaciones=new Crear_RN();
                        neural_net=redRecomendaciones.create_nn(topology,0);

                        ArrayList<double[][]> pesos=redRecomendaciones.asignarPesos();

                        neural_net.get(0).w=pesos.get(0);
                        neural_net.get(0).b=pesos.get(1);

                        neural_net.get(1).w=pesos.get(2);
                        neural_net.get(1).b=pesos.get(3);

                        Implementacion exe=new Implementacion(neural_net,x);
                        double[][] output=exe.Implement();
                        System.out.println("Entrada: ");
                        double[][] xa=new double[1][];
                        xa[0]=x;
                        op.print(xa);

                        System.out.println("Salida: ");
                        op.print(output);

                        ArrayList<String> salida=tr.seleccion(output[0]);
                    
                       System.out.println(salida);
                       mv.addObject("respuesta",salida.get(0));
                
               
                
     }
                    
          sql="select id_expediente from expediente where no_boleta="+alert;          
                     datosL2=this.jdbcTemplate.queryForList(sql);
                      int expedienteActivo=0;
               if(datosL2.isEmpty()){
                          expedienteActivo=0;
                      }
                      else{
                          expedienteActivo=1;
                      }
                      mv.addObject("expedienteActivo",expedienteActivo);
                       mv.addObject("expediente",new expediente());
                       
                       
                       
          sql="select no_cedula from paciente where no_boleta="+alert;
          datosL2=this.jdbcTemplate.queryForList(sql);
          
              
          
          String cedula=datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length()-1);
          
          
          sql="select fecha,horario from cita where no_boleta="+alert+" and no_cedula="+cedula+" and estado=3";
           datosL2=this.jdbcTemplate.queryForList(sql);

          mv.addObject("citaNutriologo",datosL2);
          
          mv.addObject("citaPsicologo",datosL2);
          sql="select nombre,ap_uno,ap_dos,no_cedula,institucion,correo,telefono,consultorio from nutriologo where no_cedula="+cedula;
          datosL2=this.jdbcTemplate.queryForList(sql);
          mv.addObject("nombreN",datosL2);
          
          
                       
          sql="select no_cedulap from paciente where no_boleta="+alert;
          datosL2=this.jdbcTemplate.queryForList(sql);
          
              
          
           cedula=datosL2.get(0).toString().substring(12, datosL2.get(0).toString().length()-1);
          
          
          sql="select fecha,horario from cita where no_boleta="+alert+" and no_cedula="+cedula+" and estado=3";
           datosL2=this.jdbcTemplate.queryForList(sql);

          
     //System.out.println(datas);
      return mv;
     }
     
     
     
     
     
     ////////////////////////////////////
     
     /////PANTALLA DE FORO
      @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton foro se cambiara a la vista de foroPs
          HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                        //CREACIÓN DEL MODELO
                mv.setViewName("foro");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lista completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente 
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo 
                                 
                sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("listaEntradas",datosL2);                                               // PASAMOS LA LISTA COMPLETA
               
                                 
                   sql = "select id_entrada,fecha from comentarios where id_usuario="+alert+" order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("FechaComentarios",datosL2);                                               // PASAMOS LA LISTA COMPLETA
              
                                 
                   sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("NombreEntrada",datosL2);                                               // PASAMOS LA LISTA COMPLETA            
                                 
                                 
                                 return mv;                                                                                            // RETORNAMOS EL MODELO
                            
     }
     
     
     
     
     /////////////////////////
     
     /////////PANTALLA DE MENSAJERIA
     
     @RequestMapping(params="mensajeria",method = RequestMethod.POST)
     public ModelAndView cambiarMensajes(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("mensajeria"); 
        
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();                           //CREACIÓN DEL MODELO
                mv.setViewName("mensajeria");                                 //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                
                return mv;
                
     }
     
     
     ////////////////////////////
     ///////////////PANTALLA DE EXPEDIENTE
     
     @RequestMapping(params="expediente",method = RequestMethod.POST)
     public ModelAndView cambiarExpediente(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("expediente"); 
        
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                         //CREACIÓN DEL MODELO
                mv.setViewName("expedientePaciente");                       //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 
                                 
                                 sql="select*from evolucion where id_exp=(select id_expediente from expediente where no_boleta='"+alert+"');";
                    List datosEv=this.jdbcTemplate.queryForList(sql);
                    //ConsultaEvolucion evo=new ConsultaEvolucion(login.getUsuario());
                    //List datas=evo.consulta();
                    System.out.println(datosEv);
                    mv.addObject("datas",datosEv);
                    //sql="select edad,sexo,peso,altura,ansiedad,depresion,ira,estres,"
                      //      + "felicidad,dulce,amarga,salada,picante,acida,act_f,suplementos,"
                       //     + "motivacional,preparacionA,beneficiosA,deportes,medicamentos,salud from paciente,expediente where paciente.no_boleta='"+lo.getUsuario()+"';";

                   List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
                    for(Map<String, Object> row : rows){
                       String edad = row.get("edad").toString();
                       String sexo = row.get("sexo").toString();
                       String peso = row.get("peso").toString();
                       String altura = row.get("altura").toString();
                       String ansiedad = row.get("ansiedad").toString();
                       String depresion = row.get("depresion").toString();
                       String ira = row.get("ira").toString();
                       String estres = row.get("estres").toString();
                       String felicidad = row.get("felicidad").toString();
                       String dulce = row.get("dulce").toString();
                       String amarga = row.get("amarga").toString();
                       String salada = row.get("salada").toString();
                       String picante = row.get("picante").toString();
                       String acida = row.get("acida").toString();
                       String act_f = row.get("act_f").toString();
                       String suplementos = row.get("suplementos").toString();
                       String motivacional = row.get("motivacional").toString();
                       String preparacionA = row.get("preparacionA").toString();
                       String beneficiosA = row.get("beneficiosA").toString();
                       String deportes = row.get("deportes").toString();
                       String medicamentos = row.get("medicamentos").toString();
                       String salud= row.get("salud").toString();
                       
                       double pesoD=Double.parseDouble(peso);
                       int pesoI=(int)pesoD;
                       String pesoS=pesoI+"";
                       
                       System.out.println(edad + " " + pesoS + " "+ sexo + " " + dulce + " " +medicamentos+" "+act_f);
                       
                       Tratamiento tr=new Tratamiento(edad,sexo,pesoS,altura,ansiedad,
                               depresion,ira,estres,felicidad,dulce,amarga,salada,picante,
                               acida,act_f,suplementos,motivacional, preparacionA,beneficiosA,
                               deportes,medicamentos,salud);
                       
                        double[] x=tr.vector();
                        ArrayList<Capa_neuronas> neural_net;
                        libMatrices op=new libMatrices();

                        Crear_RN redRecomendaciones=new Crear_RN();
                        neural_net=redRecomendaciones.create_nn(topology,0);

                        ArrayList<double[][]> pesos=redRecomendaciones.asignarPesos();

                        neural_net.get(0).w=pesos.get(0);
                        neural_net.get(0).b=pesos.get(1);

                        neural_net.get(1).w=pesos.get(2);
                        neural_net.get(1).b=pesos.get(3);

                        Implementacion exe=new Implementacion(neural_net,x);
                        double[][] output=exe.Implement();
                        System.out.println("Entrada: ");
                        double[][] xa=new double[1][];
                        xa[0]=x;
                        op.print(xa);

                        System.out.println("Salida: ");
                        op.print(output);

                        ArrayList<String> salida=tr.seleccion(output[0]);
                    
                       System.out.println(salida);
                       mv.addObject("respuesta",salida.get(0));
                
               
                
     }
     //System.out.println(datas);
      return mv;
     }
     
     
        
    
    //////////////////////
     ///////////PANTALLA DE PRIMERA CITA
    
      @RequestMapping(params="primera_cita",method = RequestMethod.POST)
     public ModelAndView cambiarPrimeraCita(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("primera cita POST"); 
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("primera_cita");
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASA OBJETO PACIENTE
                
          
                                  mv.addObject("Nutriologo",new Nutriologo());                                             // PASA OBJETO NUTRIOLOGO
             
             
           
                                   mv.addObject("cita",new cita());                                             // PASA OBJETO CITA
            
                                 
           
             sql="select nombre,ap_uno,ap_dos, no_cedula from nutriologo where estatus =1;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

            
      
             datosL2=this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta
                  
             mv.addObject("ListaESCOM",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO    
             
             
             
             
             
             
             return mv;                                                                                          // RETORNAMOS EL MODELO
                
         
         
                
     }
     
     //////////////////////
     //////////PANTALLA DE NUEVA ENTRADA EN EL FORO
     
      @RequestMapping(params="nuevaEntrada",method = RequestMethod.POST)
     public ModelAndView cambiarNuevaEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
         HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
      
         
                
                ModelAndView mv=new ModelAndView();  //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntrada");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                
                return mv;                                                                                           //Retornarmos el modelo
                
     
         
     }
     
     
    ////////////////////
     ////ACCIÓN DE BOTON guardarEntrada
     
      @RequestMapping(params="guardarEntrada", method = RequestMethod.POST)
    public ModelAndView guardarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
       this.foroValidar.validate(eF, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                            // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntrada");                                  //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASAMOS OBJETO PACIENTE
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                                 
                return mv;                                                                                            //RETORNAMOS EL MODELO
         }                                                                               // CIERRE DE IF
         else{                                                                           // INICIO ELSE
             String sql="insert into entrada values("+'0'+","+alert+",'"+eF.getTitulo()+"','"+eF.getContenido()+"','','"+eF.getFecha()+"');";   // INSERTAMOS EN LA TABLA ENTRADA NUESTRO id_usuario mientras el dato de session alert, el titulo de nuestra entrada y el contenido
                               
       
                this.jdbcTemplate.update(sql);                                                                              // REALIZAMOS LA INSERCIÓN
            
       
       
        
            
            
            
           ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASAMOS EL OBJETO PACIENTE
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS EL OBJETO entradaForo
                                 mv.addObject("Comentario",new Comentario());                                         // PASAMOS EL OBJETO comentario
                                 
                sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc limit 1";      // CONSULTA PARA EXTRAER LA ULTIMA ENTRADA EN EL FORO REALIZADA POR NUESTRO PACIENTE ACTIVO
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                           // ASIGNAMOS  EL RESULTADO DE LA CONSULTA
                    
                                 mv.addObject("Entrada",datosL2);                                             // PASAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 
               sql="select * from comentarios where id_entrada="+eF.getId_entrada()+" order by id_comnt";      // CONSULTA PARA OBTENER TODOS LOS COMENTARIOS REALIZADOS EN ESTA ENTRADA ORDENADOS POR´ id_comnt 
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                // ASIGNAMOS EL RESULTADO DE NUSTRA CONSULTA
                                 
                                 mv.addObject("ListaComentarios",datosL2);                                        // PASAMOS LA LISTA COMPLETA
                    
                 sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
         }                                                                                                   // CIERRE DE ELSE
       
       
    }
    
    
    ///////////////
    /// ACCIÓN BOTON EliminarEntrada
    
     @RequestMapping(params="EliminarEntrada", method = RequestMethod.POST)
    public ModelAndView EliminarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
     
        String sql=" delete from entrada where id_entrada="+eF.getId_entrada()+";";         // BORRAMOS LA ENTRADA IDENTIFICADA CON EL ID id_entrada
                               
       
                this.jdbcTemplate.update(sql);                                                // REALIZAMOS LA ELIMINACIÓN DE LA ENTRADA
            
       
                
        sql="delete  from comentarios where id_entrada="+eF.getId_entrada();                   // BORRAMOS TODOS LOS COMENTARIOS DE REALACIONADOS A LA ENTRADA CON EL ID id_entrada
                     this.jdbcTemplate.update(sql);                                            // REALIZAMOS LA ELIMINACION DE LOS COMENTARIOS
                                          
                
       
        
            
            
            
           return new ModelAndView("redirect:/foro.htm");                                       // REDIRECCIONAMOS AL FORO
        
       
            
                
       
       
    }
    
    
   //////////////////////
    ////////ACCIÓN DEL BOTÓN MoficiarEntrada
     
     @RequestMapping(params="ModificarEntrada", method = RequestMethod.POST)
    public ModelAndView ModificarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
       System.out.println("MODIFICAR ENTRADA DEL FORO");
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        this.foroValidar.validate(eF, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                 // INICIO DE IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();                          //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS EL OBJETO PACIENTE
                                 mv.addObject("entradaForo",new entradaForo());                                      // PASAMOS EL OBJETO entradaForo
                                 mv.addObject("Comentario",new Comentario());                                        // PASAMOS EL OBJETO Comentario
                                 
                sql="select * from entrada where id_entrada="+eF.getId_entrada();                                 //CONSULTA PARA OBJETER LA ENTRADA RELACIONADA AL ID id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                               //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("Entrada",datosL2);                                                 // PASAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 
                                 
                sql="select * from comentarios where id_entrada="+eF.getId_entrada()+" order by id_comnt";       // SELECCIONAMOS TODOS LOS COMENTARIOS RELACIONADOS CON EL ID id_entrada ORDENADOS POR id_comnt
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaComentarios",datosL2);                                       // PASAMOS LA LISTA COMPLETA
                    
                sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
         }                                                                                                        // CIERRE DE IF  
         else{                                                                                                    // INICIO DE ELSE
             String sql="update entrada set titulo='"+eF.getTitulo()+"',contenido='"+eF.getContenido()+"'where id_entrada="+eF.getId_entrada()+";"; // ACTUALIZAMOS LA ENTRADA EN TITULO Y CONTENIDO DONDE el ID DE ENTRADA SE IGUAL A id_entrada
                               
       
                this.jdbcTemplate.update(sql);                       //REALIZAMOS LA ACTUALIZACIÓN
             

      
                
       
       
       
            
            
            
          ModelAndView mv=new ModelAndView();                                  //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                            //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASAMOS EL OBJETO PACIENTE
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS EL OBJETO entradaForo
                                 
                sql="select * from entrada where id_entrada="+eF.getId_entrada();                                      // CONSULTA PARA OBTENER LA ENTRADA CON EL ID id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                  // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                
                                 mv.addObject("Entrada",datosL2);                                                    // PASAMOS EL RESULTADO DE LA CONSULTA
                                  mv.addObject("Comentario",new Comentario());                                       // PASAMOS EL OBJETO Comentario
                                  
                                  
                                  
                sql="select * from comentarios where id_entrada="+eF.getId_entrada()+" order by id_comnt";          // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                 //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaComentarios",datosL2);                                          // PASAMOS EL RESULTADO DE LA CONSULTA
                    
                                 
                                   
                                 
                                 
                sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
         
         }                                                                                                          // CIERRE DE ELSE
            
       
    }
     
    
    
    
    
    
    
   /////////////////
    ////VISTA CONSULTAR ENTRADA
    
      @RequestMapping(params="consultarEntrada",method = RequestMethod.POST)
     public ModelAndView cambiarConsultarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
             HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente        
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo
                                 
                                 
                 sql="select * from entrada where id_entrada="+eF.getId_entrada();                                   // CONSULTA PARA OBTENER TODO SOBRE LA ENTRADA QUE SEA IGUAL AL Id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                  //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("Entrada",datosL2);                                                   //PASAMOS OBJETO LA LISTA COMPLETA
                                 
                                 mv.addObject("Comentario",new Comentario());                                      //PASAMOS EL OBJETO Comentario
                 
                                 
                                 
                 sql="select * from comentarios where id_entrada="+eF.getId_entrada()+" order by id_comnt";             //CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON id_entrada Y ORDENADOS DE MANERA DESCENDENTE POR id_comnt
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaComentarios",datosL2);                                           //PASAMOS LA LISTA COMPLETA
                    
                sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
                
     
     }
  
     ///////////////////////
     /////VISTA FORO PRINCIPAL
     
      @RequestMapping(params="foroPrincipal",method = RequestMethod.POST)
     public ModelAndView cambiarForoPrincipal(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
       
       
                
                ModelAndView mv=new ModelAndView();                  //CREACIÓN DEL MODELO
                mv.setViewName("ForoPrincipal");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo
                
                                 
                sql="select * from entrada order by id_entrada desc";                                            //OBTENEMOS TODOS LOS ELEMENTOS DE LA TABLA ENTRADA ORDENADOS DE MANERA DESCENDENTE A PARTIR DEL ID_ENTRADA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("listaEntradas",datosL2);                                          // PASAMOS EL RESULTADO COMPLETO
                                
                     
                 sql="select id_entrada from entrada order by id_entrada desc";                                  //OBTENEMOS EL ID_ENTRADA DE TODAS LAS ENTRADAS EN LA TABLA ENTRADA ORDENADAS DE MANERA DESCENTE A PARTIR DEL ID_ENTRADA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                             //ASIGNACIÓN DEL RESULTADO DE LA CONSULTA
                                            
                 int contadorEntradas=datosL2.size();                                                           //ASIGNAMOS EL VALOR DEL TAMAÑO DE LA LISTA OBTENIDA EN LA CONSULTA
                 
                 List contadorComentarios = null;                                                              //INICIALIZAMOS LA LISTA contadorComentarios
                 String cadena="";                                                                             //INICIALIZAMOS EL STRING CADENA
                 String subcadena="";                                                                          //INICIALIZAMOS EL STRING SUBCADENA
                 
                
                cadena=datosL2.get(0).toString();                                                             //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICIÓN O DE LA LISTA DE NUESTRA CONSULTA
                subcadena=cadena.substring(1,cadena.length()-1);                                              //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA
                 
                 sql="select count(id_comnt) from comentarios where "+subcadena;                              //CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA
                     
                           
                contadorComentarios= this.jdbcTemplate.queryForList(sql);                                    //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                 
                 
                 for(int i=1;i<=contadorEntradas-1;i++){                                                     // INICIO DE CICLO FOR EN EL QUE CONTAREMOS DESDE 1 HASTA EL NUMERO DE ENTRADAS - 1
                     
                cadena=datosL2.get(i).toString();                                                            //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE LA LISTA DE NUESTRA CONSULTA 
                subcadena=cadena.substring(1,cadena.length()-1);                                             //ASIGNAMOS A SUBCADENA LA CADENA CONFORMADA POR LOS CARACTERES UBICADOS ENTRE LA POSICION 1 Y LA PENULTIMA POSICION DEL STRING CADENA
                 sql="select count(id_comnt) from comentarios where "+subcadena;                             // CONTAMOS EL NUMERO DE COMENTARIOS REALIZADOS EN LA ENTRADA GUARDADA COMO SUBCADENA
                     
                           
                 contadorComentarios.add(i, this.jdbcTemplate.queryForList(sql));                            //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                
                 }
                 
                 
                 
                 List<String> contador = new ArrayList<String>();                                          //CREAMOS UN ARREGLO DE STRINGS EN EL QUE GUARDAREMOS LA CANTIDAD DE COMENTARIOS POR ENTRADA EN EL FORO
                 
                                 
                 
                 for(int i=0;i<=contadorEntradas-1;i++){                                                   // INICIO DE CICLO FOR 1 EN EL QUE CONTAREMOS DESDE 0 HASTA EL NUMERO DE ENTRADAS -1
                     
                cadena=contadorComentarios.get(i).toString();                                              //ASIGNAMOS A CADENA EL VALOR UBICADO EN LA POSICION I DE contadorComentarios
               
                 for(int j=0;j<cadena.length();j++){                                                        //INICIO DE CICLO FOR 2 DONDE CONTAMOS DESDE 0 HASTA LA LONGITUD DE CADENA
                     if(cadena.charAt(j)=='='){                                                             // SI EL CARACTER UBICADO EN LA POSICION J DE CADENA ES IGUAL AL SIMBOLO =    INICIO DE IF 1
                             
                         if(cadena.endsWith("}")){                                                         // SI NUESTRA CADENA TERMINA CON EL SIMBOLO } INICIO DE IF 2
                   subcadena=cadena.substring(j+1,cadena.length()-1);                                      // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -1
                   
                   
                       contador.add(i,subcadena);                                                          // AGREGAMOS LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR
                   
                   
                   j=cadena.length();                                                                      // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                 }                                                                                        // CIERRE DE IF 2
                     if(cadena.endsWith("]")){                                                          // SI NUESTRA CADENA TERMINA CON EL SIMOBOLO } INICIO DE IF 3
                   subcadena=cadena.substring(j+1,cadena.length()-2);                                   // ASIGNAMOS A SUBCADENA EL VALOR DE LA CADENA CONFORMADA POR LOS CARACTERES J+1 HASTA LA LONGITUD DE LA CADENA -2
                   
                   contador.add(i,subcadena);                                                          // AGREGAMOOS  LA SUBCADENA EN LA POSICION I DE LA LISTA CONTADOR
                   
                   j=cadena.length();                                                                    // ASIGNAMOS A J EL VALOR DE LA LONGITUD DE CADENA
                 }                                                                                        // CIERRE IF 3
                         
                         
                     }                                                                                   // CIERRE IF 1
                 }                                                                                      // CIERRE FOR 2
                
                 }                                                                                       //FINAL DE CICLO FOR 1
                 
                 
                 
                 mv.addObject("contadorComentarios",contador);                                        //PASAMOS OBJETO contador
                 
                                 
                return mv;                                                                               // RETORNAMOS EL MODELO
                
     }
     
     
     ////////////////////////
     /////ACCION DEL BOTÓN AGREGAR COMENTARIO
     
     
      @RequestMapping(params="AgregarComentario", method = RequestMethod.POST)
    public ModelAndView AgregarComentario(@ModelAttribute("Comentario") Comentario comen, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
      
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.comentarioValidar.validate(comen, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                        // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();                          //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                            // PASAMOS EL OBJETO PACIENTE
                                 mv.addObject("entradaForo",new entradaForo());                                      // PASAMOS EL OBJETO entradaForo
                                 mv.addObject("Comentario",new Comentario());                                        // PASAMOS EL OBJETO Comentario
                                 
                sql="select * from entrada where id_entrada="+comen.getId_entrada();                              //CONSULTAMOS LA ENTRADA CON EL ID id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                               // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                
                                 mv.addObject("Entrada",datosL2);                                                 // PASAMOS EL RESULTADO DE LA CONSULTA
                                 
                sql="select * from comentarios where id_entrada="+comen.getId_entrada()+" order by id_comnt";     // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS A LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                              //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaComentarios",datosL2);                                      // PASAMOS EL RESULTADO DE LA CONSULTA
                    
                  sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO                                                                                // RETORNAMOS EL MODELO
                                   
                
         }                                                                                  // CIERRE IF
         else{
             String sql="insert into comentarios values("+'0'+","+comen.getId_entrada()+",'"+comen.getId_usuario()+"','"+comen.getContenido()+"','','"+comen.getFecha()+"');"   ; // INSERTAMOS EL COMENTARIO EN LA ENTRADA CON EL ID id_entrada EL TITULO Y EL CONTENIDO
                               
       
                this.jdbcTemplate.update(sql);       // INSERTAMOS EL COMENTARIO
             

      
                
       
       
        
            
            
            
          ModelAndView mv=new ModelAndView();                               //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASAMOS EL OBJETO PACIENTE
                                 mv.addObject("entradaForo",new entradaForo());                                         // PASAMOS EL OBJETO entradaForo
                                 
                sql="select * from entrada where id_entrada="+comen.getId_entrada();                                 // CONSULTA PARA OBTENER LA ENTRADA CON EL ID id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                 // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                               
                                 mv.addObject("Entrada",datosL2);                                                  // PASAMOS EL RESULTADO DE LA CONSULTA
                                  mv.addObject("Comentario",new Comentario());                                    // PASAMOS EL OBJETO Comentario
                                  
               sql="select * from comentarios where id_entrada="+comen.getId_entrada()+" order by id_comnt";      // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADO A LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                               // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                             
                                 mv.addObject("ListaComentarios",datosL2);                                        // PASAMOS EL RESULTADO DE LA CONSULTA 
                    
                 sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
         
         }                                                                                                 // CIERRE ELSE
            
       
    }
     

    
    /////////////////////////////
    /////ACCION DEL BOTÓN EliminarComentario
    
    
          @RequestMapping(params="EliminarComentario", method = RequestMethod.POST)
    public ModelAndView EliminarComentario(@ModelAttribute("Comentario") Comentario comen, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
      
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
        
           String  sql="delete  from comentarios where id_comnt="+comen.getId_comnt();     //ELIMINAMOS EL COMENTARIO RELACIONADO CON EL ID id_comnt
                     this.jdbcTemplate.update(sql);                                    //ELIMINAMOS EL COMENTARIO

      
                
       
       

            
            
            
          ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntrada");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                              // PASAMOS EL OBJETO PACIENTE
                                 mv.addObject("entradaForo",new entradaForo());                                      // PASAMOS EL OBJETO entradaForo
                                 
                sql="select * from entrada where id_entrada="+comen.getId_entrada();                                // CONSULTA PARA OBTENER LA ENTRADA CON EL ID id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                    // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                          
                                 mv.addObject("Entrada",datosL2);                                                  // PASAMOS EL RESULTADO DE LA CONSULTA
                                  mv.addObject("Comentario",new Comentario());                                     // PASAMOS EL OBJETO Comentario
                                  
               sql="select * from comentarios where id_entrada="+comen.getId_entrada()+" order by id_comnt";   // CONSULTA PARA OBTENER LOS COMENTARIOS RELACIONADOS CON LA ENTRADA CON EL ID id_entrada ORDENADOS MEDIANTE id_comnt
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                             //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                
                                 mv.addObject("ListaComentarios",datosL2);                                       // PASAMOS EL RESULTADO DE LA CONSULTA
                    
                sql="select no_boleta,nombre, ap_uno, ap_dos from paciente";            //CONSULTA PARA OBTENER LOS PACIENTES
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                
                     sql="select no_empleado,nombre, ap_uno, ap_dos from nutriologo";            //CONSULTA PARA OBTENER LOS NUTRIOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaNutriologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                 
                                 
                              sql="select no_empleado,nombre, ap_uno, ap_dos from psicologo";            //CONSULTA PARA OBTENER LOS PSICOLOGOS PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPsicologos",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                          
                           sql="select no_empleado,nombre, ap_uno, ap_dos from administrador";            //CONSULTA PARA OBTENER LOS ADMINISTRADORES PERTENECIENTES AL SISTEMA
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                   //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaAdministrador",datosL2);                                           //PASAMOS LA LISTA COMPLETA  
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
         
            
       
    }
    
    
    
    /////////////////////////////
    /////ACCION DEL BOTÓN ConsultarActividad
    
    
          @RequestMapping(params="ConsultarActividad", method = RequestMethod.POST)
    public ModelAndView ConsultarActividad(@ModelAttribute("ActivdadP") ActividadP ap, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
      
             
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarActividad");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente        
                              
                                 
                                 
                 sql="select * from actividadp where id_actividad="+ap.getId_actividad();                                   // CONSULTA PARA OBTENER TODO SOBRE LA ENTRADA QUE SEA IGUAL AL Id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                  //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                               System.out.println(datosL2);  
                                 mv.addObject("Actividad",datosL2);                                                   //PASAMOS OBJETO LA LISTA COMPLETA
                                 
                               
                 sql="select nombre,ap_uno,ap_dos, no_cedula from psicologo where no_cedula=(select no_cedula from actividadp where id_actividad="+ap.getId_actividad()+")";                                   // CONSULTA PARA OBTENER TODO SOBRE LA ENTRADA QUE SEA IGUAL AL Id_entrada
                     datosL2 = this.jdbcTemplate.queryForList(sql);                                                  //ASIGNAMOS EL RESULTADO DE LA CONSULTA
                               System.out.println(datosL2);  
                                 mv.addObject("nombre",datosL2);                                                   //PASAMOS OBJETO LA LISTA COMPLETA
                                 
                                 
             
         
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
     
         
                    
                
       
    }
    
    
     /////////////////////////////
    /////ACCION DEL BOTÓN guardarHoja
    
    
          @RequestMapping(params="guardarHoja", method = RequestMethod.POST)
    public ModelAndView GuardarHoja(@ModelAttribute("diario") diario d, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
      
             
             
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.diarioValidar.validate(d, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                        // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();                          //CREACIÓN DEL MODELO
                mv.setViewName("diario_paciente");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                            // PASAMOS EL OBJETO PACIENTE
                                 mv.addObject("diario",new diario());                                             // PASA OBJETO DIARIO
                          
                     
                       return mv;                                                                                           // RETORNAMOS EL MODELO                                                                                // RETORNAMOS EL MODELO
                                   
                
         }                                                                                  // CIERRE IF
         else{
             
             String sql="select id_expediente from expediente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
             
             
             
            
            String cadena=datosL2.get(0).toString();
            String subcadena=cadena.substring(15, cadena.length()-1);
           
             sql="select id_diario from diario where id_expediente="+subcadena;   // CONSULTA PARA EXTRAER id_ de diario
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
             
            
             if(datosL2.isEmpty()){                                                                              // VERIFICAMOS SI EXISTE UN DIARIO RELACIONADO A ESE EXPEDIENTE
                 System.out.println("NO EXISTE EL DIARIO "+datosL2);
                  sql="insert into diario values(0"+","+subcadena+",'Diario de seguimiento','"+d.getFecha()+"')"; // CREAMOS EL DIARIO
                               
       
              this.jdbcTemplate.update(sql);       // INSERTAMOS EL COMENTARIO
               
                  sql="select id_diario from diario where id_expediente="+subcadena;   // CONSULTA PARA EXTRAER id_ de diario
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                 
                   cadena=datosL2.get(0).toString();                            // sacamos el id
                 subcadena=cadena.substring(11, cadena.length()-1);           // sacamos el id
                 
                 
                 sql="insert into hojas values("+'0'+","+subcadena+",'"+d.getFecha()+"','"+d.getContenido()+"','"+d.getSentimiento()+"','')"; // INSERTAMOS LA ENTRADA EN EL DIARIO
                               
       
                  this.jdbcTemplate.update(sql);       // INSERTAMOS LA HOJA EN EL DIARIO
            
             }
             else{
                 cadena=datosL2.get(0).toString();                            // sacamos el id 
                 subcadena=cadena.substring(11, cadena.length()-1);           // sacamos el id
                 
                 
                 sql="insert into hojas values("+'0'+","+subcadena+",'"+d.getFecha()+"','"+d.getContenido()+"','"+d.getSentimiento()+"','')"; // INSERTAMOS LA ENTRADA EN EL DIARIO
                               
       
                  this.jdbcTemplate.update(sql);       // INSERTAMOS LA HOJA EN EL DIARIO
                 
             }
             
            
            
            
            
            
          ModelAndView mv=new ModelAndView();                               //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarDiario");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASAMOS EL OBJETO PACIENTE
                                  mv.addObject("diario",new diario());                                             // PASA OBJETO DIARIO
                                 
                sql="select * from hojas where id_diario="+subcadena+" and fecha='"+d.getFecha()+"'";   // CONSULTA PARA EXTRAER DATOS DE HOJA DE DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNA
                                                                             
                                 mv.addObject("hoja",datosL2); 
                                  
                 return mv;                                                                                           // RETORNAMOS EL MODELO
         
         }                                                                                                 // CIERRE ELSE
            
         
                    
                
       
    }
    
    
    
    /////////////////
    ////VISTA CONSULTAR DIARIO
    
      @RequestMapping(params="consultarHoja",method = RequestMethod.POST)
     public ModelAndView cambiarConsultarDiario(@ModelAttribute("diario") diario d, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
             HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarDiario");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             //PASAMOS OBJETO Paciente        
                                 mv.addObject("diario",new diario());                                       //PASAMOS OBJETO entradaForo
                                 
                 sql="select * from hojas where id_hojas="+d.getId_hojas();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                mv.addObject("hoja",datosL2);                                                       // Pasa la lilsta completa
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
                
     
     }
  
    
    
    
     
     
     
     
     
     
     
     
     
     /////////////////////////////
    /////ACCION DEL BOTÓN guardarHoja
    
    
          @RequestMapping(params="guardarCita", method = RequestMethod.POST)
    public ModelAndView GuardarCita(@ModelAttribute("cita") cita c, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
      
             
             
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.citaValidar.validate(c, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                        // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("primera_cita");
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                             // PASA OBJETO PACIENTE
                
          
                                  mv.addObject("Nutriologo",new Nutriologo());                                             // PASA OBJETO NUTRIOLOGO
             
             
           
                                   mv.addObject("cita",new cita());                                             // PASA OBJETO CITA
            
                                 
           
             sql="select nombre,ap_uno,ap_dos, no_cedula from nutriologo where estatus =1;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

            
      
             datosL2=this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta
                  
             mv.addObject("ListaESCOM",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO    
             
             
             
             
             
             
             return mv;                                                                                          // RETORNAMOS EL MODELO
                
         }                                                                                  // CIERRE IF
         else{
             
           
             
             
             String sql="update cita set estado=3, no_boleta="+alert+" where no_cita="+c.getNo_cita(); // ACTIVAMOS LA CITA
                               
       
              this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA
              
             
              
            sql="select horario from cita where no_cita="+c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
            String horarioBase=datosL2.get(0).toString().substring(9,datosL2.get(0).toString().length()-1);
            
            
             sql="select no_cedula from cita where no_cita="+c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
                           datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
            String cedula=datosL2.get(0).toString().substring(11,datosL2.get(0).toString().length()-1);
         
            sql="select fecha from cita where no_cita="+c.getNo_cita();   // CONSULTA PARA EXTRAER DATOS DE CITA
                           datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
            String fecha=datosL2.get(0).toString().substring(7,datosL2.get(0).toString().length()-1);
              
           sql="select horario from cita where no_cedula="+cedula+" and fecha='"+fecha+"' and estado=1";   // CONSULTA PARA EXTRAER  HORARIOS DE CITA
                           datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
         
            
            String horario1="";       //VARIABLE PARA HORARIO QUE COMPARAREMOS
            String subHorario="";      // VARIABLE EN LA QUE SE DIVIDEN LAS HORAS DEL HORARIO QUE SE COMPARARÁ
            String subMinutoHorario1="";   // VARIABLE PARA CALCULO DE MINUTOS DEL HORARIO A COMPARAR
            String subMinutoBase="";     // VARIABLE PARA CALCULOS DE MINUTOS DEL HORARIO BASE
             String subHorarioBase=""; // VARIABLE PARA EN LA QUE SE DIVIDEN LAS HORAS DEL HORARIO BASE
            
            for(int x=0; x<datosL2.size();x++){                                                         // INICIO DE CICLO FOR 1 DE X= 0 HASTA EL TAMAÑO DE LA LISTA DE HORARIOS
                horario1=datosL2.get(x).toString().substring(9,datosL2.get(x).toString().length()-1);    // CREACION DEL HORARIO A COMPARAR
               
               //PARA COMPARAR PRIMERA HORA POR LA IZQUIERDA
                for(int i=0;i<horario1.length();i++){                                                  // INICIO CICLO FOR 2
                                   
                    if(horario1.charAt(i)==':'){                                                    // INICIO IF 1 EN EL QUE SE COMPARA EL CARACTER DEL HORARIO EN EL INDICE I CON EL SIMBOLO :
                       subHorario=horario1.substring(0,i);                                          // SEPARACION DEL HORARIO
                       subMinutoHorario1=horario1.substring(i+1,i+3);                              // CALCULO DE MINUTOS DEL HORARIO
                       i=horario1.length();                                                       // FINALIZACIÓN DEL IF AL ASIGNAR A I EL VALOR DE LA LONGITUD DEL HORARIO
                    }                                                                            // CIERRE IF 1
                   
                }                                                                               // CIERRE FOR 2
                 for(int i=0;i<horarioBase.length();i++){                                      // INICIO CICLO FOR 3
                     if(horarioBase.charAt(i)==':'){                                            // INICIO IF 2 EN EL QUE SE COMÁRA EL CARACTER DEL HORARIO BASE EN EL INDICE I CON EL SIMBOLO :
                        subHorarioBase=horarioBase.substring(0,i);                            // SEPARACION DEL HORARIO BASE
                        subMinutoBase=horarioBase.substring(i+1,i+3);                      // CALCULO DE MINUTOS DEL HORARIO BASE
                        i=horarioBase.length();                                           // FINALIZACION DEL IF AL ASIFNAR A I EL VALRO DE LA LONGITUD DEL HORARIO BASE
                    }                                                                 // CIERRE IF 2
                                        
                    
                }                                                                         // CIERRE FOR 3
                
                
                
                
                int horaInicio=Integer.parseInt(subHorarioBase); // PASAR HORA A ENTERO
                
              
                int horaInicioCompara=Integer.parseInt(subHorario); // PASAR HORA A ENTERO
                
              
                
                
                 float MinInicio=Float.parseFloat(subMinutoBase); // PASAR MINUTOS A FLOTANTE
                 float MinFin=Float.parseFloat(subMinutoHorario1);     // PASAR MINUTOS A FLOTANTE
                
              
                
                float minI= MinInicio/60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
          float minS= MinFin/60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
          
          float horaE=horaInicio+minI;    // GENERACIÓN DE HORA DE ENTRADA
          float horaSa=horaInicioCompara+minS;    // GENERACIÓN DE HORA A COMPARAR
        
               
                
                if(horaE==horaSa){                         // COMPARACION DE HORA BASE CON HORA DE COMPARACION
              
                 sql="update cita set estado=4 where fecha='"+fecha+"' and horario='"+horario1+"' and no_cedula="+cedula; // BLOQUEAMOS HORARIOS QUE INTERFIERAN CON LA CITA
                               
       
                 this.jdbcTemplate.update(sql);       // INSERTAMOS LOS BLOQUEOS
                }
                
                
                
                //PARA COMPARAR SEGUNDA HORA POR LA DERECHA
                
                int j=0;              // VARIABLE BANDERA PARA CALCULO DE HORARIOS POR LA DERECHA
        int k=0;                      // VARIABLE BANDERA PARA CALCULO DE HORARIO POR LA DERECHA
                for(int i=0;i<horario1.length();i++){                 // INICIO FOR 1 POR LA DERECHA
                                   
                    if(horario1.charAt(i)=='-'){                     // INICIO IF 1 POR LA DERECHA
                       j=1;                                         // ASIGNACIÓN DE VALOR A NUESTRA BANDERA J
                       k=i;                                        // ASIGNACION DE VALOR A NUESTRA BANDERA K
                    }                                              // FIN IF 1 POR LA DERECHA
                    if(j==1 && horario1.charAt(i)==':'){               // INICIO IF 2 POR LA DERECHA
                       subHorario=horario1.substring(k+1,i);          // DIVISION DEL SUBHORARIO POR LA DERECHA
                       subMinutoHorario1=horario1.substring(i+1,i+3);   // DIVISION DE LOS MINUTOS POR LA DERECHA
                       i=horario1.length();                            // FINALIZACIÓN DEL IF AL ASIGNAR EL VALOR DE i IGUAL A LA LONGITUDE DEL HORARIO
                       j=0;                                        // RESTABLECIMIENTO DE BANDERA J
                       k=0;                                      // RESTABLECIMIENTO DE BANDERA K
                    }                                            // CIERRE IF 2 POR LA DERECHA
                   
                }                                              // CIERRE FOR 1 POR LA DERECHA
                 for(int i=0;i<horarioBase.length();i++){        // INICIO FOR 2 POR LA DERECHA
                    if(horarioBase.charAt(i)=='-'){              // INICIO IF 2 POR LA DERECHA EN EL QUE SE COMPARA EL CARACTER DEL HORARIO BASE EN EL INDICE i CON EL CARACTER -
                       j=1;                                      // ASIGNACION DE VALOR A LA BANDERA J
                       k=i;                                      // ASIGNACION DE VALOR A LA BANDERA K
                    }                                               // CIERRE IF 2 POR LA DERECHA
                     
                     if(j==1 && horarioBase.charAt(i)==':'){           // INICIO IF 3 POR LA DERECHA
                        subHorarioBase=horarioBase.substring(k+1,i);     // DIVICION DEL HORARIO BASE 
                        subMinutoBase=horarioBase.substring(i+1,i+3);     // DIVISION DE MINUTOS DEL HORARIO BASE
                        i=horarioBase.length();                             // FINALIZACIÓN DE IF 3 POR LA DERECHA AL ASIGNAR A i EL VALOR DE LA LONGITU DEL HORARIO BASE
                        j=0;                                       // RESTABLECIMIENTO DE ANDERA J
                        k=0;                                            // RESTABLECIMIENTO DE BANDERA K
                    }                                                 // CIERRE IF 3 POR LA DERECHA
                                        
                    
                }                                                   // CIERRE FOR 2 POR LA DERECHA
                 
              
                 
                  horaInicio=Integer.parseInt(subHorarioBase); // PASAR HORA A ENTERO
                
                
                
                 horaInicioCompara=Integer.parseInt(subHorario); // PASAR HORA A ENTERO
                
               
                
                
                
                 MinInicio=Float.parseFloat(subMinutoBase); // PASAR MINUTOS A FLOTANTE
                 MinFin=Float.parseFloat(subMinutoHorario1);     // PASAR MINUTOS A FLOTANTE
                
               
                minI= MinInicio/60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
           minS= MinFin/60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
          
           horaE=horaInicio+minI;    // GENERACIÓN DE HORA DE ENTRADA
           horaSa=horaInicioCompara+minS;    // GENERACIÓN DE HORA DE SALIDA
        
                
                if(horaE==horaSa){
               
                
                
                 sql="update cita set estado=4 where fecha='"+fecha+"' and horario='"+horario1+"' and no_cedula="+cedula; // ACTIVAMOS LA CITA
                               
       
                 this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA
                
                }
                 
                
                
                
                
                
            }
            
             sql="update paciente set no_cedula="+cedula+" where no_boleta="+alert; // ACTIVAMOS LA CITA
                               
       
              this.jdbcTemplate.update(sql);       // INSERTAMOS LA CITA
              
            
            
             
                      ModelAndView mv=new ModelAndView();                          //CREACIÓN DEL MODELO
                mv.setViewName("expedientePaciente");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 
                                 
                                 sql="select*from evolucion where id_exp=(select id_expediente from expediente where no_boleta='"+alert+"');";
                    List datosEv=this.jdbcTemplate.queryForList(sql);
                    //ConsultaEvolucion evo=new ConsultaEvolucion(login.getUsuario());
                    //List datas=evo.consulta();
                    System.out.println(datosEv);
                    mv.addObject("datas",datosEv);
                    //sql="select edad,sexo,peso,altura,ansiedad,depresion,ira,estres,"
                      //      + "felicidad,dulce,amarga,salada,picante,acida,act_f,suplementos,"
                       //     + "motivacional,preparacionA,beneficiosA,deportes,medicamentos,salud from paciente,expediente where paciente.no_boleta='"+lo.getUsuario()+"';";

                   List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
                    for(Map<String, Object> row : rows){
                       String edad = row.get("edad").toString();
                       String sexo = row.get("sexo").toString();
                       String peso = row.get("peso").toString();
                       String altura = row.get("altura").toString();
                       String ansiedad = row.get("ansiedad").toString();
                       String depresion = row.get("depresion").toString();
                       String ira = row.get("ira").toString();
                       String estres = row.get("estres").toString();
                       String felicidad = row.get("felicidad").toString();
                       String dulce = row.get("dulce").toString();
                       String amarga = row.get("amarga").toString();
                       String salada = row.get("salada").toString();
                       String picante = row.get("picante").toString();
                       String acida = row.get("acida").toString();
                       String act_f = row.get("act_f").toString();
                       String suplementos = row.get("suplementos").toString();
                       String motivacional = row.get("motivacional").toString();
                       String preparacionA = row.get("preparacionA").toString();
                       String beneficiosA = row.get("beneficiosA").toString();
                       String deportes = row.get("deportes").toString();
                       String medicamentos = row.get("medicamentos").toString();
                       String salud= row.get("salud").toString();
                       
                       double pesoD=Double.parseDouble(peso);
                       int pesoI=(int)pesoD;
                       String pesoS=pesoI+"";
                       
                       System.out.println(edad + " " + pesoS + " "+ sexo + " " + dulce + " " +medicamentos+" "+act_f);
                       
                       Tratamiento tr=new Tratamiento(edad,sexo,pesoS,altura,ansiedad,
                               depresion,ira,estres,felicidad,dulce,amarga,salada,picante,
                               acida,act_f,suplementos,motivacional, preparacionA,beneficiosA,
                               deportes,medicamentos,salud);
                       
                        double[] x=tr.vector();
                        ArrayList<Capa_neuronas> neural_net;
                        libMatrices op=new libMatrices();

                        Crear_RN redRecomendaciones=new Crear_RN();
                        neural_net=redRecomendaciones.create_nn(topology,0);

                        ArrayList<double[][]> pesos=redRecomendaciones.asignarPesos();

                        neural_net.get(0).w=pesos.get(0);
                        neural_net.get(0).b=pesos.get(1);

                        neural_net.get(1).w=pesos.get(2);
                        neural_net.get(1).b=pesos.get(3);

                        Implementacion exe=new Implementacion(neural_net,x);
                        double[][] output=exe.Implement();
                        System.out.println("Entrada: ");
                        double[][] xa=new double[1][];
                        xa[0]=x;
                        op.print(xa);

                        System.out.println("Salida: ");
                        op.print(output);

                        ArrayList<String> salida=tr.seleccion(output[0]);
                    
                       System.out.println(salida);
                       mv.addObject("respuesta",salida.get(0));
                
               
                
     }
           
            
             
             
             
             
             return mv;                                  
         }                                                                                                 // CIERRE ELSE
            
         
                    
                
       
    }
    
     
     
     
     
     /////////////////////////////
    /////ACCION DEL BOTÓN Historial EXPEDIENTE
    
    
          @RequestMapping(params="HistorialExpedientePaciente", method = RequestMethod.POST)
    public ModelAndView AtenderCita(@ModelAttribute("expediente") expediente ex, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
      
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
        
     
       
       

            
            
            
          ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ExpedienteGeneralPaciente");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                
                
                   String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                            // PASAMOS EL OBJETO PACIENTE
                                 
              
                
                 sql="select * from expediente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                       
                
                
                                mv.addObject("ExpedienteBase",datosL2);
                                                                                                              // Pasa la lilsta completa
                                 mv.addObject("expediente",new expediente());
                               
                sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190101' and '20190131' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteEnero",datosL2);
                       
                                  
                                  
                  sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190201' and '20190229' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteFebrero",datosL2);                
                                  
                                  
                                  
                   sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190301' and '20190331' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteMarzo",datosL2);                
                                                 
                                  
                     sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190401' and '20190430' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteAbril",datosL2);                
                                                                
                                  
                     sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190501' and '20190531' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteMayo",datosL2);                
                                                            
                                  
                   sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190601' and '20190630' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteJunio",datosL2);                
                                                               
                        
                     sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190701' and '20190731' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteJulio",datosL2);                
                                                                              
                                  
                                  
                     sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190801' and '20190831' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteAgosto",datosL2);                
                                                                              
                                  
                   sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20190901' and '20190930' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteSeptiembre",datosL2);                
                                                                                
                                  
                  sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20191001' and '20191031' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteOctubre",datosL2);                
                                                                                 
                   sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20191101' and '20191130' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteNoviembre",datosL2);                
                   
                                  
                   sql="select fecha_ini,id_hojaexpediente from hojaexpediente where no_boleta="+alert+" and fecha_ini between '20191201' and '20191231' order by fecha_ini desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("expedienteDiciembre",datosL2);                
                                                                                
                                  return mv;                                                                                           // RETORNAMOS EL MODELO
         
         
            
       
    }   
     
     
     
     
     ////////////////////
    //ACCIÓN DEL BOTON CERRAR
     
     
     
     @RequestMapping(params="cerrar", method = RequestMethod.POST)
    public ModelAndView logout(@ModelAttribute("Paciente") Paciente p, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
                
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
          
          
       
            
            session.removeAttribute("Paciente");                                 // REMOVEMOS EL ATRIBUTO DE SESION RELACIONADO AL PACIENTE
                  
            session.invalidate();                                                 // INVALIDAMOS LA SESION
            
           
            
            
            
            
            return new ModelAndView("redirect:/login.htm");                      // REDIRECCIONAMOS A LOGIN
        
       
            
                
       
       
    }
     
    /////////////////////////////
    /////ACCION DEL BOTÓN consultar entrada expediente
    
    
          @RequestMapping(params="consultarEntradaExpedientePaciente", method = RequestMethod.POST)
    public ModelAndView consultarEntradaExpediente(@ModelAttribute("expediente") expediente ex, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
      
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Paciente");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
        
     
       
       

            
            
            
          ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarExpedientePaciente");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                
                   String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());                                            // PASAMOS EL OBJETO PACIENTE
                                 
                                 
                                 
                     if(ex.getId_expediente()==null){
                         sql="select * from hojaexpediente where id_hojaExpediente="+ex.getId_hojaexpediente();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("datosExpediente",datosL2);
                                  System.out.println(datosL2);
                  
                                             
                sql="select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("datosPaciente",datosL2);  
                                  
                                  
                                  
                     }  
                     else{
                         sql="select * from expediente where id_expediente="+ex.getId_expediente();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("datosExpediente",datosL2);
                                
                       
                sql="select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                  mv.addObject("datosPaciente",datosL2);
                
                          
                          
                     }
                                 
                                 
              
               
                               
               
                                  return mv;                                                                                           // RETORNAMOS EL MODELO
         
         
            
       
    }   
      
}
