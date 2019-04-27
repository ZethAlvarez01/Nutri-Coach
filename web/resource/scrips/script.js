function TypeDiet(evt, typediet) {
    var i, x, tablinks;
    x = document.getElementsByClassName("dieta");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("boton");
    document.getElementById(typediet).style.display = "block";
    var y = document.getElementsByClassName(typediet);
    var smae = document.getElementsByClassName("smae");
    smae[0].style += "background: white;";
    var platillos = document.getElementsByClassName("platillos");
    platillos[0].style += "background: white;";
    var agregar = document.getElementsByClassName("agregar");
    agregar[0].style += "background: white;";
    y[0].style = "background: rgb(108, 29, 69); color: white;";

    if (typediet == "agregar") {
        document.getElementById("contenido").style.height = "880px";
        var agre = document.getElementsByClassName("menu_t");
        agre[0].style = "height: 880px;";
    } else {
        document.getElementById("contenido").style.height = "950px";
        var agre = document.getElementsByClassName("menu_t");
        agre[0].style = "height: 950px;";
    }
}

function TypeEspe(evt, typediet) {
    var i, x, tablinks;
    x = document.getElementsByClassName("especialista");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("boton");
    document.getElementById(typediet).style.display = "block";
    var y = document.getElementsByClassName(typediet);
    var smae = document.getElementsByClassName("psicologo");
    smae[0].style += "background: white;";
    var platillos = document.getElementsByClassName("nutriologo");
    platillos[0].style += "background: white;";
    var todos = document.getElementsByClassName("todos");
    todos[0].style += "background: white;";

    y[0].style = "background: rgb(108, 29, 69); color: white;";


}
function TypeOption() {
    var i, x, tablinks;
    x = document.getElementsByClassName("listaUsuarios");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
        
    }
   
    var cod = document.getElementById("listaUsuarios").value;
    if(document.getElementById("listaUsuarios").value==="nutriologos"){
    
    document.getElementById("nutriologo").style.display = "block";
    var y = document.getElementsByClassName("nutriologo");
    var nutri = document.getElementsByClassName("nutriologo");
    nutri[0].style += "background: white;";
    
}

if(document.getElementById("listaUsuarios").value==="pacientes"){
    
    document.getElementById("paciente").style.display = "block";
    var y = document.getElementsByClassName("paciente");
    var pacien = document.getElementsByClassName("paciente");
    pacien[0].style += "background: white;";
}

    if(document.getElementById("listaUsuarios").value==="psicologos"){
   
    document.getElementById("psicologo").style.display = "block";
    var y = document.getElementsByClassName("psicologo");
    var psico = document.getElementsByClassName("psicologo");
    psico[0].style += "background: white;";
}
    
    y[0].style = "background: rgb(108, 29, 69); color: white;";
    

}

function TypeDiv(ID,nombre,generales,generales2,generales3,estado,usuario) {
  var cambio = document.getElementById("usuario");
  
  document.getElementById("info_completa");
  
  
  document.getElementById("info_completa").style.display = "block"; 
  document.getElementById("num_boleta").innerHTML=ID;
  document.getElementById("nombreUser").innerHTML=nombre;
  document.getElementById("datosGenerales").innerHTML=generales;
  document.getElementById("datosGenerales2").innerHTML=generales2;
  document.getElementById("datosGenerales3").innerHTML=generales3;
  
  
  
  
  if(usuario==="paciente"){
     document.getElementById("estadoP").style.display = "block";  
  }
   if(usuario==="nutriologo"){
     document.getElementById("estadoN").style.display = "block";  
  }
   if(usuario==="psicologo"){
     document.getElementById("estadoPs").style.display = "block";  
  }
  

  if(estado.valueOf()==1){
    document.getElementById("etiqueta_estado").innerHTML="Activo"; 
    document.getElementById('activo').checked = true;
    
  }
    if(estado.valueOf()==2){
    document.getElementById("etiqueta_estado").innerHTML="Suspendido"; 
    document.getElementById('bloquear').checked = true;
  }
   if(estado.valueOf()==3){
    document.getElementById("etiqueta_estado").innerHTML="De baja"; 
    document.getElementById('baja').checked = true;
  }
    document.getElementById("etiqueta_estado").style.display="block"; 
      document.getElementById("estado").style.display="block"; 

}
function TypeDivPaciente(ID,nombre,generales,generales2,generales3) {

 
  
  document.getElementById("informacion");
  
  
  document.getElementById("informacion").style.display = "block"; 
  document.getElementById("num_boleta").innerHTML=ID;
  document.getElementById("nombreUser").innerHTML=nombre;
  document.getElementById("datosGenerales").innerHTML=generales;
  document.getElementById("datosGenerales2").innerHTML=generales2;
  document.getElementById("datosGenerales3").innerHTML=generales3;
  
  
  
  
  
  
}







function fecha() {
    var date = new Date();
    var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    document.getElementById("fecha").innerHTML = "Ciudad de México a " + date.getDay() + " de " + meses[date.getMonth()] + " de " + date.getFullYear();
}

function fecha2() {
    var date = new Date();
    var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    document.getElementById("fecha2").innerHTML = "Ciudad de México a " + date.getDay() + " de " + meses[date.getMonth()] + " de " + date.getFullYear();
}

function confirmacion() {
    var mensaje = "El estado del paciente cambio.";
    var opcion = confirm("¿Deseas cambiar estado del usuario?");
    if (opcion == true) {
        alert(mensaje);
    } else {
        alert("Estado NO cambiado");
    }

}

function expandir() {
    alert("Expandir informacion");
}

function regresar(){
    window.history.back()
}

/* Falta codigo para cambair emogis de color al seleccionar */