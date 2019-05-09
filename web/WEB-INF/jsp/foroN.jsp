<%-- 
    Document   : foro
    Created on : 15/11/2018, 01:13:11 AM
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
                    <a href="cronograma.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                     <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Nutriologo">
                            <li><a class="texto_menu" href="cronograma.htm">Cronograma</a></li>
                        <li><a class="texto_menu" href="mensajeriaN.htm">Mensajes</a></li>
                        <li><a class="texto_menu" href="bienvenida_nutriologo.htm">Pacientes</a></li>
                        
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_empleado" placeholder="${item.no_empleado}" value="${item.no_empleado}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                   
                   
                </div>
            </div>

        </div>
    </div>    
         <a class="regresar" href="<c:url value="/nuevaEntradaN.htm" />">Nueva entrada</a>

        
        <div id="contenido">
            <div class="container">
                <center>
                    <h1>Foro de Nutri-Coach</h1>
                    <h2> <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> Este es el foro</h1></p>
                </c:forEach></h2>
                </center>
                
                
                <div class="grid-container">
                    <div class="item1"><p class="titulo">Recomendaciones</p>
                        <hr>
                        <ul>
                            <li>Motivacionales: <c:out value="${salida1}"/></li>
                            <li>Preparacion alimentos: <c:out value="${salida2}"/></li>
                            <li>Beneficios alimentos: <c:out value="${salida3}"/></li>
                            <li>Deportes: <c:out value="${salida4}"/></li>
                            <li>Medicamentos: <c:out value="${salida5}"/></li>
                            <li>Salud: <c:out value="${salida6}"/></li>
                        </ul> 
                    </div>  
                         <div class="item2"><p class="titulo">Entradas recientes</p>
                            <hr>
                                                       
                           
                           
                            <c:forEach items="${listaEntradas}" var="item"> 
                          
                    <ul>
                       <form:form method="post" commandName="entradaForo">
                         ${item.fecha}
                        <form:button id="button" class="button" type="submit" name="consultarEntrada" value="${item.titulo}">${item.titulo}</form:button>
                            <form:input path="id_entrada" placeholder="${item.entrada}" value="${item.id_entrada}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                            
                        </div>
                        
                        
                        
                        
                        
                        
                        <div class="item3"><p class="titulo">Historial de comentarios</p>
                            <hr>
                            <table>
                                <tr>
                                   <td><strong>Fecha</strong></td>
                                   <td><strong>Titulo de Entrada</strong></td>
                                   
                                </tr>
                                <c:forEach items="${FechaComentarios}" var="comentarios">
                                    
                                <tr>
                                   <td>${comentarios.fecha}</td>
                                   <c:set var = "entrada"  value = "${comentarios.id_entrada}"/>
                                <c:forEach items="${NombreEntrada}" var="nombreEntrada">
                                    <c:set var = "titulo"  value = "${nombreEntrada.id_entrada}"/>
                                    
                                    <c:if test ="${entrada == titulo}">
                                       
                                        
                                        
                                        <form:form method="post" commandName="entradaForo">
                        
                      <td>  <form:button id="button" class="button" type="submit" name="consultarEntrada" value="${nombreEntrada.titulo}">${nombreEntrada.titulo}</form:button> </td>

                            <form:input path="id_entrada" placeholder="${nombreEntrada.id_entrada}" value="${nombreEntrada.id_entrada}" type="hidden" />
                       </form:form>
                                        
                                </c:if>
                                </c:forEach>
                                   
                                   
                                  
                                </tr>
                                
                                    
                                    
                                </c:forEach> 
                                
                            </table>
                        </div>
                        <a class="regresar" href="<c:url value="/ForoPrincipalN.htm" />">Ver foro</a>
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
