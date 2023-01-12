<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="editModal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
    			<form class="form" action="despesas" method="POST">
    				<input type="hidden" name="task" value="editar">
    				<input type="hidden" name="idDespesa" value="" id="edition">
                   	
                	<div class="form__input-group">
                    	<div class="form__input-field">
                            <label for="name" class="form__label">Nome</label>
                            <input class="form__input" id="nome" type="text" name="name" maxlength="32" placeholder="Máx. 32 caracteres">
                        </div>
						
						<input type="hidden" value="" id="categoriaDespesa">
                        <div class="form__input-field form__input-field--grow0">
                            <label for="categoria" class="form__label form__label--select">Categoria</label>
                            <select class="form__input form__input--select" id="categoria" name="categoria">
                                <c:forEach var="i" items="${categorias}">
                                	<option value="${i.idCategoria}">${i.nmCategoria}</option>
                                </c:forEach>
                            </select>
                      	</div>			                       		
					</div>
					
					<div class="form__input-group" >	
						<div class="form__input-field">
							<label for="valor" class="form__label">Valor</label>
							<input class="form__input" id="valor" type="text" name="valor" placeholder="R$ 999.999.999,99" maxlength="17">
						</div>

						<div class="form__input-field form__input-field--grow0">
							<label for="date" class="form__label">Data</label>
							<input class="form__input cursor-pointer" id="data" type="date" name="date">
						</div>

						<div class="form__input-field form__input-field--grow0">
							<label for="date" class="form__label">Hora</label>
							<input class="form__input cursor-pointer" id="time" type="time" name="time">
						</div>     
                   </div>
                   <button class="form__btn" type="submit">Salvar</button>
               </form>
            </div>
        </div>
    </div>
</div>      