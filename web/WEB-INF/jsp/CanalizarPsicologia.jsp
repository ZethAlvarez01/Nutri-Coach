
<%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);
%>



<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="shortcut icon" type="image/png" href="<c:url value="/resource/imagenes/iconos/favicon.png" />" />

        <!--Hoja de estilo--> 
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/estilos_primera_cita.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/estilos_cronograma.css" />" />


        <!--Scripts-->
        <script type="text/javascript" src="resource/scrips/jquery-3.4.1.min.js"/></script> 
       
        
      
     
        <script type="text/javascript" src="resource/scrips/script.js"/></script>  
        <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>


        <script type="text/javascript" src="resource/scrips/canalizarPsicologo.js"/></script>
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
                    <a href="expedientePaciente.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                     <c:forEach items="${datos}" var="item"> 
                          
                    <ul id="menu_nutrio">
                        <form:form method="post" commandName="Nutriologo">
                        <li><a class="texto_menu" href="cronograma.htm">Cronograma</a></li>
                        <li><a class="texto_menu" href="mensajeriaN.htm">Mensajes</a></li>
                        <li><a class="texto_menu" href="ConsultarPacientePrincipaln.htm">Pacientes</a></li>
                        <li><a class="texto_menu" href="foroN.htm">Foro</a></li>
                        <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                        <form:input path="no_empleado" placeholder="${item.no_empleado}" value="${item.no_empleado}"  id="no_empleado"  type="hidden"/>
                       </form:form>
                    </ul>
                         </c:forEach> 
                    
                    
                </div>
            </div>

        </div>
    </div>

    <div id="contenido">
        <div class="container">
            <h1>Primera cita</h1>
            <div id="texto">
                Aquí podrás agendar tu primera cita. Solo ingresa algunos datos para conocer tu disponibilidad y la de un especialista, de esta forma te podremos atender a la brevedad. </div>
            <div id="calendario">
                <div id="calendar">
                    <div id="combo_box">
                        <select name="Mes" id="cajaMes" class="caja" onchange="mover_mes();">
                                    <option value="Enero">Enero</option>
                                    <option value="Febrero">Febrero</option>
                                    <option value="Marzo">Marzo</option>
                                    <option value="Abril">Abril</option>
                                    <option value="Mayo">Mayo</option>
                                    <option value="Junio">Junio</option>
                                    <option value="Julio">Julio</option>
                                    <option value="Agosto">Agosto</option>
                                    <option value="Septiembre">Septiembre</option>
                                    <option value="Octubre">Octubre</option>
                                    <option value="Noviembre">Noviembre</option>
                                    <option value="Diciembre">Diciembre</option>
                                    
                            </select>

                        <select name="Anio" id="anio" class="caja" onchange="mover_mes();">
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                                <option value="2021">2021</option>
                            </select>

                    </div>
                    <script>
                        actualizar(); 
                    </script>
                    
                    <br>
                    <table class="tabla">
                        <thead id="tabla">
                            <div class="c_dia" id="lunes">Dom.</div>
                            <div class="c_dia">Lun.</div>
                            <div class="c_dia">Mar.</div>
                            <div class="c_dia">Mié.</div>
                            <div class="c_dia">Jue.</div>
                            <div class="c_dia">Vie.</div>
                            <div class="c_dia">Sáb.</div>
                           <script>
                                dias();
                            </script>
                        </thead>
                    </table>
                    <p id="valor"></p> 
                 
                  

                </div>
                <div id="programar">


                    <p>Información de la cita</p>
                    <form:form method="post" commandName="cita">
                    <div class="texto">Institución</div>
                    <select id="institucion" class="primera_cita" >
                        
                        <option value="0">Escuela Superior de Cómputo (ESCOM)</option>
                       
                    </select>
                        
                    </select>

                    <div class="texto">Nutriólogo</div>
                    
                    <select id="nutriologoESCOM" class="primera_cita"  >
                       
                        <c:forEach items="${ListaESCOM}" var="item" varStatus="loop">
                          
                        <option>${item.nombre} ${item.ap_uno} ${item.ap_dos} ${item.no_cedula}</option>
                        
                        
                         
                        </c:forEach> 
                    </select>
                    
                    <div class="texto">Dirección</div>
                    <p id="direccion_nutri">
                        Av. Juan de Dios Bátiz S/N, Nueva Industrial Vallejo, 07738 Ciudad de México, CDMX
                    </p>

                    <div class="texto">Fecha de la cita</div>

                    <p id="fecha_cita">No seleccionada...</p>
                    
                    
                    <div class="texto">Horarios disponibles:</div>

                    <form:select path="horario" id="horas_disponibles" class="primera_cita" onchange="CambioHorario()" name="horas_disponibles">
                        <form:option value=""></form:option>
                    </form:select>
                   
                    <p>  <form:errors path="no_cita"/> </p>
                    <form:input id="valorCita" path="no_cita" type="hidden"/>
                    <form:input  path="no_boleta" value="${datosPaciente[0].no_boleta}" type="hidden"/>
                     <form:button id="boton" type="submit" name="CanalizarPsicolgo">Confirmar</form:button>
                    
                    </form:form>
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