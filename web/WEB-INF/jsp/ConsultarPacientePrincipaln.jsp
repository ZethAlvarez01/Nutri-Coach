<%-- 
    Document   : ConsultarPacientePrincipalp
    Created on : 11-may-2019, 13:03:04
    Author     : jms-m
--%>
<%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
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
      
        <link rel="stylesheet" href="<c:url value="/resource/estilos/vista_usuarios.css" />" />
         <link rel="stylesheet" href="<c:url value="/resource/estilos/barra_menu.css" />" />

        <!-- Scripts -->

        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/psicologo.js"/></script>
        <script type="text/javascript" src="resource/scrips/fecha.js"/></script> 
        <script type="text/javascript" src="resource/scrips/busqueda.js"/></script>  

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
                    <a href="cronograma.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                   <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Nutriologo">
                            <li><a class="texto_menu" href="cronograma.htm">Cronograma</a></li>
                        <li><a class="texto_menu" href="mensajeriaN.htm">Mensajes</a></li>
                       
                        <li><a class="texto_menu" href="foroN.htm">Foro</a></li>
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_empleado" placeholder="${item.no_empleado}" value="${item.no_empleado}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                   
                </div>
            </div>

        </div>
    </div>
                
                
         <div id="contenido">
        <div class="container">
            <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> Estos son tus pacientes .</h1></p>
                </c:forEach>
            <div id="grid">
                <div id="notificaciones" >
                  
                </div>
                <div id="listado">
                   <div id="encabezado_lista">
                        <p id="sub-titulo">Listado de usuarios</p>
                       <!--Barra de busqueda-->
                       <input type="search" class="filtro" data-table="order-table" placeholder="Buscar paciente">

                         <!------------------------------------------>  
                        
                        <p id="numero_usuarios">Número de usuarios: ${LongitudP}</p>  
                    </div>

                    <div id="lista">
                        <table class="order-table table"><!--se le asigna a la tabla que se quiere filtrar-->
                        <thead>                           
                            <tr>

                            <th></th>
                            <th>No. Boleta</th>
                            <th>Nombre</th>
                            <th>Primer apellido</th>
                            <th>Segundo apellido</th>
                            <th>Teléfono</th>
                            <th>Correo</th>
                            <th></th>
                            </tr>
                        </thead>
                        <c:forEach items="${ListaPacientes}" var="item"> 
                            <tbody>                
                                <tr>
                                    <div class="usuario">             

                                        <td><img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto"></td>
                                        <td>${item.no_boleta}</td>
                                        <td>${item.nombre}</td>
                                        <td>${item.ap_uno}</td>
                                        <td>${item.ap_dos}</td>
                                        <td>${item.telefono}</td>
                                        <td>${item.correo}</td>
                                        <td>
                                        <form:form method="post" commandName="Paciente">
                                            <input type="submit"  name="expediente" value="Visualizar expediente">  
                                             <form:input path="no_boleta" placeholder="${item.no_boleta}" value="${item.no_boleta}" type="hidden" />
                                        </form:form>
                                        </td>
                                    </div>

                               </tr>

                                </c:forEach>          
                            </tbody> 
                        </table>      
                                
                                
                                
                           
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
