<%-- 
    Document   : login
    Created on : 18/11/2018, 02:00:06 AM
    Author     : Zeth
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,
              user-scalable=no"/>
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resource/estilos/style.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resource/estilos/tyc.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resource/estilos/login.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resource/estilos/footer.css" />" />
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
                        <a href="<c:url value="/inicio.htm" />"><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
            </div>
        </div> 
        
         <div id="contenido">
            <div class="container">
                
                <div id="caja">
                    <center>
                        <h1>Inicio de sesión</h1>
                    </center>
                    <form:form method="POST" commandName="Login">
                        <form:input id="usuario" path="Usuario" placeholder="No. de boleta o empleado"/> 
                        <form:errors path="Usuario"/>
                        <form:input id="pass" path="Pass" type="password" placeholder="Contraseña"/>
                        <form:errors path="Pass"/>
                        <form:button id="button" type="submit">Iniciar sesión</form:button>
                        <hr>
                        <center>
                        <p class="hiperlogin">¿No tienes una cuenta?</p><p>  <a href="<c:url value="/preregistro.htm" />">Regístrate </a></p>
                        <p class="hiperlogin" id="olvide">  ¿Olvidaste tu contraseña?</p><p>  <a href="<c:url value="/recuperarC.htm" />">Recuperar contraseña</a></p>
                        </center>
                    </form:form>
                </div>
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
