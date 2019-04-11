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
 * @author Zeth
 */
@Controller
@RequestMapping("recupearC.htm")
public class RecuperarController {
    
    private RecuperarValidar Validar;

    public RecuperarController() {
        this.Validar=new RecuperarValidar();
    }
    
    

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView inicio(){
        return new ModelAndView("recuperarC","recuperarC",new Recuperar());
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView inicio(
                        @ModelAttribute("Recuperar") @Validated Recuperar recuperar,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.Validar.validate(recuperar, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("recuperarC");
            mv.addObject("recuperarC",new Recuperar());
            mv.addObject("correo",recuperar.getCorreo());
            return mv;
        }else{
            ModelAndView mv=new ModelAndView();
            mv.setViewName("exito0_1");
           mv.addObject("correo",recuperar.getCorreo());

            return mv;
        }
    }

}
