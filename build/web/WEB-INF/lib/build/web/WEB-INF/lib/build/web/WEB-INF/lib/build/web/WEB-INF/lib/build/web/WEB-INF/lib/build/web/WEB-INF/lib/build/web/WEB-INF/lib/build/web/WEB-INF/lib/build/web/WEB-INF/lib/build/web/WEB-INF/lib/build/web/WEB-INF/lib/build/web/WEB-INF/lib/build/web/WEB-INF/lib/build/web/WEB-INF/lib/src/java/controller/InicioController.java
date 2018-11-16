package controller;

import models.Nutriologo;
import models.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Zeth
 */

public class InicioController {

    @RequestMapping(value="inicio.htm",method=RequestMethod.GET)
    public ModelAndView inicio(){
        
        return new ModelAndView("inicio","command",new Login());
    }
    
    @RequestMapping(value="inicio.htm",method=RequestMethod.POST)
    public String inicio(Login login,ModelMap model){
        
        model.addAttribute("Usuario",login.getUsuario());
        model.addAttribute("Pass",login.getPass());
        
        return "exito0";
    }

}
