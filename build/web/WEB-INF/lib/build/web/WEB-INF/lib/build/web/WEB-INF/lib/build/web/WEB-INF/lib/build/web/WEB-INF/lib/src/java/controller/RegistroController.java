package controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import models.Conexion;
import models.Login;
import models.LoginValidar;
import models.Nutriologo;
import models.NutriologoValidar;
import models.Paciente;
import models.PacienteValidar;
import models.Psicologo;
import models.PsicologoValidar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zeth
 */
@Controller
public class RegistroController {
    
    private PacienteValidar pacienteValidar;
    private NutriologoValidar nutriologoValidar;
    private PsicologoValidar psicologoValidar;
    private JdbcTemplate jdbcTemplate;
    
    
    public RegistroController() {
        this.pacienteValidar=new PacienteValidar();
        this.nutriologoValidar=new NutriologoValidar();
        this.psicologoValidar=new PsicologoValidar();
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
    
 
/////////////////////
    
    
    @RequestMapping(value="registroN.htm",method=RequestMethod.GET)
    public ModelAndView formularioN(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("registroN");
        mv.addObject("registroN",new Nutriologo());
        return mv;
    }
  
    @RequestMapping(value="registroN.htm",method=RequestMethod.POST)
    public ModelAndView formularioN(
                        @ModelAttribute("Nutriologo") Nutriologo nutriologo,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.nutriologoValidar.validate(nutriologo, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("registroN");
            mv.addObject("registroN",new Nutriologo());
            return mv;
        }else{
            
             if(nutriologo.getContraseña().equals(nutriologo.getContraseña2())){

                String sql="insert into nutriologo values("+nutriologo.getNo_cedula()+",'"+nutriologo.getNombre()+"','"+nutriologo.getAp_uno()+"','"+nutriologo.getAp_dos()+"','"+nutriologo.getTelefono()+"','"+nutriologo.getConsultorio()+"','"+nutriologo.getCorreo()+"',"+nutriologo.getNo_empleado()+",'"+nutriologo.getContraseña()+"','"+nutriologo.getInstitucion()+"');";

                this.jdbcTemplate.update(sql);
            
                ModelAndView mv=new ModelAndView();
                mv.setViewName("exito1");
                mv.addObject("no_cedula",nutriologo.getNo_cedula());
                mv.addObject("no_empleado",nutriologo.getNo_empleado());
                mv.addObject("nombre",nutriologo.getNombre());
                mv.addObject("ap_uno",nutriologo.getAp_uno());
                mv.addObject("ap_dos",nutriologo.getAp_dos());
                mv.addObject("institucion",nutriologo.getInstitucion());
                mv.addObject("telefono",nutriologo.getConsultorio());
                mv.addObject("consultorio",nutriologo.getConsultorio());
                mv.addObject("correo",nutriologo.getCorreo());
                mv.addObject("contraseña",nutriologo.getContraseña());
                mv.addObject("contraseña2",nutriologo.getContraseña2());

                return mv;
             }else{
                ModelAndView mv=new ModelAndView();
                mv.setViewName("registroN");
                mv.addObject("registroN",new Nutriologo());
                return mv;
             }
        }
    }
    ////////////////////////////////////////////////////////////////////
    @RequestMapping(value="registroP.htm",method=RequestMethod.GET)
    public ModelAndView formularioP(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("registroP");
        mv.addObject("registroP",new Paciente());
        return mv;
    }
    
    @RequestMapping(value="registroP.htm",method=RequestMethod.POST)
    public ModelAndView formularioP(
                        @ModelAttribute("Paciente") Paciente paciente,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.pacienteValidar.validate(paciente, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("registroP");
            mv.addObject("registroP",new Paciente());
            return mv;
        }else{
            
            if(paciente.getContraseña().equals(paciente.getContraseña2())){
                
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNac = LocalDate.parse(paciente.getFecha_n(), fmt);
                LocalDate ahora = LocalDate.now();

                Period periodo = Period.between(fechaNac, ahora);

                String sql="insert into paciente values("+paciente.getNo_boleta()+",0,'"+paciente.getNombre()+"','"+paciente.getAp_uno()+"','"+paciente.getAp_dos()+"',"+periodo.getYears()+",'"+paciente.getSexo()+"','"+paciente.getFecha_n()+"','"+paciente.getTelefono()+"','"+paciente.getDomicilio()+"','"+paciente.getCorreo()+"','"+paciente.getContraseña()+"');";

                this.jdbcTemplate.update(sql);

                ModelAndView mv=new ModelAndView();
                mv.setViewName("exito2");
                mv.addObject("no_boleta",paciente.getNo_boleta());
                mv.addObject("nombre",paciente.getNombre());
                mv.addObject("ap_uno",paciente.getAp_uno());
                mv.addObject("ap_dos",paciente.getAp_dos());
                mv.addObject("sexo",paciente.getSexo());
                mv.addObject("fecha_n",paciente.getFecha_n());
                mv.addObject("telefono",paciente.getTelefono());
                mv.addObject("domicilio",paciente.getDomicilio());
                mv.addObject("correo",paciente.getCorreo());
                mv.addObject("contraseña",paciente.getContraseña());
                mv.addObject("contraseña2",paciente.getContraseña2());

                return mv;
            }else{
                ModelAndView mv=new ModelAndView();
                mv.setViewName("registroP");
                mv.addObject("registroP",new Paciente());
                return mv;  
            }
            
        }
    }
  
    //////////////////////////////////////////////////////////////////////
    @RequestMapping(value="registroPs.htm",method=RequestMethod.GET)
    public ModelAndView formularioPs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("registroPs");
        mv.addObject("registroPs",new Psicologo());
        return mv;
    }
  
    @RequestMapping(value="registroPs.htm",method=RequestMethod.POST)
    public ModelAndView formularioPs(
                        @ModelAttribute("Psicologo") Psicologo psicologo,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.psicologoValidar.validate(psicologo, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("registroPs");
            mv.addObject("registroPs",new Psicologo());
            return mv;
        }else{
            
            if(psicologo.getContraseña().equals(psicologo.getContraseña2())){

                String sql="insert into psicologo values("+psicologo.getNo_cedula()+",'"+psicologo.getNombre()+"','"+psicologo.getAp_uno()+"','"+psicologo.getAp_dos()+"','"+psicologo.getTelefono()+"','"+psicologo.getCorreo()+"',"+psicologo.getNo_empleado()+",'"+psicologo.getContraseña()+"');";

                this.jdbcTemplate.update(sql);
            
                ModelAndView mv=new ModelAndView();
                mv.setViewName("exito3");
                mv.addObject("no_cedula",psicologo.getNo_cedula());
                mv.addObject("no_empleado",psicologo.getNo_empleado());
                mv.addObject("nombre",psicologo.getNombre());
                mv.addObject("ap_uno",psicologo.getAp_uno());
                mv.addObject("ap_dos",psicologo.getAp_dos());
                mv.addObject("telefono",psicologo.getTelefono());
                mv.addObject("correo",psicologo.getCorreo());
                mv.addObject("contraseña",psicologo.getContraseña());
                mv.addObject("contraseña2",psicologo.getContraseña2());

                return mv;
            }else{
                ModelAndView mv=new ModelAndView();
                mv.setViewName("registroPs");
                mv.addObject("registroPs",new Psicologo());
                return mv;
            }
        }
    }
}
