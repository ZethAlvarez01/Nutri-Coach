/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import models.comentarioValidar;
import models.diario;
import models.diarioValidar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     
      public PacienteController() {
        this.foroValidar=new foroValidar();                            // Instancia de la clase foroValidar
        this.comentarioValidar=new comentarioValidar();               // Instancia de la clase comentarioValidar
        this.diarioValidar=new diarioValidar();                      // Instancia de la clase diarioValidar
        Conexion conn=new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
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
                
              sql=" select * from nutriologo where estatus <> 4 and estatus <> 0 order by ap_uno asc;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3

            
      
             datosL2=this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta
                  
             mv.addObject("ListaN",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO     
            
             System.out.println(datosL2);
             
             
             
             
             
              sql=" select * from nutriologo where estatus <>4 and estatus <>0 and institucion='Escuela Superior de Cómputo (ESCOM)' order by ap_uno"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3 y sean del IPN

            
      
             datosL2=this.jdbcTemplate.queryForList(sql); // pasamos el resultado de la consulta
                  
             mv.addObject("ListaESCOM",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO     
            
             System.out.println(datosL2);
             
                                 
             mv.addObject("Nutriologo",new Nutriologo());                                             // PASA OBJETO NUTRIOLOGO
                                 
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
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();                         //CREACIÓN DEL MODELO
                mv.setViewName("primera_cita");                             //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula,no_cedulap from paciente where no_boleta="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                
                return mv;
                
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
                 
                 
                 sql="insert into hojas values("+'0'+","+subcadena+",'"+d.getFecha()+"','"+d.getContenido()+"','"+d.getSentimiento()+"')"; // INSERTAMOS LA ENTRADA EN EL DIARIO
                               
       
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
     
     
}
