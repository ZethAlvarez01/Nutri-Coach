<%-- 
    Document   : verificacion_cuentas
    Created on : 13-mar-2019, 0:13:11
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
    <link rel="stylesheet" href="<c:url value="/resource/estilos/contenido.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/contenido_verificacion.css" />" />
    <script type="text/javascript" src="resource/scrips/script.js"/></script>  
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
            <div id="cont_barra">
                <div id="imagen_barra">
                    <a href="inicio.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                    <c:forEach items="${ListaAdmin}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Administrador">
                            <li><input type="submit" class="texto_menu" name="mensajes" value="Mensajes" formaction="mensajeriaAdmin.htm"></li>
                                
                         <li><input type="submit" class="texto_menu" name="bienvenida" value="Administración" formaction="bienvenida_admin.htm"></li>
                       
                        <li><input type="submit" class="texto_menu" name="foro" value="Foro" formaction="foroAdmin.htm"></li></li>
                        <li><a class="texto_menu" href="">XXXXXX</a></li>
                        <form:input path="no_empleado" placeholder="${item.no_empleado}" value="${item.no_empleado}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                    
                    
                </div>
            </div>

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
                        <c:forEach items="${ListaN}" var="item"> 
                            <div id="info">
                                
                                <p>${item.nombre} ${item.ap_uno} ${item.ap_dos} No_Empleado: ${item.no_empleado}  No_Cedula ${item.no_cedula} 
                                puesto: <c:out value="${puesto}"/></p>
                            </div>
                             <form:form method="POST" commandName="Nutriologo">
                                <form:input path="no_cedula" placeholder="${item.no_cedula}" value="${item.no_cedula}" type="hidden" />
                            <form:button id="aprobar" type="submit" name="aprobarN" value="Aprobar">aprobar</form:button>
                            <form:button id="rechazar" type="submit" name="rechazarN" value="Rechazar" >Rechazar</form:button>
                        </form:form>
                            </c:forEach> 
                        
                                                                     
                        
                    </div>
                         <br>
                         <br>
                         <br>
                         <br>
                          

                    
                     <div class="casillas">
                                                <c:forEach items="${ListaPs}" var="item"> 
                            <div id="info">
                                
                                <p>${item.nombre} ${item.ap_uno} ${item.ap_dos} No_Empleado: ${item.no_empleado}  No_Cedula ${item.no_cedula} 
                                puesto: <c:out value="${puesto2}"/></p>
                            </div>
                             <form:form method="POST" commandName="Psicologo">
                                <form:input path="no_cedula" placeholder="${item.no_cedula}" value="${item.no_cedula}" type="hidden" />
                            <form:button id="aprobar" type="submit" name="aprobarPs" value="Aprobar">aprobar</form:button>
                            <form:button id="rechazar" type="submit" name="rechazarPs" value="Rechazar" >Rechazar</form:button>
                        </form:form>
                            </c:forEach> 
                        
                                                                     
                        
                    </div>
                    
                   

                </div>


                <div id="nutriologo" class="especialista" style="display: none;">
                    <h3>Nutriólogo</h3>
                    <br>
                    <div class="casillas">
                        <c:forEach items="${ListaN}" var="item"> 
                            <div id="info">
                                
                                <p>${item.nombre} ${item.ap_uno} ${item.ap_dos} No_Empleado: ${item.no_empleado}  No_Cedula ${item.no_cedula} 
                               </p>
                            </div>
                            
                            
                             <form:form method="POST" commandName="Nutriologo">
                                <form:input path="no_cedula" placeholder="${item.no_cedula}" value="${item.no_cedula}" type="hidden" />
                            <form:button id="aprobar" type="submit" name="aprobarN" value="Aprobar">aprobar</form:button>
                            <form:button id="rechazar" type="submit" name="rechazarN" value="Rechazar" >Rechazar</form:button>
                        </form:form>
                            
                            
                            </c:forEach> 
                        
                                                                     
                        
                    </div>

                    </div>
                
                <div id="psicologo" class="especialista" style="display: none;">
                    <h3>Psicólogo</h3>
                    <br>
                    <div class="casillas">
                                                <c:forEach items="${ListaPs}" var="item"> 
                                                   
                            <div id="info">
                                
                                <p>
                                    ${item.nombre} ${item.ap_uno} ${item.ap_dos} No_Empleado: ${item.no_empleado}  No_cedula: ${item.no_cedula}
                                 
                                  
                                </p>
                                
                            </div>
                             <form:form method="POST" commandName="Psicologo">
                                <form:input path="no_cedula" placeholder="${item.no_cedula}" value="${item.no_cedula}" type="hidden" />
                            <form:button id="aprobar" type="submit" name="aprobarPs" value="Aprobar">aprobar</form:button>
                            <form:button id="rechazar" type="submit" name="rechazarPs" value="Rechazar" >Rechazar</form:button>
                        </form:form>
                            </c:forEach> 
                        
                                                                     
                        
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
