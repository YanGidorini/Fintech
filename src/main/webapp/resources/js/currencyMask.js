function applyCurrencyMask(value) {
    // Replace any non-digit character with an empty string
	value = value.replace(/\D/g, "");
	
	// Convert the value to a number and format it with two decimal places
	return 'R$ ' + Number(value / 100).toLocaleString("pt-BR", {
		minimumFractionDigits: 2,
		maximumFractionDigits: 2
	})
}

const inputs = document.querySelectorAll("#valor");

inputs.forEach(valorInput => {
	valorInput.addEventListener('input', function() {
		this.value = applyCurrencyMask(this.value);
	}) 
})

