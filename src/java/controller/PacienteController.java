/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Conexion;
import models.NeuralNet.Capa_neuronas;
import models.NeuralNet.Crear_RN;
import models.NeuralNet.Implementacion;
import models.NeuralNet.Tratamiento;
import models.NeuralNet.libMatrices;
import models.Paciente;
import models.Psicologo;
import models.entradaForo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
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
public class PacienteController {
     private JdbcTemplate jdbcTemplate;
     private int[] topology={42,5,6};
      public PacienteController() {
        
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
      
      
 ///////////////////////////////////////
      //Pantalla de primera cita
        @RequestMapping(value="primera_cita.htm",method = RequestMethod.GET) 
    
     public ModelAndView primeraCita(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         System.out.println("PRIMERA CITA GET"); 
        
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA PRIMERA CITA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("primera_cita");
                
                String sql="select nombre,ap_uno,ap_dos, no_boleta from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                
                return mv;
                
         
         
                    
                
    }
     
     
     ///////////////////////////////////////
      //Pantalla de nueva entrada en el foro
        @RequestMapping(value="nuevaEntrada.htm",method = RequestMethod.GET) 
    
     public ModelAndView nuevaEntradaForo(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         System.out.println("nueva entrada get"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("nuevaEntrada");
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                
                return mv;
                
     
         
                    
                
    }
     
      ///////////////////////////////////////
      //Pantalla de consultar entrada en el foro
        @RequestMapping(value="ConsultarEntrada.htm",method = RequestMethod.GET) 
    
     public ModelAndView consultarEntrada (@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{
       
         System.out.println("CONSULTAR ENTRADA get"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("consultarEntrada");
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                                 
                 sql="select * from entrada where id_entrada="+eF.getId_entrada(); 
                     datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("Entrada",datosL2);
                
                return mv;
                
     
         
                    
                
    }
     
     
     
      
      
      
      
/////////////////////////////////////////////
     ///pantalla de vista foro
      @RequestMapping(value="foro.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA foro y se aplicará el método GET
    
        public ModelAndView foro(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton foro se cambiara a la vista de foroPs
        System.out.println("foro"); 
        
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foro");
                
                String sql="select nombre,ap_uno,ap_dos, no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                                 
                sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc;"; // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 mv.addObject("listaEntradas",datosL2);
                return mv;
                
     }
     
     
      /////////////////////////////////////////////
     ///pantalla de vista mensajeriaPs
      @RequestMapping(value="mensajeria.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeria y se aplicará el método GET
    
     public ModelAndView mensajeria(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("mensajeria"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeria");
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                
                return mv;
                
     }
     
        /////////////////////////////////////////////
     ///pantalla de vista EXPEDIENTE
      @RequestMapping(value="expedientePaciente.htm",method = RequestMethod.GET)  // SE UTILIZARÁ LA VISTA mensajeria y se aplicará el método GET
    
        
     public ModelAndView expediente(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("expediente"); 
        
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("expedientePaciente");
                
                String sql="select nombre,ap_uno,ap_dos, no_boleta, no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 
                                 
                                 sql="select*from evolucion where id_exp=(select id_expediente from expediente where no_boleta='"+alert+"');";
                    List datosEv=this.jdbcTemplate.queryForList(sql);
                    //ConsultaEvolucion evo=new ConsultaEvolucion(login.getUsuario());
                    //List datas=evo.consulta();
                    System.out.println(datosEv);
                    mv.addObject("datas",datosEv);
                    //sql="select edad,sexo,peso,altura,ansiedad,depresion,ira,estres,"
                      //      + "felicidad,dulce,amarga,salada,picante,acida,act_f,suplementos,"
                       //     + "motivacional,preparacionA,beneficiosA,deportes,medicamentos,salud from paciente,expediente where paciente.no_boleta='"+lo.getUsuario()+"';";

                   List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
                    for(Map<String, Object> row : rows){
                       String edad = row.get("edad").toString();
                       String sexo = row.get("sexo").toString();
                       String peso = row.get("peso").toString();
                       String altura = row.get("altura").toString();
                       String ansiedad = row.get("ansiedad").toString();
                       String depresion = row.get("depresion").toString();
                       String ira = row.get("ira").toString();
                       String estres = row.get("estres").toString();
                       String felicidad = row.get("felicidad").toString();
                       String dulce = row.get("dulce").toString();
                       String amarga = row.get("amarga").toString();
                       String salada = row.get("salada").toString();
                       String picante = row.get("picante").toString();
                       String acida = row.get("acida").toString();
                       String act_f = row.get("act_f").toString();
                       String suplementos = row.get("suplementos").toString();
                       String motivacional = row.get("motivacional").toString();
                       String preparacionA = row.get("preparacionA").toString();
                       String beneficiosA = row.get("beneficiosA").toString();
                       String deportes = row.get("deportes").toString();
                       String medicamentos = row.get("medicamentos").toString();
                       String salud= row.get("salud").toString();
                       
                       double pesoD=Double.parseDouble(peso);
                       int pesoI=(int)pesoD;
                       String pesoS=pesoI+"";
                       
                       System.out.println(edad + " " + pesoS + " "+ sexo + " " + dulce + " " +medicamentos+" "+act_f);
                       
                       Tratamiento tr=new Tratamiento(edad,sexo,pesoS,altura,ansiedad,
                               depresion,ira,estres,felicidad,dulce,amarga,salada,picante,
                               acida,act_f,suplementos,motivacional, preparacionA,beneficiosA,
                               deportes,medicamentos,salud);
                       
                        double[] x=tr.vector();
                        ArrayList<Capa_neuronas> neural_net;
                        libMatrices op=new libMatrices();

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

                        ArrayList<String> salida=tr.seleccion(output[0]);
                    
                       System.out.println(salida);
                       mv.addObject("respuesta",salida.get(0));
                
               
                
     }
     //System.out.println(datas);
      return mv;
     }
     
      @RequestMapping(params="foro",method = RequestMethod.POST)
  
     public ModelAndView cambiarForo(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton foro se cambiara a la vista de foroPs
        System.out.println("foro"); 
        
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("foro");
                
                String sql="select nombre,ap_uno,ap_dos, no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                             
                 sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc;"; // OBETENEMOS TODAS LAS ENTRADAS QUE HA HECHO NUESTRO PACIENTE A PARTIR DE LA MÁS RECIENTE
                                 datosL2 = this.jdbcTemplate.queryForList(sql);
                                 mv.addObject("listaEntradas",datosL2);
                
                return mv;
                
     }
     
     @RequestMapping(params="mensajeria",method = RequestMethod.POST)
     public ModelAndView cambiarMensajes(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("mensajeria"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("mensajeria");
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                
                return mv;
                
     }
     
     
     @RequestMapping(params="expediente",method = RequestMethod.POST)
     public ModelAndView cambiarExpediente(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("expediente"); 
        
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("expedientePaciente");
                
                String sql="select nombre,ap_uno,ap_dos, no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 
                                 
                                 sql="select*from evolucion where id_exp=(select id_expediente from expediente where no_boleta='"+alert+"');";
                    List datosEv=this.jdbcTemplate.queryForList(sql);
                    //ConsultaEvolucion evo=new ConsultaEvolucion(login.getUsuario());
                    //List datas=evo.consulta();
                    System.out.println(datosEv);
                    mv.addObject("datas",datosEv);
                    //sql="select edad,sexo,peso,altura,ansiedad,depresion,ira,estres,"
                      //      + "felicidad,dulce,amarga,salada,picante,acida,act_f,suplementos,"
                       //     + "motivacional,preparacionA,beneficiosA,deportes,medicamentos,salud from paciente,expediente where paciente.no_boleta='"+lo.getUsuario()+"';";

                   List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
                    for(Map<String, Object> row : rows){
                       String edad = row.get("edad").toString();
                       String sexo = row.get("sexo").toString();
                       String peso = row.get("peso").toString();
                       String altura = row.get("altura").toString();
                       String ansiedad = row.get("ansiedad").toString();
                       String depresion = row.get("depresion").toString();
                       String ira = row.get("ira").toString();
                       String estres = row.get("estres").toString();
                       String felicidad = row.get("felicidad").toString();
                       String dulce = row.get("dulce").toString();
                       String amarga = row.get("amarga").toString();
                       String salada = row.get("salada").toString();
                       String picante = row.get("picante").toString();
                       String acida = row.get("acida").toString();
                       String act_f = row.get("act_f").toString();
                       String suplementos = row.get("suplementos").toString();
                       String motivacional = row.get("motivacional").toString();
                       String preparacionA = row.get("preparacionA").toString();
                       String beneficiosA = row.get("beneficiosA").toString();
                       String deportes = row.get("deportes").toString();
                       String medicamentos = row.get("medicamentos").toString();
                       String salud= row.get("salud").toString();
                       
                       double pesoD=Double.parseDouble(peso);
                       int pesoI=(int)pesoD;
                       String pesoS=pesoI+"";
                       
                       System.out.println(edad + " " + pesoS + " "+ sexo + " " + dulce + " " +medicamentos+" "+act_f);
                       
                       Tratamiento tr=new Tratamiento(edad,sexo,pesoS,altura,ansiedad,
                               depresion,ira,estres,felicidad,dulce,amarga,salada,picante,
                               acida,act_f,suplementos,motivacional, preparacionA,beneficiosA,
                               deportes,medicamentos,salud);
                       
                        double[] x=tr.vector();
                        ArrayList<Capa_neuronas> neural_net;
                        libMatrices op=new libMatrices();

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

                        ArrayList<String> salida=tr.seleccion(output[0]);
                    
                       System.out.println(salida);
                       mv.addObject("respuesta",salida.get(0));
                
               
                
     }
     //System.out.println(datas);
      return mv;
     }
     
     
        
    
    
    
      @RequestMapping(params="primera_cita",method = RequestMethod.POST)
     public ModelAndView cambiarPrimeraCita(@ModelAttribute("Paciente") Paciente p, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("primera cita POST"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("primera_cita");
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                
                return mv;
                
     }
     
     
     
      @RequestMapping(params="nuevaEntrada",method = RequestMethod.POST)
     public ModelAndView cambiarNuevaEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("nueva entrada POST"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("nuevaEntrada");
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                                 
                return mv;
                
     }
     
     
      @RequestMapping(params="guardarEntrada", method = RequestMethod.POST)
    public ModelAndView guardarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
       System.out.println("GUARDAR ENTRADA DEL FORO");
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
          
     
        String sql="insert into entrada values("+'0'+","+alert+",'"+eF.getTitulo()+"','"+eF.getContenido()+"','');";
                               
       
                this.jdbcTemplate.update(sql);
            
       
       
         System.out.println("ENTRADA GUARDADA");
            
            
            
           ModelAndView mv=new ModelAndView();
                mv.setViewName("ConsultarEntrada");
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                             
                                 
                sql="select * from entrada where id_usuario="+alert+" order by id_entrada desc limit 1"; 
                     datosL2 = this.jdbcTemplate.queryForList(sql);
                     System.out.println("ESTO OBTUVE AL GUARDAR");
                                 System.out.println(datosL2);
                                 mv.addObject("Entrada",datosL2);
                                 
                return mv;
        
       
            
                
       
       
    }
    
     @RequestMapping(params="EliminarEntrada", method = RequestMethod.POST)
    public ModelAndView EliminarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
       System.out.println("ELIMINAR ENTRADA DEL FORO");
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
          
     
        String sql=" delete from entrada where id_entrada="+eF.getId_entrada()+";";
                               
       
                this.jdbcTemplate.update(sql);
            
       
       
         System.out.println("ENTRADA ELIMINADA");
            
            
            
           return new ModelAndView("redirect:/foro.htm");
        
       
            
                
       
       
    }
    
    
    
     
     @RequestMapping(params="ModificarEntrada", method = RequestMethod.POST)
    public ModelAndView ModificarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
       
       System.out.println("MODIFICAR ENTRADA DEL FORO");
       HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
          
     
        String sql="update entrada set titulo='"+eF.getTitulo()+"',contenido='"+eF.getContenido()+"'where id_entrada="+eF.getId_entrada()+";";
                               
       
                this.jdbcTemplate.update(sql);
             

      
                
       
       
         System.out.println("MODIFICACIÓN GUARDADA");
            
            
            
          ModelAndView mv=new ModelAndView();
                mv.setViewName("ConsultarEntrada");
                
                sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                                 
                sql="select * from entrada where id_entrada="+eF.getId_entrada(); 
                     datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("Entrada",datosL2);
                                 
                return mv;
        
       
            
                
       
       
    }
     
    
    
    
    
    
    
    
    
      @RequestMapping(params="consultarEntrada",method = RequestMethod.POST)
     public ModelAndView cambiarConsultarEntrada(@ModelAttribute("entradaForo") entradaForo eF, BindingResult result, HttpServletRequest hsr, HttpServletResponse hsrl)throws Exception{ // al hacer clik en el boton mensajes se cambiara a la vista de MensajeriaPs
        System.out.println("CONSULTAR ENTRADA POST"); 
        
        HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
        
       System.out.println("no_boleta: "+alert);
         
                
                ModelAndView mv=new ModelAndView();
                mv.setViewName("ConsultarEntrada");
                
                String sql="select nombre,ap_uno,ap_dos,no_boleta,no_cedula from paciente where no_boleta="+alert;
                                List datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("datos",datosL2);          // Pasa la lilsta completa
                                 mv.addObject("Paciente",new Paciente());
                                 mv.addObject("entradaForo",new entradaForo());
                                 
                sql="select * from entrada where id_entrada="+eF.getId_entrada(); 
                     datosL2 = this.jdbcTemplate.queryForList(sql);
                                 System.out.println(datosL2);
                                 mv.addObject("Entrada",datosL2);
                                 
                return mv;
                
     }
     
     
     
     
     @RequestMapping(params="cerrar", method = RequestMethod.POST)
    public ModelAndView logout(@ModelAttribute("Paciente") Paciente p, BindingResult result,HttpServletRequest hsr, HttpServletResponse hsrl) {
           HttpSession session =hsr.getSession();
       String alert = (String)session.getAttribute("Paciente");
       System.out.println("ESTO DICE EL ALERT EN LA BIENVENIDA: "+alert);
       if (alert == null){
           return new ModelAndView("redirect:/login.htm");
       }     
          
          
             System.out.println("ADIOS PACIENTE");
            
            session.removeAttribute("Paciente");
            String alert2 = (String)session.getAttribute("Paciente");
            session.invalidate();
            
            System.out.println("ESTO OBTUVE: "+alert2);
            
            
            
            
            return new ModelAndView("redirect:/login.htm");
        
       
            
                
       
       
    }
     
     
}
