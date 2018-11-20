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
    
}
