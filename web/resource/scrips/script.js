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
    var smae = document.getElementsByClassName("psicologo");
    smae[0].style += "background: white;";
    var platillos = document.getElementsByClassName("nutriologo");
    platillos[0].style += "background: white;";
    var todos = document.getElementsByClassName("todos");
    todos[0].style += "background: white;";

    y[0].style = "background: rgb(108, 29, 69); color: white;";


}
function TypeOption() {
    var i, x, tablinks;
    x = document.getElementsByClassName("listaUsuarios");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
        
    }
   
    var cod = document.getElementById("listaUsuarios").value;
    if(document.getElementById("listaUsuarios").value==="nutriologos"){
    
    document.getElementById("nutriologo").style.display = "block";
    var y = document.getElementsByClassName("nutriologo");
    var nutri = document.getElementsByClassName("nutriologo");
    nutri[0].style += "background: white;";
    
}

if(document.getElementById("listaUsuarios").value==="pacientes"){
    
    document.getElementById("paciente").style.display = "block";
    var y = document.getElementsByClassName("paciente");
    var pacien = document.getElementsByClassName("paciente");
    pacien[0].style += "background: white;";
}

    if(document.getElementById("listaUsuarios").value==="psicologos"){
   
    document.getElementById("psicologo").style.display = "block";
    var y = document.getElementsByClassName("psicologo");
    var psico = document.getElementsByClassName("psicologo");
    psico[0].style += "background: white;";
}
    
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

function confirmacion() {
    var mensaje = "El estado del paciente cambio.";
    var opcion = confirm("¿Deseas cambiar estado del usuario?");
    if (opcion == true) {
        alert(mensaje);
    } else {
        alert("Estado NO cambiado");
    }

}

function expandir() {
    alert("Expandir informacion");
}

/* Falta codigo para cambair emogis de color al seleccionar */