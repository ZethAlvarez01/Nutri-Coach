package controller;

import java.util.ArrayList;
import models.NeuralNet.Implementacion;
import models.NeuralNet.libMatrices;
import models.NeuralNet.Capa_neuronas;
import models.Recuperar;
import models.RecuperarValidar;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import models.cambiarC;
import models.cambiarCValidar;

/**
 *
 * @author Zeth
 */

@Controller
public class VistaController {

    
    @RequestMapping("politicas.htm")
    public ModelAndView politicas(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("politicas");
        return mv;
    }
    
    @RequestMapping("login.htm")
    public ModelAndView login(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    
    @RequestMapping("tyc.htm")
    public ModelAndView tyc(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("tyc");
        return mv;
    }
    
    @RequestMapping("preregistro.htm")
    public ModelAndView preregistro(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("preregistro");
        return mv;
    }
    
    @RequestMapping("leermas.htm")
    public ModelAndView leermas(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("leermas");
        return mv;
    }


  
      @RequestMapping("suspendido.htm")
    public ModelAndView suspendido(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("suspendido");
        return mv;
    }
    @RequestMapping("Rechazado.htm")
    public ModelAndView Rechazado(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Rechazado");
        return mv;
    }
    
    @RequestMapping("espera_Aprobacion.htm")
    public ModelAndView espera_Aprobacion(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("espera_Aprobacion");
        return mv;
    }
    
    
    
    
    
    private cambiarCValidar cambiarCValidar;

    public VistaController() {
        this.cambiarCValidar=new cambiarCValidar(); // INSTANCIA DE LA CLASE RecuperarValidar
    } 
    
    
    
    
       @RequestMapping(value="cambiarC.htm", method = RequestMethod.GET) // SE UTILIZARÁ LA VISTA recuperarC y se aplicará el método GET
    public ModelAndView recuperarC(){
       
       ModelAndView mav = new ModelAndView();            //CREACIÓN DEL MODELO
       
       mav.setViewName("cambiarC");                   // SE NOMBRA AL MODELO
       
       mav.addObject("cambiarC",new cambiarC());      // SE AGREGA EL OBJETO Recupear AL MODELO
       
       return mav;
            }
    
    
       /////Recibimos y validamos los datos de nuestro formulario
    @RequestMapping(value="cambiarC.htm", method=RequestMethod.POST)
    public ModelAndView recuperarC(@ModelAttribute("cambiarC") cambiarC r, BindingResult result, SessionStatus status){
         this.cambiarCValidar.validate(r, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES              
         if(result.hasErrors()){
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             ModelAndView mav= new ModelAndView();          // CREACIÓN DEL MODELO
             mav.setViewName("cambiarC");                // SE NOMBRA AL MODELO
             mav.addObject("cambiarC",new cambiarC() );  // SE AGREGA EL OBJETO Recuperar al modelo
             return mav;
         }
         else{
             
             //El usuario ingreso bien los datos
             
            
             
             
             ModelAndView mav= new ModelAndView();
             mav.setViewName("exito4"); //Pasamos a la vista de nombre exito4
             mav.addObject("Contraeña",r.getContraseña()); //Se agrega el campo Correo al modelo
             System.out.println(r.getContraseña());
             
             
             
             
             return mav;
         } 
      
        
        
    }
}
