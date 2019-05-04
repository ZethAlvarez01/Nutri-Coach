/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Comentario;
import models.Conexion;
import models.Nutriologo;
import models.Paciente;
import models.Psicologo;
import models.comentarioValidar;
import models.entradaForo;
import models.foroValidar;
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
public class PsicologoController {
    private JdbcTemplate jdbcTemplate;
   private foroValidar foroValidar;                                 //Variable para validar foro
   private comentarioValidar comentarioValidar;                     //Variable para validar comentarios
    
     public PsicologoController() {
       this.foroValidar=new foroValidar();                            // Instancia de la clase foroValidar
        this.comentarioValidar=new comentarioValidar();               // Instancia de la clase comentarioalidar
        Conexion conn=new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
    }
     
     
     
     
     
     
     
     
     
     
      ///////////////////////////////////////
      //Pantalla de nueva entrada en el foro
        @RequestMapping(value="nuevaEntradaPs.htm",method = RequestMethod.GET) 
    
     public ModelAndView nuevaEntradaForo(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE NUTRIOLOGOS
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
      
         
                
                ModelAndView mv=new ModelAndView();  //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntradaPs");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                
                return mv;                                                                                           //Retornarmos el modelo
                
     
         
                    
                
    }
     
     ///////////////////////////////////////
      //Pantalla de FORO PRINCIPAL
        @RequestMapping(value="ForoPrincipalPs.htm",method = RequestMethod.GET) 
    
     public ModelAndView ForoPrincipal(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
           
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
       
       
                
                ModelAndView mv=new ModelAndView();                  //CREACIÓN DEL MODELO
                mv.setViewName("ForoPrincipalPs");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
        @RequestMapping(value="ConsultarEntradaPs.htm",method = RequestMethod.GET) 
    
     public ModelAndView consultarEntrada (@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
      
        
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaPs");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                   String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO                                      //PASAMOS OBJETO NUTRIOLOGO    
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
     
     
     
     
     
     
     
     
     
     
           /////////////////////////////////////////////////////////////// 
  //pantalla de lista de pacientes de psicologo
    @RequestMapping(value="bienvenida_psicologo.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_psicologo y se aplicará el método GET
    
      public ModelAndView bienvenida_psicologo(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_psicologo
        System.out.println("paciente"); 
        
            HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Psico");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_psicologo");
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                
                return mv;
                
     }
       
     
     
               /////////////////////////////////////////////////////////////// 
  //Cronograma de psicologo
    @RequestMapping(value="cronogramaPsicologo.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_psicologo y se aplicará el método GET
    
   public ModelAndView cronogramaPs(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma"); 
        
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Psico");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("cronogramaPsicologo");
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                return mv;
                
     }
     
     
     
     
      /////////////////////////////////////////////
     ///pantalla de vista mensajeriaPs
      @RequestMapping(value="mensajeriaPs.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeriaPs y se aplicará el método GET
    
       public ModelAndView mensajeriaPs(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("mensajeria"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Psico");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaPs");
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                return mv;
                
     }
     
     
      /////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foroPs.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foroPs y se aplicará el método GET
    
      public ModelAndView foroPs(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton foro se cambiara a la vista de foroPs
         HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                        //CREACIÓN DEL MODELO
                mv.setViewName("foroPs");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO           
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo 
                                 
                sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("listaEntradas",datosL2);                                               // PASAMOS LA LISTA COMPLETA
                return mv;                                                                                            // RETORNAMOS EL MODELO
                
              
                
     }
     
     
     
     
     
    
    @RequestMapping(params="cronograma",method = RequestMethod.POST)
  
     public ModelAndView cambiarCronograma(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma"); 
        
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Psico");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("cronogramaPsicologo");
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                return mv;
                
     }
     
    
    @RequestMapping(params="mensajeria",method = RequestMethod.POST)
  
     public ModelAndView cambiarMensajes(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("mensajeria"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Psico");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaPs");
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                return mv;
                
     }
     
     
       @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton foro se cambiara a la vista de foroPs
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                        //CREACIÓN DEL MODELO
                mv.setViewName("foroPs");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO           
                                 mv.addObject("entradaForo",new entradaForo());                                       //PASAMOS OBJETO entradaForo 
                                 
                sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("listaEntradas",datosL2);                                               // PASAMOS LA LISTA COMPLETA
                return mv;                                                                                            // RETORNAMOS EL MODELO
                
     }
     
     @RequestMapping(params="pacientes",method = RequestMethod.POST)
       public ModelAndView cambiarPaciente(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_psicologo
        System.out.println("paciente"); 
        
            HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Psico");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_psicologo");
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                
                return mv;
                
     }
       
       
        //////////////////////
     //////////PANTALLA DE NUEVA ENTRADA EN EL FORO
     
      @RequestMapping(params="nuevaEntrada",method = RequestMethod.POST)
     public ModelAndView cambiarNuevaEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
         HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE NUTRIOLOGOS
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
      
         
                
                ModelAndView mv=new ModelAndView();  //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntradaPs");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                
                return mv;                                                                                           //Retornarmos el modelo
                
     
                                                                                                  //Retornarmos el modelo
                
     
         
     }
     
       
        ////////////////////
     ////ACCIÓN DE BOTON guardarEntrada
     
      @RequestMapping(params="guardarEntrada", method = RequestMethod.POST)
    public ModelAndView guardarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
       this.foroValidar.validate(eF, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                            // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntradaPs");                                  //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                  String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                                 
                return mv;                                                                                            //RETORNAMOS EL MODELO
         }                                                                               // CIERRE DE IF
         else{                                                                           // INICIO ELSE
             String sql="insert into entrada values("+'0'+","+alert+",'"+eF.getTitulo()+"','"+eF.getContenido()+"','','"+eF.getFecha()+"');";   // INSERTAMOS EN LA TABLA ENTRADA NUESTRO id_usuario mientras el dato de session alert, el titulo de nuestra entrada y el contenido
                               
       
                this.jdbcTemplate.update(sql);                                                                              // REALIZAMOS LA INSERCIÓN
            
       
       
        
            
            
            
           ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaPs");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
     
        String sql=" delete from entrada where id_entrada="+eF.getId_entrada()+";";         // BORRAMOS LA ENTRADA IDENTIFICADA CON EL ID id_entrada
                               
       
                this.jdbcTemplate.update(sql);                                                // REALIZAMOS LA ELIMINACIÓN DE LA ENTRADA
            
       
                
        sql="delete  from comentarios where id_entrada="+eF.getId_entrada();                   // BORRAMOS TODOS LOS COMENTARIOS DE REALACIONADOS A LA ENTRADA CON EL ID id_entrada
                     this.jdbcTemplate.update(sql);                                            // REALIZAMOS LA ELIMINACION DE LOS COMENTARIOS
                                          
                
       
        
            
            
            
           return new ModelAndView("redirect:/foroPs.htm");                                       // REDIRECCIONAMOS AL FORO
        
       
            
                
       
       
    }
    
       
        //////////////////////
    ////////ACCIÓN DEL BOTÓN MoficiarEntrada
     
     @RequestMapping(params="ModificarEntrada", method = RequestMethod.POST)
    public ModelAndView ModificarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
       System.out.println("MODIFICAR ENTRADA DEL FORO");
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
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
                mv.setViewName("ConsultarEntradaPs");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String  sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
                mv.setViewName("ConsultarEntradaPs");                            //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaPs");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                   String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO                                      //PASAMOS OBJETO NUTRIOLOGO    
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
                                                       // RETORNAMOS EL MODELO
                                                                                           
                
     
     
     } 
       
       
        ///////////////////////
     /////VISTA FORO PRINCIPAL
     
