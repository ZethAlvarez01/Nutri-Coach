
var dia=null;
var comida=null;
var opcion=null;

var op=null;

function cambiar(nuevo,comida,opcion){
    
    for(let i=1;i<=43;i++){
           let elemento=document.getElementById("t"+i);
           elemento.style.display="none";
        }
          let cerrar=document.getElementById("smae");
          cerrar.style.display="none";
          
              let abrir=document.getElementById("smae");
    dia=nuevo;
    op=document.getElementById(dia);
    this.opcion=opcion;
    this.comida=comida;

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

function obtener(alimento,cantidad,unidad,flg,calorias){
    var val;
    if(flg==1){
        if(confirm("¿Deseas agregar este alimento a la dieta?")){
            console.log(op);
            val=op.getElementsByTagName("div");
            let inp=val[opcion].getElementsByTagName("input");
            console.log(inp);
            inp[0].value=cantidad;
            inp[1].value=unidad;
            inp[2].value=alimento;
            inp[3].value=calorias;
            
            abrir_menu("x");
            
            if((opcion+1)<=4){
                val[opcion+1].style="display: block;"
            }
        }
    }else{
        if(confirm("¿Deseas agregar este alimento a la dieta?")){
            console.log(op);
            val=op.getElementsByTagName("div");
            let inp=val[opcion].getElementsByTagName("input");
            console.log(inp);

            inp[0].style="display: none;";
            inp[1].style="display: none;";

            inp[2].value=alimento;
            inp[3].value=calorias;

            abrir_menu("x");

            if((opcion+1)<=4){
                val[opcion+1].style="display: block;"
            }  
        }
    }

    
    
}

function limpiar(elemento,indice){
    var val;
    op=document.getElementById(elemento);
    val=op.getElementsByTagName("div");
    let inp=val[indice].getElementsByTagName("input");
    console.log(inp);
    inp[0].value="";
    inp[1].value="";
    inp[2].value="";
    inp[3].value="";
    
        
    inp[0].style="display: block;";
    inp[1].style="display: block;";
    inp[2].style="display: block;";
    inp[3].style="display: block;";
    
    /*Alert*/
    
    op=document.getElementById(elemento);
    val=op.getElementsByTagName("div");
    inp=val[indice].getElementsByTagName("p");
    console.log(inp);
    inp[0].style="display: none;";
    inp[1].style="display: none;";
    inp[2].style="display: none;";
    inp[3].style="display: none;";
    
    if((indice+1)<=4){
        val[indice+1].style="display: block;"
    }
    
}


function mostrar(elemento,indice){
    var val;
    op=document.getElementById(elemento);
    val=op.getElementsByTagName("div");
    let inp=val[indice].getElementsByTagName("p");
    console.log(inp);
    inp[0].style="display: block;";
    inp[1].style="display: block;";
    inp[2].style="display: block;";
    inp[3].style="display: block;";
    
    if((indice+1)<=4){
        val[indice+1].style="display: block;"
    }
    
}
