<%-- 
    Document   : diario_psicologo_solo_paciente
    Created on : 14-mar-2019, 0:09:54
    Author     : jms-m
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/contenido_diario_psicologo_solo.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/barra_menu.css" />" />

        <!-- Scripts -->

        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>  
     <!--    <script type="text/javascript" src="resource/scrips/psicologo.js"/></script>-->
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
     <div id="barra">
        <div class="container">
            <div id="cont_barra">
                <div id="imagen_barra">
                    <a href="cronogramaPsicologo.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                    <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Psicologo">
                              <li><a class="texto_menu" href="cronogramaPsicologo.htm">Cronograma</a></li>
                        <li><a class="texto_menu" href="mensajeriaPs.htm">Mensajes</a></li>
                        <li><a class="texto_menu" href="foroPs.htm">Foro</a></li>
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_empleado" placeholder="${item.no_empleado}" value="${item.no_empleado}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                   
                </div>
            </div>

        </div>
    </div>
                                        <a class="regresar" href="<c:url value="/bienvenida_psicologo.htm" />">Regresar</a>
    <div id="contenido">
        <div class="container">
            <div id="grid">
                <div id="informacion">
                    <h2>
                        Notas del paciente
                    </h2>
                    <p id="nombre_paciente">${nombreP[0].nombre} ${nombreP[0].ap_uno} ${nombreP[0].ap_dos}</p>
                    <label>Mes</label>
                    <select class="historial_dias" id="mes">
                        <option>Enero</option>
                        <option>Febrero</option>
                        <option>Marzo</option>
                        <option>Abril</option>
                        <option>Mayo</option>
                        <option>Junio</option>
                        <option>Julio</option>
                        <option>Agosto</option>
                        <option>Septiembre</option>
                        <option>Octubre</option>
                        <option>Noviembre</option>
                        <option>Diciembre</option>
                    </select>
                    
                    <label>Hoja</label>
                    <select class="historial_dias" id="hoja" onchange="diario()">
                        <c:forEach items="${hojas}" var="hoja">
                             <option>${hoja.fecha}</option>
                             
                        </c:forEach> 
                       
                            
                    </select>

                    <div id="diario">
                        <div class="dias">
                            <p id="fecha"></p>
                            <script>
                                fecha();
                            </script>
                            <br>
                            <p id="titulo">Hoy Lunes...</p>
                            <br>
                            <div id="escrito">
                               
                                <p id="escritoT">
                                    ${hojas[x].contenido}
                                </p>
                                <br>
                                <img id="imagen_adjunta" src="">
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="observaciones">
                    <h3>Observaciones</h3>
                    <textarea id="areaTxt" rows="10" cols="50" name="comentario" placeholder="Observaciones del especialista"></textarea>
                    <div id="boton">
                        Guardar
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
