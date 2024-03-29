package controller;

import models.Nutriologo;
import models.Login;
import models.Recuperar;
import models.RecuperarValidar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

public class RecuperarController {
    
    private RecuperarValidar RecuperarValidar;

    public RecuperarController() {
        this.RecuperarValidar=new RecuperarValidar(); // INSTANCIA DE LA CLASE RecuperarValidar
    }
    
    

    @RequestMapping(value="recuperarC.htm", method = RequestMethod.GET) // SE UTILIZARÁ LA VISTA recuperarC y se aplicará el método GET
    public ModelAndView recuperarC(){
       
       ModelAndView mav = new ModelAndView();            //CREACIÓN DEL MODELO
       
       mav.setViewName("recuperarC");                   // SE NOMBRA AL MODELO
       
       mav.addObject("Recuperar",new Recuperar());      // SE AGREGA EL OBJETO Recupear AL MODELO
       
       return mav;
            }
    
    
    
       /////Recibimos y validamos los datos de nuestro formulario
    @RequestMapping(value="recuperarC.htm", method=RequestMethod.POST)
    public ModelAndView recuperarC(@ModelAttribute("Recuperar") Recuperar r, BindingResult result, SessionStatus status){
         this.RecuperarValidar.validate(r, result);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES              
         if(result.hasErrors()){
             
             //volvemos al formulario porque los datos ingresados son incorrectos
             ModelAndView mav= new ModelAndView();          // CREACIÓN DEL MODELO
             mav.setViewName("recuperarC");                // SE NOMBRA AL MODELO
             mav.addObject("Recuperar",new Recuperar() );  // SE AGREGA EL OBJETO Recuperar al modelo
             return mav;
         }
         else{
             
             //El usuario ingreso bien los datos
             
            
             
             
             ModelAndView mav= new ModelAndView();
             mav.setViewName("exito4"); //Pasamos a la vista de nombre exito4
             mav.addObject("Correo",r.getCorreo()); //Se agrega el campo Correo al modelo
             System.out.println(r.getCorreo());
             
             
             
             
             return mav;
         } 
      
        
        
    }
    
    
    

}
