<%-- 
    Document   : foro
    Created on : 15/11/2018, 01:13:11 AM
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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/foro.css" />" />
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
            <div id="cont_barra">
                <div id="imagen_barra">
                    <a href="expedientePaciente.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                     <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Paciente">
                           <li><a class="texto_menu" href="expedientePaciente.htm">Expediente</a></li>
                          <c:if test= "${item.no_cedula>0}">
                            
                        
                              
                             <li><a class="texto_menu" href="mensajeria.htm">Mensajes</a></li>
                        
                                                 
                        </c:if>
                        <c:if test= "${item.no_cedula==0}">
                            
                        
                               <li><a class="texto_menu" href="primera_cita.htm">Citas</a></li>
                             
                                                 
                        </c:if>
                        <li><a class="texto_menu" href="foro.htm">Foro</a></li>
                               
                        
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_boleta" placeholder="${item.no_boleta}" value="${item.no_boleta}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                    
                </div>
            </div>

        </div>
    </div>
                 
       
        
        <div id="contenido">
            <div class="container">
                <center>
                     
                    <h2> <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> a la sección de seguimiento psicológico</h1></p>
                </c:forEach></h2>
                </center>
                
                
                <div class="grid-container">
                    <div class="item1"><p class="titulo">Tu psicólogo ${nombrePs[0].nombre} ${nombrePs[0].ap_uno} ${nombrePs[0].ap_dos} recomienda las siguientes actividades</p>
                        <hr>
                        <c:forEach items="${Actividades}" var="item"> 
                          
                    <ul>
                       <form:form method="post" commandName="ActividadP">
                        ${item.fecha}
                        <form:button id="button" class="button" type="submit" name="ConsultarActividad" value="${item.contenido}">${item.contenido}</form:button>
                            <form:input path="id_actividad" placeholder="${item.id_actividad}" value="${item.id_actividad}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                    </div>  
                        
                        
                        
                        <div class="item2"><p class="titulo">Diario</p>
                            <hr>
                                                       
                            <p>Expresa cómo te sientes hoy </p>
                         <a class="regresar" href="<c:url value="/diario_paciente.htm" />">Diario</a>
                            
                        </div>
                        
                        
                        
                        
                        
                        
                        <div class="item3"><p class="titulo">Información del Psicólogo</p>
                            <hr>
                            <p> ${nombrePs[0].nombre} ${nombrePs[0].ap_uno} ${nombrePs[0].ap_dos}      No. cédula: ${nombrePs[0].no_cedula}</p>
                            <p> Institución: ${nombrePs[0].institucion}      Consultorio: ${nombrePs[0].consultorio}</p>
                            <p> Teléfono: ${nombrePs[0].telefono}     correo:${nombrePs[0].correo}</p>
                        </div>
                       
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
