package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import models.Conexion;
import models.ConsultaEvolucion;
import models.Foro;
import models.Nutriologo;

import models.Login;
import models.LoginValidar;

import models.NeuralNet.Implementacion;
import models.NeuralNet.libMatrices;
import models.NeuralNet.Capa_neuronas;
import models.NeuralNet.Crear_RN;
import models.NeuralNet.Tratamiento;
import models.Paciente;
import models.Psicologo;
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
 * @author Nutri-Coach
 */
@Controller

public class InicioController {
    
    private int[] topology={42,5,6};
    
    
    private JdbcTemplate jdbcTemplate;
    private LoginValidar LoginValidar;

    public InicioController() {
        this.LoginValidar=new LoginValidar();                       // INSTANCIA DE LA CLASE LoginValidar
        Conexion conn=new Conexion();
        this.jdbcTemplate=new JdbcTemplate(conn.conectar());
    }
    
    
    
   ////////////////////////////////////////////////////
    
    //PAGINA DE INICIO
    
    @RequestMapping(value="inicio.htm",method=RequestMethod.GET)     // SE UTILIZARÁ LA VISTA inicio y se aplicará el método GET
    
    public ModelAndView inicio(){                
        ModelAndView mv=new ModelAndView();                  // SE CREA EL MODELO
        mv.setViewName("inicio");                            // SE NOMBRA EL MODELO
        mv.addObject("Login",new Login());                   // SE AGREGA EL OBJETO Login AL MODELO
        return mv;

       
    }
    
