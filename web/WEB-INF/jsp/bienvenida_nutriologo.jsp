<%-- 
    Document   : bienvenida_nutriologo
    Created on : 12-mar-2019, 22:58:36
    Author     : jms-m
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <link rel="stylesheet" href="<c:url value="/resource/estilos/bienvenida_nutriologo.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/vista_usuarios.css" />" />
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

    <div id="contenido">
        <div class="container">
            <div id="grid">
                <div id="notificaciones">
                    <a href="mensajeria.htm">
                        <img src="<c:url value="/resource/imagenes/paper.png"/>" alt="">
                    </a>
                </div>
                <div id="listado">
                    <div id="encabezado_lista">
                        <p id="sub-titulo">Listado de usuarios</p>
                        <input type="text" placeholder="Buscar usuario">
                        <p id="numero_usuarios">Número de usuarios: ?</p>
                    </div>

                    <div id="lista">

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                           <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>
                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                        <div class="usuario">
                            <img src="<c:url value="/resource/imagenes/deku.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">Nombre PrimerApellido</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                        </div>

                    </div>

                </div>

                <div id="informacion">
                    <div id="h3">
                        <h3>Informacion del paciente</h3>
                    </div>
                    <p>Nombre PrimerApellido</p>
                    <br>
                    <div id="grid_info">
                        <div class="casillas" id="resumen">
                            <h3 onclick="expandir()" style="cursor:pointer;">Expediente clínico</h3>
                            <p>Fecha de inicio</p>
                            <p>Cosas y mas cosas</p>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Reprehenderit placeat, ad, harum assumenda est quas inventore, eius dolor tenetur nesciunt libero vitae quibusdam culpa? Quo, beatae facere. Officiis, pariatur sed.</p>
                            <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Doloremque incidunt debitis asperiores cupiditate suscipit unde, libero amet quisquam beatae? Exercitationem, animi. Voluptatum sequi facilis minus quam sint suscipit,
                                atque quaerat.</p>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ducimus iusto nam natus corporis quibusdam error obcaecati qui, voluptatem ratione, omnis adipisci aperiam numquam repudiandae. Ullam eaque delectus fugit exercitationem
                                sapiente!
                            </p>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Enim molestiae ab reprehenderit doloremque repellat quod officia assumenda, a impedit vel mollitia harum provident, pariatur veritatis, eveniet non temporibus sunt sint?</p>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Labore cum esse aliquam in, recusandae maiores animi ut ipsum, vel at, ullam illum reprehenderit nobis consequuntur eaque dicta quis! Dolor, id.</p>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab consectetur, sed id sapiente eum facilis pariatur nobis! Inventore omnis voluptatem blanditiis beatae, eius vel accusantium numquam iste velit cumque quibusdam?</p>
                        </div>

                        <div class="casillas" id="graficas">
                            <h3 onclick="expandir()" style="cursor:pointer;">Gráficas</h3>
                        </div>

                        <div class="casillas" id="dieta">
                            <h3 onclick="expandir()" style="cursor:pointer;">Dieta de la semana</h3>
                        </div>

                        <div class="casillas" id="observacion">
                            <h3 onclick="expandir()" style="cursor:pointer;">Observaciones</h3>
                        </div>

                        <div class="casillas" id="historial_act">
                            <h3 onclick="expandir()" style="cursor:pointer;">Historial de actividades</h3>
                            <ul>
                                <li>Post en el foto</li>
                                <li>Actividad #1 con el psicologo</li>
                                <li>Tacho como seguida la dieta</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>Post en el foto</li>
                                <li>Actividad #1 con el psicologo</li>
                                <li>Tacho como seguida la dieta</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>Post en el foto</li>
                                <li>Actividad #1 con el psicologo</li>
                                <li>Tacho como seguida la dieta</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>Post en el foto</li>
                                <li>Actividad #1 con el psicologo</li>
                                <li>Tacho como seguida la dieta</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>Post en el foto</li>
                                <li>Actividad #1 con el psicologo</li>
                                <li>Tacho como seguida la dieta</li>
                                <li>No consumio X alimento (Fecha)</li>
                                <li>No consumio X alimento (Fecha)</li>
                            </ul>
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