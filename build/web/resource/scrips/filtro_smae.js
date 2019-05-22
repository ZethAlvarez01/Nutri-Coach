function abrir_menu(boton){
    for(let i=1;i<=43;i++){
        let elemento=document.getElementById("t"+i);
        elemento.style.display="none";
    }
    let seleccion=document.getElementById(boton);
    seleccion.style.display="block";
}

