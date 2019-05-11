<%-- 
    Document   : exito2
    Created on : 12/11/2018, 01:07:21 AM
    Author     : zetok
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/confirmacion.css" />" />
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
            
        <div class="container">
            <div id="contenido"> 
                    
                <center><h1>Registro completo</h1></center>
                <p>
                    ¡Bienvenido, <c:out value="${nombre}"/> !
                    Gracias por unirte a Nutri-Coach como paciente, aquí tienes la información de tu cuenta:
                </p>
                <div id="informacion">
                    <b>Número de boleta/empleado:</b> <c:out value="${no_boleta}"/>
                    <br/><br/><b>Nombre:</b> <c:out value="${nombre}"/> <c:out value="${ap_uno}"/> <c:out value="${ap_dos}"/>
                    <br/><br/><b>Sexo:</b> <c:out value="${sexo}"/> 
                    <br/><br/><b>Fecha de nacimiento:</b> <c:out value="${fecha_n}"/>
                    <br/><br/><b>Domicilio:</b> <c:out value="${domicilio}"/>
                    <br/><br/><b>Correo:</b> <c:out value="${correo}"/>
                    
                    <a id="boton" href="<c:url value="/login.htm" />">Iniciar sesión</a>
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
