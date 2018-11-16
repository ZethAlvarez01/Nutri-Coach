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
        model.addAttribute("contrasena",nutriologo.getContraseña());
        
        return "exito1";
    }
    
    
    
    @RequestMapping(value="registroP.htm",method=RequestMethod.GET)
    public ModelAndView formularioP(){
        
        return new ModelAndView("registroP","command",new Paciente());
    }
    
    @RequestMapping(value="registroP.htm",method=RequestMethod.POST)
    public String formularioP(Paciente paciente,ModelMap model){
        
        model.addAttribute("No_Boleta",paciente.getNo_Boleta());
        model.addAttribute("Nombre",paciente.getNombre());
        model.addAttribute("Apellido_P",paciente.getApellido_P());
        model.addAttribute("Apellido_M",paciente.getApellido_M());
        model.addAttribute("Sexo",paciente.getSexo());
        model.addAttribute("Fecha_Nacimiento",paciente.getFecha_Nacimiento());
        model.addAttribute("Telefono",paciente.getTelefono());
        model.addAttribute("Domicilio",paciente.getDomicilio());
        model.addAttribute("Correo",paciente.getCorreo());
        model.addAttribute("Contrasena",paciente.getContrasena());
        
        
        return "exito2";
    }
    
    
    @RequestMapping(value="registroPs.htm",method=RequestMethod.GET)
    public ModelAndView formularioPs(){
        
        return new ModelAndView("registroPs","command",new Nutriologo());
    }
    
    @RequestMapping(value="registroPs.htm",method=RequestMethod.POST)
    public String formularioPs(Psicologo psicologo,ModelMap model){
        
        model.addAttribute("No_Cedula_P",psicologo.getNo_Cedula_P());
        model.addAttribute("No_Empleado",psicologo.getNo_Empleado());
        model.addAttribute("Nombre",psicologo.getNombre());
        model.addAttribute("Apellido_P",psicologo.getApellido_P());
        model.addAttribute("Apellido_M",psicologo.getApellido_M());
        model.addAttribute("Telefono",psicologo.getTelefono());
        model.addAttribute("Correo",psicologo.getCorreo());
        model.addAttribute("Contrasena",psicologo.getContrasena());
        
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
