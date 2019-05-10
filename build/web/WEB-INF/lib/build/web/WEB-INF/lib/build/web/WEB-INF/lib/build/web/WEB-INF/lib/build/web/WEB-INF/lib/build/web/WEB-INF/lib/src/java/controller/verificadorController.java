/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Conexion;
import models.LoginValidar;
import models.Nutriologo;
import models.NutriologoValidar;
import models.Paciente;
import models.PacienteValidar;
import models.Psicologo;
import models.PsicologoValidar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author jms-m
 */
@Controller
public class verificadorController {
    
    private JdbcTemplate jdbcTemplate;
    
     public verificadorController() {
        
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
    
    
    
    
    /////////////////////////////////////////////////////////////// 
  //Verificador General
    @RequestMapping(value="verificacion_cuentas.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA registroN y se aplicará el método GET
    
    public ModelAndView verificarController(){
       
        
                
        ModelAndView mav = new ModelAndView();              // CREACIÓN DEL MODELO
       
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
       
       return mav;
    }
  
     @RequestMapping(params="rechazarPs",method = RequestMethod.POST)
  
     public ModelAndView verificarRechazar(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){
        System.out.println("Detecte rechazar"); 
        
       
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
       
                return mav;
        }
     
       @RequestMapping(params="aprobarPs",method = RequestMethod.POST)
  
     public ModelAndView verificarAceptar(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){
        System.out.println("Detecte aprobar"); 
        
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
       
                return mav;
        }
    
  
     @RequestMapping(params="rechazarN",method = RequestMethod.POST)
  
     public ModelAndView verificarRechazarN(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, SessionStatus status){
        System.out.println("Detecte rechazar"); 
        
       
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
       
                return mav;
        }
     
       @RequestMapping(params="aprobarN",method = RequestMethod.POST)
  
     public ModelAndView verificarAceptarN(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, SessionStatus status){
        System.out.println("Detecte aprobar"); 
        
       System.out.println(n.getNo_cedula());
       String sql="update nutriologo set estatus=1 where no_cedula ="+n.getNo_cedula()+";";

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
       
                return mav;
        }
    
     
     
     
     
     
     
    
}
