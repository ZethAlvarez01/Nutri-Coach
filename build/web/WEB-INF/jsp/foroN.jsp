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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/foro.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
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
                    <ul id="menu_nutrio">
                        <li><a class="texto_menu" href="cronograma.htm">Cronograma</a></li>
                        <li><a class="texto_menu" href="bienvenida_nutriologo.htm">Pacientes</a></li>
                        <li><a class="texto_menu" href="mensajeriaN.htm">Mensajes</a></li>
                        <li><a class="texto_menu" href="">Dietas</a></li>
                    </ul>
                </div>
            </div>

        </div>
    </div>    
        <a class="regresar" href="<c:url value="/inicio.htm" />">Regresar</a>
        
        <div id="contenido">
            <div class="container">
                <center>
                    <h1>Foro de Nutri-Coach</h1>
                    <h2>${nombre}</h2>
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
                            <ul>
                                <li>Entrada 1</li>
                                <li>Entrada 2</li>
                                <li>Entrada 3</li>
                                <li>Entrada 4</li>
                                <li>Entrada 5</li>
                            </ul> 
                        </div>
                        <div class="item3"><p class="titulo">Historial</p>
                            <hr>
                            <table>
                                <tr>
                                   <td><strong>Fecha</strong></td>
                                   <td><strong>Titulo</strong></td>
                                   <td><strong>Comentarios</strong></td> 
                                </tr>
                                <tr>
                                   <td>18/03/1995</td>
                                   <td>Comida rica</td>
                                   <td>25</td> 
                                </tr>
                                <tr>
                                   <td>02/03/1996</td>
                                   <td>Deportes</td>
                                   <td>35</td> 
                                </tr>
                                <tr>
                                   <td>30/03/1998</td>
                                   <td>Suplementos y mas</td>
                                   <td>21</td> 
                                </tr>
                                <tr>
                                   <td>21/06/1997</td>
                                   <td>Comida chatarra</td>
                                   <td>120</td> 
                                </tr>
                            </table>
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
