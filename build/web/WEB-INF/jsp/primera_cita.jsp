<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es_MX">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/png" href="favicon.png " />

        <!--Hoja de estilo-->
        
        <link rel="stylesheet" href="<c:url value="/resource/estilos/generales.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/pleca.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/footer.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/estilos_primera_cita.css" />" />
        <link rel="stylesheet" href="<c:url value="/resource/estilos/estilos_cronograma.css" />" />


        <!--Scripts-->
        
        <script type="text/javascript" src="resource/scrips/calendario_primera_cita.js"></script>
        <script type="text/javascript" src="resource/scrips/script.js"></script>
        <script type="text/javascript" src="resource/scrips/barra_script.js"></script>

        <title>Nutri-Coach</title>
    </head>

<body onscroll="bajar()">
    <header>
        <div class="container">
            <div id="pleca">
                <div id="logoSEP">
                    <a href="https://www.gob.mx/sep">
                        <img src="logo-sep.png" alt="SecretarÃ­a de EducaciÃ³n PÃºblica">
                    </a>
                </div>
                <div id="logoIPN">
                    <a href="https://www.ipn.mx">
                        <p>Instituto PolitÃ©cnico Nacional</p>
                        "La TÃ©cnica al Servicio de la Patria"
                    </a>
                    <img src="logo-ipn.jpg " alt="Instituto PolitÃ©cnico Nacional">
                </div>
            </div>
        </div>
        <!--Fin container-->
    </header>

    <div id="barra">
        <div class="container">
            <div id="cont_barra">
                <div id="imagen_barra">
                    <a href="inicio.htm "><img id="imagen" src="logo-nutri.png" alt="Nutri-Coach"></a>
                </div>
                <div id="menu">
                    <ul id="menu_nutrio">
                        <li><a class="texto_menu" href="">Mensajes</a></li>
                        <li><a class="texto_menu" href="">Pacientes</a></li>
                        <li><a class="texto_menu" href="">Foro</a></li>
                        <li><a class="texto_menu" href="">Dietas</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div id="contenido">
        <div class="container">
            <h1>Primera cita</h1>
            <div id="texto">
                AquÃ­ podrÃ¡s agendar tu primera cita. Solo ingresa algunos datos para conocer tu disponibilidad y la de un especialista, de esta forma te podremos atender a la brevedad. </div>
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
                                <option value="2017">2017</option>
                                <option value="2018">2018</option>
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
                            <div class="c_dia" id="lunes">Lun.</div>
                            <div class="c_dia">Mar.</div>
                            <div class="c_dia">MiÃ©.</div>
                            <div class="c_dia">Jue.</div>
                            <div class="c_dia">Vie.</div>
                            <div class="c_dia">SÃ¡b.</div>
                            <div class="c_dia">Dom.</div>
                            <script>
                                dias();
                            </script>
                        </thead>
                    </table>

                </div>
                <div id="programar">


                    <p>InformaciÃ³n de la cita</p>

                    <div class="texto">InstituciÃ³n</div>
                    <select id="institucion" class="primera_cita">
                        <option value="nulo"></option>
                        <option >Escuela Superior de CÃ³mputo (ESCOM)</option>
                    </select>

                    <div class="texto">NutriÃ³logo</div>
                    <select id="nutriologo" class="primera_cita">
                        <option value="nulo">  </option>
                        <option>Alvarez Hernandez Zeth</option>
                    </select>

                    <div class="texto">DirecciÃ³n</div>
                    <p id="direccion_nutri">
                        Estado de no se donde
                    </p>

                    <div class="texto">Fecha de la cita</div>

                    <p id="fecha_cita">No seleccionada...</p>

                    <div class="texto">Horarios disponibles:</div>

                    <select id="horas_disponibles" class="primera_cita">
                        <option></option>
                    </select>

                    <section>
                        Captcha
                    </section>

                    <div id="boton">Confirmar</div>

                </div>
            </div>
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
            <p>Â© 2018 ESCOMPROJECT - Todos Los Derechos Reservados</p>
            <br/>
            <p>
                <a class="hiperfooter" href="tyc.htm ">TÃ©rminos y condiciones</a>

                <a class="hiperfooter" id="politicas" href="politicas.htm ">PolÃ­ticas de privacidad</a>
            </p>
            <br/>

            <!--Fin Modal TÃ©rminos y condiciones-->
        </div>
    </footer>
</body>

</html>