function openExpediente(tipo) {
    var i;
    var x = document.getElementsByClassName("expediente_c");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }

    document.getElementById(tipo).style.display = "block";

    switch (tipo) {
        case 'Historial':
            document.getElementById(tipo).style.height = "550px";
            document.getElementById("contenido").style.height = "850px";

            break;
        case 'Ginecologico':
            document.getElementById(tipo).style.height = "440px";
            document.getElementById("contenido").style.height = "750px";

            break;
        case 'Fisica':
            document.getElementById(tipo).style.height = "600px";
            document.getElementById("contenido").style.height = "850px";

            break;
        case 'Indicadores':
            document.getElementById(tipo).style.height = "700px";
            document.getElementById("contenido").style.height = "950px";

            break;
        case 'Diagnostico':
            document.getElementById(tipo).style.height = "500px";
            document.getElementById("contenido").style.height = "780px";
            break;
        default:
            break;
    }
}