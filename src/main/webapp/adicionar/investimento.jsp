<!DOCTYPE html>
<html>
	<head>
	<jsp:include page="../parts/head.jsp"/>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>Novo investimento</title>
	</head>
<body>
	<jsp:include page="../parts/header.jsp"/>
	
	<main class="adicionar container-sm main-container">
		<div class="row justify-content-center">
	    	<div class="col-lg-9 col-xxl-8">
	    		
		    	<p class="title">Adicionar</p>
		    	
		    	<div class="adicionar__buttons row justify-content-center align-items-center mb-5">
		    		<div class="col-6 col-md-3">
	        			<a href="receita"><span class="adicionar__btn ">Receita</span></a>
	    			</div>
	    			<div class="col-6 col-md-3">
	    				<a href="despesa"><span class="adicionar__btn">Despesa</span></a>
	    			</div>
	    			<div class="col-6 col-md-3">
	    				<a href="investimento"><span class="adicionar__btn adicionar__btn--selected">Investimento</span></a>
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
			    			<p class="title text-center">Novo investimento</p>
			    			<form class="form" action="investimento" method="POST">
			    			
				    			
			                    	<div class="form__input-field">
			                            <label for="name" class="form__label">Nome<span class="form__mandatory">*</span></label>
			                            <input class="form__input" id="name" type="text" name="name" maxlength="32" placeholder="Máx. 32 caracteres" required>
			                        </div>
									
									<div class="form__input-field">
										<label for="valor" class="form__label">Valor<span class="form__mandatory">*</span></label>
										<input class="form__input" id="valor" type="text" name="valor" placeholder="R$ 999.999.999,99" maxlength="17" required>
									</div>
									
									<div class="form__input-group">	
										<div class="form__input-field form__input-field--grow0">
											<label for="dtRealizacao" class="form__label">Data realização<span class="form__mandatory">*</span></label>
											<input class="form__input cursor-pointer" id="dtRealizacao" type="date" name="dtRealizacao" required>
										</div>
										
										<div class="form__input-field form__input-field--grow0">
											<label for="dtVencimento" class="form__label">Data vencimento</label>
											<input class="form__input cursor-pointer" id="dtVencimento" type="date" name="dtVencimento">
										</div>
									</div>
									
									<div class="form__input-group">	
				                        <div class="form__input-field form__input-field--grow0">
				                            <label for="corretora" class="form__label form__label--select">Corretora/Banco<span class="form__mandatory">*</span></label>
				                            <select class="form__input form__input--select" id="corretora" name="corretora" required>
				                                <c:forEach var="i" items="${corretoras}">
				                                	<option value="${i.idCorretora}">${i.nmCorretora}</option>
				                                </c:forEach>
				                            </select>
			                       		</div>			                       		
										
										<div class="form__input-field form__input-field--grow0">
				                            <label for="tipo" class="form__label form__label--select">Tipo aplicação<span class="form__mandatory">*</span></label>
				                            <select class="form__input form__input--select" id="tipo" name="tipo" required>
				                                <c:forEach var="i" items="${tipos}">
				                                	<option value="${i.idTipo}">${i.nmTipo}</option>
				                                </c:forEach>
				                            </select>
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