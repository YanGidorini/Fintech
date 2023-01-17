<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./parts/head.jsp"/>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="pt_BR" />
	<title>Dashboard</title>
</head>
<body>
	<jsp:include page="./parts/header.jsp"/>
	
	<main class="dashboard container-sm main-container">
        <div class="row justify-content-center">
            <div class="col-lg-9 col-xl-8">
            	<div class="mb-4">
            		<span class="title title--decorated">${nomeMes} de ${ano}</span>
            	</div>
            	<section class="row gy-2 gy-md-3 ">
                    <div class="col-md ">
                    	<c:if test="${sumReceitas != null}">
	                        <div class="card--default p-0 d-flex justify-content-between d-md-block">
	                            <p class="col-auto order-1 my-2 py-1 my-md-2"><fmt:formatNumber value="${sumReceitas}" type="currency"/></p>
	                            <div class="dashboard__tag dashboard__tag--positive d-flex d-md-block align-items-center px-2 px-sm-3 px-md-0 py-md-1">
	                                <svg class="dashboard__icon revenue" width="37" height="26" viewBox="0 0 37 26" fill="none" xmlns="http://www.w3.org/2000/svg">
	                                    <g clip-path="url(#clip0_767_380)">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M35.2248 9.5188L36.4747 0.9729L28.1687 3.34048L30.546 5.42206L20.0487 17.2223L13.6017 10.7753L1.51352 22.8796L3.93115 25.2972L13.6017 15.6106L20.0487 22.0576L32.9602 7.53591L35.2248 9.5188Z"></path>
	                                    </g>
	                                    <defs>
	                                    <clipPath id="clip0_767_380">
	                                    <rect width="35.6757" height="25.1351" transform="translate(0.972977 0.432373)"></rect>
	                                    </clipPath>
	                                    </defs>
	                                </svg>
	                                <span>Receitas</span>
	                            </div>
	                        </div>
	                    </c:if>
	                    <c:if test="${sumReceitas == null}">
	                        <div class="card--default p-0 d-flex justify-content-between d-md-block">
	                            <p class="col-auto order-1 my-2 py-1 my-md-2"><fmt:formatNumber value="0" type="currency"/></p>
	                            <div class="dashboard__tag dashboard__tag--positive d-flex d-md-block align-items-center px-2 px-sm-3 px-md-0 py-md-1">
	                                <svg class="dashboard__icon revenue" width="37" height="26" viewBox="0 0 37 26" fill="none" xmlns="http://www.w3.org/2000/svg">
	                                    <g clip-path="url(#clip0_767_380)">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M35.2248 9.5188L36.4747 0.9729L28.1687 3.34048L30.546 5.42206L20.0487 17.2223L13.6017 10.7753L1.51352 22.8796L3.93115 25.2972L13.6017 15.6106L20.0487 22.0576L32.9602 7.53591L35.2248 9.5188Z"></path>
	                                    </g>
	                                    <defs>
	                                    <clipPath id="clip0_767_380">
	                                    <rect width="35.6757" height="25.1351" transform="translate(0.972977 0.432373)"></rect>
	                                    </clipPath>
	                                    </defs>
	                                </svg>
	                                <span>Receitas</span>
	                            </div>
	                        </div>
	                    </c:if>
                    </div>
        
                    <img src="${pageContext.request.contextPath}/resources/icons/minus.svg" alt="" class="col-md-1 d-none d-md-block">
        
                    <div class="col-md">
                    	<c:if test="${sumDespesas != null}">
	                        <div class="card--default p-0 d-flex justify-content-between d-md-block">
	                            <p class="my-2 py-1 my-md-2"><fmt:formatNumber value="${sumDespesas}" type="currency"/></p>
	                            <div class="dashboard__tag dashboard__tag--negative d-flex d-md-block align-items-center px-2 px-sm-3 px-md-0 py-md-1">
	                                <svg class="dashboard__icon spent" width="37" height="26" viewBox="0 0 37 26" fill="none" xmlns="http://www.w3.org/2000/svg">
	                                    <g clip-path="url(#clip0_767_376)">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M34.279 16.7516L35.5288 25.2975L27.2229 22.9299L29.6002 20.8483L19.1027 9.04795L12.6557 15.495L0.567596 3.3907L2.98522 0.973076L12.6557 10.6597L19.1027 4.2127L32.0144 18.7345L34.279 16.7516Z"></path>
	                                    </g>
	                                    <defs>
	                                    <clipPath id="clip0_767_376">
	                                    <rect width="35.6757" height="25.1351" transform="matrix(1 0 0 -1 0.567566 25.5676)"></rect>
	                                    </clipPath>
	                                    </defs>
	                                </svg>
	                                <span>Despesas</span>
	                            </div>
	                        </div>
	                    </c:if>
	                    <c:if test="${sumDespesas == null}">
	                        <div class="card--default p-0 d-flex justify-content-between d-md-block">
	                            <p class="my-2 py-1 my-md-2"><fmt:formatNumber value="0" type="currency"/></p>
	                            <div class="dashboard__tag dashboard__tag--negative d-flex d-md-block align-items-center px-2 px-sm-3 px-md-0 py-md-1">
	                                <svg class="dashboard__icon spent" width="37" height="26" viewBox="0 0 37 26" fill="none" xmlns="http://www.w3.org/2000/svg">
	                                    <g clip-path="url(#clip0_767_376)">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M34.279 16.7516L35.5288 25.2975L27.2229 22.9299L29.6002 20.8483L19.1027 9.04795L12.6557 15.495L0.567596 3.3907L2.98522 0.973076L12.6557 10.6597L19.1027 4.2127L32.0144 18.7345L34.279 16.7516Z"></path>
	                                    </g>
	                                    <defs>
	                                    <clipPath id="clip0_767_376">
	                                    <rect width="35.6757" height="25.1351" transform="matrix(1 0 0 -1 0.567566 25.5676)"></rect>
	                                    </clipPath>
	                                    </defs>
	                                </svg>
	                                <span>Despesas</span>
	                            </div>
	                        </div>
	                    </c:if>       
                    </div>
        
                    <img src="${pageContext.request.contextPath}/resources/icons/equal.svg" alt="" class="col-md-1 d-none d-md-block">
        
                    <div class="col-md">
	                    <c:choose>
	                    	<c:when test="${saldo > 0 }">
		                        <div id="saldo" class="card--default p-0 text-color--positive d-flex flex-column d-md-block">
		                            <p class="my-2 my-md-3 order-1">+ <fmt:formatNumber value="${saldo}" type="currency"/></p>
		                            <div class="dashboard__tag dashboard__tag--positive py-0 py-md-2">
		                                <span>Saldo</span>
		                            </div>
		                        </div>
		                	</c:when>
		                	
		                	<c:when test="${saldo < 0 }">
		  						<div id="saldo" class="card--default p-0 text-color--negative d-flex flex-column d-md-block">
		                            <p class="my-2 my-md-3 order-1"><fmt:formatNumber value="${saldo}" type="currency"/></p>
		                            <div class="dashboard__tag dashboard__tag--negative py-0 py-md-2">
		                                <span>Saldo</span>
		                            </div>
		                        </div>
		                	</c:when>
		                	
		                	<c:otherwise>
		                		<div id="saldo" class="card--default p-0 text-color--positive d-flex flex-column d-md-block">
		                            <p class="my-2 my-md-3 order-1"><fmt:formatNumber value="0" type="currency"/></p>
		                            <div class="dashboard__tag dashboard__tag--positive py-0 py-md-2">
		                                <span>Saldo</span>
		                            </div>
		                        </div>
		                	</c:otherwise>
                        </c:choose>  
                    </div>
                </section>    
                
                <section class="row mt-5">
                    <div class="col-md">
                    	<div class="mb-4">
                    		<span class="title title--decorated">Receita mais recente</span>
                    	</div>
                    	<p class="d-flex justify-content-between mb-2 font-color-1">${receitaRecente.dtExtenso} <time>${receitaRecente.hora}</time></p>
                    	<div class="card--default position-relative">
                            <div class="d-flex justify-content-between">
                                <span>${receitaRecente.nome}</span>
                                <span class="text-color--positive">+ <fmt:formatNumber value="${receitaRecente.valor}" type="currency"/></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md mt-4 mt-md-0">
                    	<div class="mb-4">
                    		<span class="title title--decorated">Despesa mais recente</span>
                    	</div>
                    	<p class="d-flex justify-content-between mb-2 font-color-1">${despesaRecente.dtExtenso} <time>${despesaRecente.hora}</time></p>
      					<div class="card--default position-relative">
                        	<div class="categoria__tag--inline ${categoriaClasses.get(despesaRecente.categoria.nmCategoria)}"></div>
                            <div class="d-flex justify-content-between">
                                <span>${despesaRecente.nome}</span>
                                <span class="text-color--negative">- <fmt:formatNumber value="${despesaRecente.valor}" type="currency"/></span>
                            </div>
                        </div>
                    </div>
                </section>             
			</div>
		</div>
	</main>		
	
	<jsp:include page="./parts/addBtn.jsp"/>
	<jsp:include page="./parts/footer.jsp"/>
	<jsp:include page="./parts/mobileMenu.jsp"/>
</body>
</html>