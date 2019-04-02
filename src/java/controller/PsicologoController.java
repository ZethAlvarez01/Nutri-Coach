/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import models.Conexion;
import models.Nutriologo;
import models.Paciente;
import models.Psicologo;
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
    
     public PsicologoController() {
        
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
     
           /////////////////////////////////////////////////////////////// 
  //pantalla de lista de pacientes de psicologo
    @RequestMapping(value="bienvenida_psicologo.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_psicologo y se aplicará el método GET
    
     public ModelAndView bienvenida_psicologoController(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_psicologo");
                mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
                return mv;       

                        
    }
     
     
               /////////////////////////////////////////////////////////////// 
  //Cronograma de psicologo
    @RequestMapping(value="cronogramaPsicologo.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_psicologo y se aplicará el método GET
    
     public ModelAndView cronograma_Psicologo(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("cronogramaPsicologo");
                mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
                return mv;       

                        
    }
     
     
     
     
      /////////////////////////////////////////////
     ///pantalla de vista mensajeriaPs
      @RequestMapping(value="mensajeriaPs.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeriaPs y se aplicará el método GET
    
     public ModelAndView mensajeriaN(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaPs");
                mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
                return mv;       

                        
    }
     
     
      /////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foroPs.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foroPs y se aplicará el método GET
    
     public ModelAndView foroN(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroPs");
                mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
                return mv;       

                        
    }
     
     
     
     
     
    
    @RequestMapping(params="cronograma",method = RequestMethod.POST)
  
     public ModelAndView cambiarCronograma(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte cronograma"); 
        
       
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("cronogramaPsicologo");
                
                String sql="select nombre,ap_uno,ap_dos from psicologo where no_empleado="+ps.getNo_empleado();
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                return mv;
                
     }
     
    
    @RequestMapping(params="mensajeria",method = RequestMethod.POST)
  
     public ModelAndView cambiarMensajes(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("mensajeria"); 
        
       
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaPs");
                
                String sql="select nombre,ap_uno,ap_dos from psicologo where no_empleado="+ps.getNo_empleado();
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                return mv;
                
     }
       @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){ // al hacer clik en el boton foro se cambiara a la vista de foroPs
        System.out.println("foro"); 
        
       
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroPs");
                
                String sql="select nombre,ap_uno,ap_dos from psicologo where no_empleado="+ps.getNo_empleado();
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                return mv;
                
     }
     
     @RequestMapping(params="pacientes",method = RequestMethod.POST)
       public ModelAndView cambiarPaciente(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){ // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_psicologo
        System.out.println("paciente"); 
        
                
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_psicologo");
                
                String sql="select nombre,ap_uno,ap_dos from psicologo where no_empleado="+ps.getNo_empleado();
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Psicologo",new Psicologo());
                
                
                return mv;
                
     }
    
     
}
