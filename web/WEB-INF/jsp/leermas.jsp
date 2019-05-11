<%-- 
    Document   : Leermas
    Created on : 14/11/2018, 12:57:17 AM
    Author     : Zeth
--%>
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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/leermas.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
                
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
                <a href="<c:url value="/inicio.htm"/>"><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>    
            </div>
        </div>
                    
        <a class="regresar" href="<c:url value="/inicio.htm" />">Regresar</a>
                    
        <div id="contenido">
            <div class="container">
                
                <center>
                    <h1>Nutri-Coach</h1>
                </center>
                <p class="titulo">Planteamiento del problema</p>
                <p>En la actualidad, para toda persona es difícil organizar sus tiempos, sobre todo a la hora de comer.
                    Además de esto, en muchas ocasiones las personas aparte de no comer en horas determinadas, comen
                    cosas que no siempre son nutritivas o adecuadas para su salud. Dadas estas complicaciones, surgió
                    la necesidad de analizar el comportamiento de la comunidad de la Escuela Superior de Cómputo (ESCOM),
                    quienes, en su mayoría, no cuentan con un horario fijo de comida y consumen alimentos preparados en
                    la cafetería o en lugares aledaños a la escuela, lo que conlleva a tener una alimentación desbalanceada.
                    Y, debido a que la escuela se especializa en sistemas computacionales, la comunidad de ESCOM permanece
                    la mayor parte de su tiempo sentados. Una vez destacados estos factores y con ayuda de un nutriólogo,
                    se determinó que los miembros comunidad de la Escuela Superior de Cómputo son vulnerables a padecer
                    obesidad en cualquiera de sus grados, lo que a su vez causa en la mayoría de las personas problemas de auto aceptación.</p>
                    
                <p class="titulo">Objetivo</p>
                <p>
                    Desarrollar un prototipo de Sistema Web responsivo que sirva como herramienta para administrar, documentar y guiar el tratamiento de posibles problemas relacionados con los hábitos nutricionales de las personas. Además, se debe permitir la interacción entre usuarios y brindar el coaching especializado necesario para cada caso.
                </p>
            </div>
            <!--Fin container-->
        </div>
        <!--Fin contenido-->

        <footer>
            <div class="container">
                    <p id="visita">Visita nuestras redes sociales</p>
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
