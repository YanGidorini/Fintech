<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./parts/head.jsp"/>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="pt_BR" />
	<title>Receitas</title>
</head>

<body>
	<jsp:include page="./parts/header.jsp"/>
	
	<main class="receitas container-sm main-container">
        <div class="row justify-content-center">
            <div class="col-lg-9 col-xxl-8">
            
            	<div class="mb-4">
            		<span class="title title--decorated">Receitas</span>
            	</div> 
            	
            	<c:if test="${not empty deleteMsg}">
        			<script>window.alert("${deleteMsg}")</script>
      			</c:if>
	
            	<c:choose>
            		<c:when test="${not empty erro}">
            			<h1 class="title text-center">${erro}</h1>
            		</c:when>
            		
            		<c:when test="${not empty listReceitaMes}">
		            	<c:forEach items="${years}" var="year">      	
			            	<div class="d-flex justify-content-center mb-3">
			            		<span class="title title--decorated title--pill">${year}</span>
			            	</div>
			            	
				            <c:forEach items="${listReceitaMes}" var="receitaMes">
				            	<c:if test="${receitaMes.year == year}">
				            		<section class="mb-4">
									    <p class="d-flex justify-content-between py-3 mes_vigente my-0">${receitaMes.mes}<span><fmt:formatNumber value="${receitaMes.total}" type="currency" /> </span></p>
									    
									    <c:forEach items="${receitaMes.receitas}" var="receita">
											<div class="mt-3">
						                        <p class="d-flex justify-content-between mb-2 font-color-1">${receita.dtExtenso} <time>${receita.hora}</time></p>
						                        <div class="row">
						                            <div class="col-sm">
						                                <div class="card--default position-relative">
						                                    <div class="d-flex justify-content-between">
						                                        <span>${receita.nome}</span>
						                                        <span class="text-color--positive"><fmt:formatNumber value="${receita.valor}" type="currency"/></span>
						                                    </div>
						                                </div>
						                            </div>
						        
						                            <div class="col-auto d-flex mt-3 mt-sm-0">
						                                <button class="button me-2 me-sm-3" type="button" title="Editar despesa">
															<svg viewBox="0 0 24 24">
															    <path  d="M20.71,7.04C21.1,6.65 21.1,6 20.71,5.63L18.37,3.29C18,2.9 17.35,2.9 16.96,3.29L15.12,5.12L18.87,8.87M3,17.25V21H6.75L17.81,9.93L14.06,6.18L3,17.25Z" />
															</svg>
						                                </button>
						                                
						                                <button class="button" type="button" title="Excluir despesa" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="exclusion.value = ${receita.idReceita}">
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
						<h1 class="title text-center">Nenhuma receita adicionada ainda</h1>
					</c:otherwise>
				</c:choose>	

			</div>
		</div>
	</main>
	<jsp:include page="./parts/deleteModal.jsp"/>
	<jsp:include page="./parts/addBtn.jsp"/>
	<jsp:include page="./parts/footer.jsp"/>
	<jsp:include page="./parts/mobileMenu.jsp"/>
	<script type="text/javascript">
		document.querySelector("#deleteModalLabel").textContent = 'Excluir Receita';
		document.querySelector("#confirm-deletion").setAttribute('action','receitas');
	</script>
</body>
</html>