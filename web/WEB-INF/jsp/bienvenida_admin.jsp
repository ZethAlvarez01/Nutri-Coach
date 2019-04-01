<%-- 
    Document   : bienvenida_admin
    Created on : 12-mar-2019, 11:57:01
    Author     : jms-m
--%>

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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/bienvenida_admin.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/vista_usuarios.css" />" />
        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        
         
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
                    <c:forEach items="${ListaAdmin}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Administrador">
                            <li><input type="submit" class="texto_menu" name="mensajes" value="Mensajes" formaction="mensajeriaAdmin.htm"></li>
                                
                         <li><input type="submit" class="texto_menu" name="solicitudes" value="Solicitudes" formaction="verificacion_cuentas.htm"></li>
                       
                        <li><input type="submit" class="texto_menu" name="foro" value="Foro" formaction="foroAdmin.htm"></li></li>
                        <li><a class="texto_menu" href="">XXXXXX</a></li>
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
               
                <div id="listado">
                    <div id="encabezado_lista" >
                        <p id="sub-titulo">Listado de usuarios</p>
                        <select id="listaUsuarios" onchange="TypeOption()" name="listaUsuarios" >
                            <option  value="pacientes"  >Pacientes</option>
                            <option  value="nutriologos" >Nutriólogos</option>
                            <option  value="psicologos">Psicólogos</option>
                        </select>
                        <input type="text" placeholder="Buscar usuario">
                        
                    </div>

                    <div id="paciente" class="listaUsuarios" style="display: block;">
                          <p id="numero_usuarios">Número de usuarios: ${LongitudP}</p>  
                         <c:forEach items="${ListaP}" var="item"> 
                        <div class="usuario" onclick="TypeDiv('${item.no_boleta}','${item.nombre} ${item.ap_uno} ${item.ap_dos}','Edad: ${item.edad} Sexo:${item.sexo} Fecha de nacimiento:${item.fecha_n}',' domicilio: ${item.domicilio}',' telefono:${item.telefono}  correo:${item.correo}','${item.estatus}')" name="usuario">
                           
                                <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user"> 
                                <p id="nombre">${item.nombre} ${item.ap_uno} ${item.ap_dos}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                           
                             </div>
                            </c:forEach> 
                            
                          
                        
                    </div>
                            
                    <div id="psicologo" class="listaUsuarios"  style="display: none;">
                        <p id="numero_usuarios">Número de usuarios: ${LongitudPs}</p>  
                         <c:forEach items="${ListaPs}" var="item"> 
                        <div class="usuario" onclick="TypeDiv('No. Cédula: ${item.no_cedula} No. Trabajador: ${item.no_empleado}','${item.nombre} ${item.ap_uno} ${item.ap_dos}',' telefono:${item.telefono}','  correo:${item.correo}','','${item.estatus}')" name="usuario">
                           
                                <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">${item.nombre} ${item.ap_uno} ${item.ap_dos}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                            </div>
                             
                            </c:forEach> 
                       
                     </div>
                            
                       <div id="nutriologo" class="listaUsuarios" style="display: none">   
                           <p id="numero_usuarios">Número de usuarios: ${LongitudN}</p>  
                         <c:forEach items="${ListaN}" var="item" > 
                            <div class="usuario" onclick="TypeDiv('No. Cédula: ${item.no_cedula} No. Trabajador: ${item.no_empleado}','${item.nombre} ${item.ap_uno} ${item.ap_dos}','Institucion: ${item.institucion}',' consultorio: ${item.consultorio}',' telefono:${item.telefono}  correo:${item.correo}','${item.estatus}')" name="usuario">
                        
                            
                                <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto">
                            <div id="texto_user">
                                <p id="nombre">${item.nombre} ${item.ap_uno} ${item.ap_dos}</p>
                                <p id="nombre-info">Mas informacion</p>
                            </div>
                                
                                
                           
                               </div>
                            </c:forEach> 
                      
                        

                    </div>

                </div>

                <div id="informacion">
                    <div id="h3">
                        <h3>Administración general de usuarios</h3>
                    </div>
                    <div id="info_general">
                        <h2>Datos Generales</h2>
                        <p id="etiqueta_estado" style="display: none;">Activo</p>
                        <div id="datos">
                            <p id="fecha"></p>
                            <script>
                                fecha();
                            </script>
                            <div id="info_completa" style="display: none;" name="info_completa">
                                <img src="<c:url value="/resource/imagenes/foto-prueba.png"/>" alt="Foto de perfil" id="foto_info">
                               
                                <p id="num_boleta">ID</p>
                                <p id="nombreUser">Nombre Completo:</p> 
                                <p id="datosGenerales">  EDAD: SEXO: FECHA DE NACIMIENTO:  </p>
                                <p id="datosGenerales2">  DOMICILIO:  </p>
                                <p id="datosGenerales3">   TELEFONO:  CORREO: </p>
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


                    <div id="estado" style="display: none;" name="estado">
                        <h2 id="titulo_derecha">Cambiar estado del usuario</h2>
                        <p id="estado"></p>
                        <div id="botones">
                            <input type="radio" id="activo" name="group1" class="radioB" value="activo" > <label class="c_estado">Activo</label><br>
                            <input type="radio" id="bloquear" name="group1" class="radioB" value="activo"> <label class="c_estado">Suspender</label><br>
                            <input type="radio" id="baja" name="group1" class="radioB" value="activo"> <label class="c_estado">Dar de baja</label>
                        </div>

                        
                        <div id="boton" onclick="confirmacion()">Guardar cambio</div>
                        
                       
                        <h2 id="titulo_derecha">Enviar mensajes</h2>
                        <br>
                        <form:form method="POST" commandName="Mensaje">
                        <form:textarea path="contenido" id="textarea" cols="30" rows="10" placeholder="Mensaje"/>
                          
                        
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
