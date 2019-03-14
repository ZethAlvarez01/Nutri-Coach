package controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import models.Conexion;
import models.Login;
import models.LoginValidar;
import models.NeuralNet.Capa_neuronas;
import models.NeuralNet.Crear_RN;
import models.NeuralNet.Implementacion;
import models.NeuralNet.libMatrices;

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

/**
 *
 * @author Nutri-Coach
 */
@Controller
public class RegistroController {
    
    private PacienteValidar PacienteValidar;
    private NutriologoValidar nutriologoValidar;
    private PsicologoValidar PsicologoValidar;
    private LoginValidar loginValidar;
    private JdbcTemplate jdbcTemplate;
    private int[] topology={42,5,6};
    
    
    public RegistroController() {
        this.PacienteValidar=new PacienteValidar();               // Instancia de la clase PacienteValidar
        this.nutriologoValidar=new NutriologoValidar();           // Instancia de la clase nutriologoValidar
        this.PsicologoValidar=new PsicologoValidar();             // instancia de la clase PsicologoValidar
        this.loginValidar=new LoginValidar();
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
    
//////////////////////////////////////////    

    
    
 /////////////////////////////////////////////////////////////// 
  //REGISTRO DE NUTRIOLOGO
    @RequestMapping(value="registroN.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA registroN y se aplicará el método GET
    
    public ModelAndView registroN(){
       ModelAndView mav = new ModelAndView();              // CREACIÓN DEL MODELO
       
       mav.setViewName("registroN");                       // SE NOMBRA AL MODELO
       
       mav.addObject("Nutriologo",new Nutriologo());       // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
       
       return mav;
    }
  
   @RequestMapping(method = RequestMethod.POST)
     public ModelAndView registroN(@ModelAttribute("Nutriologo") Nutriologo n, BindingResult result, SessionStatus status){
         this.nutriologoValidar.validate(n, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         if(result.hasErrors()){
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             ModelAndView mav= new ModelAndView(); 
             mav.setViewName("registroN");
             mav.addObject("Nutriologo",new Nutriologo() );
             return mav;
         }
        else{
              //El usuario ingreso bien los datos
             if(n.getContraseña().equals(n.getContraseña2())){

                String sql="insert into nutriologo values("+n.getNo_cedula()+",'"+n.getNombre()+"','"+n.getAp_uno()+"','"+n.getAp_dos()+"','"+n.getTelefono()+"','"+n.getConsultorio()+"','"+n.getCorreo()+"','"+n.getNo_empleado()+"','"+n.getContraseña()+"','"+n.getInstitucion()+"');";

                this.jdbcTemplate.update(sql);
            
              
             ModelAndView mav= new ModelAndView();
             mav.setViewName("exito1"); //Pasamos a la vista de nombre exito
             mav.addObject("no_cedula",n.getNo_cedula()); //Se agrega el campo No_cedula al modelo
             System.out.println(n.getNo_cedula());
             mav.addObject("no_empleado",n.getNo_empleado()); //Se agrega el campo No_empleado al modelo
             System.out.println(n.getNo_empleado());
             mav.addObject("nombre",n.getNombre()); //Se agrega el campo Nombre al modelo
             System.out.println(n.getNombre());
             mav.addObject("ap_uno",n.getAp_uno()); //Se agrega el campo Ap_uno al modelo
             System.out.println(n.getAp_uno());
             mav.addObject("ap_dos",n.getAp_dos()); //Se agrega el campo Ap_dos al modelo
             System.out.println(n.getAp_dos());
             mav.addObject("institucion",n.getInstitucion()); //Se agrega el campo InstituciÃ³n al modelo
             System.out.println(n.getInstitucion());
             mav.addObject("consultorio",n.getConsultorio()); //Se agrega el campo Consultorio al modelo
             System.out.println(n.getConsultorio());
             mav.addObject("telefono",n.getTelefono()); //Se agrega el campo Telefono al modelo
             System.out.println(n.getTelefono());
             mav.addObject("correo",n.getCorreo()); //Se agrega el campo Correo al modelo
             System.out.println(n.getCorreo());
             mav.addObject("contraseña",n.getContraseña()); //Se agrega el campo Contraseña al modelo
             System.out.println(n.getContraseña());
             mav.addObject("contraseña2",n.getContraseña2()); //Se agrega el campo Contraseña2 al modelo
             System.out.println(n.getContraseña2());

             return mav;
             }else{
                ModelAndView mav=new ModelAndView();
                mav.setViewName("registroN");
                mav.addObject("Nutriologo",new Nutriologo());
                return mav;
             }
        }
    }
   ///////////////////////////////////////////////////////////////////////// 
  //REGISTRO DE PACIENTE
   @RequestMapping(value="registroP.htm", method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA registroP y se aplicará el método GET
    
    public ModelAndView registroP(){
       ModelAndView mav = new ModelAndView();         // CREACIÓN DEL MODELO
       
       mav.setViewName("registroP");                  // SE NOMBRA AL MODELO
       
       mav.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO
       
       return mav;
    } 
    
    
      /////Recibimos y validamos los datos de nuestro formulario
     @RequestMapping(value="registroP.htm",method = RequestMethod.POST)
     public ModelAndView registroP(@ModelAttribute("Paciente") Paciente p, BindingResult result, SessionStatus status){
         this.PacienteValidar.validate(p, result);
         // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES
         if(result.hasErrors()){
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             ModelAndView mav= new ModelAndView();     
             mav.setViewName("registroP");
             mav.addObject("Paciente",new Paciente() );
             return mav;
         }
         else{
            //El usuario ingreso bien los datos
            if(p.getContraseña().equals(p.getContraseña2())){
                
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNac = LocalDate.parse(p.getFecha_n(), fmt);
                LocalDate ahora = LocalDate.now();

                Period periodo = Period.between(fechaNac, ahora);

                String sql="insert into paciente values("+p.getNo_boleta()+",0,'"+p.getNombre()+"','"+p.getAp_uno()+"','"+p.getAp_dos()+"',"+periodo.getYears()+",'"+p.getSexo()+"','"+p.getFecha_n()+"','"+p.getTelefono()+"','"+p.getDomicilio()+"','"+p.getCorreo()+"','"+p.getContraseña()+"');";

                this.jdbcTemplate.update(sql);

                ModelAndView mav=new ModelAndView();
            
                mav.setViewName("exito2"); //Pasamos a la vista de nombre exito3
                mav.addObject("no_boleta",p.getNo_boleta()); //Se agrega el campo No_boleta al modelo
                System.out.println(p.getNo_boleta()+" Este es el número de boleta");
                mav.addObject("nombre",p.getNombre()); //Se agrega el campo Nombre al modelo
                System.out.println(p.getNombre()+" Este es el nombre");
                mav.addObject("ap_uno",p.getAp_uno()); //Se agrega el campo Ap_uno al modelo
                System.out.println(p.getAp_uno()+ " Este es el primer apellido");
                mav.addObject("ap_dos",p.getAp_dos()); //Se agrega el campo Ap_dos al modelo
                System.out.println(p.getAp_dos() + " Este es el segundo paterno");
                mav.addObject("sexo",p.getSexo()); //Se agrega el campo Sexo al modelo
                System.out.println(p.getSexo() + " Este es el sexo" );
                mav.addObject("fecha_n",p.getFecha_n()); //Se agrega el campo Fecha_n al modelo
                System.out.println(p.getFecha_n()+ " Esta es la fecha");
                mav.addObject("telefono",p.getTelefono()); //Se agrega el campo Telefono al modelo
                System.out.println(p.getTelefono()+ " Este es el telefono");
                mav.addObject("domicilio",p.getDomicilio()); //Se agrega el campo Domicilio al modelo
                System.out.println(p.getDomicilio()+ "Este es el domicilio");
                mav.addObject("correo",p.getCorreo()); //Se agrega el campo Correo al modelo
                System.out.println(p.getCorreo()+ " Este es el correo");
                mav.addObject("contraseña",p.getContraseña()); //Se agrega el campo Contraseña al modelo
                System.out.println(p.getContraseña()+" ESTA ES LA CONTRASEÑA1");
                mav.addObject("contraseña2",p.getContraseña2()); //Se agrega el campo Contraseña2 al modelo
                System.out.println(p.getContraseña2()+" ESTA ES LA CONTRASEÑA2");

                return mav;
            }else{
                ModelAndView mav=new ModelAndView();
                mav.setViewName("registroP");
                mav.addObject("Paciente",new Paciente());
                return mav;  
            }
            
        }
    }
  
 ///////////////////////////////////////////////////////////////////// 
  //REGISTRO DE PSICOLOGO
   @RequestMapping(value="registroPs.htm", method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA registroPs y se aplicará el método GET
    
    public ModelAndView registroPs(){
       ModelAndView mav = new ModelAndView();           // CREACIÓN DEL MODELO
       
       mav.setViewName("registroPs");                   // SE NOMBRA AL MODELO
       
       mav.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
       
       return mav;
    } 
    
  
          //Recibimos y validamos los datos de nuestro formulario
     @RequestMapping(value="registroPs.htm",method = RequestMethod.POST)
     public ModelAndView registroPS(@ModelAttribute("Psicologo") Psicologo ps, BindingResult result, SessionStatus status){
   
         this.PsicologoValidar.validate(ps, result);
   
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
         
        
         if(result.hasErrors()){
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             ModelAndView mav= new ModelAndView(); 
             mav.setViewName("registroPs");
             mav.addObject("Psicologo",new Psicologo() );
             return mav;
         }
        else{
                 //El usuario ingreso bien los datos
            if(ps.getContraseña().equals(ps.getContraseña2())){

                String sql="insert into psicologo values("+ps.getNo_cedula()+",'"+ps.getNombre()+"','"+ps.getAp_uno()+"','"+ps.getAp_dos()+"','"+ps.getTelefono()+"','"+ps.getCorreo()+"',"+ps.getNo_empleado()+",'"+ps.getContraseña()+"');";

                this.jdbcTemplate.update(sql);
            
                ModelAndView mav=new ModelAndView();
                
                mav.setViewName("exito3"); //Pasamos a la vista de nombre exito
                mav.addObject("no_cedula",ps.getNo_cedula()); //Se agrega el campo No_cedula al modelo
                System.out.println(ps.getNo_cedula());
                mav.addObject("no_empleado",ps.getNo_empleado()); //Se agrega el campo No_empleado al modelo
                System.out.println(ps.getNo_empleado());
                mav.addObject("nombre",ps.getNombre()); //Se agrega el campo Nombre al modelo
                System.out.println(ps.getNombre());
             mav.addObject("ap_uno",ps.getAp_uno()); //Se agrega el campo Ap_uno al modelo
             System.out.println(ps.getAp_uno());
             mav.addObject("ap_dos",ps.getAp_dos()); //Se agrega el campo Ap_dos al modelo
             System.out.println(ps.getAp_dos());
             mav.addObject("telefono",ps.getTelefono()); //Se agrega el campo Telefono al modelo
             System.out.println(ps.getTelefono());
             mav.addObject("correo",ps.getCorreo()); //Se agrega el campo Correo al modelo
             System.out.println(ps.getCorreo());
             mav.addObject("contraseña",ps.getContraseña()); //Se agrega el campo Contraseña al modelo
             System.out.println(ps.getContraseña());
             mav.addObject("contraseña2",ps.getContraseña2()); //Se agrega el campo Contraseña2 al modelo
             System.out.println(ps.getContraseña2());

                return mav;
            }else{
                ModelAndView mav=new ModelAndView();
                mav.setViewName("registroPs");
                mav.addObject("Psicologo",new Psicologo());
                return mav;
            }
        }
    
   
   
     }
     
}
