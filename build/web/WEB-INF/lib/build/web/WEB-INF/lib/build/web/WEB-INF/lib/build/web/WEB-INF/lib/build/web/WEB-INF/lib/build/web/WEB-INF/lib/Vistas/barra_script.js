var x = 0;

function bajar() {

    let v = window.scrollY;

    let elem = document.getElementById("barra");
    let cont = document.getElementById("contenido");

    if (v >= 95) {
        elem.setAttribute('class', 'fijar');
        cont.style.marginTop = "95px";
    } else {
        elem.removeAttribute('class', 'fijar');
        cont.style.marginTop = "0px";
    }



}