/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Conexion;
import models.Dieta;
import models.NeuralNet.Capa_neuronas;
import models.NeuralNet.Crear_RN;
import models.NeuralNet.Implementacion;
import models.Nutriologo;
import models.Psicologo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author jms-m
 */
@Controller
public class DietaController {
    @RequestMapping("generar_dieta_nutriologo.htm")
    public ModelAndView generar_dieta_nutriologo(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("generar_dieta_nutriologo");
        
        return mv;
    }
    
   
    @RequestMapping(params = "Dieta", method = RequestMethod.POST)
    public ModelAndView registroDieta(@ModelAttribute("Dieta") Dieta n, BindingResult result, SessionStatus status) {
        // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES 
        if (result.hasErrors()) {
            //volvemos al formulario porque los datos ingresados son incorrectos
            ModelAndView mav = new ModelAndView();
            mav.setViewName("generar_dieta_nutriologo");
            mav.addObject("Dieta", new Dieta());
            return mav;
        } else {
            
                ModelAndView mv = new ModelAndView();
                mv.setViewName("bienvenida_nutriologo"); //Pasamos a la vista de nombre exito
                mv.addObject("hora_des", n.getHora_des()); //Se agrega el campo No_cedula al modelo
                System.out.println(n.getHora_des());
                mv.addObject("hora_col1", n.getHora_col1()); //Se agrega el campo No_cedula al modelo
                System.out.println(n.getHora_col1());
                mv.addObject("hora_com", n.getHora_com()); //Se agrega el campo No_cedula al modelo
                System.out.println(n.getHora_com());
                mv.addObject("hora_col2", n.getHora_col2()); //Se agrega el campo No_cedula al modelo
                System.out.println(n.getHora_col2());
                mv.addObject("hora_cena", n.getHora_cena()); //Se agrega el campo No_cedula al modelo
                System.out.println(n.getHora_cena());
                
                Conexion conn=new Conexion();                                 //Instacia a la conexión de base de datos
        JdbcTemplate jdbc=new JdbcTemplate(conn.conectar("smae"));
        
        String t1="select*from aceitesygrasas"; // 1
        String t2="select*from aceitesygrasasconproteinas"; // 2
        String t3="select*from alimentoslibresenenergia"; // 3
        String t4="select*from aoa_altosengrasa"; // 4
        String t5="select*from aoa_bajosengrasa"; // 5
        String t6="select*from aoa_moderadosengrasa"; // 6
        String t7="select*from aoa_muybajosengrasa"; // 7
        String t8="select*from azucarescongrasa"; // 8
        String t9="select*from azucaressingrasa"; // 9
        String t10="select*from bebidasalcoholicas"; // 10
        String t11="select*from bebidasybebidasalcoholicas"; // 11
        String t12="select*from burgerking"; // 12
        String t13="select*from cerealescongrasa"; // 13
        String t14="select*from cerealessingrasa"; //14
        String t15="select*from dominos"; // 15
        String t16="select*from frutas"; // 16
        String t17="select*from guarniciones"; // 17
        String t18="select*from kfc"; // 18
        String t19="select*from lecheconazucar"; //19
        String t20="select*from lechedescremada"; // 20
        String t21="select*from lecheentera"; // 21
        String t22="select*from lechesemidescremada"; // 22
        String t23="select*from leguminosas"; // 23
        String t24="select*from littlecaesars"; // 24
        String t25="select*from mcdonalds"; // 25
        String t26="select*from papajohns"; // 26
        String t27="select*from pizzahut"; // 27
        String t28="select*from platillosbebidas"; // 28
        String t29="select*from platillosdesayuno"; // 29
        String t30="select*from platillosdesayuno2"; // 30
        String t31="select*from platillosguarniciones"; // 31
        String t32="select*from platillosplatosfuertes"; // 32
        String t33="select*from platillospostres"; //33
        String t34="select*from platillossopas"; // 34
        String t35="select*from platosfuertes"; // 35
        String t36="select*from postres"; // 36
        String t37="select*from productosyakult"; // 37
        String t38="select*from sopas"; // 38
        String t39="select*from starbucks"; // 39
        String t40="select*from subway"; // 40
        String t41="select*from tacobell"; //41
        String t42="select*from verduras"; // 42
        String t43="select*from wendys"; // 43
        
        List datosT1 = jdbc.queryForList(t1); 
        List datosT2 = jdbc.queryForList(t2); 
        List datosT3 = jdbc.queryForList(t3); 
        List datosT4 = jdbc.queryForList(t4);
        List datosT5 = jdbc.queryForList(t5); 
        List datosT6 = jdbc.queryForList(t6); 
        List datosT7 = jdbc.queryForList(t7); 
        List datosT8 = jdbc.queryForList(t8); 
        List datosT9 = jdbc.queryForList(t9); 
        List datosT10 = jdbc.queryForList(t10); 
        List datosT11 = jdbc.queryForList(t11); 
        List datosT12 = jdbc.queryForList(t12); 
        List datosT13= jdbc.queryForList(t13); 
        List datosT14 = jdbc.queryForList(t4); 
        List datosT15 = jdbc.queryForList(t15); 
        List datosT16 = jdbc.queryForList(t16); 
        List datosT17 = jdbc.queryForList(t17); 
        List datosT18 = jdbc.queryForList(t18); 
        List datosT19 = jdbc.queryForList(t19); 
        List datosT20 = jdbc.queryForList(t20);
        List datosT21 = jdbc.queryForList(t21); 
        List datosT22 = jdbc.queryForList(t22); 
        List datosT23 = jdbc.queryForList(t23); 
        List datosT24 = jdbc.queryForList(t24);
        List datosT25 = jdbc.queryForList(t25); 
        List datosT26 = jdbc.queryForList(t26); 
        List datosT27 = jdbc.queryForList(t27); 
        List datosT28 = jdbc.queryForList(t28); 
        List datosT29 = jdbc.queryForList(t29); 
        List datosT30 = jdbc.queryForList(t30); 
        List datosT31 = jdbc.queryForList(t31); 
        List datosT32 = jdbc.queryForList(t32); 
        List datosT33 = jdbc.queryForList(t33); 
        List datosT34 = jdbc.queryForList(t34);
        List datosT35 = jdbc.queryForList(t35); 
        List datosT36 = jdbc.queryForList(t36); 
        List datosT37 = jdbc.queryForList(t37); 
        List datosT38 = jdbc.queryForList(t38); 
        List datosT39 = jdbc.queryForList(t39); 
        List datosT40 = jdbc.queryForList(t40); 
        List datosT41 = jdbc.queryForList(t41); 
        List datosT42 = jdbc.queryForList(t42);
        List datosT43 = jdbc.queryForList(t43); 
        
        
        //System.out.print(datosL);
   
        mv.addObject("datoT1",datosT1);  
        mv.addObject("datoT2",datosT2);
        mv.addObject("datoT3",datosT3);
        mv.addObject("datoT4",datosT4);
        mv.addObject("datoT5",datosT5);  
        mv.addObject("datoT6",datosT6);
        mv.addObject("datoT7",datosT7);
        mv.addObject("datoT8",datosT8);
        mv.addObject("datoT9",datosT9);  
        mv.addObject("datoT10",datosT10);
        mv.addObject("datoT11",datosT11);
        mv.addObject("datoT12",datosT12);
        mv.addObject("datoT13",datosT13);
        mv.addObject("datoT14",datosT14);
        mv.addObject("datoT15",datosT15);
        mv.addObject("datoT16",datosT16);
        mv.addObject("datoT17",datosT17);
        mv.addObject("datoT18",datosT18);
        mv.addObject("datoT19",datosT19);
        mv.addObject("datoT20",datosT20);
        mv.addObject("datoT21",datosT21);
        mv.addObject("datoT22",datosT22);
        mv.addObject("datoT23",datosT23);
        mv.addObject("datoT24",datosT24);
        mv.addObject("datoT25",datosT25);
        mv.addObject("datoT26",datosT26);
        mv.addObject("datoT27",datosT27);
        mv.addObject("datoT28",datosT28);
        mv.addObject("datoT29",datosT29);
        mv.addObject("datoT30",datosT30);
        mv.addObject("datoT31",datosT31);
        mv.addObject("datoT32",datosT32);
        mv.addObject("datoT33",datosT33);
        mv.addObject("datoT34",datosT34);
        mv.addObject("datoT35",datosT35);
        mv.addObject("datoT36",datosT36);
        mv.addObject("datoT37",datosT37);
        mv.addObject("datoT38",datosT38);
        mv.addObject("datoT39",datosT39);
        mv.addObject("datoT40",datosT40);
        mv.addObject("datoT41",datosT41);
        mv.addObject("datoT42",datosT42);
        mv.addObject("datoT43",datosT43);

        mv.addObject("Dieta", new Dieta());
                
                return mv;
        } 
    }
    

     
     @RequestMapping(value="generar_dieta_nutriologo",method = RequestMethod.POST)
  
     public ModelAndView calificar_dieta(@ModelAttribute(" ") Dieta dieta){
         
         double[] input = null;
         double[] target = null;
         
         
          ArrayList<Capa_neuronas> neural_net=new ArrayList<>();  
          
          int[] topology={20,10,5,2};
          
          Crear_RN redRecomendaciones = new Crear_RN();
          neural_net = redRecomendaciones.create_nn(topology, 0);
          
          Implementacion exe = new Implementacion(neural_net);
          double[][] resultado=exe.train(input, target);
          
          System.out.println(resultado);
         
     return null;
     }
    
}