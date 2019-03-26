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
  //Verificador General
    @RequestMapping(value="bienvenida_psicologo.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA cronogramaPsicologo y se aplicará el método GET
    
     public ModelAndView bienvenida_psicologoController(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("bienvenida_psicologo");
                mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
                return mv;       

                        
    }
    
    @RequestMapping(params="cronograma",method = RequestMethod.POST)
  
     public ModelAndView cambiarCronograma(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){
        System.out.println("cronograma"); 
        
       
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("cronogramaPsicologo");
                return mv;
                
     }
    
    
    
    
     
}
