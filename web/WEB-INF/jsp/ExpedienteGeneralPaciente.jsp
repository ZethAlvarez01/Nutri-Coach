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
        <script type="text/javascript" src="resource/scrips/expediente.js"/></script> 
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
            </div>

        </div>
    </div>

                
                  <a class="regresar" href="<c:url value="javascript:history.back(1)" />">Regresar</a>
                   
          <div id="contenido">
            <div class="container">
                <center>
                 
                    <h2> <c:forEach items="${datos}" var="dato">
                    <p id="txt-bnv"><h1>Bienvenido, <c:out value="${dato.nombre}"/> este es tu expediente general</h1></p>
                </c:forEach></h2>
                </center>
                <div id="encabezado_lista" >
                        <p id="sub-titulo"></p>
                        <select id="listaUsuarios" onchange="TypeExpediente()" name="listaUsuarios" >
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
                        
                        
                    </div>
                
                 <div class="grid-container">
                     <div class="item1">
                         <form:form method="post" commandName="expediente">
                         <p><b>Primer expediente: </b>   
                             
                        <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${ExpedienteBase[0].fecha_ini}">${ExpedienteBase[0].fecha_ini}</form:button>
                           </p>
                            <form:input path="id_hojaexpediente"  value="${ExpedienteBase[0].id_hojaExpediente}" type="hidden" />
                            <form:input path="id_expediente"  value="${ExpedienteBase[0].id_expediente}" type="hidden" />
                           </form:form>
                        <hr>
                       
                       
                          <div id="lista">
                        
                        
                        <div id="Enero" class="listaUsuarios" style="display: block;">
                           
                         <c:forEach items="${expedienteEnero}" var="item"> 
                        <div class="usuario"  name="usuario">
                           
                               
                            <div id="texto_user"> 
                                  <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                         </p>
                        <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                           
                             </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Febrero" class="listaUsuarios"  style="display: none;">
                      
                         <c:forEach items="${expedienteFebrero}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                              <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                         </p>
                        <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                                
                            </div>
                             
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Marzo" class="listaUsuarios" style="display: none">   
                            
                         <c:forEach items="${expedienteMarzo}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                               <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                       <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>

                         
                        <div id="Abril" class="listaUsuarios" style="display: none;">
                          
                         <c:forEach items="${expedienteAbril}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                              <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                      <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Mayo" class="listaUsuarios"  style="display: none;">
                         
                         <c:forEach items="${expedienteMayo}" var="item"> 
                        <div class="usuario">
                           
                                
                            <div id="texto_user">
                               <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                        <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                             
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Junio" class="listaUsuarios" style="display: none">   
                           
                         <c:forEach items="${expedienteJunio}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                             <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                       <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>
 
                       <div id="Julio" class="listaUsuarios" style="display: none;">
                         
                         <c:forEach items="${expedienteJulio}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                               <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                       <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Agosto" class="listaUsuarios"  style="display: none;">
                          
                         <c:forEach items="${expedienteAgosto}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                      <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                             
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Septiembre" class="listaUsuarios" style="display: none">   
                           
                         <c:forEach items="${expedienteSeptiembre}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                              <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                       <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>
  
                         
                         
                         <div id="Octubre" class="listaUsuarios" style="display: none;">
                         
                         <c:forEach items="${expedienteOctubre}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                               <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                       <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="Noviembre" class="listaUsuarios"  style="display: none;">
                        
                         <c:forEach items="${expedienteNoviembre}" var="item"> 
                        <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                                <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                         </p>
                        <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="Diciembre" class="listaUsuarios" style="display: none">   
                           
                         <c:forEach items="${expedienteDiciembre}" var="item" > 
                            <div class="usuario"   name="usuario">
                           
                               
                            <div id="texto_user">
                              <form:form method="post" commandName="expediente">
                          
                          <p> <b>Fecha: </b> 
                                <form:button id="button" class="button" type="submit" name="consultarEntradaExpedientePaciente" value="${item.fecha_ini}">${item.fecha_ini}</form:button>
                          </p>
                       <form:input path="id_hojaexpediente" placeholder="${item.id_hojaExpediente}" value="${item.id_hojaExpediente}" type="hidden" />
                       </form:form>
                            </div>
                                
                                
                            </div>
                            </c:forEach> 
                      
                        

                    </div>

                        
                        

                    </div>
                   
                        
                        
                         
                        
                      
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
