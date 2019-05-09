function fecha2() {
    let fecha=new Date();
                            let cadena=fecha.getFullYear()+"-"+(fecha.getMonth()+1)+"-"+fecha.getUTCDate()+" "+fecha.getHours()+":"+
                                    fecha.getMinutes()+":"+fecha.getSeconds();
                            console.log(cadena);
                            
                        
                            document.getElementById("fecha").value=cadena;
                   
}

