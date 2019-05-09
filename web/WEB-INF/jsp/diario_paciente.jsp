<%-- 
    Document   : diario_paciente
    Created on : 05-may-2019, 21:49:12
    Author     : jms-m
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        
        <!-- Hojas de estilos -->
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/contenido_diario.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
                
         <!-- Scrips -->
    
        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
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
                    <a href="expedientePaciente.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                     <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Paciente">
                           <li><a class="texto_menu" href="expedientePaciente.htm">Expediente</a></li>
                          <c:if test= "${item.no_cedula>0}">
                            
                        
                              
                             <li><a class="texto_menu" href="mensajeria.htm">Mensajes</a></li>
                        
                                                 
                        </c:if>
                        <c:if test= "${item.no_cedula==0}">
                            
                        
                               <li><a class="texto_menu" href="primera_cita.htm">Citas</a></li>
                             
                                                 
                        </c:if>
                        <li><a class="texto_menu" href="foro.htm">Foro</a></li>
                               
                        
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_boleta" placeholder="${item.no_boleta}" value="${item.no_boleta}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                    
                </div>
            </div>

        </div>
    </div>
                
                   <a class="regresar" href="<c:url value="/SeguimientoPsicologico.htm" />">Regresar</a>
                   <div id="contenido">
        <div class="container">
            <h2>Mi diario <img src="<c:url value="/resource/imagenes/edit.png" />" id="editar" alt="editar">
                
            </h2>

            <p id="midiario">Escribe en este diario cualquier sentimiento o pensamiento que tengas sobre lo que ingeriste durante el día .</p>
            <div id="diario">
                <div id="hoja">
                    <label id="fechadia"></label>
                    <script>
                        fecha();
                    </script>
                    <p id="nueva_hoja">Nueva hoja</p>
                    
                    <p class="titulo" id="em">¿Cómo te sientes hoy?</p>
                    <div id="emogis">
                         <form:form method="post" commandName="diario">  
                      
                        <div id="feliz"><img  src="<c:url value="/resource/imagenes/feliz.png" />" alt="Feliz" class="emogi">
                            <p> <form:radiobutton path="sentimiento" class="Radio" value="Feliz"/><label>Feliz</label></p></div>
                        <div id="triste"><img  src="<c:url value="/resource/imagenes/triste.png" />" alt="Triste" class="emogi">
                             <p> <form:radiobutton path="sentimiento" class="Radio" value="Triste"/><label>Triste</label></p></div>
                        <div id="ansiedad"><img  src="<c:url value="/resource/imagenes/ansiedad.png" />" alt="Ansiedad" class="emogi">
                             <p> <form:radiobutton path="sentimiento" class="Radio" value="Ansioso"/><label>Ansioso</label></p></div>
                        <div id="estres"><img  src="<c:url value="/resource/imagenes/estres.png" />" alt="Estres" class="emogi">
                             <p> <form:radiobutton path="sentimiento" class="Radio" value="Estresado"/><label>Estrés</label></p></div>
                        <div id="ira"><img  src="<c:url value="/resource/imagenes/ira.png" />" alt="Ira" class="emogi">
                             <p> <form:radiobutton path="sentimiento" class="Radio" value="Enojado"/><label>Enojado</label></p></div>
                    </div> 
                    <br>
                    <br>
                    <p class="titulo">Platícame tu día:</p>
                    <br>
                    
                         <form:textarea path="contenido" id="areaTxt" rows="10" cols="50" name="comentario" placeholder="¡Exprésate!" />
                         <p>  <form:errors path="contenido"/> </p>
                   
                    
                    
                   
                    
                   <form:input path="fecha" value=""  type="hidden" />
                    <input   type="submit" name="guardarHoja" value="Guardar" onclick="fecha2()">
                   </form:form>
                  
                    
                 </div>
                        
                    
                    
           
                <div id="historial">
                    <c:forEach items="${hojas}" var="item">
                    <div class="dias">
                        <div id="dia">
                            <form:form method="post" commandName="diario">  
                            <label>
                                ${item.fecha}
                                                              
                                <br>
                                Hoy me siento ${item.sentimiento}
                            </label>
                        </div>
                        
                           
                            <form:button id="explorar" type="submit" name="consultarHoja" value="Explorar">Explorar</form:button></td>
                        <form:input path="id_hojas" placeholder="${item.id_hojas}" value="${item.id_hojas}" type="hidden" />
                       </form:form>
                        
                    </div>
                    </c:forEach> 
                         


                </div>
            </div>
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
