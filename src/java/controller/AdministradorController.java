/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Administrador;
import models.Comentario;
import models.Conexion;
import models.Login;
import models.Mensaje;
import models.Nutriologo;
import models.Paciente;
import models.Psicologo;
import models.comentarioValidar;
import models.entradaForo;
import models.foroValidar;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class AdministradorController {
  private JdbcTemplate jdbcTemplate;
   private foroValidar foroValidar;                                 //Variable para validar foro
   private comentarioValidar comentarioValidar;                     //Variable para validar comentarios
    
    public AdministradorController() {
       
        this.foroValidar=new foroValidar();                            // Instancia de la clase foroValidar
        this.comentarioValidar=new comentarioValidar();               // Instancia de la clase comentarioalidar
        Conexion conn=new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
    }
    
     ///////////////////////////////////////
      //Pantalla de nueva entrada en el foro
        @RequestMapping(value="nuevaEntradaAdmin.htm",method = RequestMethod.GET) 
    
     public ModelAndView nuevaEntradaForo(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE NUTRIOLOGOS
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
      
         
                
                ModelAndView mv=new ModelAndView();  //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntradaAdmin");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                
                return mv;                                                                                           //Retornarmos el modelo
                
     
         
                    
                
    }
     
     
     ///////////////////////////////////////
      //Pantalla de FORO PRINCIPAL
        @RequestMapping(value="ForoPrincipalAdmin.htm",method = RequestMethod.GET) 
    
     public ModelAndView ForoPrincipal(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
           
        
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
       
       
                
                ModelAndView mv=new ModelAndView();                  //CREACIÓN DEL MODELO
                mv.setViewName("ForoPrincipalAdmin");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
        @RequestMapping(value="ConsultarEntradaAdmin.htm",method = RequestMethod.GET) 
    
     public ModelAndView consultarEntrada (@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
      
        
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaAdmin");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                   String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR                                      //PASAMOS OBJETO NUTRIOLOGO    
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
  //Verificador General
    @RequestMapping(value="bienvenida_admin.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_admin y se aplicará el método GET
    
     public ModelAndView listadoController(@ModelAttribute("Administrador") Administrador n, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
   
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
      ModelAndView mv=new ModelAndView();                                // Creación del modelo
                         mv.setViewName("bienvenida_admin");                                            // Nombra al modelo
                         System.out.println("Pasando datos"); 
                         
                     
                         
      
                     String    sql=" select * from paciente where estatus <> 4 and estatus and estatus <> 0 order by ap_uno asc;"; // Se buscan todos aquellos pacientes que tengan un estatus entre 1 y 3

            
      
            List  datosL=this.jdbcTemplate.queryForList(sql);
                  
             mv.addObject("ListaP",datosL);       // SE AGREGA EL OBJETO LISTA DE PACIENTES AL MODELO     
            
             System.out.println(datosL);
             
             System.out.println(datosL.size());
      
             mv.addObject("LongitudP",datosL.size());   
             mv.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO
          
             
            String  sql2=" select * from nutriologo where estatus <> 4 and estatus <> 0 order by ap_uno asc;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3

            
      
             List datosL2=this.jdbcTemplate.queryForList(sql2);
                  
             mv.addObject("ListaN",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO     
            
             System.out.println(datosL2);
              System.out.println(datosL2.size());
      
             mv.addObject("LongitudN",datosL2.size()); 
             
      
       
             mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                  
       
             
             
             String sql3=" select * from psicologo where estatus <> 4 and estatus <> 0 order by ap_uno asc;"; // Se buscan todos aquellos psicologos que tengan un estatus entre 1 y 3

            
      
             List datosL3=this.jdbcTemplate.queryForList(sql3);
                  
             mv.addObject("ListaPs",datosL3);       // SE AGREGA EL OBJETO LISTA DE PSICOLOGOS AL MODELO     
            
             System.out.println(datosL3);
              System.out.println(datosL3.size());
      
             mv.addObject("LongitudPs",datosL3.size()); 
             
             
             
             
      
       
             mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             
             mv.addObject("Mensaje",new Mensaje());     // SE AGREGA EL OBJETO MENSAJE AL MODELO
       
             sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());
                         return mv;
                        
    }
    
    
     /////////////////////////////////////////////////////////////// 
  //Verificador General
    @RequestMapping(value="verificacion_cuentas.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA registroN y se aplicará el método GET
    
   
    public ModelAndView verificarController(@ModelAttribute("Administrador") Administrador n, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
   ModelAndView mav = new ModelAndView();              // CREACIÓN DEL MODELO
       
   HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
   
   
       mav.setViewName("verificacion_cuentas");                       // SE NOMBRA AL MODELO
       
       
      
      String sql="select * from Nutriologo where estatus = 0;";

            
             List datosL=this.jdbcTemplate.queryForList(sql);
             String puesto ="Nutriologo";        
             mav.addObject("ListaN",datosL);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto",puesto);
             System.out.println(datosL);
             System.out.println(puesto);
      
        String sql2="select * from psicologo where estatus = 0;";

            
             List datosL2=this.jdbcTemplate.queryForList(sql2);
             String puesto2 ="Psicologo";        
             mav.addObject("ListaPs",datosL2);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto2",puesto2);
             System.out.println(datosL2);
             System.out.println(puesto2);      
             
             mav.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             mav.addObject("Nutriologo",new Nutriologo());       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
             
             sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mav.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mav.addObject("Administrador",new Administrador());
                         
       
                                 

       return mav;
    }
     
     
  
    
    
    /////////////////////////////////////////////
     ///pantalla de vista mensajeriaN
      @RequestMapping(value="mensajeriaAdmin.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeriaAdmin y se aplicará el método GET
    
     public ModelAndView mensajeriaAdmin(@ModelAttribute("Administrador") Administrador n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaAdmin
        System.out.println("detecte mensajeria"); 
        System.out.println("AQUI ESTA LA MENSAJERIA");
       
         HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
        
        
                System.out.println("NO_ EMPLEADO: "+alert);
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaAdmin");
                mv.addObject("Administrador",new Administrador());     // SE AGREGA EL OBJETO ADMINISTRADOR AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
     } 
     
    
       /////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foroAdmin.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foroN y se aplicará el método GET
    
    public ModelAndView foroAdmin(@ModelAttribute("Administrador") Administrador n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
       HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                        //CREACIÓN DEL MODELO
                mv.setViewName("foroAdmin");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);    
                                 mv.addObject("ListaAdmin",datosL2);                                                       // Pasa la lilsta completa
                                  mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR           
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
    
     
     
     
    
    
    
    @RequestMapping(params="solicitudes",method = RequestMethod.POST)
  
     public ModelAndView cambiarSolicitudes(@ModelAttribute("Administrador") Administrador n, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
   ModelAndView mav = new ModelAndView();              // CREACIÓN DEL MODELO
       
   HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
   
   
       mav.setViewName("verificacion_cuentas");                       // SE NOMBRA AL MODELO
       
       
      
      String sql="select * from Nutriologo where estatus = 0;";

            
             List datosL=this.jdbcTemplate.queryForList(sql);
             String puesto ="Nutriologo";        
             mav.addObject("ListaN",datosL);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto",puesto);
             System.out.println(datosL);
             System.out.println(puesto);
      
        String sql2="select * from psicologo where estatus = 0;";

            
             List datosL2=this.jdbcTemplate.queryForList(sql2);
             String puesto2 ="Psicologo";        
             mav.addObject("ListaPs",datosL2);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto2",puesto2);
             System.out.println(datosL2);
             System.out.println(puesto2);      
             
             mav.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             mav.addObject("Nutriologo",new Nutriologo());       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
             
             sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mav.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mav.addObject("Administrador",new Administrador());
                         
       
       return mav;
    }
     
     
     
     @RequestMapping(params="bienvenida",method = RequestMethod.POST)
  
     public ModelAndView cambiarBienvenida(@ModelAttribute("Administrador") Administrador n, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
   
         HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
      ModelAndView mv=new ModelAndView();                                // Creación del modelo
                         mv.setViewName("bienvenida_admin");                                            // Nombra al modelo
                         System.out.println("Pasando datos"); 
                         
                     
                         
      
                     String    sql=" select * from paciente where estatus <> 4 and estatus and estatus <> 0 order by ap_uno asc;"; // Se buscan todos aquellos pacientes que tengan un estatus entre 1 y 3

            
      
            List  datosL=this.jdbcTemplate.queryForList(sql);
                  
             mv.addObject("ListaP",datosL);       // SE AGREGA EL OBJETO LISTA DE PACIENTES AL MODELO     
            
             System.out.println(datosL);
             
             System.out.println(datosL.size());
      
             mv.addObject("LongitudP",datosL.size());   
             mv.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO
          
             
            String  sql2=" select * from nutriologo where estatus <> 4 and estatus <> 0 order by ap_uno asc;"; // Se buscan todos aquellos nutriologos que tengan un estatus entre 1 y 3

            
      
             List datosL2=this.jdbcTemplate.queryForList(sql2);
                  
             mv.addObject("ListaN",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO     
            
             System.out.println(datosL2);
              System.out.println(datosL2.size());
      
             mv.addObject("LongitudN",datosL2.size()); 
             
      
       
             mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                  
       
             
             
             String sql3=" select * from psicologo where estatus <> 4 and estatus <> 0 order by ap_uno asc;"; // Se buscan todos aquellos psicologos que tengan un estatus entre 1 y 3

            
      
             List datosL3=this.jdbcTemplate.queryForList(sql3);
                  
             mv.addObject("ListaPs",datosL3);       // SE AGREGA EL OBJETO LISTA DE PSICOLOGOS AL MODELO     
            
             System.out.println(datosL3);
              System.out.println(datosL3.size());
      
             mv.addObject("LongitudPs",datosL3.size()); 
             
             
             
             
      
       
             mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             
             mv.addObject("Mensaje",new Mensaje());     // SE AGREGA EL OBJETO MENSAJE AL MODELO
       
             sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());
                         return mv;
                        
    }
     
     
     
     @RequestMapping(params="mensajes",method = RequestMethod.POST)
  
     public ModelAndView cambiarMensajeria(@ModelAttribute("Administrador") Administrador n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaAdmin
        System.out.println("detecte mensajeria"); 
        System.out.println("AQUI ESTA LA MENSAJERIA");
       
         HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
        
        
                System.out.println("NO_ EMPLEADO: "+alert);
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaAdmin");
                mv.addObject("Administrador",new Administrador());     // SE AGREGA EL OBJETO ADMINISTRADOR AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
     } 
     
     //VISTA FORO
     @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Administrador") Administrador n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
         HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
         
                
                ModelAndView mv=new ModelAndView();                        //CREACIÓN DEL MODELO
                mv.setViewName("foroAdmin");                                    //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);    
                                 mv.addObject("ListaAdmin",datosL2);                                                       // Pasa la lilsta completa
                                  mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR           
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
     ///////RECHAZAR PSICOLOGO
  
     @RequestMapping(params="rechazarPs",method = RequestMethod.POST)
  
     public ModelAndView verificarRechazar(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
        System.out.println("Detecte rechazar"); 
       
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
       
         String sql="update psicologo set estatus=4 where no_cedula ="+ps.getNo_cedula()+";";

         this.jdbcTemplate.update(sql);
                
                
                ModelAndView mav=new ModelAndView();
                mav.setViewName("verificacion_cuentas");
                
             sql="select * from Nutriologo where estatus = 0;";

            
             List datosL=this.jdbcTemplate.queryForList(sql);
             String puesto ="Nutriologo";        
             mav.addObject("ListaN",datosL);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto",puesto);
             System.out.println(datosL);
             System.out.println(puesto);
            
             String sql2="select * from psicologo where estatus = 0;";

            
             List datosL2=this.jdbcTemplate.queryForList(sql2);
             String puesto2 ="Psicologo";        
             mav.addObject("ListaPs",datosL2);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto2",puesto2);
             System.out.println(datosL2);
             System.out.println(puesto2);      
             
             mav.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             mav.addObject("Nutriologo",new Nutriologo());       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
       
             
              sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mav.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mav.addObject("Administrador",new Administrador());
                         
             
                return mav;
        }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     ////////////////////////
     //APROBAR PSICOLOGO
     
     
       @RequestMapping(params="aprobarPs",method = RequestMethod.POST)
  
     public ModelAndView verificarAceptar(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
        System.out.println("Detecte aprobar"); 
        
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
        
        
       System.out.println(ps.getNo_cedula());
       String sql="update psicologo set estatus=1 where no_cedula ="+ps.getNo_cedula()+";";

       this.jdbcTemplate.update(sql);
                
                
                ModelAndView mav=new ModelAndView();
                mav.setViewName("verificacion_cuentas");
             sql="select * from Nutriologo where estatus = 0;";

            
             List datosL=this.jdbcTemplate.queryForList(sql);
             String puesto ="Nutriologo";        
             mav.addObject("ListaN",datosL);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto",puesto);
             System.out.println(datosL);
             System.out.println(puesto);
            
            String sql2="select * from psicologo where estatus = 0;";

            
             List datosL2=this.jdbcTemplate.queryForList(sql2);
             String puesto2 ="Psicologo";        
             mav.addObject("ListaPs",datosL2);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto2",puesto2);
             System.out.println(datosL2);
             System.out.println(puesto2);      
             
             mav.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             mav.addObject("Nutriologo",new Nutriologo());       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
       
             
               sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mav.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mav.addObject("Administrador",new Administrador());
                         
       
                                              
               List<String> dias = new ArrayList<String>();                  
                                 
    //METODO PARA ARMAR EL CALENDARIO                             
       Calendar c = Calendar.getInstance();                  // CREACION DE UNA INSTANCIA DE CALENDARIO
   
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);            // EMPEZAR LA SEMANA EN LUNES, SE TOMA EL LUNES DE LA SEMANA EN CURSO
    
   
    DateFormat df = new SimpleDateFormat("EEE yyyy/MM/dd");            // DAR FORMATO A LA FECHA  yyyy//mm/dd
    
    for (int i = 0; i < 30; i++) {                                  // CONTAMOS 30 DÍAS HABILES PARA ASIGNAR AL CALENDARIO DE CITAS
     System.out.println(df.format(c.getTime()));
     dias.add(df.format(c.getTime()).toString());                  // AGREGAMOS A NUESTRA LSITA EL DIA CORRESPONDIENTE
     int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);                 // OBTIENE EL DÍA DE LA SEMANA
     
     if (dayOfWeek == Calendar.FRIDAY) {                         // SI EL DÍA CORRESPONDE A VIERNES
      c.add(Calendar.DATE, 3);                                   // AVANZA 3 DÍAS EN EL CALENDARIO
     }  else { 
      c.add(Calendar.DATE, 1);                                   // SI ESTA EN CUALQUIER OTRO DÍA DE LA SEMANA HABIL AVANZA UN DÍA
     } 

    
    }               
    
                                 
          System.out.println(dias.size());
          System.out.println(dias.get(0));
          for(int i=0; i<dias.size();i++){
              System.out.println(dias.get(i).substring(4,dias.get(i).length()));      // LIMPLIA EL NOMBRE DEL DIA Y LO OBTIENE EN FORMATO yyyy/mm/dd
                      
          } 
          
          
          String horaEntrada=ps.getHoraEntrada().substring(0, ps.getHoraEntrada().length()-3);
          String horaSalida=ps.getHoraSalida().substring(0, ps.getHoraSalida().length()-3);
          
          int horaEn=Integer.parseInt(horaEntrada.substring(0, 2)); // PASAR HORA A ENTERO
          int horaS=Integer.parseInt(horaSalida.substring(0,2));     // PASAR HORA A ENTERO
          
          float MinEn=Float.parseFloat(horaEntrada.substring(3, 5)); // PASAR MINUTOS A FLOTANTE
          float MinS=Float.parseFloat(horaSalida.substring(3, 5));     // PASAR MINUTOS A FLOTANTE
          
          float minE= MinEn/60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
          float minS= MinS/60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
          
          float horaE=horaEn+minE;    // GENERACIÓN DE HORA DE ENTRADA
          float horaSa=horaS+minS;    // GENERACIÓN DE HORA DE SALIDA
        
          List<String> rangoh = new ArrayList<String>();        
          float horario;
          float horario2; 
          float creaHorario;
          int HoraHorario;
          List<String> rangomh = new ArrayList<String>();  
          String cero="00";
          String media="30";
          String diez="10";
          String veinte="20";
         String cuarenta="40";
          String cincuenta="50";
          //GENERA RANGOS DE UNA 1 HORA
           if(horaE<horaSa){
              horario=horaE+1; //GENERA RANGOS DE UNA HORA
             
               if(horario<horaSa){
                  HoraHorario=(int)horario;
                   if(MinEn==0.0){
                     
                       System.out.println(horaEntrada+"-"+HoraHorario+":"+cero); // CADENA A GUARDAR EN LA LISTA
                       rangoh.add(horaEntrada+"-"+HoraHorario+":"+cero);
                       horario2=horario+1;
                     //   System.out.println("NUEVA HORA DE TERMINO:"+ horario2);
                        while(horario2<=horaSa){
                           
                           
                           System.out.println((int)horario+":"+cero+"-"+(int)horario2+":"+cero); // CADENA A GUARDAR EN LA LISTA
                           rangoh.add((int)horario+":"+cero+"-"+(int)horario2+":"+cero);
                           horario=horario+1;
                           horario2=horario2+1;
                        }
                   }
                   else{
                      System.out.println(horaEntrada+"-"+HoraHorario+":"+(int)MinEn); // CADENA A GUARDAR EN LA LISTA
                      rangoh.add(horaEntrada+"-"+HoraHorario+":"+(int)MinEn);
                      horario2=horario+1;
                     // System.out.println("NUEVA HORA DE TERMINO "+ (int)horario2+":"+(int)MinEn);
                       while(horario2<=horaSa){
                           
                           
                           System.out.println((int)horario+":"+(int)MinEn+"-"+(int)horario2+":"+(int)MinEn); // CADENA A GUARDAR EN LA LISTA
                           rangoh.add((int)horario+":"+(int)MinEn+"-"+(int)horario2+":"+(int)MinEn);
                           horario=horario+1;
                           horario2=horario2+1;
                        }
                      
                   }
                   
                 
            }
           
          }
          
       ////////////////////////GENERAR RANGOS DE MEDIA HORA      
           if(horaE<horaSa){
               System.out.println(horaE);
               System.out.println(horaSa);
            horario=(float) (horaE+0.5);
           // System.out.println(horario+" ESTE ES EL NUEVO HORARIO");
             if(horario<horaSa){
                  HoraHorario=(int)horario;
                   if(MinEn==0.0){
                      
                       System.out.println(horaEntrada+"-"+HoraHorario+":"+media); // CADENA A GUARDAR EN LA LISTA
                       rangomh.add(horaEntrada+"-"+HoraHorario+":"+media);
                       horario2=(float) (horario+0.5);
                        System.out.println("NUEVA HORA DE TERMINO:"+ horario2);
                        while(horario2<=horaSa){
                          
                           if((horario2-(int)horario)==1){
                               System.out.println((int)horario+":"+media+"-"+(int)horario2+":"+cero); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+media+"-"+(int)horario2+":"+cero);
                           }
                           else{
                               System.out.println((int)horario+":"+cero+"-"+(int)horario2+":"+media); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cero+"-"+(int)horario2+":"+media);
                           }
                           horario=(float) (horario+0.5);
                           horario2=(float) (horario2+0.5);
                        }
                   }
                    else{
                      if((int)MinEn==10){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+cuarenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+cuarenta);
                           }
                      if((int)MinEn==20){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+cincuenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+cincuenta);
                           }
                      if((int)MinEn==30){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+cero); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+cero);
                           }
                      if((int)MinEn==40){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+diez); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+diez);
                           }
                      if((int)MinEn==50){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+veinte); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+veinte);
                           }
                      
                      horario2=(float) (horario+0.5);
                      System.out.println("NUEVA HORA DE TERMINO "+ (int)horario2+":"+(int)MinEn);
                       while(horario2<=horaSa){
                          
                           creaHorario=horario-(int)horario;
                           
                          System.out.println(creaHorario=horario-(int)horario);
                           if((float)creaHorario>(float)0.6000000 && (float)creaHorario<(float)0.7000000){
                              
                               System.out.println((int)horario+":"+cuarenta+"-"+(int)horario2+":"+diez); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cuarenta+"-"+(int)horario2+":"+diez);
                           }
                           if((float)creaHorario>(float)0.8000000 && (float)creaHorario<(float)0.9000000){
                              
                               System.out.println((int)horario+":"+cincuenta+"-"+(int)horario2+":"+veinte); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cincuenta+"-"+(int)horario2+":"+veinte);
                           }
                           if((float)creaHorario==(float)0.0){
                              
                               System.out.println((int)horario+":"+cero+"-"+(int)horario2+":"+media); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cero+"-"+(int)horario2+":"+media);
                           }
                            if((float)creaHorario>(float)0.16000000 && (float)creaHorario<(float)0.17000000){
                              
                               System.out.println((int)horario+":"+diez+"-"+(int)horario2+":"+cuarenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+diez+"-"+(int)horario2+":"+cuarenta);
                           }
                           if((float)creaHorario>(float)0.30000000 && (float)creaHorario<(float)0.40000000 ){
                              
                               System.out.println((int)horario+":"+veinte+"-"+(int)horario2+":"+cincuenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+veinte+"-"+(int)horario2+":"+cincuenta);
                           }
                           if((float)creaHorario==(float)0.5){
                              
                               System.out.println((int)horario+":"+media+"-"+(int)horario2+":"+cero); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+media+"-"+(int)horario2+":"+cero);
                           }
                            horario=(float) (horario+0.5);
                           horario2=(float) (horario2+0.5);
                           
                           float baderaError=horario2-horaSa;
                           if(baderaError<0.000010){
                               horario2=(float) (horario2-0.000010);
                           }
                               
                           
                           System.out.println("HORARIO SA: "+horaSa);
                           System.out.println("HORARIO2: "+horario2);
                        }
                      
                   }
                   
             }
            
            
            
}
           
           
           
           
        System.out.println(rangoh.size()+" horario de una hora");
        for(int i=0;i<rangoh.size();i++){
            System.out.println(rangoh.get(i).toString());
        }
             
        System.out.println(rangomh.size()+ "HORARIOS DE MEDIA HORA");
        for(int i=0;i<rangomh.size();i++){
            System.out.println(rangomh.get(i).toString());
        } 
        
        for(int i=0;i<dias.size();i++){
            for(int j=0;j<rangoh.size();j++){
                   sql="insert into cita values(0,0,"+ps.getNo_cedula()+",'"+dias.get(i).substring(4,dias.get(i).length())+"','0','"+rangoh.get(j).toString()+"')"; // INSERTAMOS LA ENTRADA EN EL DIARIO
                               
       
                  this.jdbcTemplate.update(sql);       // INSERTAMOS LA HOJA EN EL DIARIO
            }
             for(int k=0;k<rangomh.size();k++){
                   sql="insert into cita values(0,0,"+ps.getNo_cedula()+",'"+dias.get(i).substring(4,dias.get(i).length())+"','1','"+rangomh.get(k).toString()+"')"; // INSERTAMOS LA ENTRADA EN EL DIARIO
                               
       
                  this.jdbcTemplate.update(sql);       // INSERTAMOS LA HOJA EN EL DIARIO
            }
        }
                return mav;
        }
    
  /////////////////////////
     //RECHAZAR NUTRIÓLOGO
     @RequestMapping(params="rechazarN",method = RequestMethod.POST)
  
     public ModelAndView verificarRechazarN(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
        System.out.println("Detecte rechazar"); 
        
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
        
        
        
       
         String sql="update nutriologo set estatus=4 where no_cedula ="+n.getNo_cedula()+";";

         this.jdbcTemplate.update(sql);
                
                
                ModelAndView mav=new ModelAndView();
                mav.setViewName("verificacion_cuentas");
                
             sql="select * from Nutriologo where estatus = 0;";

            
             List datosL=this.jdbcTemplate.queryForList(sql);
             String puesto ="Nutriologo";        
             mav.addObject("ListaN",datosL);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto",puesto);
             System.out.println(datosL);
             System.out.println(puesto);
            
             String sql2="select * from psicologo where estatus = 0;";

            
             List datosL2=this.jdbcTemplate.queryForList(sql2);
             String puesto2 ="Psicologo";        
             mav.addObject("ListaPs",datosL2);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto2",puesto2);
             System.out.println(datosL2);
             System.out.println(puesto2);      
             
             mav.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             mav.addObject("Nutriologo",new Nutriologo());       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
       
             
               sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mav.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mav.addObject("Administrador",new Administrador());
                         
             
             
                return mav;
        }
     
     
     ///////////////////
     //APROBAR NUTRIOLOGO
     
       @RequestMapping(params="aprobarN",method = RequestMethod.POST)
  
     public ModelAndView verificarAceptarN(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
        System.out.println("Detecte aprobar"); 
        
       
       
   HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Admin");
       System.out.println("ESTO DICE EL ALERT: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
        
         ModelAndView mav = new ModelAndView();              // CREACIÓN DEL MODELO
        
       System.out.println(n.getNo_cedula());
       String sql="update nutriologo set estatus=1 where no_cedula ="+n.getNo_cedula()+";";

       this.jdbcTemplate.update(sql);
                
                
                
             mav.setViewName("verificacion_cuentas");
             sql="select * from Nutriologo where estatus = 0;";

            
             List datosL=this.jdbcTemplate.queryForList(sql);
             String puesto ="Nutriologo";        
             mav.addObject("ListaN",datosL);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto",puesto);
             System.out.println(datosL);
             System.out.println(puesto);
            
            String sql2="select * from psicologo where estatus = 0;";

            
             List datosL2=this.jdbcTemplate.queryForList(sql2);
             String puesto2 ="Psicologo";        
             mav.addObject("ListaPs",datosL2);       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO     
             mav.addObject("puesto2",puesto2);
             System.out.println(datosL2);
             System.out.println(puesto2);      
             
             mav.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             mav.addObject("Nutriologo",new Nutriologo());       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
             
             sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mav.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mav.addObject("Administrador",new Administrador());
              
            
                                 
               List<String> dias = new ArrayList<String>();                  
                                 
    //METODO PARA ARMAR EL CALENDARIO                             
       Calendar c = Calendar.getInstance();                  // CREACION DE UNA INSTANCIA DE CALENDARIO
   
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);            // EMPEZAR LA SEMANA EN LUNES, SE TOMA EL LUNES DE LA SEMANA EN CURSO
    
   
    DateFormat df = new SimpleDateFormat("EEE yyyy/MM/dd");            // DAR FORMATO A LA FECHA  yyyy//mm/dd
    
    for (int i = 0; i < 30; i++) {                                  // CONTAMOS 30 DÍAS HABILES PARA ASIGNAR AL CALENDARIO DE CITAS
     System.out.println(df.format(c.getTime()));
     dias.add(df.format(c.getTime()).toString());                  // AGREGAMOS A NUESTRA LSITA EL DIA CORRESPONDIENTE
     int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);                 // OBTIENE EL DÍA DE LA SEMANA
     
     if (dayOfWeek == Calendar.FRIDAY) {                         // SI EL DÍA CORRESPONDE A VIERNES
      c.add(Calendar.DATE, 3);                                   // AVANZA 3 DÍAS EN EL CALENDARIO
     }  else { 
      c.add(Calendar.DATE, 1);                                   // SI ESTA EN CUALQUIER OTRO DÍA DE LA SEMANA HABIL AVANZA UN DÍA
     } 

    
    }               
    
                                 
          System.out.println(dias.size());
          System.out.println(dias.get(0));
          for(int i=0; i<dias.size();i++){
              System.out.println(dias.get(i).substring(4,dias.get(i).length()));      // LIMPLIA EL NOMBRE DEL DIA Y LO OBTIENE EN FORMATO yyyy/mm/dd
                      
          } 
          
          
          String horaEntrada=n.getHoraEntrada().substring(0, n.getHoraEntrada().length()-3);
          String horaSalida=n.getHoraSalida().substring(0, n.getHoraSalida().length()-3);
          
          System.out.println("HORA DE ENTRADA: "+horaEntrada);
          System.out.println("HORA DE SALIDA: "+horaSalida);
          
          int horaEn=Integer.parseInt(horaEntrada.substring(0, 2)); // PASAR HORA A ENTERO
          int horaS=Integer.parseInt(horaSalida.substring(0,2));     // PASAR HORA A ENTERO
          
          float MinEn=Float.parseFloat(horaEntrada.substring(3, 5)); // PASAR MINUTOS A FLOTANTE
          float MinS=Float.parseFloat(horaSalida.substring(3, 5));     // PASAR MINUTOS A FLOTANTE
          
          float minE= MinEn/60;       // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
          float minS= MinS/60;        // OBTENER EL EQUIVALENTE A 1 DE LA CANTIDAD DE MINUTOS 
          
          float horaE=horaEn+minE;    // GENERACIÓN DE HORA DE ENTRADA
          float horaSa=horaS+minS;    // GENERACIÓN DE HORA DE SALIDA
        
          List<String> rangoh = new ArrayList<String>();        
          float horario;
          float horario2; 
          float creaHorario;
          int HoraHorario;
          List<String> rangomh = new ArrayList<String>();  
          String cero="00";
          String media="30";
          String diez="10";
          String veinte="20";
         String cuarenta="40";
          String cincuenta="50";
          //GENERA RANGOS DE UNA 1 HORA
           if(horaE<horaSa){
              horario=horaE+1; //GENERA RANGOS DE UNA HORA
             
               if(horario<horaSa){
                  HoraHorario=(int)horario;
                   if(MinEn==0.0){
                     
                       System.out.println(horaEntrada+"-"+HoraHorario+":"+cero); // CADENA A GUARDAR EN LA LISTA
                       rangoh.add(horaEntrada+"-"+HoraHorario+":"+cero);
                       horario2=horario+1;
                     //   System.out.println("NUEVA HORA DE TERMINO:"+ horario2);
                        while(horario2<=horaSa){
                           
                           
                           System.out.println((int)horario+":"+cero+"-"+(int)horario2+":"+cero); // CADENA A GUARDAR EN LA LISTA
                           rangoh.add((int)horario+":"+cero+"-"+(int)horario2+":"+cero);
                           horario=horario+1;
                           horario2=horario2+1;
                        }
                   }
                   else{
                      System.out.println(horaEntrada+"-"+HoraHorario+":"+(int)MinEn); // CADENA A GUARDAR EN LA LISTA
                      rangoh.add(horaEntrada+"-"+HoraHorario+":"+(int)MinEn);
                      horario2=horario+1;
                     // System.out.println("NUEVA HORA DE TERMINO "+ (int)horario2+":"+(int)MinEn);
                       while(horario2<=horaSa){
                           
                           
                           System.out.println((int)horario+":"+(int)MinEn+"-"+(int)horario2+":"+(int)MinEn); // CADENA A GUARDAR EN LA LISTA
                           rangoh.add((int)horario+":"+(int)MinEn+"-"+(int)horario2+":"+(int)MinEn);
                           horario=horario+1;
                           horario2=horario2+1;
                        }
                      
                   }
                   
                 
            }
           
          }
          
      ////////////////////////GENERAR RANGOS DE MEDIA HORA      
           if(horaE<horaSa){
               System.out.println(horaE);
               System.out.println(horaSa);
            horario=(float) (horaE+0.5);
           // System.out.println(horario+" ESTE ES EL NUEVO HORARIO");
             if(horario<horaSa){
                  HoraHorario=(int)horario;
                   if(MinEn==0.0){
                      
                       System.out.println(horaEntrada+"-"+HoraHorario+":"+media); // CADENA A GUARDAR EN LA LISTA
                       rangomh.add(horaEntrada+"-"+HoraHorario+":"+media);
                       horario2=(float) (horario+0.5);
                        System.out.println("NUEVA HORA DE TERMINO:"+ horario2);
                        while(horario2<=horaSa){
                          
                           if((horario2-(int)horario)==1){
                               System.out.println((int)horario+":"+media+"-"+(int)horario2+":"+cero); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+media+"-"+(int)horario2+":"+cero);
                           }
                           else{
                               System.out.println((int)horario+":"+cero+"-"+(int)horario2+":"+media); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cero+"-"+(int)horario2+":"+media);
                           }
                           horario=(float) (horario+0.5);
                           horario2=(float) (horario2+0.5);
                        }
                   }
                    else{
                      if((int)MinEn==10){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+cuarenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+cuarenta);
                           }
                      if((int)MinEn==20){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+cincuenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+cincuenta);
                           }
                      if((int)MinEn==30){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+cero); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+cero);
                           }
                      if((int)MinEn==40){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+diez); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+diez);
                           }
                      if((int)MinEn==50){
                               System.out.println(horaEntrada+"-"+HoraHorario+":"+veinte); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add(horaEntrada+"-"+HoraHorario+":"+veinte);
                           }
                      
                      horario2=(float) (horario+0.5);
                      System.out.println("NUEVA HORA DE TERMINO "+ (int)horario2+":"+(int)MinEn);
                       while(horario2<=horaSa){
                          
                           creaHorario=horario-(int)horario;
                           
                          System.out.println(creaHorario=horario-(int)horario);
                           if((float)creaHorario>(float)0.6000000 && (float)creaHorario<(float)0.7000000){
                              
                               System.out.println((int)horario+":"+cuarenta+"-"+(int)horario2+":"+diez); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cuarenta+"-"+(int)horario2+":"+diez);
                           }
                           if((float)creaHorario>(float)0.8000000 && (float)creaHorario<(float)0.9000000){
                              
                               System.out.println((int)horario+":"+cincuenta+"-"+(int)horario2+":"+veinte); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cincuenta+"-"+(int)horario2+":"+veinte);
                           }
                           if((float)creaHorario==(float)0.0){
                              
                               System.out.println((int)horario+":"+cero+"-"+(int)horario2+":"+media); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+cero+"-"+(int)horario2+":"+media);
                           }
                            if((float)creaHorario>(float)0.16000000 && (float)creaHorario<(float)0.17000000){
                              
                               System.out.println((int)horario+":"+diez+"-"+(int)horario2+":"+cuarenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+diez+"-"+(int)horario2+":"+cuarenta);
                           }
                           if((float)creaHorario>(float)0.30000000 && (float)creaHorario<(float)0.40000000 ){
                              
                               System.out.println((int)horario+":"+veinte+"-"+(int)horario2+":"+cincuenta); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+veinte+"-"+(int)horario2+":"+cincuenta);
                           }
                           if((float)creaHorario==(float)0.5){
                              
                               System.out.println((int)horario+":"+media+"-"+(int)horario2+":"+cero); // CADENA A GUARDAR EN LA LISTA
                               rangomh.add((int)horario+":"+media+"-"+(int)horario2+":"+cero);
                           }
                            horario=(float) (horario+0.5);
                           horario2=(float) (horario2+0.5);
                           
                           float baderaError=horario2-horaSa;
                           if(baderaError<0.000010){
                               horario2=(float) (horario2-0.000010);
                           }
                               
                           
                           System.out.println("HORARIO SA: "+horaSa);
                           System.out.println("HORARIO2: "+horario2);
                        }
                      
                   }
                   
             }
            
            
            
}
           
           
           
           
        System.out.println(rangoh.size()+" horario de una hora");
        for(int i=0;i<rangoh.size();i++){
            System.out.println(rangoh.get(i).toString());
        }
             
        System.out.println(rangomh.size()+ "HORARIOS DE MEDIA HORA");
        for(int i=0;i<rangomh.size();i++){
            System.out.println(rangomh.get(i).toString());
        }                      
                                 
                                 
      
           for(int i=0;i<dias.size();i++){
            for(int j=0;j<rangoh.size();j++){
                   sql="insert into cita values(0,0,"+n.getNo_cedula()+",'"+dias.get(i).substring(4,dias.get(i).length())+"','0','"+rangoh.get(j).toString()+"')"; // INSERTAMOS LA ENTRADA EN EL DIARIO
                               
       
                  this.jdbcTemplate.update(sql);       // INSERTAMOS LA HOJA EN EL DIARIO
            }
             for(int k=0;k<rangomh.size();k++){
                   sql="insert into cita values(0,0,"+n.getNo_cedula()+",'"+dias.get(i).substring(4,dias.get(i).length())+"','1','"+rangomh.get(k).toString()+"')"; // INSERTAMOS LA ENTRADA EN EL DIARIO
                               
       
                  this.jdbcTemplate.update(sql);       // INSERTAMOS LA HOJA EN EL DIARIO
            }
        }                         
                                 
                                 
                                 
                                 
                                 
       
                return mav;
        }
    
      
     
       //////////////////////
     //////////PANTALLA DE NUEVA ENTRADA EN EL FORO
     
      @RequestMapping(params="nuevaEntrada",method = RequestMethod.POST)
     public ModelAndView cambiarNuevaEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
       HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE NUTRIOLOGOS
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
        
      
         
                
                ModelAndView mv=new ModelAndView();  //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntradaAdmin");      //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                
                return mv;                                                                                           //Retornarmos el modelo
                
     
                
     
         
     }
     
     
     
      ////////////////////
     ////ACCIÓN DE BOTON guardarEntrada
     
      @RequestMapping(params="guardarEntrada", method = RequestMethod.POST)
    public ModelAndView guardarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
       this.foroValidar.validate(eF, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                            // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("nuevaEntradaAdmin");                                  //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                  String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
                                 mv.addObject("entradaForo",new entradaForo());                                       // PASAMOS OBJETO entradaForo
                                 
                return mv;                                                                                            //RETORNAMOS EL MODELO
         }                                                                               // CIERRE DE IF
         else{                                                                           // INICIO ELSE
             String sql="insert into entrada values("+'0'+","+alert+",'"+eF.getTitulo()+"','"+eF.getContenido()+"','','"+eF.getFecha()+"');";   // INSERTAMOS EN LA TABLA ENTRADA NUESTRO id_usuario mientras el dato de session alert, el titulo de nuestra entrada y el contenido
                               
       
                this.jdbcTemplate.update(sql);                                                                              // REALIZAMOS LA INSERCIÓN
            
       
       
        
            
            
            
           ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaAdmin");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
     
        String sql=" delete from entrada where id_entrada="+eF.getId_entrada()+";";         // BORRAMOS LA ENTRADA IDENTIFICADA CON EL ID id_entrada
                               
       
                this.jdbcTemplate.update(sql);                                                // REALIZAMOS LA ELIMINACIÓN DE LA ENTRADA
            
       
                
        sql="delete  from comentarios where id_entrada="+eF.getId_entrada();                   // BORRAMOS TODOS LOS COMENTARIOS DE REALACIONADOS A LA ENTRADA CON EL ID id_entrada
                     this.jdbcTemplate.update(sql);                                            // REALIZAMOS LA ELIMINACION DE LOS COMENTARIOS
                                          
                
       
        
            
            
            
           return new ModelAndView("redirect:/foroAdmin.htm");                                       // REDIRECCIONAMOS AL FORO
        
       
            
                
       
       
    }
    
     
     //////////////////////
    ////////ACCIÓN DEL BOTÓN MoficiarEntrada
     
     @RequestMapping(params="ModificarEntrada", method = RequestMethod.POST)
    public ModelAndView ModificarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
       System.out.println("MODIFICAR ENTRADA DEL FORO");
            
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
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
                mv.setViewName("ConsultarEntradaAdmin");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                String   sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
                mv.setViewName("ConsultarEntradaAdmin");                            //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaAdmin");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                   String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR                                    //PASAMOS OBJETO NUTRIOLOGO    
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
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
       
       
                
                ModelAndView mv=new ModelAndView();                  //CREACIÓN DEL MODELO
                mv.setViewName("ForoPrincipalAdmin");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS  
        this.comentarioValidar.validate(comen, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                        // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                ModelAndView mv=new ModelAndView();                          //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaAdmin");                          //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
             String sql="insert into comentarios values("+'0'+","+comen.getId_entrada()+",'"+comen.getId_usuario()+"','"+comen.getContenido()+"','','"+comen.getFecha()+"');"   ;  // INSERTAMOS EL COMENTARIO EN LA ENTRADA CON EL ID id_entrada EL TITULO Y EL CONTENIDO
                               
       
                this.jdbcTemplate.update(sql);       // INSERTAMOS EL COMENTARIO
             

      
                
       
       
        
            
            
            
          ModelAndView mv=new ModelAndView();                               //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaAdmin");                         //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
        
           String  sql="delete  from comentarios where id_comnt="+comen.getId_comnt();     //ELIMINAMOS EL COMENTARIO RELACIONADO CON EL ID id_comnt
                     this.jdbcTemplate.update(sql);                                    //ELIMINAMOS EL COMENTARIO

      
                
       
       

            
            
            
          ModelAndView mv=new ModelAndView();                              //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarEntradaAdmin");                        //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
                
                sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());                                    //PASAMOS OBJETO ADMINISTRADOR
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
    public ModelAndView logout(@ModelAttribute("Administrador") Administrador admin, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
                
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Admin");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
          
          
       
            
            session.removeAttribute("Admin");                                 // REMOVEMOS EL ATRIBUTO DE SESION RELACIONADO AL PSICÓLOGO
                  
            session.invalidate();                                                 // INVALIDAMOS LA SESION
            
           
            
            
            
            
            return new ModelAndView("redirect:/login.htm");                      // REDIRECCIONAMOS A LOGIN
        
       
            
                
       
       
    }
   
     
     
     
     
     
     
     
     
     
     
}
