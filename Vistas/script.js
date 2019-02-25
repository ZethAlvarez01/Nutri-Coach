function TypeDiet(evt, typediet) {
    var i, x, tablinks;
    x = document.getElementsByClassName("dieta");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("boton");
    document.getElementById(typediet).style.display = "block";
    var y = document.getElementsByClassName(typediet);
    var smae = document.getElementsByClassName("smae");
    smae[0].style += "background: white;";
    var platillos = document.getElementsByClassName("platillos");
    platillos[0].style += "background: white;";
    var agregar = document.getElementsByClassName("agregar");
    agregar[0].style += "background: white;";
    y[0].style = "background: rgb(108, 29, 69); color: white;";

    if (typediet == "agregar") {
        document.getElementById("contenido").style.height = "880px";
        var agre = document.getElementsByClassName("menu_t");
        agre[0].style = "height: 880px;";
    } else {
        document.getElementById("contenido").style.height = "950px";
        var agre = document.getElementsByClassName("menu_t");
        agre[0].style = "height: 950px;";
    }
}

function TypeEspe(evt, typediet) {
    var i, x, tablinks;
    x = document.getElementsByClassName("especialista");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("boton");
    document.getElementById(typediet).style.display = "block";
    var y = document.getElementsByClassName(typediet);
    var smae = document.getElementsByClassName("nutriologo");
    smae[0].style += "background: white;";
    var platillos = document.getElementsByClassName("psicologo");
    platillos[0].style += "background: white;";
    var todos = document.getElementsByClassName("todos");
    todos[0].style += "background: white;";

    y[0].style = "background: rgb(108, 29, 69); color: white;";


}


function fecha() {
    var date = new Date();
    var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    document.getElementById("fecha").innerHTML = "Ciudad de México a " + date.getDay() + " de " + meses[date.getMonth()] + " de " + date.getFullYear();
}

function fecha2() {
    var date = new Date();
    var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    document.getElementById("fecha2").innerHTML = "Ciudad de México a " + date.getDay() + " de " + meses[date.getMonth()] + " de " + date.getFullYear();
}

/* Falta codigo para cambair emogis de color al seleccionar */