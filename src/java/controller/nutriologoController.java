/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Administrador;
import models.Conexion;
import models.Login;
import models.Nutriologo;
import models.Paciente;
import models.Psicologo;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
   private List datos;
    
     public nutriologoController() {
       
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
        
    }
  
           /////////////////////////////////////////////////////////////// 
  //pantalla de lista de pacientes de nutriologo
    @RequestMapping(value="bienvenida_nutriologo.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_nutriologo y se aplicará el método GET
    
     public ModelAndView bienvenida_nutriologo(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma"); 
        System.out.println("AQUI ESTA EL CRONOGRAMA");
       
          HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
       
                System.out.println("NO_ EMPLEADO: "+alert);
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_nutriologo");
                
                
                String sql="select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio from paciente where no_cedula="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaP",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 System.out.println(datosL2);
                                 mv.addObject("LongitudP",datosL2.size()); // Pasa el tamaño de la lista
               
                sql="select nombre,ap_uno,ap_dos from nutriologo where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Nutriologo",new Nutriologo());
                return mv;
                
     }
    
    /////////////////////////////////////////////
     ///pantalla de vista mensajeriaN
      @RequestMapping(value="mensajeriaN.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeriaN y se aplicará el método GET
    
      public ModelAndView mensajeriaN (@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte mensajeria"); 
        System.out.println("AQUI ESTA LA MENSAJERIA");
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
                System.out.println("NO_ EMPLEADO: "+alert);
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaN");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
     }
     
     
      /////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foroN.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foroN y se aplicará el método GET
    
      public ModelAndView foroN (@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte Foro"); 
        System.out.println("AQUI ESTA EL FORO");
        
          HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
       
                System.out.println("NO_ EMPLEADO: "+alert);
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroN");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
     }
     
               /////////////////////////////////////////////////////////////// 
  //Cronograma 
    @RequestMapping(value="cronograma.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA cronograma y se aplicará el método GET
    
  public ModelAndView cronograma(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma"); 
        System.out.println("AQUI ESTA EL CRONOGRAMA");
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
        
       
                System.out.println("NO_ EMPLEADO: "+alert);
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 
                
                
                  ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("cronograma");                                            // Nombra al modelo
                                    mv.addObject("datos",datosL2);         // agrega al modelo el objeto datos
                                    mv.addObject("Nutriologo",new Nutriologo());
                                    return mv;
                
     }
    
     
     
     
     
      
    @RequestMapping(params="pacientes",method = RequestMethod.POST)
  
     public ModelAndView cambiarPacientes(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma"); 
        System.out.println("AQUI ESTA EL CRONOGRAMA");
       
          HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
       
                System.out.println("NO_ EMPLEADO: "+alert);
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_nutriologo");
                
                
                String sql="select no_boleta,nombre,ap_uno,ap_dos,edad,sexo,fecha_n,telefono,domicilio from paciente where no_cedula="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaP",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 System.out.println(datosL2);
                                 mv.addObject("LongitudP",datosL2.size()); // Pasa el tamaño de la lista
               
                sql="select nombre,ap_uno,ap_dos from nutriologo where no_empleado="+alert;
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Nutriologo",new Nutriologo());
                return mv;
                
     }
    
     
     @RequestMapping(params="mensajeria",method = RequestMethod.POST)
  
     public ModelAndView cambiarMensajeria(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte mensajeria"); 
        System.out.println("AQUI ESTA LA MENSAJERIA");
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
                System.out.println("NO_ EMPLEADO: "+alert);
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaN");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
     }
    
     
      @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte Foro"); 
        System.out.println("AQUI ESTA EL FORO");
        
          HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
       
                System.out.println("NO_ EMPLEADO: "+alert);
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroN");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
     }
    
     
     @RequestMapping(params="cronograma",method = RequestMethod.POST)
  
     public ModelAndView cambiarCronograma(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma"); 
        System.out.println("AQUI ESTA EL CRONOGRAMA");
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Nutri");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }
         
        
       
                System.out.println("NO_ EMPLEADO: "+alert);
                
                 String sql="select nombre,ap_uno,ap_dos, no_empleado from nutriologo where no_empleado="+alert;
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 
                
                
                  ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("cronograma");                                            // Nombra al modelo
                                    mv.addObject("datos",datosL2);         // agrega al modelo el objeto datos
                                    mv.addObject("Nutriologo",new Nutriologo());
                                    return mv;
                
     }
    
     
     
     @RequestMapping(params="cerrar", method = RequestMethod.POST)
    public ModelAndView logout(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
          HttpSession session =hsr.getSession(); 
          
          
          
             System.out.println("ADIOS NUTRIOLOGO");
            
            session.removeAttribute("Nutri");
            String alert2 = (String)session.getAttribute("Nutri");
            session.invalidate();
            
            System.out.println("ESTO OBTUVE: "+alert2);
            
            
            
            
            return new ModelAndView("redirect:/login.htm");
        
       
            
                
       
       
    }
   
      
     
     
}
