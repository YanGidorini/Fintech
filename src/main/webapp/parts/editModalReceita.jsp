<div class="modal fade" id="editModal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
    			<form class="form" action="receitas" method="POST">
    				<input type="hidden" name="task" value="editar">
    				<input type="hidden" name="idReceita" value="" id="edition">
                   	<div class="form__input-field">
                     	<label for="name" class="form__label">Nome</label>
                     	<input class="form__input" id="nome" type="text" name="name" maxlength="32" placeholder="Máx. 32 caracteres" required>
                    </div>

					<div class="form__input-group">			
						<div class="form__input-field">
							<label for="valor" class="form__label">Valor</label>
							<input class="form__input" id="valor" type="text" name="valor" placeholder="R$ 999.999.999,99" maxlength="17" required>
						</div>
					
						<div class="form__input-field form__input-field--grow0">
							<label for="date" class="form__label">Data</label>
							<input class="form__input cursor-pointer" id="data" type="date" name="date" required>
						</div>

						<div class="form__input-field form__input-field--grow0">
							<label for="date" class="form__label">Hora</label>
							<input class="form__input cursor-pointer" id="time" type="time" name="time">
						</div>   
                    </div>
                   	<button class="form__btn" type="submit">Adicionar</button>
               </form>
            </div>
        </div>
    </div>
</div>      