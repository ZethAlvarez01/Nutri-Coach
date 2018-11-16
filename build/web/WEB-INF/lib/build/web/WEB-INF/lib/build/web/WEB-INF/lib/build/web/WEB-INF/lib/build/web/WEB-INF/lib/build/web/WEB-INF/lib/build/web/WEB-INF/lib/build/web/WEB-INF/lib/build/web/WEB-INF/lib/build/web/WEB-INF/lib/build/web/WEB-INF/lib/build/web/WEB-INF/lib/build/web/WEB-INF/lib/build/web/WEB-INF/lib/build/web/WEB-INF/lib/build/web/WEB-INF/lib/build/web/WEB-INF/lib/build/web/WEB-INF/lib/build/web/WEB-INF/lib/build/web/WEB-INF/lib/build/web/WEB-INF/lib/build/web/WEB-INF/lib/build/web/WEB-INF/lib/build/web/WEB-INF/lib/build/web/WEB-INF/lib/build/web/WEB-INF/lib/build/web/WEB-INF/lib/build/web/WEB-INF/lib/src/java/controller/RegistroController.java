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
        
        model.addAttribute("No_Cedula_P",nutriologo.getNo_Cedula_P());
        model.addAttribute("No_Empleado",nutriologo.getNo_Empleado());
        model.addAttribute("Nombre",nutriologo.getNombre());
        model.addAttribute("Apellido_P",nutriologo.getApellido_P());
        model.addAttribute("Apellido_M",nutriologo.getApellido_M());
        model.addAttribute("Telefono",nutriologo.getTelefono());
        model.addAttribute("Institucion",nutriologo.getInstitucion());
        model.addAttribute("Domicilio_C",nutriologo.getDomicilio_C());
        model.addAttribute("Correo",nutriologo.getCorreo());
        model.addAttribute("Contrasena",nutriologo.getContrasena());
        
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
    public String formularioN(Administrador admin,ModelMap model){
        
        model.addAttribute("No_Empleado",admin.getNo_Empleado());
        model.addAttribute("Nombre",admin.getNombre());
        model.addAttribute("Apellido_P",admin.getApellido_P());
        model.addAttribute("Apellido_M",admin.getApellido_M());
        model.addAttribute("Telefono",admin.getTelefono());
        model.addAttribute("Cargo",admin.getCargo());
        model.addAttribute("Correo",admin.getCorreo());
        model.addAttribute("Contrasena",admin.getContrasena());
        
        return "exito4";
    }
    
}
