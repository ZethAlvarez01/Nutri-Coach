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
import models.NeuralNet.Crear_RN;
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
            String sql="select * from administrador where contraseña='"+
            login.getPass()+"' and no_empleado="+login.getUsuario()+";";
            List datos=this.jdbcTemplate.queryForList(sql);
            System.out.println(datos);
            
            if(datos.size()>=1){
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foro");
                ArrayList<Capa_neuronas> neural_net;
                libMatrices op=new libMatrices();
                 double[] x={0,0,1,0,1,1,1,1,0,0,
                                 1,0,1,1,0,1,1,0,1,0,
                                 0,1,0,0,1,0,0,1,0,0,
                                 1,0,0,0,1,0,0,0,0,1,
                                 0,0};
                    Crear_RN redRecomendaciones=new Crear_RN();
                    neural_net=redRecomendaciones.create_nn(topology,0);
                    
                    ArrayList<double[][]> pesos=redRecomendaciones.asignarPesos();
                    
                    neural_net.get(0).w=pesos.get(0);
                    neural_net.get(0).b=pesos.get(1);

                    neural_net.get(1).w=pesos.get(2);
                    neural_net.get(1).b=pesos.get(3);
                Implementacion exe=new Implementacion(neural_net,x);
                double[][] output=exe.Implement();
                System.out.println("Entrada: ");
                double[][] xa=new double[1][];
                xa[0]=x;
                op.print(xa);
                System.out.println("Salida: ");
                op.print(output);
                mv.addObject("salida1",output[0][0]);
                mv.addObject("salida2",output[0][1]);
                mv.addObject("salida3",output[0][2]);
                mv.addObject("salida4",output[0][3]);
                mv.addObject("salida5",output[0][4]);
                mv.addObject("salida6",output[0][5]);
                mv.addObject("nombre","Administrador");
                return mv;
            }else{
                sql="select * from paciente where contraseña='"+
                login.getPass()+"' and no_boleta="+login.getUsuario()+";";
                datos=this.jdbcTemplate.queryForList(sql);
                System.out.println(datos);
                
                if(datos.size()>=1){
                    ModelAndView mv=new ModelAndView();
                    mv.setViewName("foro");
                    ArrayList<Capa_neuronas> neural_net;
                    libMatrices op=new libMatrices();
                     double[] x={0,0,1,0,1,1,1,1,0,0,
                                 1,0,1,1,0,1,1,0,1,0,
                                 0,1,0,0,1,0,0,1,0,0,
                                 1,0,0,0,1,0,0,0,0,1,
                                 0,0};
                    Crear_RN redRecomendaciones=new Crear_RN();
                    neural_net=redRecomendaciones.create_nn(topology,0);
                    
                    ArrayList<double[][]> pesos=redRecomendaciones.asignarPesos();
                    
                    neural_net.get(0).w=pesos.get(0);
                    neural_net.get(0).b=pesos.get(1);

                    neural_net.get(1).w=pesos.get(2);
                    neural_net.get(1).b=pesos.get(3);
                    Implementacion exe=new Implementacion(neural_net,x);
                    double[][] output=exe.Implement();
                    System.out.println("Entrada: ");
                    double[][] xa=new double[1][];
                    xa[0]=x;
                    op.print(xa);
                    System.out.println("Salida: ");
                    op.print(output);
                    mv.addObject("salida1",output[0][0]);
                    mv.addObject("salida2",output[0][1]);
                    mv.addObject("salida3",output[0][2]);
                    mv.addObject("salida4",output[0][3]);
                    mv.addObject("salida5",output[0][4]);
                    mv.addObject("salida6",output[0][5]);
                    mv.addObject("nombre","Paciente");
                    return mv;
                }else{
                   sql="select * from nutriologo where contraseña='"+
                   login.getPass()+"' and no_empleado="+login.getUsuario()+";";
                   datos=this.jdbcTemplate.queryForList(sql);
                   System.out.println(datos); 
                   
                   if(datos.size()>=1){
                        ModelAndView mv=new ModelAndView();
                        mv.setViewName("foro");
                        ArrayList<Capa_neuronas> neural_net;
                        libMatrices op=new libMatrices();
                         double[] x={0,0,1,0,1,1,1,1,0,0,
                                 1,0,1,1,0,1,1,0,1,0,
                                 0,1,0,0,1,0,0,1,0,0,
                                 1,0,0,0,1,0,0,0,0,1,
                                 0,0};
                    Crear_RN redRecomendaciones=new Crear_RN();
                    neural_net=redRecomendaciones.create_nn(topology,0);
                    
                    ArrayList<double[][]> pesos=redRecomendaciones.asignarPesos();
                    
                    neural_net.get(0).w=pesos.get(0);
                    neural_net.get(0).b=pesos.get(1);

                    neural_net.get(1).w=pesos.get(2);
                    neural_net.get(1).b=pesos.get(3);
                        Implementacion exe=new Implementacion(neural_net,x);
                        double[][] output=exe.Implement();

                        System.out.println("Entrada: ");
                        double[][] xa=new double[1][];
                        xa[0]=x;
                        op.print(xa);
                        System.out.println("Salida: ");
                        op.print(output);
                        mv.addObject("salida1",output[0][0]);
                        mv.addObject("salida2",output[0][1]);
                        mv.addObject("salida3",output[0][2]);
                        mv.addObject("salida4",output[0][3]);
                        mv.addObject("salida5",output[0][4]);
                        mv.addObject("salida6",output[0][5]);
                        mv.addObject("nombre","Nutriologo");
                        return mv;    
                   }else{
                        sql="select * from psicologo where contraseña='"+
                        login.getPass()+"' and no_empleado="+login.getUsuario()+";";
                        datos=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datos);  
                        
                        if(datos.size()>=1){
                        ModelAndView mv=new ModelAndView();
                        mv.setViewName("foro");
                        ArrayList<Capa_neuronas> neural_net;
                        libMatrices op=new libMatrices();
                         double[] x={0,0,1,0,1,1,1,1,0,0,
                                 1,0,1,1,0,1,1,0,1,0,
                                 0,1,0,0,1,0,0,1,0,0,
                                 1,0,0,0,1,0,0,0,0,1,
                                 0,0};
                    Crear_RN redRecomendaciones=new Crear_RN();
                    neural_net=redRecomendaciones.create_nn(topology,0);
                    
                    ArrayList<double[][]> pesos=redRecomendaciones.asignarPesos();
                    
                    neural_net.get(0).w=pesos.get(0);
                    neural_net.get(0).b=pesos.get(1);

                    neural_net.get(1).w=pesos.get(2);
                    neural_net.get(1).b=pesos.get(3);

                        Implementacion exe=new Implementacion(neural_net,x);
                        double[][] output=exe.Implement();

                        System.out.println("Entrada: ");
                        double[][] xa=new double[1][];
                        xa[0]=x;
                        op.print(xa);
                        System.out.println("Salida: ");
                        op.print(output);
                        mv.addObject("salida1",output[0][0]);
                        mv.addObject("salida2",output[0][1]);
                        mv.addObject("salida3",output[0][2]);
                        mv.addObject("salida4",output[0][3]);
                        mv.addObject("salida5",output[0][4]);
                        mv.addObject("salida6",output[0][5]);
                        mv.addObject("nombre","Psicologo");
                        return mv;    
                   }else{
                        ModelAndView mv=new ModelAndView();
                        mv.setViewName("inicio");
                        mv.addObject("loginO",new Login());
                        return mv; 
                        }
                   }
                }
            }
        }
    }
}
                