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