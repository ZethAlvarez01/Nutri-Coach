<%-- 
    Document   : expediente_nutriologo
    Created on : 16-may-2019, 12:37:43
    Author     : jms-m
--%>

<!DOCTYPE html>
<%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        
        <!-- Hojas de estilos -->
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/foro.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
         <link rel="stylesheet" href="<c:url value="/resource/estilos/estilo_expediente.css" />" />
    
    <!--Hoja de estilo-->
     <script type="text/javascript" src="resource/scrips/script.js"/></script>  
     <script type="text/javascript" src="resource/scrips/expediente_script.js"/></script>  
<script type="text/javascript" src="resource/scrips/barra_script.js"/></script>  
  <script type="text/javascript" src="resource/scrips/fecha.js"/></script>
   

    <title>Nutri-Coach</title>
    </head>
    <body onscroll="bajar()">
         <header>
            <div class="container">
                <div id="pleca">
                    <div id="logoSEP">
                        <a href="https://www.gob.mx/sep">
                            <img src="<c:url value="/resource/imagenes/logo-sep.png" />" alt="Secretaría de Educación Pública">
                        </a>
                    </div>
                    <div id="logoIPN">
                        <a href="https://www.ipn.mx">
                            <p>Instituto Politécnico Nacional</p>
                            "La Técnica al Servicio de la Patria"
                        </a>
                        <img src="<c:url value="/resource/imagenes/logo-ipn.jpg" />" alt="Instituto Politécnico Nacional">
                    </div>
                </div>
            </div>
            <!--Fin container-->
        </header>    
                    
        <div id="barra">
        <div class="container">
            <div id="cont_barra">
                <div id="imagen_barra">
                    <a href="cronograma.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                            <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Paciente">
                             <c:if test= "${item.no_cedula>0}">
                            
                        
                              
                             <li><a class="texto_menu" href="mensajeria.htm">Mensajes</a></li>
                        
                                                 
                        </c:if>
                        <c:if test= "${item.no_cedula==0}">
                            
                        
                               <li><a class="texto_menu" href="primera_cita.htm">Citas</a></li>
                             
                                                 
                        </c:if>
                            
                             
                          <li><a class="texto_menu" href="foro.htm">Foro</a></li>
                          <c:if test= "${item.no_cedulap>0}">
                            
                        
                               <li><a class="texto_menu" href="SeguimientoPsicologico.htm">Psicólogo</a></li>
                             
                                                 
                        </c:if>
                            
                          
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_boleta" placeholder="${item.no_boleta}" value="${item.no_boleta}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                </div>
            </div>

        </div>
    </div>

    <div id="contenido">
        <div class="container">
            <h1>Cargar nuevo expediente para ${datosPaciente[0].nombre} ${datosPaciente[0].ap_uno} ${datosPaciente[0].ap_dos}</h1>

            <div class="barra_expediente">
                <input type="button" class="expediente_t" onclick="openExpediente('Historial')" value="Historial clínico"/>
                <input type="button" class="expediente_t" onclick="openExpediente('Ginecologico')" value="Estado ginecológico"/>
                <input type="button" class="expediente_t" onclick="openExpediente('Fisica')" value="Exploración fisica"/>
                <input type="button"  class="expediente_t" onclick="openExpediente('Indicadores')" value="Indicadores dietéticos"/>
                    <input type="button" class="expediente_t" onclick="openExpediente('Diagnostico')" value="Diagnostico clínico"/>
            </div>

             <form:form method="post" commandName="expediente">
            <div id="Historial" class="expediente_c">
                <h2>Historial clínico</h2>
                <ul>
                    <li>¿Realiza algún tipo de actividad física?
                        
                        
                          <c:if test= "${datosExpediente[0].act_f == 'true'}">
                            
                             <input path="act_f" type="radio" name="act_f" value="1" disabled="true" checked="true"> Si
                        <input path="act_f" type="radio" name="act_f" value="0" disabled="true" > No<br>                
                        </c:if>
                          <c:if test= "${datosExpediente[0].act_f == 'false'}">
                            
                        <input path="act_f" type="radio" name="act_f" value="1" disabled="true" > Si
                        <input path="act_f" type="radio" name="act_f" value="0" disabled="true" checked="true"> No<br>        
                        </c:if> 
                        
                    
                         
                        

                    </li>
                    <li>¿Qué tipo de actividad física? <form:input path="tipo_act" type="text" value="${datosExpediente[0].tipo_act}" disabled="true"/></li>
                    <li>¿Con que frecuencia realiza actividad física? <form:input path="frecuencia" type="number" min="0" max="7"  value="${datosExpediente[0].frecuencia}" disabled="true" /></li>
                  
              
                    <li>¿Tiene algún padecimiento o enfermedad, cuál? <form:input  path="padecimiento" type="text" value="${datosExpediente[0].padecimiento}" disabled="true"/></li>
                    <li>¿Tiene antecedentes familiares con padecimientos o enfermedades crónicas o hereditarias, cuál? <form:input  path="antec_hf" type="text" value="${datosExpediente[0].antec_hf}" disabled="true"/></li>
                    <li>¿Consume tabaco?
                        
                        
                        
                        <c:if test= "${datosExpediente[0].tabaco == 'true'}">
                            
                        
                                
                            <input path="tabaco" type="radio" name="tabaco"  value="${datosExpediente[0].tabaco}" disabled="true" checked="true"> Si
                        <input path="tabaco" type="radio" name="tabaco"  value="${datosExpediente[0].tabaco}" disabled="true"> No<br>
                                                 
                        </c:if>
                          <c:if test= "${datosExpediente[0].tabaco == 'false'}">
                            
                         <input path="tabaco" type="radio" name="tabaco"  value="${datosExpediente[0].tabaco}" disabled="true"> Si
                         <input path="tabaco" type="radio" name="tabaco"  value="${datosExpediente[0].tabaco}" disabled="true" checked="true"> No<br>
                             
                                                 
                        </c:if>

                       

                    </li>
                    <li>¿Con qué frecuencia?  <form:input  path="frec_tabaco" type="number" min="0"  value="${datosExpediente[0].frec_tabaco}" disabled="true"/></li>
                    <li>¿Consume bebidas alcohólicas?
   
                        <c:if test= "${datosExpediente[0].alcohol == 'true'}">
                            
                        
                            <input path="alcohol" type="radio" name="alcohol"  value="${datosExpediente[0].alcohol}" disabled="true" checked="true"> Si
                            <input path="alcohol" type="radio" name="alcohol"  value="${datosExpediente[0].alcohol}" disabled="true" > No<br>
                                                 
                        </c:if>
                          <c:if test= "${datosExpediente[0].alcohol == 'false'}">
                            
                              <input path="alcohol" type="radio" name="alcohol"  value="${datosExpediente[0].alcohol}" disabled="true" > Si
                              <input path="alcohol" type="radio" name="alcohol"  value="${datosExpediente[0].alcohol}" disabled="true" checked="true"> No<br>
                                                 
                        </c:if>

                      

                    </li>
                    <li>¿Con qué frecuencia? <form:input  path="frec_alcohol" type="number" min="0" value="${datosExpediente[0].frec_alcohol}" disabled="true" /></li>
                </ul>
                
                
                
            </div>
            <div id="Ginecologico" class="expediente_c" style="display: none;">
                <h2>Estado ginecológico</h2>
                <ul>
                    <li>¿Lleva una vida sexual activa?

                         <c:if test= "${datosExpediente[0].act_sex == 'true'}">
                            
                             <input path="act_sex" type="radio" name="act_s" value="${datosExpediente[0].act_sex}" disabled="true" checked="true"> Si
                              
                          <input path="act_sex"  type="radio" name="act_s" value="${datosExpediente[0].act_sex}" disabled="true" > No<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].act_sex == 'false'}">
                            
                        <input path="act_sex"  type="radio" name="act_s" value="${datosExpediente[0].act_sex}" disabled="true" > Si
                              
                          <input path="act_sex" type="radio" name="act_s" value="${datosExpediente[0].act_sex}" disabled="true" checked="true" > No<br>                      
                        </c:if>
                        
                        
                       

                    </li>
                    <li>¿Se encuentra en estado de gestación?
                                <c:if test= "${datosExpediente[0].edo_gestacion == '1'}">
                            
                             <input path="edo_gestacion" type="radio" name="gestacion" value="${datosExpediente[0].edo_gestacion}" disabled="true" checked="true"> Si
                              
                          <input path="edo_gestacion" type="radio" name="gestacion" value="${datosExpediente[0].edo_gestacion}" disabled="true" > No<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].edo_gestacion == '0'}">
                            
                        <input path="edo_gestacion" type="radio" name="gestacion" value="${datosExpediente[0].edo_gestacion}" disabled="true" > Si
                              
                              
                        <input path="edo_gestacion" type="radio" name="gestacion" value="${datosExpediente[0].edo_gestacion}" disabled="true" checked="true" > No<br>                      
                        </c:if>
                       

                    </li>
                    <li>¿Cuánto tiempo lleva gestando? <form:input  path="T_Gestacion" type="number" min="0" value="${datosExpediente[0].T_Gestacion}" disabled="true"/></li>
                    <li>¿Usa algún método anticonceptivo, cuál? <form:input  path="m_anticonceptivo" type="text" value="${datosExpediente[0].m_anticonceptivo}" disabled="true"/></li>
                    <li>¿Lleva alguna terapia de reemplazo hormonal?

                        
                        
                       <c:if test= "${datosExpediente[0].terapia_rh == '1'}">
                            
                             <input path="terapia_rh" type="radio" name="terapia_h" value="${datosExpediente[0].terapia_rh}"   disabled="true" checked="true"> Si
                              
                             <input path="terapia_rh" type="radio" name="terapia_h" value="${datosExpediente[0].terapia_rh}"   disabled="true" > No<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].terapia_rh == '0'}">
                            
                           <input path="terapia_rh" type="radio" name="terapia_h" value="${datosExpediente[0].terapia_rh}"   disabled="true" > Si
                              
                              
                           <input path="terapia_rh" type="radio"  name="terapia_h" value="${datosExpediente[0].terapia_rh}"   disabled="true" checked="true"> No<br>                      
                        </c:if> 
                        
                        
                        
                        
                     

                    </li>
                    <li>¿Cuál? <form:input  path="TipoTerapia" type="text" value="${datosExpediente[0].TipoTerapia}" disabled="true"/></li>
                    <li>Dosis <form:input  path ="dosis" type="text" value="${datosExpediente[0].dosis}" disabled="true" /></li>
                </ul>
            </div>
            <div id="Fisica" class="expediente_c" style="display: none;">
                <div id="contenido_fisica">
                    <div id="exploracion">
                        <h2>Exploración fisica</h2>
                        <ul>
                            <li>Peso <form:input  path="peso" type="number" min="0" value="${datosExpediente[0].peso}" disabled="true"  step=".01" required="required" /></li>
                            <li>Estatura <form:input  path="talla" type="number" min="0" value="${datosExpediente[0].talla}" disabled="true" step=".01" required="required"/></li>
                            <li>Temperatura <form:input  path="temperatura" type="number" min="0" value="${datosExpediente[0].temperatura}" disabled="true" step=".01" required="required"/></li>
                            <li>P/A <form:input  path="tension_art" type="text" required="required" value="${datosExpediente[0].tension_art}" disabled="true"/></li>
                            <li>F/C <form:input  path="fec_cardiaca" type="text" required="required" value="${datosExpediente[0].frec_cardiaca}" disabled="true"/></li>
                        </ul>
                    </div>
                    <div id="medidas">
                        <h3>Medidas antropométricas</h3>
                        <ul>
                            <li>Cuello <form:input  path="cuello" type="number" name="" id="" min="0" value="${datosExpediente[0].cuello}" disabled="true" step=".01" required="required"/></li>
                            <li>Tórax <form:input  path="torax" type="number" name="" id=""min="0" value="${datosExpediente[0].torax}" disabled="true" step=".01" required="required"/></li>
                            <li>Brazo <form:input  path="brazo"type="number" name="" id="" min="0" value="${datosExpediente[0].brazo}" disabled="true" step=".01" required="required"/></li>
                            <li>Antebrazo <form:input  path="antebrazo"type="number" name="" id="" min="0" value="${datosExpediente[0].antebrazo}" disabled="true" step=".01" required="required"/></li>
                            <li>Abdomen <form:input  path="abdomen" type="number" name="" id="" min="0" value="${datosExpediente[0].abdomen}" disabled="true" step=".01" required="required"/></li>
                            <li>Cadera <form:input  path="cadera" type="number" name="" id="" min="0" value="${datosExpediente[0].cadera}" disabled="true" step=".01"  required="required"/></li>
                            <li>Muslo <form:input  path="mulso" type="number" name="" id="" min="0" value="${datosExpediente[0].muslo}" disabled="true" step=".01" required="required"/></li>
                            <li>Pierna <form:input  path="pierna" type="number" name="" id="" min="0" value="${datosExpediente[0].pierna}" disabled="true" step=".01" required="required"/></li>
                            <li>Aspectos generales <form:textarea  path="aspect_grls" min="0"  required="required" placeholder="${datosExpediente[0].aspect_grles}" disabled="true"/></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="Indicadores" class="expediente_c" style="display: none;">
                <h2>Indicadores dietéticos</h2>
                <ul>
                    <li>¿Se ha sometido algún tratamiento nutricional anteriormente?

                         <c:if test= "${datosExpediente[0].tratamiento_n== '1'}">
                            
                             <input path="tratamiento_n" type="radio" name="tratamiento_n" value="${datosExpediente[0].tratamiento_n}"   disabled="true" checked="true"> Si
                              
                             <input path="tratamiento_n" type="radio" name="tratamiento_n" value="${datosExpediente[0].tratamiento_n}"  disabled="true" > No<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].tratamiento_n == '0'}">
                            
                           <input path="tratamiento_n" type="radio" name="tratamiento_n" value="${datosExpediente[0].tratamiento_n}"   disabled="true" > Si
                              
                              
                           <input path="tratamiento_n" type="radio" name="tratamiento_n" value="${datosExpediente[0].tratamiento_n}"  disabled="true" checked="true"> No<br>                      
                        </c:if> 
                       
                    </li>
                    <li>¿Qué tipo de tratamiento? <form:input path="tratamient" type="text" value="${datosExpediente[0].tratamient}" disabled="true"/></li>
                    <li>¿Por cuánto tiempo? <form:input  path="tiempo" type="text" value="${datosExpediente[0].tiempo}" disabled="true"/></li>
                    <li>¿Cuántas ingestas de comida tiene al día? <form:input  path="CantidadIngesta" type="number" name="" id="" min="0"  value="${datosExpediente[0].cantidadIngesta}" disabled="true"/></li>
                    <li>¿Consume golosinas entre comidas?

                        
                        
                         <c:if test= "${datosExpediente[0].postre == '1'}">
                            
                             <input path="postre" type="radio"  name="golosina"  value="${datosExpediente[0].postre}" disabled="true"checked="true"> Si
                              
                             <input path="postre" type="radio" name="golosina"  value="${datosExpediente[0].postre}" disabled="true" disabled="true" > No<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].postre == '0'}">
                            
                           <input path="postre" type="radio" name="golosina"  value="${datosExpediente[0].postre}" disabled="true"  disabled="true" > Si
                              
                              
                           <input path="postre" type="radio" name="golosina"  value="${datosExpediente[0].postre}" disabled="true" checked="true"> No<br>                      
                        </c:if> 
                        
                        
                    

                    </li>
                    <li>¿Cuáles? <form:input path="golosinas" type="text" value="${datosExpediente[0].golosinas}" disabled="true"/></li>
                    <li>¿Tiene algún horario de comida?

                         <c:if test= "${datosExpediente[0].HorarioComida == '1'}">
                            
                             <input path="HorarioComida" type="radio" name="horario_c"  value="${datosExpediente[0].HorarioComida}" disabled="true" checked="true"> Si
                              
                          <input path="HorarioComida" type="radio" name="horario_c"  value="${datosExpediente[0].HorarioComida}" disabled="true" > No<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].HorarioComida == '0'}">
                            
                              <input path="HorarioComida" type="radio"  name="horario_c"  value="${datosExpediente[0].HorarioComida}" disabled="true" > Si
                              
                          <input path="HorarioComida" type="radio" name="horario_c"  value="${datosExpediente[0].HorarioComida}" disabled="true" checked="true" > No<br>                      
                        </c:if>
                        
                        
                        
                        
                       

                    </li>
                    <li>¿Cuáles? <form:input  path="HorariosComida" type="text" value="${datosExpediente[0].HorariosComida}" disabled="true"/></li>
                    <li>Tipo Alimentos de su gusto<br><br>
                         <c:if test= "${datosExpediente[0].dulce == 'true'}">
                            
                             <input type="checkbox" name="alimento1" value="${datosExpediente[0].dulce}" checked="true" disabled="true"> Dulce<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].dulce == 'false'}">
                            
                              <input type="checkbox" name="alimento1" value="${datosExpediente[0].dulce}"  disabled="true"> Dulce<br>                   
                        </c:if> 

                          <c:if test= "${datosExpediente[0].amarga == 'true'}">
                            
                             <input type="checkbox" path="amarga"  name="alimento2" value="${datosExpediente[0].amarga}" checked="true" disabled="true"> Amarga<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].amarga == 'false'}">
                            
                              <input type="checkbox" path="amarga"  name="alimento2" value="${datosExpediente[0].amarga}"   disabled="true"> Amarga<br>                   
                        </c:if> 

                       <c:if test= "${datosExpediente[0].salada == 'true'}">
                            
                             <input type="checkbox" path="salada"  name="alimento3" value="${datosExpediente[0].salada}" checked="true" disabled="true"> Salada<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].salada == 'false'}">
                            
                              <input type="checkbox" path="salada"  name="alimento3" value="${datosExpediente[0].salada}"   disabled="true"> Salada<br>                   
                        </c:if> 

                         <c:if test= "${datosExpediente[0].picante == 'true'}">
                            
                             <input type="checkbox" path ="picante"  name="alimento4" value="${datosExpediente[0].picante}" checked="true" disabled="true"> Picante<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].picante == 'false'}">
                            
                              <input type="checkbox" path ="picante"  name="alimento4" value="${datosExpediente[0].picante}"   disabled="true"> Picante<br>                   
                        </c:if> 

                          <c:if test= "${datosExpediente[0].acida == 'true'}">
                            
                             <input type="checkbox" path ="picante"  path="acida"  name="alimento5" value="${datosExpediente[0].acida}" checked="true" disabled="true"> Acida<br>                      
                        </c:if>
                          <c:if test= "${datosExpediente[0].acida == 'false'}">
                            
                              <input type="checkbox" path ="picante" path="acida"  name="alimento5" value="${datosExpediente[0].acida}"   disabled="true"> Acida<br>                   
                        </c:if> 

                      

                    </li>
                    <li>¿Tiene alergias a algun alimento, cúal? <form:input  path="alergias" type="text" value="${datosExpediente[0].alergias}" disabled="true"/></li>
                </ul>
            </div>
            <div id="Diagnostico" class="expediente_c" style="display: none;">
                <h2>Diagnostico clínico</h2>

                <h3>Observaciones</h3>
                <form:textarea  path="observaciones" id="obs" name="observaciones_dc" placeholder="${datosExpediente[0].observaciones}" disabled="true"/>

                    

                <h3>Recomendaciones</h3>
                <form:textarea   path="recomendaciones" id="recom" name="recomendaciones_dc" placeholder="${datosExpediente[0].recomendaciones}" disabled="true"/>
                        
                   
            </div>
             
            
            
          
             </form:form>
            
            
            
        </div>
    </div>

    <!--Fin contenido-->
    <footer>
            <div class="container">
                <center>
                    <p id="visita">Visita nuestras redes sociales</p>
                </center>
                <hr>
                <ul id="redes">
                    <li>
                        <a href="https://www.facebook.com/escomipnmx/">
                            <img src="<c:url value="/resource/imagenes/iconos/facebook.png" />" />
                        </a>
                    </li>
                    <li>
                        <a href="https://twitter.com/escomunidad">
                            <img src="<c:url value="/resource/imagenes/iconos/twitter.png" />" />
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/ipn_oficial/">
                            <img src="<c:url value="/resource/imagenes/iconos/instagram.png" />" />
                        </a>
                    </li>
                </ul>
                <!--Fin Redes sociales-->   
                <p>© 2018 ESCOMPROJECT - Todos Los Derechos Reservados</p>
                <br/>
                <p>
                    <a class="hiperfooter" href="<c:url value="/tyc.htm" />">Términos y condiciones</a>
                    <a  class="hiperfooter" href="<c:url value="/politicas.htm" />">Políticas de privacidad</a>
                </p>
                <br/>
                
                <!--Fin Modal Términos y condiciones-->
            </div>
        </footer>
</body>
</html>