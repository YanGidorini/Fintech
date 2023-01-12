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

<body>
	<jsp:include page="./parts/header.jsp"/>
	
	<main class="receitas container-sm main-container">
        <div class="row justify-content-center">
            <div class="col-lg-9 col-xxl-8">
            	
            	<section class="mb-4"> 
	            	<div class="mb-4">
	            		<span class="title title--decorated">Categorias</span>
	            	</div> 
					<div class="row mb-5">
	    				<c:forEach var="categoria" items="${categoriaClasses}">	
							<div class="col-6 col-sm-4 col-xl-3 d-flex align-items-center">
	                            <div class="categoria__tag ${categoria.value}"></div>${categoria.key}
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
            		
            		<c:when test="${not empty listDespesaMes}">
		            	<c:forEach items="${years}" var="year">      	
			            	<div class="d-flex justify-content-center mb-3">
			            		<span class="title title--decorated title--pill">${year}</span>
			            	</div>
			            	
				            <c:forEach items="${listDespesaMes}" var="despesaMes">
				            	<c:if test="${despesaMes.year == year}">
				            		<section class="mb-4">
									    <p class="d-flex justify-content-between py-3 mes_vigente my-0">${despesaMes.mes}<span><fmt:formatNumber value="${despesaMes.total}" type="currency" /> </span></p>
									    
									    <c:forEach items="${despesaMes.despesas}" var="despesa">
											<div class="mt-3">
						                        <p class="d-flex justify-content-between mb-2 font-color-1">${despesa.dtExtenso} <time>${despesa.hora}</time></p>
						                        <div class="row">
						                            <div class="col-sm">
						                                <div class="card--default position-relative">
						                                	<div class="categoria__tag--inline ${categoriaClasses.get(despesa.categoria.nmCategoria)}"></div>
						                                    <div class="d-flex justify-content-between">
						                                        <span>${despesa.nome}</span>
						                                        <span class="text-color--negative">- <fmt:formatNumber value="${despesa.valor}" type="currency"/></span>
						                                    </div>
						                                </div>
						                            </div>
						        
						                            <div class="col-auto d-flex mt-3 mt-sm-0">
						                                <button class="button me-2 me-sm-3" type="button" title="Editar despesa" data-bs-toggle="modal" data-bs-target="#editModal" onclick="edition.value = '${despesa.idDespesa}'; nome.value = '${despesa.nome}'; valor.value = '${despesa.valor}'; data.value = '${despesa.dtDespesa}' ; time.value = '${despesa.hora}'; categoriaDespesa.value = '${despesa.categoria.nmCategoria}'; selectCategoria() ">
															<svg viewBox="0 0 24 24">
															    <path  d="M20.71,7.04C21.1,6.65 21.1,6 20.71,5.63L18.37,3.29C18,2.9 17.35,2.9 16.96,3.29L15.12,5.12L18.87,8.87M3,17.25V21H6.75L17.81,9.93L14.06,6.18L3,17.25Z" />
															</svg>
						                                </button>
						                                
						                                <button class="button" type="button" title="Excluir despesa" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="exclusion.value = ${despesa.idDespesa}">
						                                    <svg viewBox="0 0 24 24">
															    <path  d="M6,19A2,2 0 0,0 8,21H16A2,2 0 0,0 18,19V7H6V19M8,9H16V19H8V9M15.5,4L14.5,3H9.5L8.5,4H5V6H19V4H15.5Z" />
															</svg>
						                                </button>
						                            </div>
						                            
						                        </div>
						                    </div>
									    </c:forEach>
									</section>
								</c:if>
							</c:forEach>		   
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<h1 class="title text-center">Nenhuma despesa adicionada ainda</h1>
					</c:otherwise>
				</c:choose>	

			</div>
		</div>
	</main>
	<jsp:include page="./parts/editModalDespesa.jsp"/>
	<jsp:include page="./parts/deleteModal.jsp"/>
	<jsp:include page="./parts/addBtn.jsp"/>
	<jsp:include page="./parts/footer.jsp"/>
	<jsp:include page="./parts/mobileMenu.jsp"/>
	<script type="text/javascript">
		document.querySelector("#editModalLabel").textContent = 'Editar Despesa';
		document.querySelector("#deleteModalLabel").textContent = 'Excluir Despesa';
		document.querySelector("form#confirm-deletion").setAttribute('action','despesas');
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/currencyMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/selectCategoria.js"></script>
</body>
</html>