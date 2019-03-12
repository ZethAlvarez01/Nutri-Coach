function fecha() {
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "octubre", "Noviembre", "Diciembre"];
    let fecha = new Date();
    let textod = document.getElementById("fecha_dia");
    let texto = document.getElementById("mes_anio");
    textod.innerHTML += fecha.getDate();
    texto.innerHTML += mes[fecha.getMonth()] + " " + fecha.getFullYear();
}

function hora() {
    let fecha = new Date();
    let hora = document.getElementById("hora");
    var ampm = fecha.getHours() >= 12 ? 'pm' : 'am';
    hora.innerHTML += fecha.getHours() + ":" + fecha.getMinutes() + " " + ampm;
}