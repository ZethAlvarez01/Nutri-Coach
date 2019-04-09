package controller;

import java.util.ArrayList;
import java.util.List;
import models.Conexion;
import models.Foro;
import models.Nutriologo;
import models.Login;
import models.LoginValidar;
import models.NeuralNet.Implementacion;
import models.NeuralNet.libMatrices;
import models.NeuralNet.Capa_neuronas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("inicio.htm")
public class InicioController {
    
    private int[] topology={42,5,6};
    
    private JdbcTemplate jdbcTemplate;
    private LoginValidar loginValidar;

    public InicioController() {
        this.loginValidar=new LoginValidar();
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView inicio(){
        return new ModelAndView("inicio","loginO",new Login());
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView inicio(
                        @ModelAttribute("Usuario") @Validated Login login,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.loginValidar.validate(login, resultado);
        if(resultado.hasErrors()){
            ModelAndView mv=new ModelAndView();
            mv.setViewName("inicio");
            mv.addObject("loginO",new Login());
            return mv;
        }else{
            String sql="select * from psicologo where contraseña='"+
            login.getPass()+"' and no_empleado="+login.getUsuario()+";";
            List datos=this.jdbcTemplate.queryForList(sql);
            System.out.println(datos);  
        }
    }
}
                