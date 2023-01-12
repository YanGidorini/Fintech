function selectCategoria() {
	let categoria = document.querySelector("#categoriaDespesa");
	let select = document.querySelector("select#categoria");
	let options = select.querySelectorAll("option");
	
	console.log(options)
	options.forEach(item => {
		if (item.textContent == categoria.value){
			item.setAttribute("selected", "selected");
		}
		
	})
}

