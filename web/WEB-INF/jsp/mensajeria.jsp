<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
    
    <!-- Hojas de estilos -->
    
    <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/mensajeria.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/vista_usuarios.css" />" />   
                         
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
                    <ul id="menu_nutrio">
                        <li><a class="texto_menu" href="expedientePaciente.htm">Expediente</a></li>
                        <li><a class="texto_menu" href="foro.htm">Foro</a></li>
                        <li><a class="texto_menu" href="">XXXXXX</a></li>
                        <li><a class="texto_menu" href="">XXXXXX</a></li>
                    </ul>
                </div>
            </div>

        </div>
    </div>

    <div id="contenido">
        <div class="container">
            <div id="grid">
                <div id="listado">
                    <div id="encabezado_lista">
                        <p id="sub-titulo">Mensajes</p>
                        <p><input type="checkbox" name="leido" value="leido"> Leidos<br> 
                            <input type="checkbox" name="Noleido" value="No leido">No Leidos<br></p>
                        <input type="text" placeholder="Buscar usuario">
                        <p id="numero_usuarios">Número de usuarios: ?</p>
                    </div>

                    <div id="lista">

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>

                        <div class="usuario2">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mensaje bla bla bla</p>
                            </div>
                            <div id="bolita">
                                <div id="bolita_verde"></div>
                            </div>
                        </div>


                    </div>

                </div>

                <div id="informacion">
                    <div id="destinatario">
                        <p>Nombre PrimerApellido<br> Activo(a) hace 11 minutos.</p>
                    </div>
                    <div id="cuerpo">

                    </div>
                    <div id="redaccion">
                        <input type="text" name="" id="mensaje" placeholder="Escribe un mensaje...">
                        <img src="<c:url value="/resource/imagenes/send.png"/>" alt="Enviar" class="imagen" id="enviar">
                        <img src="<c:url value="/resource/imagenes/picture.png"/>" alt="Adjuntar imagen" class="imagen" id="img_a">
                        <img src="<c:url value="/resource/imagenes/clip.png"/>" alt="Adjuntar archivo" class="imagen" id="arc_a">
                    </div>

                </div>
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
