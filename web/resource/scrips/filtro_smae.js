
var dia=null;

function cambiar(nuevo){
    dia=nuevo;
    let abrir=document.getElementById("smae");
    abrir.style.display="block";
}


function abrir_menu(boton){
    if(boton=="x"){
        for(let i=1;i<=43;i++){
           let elemento=document.getElementById("t"+i);
           elemento.style.display="none";
        }
          let cerrar=document.getElementById("smae");
          cerrar.style.display="none";
    }else{
        for(let i=1;i<=43;i++){
            let elemento=document.getElementById("t"+i);
            elemento.style.display="none";
        }
        
        let seleccion=document.getElementById(boton);
        seleccion.style.display="block";
    } 
}

function obtener(alimento,lista,dia){
    if(confirm("¿Deseas agregar este alimento a la dieta?")){
        console.log(alimento+" "+lista)
        alert(dia);
        let editar=document.getElementById(dia);
        editar.innerHTML=lista;
    }
}
