<%-- 
    Document   : expedientePsicologico
    Created on : 11-may-2019, 13:38:27
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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/bienvenida_psicologo.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/vista_usuarios.css" />" />
         <link rel="stylesheet" href="<c:url value="/resource/estilos/barra_menu.css" />" />

        <!-- Scripts -->

        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/psicologo.js"/></script>
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
    </header>
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


    <div id="contenido">
        <div class="container">
            <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> Este es el expediente .</h1></p>
                </c:forEach>
            <div id="grid">
                <div id="notificaciones" >
                  <a class="regresar" href="<c:url value="/ConsultarPacientePrincipalp.htm" />">Regresar</a>
                </div>
               
                
                       <div id="informacion" >
                    <div id="h3">
                        <h3>Informacion del paciente</h3>
                    </div>
                    <h1><p id="nombreUser">${ListaPacientes[0].nombre} ${ListaPacientes[0].ap_uno} ${ListaPacientes[0].ap_dos}</p></h1>
                    <br>
                    <div id="grid_info">
                        <div class="casillas" id="resumen">
                            <h3 onclick="expandir()" style="cursor:pointer;">Expediente Clínico</h3>
                            <form:form method="post" commandName="Paciente">
                            <form:input path="no_boleta"  value="${ListaPacientes[0].no_boleta}" id="boletadiario"  type="hidden"  /> 
                            <input  id="boton" type="submit" name="ConsultarDiario" value="Diario">
                            </form:form>
                            <p>Fecha de inicio</p>
                             <p id="num_boleta">Número de boleta: ${ListaPacientes[0].no_boleta}</p>
                             
                                <p id="datosGenerales">  Edad:${ListaPacientes[0].edad} Sexo:${ListaPacientes[0].sexo} Fecha de nacimiento: ${ListaPacientes[0].fecha_n} </p>
                                <p id="datosGenerales2">  Domicilio:${ListaPacientes[0].domicilio} </p>
                                <p id="datosGenerales3">   Teléfono:${ListaPacientes[0].telefono}  Correo:${ListaPacientes[0].correo} </p>
                  
                        
                        </div>

                        <div class="casillas" id="actividad">
                            <form:form method="post" commandName="ActividadP">
                                
                            <h3 style="cursor:pointer;">Asignar actividad</h3>
                            
                            <form:textarea path="contenido"  id="desc_actividad" cols="20" rows="8" placeholder="Descripcion de la actividad" />
                            <p>  <form:errors path="contenido"/> </p> 
                            <div id="archivo">
                                <button id="insertar_a">Insertar archivos</button><label id="n_archivo">nombre_archivo.ext</label>
                            </div>
                            <c:forEach items="${datos}" var="psicologo">
                                <form:input path="no_cedula"  value="${psicologo.no_cedula}" type="hidden"   /> 
                            </c:forEach> 
                            
                             
                            <form:input path="no_boleta" id="num_boleta2" value="${ListaPacientes[0].no_boleta}"  type="hidden" />  
                            
                            <form:input path="fecha" value="" type="hidden"   />
                            <input  id="boton" type="submit" name="AsignarActividad" value="Asignar Actividad" onclick="fecha2()">
                             </form:form>
                        </div>
                                
                                
                                
                                

                        <div class="casillas" id="historial_act">
                            <h3  style="cursor:pointer;">Historial</h3>
       
                    ACTIVIDADES QUE DEJO EL PSICOLOGO        
                               <c:forEach items="${ListaActividades}" var="item"> 
                          
                    <ul>
                       
                      <form:form method="post" commandName="ActividadP">
                         ${item.fecha}
                        <form:button id="button" class="button" type="submit" name="consultarActividadPs" value="${item.contenido}">${item.contenido}</form:button>
                            <form:input path="id_actividad" placeholder="${item.id_actividad}" value="${item.id_actividad}" type="hidden" />
                            <form:input path="no_boleta" placeholder="${ListaPacientes[0].no_boleta}" value="${ListaPacientes[0].no_boleta}" type="hidden" />
                       </form:form>
                        
                       
                    </ul>
                         </c:forEach> 
             
                            
       
                            
              ENTRADAS EN EL FORO              
                            
         <c:forEach items="${listaEntradas}" var="item"> 
                          
                    <ul>
                       <form:form method="post" commandName="entradaForo">
                         ${item.fecha}
                        <form:button id="button" class="button" type="submit" name="consultarEntrada" value="${item.titulo}">${item.titulo}</form:button>
                            <form:input path="id_entrada" placeholder="${item.entrada}" value="${item.id_entrada}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach>   
                            
                          
              COMENTARIOS EN EL FORO
              
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
