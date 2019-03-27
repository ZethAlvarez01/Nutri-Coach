/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import models.Conexion;
import models.Nutriologo;
import models.Psicologo;
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
public class nutriologoController {
   private JdbcTemplate jdbcTemplate;
    
     public nutriologoController() {
        
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
     
           /////////////////////////////////////////////////////////////// 
  //pantalla de lista de pacientes de nutriologo
    @RequestMapping(value="bienvenida_nutriologo.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_nutriologo y se aplicará el método GET
    
     public ModelAndView bienvenida_nutriologoController(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_nutriologo");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                return mv;       

                        
    }
    /////////////////////////////////////////////
     ///pantalla de vista mensajeriaN
      @RequestMapping(value="mensajeriaN.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeriaN y se aplicará el método GET
    
     public ModelAndView mensajeriaN(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaN");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                return mv;       

                        
    }
     
     
      /////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foroN.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foroN y se aplicará el método GET
    
     public ModelAndView foroN(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroN");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                return mv;       

                        
    }
     
               /////////////////////////////////////////////////////////////// 
  //Cronograma 
    @RequestMapping(value="cronograma.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_nutriologo y se aplicará el método GET
    
     public ModelAndView cronograma(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("cronograma");
                mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                return mv;       

                        
    }
     
     
     
     
    
    @RequestMapping(params="cronograma",method = RequestMethod.POST)
  
     public ModelAndView cambiarCronograma(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, SessionStatus status){ // al hacer clik en el boton cronograma se cambiara a la vista de cronograma
        System.out.println("cronograma"); 
        
       
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("cronograma");
                return mv;
                
     }
    
    @RequestMapping(params="mensajes",method = RequestMethod.POST)
  
     public ModelAndView cambiarMensajes(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, SessionStatus status){ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaN
        System.out.println("mensajeria"); 
        
       
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaN");
                return mv;
                
     }
       @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, SessionStatus status){ // al hacer clik en el boton foro se cambiara a la vista de foroN
        System.out.println("foro"); 
        
       
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroN");
                return mv;
                
     }
       public ModelAndView cambiarPaciente(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, SessionStatus status){ // al hacer clik en el boton paciente se cambiara a la vista de bienvenida_nutriologo
        System.out.println("paciente"); 
        
                
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_nutriologo");
                return mv;
                
     }
    
     
}

