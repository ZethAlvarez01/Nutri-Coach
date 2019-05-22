<!DOCTYPE html>
<html lang="es_MX">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/png" href="favicon.png " />
    <link rel="stylesheet" href="generales.css " />
    <link rel="stylesheet" href="pleca.css" />
    <link rel="stylesheet" href="footer.css " />
    <link rel="stylesheet" href="dieta_nutriologo.css">
    <!--Hoja de estilo-->
    <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript" src="script_dieta.js"></script>
    <script type="text/javascript" src="obtener_data.js"></script>

    <title>Nutri-Coach</title>
</head>

<body>
    <header>
        <div class="container">
            <div id="pleca">
                <div id="logoSEP">
                    <a href="https://www.gob.mx/sep">
                        <img src="logo-sep.png" alt="Secretaría de Educación Pública">
                    </a>
                </div>
                <div id="logoIPN">
                    <a href="https://www.ipn.mx">
                        <p>Instituto Politécnico Nacional</p>
                        "La Técnica al Servicio de la Patria"
                    </a>
                    <img src="logo-ipn.jpg " alt="Instituto Politécnico Nacional">
                </div>
            </div>
        </div>
        <!--Fin container-->
    </header>

    <div id="barra">
        <div class="container">
            <a href="inicio.htm "><img id="imagen" src="logo-nutri.png" alt="Nutri-Coach"></a>
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
                            var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
                            var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
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