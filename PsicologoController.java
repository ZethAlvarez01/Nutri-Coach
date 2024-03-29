/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ActividadP;
import models.ActividadPValidar;
import models.Comentario;
import models.Conexion;
import models.Nutriologo;
import models.Paciente;

import models.Psicologo;
import models.comentarioValidar;
import models.diario;
import models.diarioValidar;
import models.diarioValidarObservacion;
import models.entradaForo;
import models.foroValidar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class PsicologoController {
    private JdbcTemplate jdbcTemplate;
   private foroValidar foroValidar;                                 //Variable para validar foro
   private comentarioValidar comentarioValidar;                     //Variable para validar comentarios
   private ActividadPValidar ActividadPValidar;                     //Variable para validar activad del psicólogo
    private diarioValidarObservacion diarioValidarObservacion;                     //Variable para validar activad del psicólogo
           

     public PsicologoController() {
       this.foroValidar=new foroValidar();                            // Instancia de la clase foroValidar
        this.comentarioValidar=new comentarioValidar();               // Instancia de la clase comentarioalidar
        this.ActividadPValidar=new ActividadPValidar();               // Instancia de la clase ActovodadPValidar
        this.diarioValidarObservacion=new diarioValidarObservacion();                       // Instancia de la clase diarioPValidar
        Conexion conn=new Conexion();                                 //Instacia a la conexión de base de datos
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());         //Instacia a la conexión de base de datos
    }
     
      ///////////////////////////////
     //PETICION DE CRONOGRAMA 
      
      @RequestMapping(value="/mostrarHorarioPsicologo", method=RequestMethod.GET)
       public @ResponseBody String getCrono(@RequestParam String fechaConsulta, @RequestParam String no_empleadoConsulta){
        System.out.println("-----getCrono-------");
        
        System.out.println(fechaConsulta+" esta fue la fecha");
          System.out.println(no_empleadoConsulta+" este es el no_empleado");
       String sql=" select no_cedula from psicologo where no_empleado="+no_empleadoConsulta;   // CONSULTA PARA EXTRAER DATOS HORARIOS
                               List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
           System.out.println(datosL2);
           
         String cedula=datosL2.get(0).toString().substring(11, datosL2.get(0).toString().length()-1);
           System.out.println(cedula);
           
      sql="select t1.nombre, t1.ap_uno, t1.ap_dos, t1.no_boleta, t2.no_cita,t2.horario from paciente t1 inner join cita t2 on t1.no_boleta=t2.no_boleta and t1.no_cedulap=t2.no_cedula and t2.no_cedula="+cedula+" and t2.fecha='"+fechaConsulta+"' and t2.estado=3 order by t2.horario"; 
           datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
           System.out.println(datosL2);
           
           String objeto;
           if(datosL2.isEmpty()){
                objeto = new Gson().toJson(null);
           }
           else{
                objeto = new Gson().toJson(datosL2);
           }
     
                               
                              
         
        return objeto; 
    }
       
     
   
     
     
           /////////////////////////////////////////////////////////////// 
  //pantalla de lista de pacientes de psicologo
    @RequestMapping(value="ConsultarPacientePrincipalp.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_psicologo y se aplicará el método GET
    
      public ModelAndView ConsultarPacientePrincipalp(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_psicologo
        System.out.println("paciente"); 
        
                
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarPacientePrincipalp");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
         
                
                
                
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO        
                
                         
                 sql="select no_boleta,no_cedula,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,"
                        + "domicilio,correo from paciente where no_cedulap= (select no_cedula from psicologo where no_empleado="+alert+") order by ap_uno"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
                datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
             
                                 mv.addObject("ListaPacientes",datosL2);          // Pasa la lilsta completa
                                 
                
                mv.addObject("LongitudP",datosL2.size());    // SE PASA EL TOTAL DE PACIENTES
                mv.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO  
                
                 
                                 
                return mv;
                
     }
       
     
     
     
     
     
                    /////////////////////////////////////////////////////////////// 
  //pantalla de lista Diario del paciente
    @RequestMapping(value="diario_psicologo_solo_paciente.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_psicologo y se aplicará el método GET
    
      public ModelAndView ConsultarDiario(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_psicologo
        
        
                
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         ModelAndView mv=new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("diario_psicologo_solo_paciente");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        
        
         String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                 mv.addObject("diario",new diario());                                             //PASAMOS OBJETO PSICOLOGO
                
                                 
           sql="select no_boleta fecha,contenido from actividadp where no_cedula=(select no_cedula from psicologo where no_empleado="+alert+") order by fecha desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                   datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("ActividadP",datosL2);                                                       // Pasa la lilsta completa                  
                return mv;
                
     }
       
     
     
     
     
     
   
                /////////////////////////////////////////////////////////////// 
  //pantalla de lista Edición de actividad 
    @RequestMapping(value="ConsultarActividadp.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA ConsultarActividad y se aplicará el método GET
    
      public ModelAndView ConsultarActividadp(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_psicologo
        
        
                
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarActividadp");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
         
                
                
                
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO        
                
                                 
                sql="select no_boleta,no_cedula,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,"
                        + "domicilio,correo from paciente where no_cedulap= (select no_cedula from psicologo where no_empleado="+alert+")"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
                datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);          // Pasa la lilsta completa
                                 
                
                mv.addObject("LongitudP",datosL2.size());    // SE PASA EL TOTAL DE PACIENTES
                mv.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO  
                 mv.addObject("ActividadP",new ActividadP());     // SE AGREGA EL OBJETO ActividadP AL MODELO
                                 
                return mv;
                
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
        
                
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS 
        
       
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("bienvenida_psicologo");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
         
                
                
                
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO        
                
                         
                sql="select no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                datosL2 = this.jdbcTemplate.queryForList(sql); 
                String cadena=datosL2.toString();
                
                System.out.println(cadena);
                
                String cedula=cadena.substring(12, cadena.length()-2);
                System.out.println(cedula);
                                  
                                 
                 sql="select * from paciente t1 inner join actividadp t2 on t1.no_cedulap=t2.no_cedula and t1.no_boleta=t2.no_boleta and t2.no_cedula="+cedula+" order by t1.ap_uno,t2.fecha"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
                datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                 mv.addObject("ListaActividades",datosL2);          // Pasa la lilsta completa
                
                
                
                
                sql="select no_boleta,no_cedula,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,"
                        + "domicilio,correo from paciente where no_cedulap= (select no_cedula from psicologo where no_empleado="+alert+") order by ap_uno"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
                datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);          // Pasa la lilsta completa
                                 
                
                mv.addObject("LongitudP",datosL2.size());    // SE PASA EL TOTAL DE PACIENTES
                mv.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO  
                 mv.addObject("ActividadP",new ActividadP());     // SE AGREGA EL OBJETO ActividadP AL MODELO
                 mv.addObject("diario",new diario());     // SE AGREGA EL OBJETO ActividadP AL MODELO
                 
                 
                                 
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
                  sql = "select id_entrada,fecha from comentarios where id_usuario="+alert+" order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("FechaComentarios",datosL2);                                               // PASAMOS LA LISTA COMPLETA
              
                                 
                   sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("NombreEntrada",datosL2);                                               // PASAMOS LA LISTA COMPLETA            
                                 
                                 
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
                 sql = "select id_entrada,fecha from comentarios where id_usuario="+alert+" order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("FechaComentarios",datosL2);                                               // PASAMOS LA LISTA COMPLETA
              
                                 
                   sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("NombreEntrada",datosL2);                                               // PASAMOS LA LISTA COMPLETA            
                                 
                                 
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
         
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("bienvenida_psicologo");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
         
                
                
                
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO        
                
                                 
                sql="select no_boleta,no_cedula,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,"
                        + "domicilio,correo from paciente where no_cedulap= (select no_cedula from psicologo where no_empleado="+alert+")"; //OBTENEMOS TODOS LOS PACIENTES RELACIONADOS AL PSICÓLGO
                datosL2 = this.jdbcTemplate.queryForList(sql);                     // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 
                                 mv.addObject("ListaPacientes",datosL2);          // Pasa la lilsta completa
                                 
                
                mv.addObject("LongitudP",datosL2.size());    // SE PASA EL TOTAL DE PACIENTES
                mv.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO  
                 mv.addObject("ActividadP",new ActividadP());     // SE AGREGA EL OBJETO ActividadP AL MODELO
                                 
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
             String sql="insert into comentarios values("+'0'+","+comen.getId_entrada()+",'"+comen.getId_usuario()+"','"+comen.getContenido()+"','','"+comen.getFecha()+"');"   ;  // INSERTAMOS EL COMENTARIO EN LA ENTRADA CON EL ID id_entrada EL TITULO Y EL CONTENIDO
                               
       
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
     ////ACCIÓN DE BOTON guardarEntrada
     
      @RequestMapping(params="AsignarActividad", method = RequestMethod.POST)
    public ModelAndView AsignarActividad(@ModelAttribute("ActividadP") ActividadP ap, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
       this.ActividadPValidar.validate(ap, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                            // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                
                
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("expedientePsicologico");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
         
                
                
                
                
             
        
        
         String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                 mv.addObject("diario",new diario());                                             //PASAMOS OBJETO PSICOLOGO
               
                                 
                    sql="select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio,correo  from paciente where no_boleta="+ap.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("ListaPacientes",datosL2);                                                       // Pasa la lilsta completa               
                                 mv.addObject("ActividadP",new ActividadP());     // SE AGREGA EL OBJETO ActividadP AL MODELO
                     
                    sql="select id_actividad,fecha,contenido from actividadp where no_boleta="+ap.getNo_boleta()+" order by fecha desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("ListaActividades",datosL2);  
                                 
                                 
                                 
                 sql="select * from entrada where id_usuario="+ap.getNo_boleta()+" order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("listaEntradas",datosL2);                                               // PASAMOS LA LISTA COMPLETA
                 
                 sql = "select id_entrada,fecha from comentarios where id_usuario="+ap.getNo_boleta()+" order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("FechaComentarios",datosL2);          
                   
                                 
                   sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("NombreEntrada",datosL2);                                               // PASAMOS LA LISTA COMPLETA            
                                                
                                 
                                 mv.addObject("entradaForo",new entradaForo());                                   //PASAMOS EL OBJETO ENTRADA EN EL FORO
                                  mv.addObject("Paciente",new Paciente()); 
                    return mv;                                                         
       
       
                                 
                                                                                                    //RETORNAMOS EL MODELO
         }                                                                               // CIERRE DE IF
         else{                                                                           // INICIO ELSE
             String sql="insert into actividadp values("+'0'+","+ap.getNo_boleta()+","+ap.getNo_cedula()+",'"+ap.getFecha()+"','"+ap.getContenido()+"','');";   // INSERTAMOS EN LA TABLA actividadp la actividad que nuestro psicologo le ha dejado al paciente
                               
       
                this.jdbcTemplate.update(sql);                                                                              // REALIZAMOS LA INSERCIÓN
            
       
       
        
            
            
            
            
                ModelAndView mv=new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("ConsultarActividadp");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        
        
          sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                
             sql="select * from actividadp where fecha='"+ap.getFecha()+"' and no_boleta="+ap.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("actividad",datosL2);                        
                                 mv.addObject("ActividadP",datosL2);
                            
                   
              sql="select nombre,ap_uno,ap_dos from paciente where no_boleta="+ap.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("nombreP",datosL2);              
                    return mv;                                                         
       
       
         }                                                                                                   // CIERRE DE ELSE
       
       
    }
             
          ////////////////////
     ////ACCIÓN DE BOTON guardarEntrada
     
      @RequestMapping(params="EditarActividad", method = RequestMethod.POST)
    public ModelAndView EditarActividad(@ModelAttribute("ActividadP") ActividadP ap, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
       this.ActividadPValidar.validate(ap, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                            // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
                    
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarActividadp");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
         
                
                
                
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO        
                
                 sql="select * from actividadp where fecha='"+ap.getFecha()+"' and no_boleta="+ap.getNo_boleta();             //OBTENEMOS LA ACTIVIDAD ESPECIFICA POR FECHA Y NO_BOLETA  
                               datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("actividad",datosL2);                                                       // Pasa la lilsta completa
                             mv.addObject("ActividadP",new ActividadP());                                            // SE AGREGA EL OBJETO ActividadP AL MODELO
                                 
                  sql="select nombre,ap_uno,ap_dos from paciente where no_boleta=(select no_boleta from actividadp where id_actividad="+ap.getId_actividad()+")";                        //Nombre de paciente  a quien se le da la actividad
                             datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                
                                 mv.addObject("nombreP",datosL2);                                                       // Pasa la lilsta completa
                             
                   return mv;
                                                                                                         //RETORNAMOS EL MODELO
         }                                                                               // CIERRE DE IF
         else{                                                                           // INICIO ELSE
             
             
             String sql="update actividadp set contenido='"+ap.getContenido()+"'where id_actividad="+ap.getId_actividad()+";"; // ACTUALIZAMOS EL CONTENIDOO  DONDE el ID DE ACTIVIDAD ES IGUAL A id_actividad
                               
       
                this.jdbcTemplate.update(sql);                       //REALIZAMOS LA ACTUALIZACIÓN
             
             
             
        
       
       
        
            
            
            
            
                ModelAndView mv=new ModelAndView();                     //CREACIÓN DEL MODELO
                mv.setViewName("ConsultarActividadp");                     //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ
         
                
                
                
                
                 sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO        
                
                 sql="select * from actividadp where id_actividad="+ap.getId_actividad();                       // OBTENEMOS LA ACTIVIDAD ESPECIFICA POR FECHA Y NO BOLETA
                               datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("actividad",datosL2);                                                       // Pasa la lilsta completa
                             mv.addObject("ActividadP",new ActividadP());                                            // SE AGREGA EL OBJETO ActividadP AL MODELO
                                 
                    sql="select nombre,ap_uno,ap_dos from paciente where no_boleta=(select no_boleta from actividadp where id_actividad="+ap.getId_actividad()+")";                        //Nombre de paciente  a quien se le da la actividad
                             datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                
                                 mv.addObject("nombreP",datosL2);                                                       // Pasa la lilsta completa
                             
                   return mv;
                                            
        
         }                                                                                                   // CIERRE DE ELSE
       
       
    }    
       
       
       ////////////////////
     ////ACCIÓN DE BOTON ConsultarDiario
     
      @RequestMapping(params="ConsultarDiario", method = RequestMethod.POST)
    public ModelAndView ConsultarDiario(@ModelAttribute("Paciente") Paciente p, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
                        ModelAndView mv=new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("diario_psicologo_solo_paciente");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        
        
         String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                 mv.addObject("diario",new diario());                                             //PASAMOS OBJETO Diario
                                  
                                 
                    sql="select no_boleta,nombre,ap_uno,ap_dos  from paciente where no_boleta="+p.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("nombreP",datosL2);                                                       // Pasa la lilsta completa               
                 
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190101' and '20190131' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasEnero",datosL2);                                                       // Pasa la lilsta completa               
                              
                       sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190201' and '20190228' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasFebrero",datosL2);                                                       // Pasa la lilsta completa               
                                         
                       
                        sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190301' and '20190331' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasMarzo",datosL2);                                                       // Pasa la lilsta completa               
                     
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190401' and '20190430' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasAbril",datosL2);                                                       // Pasa la lilsta completa               
                                                 
                                 
                    sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190501' and '20190531' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasMayo",datosL2);                                                       // Pasa la lilsta completa               
                              
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190601' and '20190630' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasJunio",datosL2);                                                       // Pasa la lilsta completa               
                                           
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190701' and '20190731' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasJulio",datosL2);                                                       // Pasa la lilsta completa               
                                          
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190801' and '20190831' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasAgosto",datosL2);                                                       // Pasa la lilsta completa               
                      
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20190901' and '20190530' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasSeptiembre",datosL2);                                                       // Pasa la lilsta completa               
                                         
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20191001' and '20191031' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasOctubre",datosL2);                                                       // Pasa la lilsta completa               
                  
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20191101' and '20191130' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasNoviembre",datosL2);                                                       // Pasa la lilsta completa               
                                           
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+p.getNo_boleta()+")) and fecha between '20191201' and '20191231' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasDiciembre",datosL2);                                                       // Pasa la lilsta completa               
                                          
                                 
                    return mv;                                                         
       
       
    }  
       
    
    
    ///////////////////////////////////
    //////////////Vista expedientePsicologico
        @RequestMapping(params="expediente", method = RequestMethod.POST)
    public ModelAndView ConsultarExpedientePsicologico(@ModelAttribute("Paciente") Paciente p, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
                        ModelAndView mv=new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("expedientePsicologico");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        
        
         String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                 mv.addObject("diario",new diario());                                             //PASAMOS OBJETO DIARIO
                               
               
                                 
                    sql="select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio,correo  from paciente where no_boleta="+p.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("ListaPacientes",datosL2);                                                       // Pasa la lilsta completa               
                                 mv.addObject("ActividadP",new ActividadP());     // SE AGREGA EL OBJETO ActividadP AL MODELO
                     
                    sql="select id_actividad,fecha,contenido from actividadp where no_boleta="+p.getNo_boleta()+" order by fecha desc";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("ListaActividades",datosL2);  
                                 
                                 
                                 
                 sql="select * from entrada where id_usuario="+p.getNo_boleta()+" order by id_entrada desc;";                     // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("listaEntradas",datosL2);                                               // PASAMOS LA LISTA COMPLETA
                 
                 sql = "select id_entrada,fecha from comentarios where id_usuario="+p.getNo_boleta()+" order by fecha desc";      // OBETENEMOS LA FECHA DE LOS COMENTARIOS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("FechaComentarios",datosL2);          
                   
                                 
                   sql = "select id_entrada,titulo from entrada";                                                      // OBETENEMOS EL NOMBRE DE TODAS LAS ENTRADAS REALIZADOS POR EL USUARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                       // ASIGNAMOS EL RESULTADO DE LA CONSULTA
                                 mv.addObject("NombreEntrada",datosL2);                                               // PASAMOS LA LISTA COMPLETA            
                                                
                                 
                                 mv.addObject("entradaForo",new entradaForo());                                   //PASAMOS EL OBJETO ENTRADA EN EL FORO
                                 
                    return mv;                                                         
       
       
    }  
       
        ////////////////////
     ////ACCIÓN DE BOTON guardarEntrada
     
      @RequestMapping(params="consultarActividadPs", method = RequestMethod.POST)
    public ModelAndView ConsultarActividadPs(@ModelAttribute("ActividadP") ActividadP ap, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
                                                                              // REALIZAMOS LA INSERCIÓN
            
       
       
        
            
            
            
            
                ModelAndView mv=new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("ConsultarActividadps");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        
        
         String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                
             sql="select * from actividadp where id_actividad="+ap.getId_actividad();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                 System.out.println(datosL2);               
                                 mv.addObject("actividad",datosL2);                        
                                 mv.addObject("ActividadP",datosL2);
                             
                   
              sql="select nombre,ap_uno,ap_dos from paciente where no_boleta="+ap.getNo_boleta();   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("nombreP",datosL2);              
                    return mv;                                                         
       
       
         }                                                                                                   // CIERRE DE ELSE
       
       
  


 ////////////////////
     ////ACCIÓN DE BOTON guardarObservación
     
      @RequestMapping(params="GuardarObservacion", method = RequestMethod.POST)
    public ModelAndView GuardarObservacion(@ModelAttribute("diario") diario d, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
           
        HttpSession session =hsr.getSession();                              //OBETENEMOS LA SESIÓN
       String alert = (String)session.getAttribute("Psico");             //EXTRAEMOS EL ATRIBUTO RELACIONADO A SESION DE PACIENTES
       
       if (alert == null){                                                  //VERIFICAMOS QUE EL ATRIBUTO NO ESTE NULO
           return new ModelAndView("redirect:/login.htm");                  // EN CASO DE QUE SEA NULO REDIRECCIONAMOS A LA VISTA DE LOGIN
       }     
       
       
       // EN CASO DE TENER UNA SESIÓN ACTIVA CONTINUAMOS
          
       this.diarioValidarObservacion.validate(d, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){                                                            // INICIO IF
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             
         
               ModelAndView mv=new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("diario_psicologo_solo_paciente");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        
        
         String sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                 mv.addObject("diario",new diario());                                             //PASAMOS OBJETO Diario
             
         sql="select no_boleta from expediente where id_expediente =(select id_expediente from diario where id_diario=(select id_diario from hojas where id_hojas="+d.getId_hojas()+"))";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
             String cadena=datosL2.get(0).toString();
             String subcadena=cadena.substring(11, cadena.length()-1);
                                 
                    sql="select nombre,ap_uno,ap_dos  from paciente where no_boleta="+subcadena;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("nombreP",datosL2);                                                       // Pasa la lilsta completa               
                 
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190101' and '20190131' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                            
                                 
                                 mv.addObject("hojasEnero",datosL2);                                                       // Pasa la lilsta completa               
                              
                       sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190201' and '20190228' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                
                                 
                                 mv.addObject("hojasFebrero",datosL2);                                                       // Pasa la lilsta completa               
                                         
                       
                        sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190301' and '20190331' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 
                                 
                                 mv.addObject("hojasMarzo",datosL2);                                                       // Pasa la lilsta completa               
                     
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190401' and '20190430' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 
                                 
                                 mv.addObject("hojasAbril",datosL2);                                                       // Pasa la lilsta completa               
                                                 
                                 
                    sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190501' and '20190531' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 
                                 
                                 mv.addObject("hojasMayo",datosL2);                                                       // Pasa la lilsta completa               
                              
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190601' and '20190630' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                
                                 
                                 mv.addObject("hojasJunio",datosL2);                                                       // Pasa la lilsta completa               
                                           
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190701' and '20190731' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                               
                                 mv.addObject("hojasJulio",datosL2);                                                       // Pasa la lilsta completa               
                                          
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190801' and '20190831' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                             
                                 
                                 mv.addObject("hojasAgosto",datosL2);                                                       // Pasa la lilsta completa               
                      
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190901' and '20190530' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                
                                 
                                 mv.addObject("hojasSeptiembre",datosL2);                                                       // Pasa la lilsta completa               
                                         
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20191001' and '20191031' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 
                                 
                                 mv.addObject("hojasOctubre",datosL2);                                                       // Pasa la lilsta completa               
                  
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20191101' and '20191130' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                
                                 
                                 mv.addObject("hojasNoviembre",datosL2);                                                       // Pasa la lilsta completa               
                                           
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20191201' and '20191231' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                
                                 
                                 mv.addObject("hojasDiciembre",datosL2);                                                       // Pasa la lilsta completa               
                                          
                                 
                    return mv; 
                                                                                                         //RETORNAMOS EL MODELO
         }                                                                               // CIERRE DE IF
         else{                                                                           // INICIO ELSE
             
          
             String sql="update hojas set observaciones='"+d.getObservaciones()+"' where id_hojas="+d.getId_hojas(); // ACTUALIZAMOS EL CONTENIDOO  DONDE el ID DE ACTIVIDAD ES IGUAL A id_actividad
                               
       
                this.jdbcTemplate.update(sql);                       //REALIZAMOS LA ACTUALIZACIÓN
             
             
             
        
       
       
          ModelAndView mv=new ModelAndView();                            //CREACIÓN DEL MODELO
        mv.setViewName("diario_psicologo_solo_paciente");               //NOMBRA AL MODELO, A ESTA VISTA SE ACCEDERÁ

        
        
          sql="select nombre,ap_uno,ap_dos, no_empleado,no_cedula from psicologo where no_empleado="+alert;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                List datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("datos",datosL2);                                                       // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());                                             //PASAMOS OBJETO PSICOLOGO   
                                 mv.addObject("diario",new diario());                                             //PASAMOS OBJETO Diario
           
         sql="select no_boleta from expediente where id_expediente =(select id_expediente from diario where id_diario=(select id_diario from hojas where id_hojas="+d.getId_hojas()+"))";   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
             String cadena=datosL2.get(0).toString();
         
             String subcadena=cadena.substring(11, cadena.length()-1);                        
                                
                    sql="select nombre,ap_uno,ap_dos  from paciente where no_boleta="+subcadena;   // CONSULTA PARA EXTRAER DATOS DE SESION
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 mv.addObject("nombreP",datosL2);                                                       // Pasa la lilsta completa               
                 
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190101' and '20190131' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasEnero",datosL2);                                                       // Pasa la lilsta completa               
                              
                       sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190201' and '20190228' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasFebrero",datosL2);                                                       // Pasa la lilsta completa               
                                         
                       
                        sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190301' and '20190331' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasMarzo",datosL2);                                                       // Pasa la lilsta completa               
                     
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190401' and '20190430' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasAbril",datosL2);                                                       // Pasa la lilsta completa               
                                                 
                                 
                    sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190501' and '20190531' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasMayo",datosL2);                                                       // Pasa la lilsta completa               
                              
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190601' and '20190630' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasJunio",datosL2);                                                       // Pasa la lilsta completa               
                                           
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190701' and '20190731' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasJulio",datosL2);                                                       // Pasa la lilsta completa               
                                          
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190801' and '20190831' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasAgosto",datosL2);                                                       // Pasa la lilsta completa               
                      
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20190901' and '20190530' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasSeptiembre",datosL2);                                                       // Pasa la lilsta completa               
                                         
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20191001' and '20191031' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasOctubre",datosL2);                                                       // Pasa la lilsta completa               
                  
                     sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20191101' and '20191130' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasNoviembre",datosL2);                                                       // Pasa la lilsta completa               
                                           
                      sql="select * from hojas where id_diario=(select id_diario from diario where id_expediente = (select id_expediente from expediente where no_boleta="+subcadena+")) and fecha between '20191201' and '20191231' order by fecha desc";   // CONSULTA PARA EXTRAER LAS HOJAS DEL DIARIO
                                 datosL2 = this.jdbcTemplate.queryForList(sql);                                  //ASIGNACIÓN DE RESULTADO DE CONSULTA
                                
                                 System.out.println(datosL2);
                                 
                                 mv.addObject("hojasDiciembre",datosL2);                                                       // Pasa la lilsta completa               
                                          
                                 
                    return mv; 
                                            
        
         }                                                                                                   // CIERRE DE ELSE
       
       
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
