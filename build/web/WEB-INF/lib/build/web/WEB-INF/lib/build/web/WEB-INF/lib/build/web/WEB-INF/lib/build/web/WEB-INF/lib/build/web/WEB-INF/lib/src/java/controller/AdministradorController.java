/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import models.Administrador;
import models.Conexion;
import models.Mensaje;
import models.Nutriologo;
import models.Paciente;
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
public class AdministradorController {
   private JdbcTemplate jdbcTemplate;
   private List datos;
    
    public AdministradorController() {
       
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
        
    }
    
       /////////////////////////////////////////////////////////////// 
  //Verificador General
    @RequestMapping(value="bienvenida_admin.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA bienvenida_admin y se aplicará el método GET
    
    public ModelAndView listadoController(){
       
        
                
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
       
             
                         return mv;
                        
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
  
    
    
    /////////////////////////////////////////////
     ///pantalla de vista mensajeriaN
      @RequestMapping(value="mensajeriaAdmin.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeriaAdmin y se aplicará el método GET
    
     public ModelAndView mensajeriaN(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaAdmin");
                mv.addObject("Administrador",new Administrador());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                return mv;       

                        
    }
    
    
       /////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foroAdmin.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foroN y se aplicará el método GET
    
     public ModelAndView foroAdmin(){
       
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroAdmin");
                mv.addObject("Administrador",new Administrador());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                return mv;       

                        
    }
     
     
    
    
    
    @RequestMapping(params="solicitudes",method = RequestMethod.POST)
  
     public ModelAndView cambiarSolicitudes(@ModelAttribute("Administrador") Administrador n, BindingResult result, SessionStatus status){ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
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
             
             sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+n.getNo_empleado();
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mav.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mav.addObject("Administrador",new Administrador());
                         
       
       return mav;
    }
     
     
     
     @RequestMapping(params="bienvenida",method = RequestMethod.POST)
  
     public ModelAndView cambiarBienvenida(@ModelAttribute("Administrador") Administrador n, BindingResult result, SessionStatus status){ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
   
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
       
             sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+n.getNo_empleado();
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Administrador",new Administrador());
                         return mv;
                        
    }
     
     
     
     @RequestMapping(params="mensajes",method = RequestMethod.POST)
  
     public ModelAndView cambiarMensajeria(@ModelAttribute("Administrador") Administrador n, BindingResult result, SessionStatus status){ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaAdmin
        System.out.println("detecte mensajeria"); 
        System.out.println("AQUI ESTA LA MENSAJERIA");
       
                System.out.println("NO_ EMPLEADO: "+n.getNo_empleado());
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeriaAdmin");
                mv.addObject("Administrador",new Administrador());     // SE AGREGA EL OBJETO ADMINISTRADOR AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from administrador where no_empleado="+n.getNo_empleado();
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
     } 
     
     //VISTA FORO
     @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Administrador") Administrador n, BindingResult result, SessionStatus status){ // al hacer clik en el boton cronograma se cambiara a la vista de cronogramaPsicologo
        System.out.println("detecte Foro"); 
        System.out.println("AQUI ESTA EL FORO");
       
                System.out.println("NO_ EMPLEADO: "+n.getNo_empleado());
                
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foroAdmin");
                mv.addObject("Administrador",new Administrador());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                
                String sql="select nombre,ap_uno,ap_dos, no_empleado from Administrador where no_empleado="+n.getNo_empleado();
                                 List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("ListaAdmin",datosL2);          // Pasa la lilsta completa
                
                
                
                return mv; 
                
              
                
          
                
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
