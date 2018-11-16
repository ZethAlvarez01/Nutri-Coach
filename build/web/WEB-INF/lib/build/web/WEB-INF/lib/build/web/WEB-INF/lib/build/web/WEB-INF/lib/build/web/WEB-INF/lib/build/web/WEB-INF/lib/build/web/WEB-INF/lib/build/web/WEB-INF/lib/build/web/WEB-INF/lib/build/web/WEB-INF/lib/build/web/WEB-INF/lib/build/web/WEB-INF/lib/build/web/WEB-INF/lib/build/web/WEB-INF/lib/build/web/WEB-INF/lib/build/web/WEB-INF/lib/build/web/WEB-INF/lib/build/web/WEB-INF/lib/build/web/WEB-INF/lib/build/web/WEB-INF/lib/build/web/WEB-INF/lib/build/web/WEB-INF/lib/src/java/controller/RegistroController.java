package controller;

import models.Administrador;
import models.Nutriologo;
import models.Paciente;
import models.Psicologo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zeth
 */

public class RegistroController {
    
    @RequestMapping(value="registroN.htm",method=RequestMethod.GET)
    public ModelAndView formularioN(){
        
        return new ModelAndView("registroN","command",new Nutriologo());
    }
    
    @RequestMapping(value="registroN.htm",method=RequestMethod.POST)
    public String formularioN(Nutriologo nutriologo,ModelMap model){
        
        model.addAttribute("no_cedula",nutriologo.getNo_cedula());
        model.addAttribute("no_empleado",nutriologo.getNo_empleado());
        model.addAttribute("nombre",nutriologo.getNombre());
        model.addAttribute("ape_uno",nutriologo.getAp_uno());
        model.addAttribute("ape_dos",nutriologo.getAp_dos());
        model.addAttribute("telefono",nutriologo.getTelefono());
        model.addAttribute("institucion",nutriologo.getInstitucion());
        model.addAttribute("consultorio",nutriologo.getConsultorio());
        model.addAttribute("correo",nutriologo.getCorreo());
        model.addAttribute("contraseña",nutriologo.getContraseña());
        
        return "exito1";
    }
    
    
    
    @RequestMapping(value="registroP.htm",method=RequestMethod.GET)
    public ModelAndView formularioP(){
        
        return new ModelAndView("registroP","command",new Paciente());
    }
    
    @RequestMapping(value="registroP.htm",method=RequestMethod.POST)
    public String formularioP(Paciente paciente,ModelMap model){
        
        model.addAttribute("no_boleta",paciente.getNo_boleta());
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("ap_uno",paciente.getAp_dos());
        model.addAttribute("ap_dos",paciente.getAp_uno());
        model.addAttribute("sexo",paciente.getSexo());
        model.addAttribute("fecha_n",paciente.getFecha_n());
        model.addAttribute("telefono",paciente.getTelefono());
        model.addAttribute("domicilio",paciente.getDomicilio());
        model.addAttribute("correo",paciente.getCorreo());
        model.addAttribute("contraseña",paciente.getContraseña());
        
        
        return "exito2";
    }
    
    
    @RequestMapping(value="registroPs.htm",method=RequestMethod.GET)
    public ModelAndView formularioPs(){
        
        return new ModelAndView("registroPs","command",new Nutriologo());
    }
    
    @RequestMapping(value="registroPs.htm",method=RequestMethod.POST)
    public String formularioPs(Psicologo psicologo,ModelMap model){
        
        model.addAttribute("no_cedula",psicologo.getNo_cedula());
        model.addAttribute("no_empleado",psicologo.getNo_empleado());
        model.addAttribute("nombre",psicologo.getNombre());
        model.addAttribute("ap_uno",psicologo.getAp_uno());
        model.addAttribute("ap_dos",psicologo.getAp_uno());
        model.addAttribute("telefono",psicologo.getTelefono());
        model.addAttribute("correo",psicologo.getCorreo());
        model.addAttribute("contraseña",psicologo.getContraseña());
        
        return "exito3";
    }
    
    @RequestMapping(value="registroA.htm",method=RequestMethod.GET)
    public ModelAndView formularioA(){
        
        return new ModelAndView("registroA","command",new Administrador());
    }
    
    @RequestMapping(value="registroA.htm",method=RequestMethod.POST)
    public String formularioA(Administrador admin,ModelMap model){
        
        model.addAttribute("no_empleado",admin.getNo_empleado());
        model.addAttribute("nombre",admin.getNombre());
        model.addAttribute("ap_uno",admin.getAp_uno());
        model.addAttribute("ap_dos",admin.getAp_dos());
        model.addAttribute("telefono",admin.getTelefono());
        model.addAttribute("cargo",admin.getCargo());
        model.addAttribute("correo",admin.getCorreo());
        model.addAttribute("contraseña",admin.getContraseña());
        
        return "exito4";
    }
    
}
