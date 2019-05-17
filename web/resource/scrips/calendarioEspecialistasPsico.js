




function dias() {
    var dia_text = ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado"];

    let tabla = document.getElementById("tabla");

    let cuerpoT = document.createElement("TBODY");
    cuerpoT.setAttribute('id', 'cuerpo');
    tabla.appendChild(cuerpoT);
    ////////////////////////////////////////////
    let fecha = new Date();
    var dt = new Date(fecha.getMonth() + ' ' + 1 + ', ' + fecha.getFullYear() + ' 12:00:00');
    //alert(dt.getUTCDay());

    let fecha2 = new Date(fecha.getFullYear(), fecha.getMonth() + 1, 0).getDate();
    console.log(fecha2);

    let dias_c = 1;
    let flg = 0;
    ////////////////////////////////////////////

    for (var s = 0; s < 6; s++) {
        let fila = document.createElement("TR");
        fila.className = "fila_clase";
        cuerpoT.appendChild(fila);
        for (var d = 0; d < 7; d++) {
            let dia = document.createElement("TD");
            dia.className = "dia_clase";
            dia.setAttribute('onclick', 'myFunction(this)');

            let numero = 0;

            if (d == dt.getUTCDay() + 2 && flg == 0) {
                dia.innerHTML = dias_c;
                dias_c++;
                flg = 1;
            } else if (flg == 1 && dias_c <= fecha2) {
                dia.innerHTML = dias_c;
                let dia_act = new Date();
                if (dias_c == dia_act.getDate()) {
                    dia.style.background += "rgb(108, 29, 69)";
                    dia.style.color += "white";
                }
                dias_c++;

            } else {
                dia.innerHTML = " ";
            }
            fila.appendChild(dia);
        }
    }
}

function fechaPorDia(año, dia) {
    var date = new Date(año, 0);
    return new Date(date.setDate(dia));
}


function numerar() {
    let anio = new Date();
    for (i = 1; i <= 365; i++) {
        let fecha = fechaPorDia(anio, i);
    }
}

function numero_mes(valor) {

    let numero = 0;
    switch (valor) {
        case "Enero":
            numero = 0;
            break;
        case "Febrero":
            numero = 1;
            break;
        case "Marzo":
            numero = 2;
            break;
        case "Abril":
            numero = 3;
            break;
        case "Mayo":
            numero = 4;
            break;
        case "Junio":
            numero = 5;
            break;
        case "Julio":
            numero = 6;
            break;
        case "Agosto":
            numero = 7;
            break;
        case "Septiembre":
            numero = 8;
            break;
        case "Octubre":
            numero = 9;
            break;
        case "Noviembre":
            numero = 10;
            break;
        case "Diciembre":
            numero = 11;
            break;
    }

    return numero;
}

function mover_mes() {
    /* Seleccion del mes */

    let valorM = document.getElementById("cajaMes").value;
    console.log(valorM);

    let numero = numero_mes(valorM);

    /* Seleccion del año */

    let valorA = document.getElementById("anio").value;
    console.log(valorA);


    /* Mato lo que traiga el calendario */

    let matar = document.getElementById("cuerpo").remove();

    let cuerpoT = document.createElement("TBODY");
    cuerpoT.setAttribute('id', 'cuerpo');
    tabla.appendChild(cuerpoT);

    ////////////////////////////////////////////

    let fecha = new Date();
    var dt = new Date(numero + 1 + ' ' + 1 + ', ' + valorA + ' 12:00:00');
    //alert(dt.getUTCDay());

    let fecha2 = new Date(valorA, numero + 1, 0).getDate();
    console.log(fecha2);

    let dias_c = 1;
    let flg = 0;

    ////////////////////////////////////////////

    for (var s = 0; s < 6; s++) {
        let fila = document.createElement("TR");
        fila.className = "fila_clase";
        cuerpoT.appendChild(fila);
        for (var d = 0; d < 7; d++) {
            let dia = document.createElement("TD");
            dia.className = "dia_clase";
            dia.setAttribute('onclick', 'myFunction(this)');

            let numero = 0;

            if (d == dt.getUTCDay() && flg == 0) {
                dia.innerHTML = dias_c;

                var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "octubre", "Noviembre", "Diciembre"];
                let valorA = document.getElementById("anio").value;
                let valorM = document.getElementById("cajaMes").value;


                let dia_act = new Date();
                if (dias_c == dia_act.getDate() && valorA == dia_act.getFullYear() && valorM == mes[dia_act.getMonth()]) {
                    dia.style.background += "rgb(108, 29, 69)";
                    dia.style.color += "white";
                }


                dias_c++;
                flg = 1;
            } else if (flg == 1 && dias_c <= fecha2) {
                dia.innerHTML = dias_c;

                var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "octubre", "Noviembre", "Diciembre"];
                let valorA = document.getElementById("anio").value;
                let valorM = document.getElementById("cajaMes").value;

                let dia_act = new Date();
                if (dias_c == dia_act.getDate() && valorA == dia_act.getFullYear() && valorM == mes[dia_act.getMonth()]) {
                    dia.style.background += "rgb(108, 29, 69)";
                    dia.style.color += "white";
                }


                dias_c++;
            } else {
                dia.innerHTML = " ";
            }

            fila.appendChild(dia);
        }
    }

}

