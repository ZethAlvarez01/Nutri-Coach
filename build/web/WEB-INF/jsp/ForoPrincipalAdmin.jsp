<%-- 
    Document   : ConsultarEntrada
    Created on : 27-abr-2019, 18:30:49
    Author     : jms-m
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
                   <a href="bienvenida_admin.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
               <div id="menu">
                    <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Administrador">
                          <li><a class="texto_menu" href="bienvenida_admin.htm">Administración</a></li>
                        <li><a class="texto_menu" href="mensajeriaAdmin.htm">Mensajes</a></li>
                        <li><a class="texto_menu" href="verificacion_cuentas.htm">Solicitudes</a></li>
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_empleado" placeholder="${item.no_empleado}" value="${item.no_empleado}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                    
                    
                    
                    
                </div>
                    
                </div>
            </div>

        </div>
    </div>

                
                   <a class="regresar" href="<c:url value="/foroAdmin.htm" />">Regresar</a>
                   
          <div id="contenido">
            <div class="container">
                <center>
                     <h1>Foro de Nutri-Coach</h1>
                    <h2> <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> Este es el foro</h1></p>
                </c:forEach></h2>
                </center>
                
                
                 <div class="grid-container">
                     <div class="item1">
                         
                         
                        
                   
                        <hr>
                       
                        <table>
                                <tr>
                                   <td><strong>Fecha</strong></td>
                                   <td><strong>Titulo</strong></td>
                                   <td><strong>Contenido</strong></td> 
                                   <td><strong>COMENTARIOS</strong></td> 
                                </tr> 
                       
                                <c:forEach items="${listaEntradas}" var="item" varStatus="loop"  > 
                            
                    
                       <form:form method="post" commandName="entradaForo">
                        
                                <tr>
                                   <td>${item.fecha}</td>
                                   <td> <form:button id="button" class="button" type="submit" name="consultarEntrada" value="${item.titulo}">${item.titulo}</form:button></td>
                                   <td>${item.contenido}...</td> 
                                   <td>${contadorComentarios[loop.index]}</td> 
                                </tr>
                                
                            
                       
                        
                            <form:input path="id_entrada" placeholder="${item.entrada}" value="${item.id_entrada}" type="hidden" />
                       </form:form>
                               
                         </c:forEach> 
                            
                       </table>
                   
                        
                        
                         
                        
                      
                    </div>  
                                    
                   
                    
                 </div>
                     
                     
              </div>
            </div>
                
                
                
                
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
