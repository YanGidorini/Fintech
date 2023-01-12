function selectCategoria() {
	let categoria = document.querySelector("#categoriaDespesa");
	let select = document.querySelector("select#categoria");
	let options = select.querySelectorAll("option");
	
	options.forEach(item => {
		if (item.textContent == categoria.value){
			item.setAttribute("selected", "selected");
		}
		
	})
}

function selectCorretora() {
	let inputIdCorretora = document.querySelector("#corretora");
	let select = document.querySelector("select#corretoras");
	let options = select.querySelectorAll("option");
	
	options.forEach(item => {
		if (item.textContent == inputIdCorretora.value){
			item.setAttribute("selected", "selected");
		}
		
	})
}

function selectTipo() {
	let inputIdTipo = document.querySelector("#tipo");
	let select = document.querySelector("select#tipos");
	let options = select.querySelectorAll("option");
	
	
	options.forEach(item => {
		if (item.textContent == inputIdTipo.value){
			item.setAttribute("selected", "selected");
		}
		
	})
}