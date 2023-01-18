<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./parts/head.jsp"/>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="pt_BR" />
	<title>Minha Conta</title>
</head>
<body>
	<jsp:include page="./parts/header.jsp"/>
	
	<main class="container-sm main-container">
		
        <div class="row justify-content-center gap-3">
        	<div>
        		<p class="title title--decorated mb-0" style="width: fit-content">Meu Perfil</p>
        	</div>
            <div class="col-auto">
                <div class="card--default p-5 full-height">
                    <input type="file" id="input-photo" accept="image/*" class="d-none">
                    <label for="input-photo" class="account-photo mb-3">
                        <img src="${pageContext.request.contextPath}/resources/imgs/FotoPerfil.png" alt="foto de perfil">
                    </label>

                    <p class="title title--500 mb-0">Account Name</p>
                </div>
            </div>
            
            <div class="col-md mt-4 mt-md-0">
            	<div>
                	<p class="title title--decorated" style="width: fit-content">Alterar Dados</p>
                </div>	
                
                <c:if test="${not empty msg}">
					<div class="alert alert-success">${msg}</div>
				</c:if>
				<c:if test="${not empty erro}">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
                
                <div class="card--default">
                   <form class="form" action="usuario" method="POST">
                        <input type="hidden" value="editar" name="task">
  						<div class="form__input-field">
                            <label for="nome" class="form__label">Nome</label>
                            <input class="form__input" id="nome" type="text" name="nome" value="${user.nmUsuario}">
                        </div>

                        <div class="form__input-field">
                            <label for="email" class="form__label">E-mail</label>
                            <input  class="form__input" id="email" type="email" name="email" value="${user.email}">
                        </div>
                            
                        <div class="row row-gap-4">
                            <div class="col-sm-6">
		                        <div class="form__input-field">
		                            <label for="data" class="form__label">Data de nascimento</label>
		                            <input class="form__input " id="data" type="date" name="dtNasc" value="${user.dtNascimento }">
		                        </div>
                            </div>

                            <div class="col-sm-6 ">
								<div class="form__input-field">
		                            <label for="genero" class="form__label form__label--select">Gênero</label>
		                            <select class="form__input form__input--select" id="genero" name="genero" value="${user.genero}">
		                                <option value="M">Masculino</option>
		                                <option value="F">Feminino</option>
		                            </select>
		                        </div>
                            </div>
                        </div>

                        <button class="form__btn" type="submit">Salvar</button>
                   </form>
                </div>
            </div>

            <div class="col-md-5 mt-4 mt-md-0">
				<div>
                	<p class="title title--decorated" style="width: fit-content">Alterar Senha</p>
				</div>
				
				<c:if test="${not empty msgSenha}">
					<div class="alert alert-success">${msg}</div>
				</c:if>
				<c:if test="${not empty erroSenhagithu}">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
				
                <div class="card--default">
                    <form class="form" action="usuario" method="POST">
                    	<input type="hidden" value="editarSenha" name="task">
                        <div class="form__input-field">
                            <label for="pswd" class="form__label">Senha atual<span class="form__mandatory">*</span></label>
                            <input class="form__input" id="pswd" type="password">
                        </div>

                        <div class="form__input-field">
                            <label for="pswd" class="form__label">Senha nova<span class="form__mandatory">*</span></label>
                            <input class="form__input" id="pswd" type="password" placeholder="6 ou mais caracteres">
                        </div>
    
                        <div class="form__input-field">
                            <label for="pswd" class="form__label">Confirmar senha nova<span class="form__mandatory">*</span></label>
                            <input class="form__input" id="pswd" type="password" placeholder="Deve ser idêntica a senha nova">
                        </div>

                        <button class="form__btn" type="submit">Alterar</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
	
	
	<jsp:include page="./parts/addBtn.jsp"/>
	<jsp:include page="./parts/footer.jsp"/>
	<jsp:include page="./parts/mobileMenu.jsp"/>
</body>
</html>