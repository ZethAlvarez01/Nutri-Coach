function fecha2() {
    let fecha=new Date();
                            let cadena=fecha.getFullYear()+"-"+(fecha.getMonth()+1)+"-"+fecha.getUTCDate()+" "+fecha.getHours()+":"+
                                    fecha.getMinutes()+":"+fecha.getSeconds();
                            console.log(cadena);
                            
                        
                            document.getElementById("fecha").value=cadena;
                   
}

function fecha3(){
    var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
                                var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
                                var f = new Date();
                                let cadena = diasSemana[f.getDay()] + ", " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear();

                                document.getElementById("fecha").innerHTML = cadena;
}

