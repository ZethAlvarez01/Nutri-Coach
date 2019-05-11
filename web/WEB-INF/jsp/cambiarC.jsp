<%-- 
    Created on : 12/11/2018, 11:07:05 PM
    Author     : Nutri-Coach
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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/recuperar.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        
        <title>Nutri-Coach</title>
    </head>
    <body>
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
                            <p>"La Técnica al Servicio de la Patria"</p>
                        </a>
                        <img src="<c:url value="/resource/imagenes/logo-ipn.jpg" />" alt="Instituto Politécnico Nacional">
                    </div>
                </div>
            </div>
            <!--Fin container-->
        </header>  
                    
        <div id="barra">
            <div class="container">
                <a href="<c:url value="/inicio.htm" />"><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>   
            </div>
        </div>
                    
        <a class="regresar" href="<c:url value="/inicio.htm" />">Regresar</a>
        
        <div class="container">
            <div id="contenido"> 
                    
                    <center><h1>Recuperar contraseña</h1>
                    <br/>
                    <p>Por favor ingresa tu nueva contraseña</p>
                    </center>
                    <div id="formulario">
                        <form:form method="POST" commandName="cambiarC">
                       <form:password path="contraseña"  placeholder="Contraseña"/>
                        <form:errors path="contraseña" />
                        </p>
                        <p>
                         <form:password showPassword="true" path="contraseña2"  placeholder="Repetir contraseña"/>
                        <form:errors path="contraseña2" />
                           <form:button id="button" class="button" type="submit">Cambiar contraseña</form:button>
                        </form:form>
                    </div>
            </div>
            <!--Fin contenido-->
        </div>
        
        
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