      @RequestMapping(params="foroPrincipal",method = RequestMethod.POST)
     public ModelAndView cambiarForoPrincipal(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
           
      HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
       
       
                
                ModelAndView mv=new ModelAndView();                  //CREACIÓN DEL MODELO
                mv.setViewName("ForoPrincipalPs");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.comentarioValidar.validate(comen, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                        // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();                          //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaPs");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
                                                  
                                 
                       return mv;                                                                                           // RETORNAMOS EL MODELO
     
                                   
                
         }                                                                                  // CIERRE IF
         else{
             String sql="insert into comentarios values("+'0'+","+comen.getId_entrada()+",'"+comen.getTitulo()+"','"+comen.getContenido()+"','','"+comen.getFecha()+"');"   ;  // INSERTAMOS EL COMENTARIO EN LA ENTRADA CON EL ID id_entrada EL TITULO Y EL CONTENIDO
                               
       
                this.jdbcTemplate.update(sql);       // INSERTAMOS EL COMENTARIO
             

      
                
       
       
        
            
            
            
          ModelAndView mv=new ModelAndView();                               //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaPs");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
        
           String  sql="delete  from comentarios where id_comnt="+comen.getId_comnt();     //ELIMINAMOS EL COMENTARIO RELACIONADO CON EL ID id_comnt
                     this.jdbcTemplate.update(sql);                                    //ELIMINAMOS EL COMENTARIO

      
                
       
       

            
            
            
          ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaPs");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos, no_empleado from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO
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
       
       
       
       
       
       
       
       
       
          ////////////////////
    //ACCIÓN DEL BOTON CERRAR
     
     
     
     @RequestMapping(params="cerrar", method = RequestMethod.POST)
    public ModelAndView logout(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
                
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
          
          
       
            
            session.removeAttribute("Psico");                                 // REMOVEMOS EL ATRIBUTO DE SESION RELACIONADO AL PSICÓLOGO
                  
            session.invalidate();                                                 // INVALIDAMOS LA SESION
            
           
            
            
            
            
            return new ModelAndView("redirect:/login.htm");                      // REDIRECCIONAMOS A LOGIN
        
       
            
                
       
       
    }
   
    
     
}
