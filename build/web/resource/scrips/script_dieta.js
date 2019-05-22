function dias() {
    let tabla = document.getElementById("tabla");

    let cuerpoT = document.createElement("TBODY");
    cuerpoT.setAttribute('id', 'cuerpo');
    tabla.appendChild(cuerpoT);

    for (var c = 0; c < 5; c++) {
        let fila = document.createElement("TR");
        fila.className = "comida";
        cuerpoT.appendChild(fila);
        for (var d = 0; d < 7; d++) {
            let dia = document.createElement("TD");
            dia.className = "dia";

            fila.appendChild(dia);
        }
    }
}

function editar(campo) {
    campo.style.display = "none";
    let tipo = document.getElementById("tipo");
    tipo.style.display = "none";

    let hora = document.getElementById("edit_h");
    hora.style.display = "block";
    hora.style.marginTop = "5px";
    hora.style.marginBottom = "25px";

    document.innerHTML = "<br>";

    let btn1 = document.getElementById("boton_h2");
    btn1.style.display = "block";
    btn1.style.position = "absolute";
    btn1.style.marginTop = "30px";


}

function actualizar() {
    let hora = document.getElementById("edit_h");
    var x = hora.value;
    hora.style.display = "none";

    let btn1 = document.getElementById("boton_h2");
    btn1.style.display = "none";

    let tipo = document.getElementById("tipo");
    tipo.style.display = "block";
    tipo.innerHTML = "Desayuno <br><br> " + x + " hr.";
    tipo.style.height = "50%";

}

function editar_c1(campo) {
    campo.style.display = "none";
    let tipo = document.getElementById("tipo_c1");
    tipo.style.display = "none";

    let hora = document.getElementById("edit_h_c1");
    hora.style.display = "block";
    hora.style.marginTop = "5px";
    hora.style.marginBottom = "25px";

    document.innerHTML = "<br>";

    let btn1 = document.getElementById("boton_h2_c1");
    btn1.style.display = "block";
    btn1.style.position = "absolute";
    btn1.style.marginTop = "30px";

}

function actualizar_c1() {
    let hora = document.getElementById("edit_h_c1");
    var x = hora.value;
    hora.style.display = "none";

    let btn1 = document.getElementById("boton_h2_c1");
    btn1.style.display = "none";

    let tipo = document.getElementById("tipo_c1");
    tipo.style.display = "block";
    tipo.innerHTML = "Colación matutina <br><br> " + x + " hr.";
    tipo.style.height = "50%";

}

function editar_c(campo) {
    campo.style.display = "none";
    let tipo = document.getElementById("tipo_c");
    tipo.style.display = "none";

    let hora = document.getElementById("edit_h_c");
    hora.style.display = "block";
    hora.style.marginTop = "5px";
    hora.style.marginBottom = "25px";

    document.innerHTML = "<br>";

    let btn1 = document.getElementById("boton_h2_c");
    btn1.style.display = "block";
    btn1.style.position = "absolute";
    btn1.style.marginTop = "30px";

}

function actualizar_c() {
    let hora = document.getElementById("edit_h_c");
    var x = hora.value;
    hora.style.display = "none";

    let btn1 = document.getElementById("boton_h2_c");
    btn1.style.display = "none";

    let tipo = document.getElementById("tipo_c");
    tipo.style.display = "block";
    tipo.innerHTML = "Comida <br><br> " + x + " hr.";
    tipo.style.height = "50%";

}

function editar_c2(campo) {
    campo.style.display = "none";
    let tipo = document.getElementById("tipo_c2");
    tipo.style.display = "none";

    let hora = document.getElementById("edit_h_c2");
    hora.style.display = "block";
    hora.style.marginTop = "5px";
    hora.style.marginBottom = "25px";

    document.innerHTML = "<br>";

    let btn1 = document.getElementById("boton_h2_c2");
    btn1.style.display = "block";
    btn1.style.position = "absolute";
    btn1.style.marginTop = "30px";

}

function actualizar_c2() {
    let hora = document.getElementById("edit_h_c2");
    var x = hora.value;
    hora.style.display = "none";

    let btn1 = document.getElementById("boton_h2_c2");
    btn1.style.display = "none";

    let tipo = document.getElementById("tipo_c2");
    tipo.style.display = "block";
    tipo.innerHTML = "Colación vespertina <br><br> " + x + " hr.";
    tipo.style.height = "50%";

}


function editar_ce(campo) {
    campo.style.display = "none";
    let tipo = document.getElementById("tipo_ce");
    tipo.style.display = "none";

    let hora = document.getElementById("edit_h_ce");
    hora.style.display = "block";
    hora.style.marginTop = "5px";
    hora.style.marginBottom = "25px";

    document.innerHTML = "<br>";

    let btn1 = document.getElementById("boton_h2_ce");
    btn1.style.display = "block";
    btn1.style.position = "absolute";
    btn1.style.marginTop = "30px";

}

function actualizar_ce() {
    let hora = document.getElementById("edit_h_ce");
    var x = hora.value;
    hora.style.display = "none";

    let btn1 = document.getElementById("boton_h2_ce");
    btn1.style.display = "none";

    let tipo = document.getElementById("tipo_ce");
    tipo.style.display = "block";
    tipo.innerHTML = "Colación vespertina <br><br> " + x + " hr.";
    tipo.style.height = "50%";

}