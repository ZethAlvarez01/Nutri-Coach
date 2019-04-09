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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jms-m
 */

@Controller
public class ListadoUsuariosController {
     private JdbcTemplate jdbcTemplate;
    
     public ListadoUsuariosController() {
        
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
             
             
       
                         return mv;
                        
    }
  
}
