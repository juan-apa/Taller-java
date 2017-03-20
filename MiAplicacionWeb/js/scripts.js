$(document).ready(function(){
	var divItems = {
		destino: $('#div-destino'),
		precio: $('#div-precio'),
		radioBtn: $('#elegirListado')
	};

	var textFields= {
		destino: $('#inp-destino'),
		precioMin: $('#inp-precio-min'),
		precioMax: $('#inp-precio-max')
	};

	var opcRadioBtn= {
		destino: $('#elegirListado-destino'),
		precio: $('#elegirListado-precio')
	};

	var funcionalidadRadioBtn = {
		mostrarDestino: function(){
			if(! $(divItems.precio).hasClass('displayNone')){
				$(divItems.precio).addClass('displayNone');
			}
			if($(divItems.destino).hasClass('displayNone')){
				$(divItems.destino).removeClass('displayNone');
			}
		},
		mostrarPrecio: function(){
			if(! $(divItems.destino).hasClass('displayNone')){
				$(divItems.destino).addClass('displayNone');
			}
			if($(divItems.precio).hasClass('displayNone')){
				$(divItems.precio).removeClass('displayNone');
			}
		}
	};

	/*Como por defecto viene marcado el radioButton de mostrar listado
	por destino, pongo para mostrar destino y esconder precio*/
	funcionalidadRadioBtn.mostrarDestino();

	/*Cuando le hago click a alguno de los radioButton llamo a las
	funciones que se encargan de mostrar el seleccionado y esconder
	el que no esta seleccionado.*/
	$(opcRadioBtn.destino).on('click', function(){
		funcionalidadRadioBtn.mostrarDestino();
	});

	$(opcRadioBtn.precio).on('click', function(){
		funcionalidadRadioBtn.mostrarPrecio();
	});



});
