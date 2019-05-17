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
            <h1>Cargar nuevo expediente para ${Paciente}</h1>

            <div class="barra_expediente">
                <input type="button" class="expediente_t" onclick="openExpediente('Historial')" value="Historial clínico"/>
                <input type="button" class="expediente_t" onclick="openExpediente('Ginecologico')" value="Estado ginecológico"/>
                <input type="button" class="expediente_t" onclick="openExpediente('Fisica')" value="Exploración fisica"/>
                <input type="button"  class="expediente_t" onclick="openExpediente('Indicadores')" value="Indicadores dietéticos"/>
                    <input type="button" class="expediente_t" onclick="openExpediente('Diagnostico')" value="Diagnostico clínico"/>
            </div>


            <div id="Historial" class="expediente_c">
                <h2>Historial clínico</h2>
                <ul>
                    <li>¿Realiza algún tipo de actividad física?

                        <input type="radio" name="act_f" value="1"> Si
                        <input type="radio" name="act_f" value="0"> No<br>

                    </li>
                    <li>¿Qué tipo de actividad física? <input type="text"></li>
                    <li>¿Con que frecuencia realiza actividad física? <input type="text"></li>
                    <li>¿Tiene algún padecimiento o enfermedad, cuál? <input type="text"></li>
                    <li>¿Tiene antecedentes familiares con padecimientos o enfermedades crónicas o hereditarias, cuál? <input type="text"></li>
                    <li>¿Consume tabaco?

                        <input type="radio" name="tabaco" value="1"> Si
                        <input type="radio" name="tabaco" value="0"> No<br>

                    </li>
                    <li>¿Con qué frecuencia? <input type="text"></li>
                    <li>¿Consume bebidas alcohólicas?

                        <input type="radio" name="alcohol" value="1"> Si
                        <input type="radio" name="alcohol" value="0"> No<br>

                    </li>
                    <li>¿Con qué frecuencia? <input type="text"></li>
                </ul>
            </div>
            <div id="Ginecologico" class="expediente_c" style="display: none;">
                <h2>Estado ginecológico</h2>
                <ul>
                    <li>¿Lleva una vida sexual activa?

                        <input type="radio" name="act_s" value="1"> Si
                        <input type="radio" name="act_s" value="0"> No<br>

                    </li>
                    <li>¿Se encuentra en estado de gestación?

                        <input type="radio" name="gestacion" value="1"> Si
                        <input type="radio" name="gestacion" value="0"> No<br>

                    </li>
                    <li>¿Cuánto tiempo lleva gestando? <input type="text"></li>
                    <li>¿Usa algún método anticonceptivo, cuál? <input type="text"></li>
                    <li>¿Lleva alguna terapia de reemplazo hormonal?

                        <input type="radio" name="terapia_h" value="1"> Si
                        <input type="radio" name="terapia_h" value="0"> No<br>

                    </li>
                    <li>¿Cuál? <input type="text"></li>
                    <li>Dosis <input type="text"></li>
                </ul>
            </div>
            <div id="Fisica" class="expediente_c" style="display: none;">
                <div id="contenido_fisica">
                    <div id="exploracion">
                        <h2>Exploración fisica</h2>
                        <ul>
                            <li>Peso <input type="number"></li>
                            <li>Talla <input type="number"></li>
                            <li>Temperatura <input type="text"></li>
                            <li>P/A <input type="text"></li>
                            <li>F/C <input type="text"></li>
                        </ul>
                    </div>
                    <div id="medidas">
                        <h3>Medidas antropométricas</h3>
                        <ul>
                            <li>Cuello <input type="number" name="" id=""></li>
                            <li>Tórax <input type="number" name="" id=""></li>
                            <li>Brazo <input type="number" name="" id=""></li>
                            <li>Antebrazo <input type="number" name="" id=""></li>
                            <li>Abdomen <input type="number" name="" id=""></li>
                            <li>Cadera <input type="number" name="" id=""></li>
                            <li>Muslo <input type="number" name="" id=""></li>
                            <li>Pierna <input type="number" name="" id=""></li>
                            <li>Aspectos generales <textarea></textarea></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="Indicadores" class="expediente_c" style="display: none;">
                <h2>Indicadores dietéticos</h2>
                <ul>
                    <li>¿Se ha sometido algún tratamiento nutricional anteriormente?

                        <input type="radio" name="tratamiento_n" value="1"> Si
                        <input type="radio" name="tratamiento_n" value="0"> No<br>

                    </li>
                    <li>¿Qué tipo de tratamiento? <input type="text"></li>
                    <li>¿Por cuánto tiempo? <input type="text"></li>
                    <li>¿Cuántas ingestas de comida tiene al día? <input type="number" name="" id=""></li>
                    <li>¿Consume golosinas entre comidas?

                        <input type="radio" name="golosinas" value="1"> Si
                        <input type="radio" name="golosinas" value="0"> No<br>

                    </li>
                    <li>¿Cuáles? <input type="text"></li>
                    <li>¿Tiene algún horario de comida?

                        <input type="radio" name="horario_c" value="1"> Si
                        <input type="radio" name="horario_c" value="0"> No<br>

                    </li>
                    <li>¿Cuáles? <input type="text"></li>
                    <li>Tipo Alimentos de su gusto<br><br>

                        <input type="checkbox" name="alimento1" value="dulce"> Dulce<br>
                        <input type="checkbox" name="alimento2" value="amarga"> Amarga<br>
                        <input type="checkbox" name="alimento3" value="salada"> Salada<br>
                        <input type="checkbox" name="alimento4" value="picante"> Picante<br>
                        <input type="checkbox" name="alimento5" value="acida"> Acida<br>

                    </li>
                    <li>¿Tiene alergias a algun alimento, cúal? <input type="text"></li>
                </ul>
            </div>
            <div id="Diagnostico" class="expediente_c" style="display: none;">
                <h2>Diagnostico clínico</h2>

                <h3>Observaciones</h3>
                <textarea id="obs" name="observaciones_dc">

                    </textarea>

                <h3>Recomendaciones</h3>
                <textarea id="recom" name="recomendaciones_dc">
                        
                    </textarea>
            </div>
            <input type="button" value="Guardar"/>
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