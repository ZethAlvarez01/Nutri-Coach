package controller;

import java.util.ArrayList;
import models.NeuralNet.Implementacion;
import models.NeuralNet.libMatrices;
import models.NeuralNet.Capa_neuronas;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    
    @RequestMapping("foro.htm")
    public ModelAndView foro(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("foro");
        return mv;
    }
    
    @RequestMapping("expedientePaciente.htm")
    public ModelAndView expedientePaciente(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("expedientePaciente");
        return mv;
    }
    
    @RequestMapping("bienvenida_admin.htm")
    public ModelAndView bienvenida_admin(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("bienvenida_admin");
        return mv;
    }
    
     @RequestMapping("bienvenida_nutriologo.htm")
    public ModelAndView bienvenida_nutriologo(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("bienvenida_nutriologo");
        return mv;
    }
    
     @RequestMapping("bienvenida_psicologo.htm")
    public ModelAndView bienvenida_psicologo(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("bienvenida_psicologo");
        return mv;
    }
     @RequestMapping("mensajeria.htm")
    public ModelAndView mensajeria(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("mensajeria");
        return mv;
    }
     @RequestMapping("verificacion_cuentas.htm")
    public ModelAndView verificacion_cuentas(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("verificacion_cuentas");
        return mv;
    }
    
     @RequestMapping("diario_psicologo_solo_paciente.htm")
    public ModelAndView diario_psicologo_solo_paciente(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("diario_psicologo_solo_paciente");
        return mv;
    }
    
}
