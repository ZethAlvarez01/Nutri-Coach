<%-- 
    Document   : expedientePaciente
    Created on : 16/11/2018, 02:15:14 AM
    Author     : Zeth
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        
        <!-- Hoajs de estilos -->
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/expediente.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
        <!-- Scrips -->
    
        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>  
        <script type="text/javascript" href="<c:url value="/resource/scrips/script.js" />"/></script>
        
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
                    <a href="inicio.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                     <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Paciente">
                              <li><input type="submit" class="texto_menu" name="mensajeria" value="Mensajeria" formaction="mensajeria.htm"></li>
                                
                         
                       
                        <li><input type="submit" class="texto_menu" name="foro" value="Foro" formaction="foro.htm"></li>
                        <li><a class="texto_menu" href="">XXXXXXXXXX</a></li>
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
                <c:forEach items="${datos}" var="dato">
                    <h1>¡Hola, <c:out value="${dato.nombre}"/>!</h1>
                </c:forEach>
               
                <div class="grid-container">
                    <div class="observaciones">
                    <p class="titulo">Observaciones</p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>
                    <div class="estadisticas">
                    <p class="titulo">Tu progreso</p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>
                    <div class="expediente">
                    <p class="titulo">Expediente</p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>
                    <div class="recomendacion">
                    <p class="titulo">Recomendación diaria</p>
                    <p id="tipo">Tipo: <c:out value="${respuesta}"/></p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>
                    <div class="dieta">
                    <p class="titulo">Tu dieta de hoy</p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>
                    <div class="historial">
                    <p>Historial</p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>     
                </div>
            </div>
            <!--Fin container-->
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
