//se crea un IFFE para realizar el encapsulamiento de las variables
(function(busqueda) { //se declara una variable llamada busqueda
	'use strict';//se usa el modo estricto para corregir y evitar errores 

	var Tabla = (function(Arr) {//se declara una variable tipo IFFE

		var _input; //se declara la variable input donde se aplicara un evento

		function _onInputEvent(e) { //se le aplica el evento on input
			_input = e.target; //se le asigna el evento a _input
			var tables = busqueda.getElementsByClassName(_input.getAttribute('data-tablePs'));// se obtiene las columnas con el tipo de data-table
			Arr.forEach.call(tables, function(table) {
				Arr.forEach.call(table.tBodies, function(tbody) {
					Arr.forEach.call(tbody.rows, _filter);
				});
                                //se obtuvieron los datos de  tbody ignorando thead
			});
		}

		function _filter(row) {
			var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
			row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
                        //se les aplicara el filtro a cadaa una de las filas que se obtubieron
		}

		return {
			init: function() {
				var inputs = busqueda.getElementsByClassName('filtro');
				Arr.forEach.call(inputs, function(input) {
					input.oninput = _onInputEvent;
				});
			}
                        //mostrara los resultados obtenidos
		};
	})(Array.prototype);

	busqueda.addEventListener('readystatechange', function() {
		if (busqueda.readyState === 'complete') {
			Tabla.init();
		}
                //hace que la tabla este lista para proximos cambios
	});

})(document);

