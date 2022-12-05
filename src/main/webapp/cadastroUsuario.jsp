<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./parts/head.jsp"/>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>Cadastre-se</title>
</head>
<body>
    <main class="container-sm main-container d-flex flex-column text-center">
        <picture class="header__logo--login mb-4">
            <source srcset="./resources/imgs/FintechLogoDark.png" media="(min-width: 1024px)">
            <img src="./resources/imgs/FintechLogoDark.png" alt="Logo Fintech">
        </picture>
			
        <div class="row justify-content-center px-2 p-sm-0">
            <div class="col-sm-8 col-lg-4">
            
         		<c:if test="${not empty msg}">
					<div class="alert alert-success">
						${msg} 
						<a href="usuario?task=login">Entrar na conta</a>
					</div>
				</c:if>
				<c:if test="${not empty erro}">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
            
                <p class="title text-start">Cadastre-se</p>
                <div class="card-default p-4 mb-3 position-relative">
                    <form class="form text-start" action="usuario" method="POST">
                    	<input type="hidden" value="cadastrar" name="task">
                        <div class="form__input">
                            <label for="email" class="form__label">E-mail<span class="mandatory">*</span></label>
                            <input id="email" type="email" name="email" required>
                        </div>
                        
                        <div class="form__input">
                            <label for="pswd" class="form__label">Senha<span class="mandatory">*</span></label>
                            <input id="pswd" type="password" name="pswd" placeholder="6 ou mais caracteres" required>
                        </div>

                        <div class="form__input">
                            <label for="nome" class="form__label">Nome<span class="mandatory">*</span></label>
                            <input id="nome" type="text" name="nome" required>
                        </div>
                                 
                        <div class="form__input">
                            <label for="data" class="form__label">Data de nascimento<span class="mandatory">*</span></label>
                            <input id="data" type="date" name="dtNasc" required>
                        </div>

                        <div class="form__input--select">
                            <label for="genero" class="form__label form__label--select">Gênero<span class="mandatory">*</span></label>
                            <select id="genero" name="genero" required>
                                <option value="" selected disabled>Selecione</option>
                                <option value="M">Masculino</option>
                                <option value="F">Feminino</option>
                            </select>
                        </div>

                        <button class="form__btn" type="submit">Cadastrar</button>
                    </form>
                </div>

            </div>
            <p class="mb-0">Já se cadastrou? <a href="usuario?task=login">Entre</a></p>
        </div>
    </main>
    
    <jsp:include page="./parts/footerLogin.jsp"/>
</body>
</html>