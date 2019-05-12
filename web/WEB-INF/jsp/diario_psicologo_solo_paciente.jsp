<%-- 
    Document   : diario_psicologo_solo_paciente
    Created on : 14-mar-2019, 0:09:54
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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/contenido_diario_psicologo_solo.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/bienvenida_psicologo.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/barra_menu.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/vista_usuarios.css" />" />
        
        <!-- Scripts -->

        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
   <script type="text/javascript" src="resource/scrips/diario.js"/></script>  
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
                                        <a class="regresar" href="<c:url value="javascript:history.back(1)" />">Regresar</a>
    <div id="contenido">
        <div class="container">
            <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> este es el diario.</h1></p>
                </c:forEach>
            <div id="grid">
                <div id="notificaciones" >
                  
                </div>
                <div id="listado" style="display: block">
                    <div id="encabezado_lista" >
                        <p id="sub-titulo">Diario del paciente</p>
                        <select id="listaUsuarios" onchange="TypeDiario()" name="listaUsuarios" >
                            <option  value="Enero"  >Enero</option>
                            <option  value="Febrero" >Febrero</option>
                            <option  value="Marzo">Marzo</option>
                            <option  value="Abril"  >Abril</option>
                            <option  value="Mayo" >Mayo</option>
                            <option  value="Junio">Junio</option>
                            <option  value="Julio"  >Julio</option>
                            <option  value="Agosto" >Agosto</option>
                            <option  value="Septiembre">Septiembre</option>
                            <option  value="Octubre"  >Octubre</option>
                            <option  value="Noviembre" >Noviembre</option>
                            <option  value="Diciembre">Diciembre</option>
                            
                        </select>
                        <input type="text" placeholder="Buscar fecha">
                        
                    </div>

                    <div id="lista">
                        
                        
                        <div id="Enero" class="listaUsuarios" style="display: block;">
                           
                         <c:forEach items="${hojasEnero}" var="item"> 
                        <div class="usuario"  name="usuario">
                           
                               
                            <div id="texto_user"> 
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                           
                             </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Febrero" class="listaUsuarios"  style="display: none;">
                      
                         <c:forEach items="${hojasFebrero}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                             
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Marzo" class="listaUsuarios" style="display: none">   
                            
                         <c:forEach items="${hojasMarzo}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>

                         
                        <div id="Abril" class="listaUsuarios" style="display: none;">
                          
                         <c:forEach items="${hojasAbril}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Mayo" class="listaUsuarios"  style="display: none;">
                         
                         <c:forEach items="${hojasMayo}" var="item"> 
                        <div class="usuario"  onclick="TypeOptionDiario('${item.fecha}','Hoy me siento ${item.sentimiento}','${item.contenido}','${item.observaciones}','${item.id_hojas}')">
                           
                                
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                             
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Junio" class="listaUsuarios" style="display: none">   
                           
                         <c:forEach items="${hojasJunio}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>
 
                       <div id="Julio" class="listaUsuarios" style="display: none;">
                         
                         <c:forEach items="${hojasJulio}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Agosto" class="listaUsuarios"  style="display: none;">
                          
                         <c:forEach items="${hojasAgosto}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                             
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Septiembre" class="listaUsuarios" style="display: none">   
                           
                         <c:forEach items="${hojasSeptiembre}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>
  
                         
                         
                         <div id="Octubre" class="listaUsuarios" style="display: none;">
                         
                         <c:forEach items="${hojasOctubre}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Noviembre" class="listaUsuarios"  style="display: none;">
                        
                         <c:forEach items="${hojasNoviembre}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Diciembre" class="listaUsuarios" style="display: none">   
                           
                         <c:forEach items="${hojasDiciembre}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <p id="nombre">${item.fecha} ${item.sentimiento}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>

                        
                        

                    </div>

                </div>
                <div id="informacion" style="display: block">
                    <div id="h3" style="display: block">
                        <h3>Diario de ${nombreP[0].nombre} ${nombreP[0].ap_uno} ${nombreP[0].ap_dos}</h3>
                    </div>
                    <h1><p id="nombreUser"></p></h1> 
                    
                    <br>
                    <div id="grid_info">
                        <div class="casillas" id="resumen">
                            <h3  style="cursor:pointer;"> <p id="fecha">FECHA DEL DIARIO</p></h3>
                            
                            <h1><p id="sentimiento"></p></h1>
                            
                            <br><br>
                             <p id="contenidoh">CONTENIDO DE LA HOJA</p>
                                
                            
                        
                        </div>

                        <div class="casillas" id="actividad">
                            
                           <h3>Observaciones</h3>
                   
                             <form:form method="post" commandName="diario" >
                                
                            <form:textarea path="observaciones"  id="desc_actividad" cols="20" rows="8" placeholder="Agrega las observaciones que tengas" />
                            <p>  <form:errors path="observaciones"/> </p> 
                            
                            
                            
                             
                            <form:input path="id_hojas" id="id_hoja" value=""  type="hidden" />  
                            
                            
                            
                            
                            <input  id="boton" type="submit" name="GuardarObservacion" value="Guardar">
                             </form:form>
                    
                 
                    
                    
                    
                    
                    
                        </div>

                        <div class="casillas" id="historial_act">
                            
                          
                            
                          
                            
                            
                            <ul id="historial_actividades">
                              
                                <h1>OBSERVACIONES HECHAS POR EL PSICÓLOGO</h1>
                                <li><p id="observaciones">OBSERVACIONES HECHAS POR EL PSICÓLOGO</p></li>
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
