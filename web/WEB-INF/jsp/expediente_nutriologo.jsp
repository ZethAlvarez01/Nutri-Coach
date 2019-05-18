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
                        <form:form method="post" commandName="Nutriologo">
                              <li><a class="texto_menu" href="cronograma.htm">Cronograma</a></li>
                             <li><a class="texto_menu" href="mensajeriaN.htm">Mensajes</a></li>
                        <li><a class="texto_menu" href="ConsultarPacientePrincipaln.htm">Pacientes</a></li>
                        <li><a class="texto_menu" href="foroN.htm">Foro</a></li>
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_empleado" placeholder="${item.no_empleado}" value="${item.no_empleado}"  id="no_empleado"  type="hidden"/>
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

                        <input path="act_f" type="radio" name="act_f" value="1" > Si
                        <input path="act_f" type="radio" name="act_f" value="0" > No<br>

                    </li>
                    <li>¿Qué tipo de actividad física? <form:input path="tipo_act" type="text" /></li>
                    <li>¿Con que frecuencia realiza actividad física? <form:input path="frecuencia" type="number" min="0" max="7" value="0" /></li>
                  
              
                    <li>¿Tiene algún padecimiento o enfermedad, cuál? <form:input  path="padecimiento" type="text" /></li>
                    <li>¿Tiene antecedentes familiares con padecimientos o enfermedades crónicas o hereditarias, cuál? <form:input  path="antec_hf" type="text" /></li>
                    <li>¿Consume tabaco?

                        <input path="tabaco" type="radio" name="tabaco" value="1"> Si
                        <input path="tabaco" type="radio" name="tabaco" value="0" > No<br>

                    </li>
                    <li>¿Con qué frecuencia?  <form:input  path="frec_tabaco" type="number" min="0" value="0" /></li>
                    <li>¿Consume bebidas alcohólicas?

                        <input path="alcohol" type="radio" name="alcohol" value="1" > Si
                        <input path="alcohol" type="radio" name="alcohol" value="0" > No<br>

                    </li>
                    <li>¿Con qué frecuencia? <form:input  path="frec_alcohol" type="number" min="0" value="0" /></li>
                </ul>
                
                
                
            </div>
            <div id="Ginecologico" class="expediente_c" style="display: none;">
                <h2>Estado ginecológico</h2>
                <ul>
                    <li>¿Lleva una vida sexual activa?

                        <form:radiobutton path="act_sex"  name="act_s" value="1" /> Si
                        <form:radiobutton path="act_sex"  name="act_s" value="0" /> No<br>

                    </li>
                    <li>¿Se encuentra en estado de gestación?

                        <form:radiobutton path="edo_gestacion"  name="gestacion" value="1" /> Si
                        <form:radiobutton path="edo_gestacion"  name="gestacion" value="0" /> No<br>

                    </li>
                    <li>¿Cuánto tiempo lleva gestando? <form:input  path="T_Gestacion" type="number" min="0" value="0" /></li>
                    <li>¿Usa algún método anticonceptivo, cuál? <form:input  path="m_anticonceptivo" type="text" /></li>
                    <li>¿Lleva alguna terapia de reemplazo hormonal?

                        <form:radiobutton path="terapia_rh"  name="terapia_h" value="1" /> Si
                        <form:radiobutton path="terapia_rh"  name="terapia_h" value="0" /> No<br>

                    </li>
                    <li>¿Cuál? <form:input  path="TipoTerapia" type="text" /></li>
                    <li>Dosis <form:input  path ="dosis" type="text" /></li>
                </ul>
            </div>
            <div id="Fisica" class="expediente_c" style="display: none;">
                <div id="contenido_fisica">
                    <div id="exploracion">
                        <h2>Exploración fisica</h2>
                        <ul>
                            <li>Peso <form:input  path="peso" type="number" min="0" value="0"  step=".01" required="required"/></li>
                            <li>Estatura <form:input  path="talla" type="number" min="0" value="0" step=".01" required="required"/></li>
                            <li>Temperatura <form:input  path="temperatura" type="number" min="0" value="0" step=".01" required="required"/></li>
                            <li>P/A <form:input  path="tension_art" type="text" required="required"/></li>
                            <li>F/C <form:input  path="fec_cardiaca" type="text" required="required"/></li>
                        </ul>
                    </div>
                    <div id="medidas">
                        <h3>Medidas antropométricas</h3>
                        <ul>
                            <li>Cuello <form:input  path="cuello" type="number" name="" id="" min="0" value="0" step=".01" required="required"/></li>
                            <li>Tórax <form:input  path="torax" type="number" name="" id=""min="0" value="0" step=".01" required="required"/></li>
                            <li>Brazo <form:input  path="brazo"type="number" name="" id="" min="0" value="0" step=".01" required="required"/></li>
                            <li>Antebrazo <form:input  path="antebrazo"type="number" name="" id="" min="0" value="0" step=".01" required="required"/></li>
                            <li>Abdomen <form:input  path="abdomen" type="number" name="" id="" min="0" value="0" step=".01" required="required"/></li>
                            <li>Cadera <form:input  path="cadera" type="number" name="" id="" min="0" value="0" step=".01"  required="required"/></li>
                            <li>Muslo <form:input  path="mulso" type="number" name="" id="" min="0" value="0" step=".01" required="required"/></li>
                            <li>Pierna <form:input  path="pierna" type="number" name="" id="" min="0" value="0" step=".01" required="required"/></li>
                            <li>Aspectos generales <form:textarea  path="aspect_grls" min="0" value="0" required="required"/></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="Indicadores" class="expediente_c" style="display: none;">
                <h2>Indicadores dietéticos</h2>
                <ul>
                    <li>¿Se ha sometido algún tratamiento nutricional anteriormente?

                        <input path="tratamiento_n" type="radio" name="tratamiento_n" value="1" > Si
                        <input  path="tratamiento_n" type="radio" name="tratamiento_n" value="0" > No<br>

                    </li>
                    <li>¿Qué tipo de tratamiento? <form:input path="tratamient" type="text" /></li>
                    <li>¿Por cuánto tiempo? <form:input  path="tiempo" type="text" /></li>
                    <li>¿Cuántas ingestas de comida tiene al día? <form:input  path="CantidadIngesta" type="number" name="" id="" min="0" value="0" /></li>
                    <li>¿Consume golosinas entre comidas?

                        <form:radiobutton path="postre"  name="golosina" value="1" /> Si
                        <form:radiobutton path="postre"  name="golosina" value="0" /> No<br>
                       

                    </li>
                    <li>¿Cuáles? <form:input path="golosinas" type="text" /></li>
                    <li>¿Tiene algún horario de comida?

                        <form:radiobutton path="HorarioComida"  name="horario_c" value="1" /> Si
                        <form:radiobutton path="HorarioComida"  name="horario_c" value="0" /> No<br>

                    </li>
                    <li>¿Cuáles? <form:input  path="HorariosComida" type="text" /></li>
                    <li>Tipo Alimentos de su gusto<br><br>

                        <form:checkbox path="dulce"  name="alimento1" value="dulce" /> Dulce<br>
                        <form:checkbox path="amarga"  name="alimento2" value="amarga"/> Amarga<br>
                        <form:checkbox path="salada"  name="alimento3" value="salada"/> Salada<br>
                        <form:checkbox path ="picante"  name="alimento4" value="picante"/> Picante<br>
                        <form:checkbox path="acida"  name="alimento5" value="acida"/> Acida<br>

                    </li>
                    <li>¿Tiene alergias a algun alimento, cúal? <form:input  path="alergias" type="text" /></li>
                </ul>
            </div>
            <div id="Diagnostico" class="expediente_c" style="display: none;">
                <h2>Diagnostico clínico</h2>

                <h3>Observaciones</h3>
                <form:textarea  path="observaciones" id="obs" name="observaciones_dc" />

                    

                <h3>Recomendaciones</h3>
                <form:textarea   path="recomendaciones" id="recom" name="recomendaciones_dc" />
                        
                   
            </div>
             
            
            
               <form:input path="no_boleta" placeholder="${datosPaciente[0].no_boleta}" value="${datosPaciente[0].no_boleta}" type="hidden" />
              <form:input path="fecha_ini" id="fecha" value="" type="hidden" />
           <input  class="cita" type="submit" name="GuardarExpediente" value="Guardar" onclick="fecha2()">
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