    @RequestMapping(value="inicio.htm", method=RequestMethod.POST)
    public ModelAndView inicio(
                        @ModelAttribute("Login") @Validated Login lo,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.LoginValidar.validate(lo, resultado);
       
      // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES   
        
        if(resultado.hasErrors()){
            //volvemos al formulario porque los datos ingresados son incorrectos
            
            ModelAndView mv=new ModelAndView();
            mv.setViewName("inicio");
            mv.addObject("Login",new Login());
            return mv;
        }else{
               //El usuario ingreso bien los datos
                String sql="select nombre from paciente where contraseña='"+ // Busca el usuario como paciente en la base de datos
                lo.getPass()+"' and no_boleta="+lo.getUsuario()+";";
                //List datos=this.jdbcTemplate.queryForList(sql);
                List datosL=this.jdbcTemplate.queryForList(sql);
//                System.out.println(datosL.get(0));
                String sql2="select estatus from paciente where contraseña='"+lo.getPass()+"' and no_boleta="+lo.getUsuario()+";"; // Extraemos su estatus
                        List estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                if(datosL.size()>=1){
                                                          // Si se encuentra el paciente se procede a verificar su estatus
                                                          
                    switch (estatus.get(0).toString().charAt(9)) {                     // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                           // Si se encuentra en 2 su cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                            // El estatus 1 significa usuario activo y se procede a ingresar a su bienvenida
                                {
                                   ModelAndView mv=new ModelAndView();   // Creación del modelo
                    mv.setViewName("expedientePaciente");  //nombra al modelo
                    mv.addObject("datos",datosL);         // agrega al modelo el objeto datos
                    
                    sql="select*from evolucion where id_exp=(select id_expediente from expediente where no_boleta='"+lo.getUsuario()+"');";
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
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }                                      
                    
            }
                else{
                        sql="select * from psicologo where contraseña='"+          // Procede a buscar al usuario en la base como psicologo
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from psicologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                        
                        
                        if(datosL.size()>=1){                                      // Si se encuentra el usuario se procede a verificar su estatus
                            switch (estatus.get(0).toString().charAt(9)) {         // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                            
                                case '2':                                         // si su estatus es 2 la cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                               // el estatus 1 refiere a usuario activo y se ingresa a su bienvenida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("cronogramaPsicologo");                                            // Nombra al modelo
                                    mv.addObject("datos",datosL);         // agrega al modelo el objeto datos
                                    return mv;
                                    /* ArrayList<Capa_neuronas> neural_net;
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
                            mv.addObject("nombre","Psicologo");*/
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                        
                   }
                        
                        else{
                            
                        sql="select * from nutriologo where contraseña='"+                // Se procede a buscar al usuario como nutriologo en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from nutriologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                       // System.out.println(estatus.get(0).toString().charAt(9));
                            
                        if(datosL.size()>=1){                                               // Si se encuentra el  usuario se procede a verificar su estatus
                            // Se verifica el estatus del usuario
                            switch (estatus.get(0).toString().charAt(9)) {            // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                                  // Si su estatus es 2 se tiene una cuenta suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                                 // Si su estatus es 1 signifa cuenta activa y se procede a acceder a su bienbenida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("cronograma");                                            // Nombra al modelo
                                    mv.addObject("datos",datosL);         // agrega al modelo el objeto datos
                                    return mv;
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                            
                        
                        
                        
                        }
                        else{ 
                            
                        sql="select * from administrador where contraseña='"+                // Se procede a buscar al usuario como administrador en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  

                        if(datosL.size()>=1){                                               // Si se encuentra el  usuario se procede a acceder a su vista de bienvenida
                         ModelAndView mv=new ModelAndView();                                // Creación del modelo
                         mv.setViewName("bienvenida_admin");                                            // Nombra al modelo
                         System.out.println("Pasando datos"); 
      
                         sql=" select * from paciente where estatus <> 4 and estatus and estatus <> 0 order by ap_uno asc;";

            
      
              datosL=this.jdbcTemplate.queryForList(sql);
                  
             mv.addObject("ListaP",datosL);       // SE AGREGA EL OBJETO LISTA DE PACIENTES AL MODELO     
            
             System.out.println(datosL);
              System.out.println(datosL.size());
      
             mv.addObject("LongitudP",datosL.size()); 
             
      
       
             mv.addObject("Paciente",new Paciente());     // SE AGREGA EL OBJETO PACIENTE AL MODELO
          
             
              sql2=" select * from nutriologo where estatus <> 4 and estatus <> 0 order by ap_uno asc;";

            
      
             List datosL2=this.jdbcTemplate.queryForList(sql2);
                  
             mv.addObject("ListaN",datosL2);       // SE AGREGA EL OBJETO LISTA DE NUTRIOLOGOS AL MODELO     
            
             System.out.println(datosL2);
             System.out.println(datosL2.size());
      
             mv.addObject("LongitudN",datosL2.size()); 
             
      
       
             mv.addObject("Nutriologo",new Nutriologo());     // SE AGREGA EL OBJETO NUTRIOLOGO AL MODELO
                  
       
             
             
             String sql3=" select * from psicologo where estatus <> 4 and estatus <> 0 order by ap_uno asc;";

            
      
             List datosL3=this.jdbcTemplate.queryForList(sql3);
                  
             mv.addObject("ListaPs",datosL3);       // SE AGREGA EL OBJETO LISTA DE PSICOLOGOS AL MODELO     
            
             System.out.println(datosL3);
             
             System.out.println(datosL3.size());
      
             mv.addObject("LongitudPs",datosL3.size()); 
      
       
             mv.addObject("Psicologo",new Psicologo());     // SE AGREGA EL OBJETO PSICOLOGO AL MODELO
             
             
       
                         return mv;
                        
                        
                        }
                        else{                                                           // Si no se encuentra el usuario en la base de datos se procede a regresar a la pagina de inicio
                         ModelAndView mv=new ModelAndView();                            // Creación del modelo
                        mv.setViewName("inicio");                                       // Nombra al modelo
                        mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                        return mv;    
                        }

                                 
                             
                        }
                            
                       
                        }
                }
        }
    }
    
    
    @RequestMapping(value="login.htm",method=RequestMethod.GET)         // SE UTILIZARÁ LA VISTA login y se aplicará el método GET
    public ModelAndView formularioLogin(){
        ModelAndView mv=new ModelAndView();                            // SE CREA EL MODELO
        mv.setViewName("login");                                       // SE NOMBRA EL MODELO
        mv.addObject("Login",new Login());                             // SE AGREGA EL OBJETO login AL MODELO
        return mv;
    }
  
    @RequestMapping(value="login.htm",method=RequestMethod.POST)
    public ModelAndView formularioLogin(
                        @ModelAttribute("Login") Login lo,
                        BindingResult resultado,
                        SessionStatus status
                        ){
        this.LoginValidar.validate(lo, resultado);
          // SE VERIFICA QUE NUESTRO FORMULARIO NO CONTENGA ERRORES
        if(resultado.hasErrors()){
            
            //volvemos al formulario porque los datos ingresados son incorrectos
            ModelAndView mv=new ModelAndView();
            mv.setViewName("login");
            mv.addObject("Login",new Login());
            return mv;
        }else{
             //El usuario ingreso bien los datos
               String sql="select nombre from paciente where contraseña='"+ // Busca el usuario como paciente en la base de datos
                lo.getPass()+"' and no_boleta="+lo.getUsuario()+";";
                //List datos=this.jdbcTemplate.queryForList(sql);
                List datosL=this.jdbcTemplate.queryForList(sql);
//                System.out.println(datosL.get(0));
                String sql2="select estatus from paciente where contraseña='"+lo.getPass()+"' and no_boleta="+lo.getUsuario()+";"; // Extraemos su estatus
                        List estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                
                if(datosL.size()>=1){                                                     // Si se encuentra en la base se procede a acceder a su vista de bienvenida
                    
                                                          
                    switch (estatus.get(0).toString().charAt(9)) {                     // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                           // Si se encuentra en 2 su cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                            // El estatus 1 significa usuario activo y se procede a ingresar a su bienvenida
                                {
                                   ModelAndView mv=new ModelAndView();   // Creación del modelo
                    mv.setViewName("expedientePaciente");  //nombra al modelo
                    mv.addObject("datos",datosL);         // agrega al modelo el objeto datos
                    
                    sql="select*from evolucion where id_exp=(select id_expediente from expediente where no_boleta='"+lo.getUsuario()+"');";
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
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }                                      
                    
                    
                    
                    
                    
                    
                    
                    
            }else{
               
                sql="select * from psicologo where contraseña='"+          // Procede a buscar al usuario en la base como psicologo
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from psicologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                    
                    if(datosL.size()>=1){                                                 // Si se encuentra en la base se procede a acceder a su vista de Bienvenida
                   
                        switch (estatus.get(0).toString().charAt(9)) {         // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                            
                                case '2':                                         // si su estatus es 2 la cuenta se encuentra suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                               // el estatus 1 refiere a usuario activo y se ingresa a su bienvenida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("cronogramaPsicologo");                                            // Nombra al modelo
                                    mv.addObject("datos",datosL);         // agrega al modelo el objeto datos
                                    return mv;
                                    /* ArrayList<Capa_neuronas> neural_net;
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
                            mv.addObject("nombre","Psicologo");*/
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                        
                        
                        
                        
                        
            }
                    else{
                  sql="select * from nutriologo where contraseña='"+                // Se procede a buscar al usuario como nutriologo en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                        sql2="select estatus from nutriologo where contraseña='"+lo.getPass()+"' and no_empleado="+lo.getUsuario()+";"; // Extraemos su estatus
                        estatus=this.jdbcTemplate.queryForList(sql2);
                        System.out.println("ESOS FUERON LOS DATOS"); 
                        System.out.println(estatus);
                      
                
                if(datosL.size()>=1){                                                         // Si se enceuntra se procede a mostrar su vista de Bienvenida
                    
                    
                    // Se verifica el estatus del usuario
                            switch (estatus.get(0).toString().charAt(9)) {            // Si su estatus es 0 se notifica como en espera de ser aprobado
                                case '0':
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("espera_Aprobacion");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '2':                                                  // Si su estatus es 2 se tiene una cuenta suspendida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("suspendido");                                            // Nombra al modelo
                                    return mv;
                                }
                                 case '4':                                           // Si se encuentra en 4 su cuenta se encuentra rechazado
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("Rechazado");                                            // Nombra al modelo
                                    return mv;
                                }
                                case '1':                                                 // Si su estatus es 1 signifa cuenta activa y se procede a acceder a su bienbenida
                                {
                                    ModelAndView mv=new ModelAndView();                                // Creación del modelo
                                    mv.setViewName("cronograma");                                            // Nombra al modelo
                                    mv.addObject("datos",datosL);         // agrega al modelo el objeto datos
                                    return mv;
                                }
                                default:
                                {
                                    
                                    ModelAndView mv=new ModelAndView();                            // Creación del modelo
                                    mv.setViewName("inicio");                                       // Nombra al modelo
                                    mv.addObject("Login",new Login());                              // Agrega el objeto Login al modelo
                                    return mv;
                                }
                            }
                    
                    
                    
                    
                    
                    
            }
                else{   
                   sql="select * from administrador where contraseña='"+                // Se procede a buscar al usuario como administrador en la base de datos
                        lo.getPass()+"' and no_empleado="+lo.getUsuario()+";";
                        datosL=this.jdbcTemplate.queryForList(sql);
                        System.out.println(datosL);  
                
                if(datosL.size()>=1){                                                         // Si se enceuntra se procede a mostrar su vista de Bienvenida
                    ModelAndView mv=new ModelAndView();                                      // Creación del modelo
                    mv.setViewName("bienvenida_admin");                                                  // Nombra al modelo
                    return mv;
            }
                else{                                                                    // En caso de no encontrar al usuario en la base ded datos se procede a regresar a la pagina de inicio
                    ModelAndView mv=new ModelAndView();                                     // Creación del modelo
                mv.setViewName("login");                                                     // Se nombra el modelo
                mv.addObject("Login",new Login());                                           // Se agrega el objeto Login al modelo
                return mv;
                }


                       
                     
                }
                
                
                    }
                    
                    
                    
                    
                
            }
        }
    }
    
}
                