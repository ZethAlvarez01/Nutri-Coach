
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



function obtener(alimento,cantidad,unidad,flg){
    let elemento=0;
    if(flg==1){
        if(confirm("¿Deseas agregar este alimento a la dieta?")){
            elemento++;
            var canti=document.createElement("form:label");
            var uni=document.createElement("form:label");
            var ali=document.createElement("form:label");
            
            let canti_id="canti*"+elemento+"*"+dia;
            let uni_id="uni*"+elemento+"*"+dia;
            let ali_id="ali*"+elemento+"*"+dia;
            
            console.log(canti_id);
            console.log(uni_id);
            console.log(ali_id);
            
            canti.setAttribute("id",canti_id);
            uni.setAttribute("id",uni_id);
            ali.setAttribute("id",ali_id);
            
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

        }
    }else{
        if(confirm("¿Deseas agregar este alimento a la dieta?")){

            var ali=document.createElement("form:label");
            
            var alitxt = document.createTextNode(alimento+"\n"); 

            ali.appendChild(alitxt);
            
            let editar=document.getElementById(dia);
            //editar.innerHTML+=cantidad+" "+unidad+" de "+alimento+"\n";

            editar.appendChild(ali);
        }
    }
    
}

