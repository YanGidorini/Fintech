<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="editModal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
    			<form class="form" action="objetivos" method="POST">
    				<input type="hidden" name="task" value="editar">
    				<input type="hidden" name="idObjetivo" value="" id="edition">
                   	
                   	<div class="form__input-field">
                        <label for="name" class="form__label">Nome</label>
                    	<input class="form__input" id="nome" type="text" name="nome" maxlength="32" placeholder="Máx. 32 caracteres">
                    </div>	
					
					<div class="form__input-group">	
						<div class="form__input-field">
							<label for="valor" class="form__label">Valor meta</label>
							<input class="form__input" id="valor" type="text" name="valor" placeholder="R$ 999.999.999,99" maxlength="17">
						</div>
						
						<div class="form__input-field">
							<label for="valor" class="form__label">Valor atual</label>
							<input class="form__input" id="vlAtual" type="text" name="vlAtual" placeholder="R$ 999.999.999,99" maxlength="17">
						</div>
					</div>
					
					<span class="mb-0">Data limite:</span>
						<div class="form__input-group">	
							<div class="form__input-field form__input-field--grow0">
		                        <label for="mes" class="form__label form__label--select">Mês</label>
		                        <select class="form__input form__input--select" id="mes" name="mes">
                        			 <option value="01">janeiro</option>
                                     <option value="02">fevereiro</option>
                                     <option value="03">março</option>
                                     <option value="04">abril</option>
                                     <option value="05">maio</option>
                                     <option value="06">junho</option>
                                     <option value="07">julho</option>
                                     <option value="08">agosto</option>
                                     <option value="09">setembro</option>
                                     <option value="10">outubro</option>
                                     <option value="11">novembro</option>
                                     <option value="12">dezembro</option>
		                        </select>
           					</div>
              					
                       		<div class="form__input-field form__input-field--grow0">
	                            <label for="ano" class="form__label form__label--select">Ano</label>
	                            <select class="form__input form__input--select" id="ano" name="ano">
	                                <c:forEach begin="2022" end="2053" var="i">
	                              		<option value="${i}">${i}</option>
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