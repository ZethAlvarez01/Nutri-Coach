<%-- 
    Document   : Generar_dietas_nutriologo
    Created on : 18/05/2019, 11:41:40 PM
    Author     : zetok
--%>

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
        <link rel="stylesheet" href="<c:url value="/resource/estilos/dieta_nutriologo.css" />" />
        
        <!--Scripts-->
        
        <script type="text/javascript" src="resource/scrips/script.js"></script>
        <script type="text/javascript" src="resource/scrips/fecha.js"></script>
        
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
                         <a href="expedientePaciente.htm "><img id="imagen" src="<c:url value="/resource/imagenes/logo-nutri.png" />" alt="Nutri-Coach"></a>
                     </div>
                     <div id="menu">
                        <c:forEach items="${datos}" var="item"> 
                            <ul id="menu_nutrio">
                                <form:form method="post" commandName="Paciente">
                                    <li><a class="texto_menu" href="expedientePaciente.htm">Expediente</a></li>
                                    <li><a class="texto_menu" href="foro.htm">Foro</a></li>
                                    <li><input type="submit" class="texto_menu" name="cerrar" value="Cerrar Sesion"></li>
                                    <form:input path="no_boleta" placeholder="${item.no_boleta}" value="${item.no_boleta}" type="hidden" />
                                </form:form>
                            </ul>
                        </c:forEach> 


                    </div>
                </div>

            </div>
        </div>

        <div id="contenido">
            <div class="container">
                <h1>Plan de alimentacion personalizado para ${paciente}</h1>
                <div id="tabla_contenido">
                    <div id="datos">
                        <ul id="datos_p">
                            <li>Edad: ${Edad}</li>
                            <li>Estatura: ${Estatura}</li>
                            <li>Peso: ${Peso}</li>
                            <li id="fecha"></li>

                            <script>
                                fecha3();
                            </script>

                        </ul>

                        <ul id="datos_c">
                            <li>Motivo de la consulta: ${Motivo}</li>
                            <li>Calculo de calorias actuales: ${Calorias}</li>
                            <li>Ajuste en calorias: <input type="number"></li>
                            <li>Calorias por consumir: ${Calorias_x_consumir}</li>
                        </ul>

                    </div>
                    <div id="dieta">
                        <div id="titulo_t">Tipo de comida</div>
                        <div id="titulo_op1">Opcion 1</div>
                        <div id="titulo_op2">Opcion 2</div>
                        <div id="titulo_op3">Opcion 3</div>

                        <div id="desayuno_t">Desayuno <br> 9:00</div>
                        <div id="desayuno_op1">
                            Menu 1
                        </div>
                        <div id="desayuno_op2">
                            Menu 2
                        </div>
                        <div id="desayuno_op3">
                            Menu 3
                        </div>

                        <div id="colacionm_t">Colación matutina <br> 9:00</div>
                        <div id="colacionm_op1">Opcion 1</div>
                        <div id="colacionm_op2">Opcion 2</div>
                        <div id="colacionm_op3"></div>

                        <div id="comida_t">Comida <br> 9:00</div>
                        <div id="comida_op1">
                            Menu 1
                        </div>
                        <div id="comida_op2">
                            Menu 2
                        </div>
                        <div id="comida_op3">
                            Menu 3
                        </div>

                        <div id="colacionv_t">Colación vespertina <br> 9:00</div>
                        <div id="colacionv_op1">Opcion 1</div>
                        <div id="colacionv_op2">Opcion 2</div>
                        <div id="colacionv_op3"></div>

                        <div id="cena_t">Cena <br> 9:00</div>
                        <div id="cena_op1">
                            Menu 1
                        </div>
                        <div id="cena_op2">
                            Menu 2
                        </div>
                        <div id="cena_op3">
                            Menu 3
                        </div>

                    </div>

                </div>
                <div id="boton">Cargar</div>
                <div id="boton">Guardar y enviar</div>
            </div>
        </div>

        <!--Fin contenido-->
        <footer>
            <div class="container">
                <p id="visita"></p>
                <ul id="redes">
                    <li>
                        <a href="https://www.facebook.com/escomipnmx/">
                            <img src="facebook.png" />
                        </a>
                    </li>
                    <li>
                        <a href="https://twitter.com/escomunidad">
                            <img src="twitter.png" />
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/ipn_oficial/">
                            <img src="instagram.png" />
                        </a>
                    </li>
                </ul>
                <!--Fin Redes sociales-->
                <p>© 2018 ESCOMPROJECT - Todos Los Derechos Reservados</p>
                <br/>
                <p>
                    <a class="hiperfooter" href="tyc.htm ">Términos y condiciones</a>

                    <a class="hiperfooter" id="politicas" href="politicas.htm ">Políticas de privacidad</a>
                </p>
                <br/>

                <!--Fin Modal Términos y condiciones-->
            </div>
        </footer>
    </body>

</html>
