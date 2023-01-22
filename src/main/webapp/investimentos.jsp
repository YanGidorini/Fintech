<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./parts/head.jsp"/>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="pt_BR" />
	<title>Investimentos</title>
</head>

<style>
	svg.investment{
		fill: var(--cta);
	}
</style>

<body>
	<jsp:include page="./parts/header.jsp"/>
	
	<main class="receitas container-sm main-container">
        <div class="row justify-content-center">
            <div class="col-lg-9 col-xxl-8">
            	
            	<section class="mb-4"> 
	            	<div class="mb-4">
	            		<span class="title title--decorated">Tipos de investimentos</span>
	            	</div> 
					<div class="row mb-5">
	    				<c:forEach var="tipo" items="${classesTipo}">	
							<div class="col-6 col-sm-4 col-xl-3 d-flex align-items-center">
	                            <div class="categoria__tag ${tipo.value}"></div>${tipo.key}
	                        </div>
		                 </c:forEach>
    				</div>
                </section>
	
            	<div class="mb-4">
            		<span class="title title--decorated">Investimentos</span>
            	</div> 
            	
            	<c:if test="${not empty msg}">
        			<script>window.alert("${msg}")</script>
      			</c:if>
	
            	<c:choose>
            		<c:when test="${not empty erro}">
            			<h1 class="title text-center">${erro}</h1>
            		</c:when>
            		
            		<c:when test="${not empty investimentos}">
            			<section class="investimento">
            				<c:forEach items="${investimentos}" var="inv">
								<div class="row mt-3">		            	   	
									<div class="col-md">
										<p class="font-color-1 text-end mb-2">${inv.dtRealizacaoToView}</p>
									
										<div class="card--default p-3 ps-4 text-start position-relative">
											<div class="categoria__tag--inline ${classesTipo.get(inv.tipo.nmTipo)}"></div>
												<p class="mb-1 investimentos__corretora">${inv.corretora.nmCorretora}</p>
												<p class="pb-2 mb-2 border-bottom border-2">${inv.nome}</p>
											<div class="d-flex justify-content-between align-items-end">
												<span class="investimentos__valor"><fmt:formatNumber value="${inv.valor}" type="currency"/></span>
												<span class="investimentos__corretora"> <c:if test="${inv.dtVencimentoToView != null}">vence ${inv.dtVencimentoToView}</c:if></span>
											</div>
										</div>                                      
									</div>  
									            
									<div class="col-auto d-flex mt-3 mt-sm-0 investimentos__btns">
										<button class="button button--min-height me-2 me-sm-3" type="button" title="Editar" data-bs-toggle="modal" data-bs-target="#editModal" 
										onclick="
											edition.value = '${inv.idInvestimento}'; 
											nome.value = '${inv.nome}'; valor.value = '${inv.valor}'; 
											dtRealizacao.value = '${inv.dtRealizacao}'; 
											dtVencimento.value = '${inv.dtVencimento}'; 
											corretora.value = '${inv.corretora.idCorretora}'; 
											tipo.value = '${inv.tipo.idTipo}'; "
										>
											<svg viewBox="0 0 24 24">
											    <path  d="M20.71,7.04C21.1,6.65 21.1,6 20.71,5.63L18.37,3.29C18,2.9 17.35,2.9 16.96,3.29L15.12,5.12L18.87,8.87M3,17.25V21H6.75L17.81,9.93L14.06,6.18L3,17.25Z" />
											</svg>
										</button>
										                  
										<button class="button button--min-height" type="button" title="Excluir" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="exclusion.value = ${inv.idInvestimento}">
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
						<h1 class="title text-center">Nenhum investimento adicionado ainda</h1>
					</c:otherwise>
				</c:choose>	

			</div>
		</div>
	</main>
	<jsp:include page="./parts/editModalInvestimento.jsp"/>
	<jsp:include page="./parts/deleteModal.jsp"/>
	<jsp:include page="./parts/addBtn.jsp"/>
	<jsp:include page="./parts/footer.jsp"/>
	<jsp:include page="./parts/mobileMenu.jsp"/>
	<script type="text/javascript">
		document.querySelector("#editModalLabel").textContent = 'Editar Investimento';
		document.querySelector("#deleteModalLabel").textContent = 'Excluir Investimento';
		document.querySelector("form#confirm-deletion").setAttribute('action','investimentos');
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/currencyMask.js"></script>
</body>
</html>