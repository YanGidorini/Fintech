<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./parts/head.jsp"/>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="pt_BR" />
	<title>Objetivos</title>
</head>

<style>
	.header svg.target > circle:nth-child(-n + 2){
		stroke: var(--cta);
	}
	.header svg.target > circle:last-child{
		fill: var(--cta);
	}
</style>

<body>
	<jsp:include page="./parts/header.jsp"/>
	
	<main class="receitas container-sm main-container">
        <div class="row justify-content-center">
            <div class="col-lg-7 col-xxl-6">
	            <div class="mb-4">
            		<span class="title title--decorated">Objetivos</span>
            	</div> 
            	
            	<c:if test="${not empty msg}">
        			<script>window.alert("${msg}")</script>
      			</c:if>
	
            	<c:choose>
            		<c:when test="${not empty erro}">
            			<h1 class="title text-center">${erro}</h1>
            		</c:when>
            		
            		<c:when test="${not empty objetivos}">
						<section class="objetivos">		            	
							<c:forEach items="${objetivos}" var="obj">
								<div class="row mt-3">
									<div class="col-md">     
										<div class="d-flex justify-content-between mb-2">
											<span><fmt:formatNumber value="${obj.valor}" type="currency"/></span>
											<span class="font-color-1">Até ${obj.dtFimObjetivo}</span>
										</div>
										
										<div class="card--default p-3">
											<p class="text-start">${obj.nome}</p>
											<p class="objetivo__valor-atual"><fmt:formatNumber value="${obj.vlAtualObjetivo}" type="currency"/></p>
											<progress value="${obj.porcentagem}" max="100"></progress>
											<p class="text-start font-color-0 objetivo__percentual">${obj.porcentagem}%</p>
										</div>                                   
									</div>  
									
									<div class="col-auto d-flex mt-3 mt-sm-0 investimentos__btns">
										<button class="button button--min-height me-2 me-sm-3" type="button" title="Editar" data-bs-toggle="modal" data-bs-target="#editModal" 
												onclick="
												edition.value = '${obj.idObjetivo}';
												nome.value = '${obj.nome}';
												valor.value = '${obj.valor}';
												vlAtual.value = '${obj.vlAtualObjetivo}';
												<c:set var="dataSplit" value="${obj.dtFimObjetivo.split('/')}"/>									
												mes.value = '${dataSplit[0]}';
												ano.value = '${dataSplit[1]}'; "
										                   >
											<svg viewBox="0 0 24 24">
												<path  d="M20.71,7.04C21.1,6.65 21.1,6 20.71,5.63L18.37,3.29C18,2.9 17.35,2.9 16.96,3.29L15.12,5.12L18.87,8.87M3,17.25V21H6.75L17.81,9.93L14.06,6.18L3,17.25Z" />
											</svg>
										</button>
										                  
										<button class="button button--min-height" type="button" title="Excluir" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="exclusion.value = ${despesa.idDespesa}">
											<svg viewBox="0 0 24 24">
												<path  d="M6,19A2,2 0 0,0 8,21H16A2,2 0 0,0 18,19V7H6V19M8,9H16V19H8V9M15.5,4L14.5,3H9.5L8.5,4H5V6H19V4H15.5Z" />
											</svg>
										</button>
									</div>        
								</div>   
							</c:forEach>
						</section>	
					</c:when>
					
					<c:otherwise>
						<h1 class="title text-center">Nenhum objetivo adicionado ainda</h1>
					</c:otherwise>
				</c:choose>	

			</div>
		</div>
	</main>
	<jsp:include page="./parts/editModalObjetivo.jsp"/>
	<jsp:include page="./parts/deleteModal.jsp"/>
	<jsp:include page="./parts/addBtn.jsp"/>
	<jsp:include page="./parts/footer.jsp"/>
	<jsp:include page="./parts/mobileMenu.jsp"/>
	<script type="text/javascript">
		document.querySelector("#editModalLabel").textContent = 'Editar Objetivo';
		document.querySelector("#deleteModalLabel").textContent = 'Excluir Objetivo';
		document.querySelector("form#confirm-deletion").setAttribute('action','objetivos');
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/currencyMask.js"></script>
</body>
</html>