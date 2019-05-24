<%-- 
    Document   : expedientePaciente
    Created on : 16/11/2018, 02:15:14 AM
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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        <!-- Hoajs de estilos -->
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/expediente.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
        <!-- Scrips -->
    
        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>  
      
        
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
                    <c:if test= "${empty datosPreferencia}"></c:if>
                    <c:if test= "${not empty datosPreferencia}">
                <div id="menu">
                     <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Paciente">
                             <c:if test= "${item.no_cedula>0}">
                            
                        
                              
                             <li><a class="texto_menu" href="mensajeria.htm">Mensajes</a></li>
                        
                                                 
                        </c:if>
                        <c:if test= "${item.no_cedula==0}">
                            
                        
                               <li><a class="texto_menu" href="primera_cita.htm">Citas</a></li>
                             
                                                 
                        </c:if>
                            
                             
                          <li><a class="texto_menu" href="foro.htm">Foro</a></li>
                          <c:if test= "${item.no_cedulap>0}">
                            
                        
                               <li><a class="texto_menu" href="SeguimientoPsicologico.htm">Psicólogo</a></li>
                             
                                                 
                        </c:if>
                            
                          
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_boleta" placeholder="${item.no_boleta}" value="${item.no_boleta}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                    
                </div>
                </c:if>
                
                
                
            </div>

        </div>
    </div>

        <div id="contenido">
            <div class="container">
                <c:forEach items="${datos}" var="dato">
                    <h1>¡Hola, <c:out value="${dato.nombre}"/>!</h1>
                </c:forEach>
         
           
                 <c:if test= "${empty datosPreferencia}">
                      <p><h1>Espera un momento!</h1></p
                            <p><h1>Para vivir la experiencia completa de Nutri-Coach debes proporcionar la siguiente información </h1></p>
                            <form:form method="post" commandName="preferencias">
                            <p>¿Cuál de los siguientes temas te resulta de intéres?</p>
                             <form:checkbox path="suplementos"   value="1"  /> Suplementos Alimenticios
                             <form:checkbox path="motivacional"   value="1"  /> Motivación
                             <form:checkbox path="preparacionA"   value="1"  /> Preparación de Alimentos
                             <form:checkbox path="beneficiosA"   value="1"  /> Beneficios de los Alimentos
                             <form:checkbox path="deportes"   value="1"  /> Deportes
                             <form:checkbox path="medicamentos"   value="1"  /> Medicamentos
                             <form:checkbox path="salud"   value="1"  /> Salud
                             <br><form:errors path="suplementos" />
                             <input  class="cita" type="submit" name="GuardarPreferencias" value="Guardar" >       
                            </form:form>
                     
                 </c:if>

                 <c:if test= "${not empty datosPreferencia}">
                 <c:forEach items="${datos}" var="dato"> 
                 <c:if test= "${dato.no_cedula==0}">
                           
                            <p><h1>No cuentas con un nutriólogo</h1></p
                            <p><h1>Por favor ingresa a la sección de citas para</h1></p>
                        <form:form method="post" commandName="Paciente">
                              
                       <input type="submit" class="cita"  name="primera_cita" value="Citas" >                       
                       </form:form>
                            
                        </c:if>
                </c:forEach>
                        <c:forEach items="${datos}" var="dato">
                            <c:if test= "${dato.no_cedula>0}">
                            
                       <div class="grid-container">
                    <div class="observaciones">
                    <p class="titulo">Próximas citas</p>
                    <div class="contenido-E">
                        
                        <c:if test= "${empty citaNutriologo}">
                            <p>No hay citas programadas con el Nutriólogo<p>   
                        </c:if>
                        <c:if test= "${not empty citaNutriologo}">
                        <p><b>Cita con Nutriólogo</b><p> 
                         <p><b>Fecha:</b> ${citaNutriologo[0].fecha} <b>Horario:</b> ${citaNutriologo[0].horario}<p>    
                        </c:if>
                        <c:if test= "${empty citaPsicologo}">
                            <p>No hay citas programadas con el Psicólogo<p>   
                        </c:if>
                        <c:if test= "${not empty citaPsicologo}">
                        <p><b>Cita con Psicólogo</b><p> 
                         <p><b>Fecha:</b> ${citaPsicologo[0].fecha} <b>Horario:</b> ${citaPsicologo[0].horario}<p>    
                        </c:if>                           
                         
                    </div>
                    </div>
                    <div class="estadisticas">
                    <p class="titulo">Tu progreso</p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>
                    <div class="expediente">
                    <p class="titulo">Expediente</p>
                    <div class="contenido-E">
                         <c:if test= "${expedienteActivo==1}">
                                           
                                           
                                           <form:form method="post" commandName="expediente">
                          
                       <input type="submit" class="texto_menu" name="HistorialExpedientePaciente" value="Expediente general">
                        <form:input path="no_boleta" placeholder="${datos[0].no_boleta}" value="${datos[0].no_boleta}" type="hidden" />
                       </form:form>
                                           
                                           
                                       </c:if>
                        
                       <c:if test= "${expedienteActivo==0}">
                                                       
                        No disponible.
                       </c:if> 
                      
                    </div>
                    </div>
                    <div class="recomendacion">
                    <p class="titulo">Recomendación diaria</p>
                    <c:if test= "${empty respuesta}">
                                       <div class="contenido-E">
                        No disponible.
                    </div>   
                    </c:if><c:if test= "${not empty respuesta}">
                         <p id="tipo">Tipo: <c:out value="${respuesta}"/></p>
                    <div class="contenido-E">
                      
                    </div>             
                    </c:if>
                    
                    </div>
                    <div class="dieta">
                    <p class="titulo">Tu dieta de hoy</p>
                    <div class="contenido-E">
                        No disponible.
                    </div>
                    </div>
                 
                    
                    
                    <div class="historial">
                    <p>Información del Nutriólogo</p>
                    <div class="contenido-E">
                       
                          
                        <p> ${nombreN[0].nombre} ${nombreN[0].ap_uno} ${nombreN[0].ap_dos}     <b> No. cédula:</b> ${nombreN[0].no_cedula}</p>
                        <p> <b>Institución:</b> ${nombreN[0].institucion}   </p>   
                            <p> <b>Consultorio:</b> ${nombreN[0].consultorio}</p>
                            <p> <b>Teléfono:</b> ${nombreN[0].telefono}     <b>correo:</b>${nombreN[0].correo}</p>
                    </div>
                    </div>   
                    
                </div>
            </div>
            <!--Fin container-->
                            
                        </c:if>
           
                        </c:forEach>    
                </c:if> 
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
