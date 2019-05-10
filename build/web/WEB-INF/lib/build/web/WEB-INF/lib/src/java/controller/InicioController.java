package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import models.Administrador;
import models.Conexion;
import models.ConsultaEvolucion;
import models.Foro;
import models.Nutriologo;

import models.Login;
import models.LoginValidar;
import models.Mensaje;

import models.NeuralNet.Implementacion;
import models.NeuralNet.libMatrices;
import models.NeuralNet.Capa_neuronas;
import models.NeuralNet.Crear_RN;
import models.NeuralNet.Tratamiento;
import models.Paciente;
import models.Psicologo;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Nutri-Coach
 */
@Controller

public class InicioController {
    
    private int[] topology={42,5,6};
    
    
    private JdbcTemplate jdbcTemplate;
    private LoginValidar LoginValidar;

    public InicioController() {
        this.LoginValidar=new LoginValidar();                       // INSTANCIA DE LA CLASE LoginValidar
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
    
    
    
   ////////////////////////////////////////////////////
    
    //PAGINA DE INICIO
    
    @RequestMapping(value="inicio.htm",method=RequestMethod.GET)     // SE UTILIZARÁ LA VISTA inicio y se aplicará el método GET
    
    public ModelAndView inicio(){                
        ModelAndView mv=new ModelAndView();                  // SE CREA EL MODELO
        mv.setViewName("inicio");                            // SE NOMBRA EL MODELO
        mv.addObject("Login",new Login());                   // SE AGREGA EL OBJETO Login AL MODELO
        return mv;

       
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView inicio(
                        @ModelAttribute("Login") @Validated Login lo,
                        BindingResult resultado,
                        HttpServletRequest hsr, HttpServletResponse hsrl
                        )throws Exception{
        this.LoginValidar.validate(lo, resultado);
       
      // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES   
        
        if(resultado.hasErrors()){
            //volvemos al formulario porque los datos ingresados son incorrectos
            
            ModelAndView mv=new ModelAndView();
           // mv.setViewName("inicio");
            mv.addObject("Login",new Login());
            return mv;
        }else{
               //El usuario ingreso bien los datos
                String sql="select nombre, ap_uno, ap_dos, edad, sexo, fecha_n, telefono, domicilio, correo, no_boleta from paciente where contraseña='"+ // Busca el usuario como paciente en la base de datos
                lo.getPass()+"' and no_boleta="+lo.getUsuario()+";";
                //List datos=this.jdbcTemplate.queryForList(sql);
                List datosL=this.jdbcTemplate.queryForList(sql);
//                System.out.println(datosL.get(0));
                String sql2="select estatus from paciente where contraseña='"+lo.getPass()+"' and no_boleta="+lo.getUsuario()+";"; // Extraemos su estatus
                        List estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                if(datosL.size()>=1){
                                                          // Si se encuentra el paciente se procede a verificar su estatus
                                                          
                    switch (estatus.get(0).toString().charAt(9)) {                     // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                           // Si se encuentra en 2 su cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                            // El estatus 1 significa usuario activo y se procede a ingresar a su bienvenida
                                {
                                   
                    
                    
                        HttpSession session = hsr.getSession();
                       
                        System.out.println("ESTO OBTUVE DE LOGIN PACIENTE: "+lo.getUsuario());
                        session.setAttribute("Paciente",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/expedientePaciente.htm");
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }                                      
                    
            }
                else{
                        sql="select no_cedula, nombre, ap_uno, ap_dos, telefono, correo, no_empleado, estatus from psicologo where contraseña='"+          // Procede a buscar al usuario en la base como psicologo
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from psicologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                        
                        
                        if(datosL.size()>=1){                                      // Si se encuentra el usuario se procede a verificar su estatus
                            switch (estatus.get(0).toString().charAt(9)) {         // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                            
                                case '2':                                         // si su estatus es 2 la cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                               // el estatus 1 refiere a usuario activo y se ingresa a su bienvenida
                                {
                                   HttpSession session = hsr.getSession();
                       
                        System.out.println("ESTO OBTUVE DE LOGIN PSICOLOGO: "+lo.getUsuario());
                        session.setAttribute("Psico",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/cronogramaPsicologo.htm");
                                    
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                        
                   }
                        
                        else{
                            
                        sql="select no_cedula, nombre, ap_uno, ap_dos, telefono, consultorio, correo, no_empleado, institucion, estatus from nutriologo where contraseña='"+                // Se procede a buscar al usuario como nutriologo en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from nutriologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                       // System.out.println(estatus.get(0).toString().charAt(9));
                            
                        if(datosL.size()>=1){                                               // Si se encuentra el  usuario se procede a verificar su estatus
                            // Se verifica el estatus del usuario
                            switch (estatus.get(0).toString().charAt(9)) {            // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                                  // Si su estatus es 2 se tiene una cuenta suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                                 // Si su estatus es 1 signifa cuenta activa y se procede a acceder a su bienbenida
                                {
                                   HttpSession session = hsr.getSession();
                       
                        System.out.println("ESTO OBTUVE DE LOGIN NUTRI: "+lo.getUsuario());
                        session.setAttribute("Nutri",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/cronograma.htm");
                                    
                                    
                                 
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                            
                        
                        
                        
                        }
                        else{ 
                            
                        sql="select no_empleado, nombre, ap_uno, ap_dos, telefono from administrador where contraseña='"+                // Se procede a buscar al usuario como administrador en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  

                        if(datosL.size()>=1){                                               // Si se encuentra el  usuario se procede a acceder a su vista de bienvenida
                        
                        HttpSession session = hsr.getSession();
                        String text = hsr.getParameter("usuario");
                        System.out.println("ESTO OBTUVE DE LOGIN ADMIN: "+lo.getUsuario());
                        session.setAttribute("Admin",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/bienvenida_admin.htm");
                        
                        
                        }
                        else{                                                           // Si no se encuentra el usuario en la base de datos se procede a regresar a la pagina de inicio
                         ModelAndView mv=new ModelAndView();                            // Creación del modelo
                        mv.setViewName("inicio");                                       // Nombra al modelo
                        mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                        return mv;    
                        }

                                 
                             
                        }
                            
                       
                        }
                }
        }
    }
    
    
    @RequestMapping(value="login.htm",method=RequestMethod.GET)         // SE UTILIZARÁ LA VISTA login y se aplicará el método GET
    public ModelAndView formularioLogin(){
        ModelAndView mv=new ModelAndView();                            // SE CREA EL MODELO
        mv.setViewName("login");                                       // SE NOMBRA EL MODELO
        mv.addObject("Login",new Login());                             // SE AGREGA EL OBJETO login AL MODELO
        return mv;
    }
  
    @RequestMapping(value="login.htm",method=RequestMethod.POST)
    public ModelAndView formularioLogin(
                        @ModelAttribute("Login") Login lo,
                        BindingResult resultado,
                        HttpServletRequest hsr, HttpServletResponse hsrl
                        )throws Exception
                        {
        this.LoginValidar.validate(lo, resultado);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES
        if(resultado.hasErrors()){
            
            //volvemos al formulario porque los datos ingresados son incorrectos
            ModelAndView mv=new ModelAndView();
            mv.setViewName("login");
            mv.addObject("Login",new Login());
            return mv;
        }else{
             //El usuario ingreso bien los datos
               String sql="select nombre from paciente where contraseña='"+ // Busca el usuario como paciente en la base de datos
                lo.getPass()+"' and no_boleta="+lo.getUsuario()+";";
                //List datos=this.jdbcTemplate.queryForList(sql);
                List datosL=this.jdbcTemplate.queryForList(sql);
//                System.out.println(datosL.get(0));
                String sql2="select estatus from paciente where contraseña='"+lo.getPass()+"' and no_boleta="+lo.getUsuario()+";"; // Extraemos su estatus
                        List estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                
                if(datosL.size()>=1){                                                     // Si se encuentra en la base se procede a acceder a su vista de bienvenida
                    
                                                          
                    switch (estatus.get(0).toString().charAt(9)) {                     // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                           // Si se encuentra en 2 su cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                            // El estatus 1 significa usuario activo y se procede a ingresar a su bienvenida
                                {
                                  
                    
                    HttpSession session = hsr.getSession();
                       
                        System.out.println("ESTO OBTUVE DE LOGIN PACIENTE: "+lo.getUsuario());
                        session.setAttribute("Paciente",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/expedientePaciente.htm");
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }                                      
                    
                    
                    
                    
                    
                    
                    
                    
            }else{
               
                sql="select no_cedula, nombre, ap_uno, ap_dos, telefono, correo, no_empleado, estatus from psicologo where contraseña='"+          // Procede a buscar al usuario en la base como psicologo
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from psicologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                    
                    if(datosL.size()>=1){                                                 // Si se encuentra en la base se procede a acceder a su vista de Bienvenida
                   
                        switch (estatus.get(0).toString().charAt(9)) {         // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                            
                                case '2':                                         // si su estatus es 2 la cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                               // el estatus 1 refiere a usuario activo y se ingresa a su bienvenida
                                {
                                   HttpSession session = hsr.getSession();
                       
                        System.out.println("ESTO OBTUVE DE LOGIN PSICOLOGO: "+lo.getUsuario());
                        session.setAttribute("Psico",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/cronogramaPsicologo.htm");
                                    
                                    
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                        
                        
                        
                        
                        
            }
                    else{
                  sql="select no_cedula, nombre, ap_uno, ap_dos, telefono, consultorio, correo, no_empleado, institucion, estatus from nutriologo where contraseña='"+                // Se procede a buscar al usuario como nutriologo en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from nutriologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                      
                
                if(datosL.size()>=1){                                                         // Si se enceuntra se procede a mostrar su vista de Bienvenida
                    
                    
                    // Se verifica el estatus del usuario
                            switch (estatus.get(0).toString().charAt(9)) {            // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                                  // Si su estatus es 2 se tiene una cuenta suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                                 // Si su estatus es 1 signifa cuenta activa y se procede a acceder a su bienbenida
                                {
                                    HttpSession session = hsr.getSession();
                       
                        System.out.println("ESTO OBTUVE DE LOGIN NUTRI: "+lo.getUsuario());
                        session.setAttribute("Nutri",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/cronograma.htm");
                                    
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                    
                    
                    
                    
                    
                    
            }
                else{   
                   sql="select no_empleado, nombre, ap_uno, ap_dos, telefono from administrador where contraseña='"+                // Se procede a buscar al usuario como administrador en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  

                        if(datosL.size()>=1){                                               // Si se encuentra el  usuario se procede a acceder a su vista de bienvenida
                  
             HttpSession session = hsr.getSession();
                        String text = hsr.getParameter("usuario");
                        System.out.println("ESTO OBTUVE DE LOGIN ADMIN: "+lo.getUsuario());
                        session.setAttribute("Admin",lo.getUsuario());
                            
                            
                                         
                         
                        
        
                         return new ModelAndView("redirect:/bienvenida_admin.htm");
       
                       
            }
                else{                                                                    // En caso de no encontrar al usuario en la base ded datos se procede a regresar a la pagina de inicio
                    ModelAndView mv=new ModelAndView();                                     // Creación del modelo
                mv.setViewName("login");                                                     // Se nombra el modelo
                mv.addObject("Login",new Login());                                           // Se agrega el objeto Login al modelo
                return mv;
                }


                       
                     
                }
                
                
                    }
                    
                    
                    
                    
                
            }
        }
    }
    
    
  
    
    

    
    
}
               