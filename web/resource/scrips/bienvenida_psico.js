function fecha() {
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "octubre", "Noviembre", "Diciembre"];
    let fecha = new Date();
    let textod = document.getElementById("fecha_dia");
    let texto = document.getElementById("mes_anio");
    textod.innerHTML += fecha.getDate();
    texto.innerHTML += mes[fecha.getMonth()] + " " + fecha.getFullYear();
    let fechaHoy=new Date();
    let cadenaHoy=fechaHoy.getFullYear()+"-"+(fechaHoy.getMonth()+1)+"-"+fechaHoy.getUTCDate();
    var fechaCarga=cadenaHoy;
   // alert(fechaCarga); 
    myFunctionC(fechaCarga);
}

function myFunctionC(fechaCarga) {





   

   
    

    var fechaSeleccionada;
    
    fechaSeleccionada=fechaCarga;
   // alert(fechaSeleccionada);
    let elemento = document.getElementById("valor");
   // elemento.innerHTML = consultaChida;


     
                                    
                           // console.log("ESTA ES LA FECHA DE HOY: "+cadenaHoy);
         
    var empleado=document.getElementById("no_empleado").value;
   // alert(empleado);
    
 
  
     
   
     
     
     
    
        
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








function hora() {
    let fecha = new Date();
    let hora = document.getElementById("hora");
    var ampm = fecha.getHours() >= 12 ? 'pm' : 'am';
    hora.innerHTML += fecha.getHours() + ":" + fecha.getMinutes() + " " + ampm;
}

function generarCitas() {

    let elementoPapa = document.getElementById("citas");

    for (i = 0; i < 20; i++) {
        let elemento = document.createElement("DIV");
        elemento.setAttribute('id', 'cita');

        citas.appendChild(elemento);

        let elemento2 = document.createElement("LABEL");
        elemento2.setAttribute('id', 'tipo');

        let elemento3 = document.createElement("DIV");
        elemento3.setAttribute('id', 'cont');

        elemento.appendChild(elemento2);
        elemento.appendChild(elemento3);

        let elemento4 = document.createElement("P");
        elemento4.setAttribute('id', 'texto');
        elemento4.innerHTML = "Nombre  Hora   Fecha";

        elemento3.appendChild(elemento4);
    }






}