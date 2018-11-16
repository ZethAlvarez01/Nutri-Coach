package controller;

import models.Nutriologo;
import models.Login;
import models.Recuperar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Zeth
 */

public class RecuperarController {

    @RequestMapping(value="recuperarC.htm",method=RequestMethod.GET)
    public ModelAndView recuperar(){
        
        return new ModelAndView("recuperarC","command",new Recuperar());
    }
    
    @RequestMapping(value="recuperarC.htm",method=RequestMethod.POST)
    public String inicio(Recuperar recuperar,ModelMap model){
        
        model.addAttribute("correo",recuperar.getCorreo());
        
        return "exito0_1";
    }

}
