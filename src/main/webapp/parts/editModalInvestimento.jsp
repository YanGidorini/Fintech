<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="editModal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style="height: auto;">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
    			<form class="form" action="investimentos" method="POST">
    				<input type="hidden" name="task" value="editar">
    				<input type="hidden" name="idInv" value="" id="edition">

                   	<div class="form__input-field">
                           <label for="name" class="form__label">Nome</label>
                           <input class="form__input" id="nome" type="text" name="name" maxlength="32" placeholder="Máx. 32 caracteres">
                    </div>
					
					<div class="form__input-field">
						<label for="valor" class="form__label">Valor</label>
						<input class="form__input" id="valor" type="text" name="valor" placeholder="R$ 999.999.999,99" maxlength="17">
					</div>
					
					<div class="form__input-group">	
						<div class="form__input-field form__input-field--grow0">
							<label for="dtRealizacao" class="form__label">Data realização</label>
							<input class="form__input cursor-pointer" id="dtRealizacao" type="date" name="dtRealizacao">
						</div>
						
						<div class="form__input-field form__input-field--grow0">
							<label for="dtVencimento" class="form__label">Data vencimento</label>
							<input class="form__input cursor-pointer" id="dtVencimento" type="date" name="dtVencimento">
						</div>
					</div>
					
					<div class="form__input-group">	
						<input type="hidden" value="" id="corretora">
                        <div class="form__input-field form__input-field--grow0">
                            <label for="corretora" class="form__label form__label--select">Corretora/Banco</label>
                            <select class="form__input form__input--select" id="corretoras" name="corretora">
                                <c:forEach var="i" items="${corretoras}">
                                	<option value="${i.idCorretora}">${i.nmCorretora}</option>
                                </c:forEach>
                            </select>
                      	</div>			                       		
                      		
						<input type="hidden" value="" id="tipo">
						<div class="form__input-field form__input-field--grow0">
                            <label for="tipo" class="form__label form__label--select">Tipo aplicação</label>
                            <select class="form__input form__input--select" id="tipos" name="tipo">
                                <c:forEach var="tipo" items="${tipos}">
                                	<option value="${tipo.idTipo}">${tipo.nmTipo}</option>
                                </c:forEach>
                            </select>
                      		</div>
					</div>
                    <button class="form__btn" type="submit">Salvar</button>
               </form>
            </div>
        </div>
    </div>
</div>      