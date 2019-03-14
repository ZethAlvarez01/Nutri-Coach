<%-- 
    Document   : verificacion_cuentas
    Created on : 13-mar-2019, 0:13:11
    Author     : jms-m
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/contenido.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/contenido_verificacion.css" />" />
    <script type="text/javascript" href="<c:url value="/resource/scrips/script.js" />"/></script>  
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
            <a href="inicio.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
        </div>
    </div>
    <!--Fin barra-->
    <div id="contenido">
        <div id="grid">
            <div class="menu_t">
                <h5 id="menu_t">Nuevos especialistas</h5>

                <button class="boton todos" onclick="TypeEspe(event, 'todos')">Todos</button>
                <button class="boton nutriologo" onclick="TypeEspe(event, 'nutriologo')">Nutriólogo</button>
                <button class="boton psicologo" onclick="TypeEspe(event, 'psicologo')">Psicólogo</button>
            </div>

            <div class="pestanas_menus">
                <h2>Aprobar especialista nuevo</h2>
                <p id="solicitud">Estas son las solicitudes de especialistas que esperan ser aprobados para poder trabajar con Nutri-Coach. <br>Tu aceptas o rechazas dichas solicitudes.</p>
                <div id="todos" class="especialista" style="display: block;">
                    <h3>Solicitudes de especialistas</h3>
                    <br>
                    <div class="casillas">
                        <div id="info">

                        </div>
                        <div id="aprobar">
                            Aprobar
                        </div>
                        <div id="rechazar">
                            Rechazar
                        </div>
                    </div>
                    <div class="casillas">
                        <div id="info">

                        </div>
                        <div id="aprobar">
                            Aprobar
                        </div>
                        <div id="rechazar">
                            Rechazar
                        </div>
                    </div>

                </div>


                <div id="nutriologo" class="especialista" style="display: none;">
                    <h3>Nutriólogo</h3>
                    <br>
                    <div class="casillas">
                        <div id="info"></div>
                        <div id="aprobar">
                            Aprobar
                        </div>
                        <div id="rechazar">
                            Rechazar
                        </div>

                    </div>
                    <div class="casillas">
                        <div id="info"></div>
                        <div id="aprobar">
                            Aprobar
                        </div>
                        <div id="rechazar">
                            Rechazar
                        </div>

                    </div>
                </div>
                <div id="psicologo" class="especialista" style="display: none;">
                    <h3>Psicólogo</h3>
                    <br>
                    <div class="casillas">
                        <div id="info"></div>
                        <div id="aprobar">
                            Aprobar
                        </div>
                        <div id="rechazar">
                            Rechazar
                        </div>

                    </div>
                </div>

                <h3 id="info_g">Información general</h3>
                <br>

                <div class="informacion">
                    <div id="especialidad">
                        <label>Nombre</label>
                    </div>
                    <div id="curriculum">
                        <label>Contenido general</label>
                    </div>
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
