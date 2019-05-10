



function TypeDiv(ID,nombre,generales,generales2,generales3,boleta) {


  
  document.getElementById("informacion");
  
  
  document.getElementById("informacion").style.display = "block"; 
  document.getElementById("num_boleta").innerHTML=ID;
  document.getElementById("num_boleta2").value=boleta;
  document.getElementById("num_boleta3").value=boleta;
  document.getElementById("boletadiario").value=boleta;
  document.getElementById("nombreUser").innerHTML=nombre;
  document.getElementById("datosGenerales").innerHTML=generales;
  document.getElementById("datosGenerales2").innerHTML=generales2;
  document.getElementById("datosGenerales3").innerHTML=generales3;
  
  


  
  
  
}


function diario(){
     alert("hola");
    
     
  var indice = document.getElementById("hoja").selectedIndex;
  alert(indice);
  
  document.getElementsByName("x").value=indice;
   
}