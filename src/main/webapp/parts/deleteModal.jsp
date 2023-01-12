    <div class="modal fade" id="deleteModal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel"></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    Tem certeza que deseja excluir?
                </div>

                <div class="modal-footer">
                	<form id="confirm-deletion" action="" method="POST">
                		<input type="hidden" name="id" value="" id="exclusion">
                		<input type="hidden" name="task" value="excluir">
                    	<button type="submit" class="form__btn" style="width: auto;">Confirmar exclusão</button>
                    </form>	
                </div>
            </div>
        </div>
    </div>      