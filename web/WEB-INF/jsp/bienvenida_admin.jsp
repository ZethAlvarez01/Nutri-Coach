
<%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />
        
        <!-- Hojas de estilos -->
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/bienvenida_admin.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/vista_usuarios.css" />" />
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/burguer_menu.css" />" />
        
        <!-- Scripts -->
        
        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>
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
                    <a href="bienvenida_admin.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                    <c:forEach items="${ListaAdmin}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Administrador">
                        <li><a class="texto_menu" href="mensajeriaAdmin.htm">Mensajes</a></li>                       
                        <li><a class="texto_menu" href="verificacion_cuentas.htm">Solicitudes</a></li>
                        <li><a class="texto_menu" href="foroAdmin.htm">Foro</a></li>
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
            <div id="grid">
               
              

                <div id="informacion">
                    <div id="h3">
                        <h3>Administración general de usuarios</h3>
                    </div>
                    <div id="info_general">
                        <h2>Datos Generales</h2>
                        <p id="etiqueta_estado" >Activo</p>
                        <div id="datos">
                            <p id="fecha"></p>
                            <script>
                                fecha();
                            </script>
                            <div id="info_completa"  name="info_completa">
                                <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto de perfil" id="foto_info">
                               <c:if test= "${p==1}">
                                <p id="num_boleta">No. Boleta: ${datosU[0].no_boleta}</p>
                                <p id="nombreUser">Nombre Completo: ${datosU[0].nombre} ${datosU[0].ap_uno} ${datosU[0].ap_dos}</p> 
                                <p id="datosGenerales">  EDAD: SEXO: FECHA DE NACIMIENTO: ${datosU[0].fecha_n}</p>
                                <p id="datosGenerales2">  DOMICILIO:  ${datosU[0].domicilio}</p>
                                <p id="datosGenerales3">   TELEFONO: ${datosU[0].telefono} CORREO: ${datosU[0].correo}</p>
                                
                                
                                </c:if>
                                <c:if test= "${ps==1}">
                                <p id="num_boleta">No. Cédula:${datosU[0].no_cedula}    No. Empleado: ${datosU[0].no_empleado}  </p>
                                <p id="nombreUser">Nombre Completo: ${datosU[0].nombre} ${datosU[0].ap_uno} ${datosU[0].ap_dos}</p> 
                                <p id="datosGenerales">  Institución ${datosU[0].institucion}   </p>
                                <p id="datosGenerales2">  CONSULTORIO: ${datosU[0].consultorio} </p>
                                <p id="datosGenerales3">   TELEFONO: ${datosU[0].telefono} CORREO: ${datosU[0].correo}</p>
                                <p id="datosGenerales4">   Hora de Entrada: ${datosU[0].horaEntrada} Hora de Salida: ${datosU[0].horaSalida}</p>
                                
                                
                                </c:if>
                                <c:if test= "${N==1}">
                                 <p id="num_boleta">No. Cédula:${datosU[0].no_cedula}    No. Empleado: ${datosU[0].no_empleado}  </p>
                                <p id="nombreUser">Nombre Completo: ${datosU[0].nombre} ${datosU[0].ap_uno} ${datosU[0].ap_dos}</p> 
                                <p id="datosGenerales">  Institución ${datosU[0].institucion}   </p>
                                <p id="datosGenerales2">  CONSULTORIO: ${datosU[0].consultorio} </p>
                                <p id="datosGenerales3">   TELEFONO: ${datosU[0].telefono} CORREO: ${datosU[0].correo}</p>
                                <p id="datosGenerales4">   Hora de Entrada: ${datosU[0].horaEntrada} Hora de Salida: ${datosU[0].horaSalida}</p>
                                
                                </c:if>
                                <br>
                                <br>
                                <br>
                                <h2>Historial de entradas</h2>
                                <br> Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ex quos optio dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam!
                                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ex quos optio dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem
                                ipsum dolor sit amet consectetur, adipisicing elit. Ex quos optio dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum
                                dolor sit amet consectetur, adipisicing elit. Ex quos optio dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum dolor
                                sit amet consectetur, adipisicing elit. Ex quos optio dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum dolor sit
                                amet consectetur, adipisicing elit. Ex quos optio dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum dolor sit amet
                                consectetur, adipisicing elit. Ex quos optio
                                <br>
                                <br>
                                <h2>Historial ...</h2>
                                <br> dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ex quos optio
                                dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ex quos optio dolorem
                                obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam!
                                <br>
                                <br>
                                <h2>Historial ...</h2>
                                <br>
                                <ul>
                                    <li>Alimentos</li>
                                    <li>Diario</li>
                                    <li>Comida</li>
                                    <li>Helado</li>
                                    <li>Más helado</li>
                                </ul>
                                <br>
                                <br> dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ex quos optio
                                dolorem obcaecati corporis. Ad temporibus blanditiis non natus illo alias reprehenderit ipsam, odit iure dolorum quasi nemo impedit laboriosam! Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ex quos optio dolorem
                                obcaecati corporis. Ad tempori

                            </div>
                        </div>
                    </div>


                    <div id="estado"  name="estado">
                        <h2 id="titulo_derecha">Cambiar estado del usuario</h2>
                        <p id="estado"></p>
                        <c:if test= "${p==1}">
                            <div id="botones">
                                       <form:form method="post" commandName="Paciente">
                                <c:if test= "${datosU[0].estatus==1}">
                                    
                                 <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1" checked="true" /> <label class="c_estado">Activo</label><br>
                             <form:radiobutton path="estatus" id="bloquear" name="group1" class="radioB" value="2"/> <label class="c_estado">Suspender</label><br>
                             <form:radiobutton path="estatus" id="baja" name="group1" class="radioB" value="3"/> <label class="c_estado">Dar de baja</label>
                                </c:if>
                             
                           <c:if test= "${datosU[0].estatus==2}">
                                <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1" /> <label class="c_estado">Activo</label><br>
                            <form:radiobutton path="estatus"id="bloquear" name="group1" class="radioB" value="2" checked="true" /> <label class="c_estado">Suspender</label><br>
                             <form:radiobutton path="estatus" id="baja" name="group1" class="radioB" value="3"/> <label class="c_estado">Dar de baja</label>
                                </c:if>
                            <c:if test= "${datosU[0].estatus==3}">
                               <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1"  /> <label class="c_estado">Activo</label><br>
                            <form:radiobutton path="estatus" id="bloquear" name="group1" class="radioB" value="2"/> <label class="c_estado">Suspender</label><br>
                            <form:radiobutton path="estatus"id="baja" name="group1" class="radioB" value="3" checked="true" /> <label class="c_estado">Dar de baja</label>
                                </c:if>
                       
                        </div>

                         <form:input path="no_boleta" placeholder="${datosU[0].no_boleta}" value="${datosU[0].no_boleta}" type="hidden" />
                        <div id="boton" > <input type="submit"  name="CambioP" value="Guardar Cambio">  </div>
                             </form:form>
                        </div>

                        
                     
                        </c:if>
                        
                         <c:if test= "${ps==1}">
                             <div id="botones">
                                 <form:form method="post" commandName="Psicologo">
                                <c:if test= "${datosU[0].estatus==1}">
                                    
                                 <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1" checked="true" /> <label class="c_estado">Activo</label><br>
                             <form:radiobutton path="estatus" id="bloquear" name="group1" class="radioB" value="2"/> <label class="c_estado">Suspender</label><br>
                             <form:radiobutton path="estatus" id="baja" name="group1" class="radioB" value="3"/> <label class="c_estado">Dar de baja</label>
                                </c:if>
                             
                           <c:if test= "${datosU[0].estatus==2}">
                                <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1" /> <label class="c_estado">Activo</label><br>
                            <form:radiobutton path="estatus"id="bloquear" name="group1" class="radioB" value="2" checked="true" /> <label class="c_estado">Suspender</label><br>
                             <form:radiobutton path="estatus" id="baja" name="group1" class="radioB" value="3"/> <label class="c_estado">Dar de baja</label>
                                </c:if>
                            <c:if test= "${datosU[0].estatus==3}">
                               <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1"  /> <label class="c_estado">Activo</label><br>
                            <form:radiobutton path="estatus" id="bloquear" name="group1" class="radioB" value="2"/> <label class="c_estado">Suspender</label><br>
                            <form:radiobutton path="estatus"id="baja" name="group1" class="radioB" value="3" checked="true" /> <label class="c_estado">Dar de baja</label>
                                </c:if>
                       
                        </div>
                       <form:input path="no_cedula" placeholder="${datosU[0].no_cedula}" value="${datosU[0].cedula}" type="hidden" />
                         <div id="boton" > <input type="submit"  name="CambioPs" value="Guardar Cambio">  </div>
                       
                             </form:form>
                        </c:if>
                        
                         <c:if test= "${N==1}">
                            <div id="botones">
                                       <form:form method="post" commandName="Nutriologo">
                                <c:if test= "${datosU[0].estatus==1}">
                                    
                                 <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1" checked="true" /> <label class="c_estado">Activo</label><br>
                             <form:radiobutton path="estatus" id="bloquear" name="group1" class="radioB" value="2"/> <label class="c_estado">Suspender</label><br>
                             <form:radiobutton path="estatus" id="baja" name="group1" class="radioB" value="3"/> <label class="c_estado">Dar de baja</label>
                                </c:if>
                             
                           <c:if test= "${datosU[0].estatus==2}">
                                <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1" /> <label class="c_estado">Activo</label><br>
                            <form:radiobutton path="estatus"id="bloquear" name="group1" class="radioB" value="2" checked="true" /> <label class="c_estado">Suspender</label><br>
                             <form:radiobutton path="estatus" id="baja" name="group1" class="radioB" value="3"/> <label class="c_estado">Dar de baja</label>
                                </c:if>
                            <c:if test= "${datosU[0].estatus==3}">
                               <form:radiobutton path="estatus" id="activo" name="group1" class="radioB" value="1"  /> <label class="c_estado">Activo</label><br>
                            <form:radiobutton path="estatus" id="bloquear" name="group1" class="radioB" value="2"/> <label class="c_estado">Suspender</label><br>
                            <form:radiobutton path="estatus"id="baja" name="group1" class="radioB" value="3" checked="true" /> <label class="c_estado">Dar de baja</label>
                                </c:if>
                       
                        </div>
                       <form:input path="no_cedula" placeholder="${datosU[0].no_cedula}" value="${datosU[0].cedula}" type="hidden" />
                        
                        <div id="boton" > <input type="submit"  name="CambioN" value="Guardar Cambio">  </div>
                             </form:form>
                        </div>

                        
                        
                        </c:if>
                      
                        
                       
                        <h2 id="titulo_derecha">Enviar mensajes</h2>
                        <br>
                        <form:form method="POST" commandName="Mensaje">
                        <form:textarea path="contenido" id="textarea" cols="30" rows="10" placeholder="Mensaje"/>
                          
                        
                        
                        <c:if test= "${p==1}">
                             <form:input path="id_usuario_emisor" placeholder="${datosU[0].no_boleta}" value="${datosU[0].no_boleta}" type="hidden" />
                        </c:if>
                        
                        <c:if test= "${N==1}">
                            <form:input path="id_usuario_emisor" placeholder="${datosU[0].no_empleado}" value="${datosU[0].no_empleado}" type="hidden" />
                        </c:if>
                        <c:if test= "${ps==1}">
                            <form:input path="id_usuario_emisor" placeholder="${datosU[0].no_empleado}" value="${datosU[0].no_empleado}" type="hidden" />
                        </c:if>
                        <form:button id="boton" type="submit" name="EnviarMensaje" value="EnviarMensaje" >Enviar mensaje</form:button>
                      
                        
                        
                        </form:form>
                                                        
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
