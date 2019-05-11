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
                        
                         <c:if test= "${item.no_cedulap>0}">
                            
                        
                               <li><a class="texto_menu" href="SeguimientoPsicologico.htm">Psicólogo</a></li>
                             
                                                 
                        </c:if>
                               
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_boleta" placeholder="${item.no_boleta}" value="${item.no_boleta}" type="hidden" />
                       </form:form>
                    </ul>
                         </c:forEach> 
                    
                    
                </div>
            </div>

        </div>
    </div>

                
                   <a class="regresar" href="<c:url value="/foro.htm" />">Regresar</a>
                   
          
            
                <center>
                     <h1>Foro de Nutri-Coach</h1>
                    <h2> <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> Este es el foro</h1></p>
                </c:forEach></h2>
                </center>
                
                
                 
                      
                       <c:forEach items="${datos}" var="item"> 
                           <c:set var = "sesion"  value = "${item.no_boleta}"/>
                        <c:forEach items="${Entrada}" var="entrada">   
                             <c:set var = "usuario"  value = "${entrada.id_usuario}"/>
                        <c:if test ="${usuario == sesion}">
                            
                        
                   <div class="grid-container">
                     <div class="item1">
                         
                         
                          <form:form method="post" commandName="entradaForo">
                         
                     <c:forEach items="${Entrada}" var="dato">
                         
                     <c:set var = "autor"  value = "${dato.id_usuario}"/>
                                <c:forEach items="${ListaPacientes}" var="listaPacientes">
                                    <c:set var = "paciente"  value = "${listaPacientes.no_boleta}"/>
                                    
                                    <c:if test ="${paciente == autor}">
                                       
                                        <p>${dato.fecha} ${listaPacientes.nombre} ${listaPacientes.ap_uno} ${listaPacientes.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        
                                 <c:forEach items="${ListaNutriologos}" var="listaNutriologos">
                                    <c:set var = "nutriologo"  value = "${listaNutriologos.no_empleado}"/>
                                    
                                    <c:if test ="${nutriologo == autor}">
                                      
                                        <p>${dato.fecha} Nutriólogo: ${listaNutriologos.nombre} ${listaNutriologos.ap_uno} ${listaNutriologos.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        
                                         <c:forEach items="${ListaPsicologos}" var="listaPsicologos">
                                    <c:set var = "psicologo"  value = "${listaPsicologos.no_empleado}"/>
                                    
                                    <c:if test ="${psicologo == autor}">
                                      
                                        <p>${dato.fecha} Psicólogo: ${listaPsicologos.nombre} ${listaPsicologos.ap_uno} ${listaPsicologoss.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        <c:forEach items="${ListaAdministrador}" var="listaAdministrador">
                                    <c:set var = "administrador"  value = "${listaAdministrador.no_empleado}"/>
                                    
                                    <c:if test ="${administrador == autor}">
                                      
                                        <p>${dato.fecha} Admnistrador</p>
                                        
                                </c:if>
                                </c:forEach>
                                
                     
                   
                        
                  
                    
                          <h1><form:input path="titulo" id="Entrada" placeholder="${dato.titulo}" disabled="true" /></h1> 
                          <p> <form:errors path="titulo"/></p>
                       
                   
                        <hr>
                        
                        <form:textarea path="contenido" id="EntradaContenido" placeholder="${dato.contenido}" disabled="true" />
                        <p>  <form:errors path="contenido"/> </p>
                    
                        
                       
                       
                        
                        
                         
                         <form:input path="id_entrada"  value="${dato.id_entrada}" type="hidden"  />
                     </c:forEach>
                        
                      
                    </div>  
                                    
                   
                    
                 </div>
                            
                            
                            
                     <input type="submit" class="regresar" id="GuardarEntrada" name="ModificarEntrada" value="Guardar" style="display: none"/>
                     <input type="submit" class="button" id="EliminarEntrada" name="EliminarEntrada" value="Eliminar"/>
                    
                        </form:form>
                     <input type="submit" class="regresar" id="EditarEntrada" value="Editar" onclick="Editar()"  />
               

                        
                            
                             
                                                 
                        </c:if>
                       
                         </c:forEach>               
             </c:forEach>   
      
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                   <c:forEach items="${datos}" var="item"> 
                           <c:set var = "sesion"  value = "${item.no_boleta}"/>
                        <c:forEach items="${Entrada}" var="entrada">   
                             <c:set var = "usuario"  value = "${entrada.id_usuario}"/>
                        <c:if test ="${usuario != sesion}">
                            
                        
                   <div class="grid-container">
                     <div class="item1">
                         
                         
                          <form:form method="post" commandName="entradaForo">
                         
                     <c:forEach items="${Entrada}" var="dato">
                         
                      <c:set var = "autor"  value = "${dato.id_usuario}"/>
                                <c:forEach items="${ListaPacientes}" var="listaPacientes">
                                    <c:set var = "paciente"  value = "${listaPacientes.no_boleta}"/>
                                    
                                    <c:if test ="${paciente == autor}">
                                       
                                        <p>${dato.fecha} ${listaPacientes.nombre} ${listaPacientes.ap_uno} ${listaPacientes.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        
                                 <c:forEach items="${ListaNutriologos}" var="listaNutriologos">
                                    <c:set var = "nutriologo"  value = "${listaNutriologos.no_empleado}"/>
                                    
                                    <c:if test ="${nutriologo == autor}">
                                      
                                        <p>${dato.fecha} Nutriólogo: ${listaNutriologos.nombre} ${listaNutriologos.ap_uno} ${listaNutriologos.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        
                                         <c:forEach items="${ListaPsicologos}" var="listaPsicologos">
                                    <c:set var = "psicologo"  value = "${listaPsicologos.no_empleado}"/>
                                    
                                    <c:if test ="${psicologo == autor}">
                                      
                                        <p>${dato.fecha} Psicólogo: ${listaPsicologos.nombre} ${listaPsicologos.ap_uno} ${listaPsicologoss.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        <c:forEach items="${ListaAdministrador}" var="listaAdministrador">
                                    <c:set var = "administrador"  value = "${listaAdministrador.no_empleado}"/>
                                    
                                    <c:if test ="${administrador == autor}">
                                       
                                        <p>${dato.fecha} Admnistrador</p>
                                        
                                </c:if>
                                </c:forEach>
                                
                     
                   
                        
                  
                    
                          <h1>${dato.titulo}</h1> 
                         
                       
                   
                        <hr>
                        
                        
                        <p>  ${dato.contenido} </p>
                    
                        
                       
                       
                        
                        
                         
                         <form:input path="id_entrada"  value="${dato.id_entrada}" type="hidden"  />
                     </c:forEach>
                        
                      
                    </div>  
                                    
                   
                    
                 </div>
                            
                            
                            
                          
                    
                        </form:form>
                     
               
                            
                            
                             
                                                 
                        </c:if>
                       
                         </c:forEach>               
             </c:forEach>   
      
                     
                       
            
            
                     <c:forEach items="${datos}" var="item">
                          <c:set var = "sesion"  value = "${item.no_boleta}"/>
                     <c:forEach items="${Entrada}" var="entrada">   
       <div class="item2"><p class="titulo">Comentarios</p>
                            
                            <hr>
                            <c:forEach items="${ListaComentarios}" var="listaComentarios">
                                <form:form method="post" commandName="Comentario">
                                <c:set var = "usuario"  value = "${listaComentarios.id_usuario}"/>
                                <c:forEach items="${ListaPacientes}" var="listaPacientes">
                                    <c:set var = "paciente"  value = "${listaPacientes.no_boleta}"/>
                                    
                                    <c:if test ="${paciente == usuario}">
                                      
                                        <p>${listaComentarios.fecha} ${listaPacientes.nombre} ${listaPacientes.ap_uno} ${listaPacientes.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        
                                 <c:forEach items="${ListaNutriologos}" var="listaNutriologos">
                                    <c:set var = "nutriologo"  value = "${listaNutriologos.no_empleado}"/>
                                    
                                    <c:if test ="${nutriologo == usuario}">
                                      
                                        <p>${listaComentarios.fecha} Nutriólogo: ${listaNutriologos.nombre} ${listaNutriologos.ap_uno} ${listaNutriologos.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        
                                         <c:forEach items="${ListaPsicologos}" var="listaPsicologos">
                                    <c:set var = "psicologo"  value = "${listaPsicologos.no_empleado}"/>
                                    
                                    <c:if test ="${psicologo == usuario}">
                                      
                                        <p>${listaComentarios.fecha} Psicólogo: ${listaPsicologos.nombre} ${listaPsicologos.ap_uno} ${listaPsicologoss.ap_dos}</p>
                                        
                                </c:if>
                                </c:forEach>
                                        <c:forEach items="${ListaAdministrador}" var="listaAdministrador">
                                    <c:set var = "administrador"  value = "${listaAdministrador.no_empleado}"/>
                                    
                                    <c:if test ="${administrador == usuario}">
                                      
                                        <p>${listaComentarios.fecha} Admnistrador</p>
                                        
                                </c:if>
                                </c:forEach>
                                
                                
                                <p>${listaComentarios.contenido}
                                
                                <c:if test ="${usuario == sesion}">
                                    
                                   <input type="submit" class="button" id="EliminarComentario" name="EliminarComentario" value="Eliminar Comentario"/>
                                    <form:input path="id_comnt" value="${listaComentarios.id_comnt}" type="hidden"  />
                                   
                                </c:if>
                                   </p>
                             <hr> 
                                  <form:input path="id_entrada" value="${entrada.id_entrada}" type="hidden"  />
                              </form:form> 
                            </c:forEach>        
                            
                                                       
                       <form:form method="post" commandName="Comentario">     
                         <form:textarea path="contenido" id="EntradaContenido" placeholder="ingresa tus comentarios"  />
                        <p>  <form:errors path="contenido"/> </p> 
                        
                        
                        <input type="submit" class="regresar" id="AgregarComentario" name="AgregarComentario" value="Agregar Comentario" onclick="fecha2()"/>
                        
                      
                        
                        <form:input path="fecha" value="" type="hidden"   />
                        <form:input path="id_entrada" value="${entrada.id_entrada}" type="hidden"  />
                        <form:input path="id_usuario"  value="${item.no_boleta}" type="hidden"  />
                        
                            
                             </form:form>
                    
                         </c:forEach>  
                         </c:forEach>
                          
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
