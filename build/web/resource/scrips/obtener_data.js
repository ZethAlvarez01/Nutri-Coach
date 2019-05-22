function convertirJSON() {
    var hora = document.getElementsByClassName("texto_tipo");

    var opciones = document.getElementsByClassName("text_area_alimentos");

    let val = hora.item(0).childNodes;
    let val1 = hora.item(1).childNodes;
    let val2 = hora.item(2).childNodes;
    let val3 = hora.item(3).childNodes;
    let val4 = hora.item(4).childNodes;

    var texto_json = '{"dieta":[' +
        '{"desayuno":[' +
        '{"hora":"' + val[3].textContent + '","op1":"' + opciones.item(0).value + '","op2":"' + opciones.item(1).value + '","op3":"' + opciones.item(2).value + '"} ]},' +
        '{"colacion1":[' +
        '{"hora":"' + val1[3].textContent + '","op1":"' + opciones.item(3).value + '","op2":"' + opciones.item(4).value + '"} ]},' +
        '{"comida":[' +
        '{"hora":"' + val2[3].textContent + '","op1":"' + opciones.item(5).value + '","op2":"' + opciones.item(6).value + '","op3":"' + opciones.item(7).value + '"} ]},' +
        '{"colacion2":[' +
        '{"hora":"' + val3[3].textContent + '","op1":"' + opciones.item(8).value + '","op2":"' + opciones.item(9).value + '"} ]},' +
        '{"cena":[' +
        '{"hora":"' + val4[3].textContent + '","op1":"' + opciones.item(10).value + '","op2":"' + opciones.item(11).value + '","op3":"' + opciones.item(12).value + '"} ]} ]}';



    console.log(texto_json);




}