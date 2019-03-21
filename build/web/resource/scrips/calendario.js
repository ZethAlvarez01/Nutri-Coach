function dias() {
    var dia_text = ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado"];

    let tabla = document.getElementById("tabla");

    let cuerpoT = document.createElement("TBODY");
    cuerpoT.setAttribute('id', 'cuerpo');
    tabla.appendChild(cuerpoT);


    ////////////////////////////////////////////

    let fecha = new Date();
    var dt = new Date(fecha.getMonth() + ' ' + 1 + ', ' + fecha.getFullYear() + ' 12:00:00');
    //alert(dt.getUTCDay());

    let fecha2 = new Date(fecha.getFullYear(), fecha.getMonth() + 1, 0).getDate();
    console.log(fecha2);

    let dias_c = 1;
    let flg = 0;

    ////////////////////////////////////////////

    for (var s = 0; s < 6; s++) {
        let fila = document.createElement("TR");
        fila.className = "fila_clase";
        cuerpoT.appendChild(fila);
        for (var d = 0; d < 7; d++) {
            let dia = document.createElement("TD");
            dia.className = "dia_clase";
            let numero = 0;

            if (d == dt.getUTCDay() && flg == 0) {
                dia.innerHTML = dias_c;
                dias_c++;
                flg = 1;
            } else if (flg == 1 && dias_c <= fecha2) {
                dia.innerHTML = dias_c;
                dias_c++;
            } else {
                dia.innerHTML = " ";
            }



            fila.appendChild(dia);
        }
    }
}

function fechaPorDia(año, dia) {
    var date = new Date(año, 0);
    return new Date(date.setDate(dia));
}


function numerar() {
    for (i = 1; i < 366; i++) {
        let fecha = fechaPorDia(2017, i);
    }
}

function numero_mes(valor) {

    let numero = 0;
    switch (valor) {
        case "Enero":
            numero = 0;
            break;
        case "Febrero":
            numero = 1;
            break;
        case "Marzo":
            numero = 2;
            break;
        case "Abril":
            numero = 3;
            break;
        case "Mayo":
            numero = 4;
            break;
        case "Junio":
            numero = 5;
            break;
        case "Julio":
            numero = 6;
            break;
        case "Agosto":
            numero = 7;
            break;
        case "Septiembre":
            numero = 8;
            break;
        case "Octubre":
            numero = 9;
            break;
        case "Noviembre":
            numero = 10;
            break;
        case "Diciembre":
            numero = 11;
            break;
    }

    return numero;
}

function mover_mes() {
    /* Seleccion del mes */

    let valorM = document.getElementById("cajaMes").value;
    console.log(valorM);

    let numero = numero_mes(valorM);

    /* Seleccion del año */

    let valorA = document.getElementById("anio").value;
    console.log(valorA);


    /* Mato lo que traiga el calendario */

    let matar = document.getElementById("cuerpo").remove();

    let cuerpoT = document.createElement("TBODY");
    cuerpoT.setAttribute('id', 'cuerpo');
    tabla.appendChild(cuerpoT);

    ////////////////////////////////////////////

    let fecha = new Date();
    var dt = new Date(numero + 1 + ' ' + 1 + ', ' + valorA + ' 12:00:00');
    //alert(dt.getUTCDay());

    let fecha2 = new Date(valorA, numero + 1, 0).getDate();
    console.log(fecha2);

    let dias_c = 1;
    let flg = 0;

    ////////////////////////////////////////////

    for (var s = 0; s < 6; s++) {
        let fila = document.createElement("TR");
        fila.className = "fila_clase";
        cuerpoT.appendChild(fila);
        for (var d = 0; d < 7; d++) {
            let dia = document.createElement("TD");
            dia.className = "dia_clase";
            let numero = 0;

            if (d == dt.getUTCDay() && flg == 0) {
                dia.innerHTML = dias_c;
                dias_c++;
                flg = 1;
            } else if (flg == 1 && dias_c <= fecha2) {
                dia.innerHTML = dias_c;
                dias_c++;
            } else {
                dia.innerHTML = " ";
            }



            fila.appendChild(dia);
        }
    }

}

function actualizar() {
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "octubre", "Noviembre", "Diciembre"];
    let caja = document.getElementById("cajaMes");

    let fecha = new Date();

    caja.value = mes[fecha.getMonth()];

    let caja2 = document.getElementById("anio");

    caja2.value = fecha.getFullYear();
}