<!DOCTYPE html>
<html>
	<head>
	<jsp:include page="../parts/head.jsp"/>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>Nova despesa</title>
	</head>
<body>
	<jsp:include page="../parts/header.jsp"/>
	
	<main class="adicionar container-sm main-container">
		<div class="row justify-content-center">
	    	<div class="col-lg-9 col-xxl-8">
	    		
		    	<div class="mb-5"><span class="title title--decorated">Adicionar</span></div> 
		    	
		    	<div class="adicionar__buttons row justify-content-center align-items-center mb-5">
		    		<div class="col-6 col-md-3">
	        			<a href="receita"><span class="adicionar__btn ">Receita</span></a>
	    			</div>
	    			<div class="col-6 col-md-3">
	    				<a href="despesa"><span class="adicionar__btn adicionar__btn--selected">Despesa</span></a>
	    			</div>
	    			<div class="col-6 col-md-3">
	    				<a href="investimento"><span class="adicionar__btn">Investimento</span></a>
	    			</div>
	    			<div class="col-6 col-md-3">
	    				<a href="objetivo"><span class="adicionar__btn">Objetivo</span></a>
	    			</div>
		    	</div>
	    		
	    		<div class="row justify-content-center">    		
	    			<div class="col-auto">
						<c:if test="${not empty msg}">
							<div class="alert alert-success text-center">${msg}</div>
						</c:if>
						<c:if test="${not empty erro}">
							<div class="alert alert-danger text-center">${erro}</div>
						</c:if>
	    				
			    		<div class="card--default">
			    			<p class="title text-center">Nova despesa</p>
			    			<form class="form" action="despesa" method="POST">
			    			
				    			<div class="form__input-group">
			                    	<div class="form__input-field">
			                            <label for="name" class="form__label">Nome<span class="form__mandatory">*</span></label>
			                            <input class="form__input" id="name" type="text" name="name" maxlength="32" placeholder="Máx. 32 caracteres" required>
			                        </div>

			                        <div class="form__input-field form__input-field--grow0">
			                            <label for="categoria" class="form__label form__label--select">Categoria<span class="form__mandatory">*</span></label>
			                            <select class="form__input form__input--select" id="categoria" name="categoria" required>
			                            	<option value="" selected disabled>Selecione</option>
			                                <c:forEach var="i" items="${categorias}">
			                                	<option value="${i.idCategoria}">${i.nmCategoria}</option>
			                                </c:forEach>
			                            </select>
		                       		</div>			                       		
								</div>
								
								<div class="form__input-group" >	
									<div class="form__input-field">
										<label for="valor" class="form__label">Valor<span class="form__mandatory">*</span></label>
										<input class="form__input" id="valor" type="text" name="valor" placeholder="R$ 999.999.999,99" maxlength="17" required>
									</div>

									<div class="form__input-field form__input-field--grow0">
										<label for="date" class="form__label">Data<span class="form__mandatory">*</span></label>
										<input class="form__input cursor-pointer" id="date" type="date" name="date" required>
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
		</div>		
	</main>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/currencyMask.js"></script>
	<jsp:include page="../parts/footer.jsp"/>
	<jsp:include page="../parts/mobileMenu.jsp"/>
</body>
</html>