function actualizar() {
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "octubre", "Noviembre", "Diciembre"];
    let caja = document.getElementById("cajaMes");

    let fecha = new Date();

    caja.value = mes[fecha.getMonth()];

    let caja2 = document.getElementById("anio");

    caja2.value = fecha.getFullYear();
}

function myFunction(obj) {
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "octubre", "Noviembre", "Diciembre"];

    var fecha = new Date();
    var tabla = document.getElementsByClassName("dia_clase");

    let valorA = document.getElementById("anio").value;
    let valorM = document.getElementById("cajaMes").value;

    for (i = 0; i < 42; i++) {
        tabla[i].style.background = "white";
        if (tabla[i].innerHTML == fecha.getDate() && valorM == mes[fecha.getMonth()] && valorA == fecha.getFullYear()) {

            tabla[i].style.background = "rgb(108, 29, 69)";

        }
    }


    var fecha_dia = document.getElementById("fecha_dia");
    var mes_anio = document.getElementById("mes_anio");

    if (obj.innerHTML != " ") {
        obj.style.background = "red";
        fecha_dia.innerHTML = obj.innerHTML;
        mes_anio.innerHTML=valorM + " " + valorA;
    }
    

    let getMes = numero_mes(valorM)+1;
    
   
    


   

   
    

    var fechaSeleccionada = valorA + "-" + getMes + "-" + obj.innerHTML;
    alert(fechaSeleccionada);
    let elemento = document.getElementById("valor");
   // elemento.innerHTML = consultaChida;


     let fechaHoy=new Date();
     let cadenaHoy=fechaHoy.getFullYear()+"-"+(fechaHoy.getMonth()+1)+"-"+fechaHoy.getUTCDate();
                                    
                           // console.log("ESTA ES LA FECHA DE HOY: "+cadenaHoy);
         
    var empleado=document.getElementById("no_empleado").value;
   // alert(empleado);
    
     if(valorA<fechaHoy.getFullYear()){
         // console.log("AÑO MENOR: "+cadenaHoy);
         $("#horas_disponibles").empty();
     }
 //     if(getMes<(fechaHoy.getMonth()+1)){
         // console.log("MES MENOR: ");
  //       $("#horas_disponibles").empty();
  //   }
     if(obj.innerHTML<fechaHoy.getUTCDate()){
         // console.log("Dia MENOR: ");
         
     }
     
     if(valorA>= fechaHoy.getFullYear() && getMes<(fechaHoy.getMonth()+1) && obj.innerHTML<fechaHoy.getUTCDate() ){
          console.log("Dia Y MES MENOR: ");
              $("#citas").empty();
    $("#citas").append("<h1>No hay citas</h1>");
         
     }
     
     if(valorA>= fechaHoy.getFullYear() && getMes<(fechaHoy.getMonth()+1) && obj.innerHTML>=fechaHoy.getUTCDate()  ){
         console.log("Dia MES: ");
             $("#citas").empty();
        $("#citas").append("<h1>No hay citas</h1>");
     }
     
      if(valorA>= fechaHoy.getFullYear() && getMes==(fechaHoy.getMonth()+1) && obj.innerHTML<fechaHoy.getUTCDate()  ){
          console.log("Dia MENOR: ");
              $("#citas").empty();
          $("#citas").append("<h1>No hay citas</h1>");
       
     }
      if(valorA>=fechaHoy.getFullYear() && getMes>(fechaHoy.getMonth()+1) && obj.innerHTML<fechaHoy.getUTCDate()  ){
           $.ajax({
        url : 'mostrarHorarioPsicologo.htm',
                        data:{fechaConsulta:fechaSeleccionada, no_empleadoConsulta:empleado},
                        dataType: "json",
                        content:"application/json;  charset=utf-8",
                        
                        success : function (data){

                               
                               if (data=== null) {
                               $("#citas").empty();
                              $("#citas").append("<h1>No hay citas</h1>");
                          }
                          else{
                           $("#citas").empty();
                                        $.each(data, function(i, option) {
                                        $("#citas").append( option.nombre, " "+ option.ap_uno,+" "+option.ap_dos +" Horario: "+  option.horario 
                                                + " <input type='submit'> Atender cita </input> <input value='"+option.no_cita+"' />");
                                        });   
                          }
                               
                                        //alert(data);
                                },
                                error:function(x) {
                                Console.log(x); }
                       
    });
    
    
 
    
    
    
    
     }
     
     
     if(valorA>=fechaHoy.getFullYear() && getMes>=(fechaHoy.getMonth()+1) && obj.innerHTML>=fechaHoy.getUTCDate() ){
        
              $.ajax({
        url : 'mostrarHorarioPsicologo.htm',
                        data:{fechaConsulta:fechaSeleccionada, no_empleadoConsulta:empleado},
                        dataType: "json",
                        content:"application/json;  charset=utf-8",
                        
                        success : function (data){

                          if (data=== null) {
                               $("#citas").empty(); 
                              $("#citas").append("<h1>No hay citas</h1>");
                          }
                          else{
                           $("#citas").empty();
                                        $.each(data, function(i, option) {
                                        
                                        
                                        $("#citas").append( option.nombre, " "+ option.ap_uno+" "+option.ap_dos +" Horario: "+  option.horario +"<br>");
                                        
                                                
                                        });   
                          }
                               
                                        //alert(data);
                                },
                                error:function(x) {
                                Console.log(x); }
                       
    });
     }






}