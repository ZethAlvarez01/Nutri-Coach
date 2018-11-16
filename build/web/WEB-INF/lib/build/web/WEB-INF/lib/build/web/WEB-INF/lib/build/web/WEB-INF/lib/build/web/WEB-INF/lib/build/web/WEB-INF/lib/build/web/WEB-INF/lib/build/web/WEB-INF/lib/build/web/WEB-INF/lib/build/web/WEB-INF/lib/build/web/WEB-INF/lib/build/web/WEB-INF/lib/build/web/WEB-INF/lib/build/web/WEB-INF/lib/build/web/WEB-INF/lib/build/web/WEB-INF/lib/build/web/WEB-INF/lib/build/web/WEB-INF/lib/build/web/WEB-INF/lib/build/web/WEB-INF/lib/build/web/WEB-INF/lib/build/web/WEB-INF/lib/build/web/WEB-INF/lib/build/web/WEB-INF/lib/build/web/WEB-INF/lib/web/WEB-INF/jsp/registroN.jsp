<%-- 
    Document   : tiporegistro
    Created on : 11/11/2018, 01:10:16 AM
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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/registroP.css" />" />
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
                            "La Técnica al Servicio de la Patria"
                        </a>
                        <img src="<c:url value="/resource/imagenes/logo-ipn.jpg" />" alt="Instituto Politécnico Nacional">
                    </div>
                </div>
            </div>
            <!--Fin container-->
        </header>    
                    
        <div id="barra"></div>  
        
        <div id="contenido">
            <div class="container">    
                <a href="<c:url value="/inicio.htm" />"><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                <center><h1>Registro para el nutriólogo</h1>
                    <br/>
                    <p>Favor de introducir sus datos correspondientes para complerar su registro:</p>
                </center>
                <div id="formulario">
                    <form:form>
                        <form:input path="No_Cedula_P" placeholder="No_Cedula_P" />
                        <form:input path="No_Empleado" placeholder="No_Empleado" />
                        <form:input path="Nombre" placeholder="Nombre"/>
                        <form:input path="Apellido_P" placeholder="Apellido_P"/>
                        <form:input path="Apellido_M" placeholder="Apellido_M"/>
                        <form:input path="Institucion" placeholder="Institucion"/>
                        <form:textarea path="Domicilio_C" placeholder="Domicilio_C"/>
                        <form:input path="Telefono"  placeholder="Telefono"/>
                        <form:input path="Correo" type="email" placeholder="Correo"/>
                        <form:input path="Contrasena" type="password" placeholder="Contraseña"/>
                        <input type="checkbox" class="check" value="first_checkbox" required>
                        <center>
                            <a href="<c:url value="/tyc.htm" />"><p style="color: black;">He leido y acepto los términos y condiciones.</p></a>
                        </center>
                        <br/>
                        <form:button id="button" type="submit">Registrar</form:button>
                    </form:form>
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
