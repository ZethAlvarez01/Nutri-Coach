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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zeth
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
        this.PacienteValidar=new PacienteValidar();
        this.nutriologoValidar=new NutriologoValidar();
        this.PsicologoValidar=new PsicologoValidar();
        this.loginValidar=new LoginValidar();
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
    
//////////////////////////////////////////    

    
    
///////////////////////////////////////////
    @RequestMapping(value="registroN.htm",method=RequestMethod.GET)
    public ModelAndView formularioN(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("registroN");
        mv.addObject("Nutriologo",new Nutriologo());
        return mv;
    }
  
    @RequestMapping(value="registroN.htm",method=RequestMethod.POST)
    public ModelAndView formularioN(
                        @ModelAttribute("Nutriologo") Nutriologo n,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.nutriologoValidar.validate(n, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("registroN");
            mv.addObject("Nutriologo",new Nutriologo());
            return mv;
        }else{
            
             if(n.getContraseña().equals(n.getContraseña2())){

                String sql="insert into nutriologo values("+n.getNo_cedula()+",'"+n.getNombre()+"','"+n.getAp_uno()+"','"+n.getAp_dos()+"','"+n.getTelefono()+"','"+n.getConsultorio()+"','"+n.getCorreo()+"','"+n.getContraseña()+"','"+n.getInstitucion()+"',"+n.getNo_empleado()+");";

                this.jdbcTemplate.update(sql);
            
                ModelAndView mv=new ModelAndView();
                mv.setViewName("exito1");
                mv.addObject("no_cedula",n.getNo_cedula());
                mv.addObject("no_empleado",n.getNo_empleado());
                mv.addObject("nombre",n.getNombre());
                mv.addObject("ap_uno",n.getAp_uno());
                mv.addObject("ap_dos",n.getAp_dos());
                mv.addObject("institucion",n.getInstitucion());
                mv.addObject("telefono",n.getTelefono());
                mv.addObject("consultorio",n.getConsultorio());
                mv.addObject("correo",n.getCorreo());
                mv.addObject("contraseña",n.getContraseña());
                mv.addObject("contraseña2",n.getContraseña2());

                return mv;
             }else{
                ModelAndView mv=new ModelAndView();
                mv.setViewName("registroN");
                mv.addObject("Nutriologo",new Nutriologo());
                return mv;
             }
        }
    }
    ////////////////////////////////////////////////////////////////////
    @RequestMapping(value="registroP.htm",method=RequestMethod.GET)
    public ModelAndView formularioP(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("registroP");
        mv.addObject("Paciente",new Paciente());
        return mv;
    }
    
    @RequestMapping(value="registroP.htm",method=RequestMethod.POST)
    public ModelAndView formularioP(
                        @ModelAttribute("Paciente") Paciente p,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.PacienteValidar.validate(p, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("registroP");
            mv.addObject("Paciente",new Paciente());
            return mv;
        }else{
            
            if(p.getContraseña().equals(p.getContraseña2())){
                
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNac = LocalDate.parse(p.getFecha_n(), fmt);
                LocalDate ahora = LocalDate.now();

                Period periodo = Period.between(fechaNac, ahora);

                String sql="insert into paciente values("+p.getNo_boleta()+",0,'"+p.getNombre()+"','"+p.getAp_uno()+"','"+p.getAp_dos()+"',"+periodo.getYears()+",'"+p.getSexo()+"','"+p.getFecha_n()+"','"+p.getTelefono()+"','"+p.getDomicilio()+"','"+p.getCorreo()+"','"+p.getContraseña()+"');";

                this.jdbcTemplate.update(sql);

                ModelAndView mv=new ModelAndView();
                mv.setViewName("exito2");
                mv.addObject("no_boleta",p.getNo_boleta());
                mv.addObject("nombre",p.getNombre());
                mv.addObject("ap_uno",p.getAp_uno());
                mv.addObject("ap_dos",p.getAp_dos());
                mv.addObject("sexo",p.getSexo());
                mv.addObject("fecha_n",p.getFecha_n());
                mv.addObject("telefono",p.getTelefono());
                mv.addObject("domicilio",p.getDomicilio());
                mv.addObject("correo",p.getCorreo());
                mv.addObject("contraseña",p.getContraseña());
                mv.addObject("contraseña2",p.getContraseña2());

                return mv;
            }else{
                ModelAndView mv=new ModelAndView();
                mv.setViewName("registroP");
                mv.addObject("Paciente",new Paciente());
                return mv;  
            }
            
        }
    }
  
    //////////////////////////////////////////////////////////////////////
    @RequestMapping(value="registroPs.htm",method=RequestMethod.GET)
    public ModelAndView formularioPs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("registroPs");
        mv.addObject("Psicologo",new Psicologo());
        return mv;
    }
  
    @RequestMapping(value="registroPs.htm",method=RequestMethod.POST)
    public ModelAndView formularioPs(
                        @ModelAttribute("Psicologo") Psicologo ps,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.PsicologoValidar.validate(ps, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("registroPs");
            mv.addObject("Psicologo",new Psicologo());
            return mv;
        }else{
            
            if(ps.getContraseña().equals(ps.getContraseña2())){

                String sql="insert into psicologo values("+ps.getNo_cedula()+",'"+ps.getNombre()+"','"+ps.getAp_uno()+"','"+ps.getAp_dos()+"','"+ps.getTelefono()+"','"+ps.getCorreo()+"',"+ps.getNo_empleado()+",'"+ps.getContraseña()+"');";

                this.jdbcTemplate.update(sql);
            
                ModelAndView mv=new ModelAndView();
                mv.setViewName("exito3");
                mv.addObject("no_cedula",ps.getNo_cedula());
                mv.addObject("no_empleado",ps.getNo_empleado());
                mv.addObject("nombre",ps.getNombre());
                mv.addObject("ap_uno",ps.getAp_uno());
                mv.addObject("ap_dos",ps.getAp_dos());
                mv.addObject("telefono",ps.getTelefono());
                mv.addObject("correo",ps.getCorreo());
                mv.addObject("contraseña",ps.getContraseña());
                mv.addObject("contraseña2",ps.getContraseña2());

                return mv;
            }else{
                ModelAndView mv=new ModelAndView();
                mv.setViewName("registroPs");
                mv.addObject("Psicologo",new Psicologo());
                return mv;
            }
        }
    }
}
