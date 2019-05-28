
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

var arreglo=[];
var ind=0;

function obtener(alimento,cantidad,unidad,flg){
    if(flg==1){
        if(confirm("¿Deseas agregar este alimento a la dieta?")){
            var canti=document.createElement("form:p");
            var uni=document.createElement("form:p");
            var ali=document.createElement("form:p");
            
            var enter=document.createElement("br");
            
            var canttxt = document.createTextNode(cantidad); 
            var alitxt = document.createTextNode(" "+alimento+"\n"); 
            var unitxt = document.createTextNode(unidad+" de "); 
            
            canti.appendChild(canttxt);
            uni.appendChild(unitxt);
            ali.appendChild(alitxt);
            
            let editar=document.getElementById(dia);
            //editar.innerHTML+=cantidad+" "+unidad+" de "+alimento+"\n";
            editar.appendChild(canti);
            editar.appendChild(uni);
            editar.appendChild(ali);
            editar.appendChild(enter);
            arreglo[ind]=editar;
            ind++;
        }
    }else{
        if(confirm("¿Deseas agregar este alimento a la dieta?")){
            let editar=document.getElementById(dia);
            editar.innerHTML+=alimento+"\n";
        }
    }
    
}

