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
            <a href="inicio.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
        </div>
    </div>

    <div id="contenido">
        <div class="container">
            <h1>Plan de alimentacion personalizado para ${paciente}</h1>
            
            <div id="smae">
                <a onclick="abrir_menu('t16')">Frutas</a>
                <a onclick="abrir_menu('t42')">Verduras</a>
                <a onclick="abrir_menu('t23')">Leguminosas</a>
                <p>Cereales</p>
                <a onclick="abrir_menu('t13')">Con grasa</a>
                <a onclick="abrir_menu('t14')">Sin grasa</a>
                <p>Alimentos de origen animal</p>
                <a onclick="abrir_menu('t7')">Muy bajos en grasa</a>
                <a onclick="abrir_menu('t5')">Bajos en grasa</a>
                <a onclick="abrir_menu('t6')">Moderados en grasa</a>
                <a onclick="abrir_menu('t4')">Altos en grasa</a>
                <p>Lacteos</p>
                <a onclick="abrir_menu('t20')">Leche descremada</a>
                <a onclick="abrir_menu('t22')">Leche semidescremada</a>
                <a onclick="abrir_menu('t21')">Leche entera</a>
                <a onclick="abrir_menu('t19')">Leche con azucar</a>
                
                <a onclick="abrir_menu('t1')">Aceites y grasas</a>
                <a onclick="abrir_menu('t2')">Aceites y grasas con proteinas</a>
                <p>Azucares</p>
                <a onclick="abrir_menu('t8')">Con grasa</a>
                <a onclick="abrir_menu('t9')">Sin grasa</a>
                
                <a onclick="abrir_menu('t3')">Alimentos libres en energia</a>
                <p>Bebidas</p>
                <a onclick="abrir_menu('t10')">Alcohólicas</a>
                <a onclick="abrir_menu('t11')">Frutales y con alcohol</a>
                <a onclick="abrir_menu('t37')">Productos Yakult</a>
                <a onclick="abrir_menu('t28')">Preparadas</a>
                <p>Platillos</p>
                <a onclick="abrir_menu('t29')">Desayunos</a>
                <a onclick="abrir_menu('t30')">Desayunos 2</a>
                <a onclick="abrir_menu('t31')">Guarniciones</a>
                <a onclick="abrir_menu('t17')">Guarniciones 2</a>
                <a onclick="abrir_menu('t34')">Sopas</a>
                <a onclick="abrir_menu('t38')">Sopas 2</a>
                <a onclick="abrir_menu('t32')">Platos fuertes</a>
                <a onclick="abrir_menu('t35')">Platos fuertes 2</a>
                <a onclick="abrir_menu('t33')">Postres</a>
                <a onclick="abrir_menu('t36')">Postres 2</a>
                <p>Comida rápida</p>
                <a onclick="abrir_menu('t12')">BURGER KING</a>
                <a onclick="abrir_menu('t25')">Mc DONALS</a>
                <a onclick="abrir_menu('t43')">WENDY's</a>
                <a onclick="abrir_menu('t15')">Domino's pizza</a>
                <a onclick="abrir_menu('t26')">Papa John's Pizza</a>
                <a onclick="abrir_menu('t27')">Pizza hut</a>
                <a onclick="abrir_menu('t24')">Little Caesars</a>
                <a onclick="abrir_menu('t27')">Kentucky Fried Chicken</a>
                <a onclick="abrir_menu('t40')">Subway</a>
                <a onclick="abrir_menu('t41')">Taco Bell</a>
                <a onclick="abrir_menu('t39')">Starbucks</a>
                

                
                <ul id="t1" style="display: none;">
                    <c:forEach items="${datoT1}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t2" style="display: none;">
                    <c:forEach items="${datoT2}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t3" style="display: none;">
                    <c:forEach items="${datoT3}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t4" style="display: none;">
                    <c:forEach items="${datoT4}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t5" style="display: none;">
                    <c:forEach items="${datoT5}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t6" style="display: none;">
                    <c:forEach items="${datoT6}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t7" style="display: none;">
                    <c:forEach items="${datoT7}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t8" style="display: none;">
                    <c:forEach items="${datoT8}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t9" style="display: none;">
                    <c:forEach items="${datoT9}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t10" style="display: none;">
                    <c:forEach items="${datoT10}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t11" style="display: none;">
                    <c:forEach items="${datoT11}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t12" style="display: none;">
                    <c:forEach items="${datoT12}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t13" style="display: none;">
                    <c:forEach items="${datoT13}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t14" style="display: none;">
                    <c:forEach items="${datoT14}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t15" style="display: none;">
                    <c:forEach items="${datoT15}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t16" style="display: none;">
                    <c:forEach items="${datoT16}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t17" style="display: none;">
                    <c:forEach items="${datoT17}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t18" style="display: none;">
                    <c:forEach items="${datoT18}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t19" style="display: none;">
                    <c:forEach items="${datoT19}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t20" style="display: none;">
                    <c:forEach items="${datoT20}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t21" style="display: none;">
                    <c:forEach items="${datoT21}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t22" style="display: none;">
                    <c:forEach items="${datoT22}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t23" style="display: none;">
                    <c:forEach items="${datoT23}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t24" style="display: none;">
                    <c:forEach items="${datoT24}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t25" style="display: none;">
                    <c:forEach items="${datoT25}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t26" style="display: none;">
                    <c:forEach items="${datoT26}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t27" style="display: none;">
                    <c:forEach items="${datoT27}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t28" style="display: none;">
                    <c:forEach items="${datoT28}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t29" style="display: none;">
                    <c:forEach items="${datoT29}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t30" style="display: none;">
                    <c:forEach items="${datoT30}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t31" style="display: none;">
                    <c:forEach items="${datoT31}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t32" style="display: none;">
                    <c:forEach items="${datoT32}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t33" style="display: none;">
                    <c:forEach items="${datoT33}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t34" style="display: none;">
                    <c:forEach items="${datoT34}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t35" style="display: none;">
                    <c:forEach items="${datoT35}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t36" style="display: none;">
                    <c:forEach items="${datoT36}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t37" style="display: none;">
                    <c:forEach items="${datoT37}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t38" style="display: none;">
                    <c:forEach items="${datoT38}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t39" style="display: none;">
                    <c:forEach items="${datoT39}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t40" style="display: none;">
                    <c:forEach items="${datoT40}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t41" style="display: none;">
                    <c:forEach items="${datoT41}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t42" style="display: none;">
                    <c:forEach items="${datoT42}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
                <ul id="t43" style="display: none;">
                    <c:forEach items="${datoT43}" var="item">
                        <li><c:out value="${item}"/></li>
                    </c:forEach> 
                </ul>
            </div>
            
            <div id="tabla_contenido">
                <div id="datos">
                    <ul id="datos_p">
                        <li>Edad: ${Edad}</li>
                        <li>Estatura: ${Estatura}</li>
                        <li>Peso: ${Peso}</li>
                        <li id="fecha"></li>

                        <script>
                            var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
                            var diasSemana = new Array("Domingo", "Lunes", "Martes", "MiÃ©rcoles", "Jueves", "Viernes", "SÃ¡bado");
                            var f = new Date();
                            let cadena = diasSemana[f.getDay()] + ", " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear();

                            document.getElementById("fecha").innerHTML = cadena;
                        </script>
                        <input type="button" value="Cargar dieta" class="cargar">
                    </ul>

                    <ul id="datos_c">
                        <li>Motivo de la consulta: ${Motivo}</li>
                        <li>Calculo de calorias actuales: ${Calorias}</li>
                        <li>Ajuste en calorias: <input type="number"></li>
                        <li>Calorias por consumir: ${Calorias_x_consumir}</li>
                    </ul>
                </div>

                <div id="dieta">
                    <div id="titulo_t" class="bordes">Tipo de comida</div>
                    <div id="titulo_op1" class="bordes_c">Opción 1</div>
                    <div id="titulo_op2" class="bordes_c">Opción 2</div>
                    <div id="titulo_op3" class="bordes_c">Opción 3</div>

                    <div id="desayuno_t" class="bordes">
                        <div id="tipo" class="texto_tipo" onclick="editar(this)">Desayuno <br><br>7:30 am</div>
                        <input type="time" id="edit_h" style="display:none;" min="00:00" max="12:00" value="07:30">
                        <a id="boton_h2" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar()">Cambiar</a>
                    </div>
                    <div id="desayuno_op1" class="bordes_c">
                        <textarea id="desayuno_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="desayuno_op2" class="bordes_c">
                        <textarea id="desayuno_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="desayuno_op3" class="bordes_c">
                        <textarea id="desayuno_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>

                    <div id="colacionm_t" class="bordes">
                        <div id="tipo_c1" class="texto_tipo" onclick="editar_c1(this)">Colación matutina <br><br>11:30 am</div>
                        <input type="time" id="edit_h_c1" style="display:none;" min="00:00" max="12:00" value="11:30">
                        <a id="boton_h2_c1" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_c1()">Cambiar</a>
                    </div>
                    <div id="colacionm_op1" class="bordes_c">
                        <textarea id="colacionm_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="colacionm_op2" class="bordes_c">
                        <textarea id="colacionm_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="colacionm_op3" class="bordes_c"></div>

                    <div id="comida_t" class="bordes">
                        <div id="tipo_c" class="texto_tipo" onclick="editar_c(this)">Comida <br><br>2:30 pm</div>
                        <input type="time" id="edit_h_c" style="display:none;" min="00:00" max="12:00" value="14:30">
                        <a id="boton_h2_c" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_c()">Cambiar</a>
                    </div>
                    <div id="comida_op1" class="bordes_c">
                        <textarea id="comida_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="comida_op2" class="bordes_c">
                        <textarea id="comida_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="comida_op3" class="bordes_c">
                        <textarea id="comida_op3_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>

                    <div id="colacionv_t" class="bordes">
                        <div id="tipo_c2" class="texto_tipo" onclick="editar_c2(this)">Colación vespertina <br><br>6:00 pm</div>
                        <input type="time" id="edit_h_c2" style="display:none;" min="00:00" max="12:00" value="18:30">
                        <a id="boton_h2_c2" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_c2()">Cambiar</a>
                    </div>
                    <div id="colacionv_op1" class="bordes_c">
                        <textarea id="colacionv_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="colacionv_op2" class="bordes_c">
                        <textarea id="colacionv_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="colacionv_op3" class="bordes_c"></div>

                    <div id="cena_t" class="bordes">
                        <div id="tipo_ce" class="texto_tipo" onclick="editar_ce(this)">Cena <br><br>9:00 pm</div>
                        <input type="time" id="edit_h_ce" style="display:none;" min="00:00" max="12:00" value="20:30">
                        <a id="boton_h2_ce" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_ce()">Cambiar</a>
                    </div>
                    <div id="cena_op1" class="bordes_c">
                        <textarea id="cena_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="cena_op2" class="bordes_c">
                        <textarea id="cena_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>
                    <div id="cena_op3" class="bordes_c">
                        <textarea id="cena_op3_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux">Buscar alimentos</a>
                    </div>

                    <div id="botones_1">
                        <input type="button" value="Guardar" class="guardar" onclick="convertirJSON()">
                    </div>

                    <div id="botones_2">
                        <input type="button" value="Guardar y enviar" class="guardar">
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