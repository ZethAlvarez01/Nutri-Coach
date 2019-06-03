
<%-- 
    Document   : expedientePsicologico
    Created on : 26-may-2019, 23:54:27
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
    
    <!-- Hoja de estilo -->
    
    <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/dieta_nutriologo.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
     <link rel="stylesheet" href="<c:url value="/resource/estilos/barra_menu.css" />" />
    
    <!-- Scripts -->
    
    <script type="text/javascript" src="resource/scrips/script.js"/></script>  
    <script type="text/javascript" src="resource/scrips/barra_script.js"/></script>  
    <script type="text/javascript" src="resource/scrips/script_dieta.js"/></script>  
    <script type="text/javascript" src="resource/scrips/obtener_data.js"/></script>
    <script type="text/javascript" src="resource/scrips/filtro_smae.js"/></script>  


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
            <h1>Plan de alimentacion personalizado para  ${datosPaciente[0].nombre} ${datosPaciente[0].ap_uno} ${datosPaciente[0].ap_dos}</h1>
            
            <div id="tabla_contenido">
                <div id="datos">
                    <ul id="datos_p">
                        <li>Edad: ${datosPaciente[0].edad} años</li>
                        <li>Estatura: ${expedientePaciente[0].talla} cm</li>
                        <li>Peso: ${expedientePaciente[0].peso} Kg</li>
                        <li id="fecha"></li>

                        <script>
                            var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
                            var diasSemana = new Array("Domingo", "Lunes", "Martes", "MiÃ©rcoles", "Jueves", "Viernes", "SÃ¡bado");
                            var f = new Date();
                            let cadena = diasSemana[f.getDay()] + ", " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear();

                            document.getElementById("fecha").innerHTML = cadena;
                        </script>
                            <input type="submit" value="Cargar dieta" class="cargar">
                        
                    </ul>

                    <ul id="datos_c">
                        <li>Motivo de la consulta: ${Motivo}</li>
                        <li>Calculo de calorias actuales: ${calorias}</li>
                        <li>Ajuste en calorias: <input type="number" min="-${calorias}" onchange="suma(${calorias},this)"></li>
                        <script> 
                            function suma(calorias,cuadrito){
                                var cal=parseInt(calorias);
                                var cuadritoVal=parseInt(cuadrito.value);
                            var caloriasPC=cal+cuadritoVal;
                            document.getElementById("caloriasPC").innerHTML="Calorias por consumir: "+caloriasPC;
                            }
                            </script>
                            <li id="caloriasPC">Calorias por consumir: </li>
                    </ul>
                </div>
                     
                
                

            <div id="dieta">
                    <div id="Desayuno">
                        <h2>Desayuno</h2>
                        <input type="time" value="07:30" />
                        <div id="opcion_1_d" style="display: block;">
                            <h3>Opción 1</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>
                                
                            <div id="alimento_4" style="display:none;">
                                <h3>Alimento 4</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_2_d" style="display: block;">
                            <h3>Opción 2</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                <h3>Alimento 4</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_3_d" style="display: block;">
                            <h3>Opción 3</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                <h3>Alimento 4</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                    </div>
                    
                    <div id="Colacion_1">
                        <h2>colación I</h2>
                        <input type="time" value="11:30" />
                        <div id="opcion_1_c_1" style="display: block;">
                            <h3>Opción 1</h3>
                            <div id="alimento_1" style="display: block;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                 <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_2_c_1" style="display: block;">
                            <h3>Opción 2</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                    </div>
                
                    <div id="Comida">
                        <h2>Comida</h2>
                        <input type="time" value="14:30" />
                        <div id="opcion_1_c" style="display: block;">
                            <h3>Opción 1</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_2_c" style="display: block;">
                            <h3>Opción 2</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_3_c" style="display: block;">
                            <h3>Opción 3</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                    </div>
                    
                    <div id="Colacion_2">
                        <h2>colación II</h2>
                        <input type="time" value="18:00" />
                        <div id="opcion_1_c_2" style="display: block;">
                            <h3>Opción 1</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_2_c_2" style="display: block;">
                            <h3>Opción 2</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                    </div>
                        
                    <div id="Cena">
                        <h2>Cena</h2>
                        <input type="time" value="21:00" />
                        <div id="opcion_1_cena" style="display: block;">
                            <h3>Opción 1</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_2_cena" style="display: block;">
                            <h3>Opción 2</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
                            </div>
                        </div>
                        
                        <div id="opcion_3_cena" style="display: block;">
                            <h3>Opción 3</h3>
                            <div id="alimento_1" style="display: block;">
                                <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_1" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_1" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_1" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_1" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_2" style="display:none;">
                                <h3>Alimento 2</h3>
                                <p>Cantidad: </p><input id="cantidad_2" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_2" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_2" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_2" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_3" style="display:none;">
                                <h3>Alimento 3</h3>
                                <p>Cantidad: </p><input id="cantidad_3" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_3" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_3" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_3" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_4" style="display:none;">
                                 <h3>Alimento 1</h3>
                                <p>Cantidad: </p><input id="cantidad_4" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_4" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_4" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_4" style="display: block;" value="0"/>
                            </div>

                            <div id="alimento_5" style="display:none;">
                                <h3>Alimento 5</h3>
                                <p>Cantidad: </p><input id="cantidad_5" style="display: block;" value="0"/>
                                <p>Unidad: </p><input id="unidad_5" style="display: block;" value="0"/>
                                <p>Alimento: </p><input id="alimento_5" style="display: block;" value="0"/>
                                <p>Kcalorias: </p><input id="calorias_5" style="display: block;" value="0"/>
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
