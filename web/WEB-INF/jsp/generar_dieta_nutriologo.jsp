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
           
            
            <div id="smae" style="display:none;">
                
                <a class="categoria" id="cerrar_btn" onclick="abrir_menu('x')">X</a>
                <p>Categorias</p>
                <a class="categoria" onclick="abrir_menu('t16')">Frutas</a>
                <a class="categoria" onclick="abrir_menu('t42')">Verduras</a>
                <a class="categoria" onclick="abrir_menu('t23')">Leguminosas</a>
                <a class="categoria" onclick="abrir_menu('t1')">Aceites y grasas</a>
                <a class="categoria" onclick="abrir_menu('t2')">Aceites y grasas con proteinas</a>
                <a class="categoria" onclick="abrir_menu('t3')">Alimentos libres en energia</a>
                <p>Cereales</p>
                <a class="categoria" onclick="abrir_menu('t13')">Con grasa</a>
                <a class="categoria" onclick="abrir_menu('t14')">Sin grasa</a>
                <p>Alimentos de origen animal</p>
                <a class="categoria" onclick="abrir_menu('t7')">Muy bajos en grasa</a>
                <a class="categoria" onclick="abrir_menu('t5')">Bajos en grasa</a>
                <a class="categoria" onclick="abrir_menu('t6')">Moderados en grasa</a>
                <a class="categoria" onclick="abrir_menu('t4')">Altos en grasa</a>
                <p>Lacteos</p>
                <a class="categoria" onclick="abrir_menu('t20')">Leche descremada</a>
                <a class="categoria" onclick="abrir_menu('t22')">Leche semidescremada</a>
                <a class="categoria" onclick="abrir_menu('t21')">Leche entera</a>
                <a class="categoria" onclick="abrir_menu('t19')">Leche con azucar</a>
                <p>Azucares</p>
                <a class="categoria" onclick="abrir_menu('t8')">Con grasa</a>
                <a class="categoria" onclick="abrir_menu('t9')">Sin grasa</a>
                <p>Bebidas</p>
                <a class="categoria" onclick="abrir_menu('t10')">Alcohólicas</a>
                <a class="categoria" onclick="abrir_menu('t11')">Frutales y con alcohol</a>
                <a class="categoria" onclick="abrir_menu('t37')">Productos Yakult</a>
                <a class="categoria" onclick="abrir_menu('t28')">Preparadas</a>
                <p>Platillos</p>
                <a class="categoria" onclick="abrir_menu('t29')">Desayunos</a>
                <a class="categoria" onclick="abrir_menu('t30')">Desayunos 2</a>
                <a class="categoria" onclick="abrir_menu('t31')">Guarniciones</a>
                <a class="categoria" onclick="abrir_menu('t17')">Guarniciones 2</a>
                <a class="categoria" onclick="abrir_menu('t34')">Sopas</a>
                <a class="categoria" onclick="abrir_menu('t38')">Sopas 2</a>
                <a class="categoria" onclick="abrir_menu('t32')">Platos fuertes</a>
                <a class="categoria" onclick="abrir_menu('t35')">Platos fuertes 2</a>
                <a class="categoria" onclick="abrir_menu('t33')">Postres</a>
                <a class="categoria" onclick="abrir_menu('t36')">Postres 2</a>
                <p>Comida rápida</p>
                <a class="categoria" onclick="abrir_menu('t12')">BURGER KING</a>
                <a class="categoria" onclick="abrir_menu('t25')">Mc DONALS</a>
                <a class="categoria" onclick="abrir_menu('t43')">WENDY's</a>
                <a class="categoria" onclick="abrir_menu('t15')">Domino's Pizza</a>
                <a class="categoria" onclick="abrir_menu('t26')">Papa John's Pizza</a>
                <a class="categoria" onclick="abrir_menu('t27')">Pizza hut</a>
                <a class="categoria" onclick="abrir_menu('t24')">Little Caesars</a>
                <a class="categoria" onclick="abrir_menu('t18')">Kentucky Fried Chicken</a>
                <a class="categoria" onclick="abrir_menu('t40')">Subway</a>
                <a class="categoria" onclick="abrir_menu('t41')">Taco Bell</a>
                <a class="categoria" onclick="abrir_menu('t39')">Starbucks</a>
                
                
                <!-- Aceites y grasas -->
                <div id="t1" style="display: none;">
                    <table>
                        <caption>Aceites y grasas</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>AG saturados (gramos)</th>
                              <th>AG Monoinsaturados (gramos)</th>
                              <th>AG Polisaturados (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Sodio (gramos)</th>
                            </tr>

                            <c:forEach items="${datoT1}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.AGSaturadas_g}"/></td>
                                    <td><c:out value="${item.AGMonosaturados_g}"/></td>
                                    <td><c:out value="${item.AGPoliisaturados_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- Aceites y grasas con proteinas -->
                <div id="t2" style="display: none;">
                    <table>
                        <caption>Aceites y grasas con proteinas</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>AG saturados (gramos)</th>
                              <th>AG Monoinsaturados (gramos)</th>
                              <th>AG Polisaturados (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Sodio (gramos)</th>
                            </tr>

                            <c:forEach items="${datoT2}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.AGSaturadas_g}"/></td>
                                    <td><c:out value="${item.AGMonosaturados_g}"/></td>
                                    <td><c:out value="${item.AGPoliisaturados_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Alimentos libres en energia -->
                <div id="t3" style="display: none;">
                    <table>
                        <caption>Alimentos libres en energia</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Sodio (gramos)</th>
                            </tr>

                            <c:forEach items="${datoT3}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Alimentos de origen animal altos en grasa -->
                <div id="t4" style="display: none;">
                    <table>
                        <caption>Alimentos de origen animal altos en grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Hierro (miligramos)</th>
                              <th>Sodio (gramos)</th>
                              <th>Selenio (miligramos)</th>
                            </tr>

                            <c:forEach items="${datoT4}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.hierro_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.selenio_mg}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Alimentos de origen animal bajos en grasa -->
                <div id="t5" style="display: none;">
                    <table>
                        <caption>Alimentos de origen animal bajos en grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Hierro (miligramos)</th>
                              <th>Sodio (gramos)</th>
                              <th>Selenio (miligramos)</th>
                            </tr>

                            <c:forEach items="${datoT5}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}'),1">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.hierro_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.selenio_mg}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Alimentos de origen animal moderados en grasa -->
                <div id="t6" style="display: none;">
                    <table>
                        <caption>Alimentos de origen animal moderados en grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Hierro (miligramos)</th>
                              <th>Sodio (gramos)</th>
                              <th>Selenio (miligramos)</th>
                            </tr>

                            <c:forEach items="${datoT6}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.hierro_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.selenio_mg}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Alimentos de origen animal muy bajos en grasa -->
                <div id="t7" style="display: none;">
                    <table>
                        <caption>Alimentos de origen animal muy bajos en grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Hierro (miligramos)</th>
                              <th>Sodio (gramos)</th>
                              <th>Selenio (miligramos)</th>
                            </tr>

                            <c:forEach items="${datoT7}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.hierro_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.selenio_mg}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>

                <!-- Azucares con grasa -->
                <div id="t8" style="display: none;">
                    <table>
                        <caption>Azucares con grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Sodio (gramos)</th>
                              <th>Azucar por equivalente (gramos)</th>
                              <th>Indice glicemico</th>
                              <th>Carga glicemica</th>
                            </tr>

                            <c:forEach items="${datoT8}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                    <td><c:out value="${item.indiceGlicemico}"/></td>
                                    <td><c:out value="${item.cargaGlicemica}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Azucares sin grasa -->
                <div id="t9" style="display: none;">
                    <table>
                        <caption>Azucares sin grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Sodio (gramos)</th>
                              <th>Azucar por equivalente (gramos)</th>
                              <th>Indice glicemico</th>
                              <th>Carga glicemica</th>
                            </tr>

                            <c:forEach items="${datoT9}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                    <td><c:out value="${item.indiceGlicemico}"/></td>
                                    <td><c:out value="${item.cargaGlicemica}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Bebidas alcohólicas -->
                <div id="t10" style="display: none;">
                    <table>
                        <caption>Bebidas alcohólicas</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol (gramos)</th>
                            </tr>

                            <c:forEach items="${datoT10}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.etanol_g}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Bebidas frutales y con alcohol -->
                <div id="t11" style="display: none;">
                    <table>
                        <caption>Bebidas frutales y con alcohol</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol (gramos)</th>
                              <th>Composición en equivalentes</th>
                              <th> </th>
                              <th> </th>
                            </tr>

                            <c:forEach items="${datoT11}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.kcal}"/></td>
                                    <td><c:out value="${item.proteinas_g}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratos_de_carbono}"/></td>
                                    <td><c:out value="${item.etanol}"/></td>
                                    <td><c:out value="${item.ComposicionEquivalente1}"/></td>
                                    <td><c:out value="${item.ComposicionEquivalente2}"/></td>
                                    <td><c:out value="${item.ComposicionEquivalente3}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- BURGER KING -->
                <div id="t12" style="display: none;">
                    <table>
                        <caption>BURGER KING</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT12}" var="item">
                                <tr onclick="obtener('${item.alimento}'),' ',' ',0">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Cereales con grasa -->
                <div id="t13" style="display: none;">
                    <table>
                        <caption>Cereales con grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Fibra (gramos)</th>
                              <th>Ácido Fólico (µg)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Hierro (miligramos)</th>
                              <th>Sodio (miligramos)</th>
                              <th>Azucar por equivalente (gramos)</th>
                              <th>Indice glicemico</th>
                              <th>Carga glicemica</th>
                            </tr>

                            <c:forEach items="${datoT13}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.Fibra_g}"/></td>
                                    <td><c:out value="${item.acidoFolico_ug}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.hierro_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                    <td><c:out value="${item.indiceGlicemico}"/></td>
                                    <td><c:out value="${item.cargaGlicemica}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Cereales sin grasa -->
                <div id="t14" style="display: none;">
                    <table>
                        <caption>Cereales sin grasa</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Fibra (gramos)</th>
                              <th>Ácido Fólico (µg)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Hierro (miligramos)</th>
                              <th>Sodio (miligramos)</th>
                              <th>Azucar por equivalente (gramos)</th>
                              <th>Indice glicemico</th>
                              <th>Carga glicemica</th>
                            </tr>

                            <c:forEach items="${datoT14}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.Fibra_g}"/></td>
                                    <td><c:out value="${item.acidoFolico_ug}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.hierro_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                    <td><c:out value="${item.indiceGlicemico}"/></td>
                                    <td><c:out value="${item.cargaGlicemica}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Domino's Pizza -->
                <div id="t15" style="display: none;">
                    <table>
                        <caption>Domino's Pizza</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias por equivalente</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT15}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Frutas -->
                <div id="t16" style="display: none;">
                    <table>
                        <caption>Frutas</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Fibra (gramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Ácido ascorbico (miligramos)</th>
                              <th>Ácido Fólico (µg)</th>
                              <th>Hierro NO HEM (mg)</th>
                              <th>Potasio (miligramos)</th>
                              <th>Azucar por equivalente (gramos)</th>
                              <th>Indice glicemico</th>
                              <th>Carga glicemica</th>
                            </tr>

                            <c:forEach items="${datoT16}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.Fibra_g}"/></td>
                                    <td><c:out value="${item.vitamiaA_ugRE}"/></td>
                                    <td><c:out value="${item.acidoAscorbico_mg}"/></td>
                                    <td><c:out value="${item.acidoFolico_ug}"/></td>
                                    <td><c:out value="${item.hierroNO_HEM_mg}"/></td>
                                    <td><c:out value="${item.potasio_mg}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                    <td><c:out value="${item.indiceGlicemico}"/></td>
                                    <td><c:out value="${item.cargaGlicemica}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- Guarniciones -->
                <div id="t17" style="display: none;">
                    <table>
                        <caption>Guarniciones 2</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol (gramos)</th>
                              <th>Composición en equivalentes</th>
                              <th> </th>
                              <th> </th>
                            </tr>

                            <c:forEach items="${datoT17}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol}"/></td>
                                    <td><c:out value="${item.ComposicionEquivalente1}"/></td>
                                    <td><c:out value="${item.ComposicionEquivalente2}"/></td>
                                    <td><c:out value="${item.ComposicionEquivalente3}"/></td>
                                    <td><c:out value="${item.composicionEquivalente4}"/></td>
                                </tr>
                                
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Kentucky Fried Chicken -->
                <div id="t18" style="display: none;">
                    <table>
                        <caption>Kentucky Fried Chicken</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT18}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Leche con azucar -->
                <div id="t19" style="display: none;">
                    <table>
                        <caption>Leche con azucar</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Sodio (miligramos)</th>
                              <th>Azucar por equivalemnte (gramos)</th>
                            </tr>

                            <c:forEach items="${datoT19}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- Leche descremada -->
                <div id="t20" style="display: none;">
                    <table>
                        <caption>Leche descremada</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Sodio (miligramos)</th>
                            </tr>

                            <c:forEach items="${datoT20}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- Leche entera -->
                <div id="t21" style="display: none;">
                    <table>
                        <caption>Leche entera</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Sodio (miligramos)</th>
                            </tr>

                            <c:forEach items="${datoT21}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- Leche semidescremada -->
                <div id="t22" style="display: none;">
                    <table>
                        <caption>Leche semidescremada</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Sodio (miligramos)</th>
                            </tr>

                            <c:forEach items="${datoT22}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- Leguminosas -->
                <div id="t23" style="display: none;">
                    <table>
                        <caption>Leguminosas</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Fibra (gramos)</th>
                              <th>Hierro NO HEM (mg)</th>
                              <th>Selenio (µg)</th>
                              <th>Sodio (miligramos)</th>
                              <th>Fosforo (miligramos)</th>
                              <th>Potasio (miligramos)</th>
                              <th>Azucar por equivalente (gramos)</th>
                              <th>Indice glicemico</th>
                              <th>Carga glicemica</th>
                            </tr>

                            <c:forEach items="${datoT23}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.Fibra_g}"/></td>
                                    <td><c:out value="${item.hierroNO_HEM_mg}"/></td>                        
                                    <td><c:out value="${item.selenio_ug}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.fosforo_mg}"/></td>                                   
                                    <td><c:out value="${item.potasio_mg}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                    <td><c:out value="${item.indiceGlicemico}"/></td>
                                    <td><c:out value="${item.cargaGlicemica}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- Little Caesars -->
                <div id="t24" style="display: none;">
                    <table>
                        <caption>Little Caesars</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias por equivalente</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT24}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Mc DONALS -->
                <div id="t25" style="display: none;">
                    <table>
                        <caption>Mc DONALS</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT25}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Papa John's Pizza -->
                <div id="t26" style="display: none;">
                    <table>
                        <caption>Papa John's Pizza</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT26}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Pizza Hut -->
                <div id="t27" style="display: none;">
                    <table>
                        <caption>Pizza Hut</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT27}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Bebidas preparadas -->
                <div id="t28" style="display: none;">
                    <table>
                        <caption>Bebidas frutales y con alcohol</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Cereales sin grasa</th>
                              <th>Cereales con grasa</th>
                              <th>Leguminosas</th>
                              <th>AOA muy bajos en grasa</th>
                              <th>AOA bajos en grasa</th>
                              <th>AOA moderados en grasa</th>
                              <th>AOA altos en grasa</th>
                              <th>Leche descremada</th>
                              <th>Leche semidescremada</th>
                              <th>Leche entera</th>
                              <th>Leche con azucar</th>
                              <th>Aceites y grasas sin proteinas</th>
                              <th>Aceites y grasas con proteinas</th>
                              <th>Azucar</th>
                              <th>Azucar con grasa</th>
                              <th>Etanol</th>
                              <th>Alimentos libres de energia</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                            </tr>

                            <c:forEach items="${datoT28}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidas}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidas}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.fruta}"/></td>
                                    <td><c:out value="${item.cerealSINGrasa}"/></td>
                                    <td><c:out value="${item.cerealCONGrasa}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.AOAMuyBajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOABajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAModeradoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAAltoEnGrasa}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.lecheSemidescremada}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheConAzucar}"/></td>
                                    <td><c:out value="${item.AYGSinProteinas}"/></td>
                                    <td><c:out value="${item.AYGConProteinas}"/></td>
                                    <td><c:out value="${item.azucar}"/></td>
                                    <td><c:out value="${item.azucarConGrasa}"/></td>
                                    <td><c:out value="${item.etanol1}"/></td>
                                    <td><c:out value="${item.AlimentosLibresDeEnergia}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol2}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos de desayuno -->
                <div id="t29" style="display: none;">
                    <table>
                        <caption>Platillos de desayuno</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Cereales sin grasa</th>
                              <th>Cereales con grasa</th>
                              <th>Leguminosas</th>
                              <th>AOA muy bajos en grasa</th>
                              <th>AOA bajos en grasa</th>
                              <th>AOA moderados en grasa</th>
                              <th>AOA altos en grasa</th>
                              <th>Leche descremada</th>
                              <th>Leche semidescremada</th>
                              <th>Leche entera</th>
                              <th>Leche con azucar</th>
                              <th>Aceites y grasas sin proteinas</th>
                              <th>Aceites y grasas con proteinas</th>
                              <th>Azucar</th>
                              <th>Azucar con grasa</th>
                              <th>Etanol</th>
                              <th>Alimentos libres de energia</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                            </tr>

                            <c:forEach items="${datoT29}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidas}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidas}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.fruta}"/></td>
                                    <td><c:out value="${item.cerealSINGrasa}"/></td>
                                    <td><c:out value="${item.cerealCONGrasa}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.AOAMuyBajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOABajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAModeradoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAAltoEnGrasa}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.lecheSemidescremada}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheConAzucar}"/></td>
                                    <td><c:out value="${item.AYGSinProteinas}"/></td>
                                    <td><c:out value="${item.AYGConProteinas}"/></td>
                                    <td><c:out value="${item.azucar}"/></td>
                                    <td><c:out value="${item.azucarConGrasa}"/></td>
                                    <td><c:out value="${item.etanol1}"/></td>
                                    <td><c:out value="${item.AlimentosLibresDeEnergia}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos de desayuno 2 -->
                <div id="t30" style="display: none;">
                    <table>
                        <caption>Platillos de desayuno 2</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                              <th>Composición de equivalentes</th>
                              <th> </th>
                              <th> </th>
                              <th> </th>
                            </tr>

                            <c:forEach items="${datoT30}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol}"/></td>
                                    <td><c:out value="${item.composicionEquivalente1}"/></td>
                                    <td><c:out value="${item.composicionEquivalente2}"/></td>
                                    <td><c:out value="${item.composicionEquivalente3}"/></td>
                                    <td><c:out value="${item.composicionEquivalente4}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos de guarniciones -->
                <div id="t31" style="display: none;">
                    <table>
                        <caption>Platillos de guarniciones</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Cereales sin grasa</th>
                              <th>Cereales con grasa</th>
                              <th>Leguminosas</th>
                              <th>AOA muy bajos en grasa</th>
                              <th>AOA bajos en grasa</th>
                              <th>AOA moderados en grasa</th>
                              <th>AOA altos en grasa</th>
                              <th>Leche descremada</th>
                              <th>Leche semidescremada</th>
                              <th>Leche entera</th>
                              <th>Leche con azucar</th>
                              <th>Aceites y grasas sin proteinas</th>
                              <th>Aceites y grasas con proteinas</th>
                              <th>Azucar</th>
                              <th>Azucar con grasa</th>
                              <th>Etanol</th>
                              <th>Alimentos libres de energia</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                            </tr>

                            <c:forEach items="${datoT31}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidas}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidas}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.fruta}"/></td>
                                    <td><c:out value="${item.cerealSINGrasa}"/></td>
                                    <td><c:out value="${item.cerealCONGrasa}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.AOAMuyBajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOABajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAModeradoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAAltoEnGrasa}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.lecheSemidescremada}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheConAzucar}"/></td>
                                    <td><c:out value="${item.AYGSinProteinas}"/></td>
                                    <td><c:out value="${item.AYGConProteinas}"/></td>
                                    <td><c:out value="${item.azucar}"/></td>
                                    <td><c:out value="${item.azucarConGrasa}"/></td>
                                    <td><c:out value="${item.etanol1}"/></td>
                                    <td><c:out value="${item.AlimentosLibresDeEnergia}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol2}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos platos fuertes -->
                <div id="t32" style="display: none;">
                    <table>
                        <caption>Platillos platos fuertes</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Cereales sin grasa</th>
                              <th>Cereales con grasa</th>
                              <th>Leguminosas</th>
                              <th>AOA muy bajos en grasa</th>
                              <th>AOA bajos en grasa</th>
                              <th>AOA moderados en grasa</th>
                              <th>AOA altos en grasa</th>
                              <th>Leche descremada</th>
                              <th>Leche semidescremada</th>
                              <th>Leche entera</th>
                              <th>Leche con azucar</th>
                              <th>Aceites y grasas sin proteinas</th>
                              <th>Aceites y grasas con proteinas</th>
                              <th>Azucar</th>
                              <th>Azucar con grasa</th>
                              <th>Etanol</th>
                              <th>Alimentos libres de energia</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                            </tr>

                            <c:forEach items="${datoT32}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidas}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidas}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.fruta}"/></td>
                                    <td><c:out value="${item.cerealSINGrasa}"/></td>
                                    <td><c:out value="${item.cerealCONGrasa}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.AOAMuyBajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOABajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAModeradoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAAltoEnGrasa}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.lecheSemidescremada}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheConAzucar}"/></td>
                                    <td><c:out value="${item.AYGSinProteinas}"/></td>
                                    <td><c:out value="${item.AYGConProteinas}"/></td>
                                    <td><c:out value="${item.azucar}"/></td>
                                    <td><c:out value="${item.azucarConGrasa}"/></td>
                                    <td><c:out value="${item.etanol1}"/></td>
                                    <td><c:out value="${item.AlimentosLibresDeEnergia}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol2}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos postres -->
                <div id="t33" style="display: none;">
                    <table>
                        <caption>Platillos postres</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Cereales sin grasa</th>
                              <th>Cereales con grasa</th>
                              <th>Leguminosas</th>
                              <th>AOA muy bajos en grasa</th>
                              <th>AOA bajos en grasa</th>
                              <th>AOA moderados en grasa</th>
                              <th>AOA altos en grasa</th>
                              <th>Leche descremada</th>
                              <th>Leche semidescremada</th>
                              <th>Leche entera</th>
                              <th>Leche con azucar</th>
                              <th>Aceites y grasas sin proteinas</th>
                              <th>Aceites y grasas con proteinas</th>
                              <th>Azucar</th>
                              <th>Azucar con grasa</th>
                              <th>Etanol</th>
                              <th>Alimentos libres de energia</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                            </tr>

                            <c:forEach items="${datoT33}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidas}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidas}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.fruta}"/></td>
                                    <td><c:out value="${item.cerealSINGrasa}"/></td>
                                    <td><c:out value="${item.cerealCONGrasa}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.AOAMuyBajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOABajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAModeradoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAAltoEnGrasa}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.lecheSemidescremada}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheConAzucar}"/></td>
                                    <td><c:out value="${item.AYGSinProteinas}"/></td>
                                    <td><c:out value="${item.AYGConProteinas}"/></td>
                                    <td><c:out value="${item.azucar}"/></td>
                                    <td><c:out value="${item.azucarConGrasa}"/></td>
                                    <td><c:out value="${item.etanol1}"/></td>
                                    <td><c:out value="${item.AlimentosLibresDeEnergia}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol2}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos sopas -->
                <div id="t34" style="display: none;">
                    <table>
                        <caption>Platillos sopas</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Cereales sin grasa</th>
                              <th>Cereales con grasa</th>
                              <th>Leguminosas</th>
                              <th>AOA muy bajos en grasa</th>
                              <th>AOA bajos en grasa</th>
                              <th>AOA moderados en grasa</th>
                              <th>AOA altos en grasa</th>
                              <th>Leche descremada</th>
                              <th>Leche semidescremada</th>
                              <th>Leche entera</th>
                              <th>Leche con azucar</th>
                              <th>Aceites y grasas sin proteinas</th>
                              <th>Aceites y grasas con proteinas</th>
                              <th>Azucar</th>
                              <th>Azucar con grasa</th>
                              <th>Etanol</th>
                              <th>Alimentos libres de energia</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                            </tr>

                            <c:forEach items="${datoT34}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidas}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidas}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.fruta}"/></td>
                                    <td><c:out value="${item.cerealSINGrasa}"/></td>
                                    <td><c:out value="${item.cerealCONGrasa}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.AOAMuyBajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOABajoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAModeradoEnGrasa}"/></td>
                                    <td><c:out value="${item.AOAAltoEnGrasa}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.lecheSemidescremada}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheConAzucar}"/></td>
                                    <td><c:out value="${item.AYGSinProteinas}"/></td>
                                    <td><c:out value="${item.AYGConProteinas}"/></td>
                                    <td><c:out value="${item.azucar}"/></td>
                                    <td><c:out value="${item.azucarConGrasa}"/></td>
                                    <td><c:out value="${item.etanol1}"/></td>
                                    <td><c:out value="${item.AlimentosLibresDeEnergia}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol2}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos platos fuertes 2 -->
                <div id="t35" style="display: none;">
                    <table>
                        <caption>Platillos de desayuno 2</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                              <th>Composición de equivalentes</th>
                              <th> </th>
                              <th> </th>
                              <th> </th>
                            </tr>

                            <c:forEach items="${datoT35}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol}"/></td>
                                    <td><c:out value="${item.composicionEquivalente1}"/></td>
                                    <td><c:out value="${item.composicionEquivalente2}"/></td>
                                    <td><c:out value="${item.composicionEquivalente3}"/></td>
                                    <td><c:out value="${item.composicionEquivalente4}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos postres 2 -->
                <div id="t36" style="display: none;">
                    <table>
                        <caption>Platillos de desayuno 2</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                              <th>Composición de equivalentes</th>
                              <th> </th>
                              <th> </th>
                              <th> </th>
                            </tr>

                            <c:forEach items="${datoT36}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol}"/></td>
                                    <td><c:out value="${item.composicionEquivalente1}"/></td>
                                    <td><c:out value="${item.composicionEquivalente2}"/></td>
                                    <td><c:out value="${item.composicionEquivalente3}"/></td>
                                    <td><c:out value="${item.composicionEquivalente4}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Productos Yakult -->
                <div id="t37" style="display: none;">
                    <table>
                        <caption>Productos Yakult</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Colesterol (miligramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Calcio (miligramos)</th>
                              <th>Sodio (miligramos)</th>
                              <th>Tipo</th>
                              <th>Azucar por equivalencia (gramos)</th>
                            </tr>

                            <c:forEach items="${datoT37}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.colesterol_mg}"/></td>
                                    <td><c:out value="${item.vitaminaA_ugRE}"/></td>
                                    <td><c:out value="${item.calcio_mg}"/></td>
                                    <td><c:out value="${item.sodio_mg}"/></td>
                                    <td><c:out value="${item.tipo}"/></td>
                                    <td><c:out value="${item.azucarPorEquivalente_g}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Platillos sopas 2 -->
                <div id="t38" style="display: none;">
                    <table>
                        <caption>Platillos sopas 2</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Kilocalorias</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Etanol</th>
                              <th>Composición de equivalentes</th>
                              <th> </th>
                              <th> </th>
                            </tr>

                            <c:forEach items="${datoT38}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidad}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidad}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.proteinas}"/></td>
                                    <td><c:out value="${item.lipidos}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono}"/></td>
                                    <td><c:out value="${item.etanol}"/></td>
                                    <td><c:out value="${item.composicionEquivalente1}"/></td>
                                    <td><c:out value="${item.composicionEquivalente2}"/></td>
                                    <td><c:out value="${item.composicionEquivalente3}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Starbucks -->
                <div id="t39" style="display: none;">
                    <table>
                        <caption>Starbucks</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT39}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Subway -->
                <div id="t40" style="display: none;">
                    <table>
                        <caption>Subway</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT40}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Taco Bell -->
                <div id="t41" style="display: none;">
                    <table>
                        <caption>Taco Bell</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT41}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
                <!-- Verduras -->
                <div id="t42" style="display: none;">
                    <table>
                        <caption>Verduras</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Cantidad sugerida</th>
                              <th>Unidad</th>
                              <th>Peso Bruto redondeado (gramos)</th>
                              <th>Peso neto (gramos)</th>
                              <th>Energia (kcal)</th>
                              <th>Proteinas (gramos)</th>
                              <th>Lipidos (gramos)</th>
                              <th>Hidratos de carbono (gramos)</th>
                              <th>Fibra (gramos)</th>
                              <th>Vitamina A (µg RE)</th>
                              <th>Ácido ascorbico (miligramos)</th>
                              <th>Ácido Fólico (µg)</th>
                              <th>Hierro NO HEM (mg)</th>
                              <th>Potasio (miligramos)</th>
                              <th>Indice glicemico</th>
                              <th>Carga glicemica</th>
                            </tr>

                            <c:forEach items="${datoT42}" var="item">
                                <tr onclick="obtener('${item.alimento}','${item.cantidadSugerida}','${item.unidad}',1)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.cantidadSugerida}"/></td>
                                    <td><c:out value="${item.unidad}"/></td>
                                    <td><c:out value="${item.pesoBrutoRedondeado_g}"/></td>
                                    <td><c:out value="${item.pesoNeto_g}"/></td>
                                    <td><c:out value="${item.energia_Kcal}"/></td>
                                    <td><c:out value="${item.proteina_g}"/></td>
                                    <td><c:out value="${item.lipidos_g}"/></td>
                                    <td><c:out value="${item.hidratosDeCarbono_g}"/></td>
                                    <td><c:out value="${item.Fibra_g}"/></td>
                                    <td><c:out value="${item.vitamiaA_ugRE}"/></td>
                                    <td><c:out value="${item.acidoAscorbico_mg}"/></td>
                                    <td><c:out value="${item.acidoFolico_ug}"/></td>
                                    <td><c:out value="${item.hierroNO_HEM_mg}"/></td>
                                    <td><c:out value="${item.potasio_mg}"/></td>
                                    <td><c:out value="${item.indiceGlicemico}"/></td>
                                    <td><c:out value="${item.cargaGlicemica}"/></td>
                                </tr>
                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                
                <!-- WENDY's -->
                <div id="t43" style="display: none;">
                    <table>
                        <caption>WENDY's</caption>
                        <tbody>
                            <tr>
                              <td></td>
                              <th>Alimento</th>
                              <th>Kilocalorias (gramos)</th>
                              <th>AOAMBAG</th>
                              <th>AOABAG</th>
                              <th>AOAMAG</th>
                              <th>Grasa sin proteina</th>
                              <th>Cereal sin grasa</th>
                              <th>Azucar sin grasa</th>
                              <th>Leche entera</th>
                              <th>Leche descremada</th>
                              <th>Verduras</th>
                              <th>Frutas</th>
                              <th>Leguminosas</th>
                              <th>Libre</th>
                            </tr>

                            <c:forEach items="${datoT43}" var="item">
                                <tr onclick="obtener('${item.alimento}',' ',' ',0)">
                                    <th><c:out value="${item.id}"/></th>
                                    <td><c:out value="${item.alimento}"/></td>
                                    <td><c:out value="${item.Kcal}"/></td>
                                    <td><c:out value="${item.aoambag}"/></td>
                                    <td><c:out value="${item.aoabag}"/></td>
                                    <td><c:out value="${item.aoamag}"/></td>
                                    <td><c:out value="${item.grasasSinProteina}"/></td>
                                    <td><c:out value="${item.cerealSinGrasa}"/></td>
                                    <td><c:out value="${item.azucarSinGrasa}"/></td>
                                    <td><c:out value="${item.lecheEntera}"/></td>
                                    <td><c:out value="${item.lecheDescremada}"/></td>
                                    <td><c:out value="${item.verduras}"/></td>
                                    <td><c:out value="${item.frutas}"/></td>
                                    <td><c:out value="${item.leguminosas}"/></td>
                                    <td><c:out value="${item.libre}"/></td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                
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
                        <a class="boton_aux" onclick="cambiar('desayuno_op1_txt')">Buscar alimentos</a>
                    </div>
                    <div id="desayuno_op2" class="bordes_c">
                        <textarea id="desayuno_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('desayuno_op2_txt')">Buscar alimentos</a>
                    </div>
                    <div id="desayuno_op3" class="bordes_c">
                        <textarea id="desayuno_op3_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('desayuno_op3_txt')">Buscar alimentos</a>
                    </div>

                    <div id="colacionm_t" class="bordes">
                        <div id="tipo_c1" class="texto_tipo" onclick="editar_c1(this)">Colación matutina <br><br>11:30 am</div>
                        <input type="time" id="edit_h_c1" style="display:none;" min="00:00" max="12:00" value="11:30">
                        <a id="boton_h2_c1" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_c1()">Cambiar</a>
                    </div>
                    <div id="colacionm_op1" class="bordes_c">
                        <textarea id="colacionm_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('colacionm_op1_txt')">Buscar alimentos</a>
                    </div>
                    <div id="colacionm_op2" class="bordes_c">
                        <textarea id="colacionm_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('colacionm_op2_txt')">Buscar alimentos</a>
                    </div>
                    <div id="colacionm_op3" class="bordes_c"></div>

                    <div id="comida_t" class="bordes">
                        <div id="tipo_c" class="texto_tipo" onclick="editar_c(this)">Comida <br><br>2:30 pm</div>
                        <input type="time" id="edit_h_c" style="display:none;" min="00:00" max="12:00" value="14:30">
                        <a id="boton_h2_c" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_c()">Cambiar</a>
                    </div>
                    <div id="comida_op1" class="bordes_c">
                        <textarea id="comida_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('comida_op1_txt')">Buscar alimentos</a>
                    </div>
                    <div id="comida_op2" class="bordes_c">
                        <textarea id="comida_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('comida_op2_txt')">Buscar alimentos</a>
                    </div>
                    <div id="comida_op3" class="bordes_c">
                        <textarea id="comida_op3_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('comida_op3_txt')">Buscar alimentos</a>
                    </div>

                    <div id="colacionv_t" class="bordes">
                        <div id="tipo_c2" class="texto_tipo" onclick="editar_c2(this)">Colación vespertina <br><br>6:00 pm</div>
                        <input type="time" id="edit_h_c2" style="display:none;" min="00:00" max="12:00" value="18:30">
                        <a id="boton_h2_c2" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_c2()">Cambiar</a>
                    </div>
                    <div id="colacionv_op1" class="bordes_c">
                        <textarea id="colacionv_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('colacionv_op1_txt')">Buscar alimentos</a>
                    </div>
                    <div id="colacionv_op2" class="bordes_c">
                        <textarea id="colacionv_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('colacionv_op2_txt')">Buscar alimentos</a>
                    </div>
                    <div id="colacionv_op3" class="bordes_c"></div>

                    <div id="cena_t" class="bordes">
                        <div id="tipo_ce" class="texto_tipo" onclick="editar_ce(this)">Cena <br><br>9:00 pm</div>
                        <input type="time" id="edit_h_ce" style="display:none;" min="00:00" max="12:00" value="20:30">
                        <a id="boton_h2_ce" class="boton_aux" style="display:none; cursor: pointer;" onclick="actualizar_ce()">Cambiar</a>
                    </div>
                    <div id="cena_op1" class="bordes_c">
                        <textarea id="cena_op1_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('cena_op1_txt')">Buscar alimentos</a>
                    </div>
                    <div id="cena_op2" class="bordes_c">
                        <textarea id="cena_op2_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('cena_op2_txt')">Buscar alimentos</a>
                    </div>
                    <div id="cena_op3" class="bordes_c">
                        <textarea id="cena_op3_txt" class="text_area_alimentos"></textarea>
                        <a class="boton_aux" onclick="cambiar('cena_op3_txt')">Buscar alimentos</a>
                    </div>

                    <div id="botones_1">
                        <input type="button" value="Guardar" class="guardar">
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